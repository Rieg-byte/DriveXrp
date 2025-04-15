package com.rieg.drivexrp.presentation.validation

import com.rieg.drivexrp.R

fun validatePassword(password: String): ValidationResult {
    if (password.isBlank()) {
        return ValidationResult.Error(
            errorMessage = R.string.error_blank_password
        )
    }
    if (password.length < 6) {
        return ValidationResult.Error(
            errorMessage = R.string.error_short_password,
        )
    }
    return ValidationResult.Success

}