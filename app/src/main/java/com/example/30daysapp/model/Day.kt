package com.example.`30daysapp`.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val dayRes: Int,
    @StringRes val topDescriptionRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val bottomDescriptionRes: Int
)
