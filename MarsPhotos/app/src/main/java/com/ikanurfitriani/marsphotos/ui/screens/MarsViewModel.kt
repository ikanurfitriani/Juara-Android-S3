// Nama package dari screens yang dibuat dalam aplikasi
package com.ikanurfitriani.marsphotos.ui.screens

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ikanurfitriani.marsphotos.MarsPhotosApplication
import com.ikanurfitriani.marsphotos.data.MarsPhotosRepository
import com.ikanurfitriani.marsphotos.model.MarsPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Antarmuka segel yang merepresentasikan status UI untuk layar utama
 */
sealed interface MarsUiState {
    // Turunan dari MarsUiState yang menyatakan bahwa permintaan berhasil, dan menyertakan daftar foto Mars
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    // Turunan dari MarsUiState yang menyatakan bahwa ada kesalahan dalam permintaan
    object Error : MarsUiState
    // Turunan dari MarsUiState yang menyatakan bahwa permintaan sedang berlangsung
    object Loading : MarsUiState
}

// Kelas ViewModel yang bertanggung jawab untuk mengelola logika dan status UI terkait foto Mars
class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    /** Properti yang menyimpan status UI saat ini dan dapat diubah dengan menggunakan mutableStateOf */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Blok inisialisasi yang secara otomatis memanggil fungsi getMarsPhotos() saat kelas MarsViewModel dibuat
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    // Fungsi yang mengambil informasi foto Mars dari layanan Retrofit Mars API dan memperbarui marsUiState sesuai hasilnya
    fun getMarsPhotos() {
        // Menjalankan kode di dalam blok lambda sebagai tugas coroutines terkait ViewModel
        viewModelScope.launch {
            // Mengatur status menjadi Loading sebelum permintaan dimulai
            marsUiState = MarsUiState.Loading
            // Mencoba melakukan permintaan dan menangkap IOException atau HttpException jika terjadi kesalahan
            marsUiState = try {
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }

    /**
     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
     */
    // Berisi objek Factory yang bertindak sebagai pabrik ViewModel
    companion object {
        // Menggunakan viewModelFactory untuk mengatur dependensi seperti MarsPhotosRepository saat membuat instans dari MarsViewModel
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}