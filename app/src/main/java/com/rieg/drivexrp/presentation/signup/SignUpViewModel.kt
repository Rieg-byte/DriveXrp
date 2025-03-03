package com.rieg.drivexrp.presentation.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

): ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState = _signUpUiState.asStateFlow()
    fun onUpdateEmail(email: String) {
        _signUpUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onUpdatePassword(password: String) {
        _signUpUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onUpdateRepeatPassword(repeatPassword: String) {
        _signUpUiState.update {
            it.copy(
                repeatPassword = repeatPassword
            )
        }
    }

    fun onAgreed(value: Boolean) {
        _signUpUiState.update {
            it.copy(
                isAgreed = !value
            )
        }
    }

}