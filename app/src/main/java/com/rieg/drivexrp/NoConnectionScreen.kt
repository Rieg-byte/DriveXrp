package com.rieg.drivexrp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rieg.drivexrp.ui.components.HeaderText
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun NoConnectionScreen(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.no_connection),
                contentDescription = stringResource(id = R.string.no_connection),
                tint = MaterialTheme.colorScheme.primary
            )
            HeaderText(
                text = stringResource(id = R.string.no_connection),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.check_connection_and_refresh),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(50.dp)
            ,
            shape = MaterialTheme.shapes.small,
            onClick = onRefresh
        ) {
            Text(
                text = stringResource(id = R.string.refresh),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoConnectionScreenPreview() {
    DriveXrpTheme {
        NoConnectionScreen()
    }
}