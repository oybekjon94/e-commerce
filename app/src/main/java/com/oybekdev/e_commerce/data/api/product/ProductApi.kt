package com.oybekdev.e_commerce.data.api.product

import com.oybekdev.e_commerce.data.api.product.dto.HomeResponse
import retrofit2.http.GET

interface ProductApi {

    @GET("home")
    suspend fun getHome():HomeResponse
}