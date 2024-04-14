package com.oybekdev.e_commerce.data.api.auth.dto

import com.google.gson.annotations.SerializedName
import com.oybekdev.e_commerce.domain.model.User

data class UserDto(
    @SerializedName("username")
    val username:String
) {
    fun toUser() = User(
        username = username
    )
}