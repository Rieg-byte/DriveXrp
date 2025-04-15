package com.rieg.drivexrp.data.repository

import com.rieg.drivexrp.data.datastore.AppPreferences
import com.rieg.drivexrp.data.mappers.toThemeModeParam
import com.rieg.drivexrp.domain.model.ThemeMode
import com.rieg.drivexrp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences
): SettingsRepository {
    override val themeMode: Flow<String>
        get() = appPreferences.themeMode
    override suspend fun setThemeMode(themeMode: ThemeMode) = appPreferences.setThemeMode(themeMode.toThemeModeParam())
}