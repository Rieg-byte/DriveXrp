package com.rieg.drivexrp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rieg.drivexrp.presentation.connectcar.ConnectCarScreen
import com.rieg.drivexrp.presentation.mybooking.MyBookingScreen
import com.rieg.drivexrp.presentation.profile.ProfileScreen
import com.rieg.drivexrp.presentation.settings.SettingsScreen
import com.rieg.drivexrp.presentation.theme.ThemeScreen
import kotlinx.serialization.Serializable


@Serializable
object SettingsRoute

@Serializable
object ProfileRoute

@Serializable
object MyBookingRoute

@Serializable
object ThemeRoute

@Serializable
object NotificationRoute

@Serializable
object ConnectCarRoute

@Serializable
object HelpRoute

@Serializable
object InviteFriendRoute

fun NavGraphBuilder.settingsGraph(navRootController: NavHostController, navController: NavController) {
    navigation<SettingsGraph>(startDestination = SettingsRoute) {
        composable<SettingsRoute> {
            SettingsScreen(
                navigateToTheme = {
                    navController.navigate(ThemeRoute)
                },
                navigateToProfile = {
                    navController.navigate(ProfileRoute)
                },
                navigateToAuth = {
                    navRootController.navigate(AuthGraph)
                },
                navigateToConnectCar = {
                    navController.navigate(ConnectCarRoute)
                },
                navigateToMyBooking = {
                    navController.navigate(MyBookingRoute)
                }
            )
        }

        composable<ProfileRoute> {
            ProfileScreen(
                onBack = { navController.navigate(SettingsGraph) }
            )
        }

        composable<MyBookingRoute> {
            MyBookingScreen(
                onBack = {
                    navController.navigate(SettingsGraph)
                }
            )
        }

        composable<ThemeRoute> {
            ThemeScreen(onBack = { navController.navigate(SettingsGraph) })
        }
        composable<NotificationRoute> {

        }

        composable<ConnectCarRoute> {
            ConnectCarScreen(
                onBack = {
                    navController.navigate(SettingsGraph)
                }
            )
        }

        composable<HelpRoute> {

        }

        composable<InviteFriendRoute> {

        }
    }
}