package com.oybekdev.e_commerce.data.repo

import com.oybekdev.e_commerce.data.api.auth.AuthApi
import com.oybekdev.e_commerce.data.api.auth.dto.AuthResponse
import com.oybekdev.e_commerce.data.api.auth.dto.SignInRequest
import com.oybekdev.e_commerce.data.api.auth.dto.SignUpRequest
import com.oybekdev.e_commerce.data.store.TokenStore
import com.oybekdev.e_commerce.data.store.UserStore
import com.oybekdev.e_commerce.domain.repo.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
    private val userStore: UserStore
) : AuthRepository {
    override suspend fun signIn(username: String, password: String) {
        val request = SignInRequest(username, password)
        val response = authApi.signIn(request)
        saveUserInfo(response)

    }

    override suspend fun signUp(username: String, email: String, password: String) {
        val request = SignUpRequest(username, email, password)
        val response = authApi.signUp(request)
        saveUserInfo(response)
    }

    private suspend fun saveUserInfo(response: AuthResponse) {
        tokenStore.set(response.token)
        userStore.set(response.user)
    }
}