package com.oybekdev.e_commerce.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oybekdev.e_commerce.data.api.product.dto.Detail
import com.oybekdev.e_commerce.domain.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
):ViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val detail = MutableLiveData<Detail>()
    val count = MutableLiveData(1)

    fun getProduct(id:String) = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val result = productRepository.getProduct(id)
            detail.postValue(result)
        }catch (e:Exception){
            error.postValue(true)
        }finally {
            loading.postValue(false)
        }
    }

    fun increment(){
        var current = count.value ?: 1
        val product = detail.value ?: return
        if (current == product.inStock)return
        current++
        count.postValue(current)
    }
    fun decrement(){
        var current = count.value ?: 1
        val product = detail.value ?: return
        if (current == 1)return
        current--
        count.postValue(current)
    }

}