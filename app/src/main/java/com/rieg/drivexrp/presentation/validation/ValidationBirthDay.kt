package com.rieg.drivexrp.presentation.validation

import com.rieg.drivexrp.R
import java.util.Calendar

fun validateBirthDay(birthDay: Long?): ValidationResult {
    if (birthDay == null) {
        return ValidationResult.Error(
            errorMessage = R.string.field_no_empty
        )
    }
    val currentDate = Calendar.getInstance()
    val selectedDate = currentDate.apply { timeInMillis = birthDay }

    if (selectedDate.after(currentDate)) {
        return ValidationResult.Error(
            errorMessage = R.string.birth_date_future_error
        )
    }

    currentDate.add(Calendar.YEAR, -18)
    if (selectedDate.after(currentDate)) {
        return ValidationResult.Error(
            errorMessage = R.string.minimum_age_error
        )
    }

    return ValidationResult.Success
}