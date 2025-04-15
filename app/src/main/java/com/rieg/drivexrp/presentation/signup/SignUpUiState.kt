package com.rieg.drivexrp.presentation.signup

import android.net.Uri
import androidx.annotation.StringRes

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isAccept: Boolean = false,
    val firstName: String = "",
    val secondName: String = "",
    val middleName: String = "",
    val birthDate: Long? = null,
    val gender: Gender = Gender.MALE,
    val driverLicense: String = "",
    val dateOfIssue: Long? = null,
    val profileImage: Uri? = null,
    val passportImage: Uri? = null,
    val driverLicenseImage: Uri? = null,
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val repeatPasswordError: Int? = null,
    @StringRes val firstNameError: Int? = null,
    @StringRes val secondNameError: Int? = null,
    @StringRes val middleNameError: Int? = null,
    @StringRes val birthDateError: Int? = null,
    @StringRes val driverLicenseError: Int? = null,
    @StringRes val dateOfIssueError: Int? = null,
    @StringRes val passportImageError: Int? = null,
    @StringRes val driverLicenseImageError: Int? = null,
    val termsError: Boolean = false,
    val registerForm: RegisterForm = RegisterForm.FIRST,
    val isFinish: Boolean = false
)

enum class RegisterForm {
    FIRST, SECOND, THIRD
}

enum class Gender {
    MALE, FEMALE
}