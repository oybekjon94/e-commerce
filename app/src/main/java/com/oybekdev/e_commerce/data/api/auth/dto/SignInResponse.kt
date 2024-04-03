package com.oybekdev.e_commerce.data.api.auth.dto

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("user")
    val user: UserDto,
    @SerializedName("token")
    val token: String
) {
}