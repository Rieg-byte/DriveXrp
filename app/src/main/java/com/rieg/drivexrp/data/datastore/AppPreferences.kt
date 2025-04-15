package com.rieg.drivexrp.data.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import com.rieg.drivexrp.data.model.ThemeModeParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.map


class AppPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        val THEME = stringPreferencesKey(name = "theme")
        const val TAG = "CurrencyConverterPreferences"
    }
    suspend fun setThemeMode(themeModeParam: ThemeModeParam) {
        dataStore.edit { preferences ->
            preferences[THEME] = themeModeParam.name
        }
    }

    val themeMode: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[THEME] ?: ThemeModeParam.SYSTEM.name
        }
}