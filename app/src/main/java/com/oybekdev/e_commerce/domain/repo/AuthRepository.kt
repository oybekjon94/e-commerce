package com.oybekdev.e_commerce.domain.repo

import com.oybekdev.e_commerce.domain.User

interface AuthRepository {

    suspend fun signIn(username: String, password: String)
}