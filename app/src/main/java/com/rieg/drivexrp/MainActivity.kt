package com.rieg.drivexrp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.rieg.drivexrp.domain.model.ThemeMode
import com.rieg.drivexrp.navigation.AppNavHost
import com.rieg.drivexrp.network.NetworkConnectionObserve
import com.rieg.drivexrp.network.NetworkConnectionObserveImpl
import com.rieg.drivexrp.network.NetworkConnectionState
import com.rieg.drivexrp.ui.theme.DriveXrpTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var networkConnectionObserve: NetworkConnectionObserve
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        var mainActivityUiState: MainActivityUiState by mutableStateOf(MainActivityUiState(themeMode = ThemeMode.SYSTEM.name))
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainActivityViewModel.mainActivityUiState
                    .onEach { mainActivityUiState = it }
                    .collect()
            }
        }
        networkConnectionObserve = NetworkConnectionObserveImpl(applicationContext)
        setContent {
            DriveXrpTheme(darkTheme = shouldUseDarkTheme(mainActivityUiState)) {
                val status by networkConnectionObserve.observe().collectAsState(
                    initial = NetworkConnectionState.Available
                )
                if (status == NetworkConnectionState.Unavailable) {
                    Toast.makeText(applicationContext, "Нет интернета", Toast.LENGTH_LONG).show() }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
private fun shouldUseDarkTheme(
    mainActivityUiState: MainActivityUiState,
): Boolean = when (mainActivityUiState.themeMode) {
    ThemeMode.LIGHT.name -> false
    ThemeMode.DARK.name -> true
    else -> isSystemInDarkTheme()
}

