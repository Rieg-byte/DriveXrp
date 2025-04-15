package com.rieg.drivexrp.presentation.validation

import android.util.Patterns
import com.rieg.drivexrp.R

fun validateEmail(email: String): ValidationResult {
    if (email.isBlank()) {
        return ValidationResult.Error(
            errorMessage = R.string.error_blank_email
        )
    }
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return ValidationResult.Error(
            errorMessage = R.string.error_not_valid_email
        )
    }
    return ValidationResult.Success
}