package com.oybekdev.e_commerce.domain.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.oybekdev.e_commerce.data.api.product.dto.Category
import com.oybekdev.e_commerce.data.api.product.dto.HomeResponse
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.domain.model.ProductQuery

interface ProductRepository {
    suspend fun getHome():HomeResponse
    suspend fun getCategories():List<Category>

    fun getProducts(query: ProductQuery) : LiveData<PagingData<Product>>
}