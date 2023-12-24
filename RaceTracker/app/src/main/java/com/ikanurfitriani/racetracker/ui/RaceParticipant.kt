// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.racetracker.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

/**
 * Kelas ini merepresentasikan pemegang status peserta balapan.
 */
class RaceParticipant(
    val name: String,
    val maxProgress: Int = 100,
    val progressDelayMillis: Long = 500L,
    private val progressIncrement: Int = 1,
    private val initialProgress: Int = 0
) {
    // Blok inisialisasi untuk memeriksa persyaratan awal pada propertinya
    init {
        require(maxProgress > 0) { "maxProgress=$maxProgress; must be > 0" }
        require(progressIncrement > 0) { "progressIncrement=$progressIncrement; must be > 0" }
    }

    /**
     * Menunjukkan kemajuan saat ini dari peserta balapan
     */
    var currentProgress by mutableStateOf(initialProgress)
        private set

    /**
     * Memperbarui nilai dari [currentProgress] dengan nilai [progressIncrement] sampai mencapai
     * [maxProgress]. Ada penundaan sebesar [progressDelayMillis] antara setiap pembaruan.
     */
    suspend fun run() {
        while (currentProgress < maxProgress) {
            delay(progressDelayMillis)
            currentProgress += progressIncrement
        }
    }

    /**
     * Terlepas dari nilai [initialProgress], fungsi reset akan mengatur ulang
     * [currentProgress] menjadi 0.
     */
    fun reset() {
        currentProgress = 0
    }
}

/**
 * Indikator kemajuan linear mengharapkan nilai kemajuan dalam kisaran 0-1. Properti ini
 * menghitung faktor kemajuan untuk memenuhi persyaratan indikator.
 */
val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()