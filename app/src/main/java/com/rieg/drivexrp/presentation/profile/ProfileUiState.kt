package com.rieg.drivexrp.presentation.profile

import android.net.Uri
import com.rieg.drivexrp.presentation.signup.Gender

data class ProfileUiState (
    val firstName: String = "Иван",
    val lastName: String = "Иванов",
    val joinYear: Int = 2020,
    val joinMonth: Int = 2,
    val email: String = "ivan@ya.ru",
    val gender: Gender = Gender.MALE,
    val profileImage: Uri? = null,
    val googleEmail: String? = null
)