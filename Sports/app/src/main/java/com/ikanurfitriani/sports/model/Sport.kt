// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.sports.model

// Import library yang akan digunakan
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * data class Sport
 * Kelas Sport merepresentasikan data tentang suatu olahraga.
 */
data class Sport(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val subtitleResourceId: Int,
    val playerCount: Int,
    val olympic: Boolean,
    @DrawableRes val imageResourceId: Int,
    @DrawableRes val sportsImageBanner: Int,
    @StringRes val sportDetails: Int
)