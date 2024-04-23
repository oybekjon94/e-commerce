package com.oybekdev.e_commerce.data.repo

import com.oybekdev.e_commerce.data.api.product.ProductApi
import com.oybekdev.e_commerce.data.api.product.dto.HomeResponse
import com.oybekdev.e_commerce.data.store.UserStore
import com.oybekdev.e_commerce.domain.repo.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val userStore: UserStore
):ProductRepository{
    override suspend fun getHome(): HomeResponse {
        val response = productApi.getHome()
        userStore.set(response.user)
        return response
    }

}