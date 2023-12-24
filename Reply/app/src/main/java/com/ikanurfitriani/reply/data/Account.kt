// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.reply.data

// Import library, kelas atau file yang dibutuhkan
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Kelas yang merepresentasikan sebuah akun.
 */
data class Account(
    /** ID unik pengguna **/
    val id: Long,
    /** Nama depan pengguna **/
    @StringRes val firstName: Int,
    /** Nama belakang pengguna **/
    @StringRes val lastName: Int,
    /** Alamat email pengguna **/
    @StringRes val email: Int,
    /** ID sumber daya avatar gambar pengguna **/
    @DrawableRes val avatar: Int
)