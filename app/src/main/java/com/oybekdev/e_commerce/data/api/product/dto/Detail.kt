package com.oybekdev.e_commerce.data.api.product.dto


import com.google.gson.annotations.SerializedName

data class Detail(
    val category: String,
    val description: String,
    val discount: Double?,
    @SerializedName("favorite")
    val wishlist: Boolean,
    val id: String,
    val images: List<String>,
    @SerializedName("in_stock")
    val inStock: Int,
    val price: Double,
    val rating: Double,
    val related: List<Product>,
    val reviews: Int,
    val title: String,
    @SerializedName("free_delivery")
    val freeDelivery:Boolean
)