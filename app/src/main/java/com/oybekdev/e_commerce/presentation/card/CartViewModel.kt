package com.oybekdev.e_commerce.presentation.card

import android.media.metrics.Event
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oybekdev.e_commerce.data.api.order.dto.Billing
import com.oybekdev.e_commerce.domain.model.Cart
import com.oybekdev.e_commerce.domain.repo.OrderRepository
import com.oybekdev.e_commerce.domain.repo.ProductRepository
import com.oybekdev.e_commerce.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
):ViewModel() {
    val carts = MutableLiveData<List<Cart>>()
    val events = SingleLiveEvent<Event>()
    val billing = MutableLiveData<Billing>()
    val billingLoading = MutableLiveData(false)
    val loading = MutableLiveData(false)

    init {
        getCarts()
        getBilling()
    }

    private fun getCarts() = viewModelScope.launch {
        productRepository.getCarts().collectLatest {
            carts.postValue(it)
        }
    }

    fun set(cart: Cart) = viewModelScope.launch {
        productRepository.setCart(cart)
    }

    fun clear() = viewModelScope.launch {
        productRepository.clearCart()
    }

    private var job: Job? = null
    fun getBilling(promo:String? = null){
        job?.cancel()
        job = viewModelScope.launch {
            billingLoading.postValue(true)
            orderRepository.getBilling(promo).catch {
                it.printStackTrace()
                events.postValue(Event.BillingError)
                billingLoading.postValue(false)
            }.collectLatest {
                billingLoading.postValue(false)
                billing.postValue(it)
            }
        }
    }
    fun createOrder(promo:String? = null) = viewModelScope.launch {
        loading.postValue(true)
        try {
            orderRepository.createOrder(promo)
            events.postValue(Event.OrderCreated)
        }catch (e:Exception){
            events.postValue(Event.OrderError)
        }finally {
            loading.postValue(false)
        }
    }

    sealed class Event{
        object BillingError:Event()
        object OrderError:Event()
        object OrderCreated:Event()
    }
}