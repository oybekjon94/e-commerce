package com.oybekdev.e_commerce.data.api.auth

import com.oybekdev.e_commerce.data.api.auth.dto.SignInRequest
import com.oybekdev.e_commerce.data.api.auth.dto.AuthResponse
import com.oybekdev.e_commerce.data.api.auth.dto.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * This interface defines the contract for interacting with an authentication
 * API. It declares methods for signing in and signing up users.
 * @POST -> This method is used to send a POST request
 * AuthResponsev -> contains information about the authentication status and possibly
 * user-related data upon successful sign-in
 * @Body ->  annotation is used to specify that the parameter should be sent as the request
 * body when making the API call. you want to directly control the request body of a
 * POST/PUT request
 *
 */
interface AuthApi {

    @POST("auth/sign-in")
    suspend fun signIn(@Body request: SignInRequest): AuthResponse

    @POST("auth/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): AuthResponse

}