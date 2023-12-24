// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.lunchtray

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ikanurfitriani.lunchtray.ui.theme.LunchTrayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aplikasi dengan tema LunchTray
        setContent {
            LunchTrayTheme {
                // Untuk menampilkan fungsi utama yaitu LunchTrayApp
                LunchTrayApp()
            }
        }
    }
}