// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.cupcake

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.ikanurfitriani.cupcake.ui.theme.CupcakeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur bahwa dekorasi tidak menyesuaikan sistem windows
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Mengatur tampilan konten aplikasi dengan tema Cupcake
        setContent {
            CupcakeTheme {
                CupcakeApp()
            }
        }
    }
}