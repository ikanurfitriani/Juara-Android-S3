// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.lunchtray.model

// Import library yang akan digunakan
import java.text.NumberFormat

// Mendeklarasikan sebuah kelas segel MenuItem
sealed class MenuItem(
    // Mendeklarasikan nama sbg properti terbuka
    open val name: String,
    // Mendeklarasikan deskripsi sbg properti terbuka
    open val description: String,
    // Mendeklarasikan harga sbg properti terbuka
    open val price: Double
) {
    /**
     * Getter method for price.
     * Includes formatting.
     */
    // Mendeklarasikan sebuah subkelas data bernama EntreeItem
    data class EntreeItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    // Mendeklarasikan sebuah subkelas data bernama SideDishItem
    data class SideDishItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    // Mendeklarasikan sebuah subkelas data bernama AccompanimentItem
    data class AccompanimentItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    /**
     * Getter method for price.
     * Includes formatting.
     */
    // Mendeklarasikan sebuah fungsi yang mengembalikan harga item dalam format mata uang
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}