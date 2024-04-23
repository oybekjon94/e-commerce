package com.oybekdev.e_commerce.data.api.product.dto


import com.google.gson.annotations.SerializedName

data class Banner(
    val category: Category,
    val image: String,
    val product: Product
)