package com.oybekdev.e_commerce.data.api.product.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oybekdev.e_commerce.data.api.product.ProductApi
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.domain.model.ProductQuery

class ProductPagingSource(
    private val productApi: ProductApi,
    private val query: ProductQuery
):PagingSource<Int,Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val key = params.key?:0

            val products = productApi.getProducts(
                categoryId = query.category?.id,
                page = key,
                size = params.loadSize
            )

            LoadResult.Page(
                data = products,
                prevKey = params.key?.let { it-1 },
                nextKey = if (products.size == params.loadSize)key +1 else null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}