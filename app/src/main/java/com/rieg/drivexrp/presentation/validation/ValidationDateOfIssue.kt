package com.rieg.drivexrp.presentation.validation

import com.rieg.drivexrp.R

fun validateDateOfIssue(dateOfIssue: Long?): ValidationResult {
    if (dateOfIssue == null) {
        return ValidationResult.Error(
            errorMessage = R.string.field_no_empty
        )
    }
    return ValidationResult.Success
}