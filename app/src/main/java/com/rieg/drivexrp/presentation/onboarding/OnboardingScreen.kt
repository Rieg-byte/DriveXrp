package com.rieg.drivexrp.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rieg.drivexrp.R
import com.rieg.drivexrp.ui.components.PagerIndicator
import com.rieg.drivexrp.ui.components.XrpButton
import com.rieg.drivexrp.ui.theme.DriveXrpTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onSkip: () -> Unit,
    onFinish: () -> Unit
) {
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            modifier = Modifier.padding(horizontal = 20.dp),
            title = {},
            actions = {
                TextButton(
                    onClick = onSkip,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = stringResource(R.string.skip))
                }
            })
        Spacer(Modifier.size(40.dp))
        HorizontalPager(
            state = pagerState
        ) { index ->
            OnboardingHorizontalPager(
                onboardingPage = pages[index]
            )
        }
        Spacer(Modifier.size(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PagerIndicator(
                pageSize = pagerState.pageCount,
                currentPage = pagerState.currentPage
            )
            XrpButton(
                modifier = Modifier.width(120.dp),
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        } else {
                            onFinish()
                        }
                    }
                }
            ) {
                Text(
                    text =
                    if (pagerState.currentPage < pagerState.pageCount - 1)
                        stringResource(R.string.next)
                    else stringResource(R.string.go)
                )
            }
        }

    }
}

@Composable
fun OnboardingHorizontalPager(
    onboardingPage: OnboardingPage
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            alignment = Alignment.Center,
            painter = painterResource(onboardingPage.image),
            contentDescription = null
        )
        Spacer(Modifier.height(50.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(onboardingPage.title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
        Spacer(Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(onboardingPage.description),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingHorizontalPagerPreview() {
    DriveXrpTheme {
        OnboardingHorizontalPager(
            onboardingPage = OnboardingPage.First
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreenPreview() {
    DriveXrpTheme {
        OnboardingScreen(
            onSkip = {},
            onFinish = {}
        )
    }
}