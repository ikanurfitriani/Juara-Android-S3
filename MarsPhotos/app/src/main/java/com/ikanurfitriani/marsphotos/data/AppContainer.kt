// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.marsphotos.data

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.marsphotos.network.MarsApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Container Dependency Injection pada tingkat aplikasi.
 */
interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

/**
 * Implementasi untuk Container Dependency Injection pada tingkat aplikasi.
 *
 * Variabel diinisialisasi secara malas (lazy) dan instance yang sama digunakan di seluruh aplikasi.
 */
class DefaultAppContainer : AppContainer {
    // URL dasar untuk mengakses layanan Mars
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    /**
     * Menggunakan pembuat Retrofit untuk membuat objek Retrofit dengan menggunakan converter kotlinx.serialization
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Objek layanan Retrofit untuk membuat panggilan API
     */
    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    /**
     * Implementasi Dependency Injection (DI) untuk repositori foto Mars
     */
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}