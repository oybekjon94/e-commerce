package com.oybekdev.e_commerce.domain.model

sealed class Destination {
    object Home : Destination()
    object Onboarding : Destination()
    object Auth : Destination()
}