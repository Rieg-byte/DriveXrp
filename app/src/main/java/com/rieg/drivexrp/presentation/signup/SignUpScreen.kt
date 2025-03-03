package com.rieg.drivexrp.presentation.signup

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.components.EmailTextField
import com.rieg.drivexrp.ui.components.HeaderText
import com.rieg.drivexrp.ui.components.PasswordTextField
import com.rieg.drivexrp.ui.components.XrpButton
import com.rieg.drivexrp.ui.theme.DriveXrpTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val signUpUiState by signUpViewModel.signUpUiState.collectAsState()
    var form by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier.padding(8.dp),
            title = {
                HeaderText(
                    text = stringResource(id = R.string.create_account)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (form == 0) {
                            onNavigateBack()
                        } else {
                            form--
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = null
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            when(form) {
                0 -> FirstSignUpForm(signUpUiState, signUpViewModel)
                1 -> SecondSignUpForm(signUpUiState,signUpViewModel)
            }
            XrpButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    form++
                }
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
        }
}


@Composable
fun FirstSignUpForm(
    signUpUiState: SignUpUiState,
    signUpViewModel: SignUpViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EmailTextField(
            label = stringResource(id = R.string.email),
            placeholder = stringResource(id = R.string.email_prompt),
            email = signUpUiState.email,
            onEmailChange = signUpViewModel::onUpdateEmail,
            isError = false,
            supportingText = "Error"
        )
        PasswordTextField(
            label = stringResource(id = R.string.password),
            placeholder = stringResource(id = R.string.password_prompt),
            password = signUpUiState.password,
            onPasswordChange = signUpViewModel::onUpdatePassword,
            isError = false,
            supportingText = ""
        )
        PasswordTextField(
            label = stringResource(id = R.string.repeat_password),
            placeholder = stringResource(id = R.string.password_prompt),
            password = signUpUiState.repeatPassword,
            onPasswordChange = signUpViewModel::onUpdateRepeatPassword,
            isError = false,
            supportingText = ""
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = signUpUiState.isAgreed,
                onCheckedChange = { signUpViewModel.onAgreed(signUpUiState.isAgreed) }
            )
            Text("Я согласен с условиями обслуживания и политикой конфиденциальности")
        }
    }
}

@Composable
fun SecondSignUpForm(
    signUpUiState: SignUpUiState,
    signUpViewModel: SignUpViewModel
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.first_name),
                    fontSize = 14.sp
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_first_name),
                    fontSize = 14.sp
                )
            },
            value = "",
            onValueChange = {  },
            singleLine = true
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.second_name),
                    fontSize = 14.sp
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_second_name),
                    fontSize = 14.sp
                )
            },
            value = "",
            onValueChange = {  },
            singleLine = true
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.middle_name),
                    fontSize = 14.sp
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_middle_name),
                    fontSize = 14.sp
                )
            },
            value = "",
            onValueChange = {  },
            singleLine = true
        )
        OutlinedTextField(
            value = selectedDate?.let { convertMillisToDate(it) } ?: "",
            onValueChange = { },
            label = { Text("DOB") },
            placeholder = { Text("MM/DD/YYYY") },
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = "Select date")
            },
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(selectedDate) {
                    awaitEachGesture {
                        // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                        // in the Initial pass to observe events before the text field consumes them
                        // in the Main pass.
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            showModal = true
                        }
                    }
                }
        )
        if (showModal) {
            DatePickerModal(
                onDateSelected = { selectedDate = it },
                onDismiss = { showModal = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DriveXrpTheme {
       SignUpScreen(onNavigateBack = {})
    }
}