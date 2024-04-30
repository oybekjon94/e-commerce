package com.oybekdev.e_commerce.data.api.order

import com.oybekdev.e_commerce.data.api.order.dto.Billing
import com.oybekdev.e_commerce.data.api.order.dto.CartRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApi {

    @POST("order/get-billing")
    suspend fun getBilling(@Body request:CartRequest):Billing

    @POST("order")
    suspend fun createOrder(@Body request: CartRequest)
}