package com.oybekdev.e_commerce.domain.model

data class User(
    val username: String,
    val avatar:String?,
    val email:String,
    val firstName:String?,
    val lastName:String?
)