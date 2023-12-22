// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.composearticle

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikanurfitriani.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aplikasi dengan tema ComposeArticle
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Untuk menampilkan fungsi utama yaitu ComposeArticle
                    ComposeArticleApp()
                }
            }
        }
    }
}

// Mendefinisikan fungsi komposabel ComposeArticleApp yang menampilkan artikel menggunakan ArticleCard
@Composable
fun ComposeArticleApp() {
    // Memanggil fungsi ArticleCard
    ArticleCard(
        // Mengambil judul dari sumber daya string
        title = stringResource(R.string.title_jetpack_compose_tutorial),
        // Mengambil deskripsi pendek dari sumber daya string
        shortDescription = stringResource(R.string.compose_short_desc),
        // Mengambil deskripsi panjang dari sumber daya string
        longDescription = stringResource(R.string.compose_long_desc),
        // Mengambil gambar dari sumber daya drawable
        imagePainter = painterResource(R.drawable.bg_compose_background)
    )
}

// Mendefinisikan fungsi komposabel ArticleCard yang menampilkan kartu artikel
@Composable
private fun ArticleCard(
    title: String,
    shortDescription: String,
    longDescription: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    // Membuat kolom komposabel untuk menempatkan elemen-elemen dalam tata letak vertikal
    Column(modifier = modifier) {
        // Menampilkan gambar dengan menggunakan Painter dari sumber daya gambar
        // Content description diatur sebagai null karena gambar tersebut bersifat dekoratif
        Image(painter = imagePainter, contentDescription = null)
        // Mengatur dan menampilkan teks
        Text(
            // Menampilkan teks judul artikel
            text = title,
            // Mengatur padding 16dp
            modifier = Modifier.padding(16.dp),
            // Mengatur ukuran huruf 24sp
            fontSize = 24.sp
        )
        // Mengatur dan menampilkan teks
        Text(
            // Menampilkan teks deskripsi pendek
            text = shortDescription,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            // Penataan teks diatur agar rata kanan-kiri
            textAlign = TextAlign.Justify
        )
        // Mengatur dan menampilkan teks
        Text(
            // Menampilkan teks deskripsi panjang
            text = longDescription,
            // Mengatur padding 16dp
            modifier = Modifier.padding(16.dp),
            // Penataan teks diatur agar rata kanan-kiri
            textAlign = TextAlign.Justify
        )
    }
}

// Menampilkan pratinjau dari aplikasi artikel dengan menggunakan tema ComposeArticleTheme dan fungsi ComposeArticleApp
@Preview(showBackground = true)
@Composable
fun ComposeArticleAppPreview() {
    ComposeArticleTheme {
        ComposeArticleApp()
    }
}