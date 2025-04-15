package com.rieg.drivexrp.presentation.mybooking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rieg.drivexrp.R

@Composable
fun MyBookingScreen(
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            MyBookingTopBar(onBack = onBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        ) {
           Text(
               text = "Ничего нет"
           )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingTopBar(
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
            Text(text = stringResource(R.string.my_bookings))
        }
    )
}