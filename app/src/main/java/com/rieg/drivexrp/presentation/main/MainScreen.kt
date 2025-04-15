import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rieg.drivexrp.navigation.MainNavGraph
import com.rieg.drivexrp.navigation.bottomLevelRoutes

@Composable
fun MainScreen(navRootController: NavHostController, navController: NavHostController) {
    Scaffold(
        bottomBar = {
            MainBottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavGraph(navRootController = navRootController, navController)
        }

    }
}


@Composable
fun MainBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomLevelRoutes.forEach { bottomLevelRoute ->
            NavigationBarItem(
                icon = { Icon(bottomLevelRoute.icon, contentDescription = bottomLevelRoute.name) },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(bottomLevelRoute.route::class) } == true,
                onClick = {
                    navController.navigate(bottomLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}