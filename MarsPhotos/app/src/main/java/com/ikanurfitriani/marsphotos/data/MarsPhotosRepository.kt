// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.marsphotos.data

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.marsphotos.model.MarsPhoto
import com.ikanurfitriani.marsphotos.network.MarsApiService

/**
 * Repositori yang mengambil daftar foto Mars dari MarsApi.
 */
interface MarsPhotosRepository {
    /** Mengambil daftar MarsPhoto dari marsApi */
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

/**
 * Implementasi Jaringan dari Repositori yang mengambil daftar foto Mars dari MarsApi.
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    /** Mengambil daftar MarsPhoto dari marsApi */
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}