package com.oybekdev.e_commerce.data.api.product.dto


data class Section(
    val id: String,
    val products: List<Product>,
    val title: String,
    val type: SectionType
)