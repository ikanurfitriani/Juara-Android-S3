// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.amphibians.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikanurfitriani.amphibians.R
import com.ikanurfitriani.amphibians.ui.screens.AmphibiansViewModel
import com.ikanurfitriani.amphibians.ui.screens.HomeScreen

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
// Fungsi composable utama dari aplikasi Amphibians
@Composable
fun AmphibiansApp() {
    // Untuk membuat tata letak aplikasi dari Jetpack Compose
    Scaffold(
        // Modifikasi penuh ukuran (full size)
        modifier = Modifier.fillMaxSize(),
        // Menyediakan App Bar di bagian atas
        topBar = {
            // App Bar dengan judul aplikasi
            TopAppBar(
                title = {
                    // Menampilkan judul aplikasi dengan gaya teks headlineMedium
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) {
        // Area konten utama aplikasi
        Surface(
            // Memberikan modifikasi untuk mengisi layar
            modifier = Modifier.fillMaxSize(),
            // Menetapkan warna latar belakang sesuai dengan skema warna Material Design
            color = MaterialTheme.colorScheme.background
        ) {
            // Mendeklarasikan dan mendapatkan instance dari AmphibiansViewModel menggunakan ViewModel Compose
            val amphibiansViewModel: AmphibiansViewModel =
                viewModel(factory = AmphibiansViewModel.Factory)
            // Menampilkan layar utama aplikasi dengan menggunakan state dari AmphibiansViewModel
            HomeScreen(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction = amphibiansViewModel::getAmphibians,
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}