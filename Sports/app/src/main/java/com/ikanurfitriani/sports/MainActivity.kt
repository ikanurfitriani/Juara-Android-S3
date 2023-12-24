// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.sports

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.ikanurfitriani.sports.ui.SportsApp
import com.ikanurfitriani.sports.ui.theme.SportsTheme

/**
 * Activity untuk aplikasi Sports
 */
// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas di mana fungsi composable digunakan sebagai konten
        super.onCreate(savedInstanceState)

        // Mengatur tampilan konten aplikasi dengan menggunakan tema Sports
        setContent {
            SportsTheme {
                // Menggunakan komposisi permukaan sebagai konten utama
                Surface {
                    // Menghitung kelas ukuran jendela untuk menentukan tata letak yang sesuai
                    val windowSize = calculateWindowSizeClass(this)
                    // Menjalankan komposisi utama aplikasi Sports dengan mengirimkan kelas ukuran jendela
                    SportsApp(
                        windowSize = windowSize.widthSizeClass,
                        onBackPressed = { finish() }
                    )
                }
            }
        }
    }
}