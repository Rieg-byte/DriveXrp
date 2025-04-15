package com.rieg.drivexrp.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rieg.drivexrp.R
import com.rieg.drivexrp.presentation.signup.forms.CongratulationScreen
import com.rieg.drivexrp.presentation.signup.forms.FirstSignUpForm
import com.rieg.drivexrp.presentation.signup.forms.SecondSignUpForm
import com.rieg.drivexrp.presentation.signup.forms.ThirdSignUpForm
import com.rieg.drivexrp.ui.components.HeaderText
import com.rieg.drivexrp.ui.components.XrpButton
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onFinish: () -> Unit
) {
    val signUpUiState by signUpViewModel.signUpUiState.collectAsState()
    val registerForm = signUpUiState.registerForm
    Scaffold(
        topBar = {
            SignUpTopAppBar(
                isFinish = signUpUiState.isFinish,
                onNavigate = {
                    when (registerForm) {
                        RegisterForm.FIRST -> onNavigateBack()
                        RegisterForm.SECOND -> signUpViewModel.updateRegisterForm(RegisterForm.FIRST)
                        RegisterForm.THIRD -> signUpViewModel.updateRegisterForm(RegisterForm.SECOND)
                    }
                }
            )
        },
        bottomBar = {
            SignUpButton(
                onClick = {
                    if (!signUpUiState.isFinish) {
                        when(registerForm) {
                            RegisterForm.FIRST -> signUpViewModel.submitFirstForm(
                                email = signUpUiState.email,
                                password = signUpUiState.password,
                                repeatPassword = signUpUiState.repeatPassword,
                                isAccepted = signUpUiState.isAccept
                            )
                            RegisterForm.SECOND -> signUpViewModel.submitSecondForm(
                                firstName = signUpUiState.firstName,
                                secondName = signUpUiState.secondName,
                                middleName = signUpUiState.middleName,
                                birthDay = signUpUiState.birthDate
                            )
                            RegisterForm.THIRD -> signUpViewModel.submitThirdForm(
                                profileImage = signUpUiState.profileImage,
                                driverLicense = signUpUiState.driverLicense,
                                dateOfIssue = signUpUiState.dateOfIssue,
                                passportImage = signUpUiState.passportImage,
                                driverLicenseImage = signUpUiState.driverLicenseImage
                            )
                        }
                    } else {
                        onFinish()
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (!signUpUiState.isFinish) {
                when(registerForm) {
                    RegisterForm.FIRST -> FirstSignUpForm(signUpUiState, signUpViewModel)
                    RegisterForm.SECOND -> SecondSignUpForm(signUpUiState, signUpViewModel)
                    RegisterForm.THIRD -> ThirdSignUpForm(signUpUiState, signUpViewModel)
                }
            } else {
                CongratulationScreen()
            }
        }
    }
}

@Composable
fun SignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        XrpButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopAppBar(
    isFinish: Boolean,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp),
        title = {
            HeaderText(
                text = if (isFinish) stringResource(id = R.string.congratulation_title) else stringResource(id = R.string.sign_up_title)
            )
        },
        navigationIcon = {
            if (!isFinish) {
                IconButton(
                    onClick = onNavigate
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DriveXrpTheme {
        SignUpScreen(onNavigateBack = {}, onFinish = {})
    }
}