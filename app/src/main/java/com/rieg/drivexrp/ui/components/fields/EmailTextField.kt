package com.rieg.drivexrp.ui.components.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun EmailTextField(
    label: String,
    placeholder: String,
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText:  @Composable() (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                fontSize = 14.sp
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp
            )
        },
        singleLine = true,
        value = email,
        onValueChange = onEmailChange,
        isError = isError,
        supportingText = supportingText
    )
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview() {
    EmailTextField(
        modifier = Modifier.fillMaxWidth(),
        label = "Электронная почта",
        placeholder = "Введите электронную почту",
        email = "",
        onEmailChange = {}
    )
}
