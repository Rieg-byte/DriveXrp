package com.rieg.drivexrp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rieg.drivexrp.presentation.main.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object MainGraph

@Serializable
object MainRoute

fun NavGraphBuilder.mainGraph() {
    navigation<MainGraph>(startDestination = MainRoute) {
        composable<MainRoute> {
            MainScreen()
        }
    }
}