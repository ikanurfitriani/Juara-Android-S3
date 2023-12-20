package com.ikanurfitriani.singles.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Singel(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)