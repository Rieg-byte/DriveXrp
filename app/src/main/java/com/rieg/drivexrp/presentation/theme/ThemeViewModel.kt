package com.rieg.drivexrp.presentation.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rieg.drivexrp.domain.model.ThemeMode
import com.rieg.drivexrp.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel() {
    val themeUiState: StateFlow<ThemeUiState> = settingsRepository.themeMode.map { themeMode ->
        ThemeUiState(themeMode = themeMode)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5.seconds.inWholeMilliseconds),
        initialValue = ThemeUiState(themeMode = ThemeMode.SYSTEM.name)
    )

    fun updateThemeMode(themeMode: ThemeMode) = viewModelScope.launch {
        settingsRepository.setThemeMode(themeMode)
    }
}