package com.rieg.drivexrp.presentation.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rieg.drivexrp.R
import com.rieg.drivexrp.presentation.signup.Gender
import com.rieg.drivexrp.ui.components.pickers.ProfilePhotoPicker
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    onBack: () -> Unit
) {
    val profileUiState by profileViewModel.profileUiState.collectAsState()
    val profilePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            profileViewModel.onUpdateProfileImage(it)
        }
    )
    ProfileScreen(
        firstName = profileUiState.firstName,
        lastName = profileUiState.lastName,
        joinYear = profileUiState.joinYear,
        joinMonth = profileUiState.joinMonth,
        uri = profileUiState.profileImage,
        onChangeProfileImage = {
            profilePhotoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        email = profileUiState.email,
        gender = profileUiState.gender,
        onChangePassword = {},
        onBack = onBack,
        googleEmail = profileUiState.googleEmail
    )
}


@Composable
private fun ProfileScreen(
    firstName: String,
    lastName: String,
    joinYear: Int,
    joinMonth: Int,
    uri: Uri?,
    onChangeProfileImage: () -> Unit,
    email: String,
    gender: Gender,
    onChangePassword: () -> Unit,
    onBack: () -> Unit,
    googleEmail: String? = null,
) {
    Scaffold(
        topBar = {
            ProfileTopBar(onBack = onBack)
        }
    ) { innerPadding ->
        ProfileContent(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            firstName = firstName,
            lastName = lastName,
            joinYear = joinYear,
            joinMonth = joinMonth,
            uri = uri,
            onChangeProfileImage = onChangeProfileImage,
            email = email,
            gender = gender,
            onChangePassword = onChangePassword,
            googleEmail = googleEmail
        )

    }
}

@Composable
private fun ProfileContent(
    firstName: String,
    lastName: String,
    joinYear: Int,
    joinMonth: Int,
    uri: Uri?,
    onChangeProfileImage: () -> Unit,
    email: String,
    gender: Gender,
    onChangePassword: () -> Unit,
    modifier: Modifier = Modifier,
    googleEmail: String? = null,
) {
    Column(
        modifier = modifier
    ) {
        UserProfileBlock(
            firstName = firstName,
            lastName = lastName,
            joinMonth = joinMonth,
            joinYear = joinYear,
            uri = uri,
            onChangeProfileImage = onChangeProfileImage,
        )
        Spacer(Modifier.height(16.dp))
        UserInfoBlock(
            email = email,
            gender = gender,
            onChangePassword = onChangePassword,
            googleEmail = googleEmail
        )
    }
}

@Composable
private fun UserProfileBlock(
    firstName: String,
    lastName: String,
    joinMonth: Int,
    joinYear: Int,
    uri: Uri?,
    onChangeProfileImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePhotoPicker(
                onClick = onChangeProfileImage,
                uri = uri
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$firstName $lastName",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.joined, joinMonth, joinYear),
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun UserInfoBlock(
    modifier: Modifier = Modifier,
    email: String,
    gender: Gender,
    onChangePassword: () -> Unit,
    googleEmail: String? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        UserInfoItem(
            title = stringResource(R.string.email),
            description = email
        )
        UserInfoItem(
            title = stringResource(R.string.password),
            description = stringResource(R.string.change_password),
            onClick = onChangePassword
        )
        UserInfoItem(
            title = stringResource(R.string.gender),
            description =
                if (gender == Gender.MALE) stringResource(R.string.male)
                else stringResource(R.string.female)
        )
        UserInfoItem(
            title = stringResource(R.string.google),
            description = googleEmail ?: stringResource(R.string.no_google_email),
        )
    }
}

@Composable
private fun UserInfoItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.clickable(enabled = onClick != null, onClick = { onClick?.invoke() }),
                text = description,
                color = if (onClick != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopBar(
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
            Text(text = stringResource(R.string.profile))
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    DriveXrpTheme {
        ProfileScreen(
            firstName = "Иван",
            lastName = "Иванов",
            joinYear = 2021,
            joinMonth = 8,
            uri = null,
            onChangeProfileImage = {},
            email = "ivan@ya.ru",
            gender = Gender.MALE,
            onChangePassword = {},
            onBack = {},
            googleEmail = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserInfoBlockPreview() {
    DriveXrpTheme {
        UserInfoBlock(
            email = "ivan@ya.ru",
            gender = Gender.MALE,
            onChangePassword = {}
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun UserInfoItemPreview() {
    DriveXrpTheme {
        UserInfoItem(
            title = "Пол",
            description = "Мужской"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserProfileBlock() {
    DriveXrpTheme {
        UserProfileBlock(
            firstName = "John",
            lastName = "Doe",
            joinMonth = 8,
            joinYear = 2021,
            uri = null,
            onChangeProfileImage = { }
        )
    }
}