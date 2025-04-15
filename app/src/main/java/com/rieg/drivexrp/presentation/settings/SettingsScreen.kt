package com.rieg.drivexrp.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun SettingsScreen(
    navigateToProfile: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToAuth: () -> Unit,
    navigateToMyBooking: () -> Unit,
    navigateToConnectCar: () -> Unit
) {
    SettingsContent(
        navigateToProfile = navigateToProfile,
        navigateToTheme = navigateToTheme,
        navigateToAuth = navigateToAuth,
        navigateToMyBooking = navigateToMyBooking,
        navigateToConnectCar = navigateToConnectCar
    )
}


@Composable
fun SettingsContent(
    navigateToProfile: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToAuth: () -> Unit,
    navigateToMyBooking: () -> Unit,
    navigateToConnectCar: () -> Unit
) {
    Scaffold(
        topBar = {
            SettingsTopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileContainer(
                    firstName = "Иван",
                    lastName = "Иванов",
                    email = "lineked@ya.ru",
                    onClick = navigateToProfile
                )
                SettingsContainer(
                    painter = painterResource(R.drawable.ic_car),
                    text = stringResource(R.string.my_bookings),
                    onClick = navigateToMyBooking,
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SettingsContainer(
                    painter = painterResource(R.drawable.ic_theme),
                    text = stringResource(R.string.theme),
                    onClick = navigateToTheme,
                )
                SettingsContainer(
                    painter = painterResource(R.drawable.ic_notification),
                    text = stringResource(R.string.notifications),
                    onClick = {},
                )
                SettingsContainer(
                    painter = painterResource(R.drawable.ic_payments),
                    text = stringResource(R.string.connect_car),
                    onClick = navigateToConnectCar,
                )
                SettingsContainer(
                    painter = painterResource(R.drawable.ic_support),
                    text = stringResource(R.string.help),
                    onClick = {},
                )
                SettingsContainer(
                    painter = painterResource(R.drawable.ic_mail),
                    text = stringResource(R.string.invite_friend),
                    onClick = {},
                )
                SettingsContainer(
                    painter = painterResource(R.drawable.ic_logout),
                    text = stringResource(R.string.log_out),
                    onClick = navigateToAuth,
                )
            }
        }
    }
}



@Composable
fun ProfileContainer(
    firstName: String,
    lastName: String,
    email: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp),
                painter = painterResource(R.drawable.ic_person),
                contentDescription = null
            )
            Spacer(Modifier.width(20.dp))
            Column {
                Text(
                    text = "$firstName $lastName",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = email,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        IconButton(
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.settings))
        }
    )
}

@Composable()
fun SettingsContainer(
    painter: Painter,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painter,
                contentDescription = null
            )
            Spacer(Modifier.width(20.dp))
            Text(text = text)
        }
        IconButton(
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsContentPreview() {
    DriveXrpTheme {
        SettingsContent(
            navigateToProfile = {},
            navigateToTheme = {},
            navigateToAuth = {},
            navigateToMyBooking = {},
            navigateToConnectCar = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileContainerPreview() {
    DriveXrpTheme {
        ProfileContainer(
            firstName = "Иван",
            lastName = "Иванов",
            email = "ivan@ya.ru",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsContainerPreview() {
    DriveXrpTheme {
        SettingsContainer(
            painter = painterResource(R.drawable.ic_theme),
            text = "Тема",
            onClick = {}
        )
    }
}
