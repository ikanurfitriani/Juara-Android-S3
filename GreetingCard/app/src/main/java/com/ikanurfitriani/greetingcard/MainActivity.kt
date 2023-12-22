// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.greetingcard

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aplikasi dengan tema GreetingCard
        setContent {
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Ika Nurfitriani")
                }
            }
        }
    }
}

// Anotasi yang menunjukkan bahwa fungsi Greeting adalah fungsi komposabel
@Composable
// Mendefinisikan fungsi komposabel bernama Greeting dengan dua parameter
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Membungkus komponen Text dalam komponen Surface
    Surface(color = Color.LightGray) {
        // Mengatur dan menampilkan text
        Text(text = "Hi, my name is $name!", modifier = modifier.padding(24.dp), color = Color.Black)
    }
}

// Menampilkan pratinjau dari aplikasi
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Ika Nurfitriani")
    }
}