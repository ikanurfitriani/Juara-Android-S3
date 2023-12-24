// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.reply

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ikanurfitriani.reply.ui.ReplyApp
import com.ikanurfitriani.reply.ui.theme.ReplyTheme

// Kelas MainActivity adalah turunan dari ComponentActivity yang merupakan aktivitas utama dalam aplikasi Reply
class MainActivity : ComponentActivity() {

    // Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    // Fungsi onCreate() dipanggil saat aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)

        // setContent digunakan untuk menetapkan tampilan konten aktivitas dengan tema Reply
        setContent {
            // Mengatur tampilan konten aplikasi dengan tema Reply
            ReplyTheme {
                Surface {
                    // Menghitung ukuran jendela (WindowSizeClass) menggunakan fungsi calculateWindowSizeClass
                    val windowSize = calculateWindowSizeClass(this)

                    // Memanggil ReplyApp dengan ukuran jendela yang dihitung
                    ReplyApp(
                        windowSize = windowSize.widthSizeClass,
                    )
                }
            }
        }
    }
}

// Anotasi Preview menampilkan tampilan pratinjau dari ReplyApp dengan ukuran jendela Compact
@Preview(showBackground = true)
@Composable
fun ReplyAppCompactPreview() {
    ReplyTheme {
        Surface {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact,
            )
        }
    }
}

// Anotasi Preview menampilkan tampilan pratinjau dari ReplyApp dengan ukuran jendela Medium
@Preview(showBackground = true, widthDp = 700)
@Composable
fun ReplyAppMediumPreview() {
    ReplyTheme {
        Surface {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium,
            )
        }
    }
}

// Anotasi Preview menampilkan tampilan pratinjau dari ReplyApp dengan ukuran jendela Expanded
@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ReplyAppExpandedPreview() {
    ReplyTheme {
        Surface {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded,
            )
        }
    }
}