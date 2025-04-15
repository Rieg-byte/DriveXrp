package com.rieg.drivexrp.presentation.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rieg.drivexrp.R
import com.rieg.drivexrp.domain.model.ThemeMode
import com.rieg.drivexrp.ui.theme.DriveXrpTheme


@Composable
fun ThemeScreen(
    themeViewModel: ThemeViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val themeUiState by themeViewModel.themeUiState.collectAsStateWithLifecycle()
    ThemeContent(
        themeMode = themeUiState.themeMode,
        onChangeModeTheme = themeViewModel::updateThemeMode,
        onBack = onBack
    )
}

@Composable
fun ThemeContent(
    themeMode: String,
    onChangeModeTheme: (ThemeMode) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            ThemeTopBar(onBack = onBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.theme),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Column(
                modifier = Modifier.selectableGroup()
            ) {
                ThemeModeChooser(
                    text = stringResource(id = R.string.theme_system),
                    selected = themeMode == ThemeMode.SYSTEM.name,
                    onClick = { onChangeModeTheme(ThemeMode.SYSTEM) }
                )
                ThemeModeChooser(
                    text = stringResource(id = R.string.theme_light),
                    selected = themeMode == ThemeMode.LIGHT.name,
                    onClick = { onChangeModeTheme(ThemeMode.LIGHT) }
                )
                ThemeModeChooser(
                    text = stringResource(id = R.string.theme_dark),
                    selected = themeMode == ThemeMode.DARK.name,
                    onClick = { onChangeModeTheme(ThemeMode.DARK) }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeTopBar(
    onBack: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = null
                )
            }
        },
        title = {
            Text(text = stringResource(R.string.theme))
        }
    )
}


@Composable
fun ThemeModeChooser(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeContentPreview() {
    DriveXrpTheme {
        ThemeContent(
            themeMode = ThemeMode.DARK.name,
            onChangeModeTheme = {},
            onBack = {}
        )
    }
}