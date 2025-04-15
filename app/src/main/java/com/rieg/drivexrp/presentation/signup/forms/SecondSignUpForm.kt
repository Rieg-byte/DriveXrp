package com.rieg.drivexrp.presentation.signup.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rieg.drivexrp.R
import com.rieg.drivexrp.presentation.signup.Gender
import com.rieg.drivexrp.presentation.signup.SignUpUiState
import com.rieg.drivexrp.presentation.signup.SignUpViewModel
import com.rieg.drivexrp.ui.components.pickers.DataPickerFieldToModal
import com.rieg.drivexrp.ui.theme.DriveXrpTheme
import androidx.compose.ui.res.stringResource as stringResource1

@Composable
fun SecondSignUpForm(
    signUpUiState: SignUpUiState,
    signUpViewModel: SignUpViewModel,
    modifier: Modifier = Modifier
) {
    val firstName = signUpUiState.firstName
    val secondName = signUpUiState.secondName
    val middleName = signUpUiState.middleName
    val birthDate = signUpUiState.birthDate
    val gender = signUpUiState.gender
    val firstNameError = signUpUiState.firstNameError?.let { stringResource(it) }
    val secondNameError = signUpUiState.secondNameError?.let { stringResource(it) }
    val middleNameError = signUpUiState.middleNameError?.let { stringResource(it) }
    val birthDateError = signUpUiState.birthDateError?.let { stringResource(it) }
    SecondSignUpForm(
        firstName = firstName,
        onFirstNameChange = signUpViewModel::onUpdateFirstName,
        secondName = secondName,
        onSecondNameChange =  signUpViewModel::onUpdateSecondName,
        middleName = middleName,
        onMiddleNameChange =  signUpViewModel::onUpdateMiddleName,
        birthDate = birthDate,
        onBirthDateChange = signUpViewModel::onUpdateBirthDate,
        gender = gender,
        onGenderChange = signUpViewModel::onGenderChange,
        firstNameError = firstNameError,
        secondNameError = secondNameError,
        middleNameError = middleNameError,
        birthDateError = birthDateError
    )
}


@Composable
private fun SecondSignUpForm(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    secondName: String,
    onSecondNameChange: (String) -> Unit,
    middleName: String,
    onMiddleNameChange: (String) -> Unit,
    birthDate: Long?,
    onBirthDateChange: (Long?) -> Unit,
    gender: Gender,
    onGenderChange: (Gender) -> Unit,
    modifier: Modifier = Modifier,
    firstNameError: String? = null,
    secondNameError: String? = null,
    birthDateError: String? = null,
    middleNameError: String? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource1(id = R.string.first_name),
                    fontSize = 14.sp
                )
            },
            placeholder = {
                Text(
                    text = stringResource1(id = R.string.enter_first_name),
                    fontSize = 14.sp
                )
            },
            value = firstName,
            onValueChange = onFirstNameChange,
            singleLine = true,
            isError = firstNameError != null,
            supportingText = { if (firstNameError != null) Text(firstNameError) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource1(id = R.string.second_name),
                    fontSize = 14.sp
                )
            },
            placeholder = {
                Text(
                    text = stringResource1(id = R.string.enter_second_name),
                    fontSize = 14.sp
                )
            },
            value = secondName,
            onValueChange = onSecondNameChange,
            singleLine = true,
            isError = secondNameError != null,
            supportingText = { if (secondNameError != null) Text(secondNameError) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource1(id = R.string.middle_name),
                    fontSize = 14.sp
                )
            },
            placeholder = {
                Text(
                    text = stringResource1(id = R.string.enter_middle_name),
                    fontSize = 14.sp
                )
            },
            value = middleName,
            onValueChange = onMiddleNameChange,
            singleLine = true,
            isError = middleNameError != null,
            supportingText = { if (middleNameError != null) Text(middleNameError) }
        )
        DataPickerFieldToModal(
            selectedDate = birthDate,
            onDateSelected = onBirthDateChange,
            label = stringResource1(R.string.birth_date),
            isError = birthDateError != null,
            supportingText = {
                if (birthDateError != null) {
                    Text(
                        text = birthDateError,
                        fontSize = 12.sp
                    )
                }
            }
        )
        Column {
            Text(text = stringResource1(id = R.string.gender))
            Spacer(Modifier.height(4.dp))
            GenderRadioButtonSelection(gender = gender, onGenderChange = onGenderChange)
        }
    }
}


@Composable
fun GenderRadioButtonSelection(
    gender: Gender,
    onGenderChange: (Gender) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GenderRadioButton(
                selected = (gender == Gender.MALE),
                onClick = { onGenderChange(Gender.MALE) },
                text = stringResource1(id = R.string.male)
            )
            Spacer(Modifier.width(4.dp))
            GenderRadioButton(
                selected = (gender == Gender.FEMALE),
                onClick = { onGenderChange(Gender.FEMALE) },
                text = stringResource1(id = R.string.female)
            )
        }
    }
}


@Composable
fun GenderRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier
                .selectable(
                    selected = selected,
                    onClick = onClick,
                    role = Role.RadioButton
                ),
            selected = selected,
            onClick = onClick
        )
        Spacer(Modifier.width(2.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun SecondSignUpFormPreview() {
    DriveXrpTheme {
        SecondSignUpForm(
            firstName = "",
            onFirstNameChange = {},
            firstNameError = null,
            secondName = "",
            onSecondNameChange = {},
            secondNameError = null,
            middleName = "",
            onMiddleNameChange = {},
            middleNameError = null,
            birthDate = null,
            onBirthDateChange = {},
            birthDateError = null,
            gender = Gender.MALE,
            onGenderChange = {}
        )
    }
}

