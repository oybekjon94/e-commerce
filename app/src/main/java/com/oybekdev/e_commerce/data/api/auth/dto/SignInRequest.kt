package com.oybekdev.e_commerce.data.api.auth.dto

import com.google.gson.annotations.SerializedName

class SignInRequest(
    @SerializedName("username")
    val username:String,
    @SerializedName("password")
    val password:String
) {
}