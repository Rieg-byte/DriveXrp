package com.rieg.drivexrp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rieg.drivexrp.domain.repository.AuthRepository
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
            it.copy(
                email = email
            )
        }
    }

    fun onUpdatePassword(password: String) {
        _signInUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onSignIn() {
        viewModelScope.launch {
            val result = authRepository.signInWithEmail(
                email = _signInUiState.value.email,
                password = _signInUiState.value.password
            )
            _signInUiState.update {
                it.copy(
                    isSignIn = result
                )
            }
        }
    }

}
