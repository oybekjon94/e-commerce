package com.oybekdev.e_commerce.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oybekdev.e_commerce.domain.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val authRepository: AuthRepository
):ViewModel(){

    fun onboarded() = viewModelScope.launch {
        authRepository.onboarded()
    }

}