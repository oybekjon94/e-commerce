package com.oybekdev.e_commerce.presentation.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oybekdev.e_commerce.domain.repo.AuthRepository
import okio.IOException
import com.oybekdev.e_commerce.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val loading = MutableLiveData(false)
    val events = SingleLiveEvent<Event>()

    fun signUp(username: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            try {
                authRepository.signUp(username, email, password)
                events.postValue(Event.NavigateToHome)
            }catch (e:Exception){
                when {
                    e is HttpException && e.code() == 401 -> events.postValue(Event.AlreadyRegistered)
                    e is IOException -> events.postValue(Event.ConnectionError)
                    else -> events.postValue(Event.Error)
                }
            }
            finally {
                loading.postValue(false)
            }

        }
    }
    sealed class Event {
        object AlreadyRegistered : Event()
        object ConnectionError : Event()
        object Error : Event()
        object NavigateToHome : Event()
    }

}

