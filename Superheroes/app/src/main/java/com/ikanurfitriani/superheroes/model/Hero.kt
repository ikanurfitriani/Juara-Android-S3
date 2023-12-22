// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.superheroes.model

// Import library yang akan digunakan
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Untuk mendeklarasikan data class kotlin bernama Hero dan digunakan untuk menyimpan dan mengelola data
data class Hero(
    // Deklarasi variabel nameRes untuk nama hero
    @StringRes val nameRes: Int,
    // Deklarasi variabel descriptionRes untuk deskripsi hero
    @StringRes val descriptionRes: Int,
    // Deklarasi variabel imageRes untuk gambar hero
    @DrawableRes val imageRes: Int
)