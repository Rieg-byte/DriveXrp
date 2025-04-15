package com.rieg.drivexrp.data.mappers

import com.rieg.drivexrp.data.model.ThemeModeParam
import com.rieg.drivexrp.domain.model.ThemeMode

fun ThemeMode.toThemeModeParam(): ThemeModeParam {
    return when(this) {
        ThemeMode.SYSTEM -> ThemeModeParam.SYSTEM
        ThemeMode.DARK -> ThemeModeParam.DARK
        ThemeMode.LIGHT -> ThemeModeParam.LIGHT
    }
}