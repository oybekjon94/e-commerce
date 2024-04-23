package com.oybekdev.e_commerce.presentation.products

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.oybekdev.e_commerce.data.api.product.dto.Category
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.domain.model.ProductQuery
import com.oybekdev.e_commerce.domain.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
):ViewModel(){

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val products = MediatorLiveData<PagingData<Product>>()
    val category = MutableLiveData<Category>()

    fun setCategory(category:Category){
        this.category.postValue(category)
        getProducts()
    }

    private fun getProducts() {
        val query = ProductQuery(category = category.value)
        val products = productRepository.getProducts(query)
        this.products.addSource(products){
            this.products.postValue(it)
        }
    }
}