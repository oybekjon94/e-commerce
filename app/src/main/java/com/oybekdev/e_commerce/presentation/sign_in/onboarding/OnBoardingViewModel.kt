package com.oybekdev.e_commerce.presentation.sign_in.onboarding

import androidx.lifecycle.ViewModel
import com.oybekdev.e_commerce.domain.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val authRepository: AuthRepository
):ViewModel(){

}