package com.rieg.drivexrp.navigation

import MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rieg.drivexrp.presentation.onboarding.OnboardingScreen
import kotlinx.serialization.Serializable


@Serializable
object OnboardingRoute

@Serializable
object AuthGraph

@Serializable
object MainRoute

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = OnboardingRoute
    ) {
        composable<OnboardingRoute> {
            OnboardingScreen(
                onSkip = { navController.navigate(route = AuthGraph) {
                    popUpTo(route = OnboardingRoute) {
                        inclusive = true
                    }
                } },
                onFinish = { navController.navigate(route = AuthGraph) {
                    popUpTo(route = OnboardingRoute) {
                        inclusive = true
                    }
                } }
            )
        }
        authGraph(navController)
        composable<MainRoute> {
            MainScreen(navRootController = navController, navController = rememberNavController())
        }
    }
}