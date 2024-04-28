package com.oybekdev.e_commerce.presentation.products

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.FragmentProductsBinding
import com.oybekdev.e_commerce.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment:BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate) {
    private val viewModel by viewModels<ProductViewModel>()
    private val args by navArgs<ProductsFragmentArgs>()

    //use when we need it
    private val adapter by lazy {
        ProductsAdapter(this::onClick,this::wishlist)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setCategory(args.category)

        adapter.addLoadStateListener {
            viewModel.setLoadStates(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner){
            loading.root.isVisible = it
        }
        viewModel.error.observe(viewLifecycleOwner){
            error.root.isVisible = it
        }
        viewModel.products.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        viewModel.category.observe(viewLifecycleOwner){
            title.text = it.title
        }
    }

    private fun initUi() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }
        error.retry.setOnClickListener {
            viewModel.getProducts()
        }
        products.adapter = adapter
    }

    private fun onClick(product:Product){
        findNavController().navigate(ProductsFragmentDirections.toDetailFragment(product.id))
    }
    private fun wishlist(product:Product){
        viewModel.toggleWishlist(product)
    }
}