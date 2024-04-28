package com.oybekdev.e_commerce.presentation.wishlist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.FragmentWishlistBinding
import com.oybekdev.e_commerce.presentation.products.ProductViewModel
import com.oybekdev.e_commerce.presentation.products.ProductsAdapter
import com.oybekdev.e_commerce.presentation.products.ProductsFragmentArgs
import com.oybekdev.e_commerce.presentation.products.ProductsFragmentDirections
import com.oybekdev.e_commerce.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishlistFragment:BaseFragment<FragmentWishlistBinding>(FragmentWishlistBinding::inflate) {

    private val viewModel by viewModels<WishlistViewModel>()

    //use when we need it
    private val adapter by lazy {
        ProductsAdapter(this::onClick,this::like)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.addLoadStateListener {
            viewModel.setLoadStates(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun initUi() = with(binding) {

        error.retry.setOnClickListener {
            viewModel.getProducts()
        }
        products.adapter = adapter

        search.setOnClickListener {
            findNavController().navigate(WishlistFragmentDirections.toSearchFragment())
        }

        swipe.setOnRefreshListener {
            viewModel.getProducts()
        }
    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner){
            loading.root.isVisible = it && swipe.isRefreshing.not()
            if (it.not()) {swipe.isRefreshing = false}
        }
        viewModel.error.observe(viewLifecycleOwner){
            error.root.isVisible = it
        }
        viewModel.products.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

    private fun onClick(product: Product){
        findNavController().navigate(WishlistFragmentDirections.toDetailFragment(product.id))
    }
    private fun like(product: Product){

    }

}