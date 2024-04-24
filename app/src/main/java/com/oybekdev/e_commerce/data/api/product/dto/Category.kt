package com.oybekdev.e_commerce.data.api.product.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Category(
    val count: Int,
    val id: String,
    val image: String,
    val title: String
):Parcelable