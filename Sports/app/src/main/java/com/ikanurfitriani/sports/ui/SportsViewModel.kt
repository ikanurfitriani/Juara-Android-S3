// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.sports.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.lifecycle.ViewModel
import com.ikanurfitriani.sports.data.LocalSportsDataProvider
import com.ikanurfitriani.sports.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * View Model untuk aplikasi olahraga
 */
class SportsViewModel : ViewModel() {

    // _uiState adalah MutableStateFlow yang menyimpan status UI aplikasi olahraga
    private val _uiState = MutableStateFlow(
        SportsUiState(
            sportsList = LocalSportsDataProvider.getSportsData(),
            currentSport = LocalSportsDataProvider.getSportsData().getOrElse(0) {
                LocalSportsDataProvider.defaultSport
            }
        )
    )
    // uiState adalah StateFlow yang dapat diakses dari luar untuk observasi perubahan status UI
    val uiState: StateFlow<SportsUiState> = _uiState

    // Fungsi untuk memperbarui olahraga yang sedang dipilih pada status UI
    fun updateCurrentSport(selectedSport: Sport) {
        _uiState.update {
            it.copy(currentSport = selectedSport)
        }
    }

    // Fungsi untuk navigasi ke halaman daftar olahraga
    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }


    // Fungsi untuk navigasi ke halaman detail olahraga
    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

// Data class yang merepresentasikan status UI aplikasi olahraga
data class SportsUiState(
    val sportsList: List<Sport> = emptyList(),
    val currentSport: Sport = LocalSportsDataProvider.defaultSport,
    val isShowingListPage: Boolean = true
)