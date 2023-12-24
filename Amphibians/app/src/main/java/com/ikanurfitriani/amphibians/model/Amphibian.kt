// Nama package dari model yang dibuat dalam aplikasi
package com.ikanurfitriani.amphibians.model

// Import library, kelas atau file yang dibutuhkan
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class ini mendefinisikan sebuah amphibians yang mencakup name, type, description, and image URL.
 */
@Serializable
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerialName("img_src") val imgSrc: String
)