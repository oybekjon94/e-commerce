package com.oybekdev.e_commerce.data.api.product.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Parcelable -> interface in Android is used to serialize and deserialize objects for passing
 * between different components of an Android application, such as activities, fragments,
 * and services.
 * Category data class -> represents a category object with properties
 * such as count, id, image, and title.
 */
@Parcelize
data class Category(
    val count: Int,
    val id: String,
    val image: String,
    val title: String
):Parcelable