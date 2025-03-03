package com.rieg.drivexrp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rieg.drivexrp.R


@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    isError: Boolean = false,
    supportingText: String? = null
) {
    var isVisibility by rememberSaveable { mutableStateOf(false) }
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
        value = password,
        onValueChange = onPasswordChange,
        isError = isError,
        singleLine = true,
        supportingText = {
            if (isError && supportingText != null) {
                Text(
                    text = supportingText,
                    fontSize = 12.sp
                )
            }
        },
        visualTransformation = if (isVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(
                onClick = {
                    isVisibility = !isVisibility
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isVisibility) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                    ),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(
        modifier = Modifier.fillMaxWidth(),
        label = "Пароль",
        placeholder = "Введите пароль",
        password = "",
        onPasswordChange = {}
    )
}
