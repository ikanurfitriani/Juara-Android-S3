// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.happybirthday

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikanurfitriani.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aplikasi dengan tema HappyBirthday
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil fungsi komposabel GreetingImage dengan dua parameter, yaitu pesan dan tanda tangan yang diambil dari sumber daya string
                    GreetingImage(
                        stringResource(R.string.happy_birthday_text),
                        stringResource(R.string.signature_text)
                    )
                }
            }
        }
    }
}

// Mendefinisikan fungsi komposabel GreetingText dengan tiga parameter: pesan, pengirim, dan modifikasi
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    // Membuat kolom untuk menempatkan teks agar tidak tumpang tindih
    Column(
        verticalArrangement = Arrangement.Center,
        // Untuk mengonfigurasi tata letak atau penampilan kolom
        modifier = modifier
    ) {
        Text(
            // Menampilkan teks dengan pesan yang diatur oleh parameter message
            text = message,
            // Mengatur ukuran huruf
            fontSize = 100.sp,
            // Mengatur ukuran ketinggian baris
            lineHeight = 116.sp,
            // Mengatur penataan teks
            textAlign = TextAlign.Center,
            // Untuk memberikan padding ke bagian atas
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            // Menampilkan teks dengan pengirim yang diatur oleh parameter from
            text = from,
            // Mengatur ukuran huruf
            fontSize = 36.sp,
            modifier = Modifier
                // Untuk memberikan padding ke atas
                .padding(top = 16.dp)
                // Untuk memberikan padding ke kanan
                .padding(end = 16.dp)
                // Untuk mengatur penempatan teks ke ujung kanan
                .align(alignment = Alignment.End)

        )
    }
}

// Mendefinisikan fungsi komposabel GreetingImage dengan tiga parameter: pesan, pengirim, dan modifikasi
@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    // Membuat kotak (Box) untuk menumpuk gambar dan teks
    Box(modifier) {
        Image(
            // Menampilkan gambar dengan memanfaatkan sumber daya gambar
            painter = painterResource(id = R.drawable.androidparty),
            contentDescription = null,
            // Mengatur skala konten
            contentScale = ContentScale.Crop,
            // Mengatur transparansi
            alpha = 0.5F
        )
        // Memanggil fungsi GreetingText untuk menampilkan teks di atas gambar
        GreetingText(
            message = message,
            from = from,
            // Untuk mengatur ukuran maksimum dan memberikan padding
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

// Menampilkan pratinjau dari desain kartu ulang tahun menggunakan tema HappyBirthdayTheme dan fungsi GreetingImage
@Preview(showBackground = false)
@Composable
private fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingImage(
            stringResource(R.string.happy_birthday_text),
            stringResource(R.string.signature_text)
        )
    }
}