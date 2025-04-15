package com.rieg.drivexrp.presentation.validation

import com.rieg.drivexrp.R

fun validateField(field: String): ValidationResult {
    if (field.isBlank()) {
        return ValidationResult.Error(
            errorMessage = R.string.field_no_empty
        )
    }
    return ValidationResult.Success
}