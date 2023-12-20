// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.courses.model

// Import library yang akan digunakan
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Membuat data class Topic
data class Topic(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)