package com.oybekdev.e_commerce.di

import com.oybekdev.e_commerce.data.api.auth.AuthApi
import com.oybekdev.e_commerce.data.api.order.OrderApi
import com.oybekdev.e_commerce.data.api.product.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit) = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit) = retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideOrderApi(retrofit: Retrofit) = retrofit.create(OrderApi::class.java)
}