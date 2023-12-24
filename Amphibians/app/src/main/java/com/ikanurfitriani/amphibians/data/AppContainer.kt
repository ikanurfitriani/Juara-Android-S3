// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.amphibians.data

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Container Dependency Injection pada tingkat aplikasi.
 */
interface AppContainer {
    val amphibiansRepository: AmphibiansRepository
}

/**
 * Implementasi untuk Container Dependency Injection pada tingkat aplikasi.
 *
 * Variabel diinisialisasi secara malas (lazy) dan instance yang sama digunakan di seluruh aplikasi.
 */
class DefaultAppContainer : AppContainer {
    private val baseURL = "https://android-kotlin-fun-mars-server.appspot.com/"

    /**
     * Menggunakan pembuat Retrofit untuk membuat objek Retrofit dengan menggunakan converter kotlinx.serialization
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .build()

    /**
     * Objek layanan Retrofit untuk membuat panggilan API
     */
    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    /**
     * Implementasi Dependency Injection (DI) untuk repositori amphibians
     */
    override val amphibiansRepository: AmphibiansRepository by lazy {
        DefaultAmphibiansRepository(retrofitService)
    }
}