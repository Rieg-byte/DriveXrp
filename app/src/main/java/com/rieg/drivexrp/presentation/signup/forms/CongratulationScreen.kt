package com.rieg.drivexrp.presentation.signup.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.components.HeaderText
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun CongratulationScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center),
                tint = Color.White,
                painter = painterResource(R.drawable.ic_done),
                contentDescription = null,
            )
        }
        HeaderText(text = stringResource(R.string.congratulation))
        Text(
            text = stringResource(R.string.congratulation_text),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CongratulationScreenPreview() {
    DriveXrpTheme {
        CongratulationScreen()
    }
}