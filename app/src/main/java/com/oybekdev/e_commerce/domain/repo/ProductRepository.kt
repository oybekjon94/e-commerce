package com.oybekdev.e_commerce.domain.repo

import com.oybekdev.e_commerce.data.api.product.dto.HomeResponse

interface ProductRepository {
    suspend fun getHome():HomeResponse
}