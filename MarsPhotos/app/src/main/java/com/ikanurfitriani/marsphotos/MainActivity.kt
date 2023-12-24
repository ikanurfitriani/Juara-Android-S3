// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.marsphotos

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.ikanurfitriani.marsphotos.ui.MarsPhotosApp
import com.ikanurfitriani.marsphotos.ui.theme.MarsPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur bahwa dekorasi tidak menyesuaikan sistem windows
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Mengatur tampilan konten aplikasi dengan tema MarsPhotos
        setContent {
            MarsPhotosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Untuk menampilkan fungsi utama yaitu MarsPhotosApp
                    MarsPhotosApp()
                }
            }
        }
    }
}