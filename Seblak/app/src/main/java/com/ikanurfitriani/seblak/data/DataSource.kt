// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak.data

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.seblak.R

// Deklarasi objek dengan nama DataSource untuk menyimnpan data
object DataSource {
    // Daftar yang berisi varian dari seblak
    val variants = listOf(
        R.string.original,
        R.string.ceker,
        R.string.cuangki_lidah,
        R.string.goang,
        R.string.chikuwa,
        R.string.odeng,
        R.string.teokbokki,
        R.string.dengbokki
    )

    // Daftar yang berisi nilai jumlah seblak
    val quantityOptions = listOf(
        Pair(R.string.one_seblak, 1),
        Pair(R.string.three_seblak, 3),
        Pair(R.string.five_seblak, 5),
        Pair(R.string.seven_seblak, 7),
        Pair(R.string.ten_seblak, 10)
    )
}