package com.rieg.drivexrp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object FavoriteRoute

@Serializable
object SettingsGraph

data class BottomLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val bottomLevelRoutes = listOf(
    BottomLevelRoute(
        "Главное", HomeRoute, Icons.Rounded.Home
    ),
    BottomLevelRoute(
        "Избранное", FavoriteRoute, Icons.Rounded.Favorite
    ),
    BottomLevelRoute(
        "Настройки", SettingsGraph, Icons.Rounded.Settings
    )
)