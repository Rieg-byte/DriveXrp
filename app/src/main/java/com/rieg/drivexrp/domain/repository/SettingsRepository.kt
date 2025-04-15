package com.rieg.drivexrp.domain.repository

import com.rieg.drivexrp.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val themeMode: Flow<String>
    suspend fun setThemeMode(themeMode: ThemeMode)
}