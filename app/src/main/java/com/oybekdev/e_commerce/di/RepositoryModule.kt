package com.oybekdev.e_commerce.di

import com.oybekdev.e_commerce.data.repo.AuthRepositoryImpl
import com.oybekdev.e_commerce.data.repo.OrderRepositoryImpl
import com.oybekdev.e_commerce.data.repo.ProductRepositoryImpl
import com.oybekdev.e_commerce.domain.repo.AuthRepository
import com.oybekdev.e_commerce.domain.repo.OrderRepository
import com.oybekdev.e_commerce.domain.repo.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ):AuthRepository
    @Binds
    abstract fun bindProductRepository(
        authRepositoryImpl: ProductRepositoryImpl
    ):ProductRepository

    @Binds
    abstract fun bindOrderRepository(
        orderRepositoryImpl: OrderRepositoryImpl
    ):OrderRepository
}