package com.rieg.drivexrp.ui.components.pickers

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun PhotoPicker(
    label: String,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    uri: Uri? = null,
    isError: Boolean = false,
    supportingText:  String? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = label)
        Spacer(Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        width = 1.dp,
                        color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.small
                    ),
                onClick = onClick
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_upload),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
            Spacer(Modifier.width(4.dp))
            Text(
                modifier = Modifier
                    .clickable { onClick() },
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                text = uri?.toString() ?: text
            )
        }
        if (supportingText != null) {
            Text(
                text = supportingText,
                color = if (isError) MaterialTheme.colorScheme.error else Color.Unspecified,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadPhotoPreview() {
    DriveXrpTheme {
        PhotoPicker(
            label = stringResource(id = R.string.load_photo_driver_license_label),
            onClick = {},
            text = stringResource(id = R.string.load_photo)
        )
    }
}