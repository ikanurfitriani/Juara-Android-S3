// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.affirmations.model

// Import library yang akan digunakan
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Membuat data class Affirmation
data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
