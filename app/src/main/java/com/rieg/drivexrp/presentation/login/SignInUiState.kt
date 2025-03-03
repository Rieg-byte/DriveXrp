package com.rieg.drivexrp.presentation.login

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isSignIn: Boolean = false
)