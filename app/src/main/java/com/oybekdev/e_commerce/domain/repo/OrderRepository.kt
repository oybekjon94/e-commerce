package com.oybekdev.e_commerce.domain.repo

import com.oybekdev.e_commerce.data.api.order.dto.Billing
import com.oybekdev.e_commerce.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getBilling( promo:String? = null):Flow<Billing>

    suspend fun createOrder(promo:String? = null)
}