package com.oybekdev.e_commerce.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oybekdev.e_commerce.data.api.product.dto.Category
import com.oybekdev.e_commerce.databinding.FragmentCategoriesBinding
import com.oybekdev.e_commerce.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment:BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    private val viewModel by viewModels<CategoriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeLiveData()
    }

    private fun subscribeLiveData()= with(binding){
        viewModel.loading.observe(viewLifecycleOwner){
            loading.root.isVisible = it
        }
        viewModel.error.observe(viewLifecycleOwner){
            error.root.isVisible = it
        }
        viewModel.categories.observe(viewLifecycleOwner){
            categories.adapter = CategoriesAdapter(it,this@CategoriesFragment::onCategoryClick)
        }
    }

    private fun initUi() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack() //for back
        }
    }

    private fun onCategoryClick(category:Category){
        findNavController().navigate(CategoriesFragmentDirections.toProductsFragment(category))
    }

}