package com.rieg.drivexrp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlin.math.sin

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    email: String,
    onEmailChange: (String) -> Unit,
    isError: Boolean = false,
    supportingText: String? = null
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
        supportingText = {
            if (isError && supportingText != null) {
                Text(
                    text = supportingText,
                    fontSize = 12.sp
                )
            }
        }
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
