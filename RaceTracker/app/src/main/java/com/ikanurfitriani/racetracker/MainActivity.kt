// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.racetracker

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ikanurfitriani.racetracker.ui.RaceTrackerApp
import com.ikanurfitriani.racetracker.ui.theme.RaceTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aktivitas dengan menggunakan Compose
        setContent {
            // Menerapkan tema RaceTrackerTheme ke seluruh aplikasi
            RaceTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // Menampilkan aplikasi balapan menggunakan fungsi komposabel RaceTrackerApp
                    RaceTrackerApp()
                }
            }
        }
    }
}