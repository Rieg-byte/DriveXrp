package com.rieg.drivexrp.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun XrpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier.height(40.dp),
        onClick = onClick,
        shape = shape,
        content = content
    )
}

@Composable
fun XrpOutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        modifier = modifier.height(40.dp),
        onClick = onClick,
        shape = shape,
        content = content
    )
}