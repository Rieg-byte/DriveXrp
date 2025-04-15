package com.rieg.drivexrp.presentation.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ProfileViewModel: ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()

    fun onUpdateProfileImage(uri: Uri?) {
        _profileUiState.update { it.copy(profileImage = uri) }
    }
}