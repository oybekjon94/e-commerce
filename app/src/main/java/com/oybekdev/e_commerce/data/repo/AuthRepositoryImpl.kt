package com.oybekdev.e_commerce.data.repo

import com.oybekdev.e_commerce.data.api.auth.AuthApi
import com.oybekdev.e_commerce.data.api.auth.dto.SignInRequest
import com.oybekdev.e_commerce.data.store.TokenStore
import com.oybekdev.e_commerce.data.store.UserStore
import com.oybekdev.e_commerce.domain.User
import com.oybekdev.e_commerce.domain.repo.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
    private val userStore: UserStore
):AuthRepository {
    override suspend fun signIn(username: String, password: String): User {
        val request = SignInRequest(username, password)
        val response = authApi.signIn(request)
        tokenStore.set(response.token)
        userStore.set(response.user)
        return response.user.toUser()
    }
}