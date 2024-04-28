package com.oybekdev.e_commerce.data.api.product

import com.oybekdev.e_commerce.data.api.product.dto.Category
import com.oybekdev.e_commerce.data.api.product.dto.Detail
import com.oybekdev.e_commerce.data.api.product.dto.HomeResponse
import com.oybekdev.e_commerce.data.api.product.dto.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("home")
    suspend fun getHome():HomeResponse

    @GET("categories")
    suspend fun getCategories():List<Category>

    @GET("products")
    suspend fun getProducts(
        @Query("category_id")categoryId:String?,
        @Query("search")search:String?,
        @Query("page")page:Int,
        @Query("size")size:Int,
        @Query("from")from:Float?,
        @Query("to")to:Float?,
        @Query("favorite") favorite:Boolean,
        @Query("rating")rating:Int?,
        @Query("discount")discount:Int?,
        @Query("sort")sort:String?
    ):List<Product>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id:String
    ):Detail

    @GET("products/{id}/toggle-wishlist")
    suspend fun toggleWishlist(
        @Path("id")id:String,
        @Query("wishlist")wishlist:Boolean
    )
}