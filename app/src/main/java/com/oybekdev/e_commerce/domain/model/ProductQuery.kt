package com.oybekdev.e_commerce.domain.model

import com.oybekdev.e_commerce.data.api.product.dto.Category

data class ProductQuery(
    val category:Category?=null
)
