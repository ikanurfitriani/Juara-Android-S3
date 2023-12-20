// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.cupcake.data

// Untuk mendeklarasikan data class kotlin bernama OrderUiState dan digunakan untuk menyimpan dan mengelola data
data class OrderUiState(
    // Deklarasi variabel quantity untuk jumlah cupcake yang akan dipesan
    val quantity: Int = 0,
    // Deklarasi variabel variant untuk varian seblak yang akan dipesan
    val flavor: String = "",
    // Deklarasi variabel date untuk tanggal pengambilan pesanan cupcake
    val date: String = "",
    // Deklarasi variabel price untuk total harga pesanan
    val price: String = "",
    // Deklarasi variabel pickupOptions untuk daftar tanggal yang tersedia dalam pengambilan pesanan
    val pickupOptions: List<String> = listOf()
)
