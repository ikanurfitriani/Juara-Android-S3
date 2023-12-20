// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.cupcake.data

// Import library yang akan digunakan
import com.ikanurfitriani.cupcake.R

// Membuat objek Datasource
object DataSource {
    // Untuk daftar rasa yang diambil dari sumber daya string
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    // Untuk daftar jumlah cupcake yang diambil dari sumber daya string
    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )
}