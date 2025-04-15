package com.rieg.drivexrp.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rieg.drivexrp.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    data object First: OnboardingPage(
        image = R.drawable.onboarding_second,
        title = R.string.onboarding_title_first,
        description = R.string.onboarding_description_first
    )
    data object Second: OnboardingPage(
        image = R.drawable.onboarding_second,
        title = R.string.onboarding_title_second,
        description = R.string.onboarding_description_second
    )
    data object Third: OnboardingPage(
        image = R.drawable.onboarding_first,
        title = R.string.onboarding_title_third,
        description = R.string.onboarding_description_third
    )
}