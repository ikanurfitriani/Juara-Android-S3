// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.koreandramas.model

// Import library yang akan digunakan
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Drama(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int
)