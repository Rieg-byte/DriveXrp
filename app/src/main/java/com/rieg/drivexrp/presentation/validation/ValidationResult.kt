package com.rieg.drivexrp.presentation.validation

import androidx.annotation.StringRes

sealed interface ValidationResult{
    data object Success: ValidationResult
    data class Error(
        @StringRes val errorMessage: Int
    ): ValidationResult
}