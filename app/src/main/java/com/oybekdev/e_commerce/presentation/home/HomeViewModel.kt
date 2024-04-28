package com.oybekdev.e_commerce.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oybekdev.e_commerce.data.api.product.dto.HomeResponse
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.domain.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
):ViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val home = MutableLiveData<HomeResponse?>(null)
    init {
        getHome()
    }

    fun getHome() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response = productRepository.getHome()
            home.postValue(response)
        }catch (e:Exception){
            error.postValue(true)
        }finally {
            loading.postValue(false)
        }
    }

    fun toggleWishlist(product:Product) = viewModelScope.launch {
        try {
            productRepository.toggleWishlist(product.id,product.wishlist)
        }catch (e:Exception){

        }
    }
}