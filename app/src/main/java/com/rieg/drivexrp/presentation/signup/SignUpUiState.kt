package com.rieg.drivexrp.presentation.signup

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isAgreed: Boolean = false
)