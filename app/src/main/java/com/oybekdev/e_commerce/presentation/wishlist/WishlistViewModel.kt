package com.oybekdev.e_commerce.presentation.wishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.domain.model.ProductQuery
import com.oybekdev.e_commerce.domain.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val productRepository: ProductRepository
):ViewModel(){

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val products = MutableLiveData<PagingData<Product>>()

    init {
        getProducts()
    }

    fun getProducts() = viewModelScope.launch {
        val query = ProductQuery(favorite = true)
        productRepository.getProducts(query).cachedIn(viewModelScope).collectLatest {
            products.postValue(it)
        }
    }
    fun setLoadStates(states: CombinedLoadStates){
        val loading = states.source.refresh is LoadState.Loading
        this.loading.postValue(loading)
    }
}