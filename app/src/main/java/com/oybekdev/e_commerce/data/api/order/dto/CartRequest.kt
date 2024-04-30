package com.oybekdev.e_commerce.data.api.order.dto


import com.google.gson.annotations.SerializedName

data class CartRequest(
    val cart: List<CartDto>,
    @SerializedName("promo_code")
    val promoCode: String?
)