package com.rieg.drivexrp.ui.components

import android.graphics.pdf.PdfDocument.Page
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rieg.drivexrp.ui.theme.DriveXrpTheme

@Composable
fun PagerIndicator(
    pageSize: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .animateContentSize()
                    .width(if (page == currentPage) 50.dp else 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = if (page == currentPage) selectedColor else unSelectedColor)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PagerIndicatorPreview1() {
    DriveXrpTheme {
        PagerIndicator(
            pageSize = 3,
            currentPage = 0
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagerIndicatorPreview2() {
    DriveXrpTheme {
        PagerIndicator(
            pageSize = 3,
            currentPage = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagerIndicatorPreview3() {
    DriveXrpTheme {
        PagerIndicator(
            pageSize = 3,
            currentPage = 2
        )
    }
}