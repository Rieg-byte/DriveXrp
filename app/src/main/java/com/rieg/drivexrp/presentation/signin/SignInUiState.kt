package com.rieg.drivexrp.presentation.signin

import androidx.annotation.StringRes

data class SignInUiState(
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null,
    val isSignIn: Boolean = false
)