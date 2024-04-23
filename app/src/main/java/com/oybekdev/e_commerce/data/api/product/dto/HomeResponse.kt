package com.oybekdev.e_commerce.data.api.product.dto

import com.google.gson.annotations.SerializedName
import com.oybekdev.e_commerce.data.api.auth.dto.UserDto


data class HomeResponse(
    val banners: List<Banner>,
    val categories: List<Category>,
    val sections: List<Section>,
    val user: UserDto,
    @SerializedName("notification_count")
    val notificationCount:Int
)