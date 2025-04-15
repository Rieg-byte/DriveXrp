package com.rieg.drivexrp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rieg.drivexrp.presentation.auth.AuthScreen
import com.rieg.drivexrp.presentation.signin.SignInScreen
import com.rieg.drivexrp.presentation.signup.SignUpScreen
import kotlinx.serialization.Serializable



@Serializable
object AuthRoute
@Serializable
object SignInRoute
@Serializable
object SignUpRoute


fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation<AuthGraph>(startDestination = AuthRoute) {
        composable<AuthRoute> {
            AuthScreen(
                onNavigateSignIn = { navController.navigate(route = SignInRoute) {
                    launchSingleTop = true
                } },
                onNavigateSignUp = { navController.navigate(route = SignUpRoute) {
                    launchSingleTop = true
                } },
            )
        }
        composable<SignInRoute> {
            SignInScreen(
                onNavigateSignUp = { navController.navigate(route = SignUpRoute) {
                    launchSingleTop = true
                } },
                onNavigateMain = { navController.navigate(route = MainRoute) {
                    launchSingleTop = true
                    popUpTo(route = AuthGraph) {
                        inclusive = true
                    }
                } }
            )
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onNavigateBack = { navController.popBackStack() },
                onFinish = { navController.navigate(route = AuthGraph) {
                    popUpTo(route = SignUpRoute) {
                        inclusive = true
                    }
                } }
            )
        }
    }
}
