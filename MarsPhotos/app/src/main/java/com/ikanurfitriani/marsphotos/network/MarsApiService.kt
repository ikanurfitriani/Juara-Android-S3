// Nama package dari network yang dibuat dalam aplikasi
package com.ikanurfitriani.marsphotos.network

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.marsphotos.model.MarsPhoto
import retrofit2.http.GET

/**
 * Interface publik yang mengekspos metode [getPhotos]
 */
interface MarsApiService {
    /**
     * Mengembalikan [List] dari [MarsPhoto] dan metode ini dapat dipanggil dari dalam Coroutine.
     * Anotasi @GET menunjukkan bahwa endpoint "photos" akan diminta dengan metode HTTP GET.
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}