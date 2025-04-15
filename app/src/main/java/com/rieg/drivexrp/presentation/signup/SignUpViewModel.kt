package com.rieg.drivexrp.presentation.signup

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.rieg.drivexrp.presentation.validation.ValidationResult
import com.rieg.drivexrp.presentation.validation.validateBirthDay
import com.rieg.drivexrp.presentation.validation.validateDateOfIssue
import com.rieg.drivexrp.presentation.validation.validateEmail
import com.rieg.drivexrp.presentation.validation.validateField
import com.rieg.drivexrp.presentation.validation.validatePassword
import com.rieg.drivexrp.presentation.validation.validateRepeatPassword
import com.rieg.drivexrp.presentation.validation.validateUri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

): ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState = _signUpUiState.asStateFlow()

    fun onUpdateEmail(email: String) {
        _signUpUiState.update { it.copy(email = email) }
    }

    fun onUpdatePassword(password: String) {
        _signUpUiState.update { it.copy(password = password) }
    }

    fun onUpdateRepeatPassword(repeatPassword: String) {
        _signUpUiState.update { it.copy(repeatPassword = repeatPassword) }
    }

    fun onUpdateAccept(value: Boolean) {
        _signUpUiState.update { it.copy(isAccept = !value) }
    }

    fun onUpdateFirstName(firstName: String) {
        _signUpUiState.update { it.copy(firstName = firstName) }
    }

    fun onUpdateSecondName(secondName: String) {
        _signUpUiState.update { it.copy(secondName = secondName) }
    }

    fun onUpdateMiddleName(middleName: String) {
        _signUpUiState.update { it.copy(middleName = middleName) }
    }

    fun onUpdateBirthDate(date: Long?) {
        _signUpUiState.update { it.copy(birthDate = date) }
    }

    fun onGenderChange(gender: Gender) {
        _signUpUiState.update { it.copy(gender = gender) }
    }


    fun onUpdateProfileImage(uri: Uri?) {
        _signUpUiState.update { it.copy(profileImage = uri) }
    }

    fun onUpdateDriverLicense(driverLicense: String) {
        _signUpUiState.update { it.copy(driverLicense = driverLicense) }
    }

    fun onUpdateDateOfIssue(date: Long?) {
        _signUpUiState.update { it.copy(dateOfIssue = date) }
    }

    fun onUpdatePassportImage(uri: Uri?) {
        _signUpUiState.update { it.copy(passportImage = uri) }
    }

    fun onUpdateDriverLicenseImage(uri: Uri?) {
        _signUpUiState.update { it.copy(driverLicenseImage = uri) }
    }

    fun submitFirstForm(email: String, password: String, repeatPassword: String, isAccepted: Boolean) {
        val emailResult = validateEmail(email)
        val passwordResult = validatePassword(password)
        val repeatPasswordResult = validateRepeatPassword(password, repeatPassword)
        val hasError = listOf(emailResult, passwordResult, repeatPasswordResult)
            .any{ it is ValidationResult.Error } || !isAccepted
        if (hasError) {
            _signUpUiState.update {
                it.copy(
                    emailError = checkValidationResult(emailResult),
                    passwordError = checkValidationResult(passwordResult),
                    repeatPasswordError = checkValidationResult(repeatPasswordResult),
                    termsError = isAccepted
                )
            }
        } else {
            updateRegisterForm(RegisterForm.SECOND)
        }
    }

    fun submitSecondForm(
        firstName: String,
        secondName: String,
        middleName: String,
        birthDay: Long?
    ) {
        val firstNameResult = validateField(firstName)
        val secondNameResult = validateField(secondName)
        val middleNameResult = validateField(middleName)
        val birthDayResult = validateBirthDay(birthDay)
        val hasError = listOf(firstNameResult, secondNameResult, middleNameResult, birthDay)
            .any{ it is ValidationResult.Error }
        if (hasError) {
            _signUpUiState.update {
                it.copy(
                    firstNameError = checkValidationResult(firstNameResult),
                    secondNameError = checkValidationResult(secondNameResult),
                    middleNameError = checkValidationResult(middleNameResult),
                    birthDateError = checkValidationResult(birthDayResult),
                )
            }
        } else {
            updateRegisterForm(RegisterForm.THIRD)
        }
    }

    fun submitThirdForm(
        profileImage: Uri?,
        driverLicense: String,
        dateOfIssue: Long?,
        passportImage: Uri?,
        driverLicenseImage: Uri?
    ) {
        val driverLicenseResult = validateField(driverLicense)
        val dateOfIssueResult = validateDateOfIssue(dateOfIssue)
        val passportImageResult = validateUri(passportImage)
        val driverLicenseImageResult = validateUri(driverLicenseImage)
        val hasError = listOf(driverLicenseResult, dateOfIssueResult, passportImageResult, driverLicenseImageResult)
            .any{ it is ValidationResult.Error }
        if (hasError) {
            _signUpUiState.update {
                it.copy(
                    driverLicenseError = checkValidationResult(driverLicenseResult),
                    dateOfIssueError = checkValidationResult(dateOfIssueResult),
                    passportImageError = checkValidationResult(passportImageResult),
                    driverLicenseImageError = checkValidationResult(driverLicenseImageResult)
                )
            }
        } else {
            _signUpUiState.update {
                it.copy(isFinish = true)
            }
        }
    }

    fun updateRegisterForm(registerForm: RegisterForm) {
        _signUpUiState.update { it.copy(registerForm = registerForm) }
    }

    private fun checkValidationResult(validationResult: ValidationResult): Int? {
        return when (validationResult) {
            is ValidationResult.Error -> validationResult.errorMessage
            is ValidationResult.Success -> null
        }
    }


}