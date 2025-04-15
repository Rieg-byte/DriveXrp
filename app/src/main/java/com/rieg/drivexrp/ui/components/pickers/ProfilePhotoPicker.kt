package com.rieg.drivexrp.ui.components.pickers

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun ProfilePhotoPicker(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    uri: Uri? = null
) {
    Box(
        modifier = modifier.size(128.dp)
    ) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(128.dp)
                .border(
                    width = 5.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = CircleShape
                )
        )
        IconButton(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.BottomEnd),
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PhotoPickerPreview() {
    DriveXrpTheme {
        ProfilePhotoPicker(
            onClick = {}
        )
    }
}