package com.rieg.drivexrp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rieg.drivexrp.presentation.auth.AuthScreen
import com.rieg.drivexrp.presentation.login.SignInScreen
import com.rieg.drivexrp.presentation.signup.SignUpScreen
import kotlinx.serialization.Serializable

// Route for nested graph
@Serializable object AuthGraph

// Routes inside nested graph
@Serializable object AuthRoute
@Serializable object SignInRoute
@Serializable object SignUpRoute


fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation<AuthGraph>(startDestination = AuthRoute) {
        composable<AuthRoute> {
            AuthScreen(
                onNavigateSignIn = { navController.navigate(route = SignInRoute) },
                onNavigateSignUp = { navController.navigate(route = SignUpRoute) },
            )
        }
        composable<SignInRoute> {
            SignInScreen(
                onNavigateSignUp = { navController.navigate(route = SignUpRoute)},
                onNavigateMain = { navController.navigate(route = MainRoute)}
            )
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
