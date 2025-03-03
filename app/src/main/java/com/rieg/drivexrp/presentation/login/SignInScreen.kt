package com.rieg.drivexrp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.components.EmailTextField
import com.rieg.drivexrp.ui.components.HeaderText
import com.rieg.drivexrp.ui.components.PasswordTextField
import com.rieg.drivexrp.ui.components.XrpButton
import com.rieg.drivexrp.ui.components.XrpOutlinedButton
import com.rieg.drivexrp.ui.theme.DriveXrpTheme


@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
    onNavigateSignUp: () -> Unit,
    onNavigateMain: () -> Unit
) {
    val signInUiState by signInViewModel.signInUiState.collectAsState()
    LaunchedEffect(signInUiState.isSignIn) {
        if (signInUiState.isSignIn) {
            onNavigateMain()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(
            horizontal = 20.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderText(text = stringResource(id = R.string.login_header))
        Text(
            text = stringResource(id = R.string.login_prompt),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        EmailTextField(
            label = stringResource(id = R.string.email),
            placeholder = stringResource(id = R.string.email_prompt),
            email = signInUiState.email,
            onEmailChange = signInViewModel::onUpdateEmail,
            isError = false,
            supportingText = ""
        )
        PasswordTextField(
            label = stringResource(id = R.string.password),
            placeholder = stringResource(id = R.string.password_prompt),
            password = signInUiState.password,
            onPasswordChange = signInViewModel::onUpdatePassword,
            isError = false,
            supportingText = ""
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.clickable {

            },
            text = stringResource(id = R.string.forgot_password),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        XrpButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = signInViewModel::onSignIn
        ) {
            Text(
                text = stringResource(id = R.string.login_button)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        XrpOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.login_with_google)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.clickable(onClick = onNavigateSignUp),
            text = stringResource(id = R.string.sign_up_button)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    DriveXrpTheme {
        SignInScreen(
            onNavigateSignUp = { },
            onNavigateMain = { }
        )
    }

}