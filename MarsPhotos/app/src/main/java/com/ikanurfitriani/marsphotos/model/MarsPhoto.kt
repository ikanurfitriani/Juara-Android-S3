// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.marsphotos.model

// Import library yang akan digunakan
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class ini mendefinisikan sebuah foto Mars yang mencakup ID dan URL gambar.
 */
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)