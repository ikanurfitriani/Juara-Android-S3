// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.ikanurfitriani.seblak.ui.theme.SeblakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur bahwa dekorasi tidak menyesuaikan sistem windows
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Mengatur tampilan konten aplikasi dengan tema Seblak
        setContent {
            SeblakTheme {
                SeblakApp()
            }
        }
    }
}