package com.oybekdev.e_commerce.di

import com.oybekdev.e_commerce.data.repo.AuthRepositoryImpl
import com.oybekdev.e_commerce.domain.repo.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Repository {

    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ):AuthRepository
}