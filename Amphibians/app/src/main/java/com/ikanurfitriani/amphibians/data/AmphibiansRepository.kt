// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.amphibians.data

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.amphibians.model.Amphibian
import com.ikanurfitriani.amphibians.network.AmphibiansApiService

/**
 * Repositori mengambil data amfibi dari sumber data yang mendasarinya
 */
interface AmphibiansRepository {
    /** Mengambil daftar amfibi dari sumber data yang mendasarinya */
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * Implementasi Jaringan repositori yang mengambil data amfibi dari sumber data yang mendasarinya
 */
class DefaultAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    /** Mengambil daftar amfibi dari sumber data yang mendasarinya */
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}