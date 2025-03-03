package com.rieg.drivexrp.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.components.XrpButton
import com.rieg.drivexrp.ui.components.XrpOutlinedButton
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun AuthScreen(
    onNavigateSignIn: () -> Unit,
    onNavigateSignUp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.auth_content),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(Modifier.height(40.dp))
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(id = R.drawable.logo_image),
                contentDescription = null
            )
            Spacer(Modifier.height(40.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                XrpButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateSignIn
                ) {
                    Text(
                        text = stringResource(id = R.string.login_button),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                XrpOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateSignUp
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up_button),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    DriveXrpTheme {
        AuthScreen(
            onNavigateSignIn = { },
            onNavigateSignUp = { }
        )
    }

}