package com.oybekdev.e_commerce.data.api.order.dto


import com.google.gson.annotations.SerializedName

data class Billing(
    val delivery: Double,
    val discount: Double?,
    val items: Double,
    val tax: Double,
    val total: Double
)