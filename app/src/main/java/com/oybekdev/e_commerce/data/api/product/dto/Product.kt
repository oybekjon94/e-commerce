package com.oybekdev.e_commerce.data.api.product.dto


import com.google.gson.annotations.SerializedName

data class Product(
    val id: String,
    val discount: Double?,
    var favorite: Boolean,
    val image: String,
    val price: Double,
    val rating: Double,
    @SerializedName("rating_count")
    val ratingCount: Int,
    val title: String,
)