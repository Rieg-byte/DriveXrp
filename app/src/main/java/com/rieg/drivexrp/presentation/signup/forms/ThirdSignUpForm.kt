package com.rieg.drivexrp.presentation.signup.forms

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rieg.drivexrp.R
import com.rieg.drivexrp.presentation.signup.SignUpUiState
import com.rieg.drivexrp.presentation.signup.SignUpViewModel
import com.rieg.drivexrp.ui.components.pickers.DataPickerFieldToModal
import com.rieg.drivexrp.ui.components.pickers.ProfilePhotoPicker
import com.rieg.drivexrp.ui.components.pickers.PhotoPicker
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun ThirdSignUpForm(
    signUpUiState: SignUpUiState,
    signUpViewModel: SignUpViewModel,
    modifier: Modifier = Modifier
) {
    val driverLicense = signUpUiState.driverLicense
    val dateOfIssue = signUpUiState.dateOfIssue
    val driverLicenseError = signUpUiState.driverLicenseError?.let { stringResource(it) }
    val dateOfIssueError = signUpUiState.dateOfIssueError?.let { stringResource(it) }
    val profileImage = signUpUiState.profileImage
    val passportImage = signUpUiState.passportImage
    val driverLicenseImage = signUpUiState.driverLicenseImage
    val profilePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            signUpViewModel.onUpdateProfileImage(it)
        }
    )
    val passportPhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            signUpViewModel.onUpdatePassportImage(it)
        }
    )
    val driverLicensePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            signUpViewModel.onUpdateDriverLicenseImage(it)
        }
    )
    ThirdSignUpForm(
        driverLicense = driverLicense,
        onDriverLicenseChange = signUpViewModel::onUpdateDriverLicense,
        dateOfIssue = dateOfIssue,
        onDateOfIssueChange = signUpViewModel::onUpdateDateOfIssue,
        onProfileImagePick = {
            profilePhotoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        onPassportImagePick = {
            passportPhotoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        onDriverLicenseImagePick = {
            driverLicensePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        dateOfIssueError = dateOfIssueError,
        profileImage = profileImage,
        driverLicenseError = driverLicenseError,
        passportImage = passportImage,
        driverLicenseImage = driverLicenseImage
    )
}
@Composable
private fun ThirdSignUpForm(
    driverLicense: String,
    onDriverLicenseChange: (String) -> Unit,
    dateOfIssue: Long?,
    onDateOfIssueChange: (Long?) -> Unit,
    onProfileImagePick: () -> Unit,
    onPassportImagePick: () -> Unit,
    onDriverLicenseImagePick: () -> Unit,
    modifier: Modifier = Modifier,
    driverLicenseError: String? = null,
    dateOfIssueError: String? = null,
    profileImage: Uri? = null,
    passportImage: Uri? = null,
    driverLicenseImage: Uri? = null,
    driverLicenseImageError: String? = null,
    passportImageError: String? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePhotoPicker(
                onClick = onProfileImagePick,
                uri = profileImage
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.load_photo_info),
                textAlign = TextAlign.Justify
            )
        }
        DriverLicenseField(
            driverLicense = driverLicense,
            onDriverLicenseChange = onDriverLicenseChange,
            isError = driverLicenseError != null,
            driverLicenseError = driverLicenseError
        )
        DataPickerFieldToModal(
            selectedDate =  dateOfIssue,
            onDateSelected = onDateOfIssueChange,
            label = stringResource(R.string.date_of_issue_label),
            isError = dateOfIssueError != null,
            supportingText = {
                if (dateOfIssueError != null) {
                    Text(
                        text = dateOfIssueError,
                        fontSize = 12.sp
                    )
                }
            }
        )
        PhotoPicker(
            label = stringResource(R.string.load_photo_driver_license_label),
            onClick = onDriverLicenseImagePick,
            text = stringResource(R.string.load_photo),
            uri = driverLicenseImage,
            isError = driverLicenseImageError != null,
            supportingText = driverLicenseImageError
        )
        PhotoPicker(
            label = stringResource(R.string.load_photo_passport_label),
            onClick = onPassportImagePick,
            text = stringResource(R.string.load_photo),
            uri = passportImage,
            isError = passportImageError != null,
            supportingText = passportImageError
        )
    }
}

@Composable
fun DriverLicenseField(
    driverLicense: String,
    onDriverLicenseChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    driverLicenseError: String? = null
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = driverLicense,
        onValueChange = onDriverLicenseChange,
        placeholder = { Text(text = stringResource(R.string.driver_license_placeholder)) },
        label = { Text(text = stringResource(R.string.driver_license_label)) },
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError && driverLicenseError != null) {
                Text(
                    text = driverLicenseError,
                    fontSize = 12.sp
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ThirdSignUpFormPreview() {
    DriveXrpTheme {
        ThirdSignUpForm(
            driverLicense = "",
            onDriverLicenseChange = {},
            driverLicenseError = "",
            dateOfIssue = null,
            onDateOfIssueChange = {},
            onProfileImagePick = {},
            onPassportImagePick = {},
            onDriverLicenseImagePick = {},
            profileImage = null,
            passportImage = null,
            driverLicenseImage = null

        )
    }
}