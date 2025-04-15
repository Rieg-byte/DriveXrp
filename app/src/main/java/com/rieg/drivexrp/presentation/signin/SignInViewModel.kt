package com.rieg.drivexrp.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rieg.drivexrp.R
import com.rieg.drivexrp.domain.repository.AuthRepository
import com.rieg.drivexrp.presentation.validation.ValidationResult
import com.rieg.drivexrp.presentation.validation.validateEmail
import com.rieg.drivexrp.presentation.validation.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _signInUiState = MutableStateFlow(SignInUiState())
    val signInUiState = _signInUiState.asStateFlow()

    fun onUpdateEmail(email: String) {
        _signInUiState.update {
            it.copy(email = email)
        }
    }

    fun onUpdatePassword(password: String) {
        _signInUiState.update {
            it.copy(password = password)
        }
    }

    fun submitData(email: String, password: String) {
        val emailResult = validateEmail(email)
        val passwordResult = validatePassword(password)
        val hasError = listOf(emailResult, passwordResult).any{ it is ValidationResult.Error }
        if (hasError) {
            checkPassword(passwordResult)
            checkEmail(emailResult)
        } else {
            onSignIn(email, password)
        }
    }

    private fun onSignIn(email: String, password: String) {
        viewModelScope.launch {
            val isAuth = authRepository.signInWithEmail(email, password)
            if (isAuth) {
                _signInUiState.update {
                    it.copy(
                        emailError = null,
                        passwordError = null,
                        isSignIn = true
                    )
                }
            } else {
                _signInUiState.update {
                    it.copy(
                        emailError = R.string.error_auth,
                        passwordError = R.string.error_auth,
                        isSignIn = false
                    )
                }
            }
        }

    }

    private fun checkPassword(passwordResult: ValidationResult) {
        if (passwordResult is ValidationResult.Error) {
            _signInUiState.update {
                it.copy(passwordError = passwordResult.errorMessage)
            }
        } else {
            _signInUiState.update {
                it.copy(passwordError = null)
            }
        }
    }

    private fun checkEmail(emailResult: ValidationResult) {
        if (emailResult is ValidationResult.Error) {
            _signInUiState.update { it.copy(emailError = emailResult.errorMessage) }
        } else {
            _signInUiState.update { it.copy(emailError = null) }
        }
    }
}

