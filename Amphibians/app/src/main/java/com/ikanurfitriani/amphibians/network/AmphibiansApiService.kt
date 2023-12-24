// Nama package dari network yang dibuat dalam aplikasi
package com.ikanurfitriani.amphibians.network

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.amphibians.model.Amphibian
import retrofit2.http.GET

/**
 * Interface publik yang mengekspos metode [getAmphibians]
 */
interface AmphibiansApiService {
    /**
     * Mengembalikan [List] dari [Amphibian] dan metode ini dapat dipanggil dari dalam Coroutine.
     * Anotasi @GET menunjukkan bahwa endpoint "amphibians" akan diminta dengan metode HTTP GET.
     */
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}