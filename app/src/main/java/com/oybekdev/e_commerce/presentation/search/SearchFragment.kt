package com.oybekdev.e_commerce.presentation.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.FragmentSearchBinding
import com.oybekdev.e_commerce.domain.model.ProductQuery
import com.oybekdev.e_commerce.presentation.filter.FilterFragment
import com.oybekdev.e_commerce.presentation.search.SearchFragmentDirections.toFilterFragment
import com.oybekdev.e_commerce.presentation.search.adapters.RecentAdapter
import com.oybekdev.e_commerce.presentation.search.adapters.SearchProductsAdapter
import com.oybekdev.e_commerce.util.BaseFragment
import com.oybekdev.e_commerce.util.hideKeyboard
import com.oybekdev.e_commerce.util.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel by viewModels<SearchViewModel>()
    //It's used to retrieve the arguments passed to the current fragment from the navigation graph.
    private val args by navArgs<SearchFragmentArgs>()
    private val adapter by lazy { SearchProductsAdapter(this::onProductClick, this::like) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setInitials(args.category, args.wishlist)

        adapter.addLoadStateListener {
            viewModel.setLoadState(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner) {
            loadingLayout.root.isVisible = it
        }
        viewModel.products.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it ?: PagingData.empty())
            }
        }
        viewModel.recents.observe(viewLifecycleOwner) {
            recents.adapter = RecentAdapter(it, this@SearchFragment::onRecentClick)
            isRecentsVisible(searchContainer.search.hasFocus())
        }
    }

    private fun initUi() = with(binding) {
        searchContainer.search.requestFocus()
        showKeyboard()

        close.setOnClickListener {
            findNavController().popBackStack()
        }
        products.adapter = adapter

        searchContainer.search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.setSearch(searchContainer.search.text.toString())

                viewLifecycleOwner.lifecycleScope.launch {
                    adapter.submitData(PagingData.empty())
                }

                hideKeyboard()
                searchContainer.search.clearFocus()
                return@OnEditorActionListener true
            }
            false
        })

        //it is visible when focused or not
        searchContainer.search.setOnFocusChangeListener { _, focused ->
            isRecentsVisible(focused)
        }
        clear.setOnClickListener {
            viewModel.clearRecents()
        }
        searchContainer.filter.setOnClickListener {
            val query = viewModel.query.value ?: ProductQuery()
            findNavController().navigate(toFilterFragment(query))
        }
        //get for the result
        setFragmentResultListener(FilterFragment.REQUEST_KEY) { _, result ->
            val query = result.getParcelable < ProductQuery>(FilterFragment.RESULT_KEY)
            viewModel.setQuery(query?: return@setFragmentResultListener)
            searchContainer.search.clearFocus()
            hideKeyboard()
            isRecentsVisible(false)
        }
    }

    private fun FragmentSearchBinding.isRecentsVisible(focused: Boolean) {
        listOf(recents, recentTitle, clear).forEach {
            it.isVisible = viewModel.recents.value.isNullOrEmpty().not() && focused
        }
    }

    private fun onProductClick(product: Product) {

    }

    private fun like(product: Product) {
        findNavController().navigate(SearchFragmentDirections.toDetailFragment(product.id))
    }

    private fun onRecentClick(recent: String) {
        viewModel.setSearch(recent)
        binding.searchContainer.search.setText(recent)
    }
}