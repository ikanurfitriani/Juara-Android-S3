// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.superheroes.model

// Import library yang akan digunakan
import com.ikanurfitriani.superheroes.R

// Membuat objek HeroesRepository
object HeroesRepository {
    // Untuk daftar pahlawan yang diambil dari sumber daya string
    val heroes = listOf(
        Hero(
            // Untuk menampilkan nama hero dengan menggunakan sumber daya string
            nameRes = R.string.hero1,
            // Untuk menampilkan deskripsi hero dengan menggunakan sumber daya string
            descriptionRes = R.string.description1,
            // Untuk menampilkan gambar hero dengan menggunakan sumber daya string
            imageRes = R.drawable.android_superhero1
        ),
        Hero(
            // Untuk menampilkan nama hero dengan menggunakan sumber daya string
            nameRes = R.string.hero2,
            // Untuk menampilkan deskripsi hero dengan menggunakan sumber daya string
            descriptionRes = R.string.description2,
            // Untuk menampilkan gambar hero dengan menggunakan sumber daya string
            imageRes = R.drawable.android_superhero2
        ),
        Hero(
            // Untuk menampilkan nama hero dengan menggunakan sumber daya string
            nameRes = R.string.hero3,
            // Untuk menampilkan deskripsi hero dengan menggunakan sumber daya string
            descriptionRes = R.string.description3,
            // Untuk menampilkan gambar hero dengan menggunakan sumber daya string
            imageRes = R.drawable.android_superhero3
        ),
        Hero(
            // Untuk menampilkan nama hero dengan menggunakan sumber daya string
            nameRes = R.string.hero4,
            // Untuk menampilkan deskripsi hero dengan menggunakan sumber daya string
            descriptionRes = R.string.description4,
            // Untuk menampilkan gambar hero dengan menggunakan sumber daya string
            imageRes = R.drawable.android_superhero4
        ),
        Hero(
            // Untuk menampilkan nama hero dengan menggunakan sumber daya string
            nameRes = R.string.hero5,
            // Untuk menampilkan deskripsi hero dengan menggunakan sumber daya string
            descriptionRes = R.string.description5,
            // Untuk menampilkan gambar hero dengan menggunakan sumber daya string
            imageRes = R.drawable.android_superhero5
        ),
        Hero(
            // Untuk menampilkan nama hero dengan menggunakan sumber daya string
            nameRes = R.string.hero6,
            // Untuk menampilkan deskripsi hero dengan menggunakan sumber daya string
            descriptionRes = R.string.description6,
            // Untuk menampilkan gambar hero dengan menggunakan sumber daya string
            imageRes = R.drawable.android_superhero6
        )
    )
}