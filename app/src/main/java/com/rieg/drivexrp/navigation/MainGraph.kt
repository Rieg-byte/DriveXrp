package com.rieg.drivexrp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rieg.drivexrp.presentation.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
object MainGraph

@Composable
fun MainNavGraph(navRootController: NavHostController, navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen()
        }
        composable<FavoriteRoute> {

        }
        settingsGraph(navRootController = navRootController, navController = navController)
    }
}

