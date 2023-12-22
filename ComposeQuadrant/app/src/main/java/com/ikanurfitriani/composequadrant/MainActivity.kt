// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.composequadrant

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aplikasi dengan tema ComposeQuadrant
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Untuk menampilkan fungsi utama yaitu ComposeQuadrantApp
                    ComposeQuadrantApp()
                }
            }
        }
    }
}

// Mendefinisikan fungsi komposabel ComposeQuadrantApp
@Composable
fun ComposeQuadrantApp() {
    // Membuat kolom komposabel yang mengisi lebar maksimum
    Column(Modifier.fillMaxWidth()) {
        // Membuat baris komposabel yang memiliki dua anak elemen yang diberi bobot yang sama (1f) untuk memastikan setiap elemen mengambil setengah dari lebar baris
        Row(Modifier.weight(1f)) {
            // Memanggil fungsi ComposableInfoCard
            ComposableInfoCard(
                // Mengambil judul dari sumber daya string
                title = stringResource(R.string.first_title),
                // Mengambil deskripsi pendek dari sumber daya string
                description = stringResource(R.string.first_description),
                // Mengatur warna latar belakang
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            // Memanggil fungsi ComposableInfoCard
            ComposableInfoCard(
                // Mengambil judul dari sumber daya string
                title = stringResource(R.string.second_title),
                // Mengambil deskripsi pendek dari sumber daya string
                description = stringResource(R.string.second_description),
                // Mengatur warna latar belakang
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        // Membuat baris komposabel yang memiliki dua anak elemen yang diberi bobot yang sama (1f) untuk memastikan setiap elemen mengambil setengah dari lebar baris
        Row(Modifier.weight(1f)) {
            // Memanggil fungsi ComposableInfoCard
            ComposableInfoCard(
                // Mengambil judul dari sumber daya string
                title = stringResource(R.string.third_title),
                // Mengambil deskripsi pendek dari sumber daya string
                description = stringResource(R.string.third_description),
                // Mengatur warna latar belakang
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            // Memanggil fungsi ComposableInfoCard
            ComposableInfoCard(
                // Mengambil judul dari sumber daya string
                title = stringResource(R.string.fourth_title),
                // Mengambil deskripsi pendek dari sumber daya string
                description = stringResource(R.string.fourth_description),
                // Mengatur warna latar belakang
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// Mendefinisikan fungsi komposabel ComposableInfoCard yang menampilkan kartu informasi
@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    // Memulai deklarasi kolom komposabel
    Column(
        modifier = modifier
            // Kolom mengisi ukuran maksimum
            .fillMaxSize()
            // Mengatur latar belakang dengan warna yang diberikan (backgroundColor
            .background(backgroundColor)
            // Memberikan padding sebanyak 16dp
            .padding(16.dp),
        // elemen anak diatur di tengah secara vertikal
        verticalArrangement = Arrangement.Center,
        // elemen anak diatur di tengah secara horizontal
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mengatur dan menampilkan teks
        Text(
            // Mengambil judul dari sumber daya string
            text = title,
            // Mengatur padding 16dp
            modifier = Modifier.padding(bottom = 16.dp),
            // Menebalkan huruf
            fontWeight = FontWeight.Bold,
            // Menambahkan warna hitam
            color = Color.Black
        )
        // Mengatur dan menampilkan teks
        Text(
            // Mengambil deskripsi dari sumber daya string
            text = description,
            // Penataan teks diatur agar rata kanan-kiri
            textAlign = TextAlign.Justify,
            // Menambahkan warna hitam
            color = Color.Black
        )
    }
}

// Menampilkan pratinjau aplikasi dengan tema ComposeQuadrantTheme dan fungsi ComposeQuadrantApp
@Preview(showBackground = true)
@Composable
fun ComposeQuadrantAppPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantApp()
    }
}