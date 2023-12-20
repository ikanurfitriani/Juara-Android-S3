// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.artgallery

// Import library yang akan digunakan
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikanurfitriani.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil fungsi utama yaitu ArtGalleryApp dari aplikasi
                    ArtGalleryApp()
                }
            }
        }
    }
}

// Untuk membuat kelas baru yaitu ArtPiece
data class ArtPiece(
    val resourceId: Int, val name: String, val description: String, val year: String
)

@Composable
// Blok fungsi ArtGalleryApp untuk menampilkan semua bagian halaman
fun ArtGalleryApp() {
    // Deklarasi variabel artPieces yang berisi daftar karya seni.
    val artPieces = listOf(
        // Setiap karya seni memiliki berbagai properti seperti gambar, judul, deskripsi, dan tahun pembuatan
        ArtPiece(
            R.drawable.karya_seni_1,
            "Diponegoro Memimpin Pertempuran",
            "Karya Seni oleh Basuki Abdullah",
            "1960"
        ),
        ArtPiece(
            R.drawable.karya_seni_2,
            "Dr. Ir. Soekarno Presiden Republik Indonesia",
            "Karya Seni oleh Basuki Abdullah",
            "1964"
        ),
        ArtPiece(
            R.drawable.karya_seni_3,
            "Ibuku",
            "Karya Seni oleh Affandi",
            "1941"
        ),
        ArtPiece(
            R.drawable.karya_seni_4,
            "Café Terrace at Night",
            "Karya Seni oleh Vincent van Gogh",
            "1888"
        ),
        ArtPiece(
            R.drawable.karya_seni_5,
            "Le Moulin de Blute",
            "Karya Seni oleh Vincent van Gogh",
            "1886"
        )
    )

    // Untuk melacak indeks karya seni yang sedang ditampilkan
    var currentArtPieceIndex by remember { mutableStateOf(0) }

    // Untuk mengatur elemen dalam column
    Column(
        modifier = Modifier
            // Untuk mengisi seluruh ruang yang tersedia dalam komposisi
            .fillMaxSize()
            // Menambahkan jarak sebesar 16 density-independent pixel dari semua sisi column
            .padding(16.dp),
        // Membuat elemen-elemen berada di tengah secara vertikal
        verticalArrangement = Arrangement.Center,
        // Membuat elemen-elemen berada di tengah secara horizontal
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Untuk mengelompokkan elemen-elemen UI ke dalam sebuah kotak
        Box(
            modifier = Modifier
                // Untuk mengisi maksimum lebar yang tersedia
                .fillMaxWidth()
                // Mengatur latar belakang kotak dengan warna hitam
                .background(Color.Black)
                // Menambahkan jarak sebesar 5 density-independent pixels di sekeliling kotak
                .padding(5.dp)
                // Membuat kotak dengan sudut yang dibulatkan, sehingga tepinya tidak tajam
                .clip(RoundedCornerShape(12.dp)),
            // Untuk mengatur konten di dalam Box agar berada di tengah
            contentAlignment = Alignment.Center
        ) {
            // Untuk mengatur dan menampilkan text
            Text(
                // Menampilkan judul aplikasi yaitu ART GALLERY
                text = "ART GALLERY",
                // Untuk menampilkan teks besar sebagai judul
                style = MaterialTheme.typography.headlineLarge,
                // Ukuran teks sebesar 24 scalable pixels
                fontSize = 24.sp,
                // Membuat teks menjadi tebal
                fontWeight = FontWeight.Bold,
                // Warna teks diatur menjadi putih
                color = Color.White,
                // Menambahkan jarak sebesar 16 density-independent pixels di dalam teks
                modifier = Modifier.padding(16.dp)
            )
        }

        // Menambahkan ruang kosong sebesar 32 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(32.dp))

        // Untuk menampilkan gambar/foto
        Image(
            // Untuk mengambil gambar dari resource drawable
            painter = painterResource(id = artPieces[currentArtPieceIndex].resourceId),
            // Untuk menampilkan deskripsi gambar
            contentDescription = artPieces[currentArtPieceIndex].name,
            // Mengatur skala konten gambaar dengan mengisi gambar ke dalam batas yang tersedia
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                // Menentukan lebar gambar sebesar 300 density-independent pixels
                .width(300.dp)
                // Menentukan tinggi gambar sebesar 400 density-independent pixels
                .height(400.dp)
                // Untuk memotong sudut gambar menjadi bentuk sudut yang dibulatkan dengan jari sebesar 12 dp
                .clip(RoundedCornerShape(12.dp))
                // Memberikan latar belakang gambar dengan warna yang sesuai dengan tema utama aplikasi
                .background(MaterialTheme.colorScheme.primary)
                // Mengatur garis tepi untuk gambar
                .border(
                    // Menentukan lebar border sebesar 2 dp
                    width = 2.dp,
                    // Warna border diatur menjadi abu-abu
                    color = Color.Gray,
                    // Bentuk border mengikuti bentuk yang telah ditentukan dalam tema Material Design
                    shape = MaterialTheme.shapes.medium
                )
        )

        // Menambahkan ruang kosong sebesar 24 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(24.dp))

        // Frame untuk nama, deskripsi dan tahun karya seni
        Box(
            modifier = Modifier
                // Untuk mengisi maksimum lebar yang tersedia
                .fillMaxWidth()
                // Memberikan latar belakang warna hitam dengan tingkat kejernihan sebesar 50% transparan
                .background(Color.Black.copy(alpha = 0.5f))
                // Menambahkan jarak sebesar 16 density-independent pixels di sekeliling box
                .padding(16.dp),
            // Untuk mengatur konten di dalam Box agar berada di tengah
            contentAlignment = Alignment.Center
        ) {
            // Untuk mengatur elemen dalam column
            Column(
                // Membuat elemen-elemen berada di tengah secara horizontal
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Untuk mengatur dan menampilkan text
                Text(
                    // Untuk menampilkan nama karya seni
                    text = artPieces[currentArtPieceIndex].name,
                    // Untuk menampilkan teks medium sebagai nama karya seni
                    style = MaterialTheme.typography.headlineMedium,
                    // Membuat teks menjadi tebal
                    fontWeight = FontWeight.Bold,
                    // Ukuran teks sebesar 24 scalable pixels
                    fontSize = 24.sp,
                    // Warna teks diatur menjadi putih
                    color = Color.White
                )

                // Menambahkan ruang kosong sebesar 8 dp diantara elemen-elemen tata letak
                Spacer(modifier = Modifier.height(8.dp))

                // Untuk mengatur dan menampilkan text
                Text(
                    // Untuk menampilkan deskripsi karya seni
                    text = artPieces[currentArtPieceIndex].description,
                    // Untuk menampilkan teks medium sebagai deskripsi karya seni
                    style = MaterialTheme.typography.bodyMedium,
                    // Ukuran teks sebesar 18 scalable pixels
                    fontSize = 18.sp,
                    // Warna teks diatur menjadi putih
                    color = Color.White
                )

                // Menambahkan ruang kosong sebesar 8 dp diantara elemen-elemen tata letak
                Spacer(modifier = Modifier.height(8.dp))

                // Untuk mengatur dan menampilkan text
                Text(
                    // Untuk menampilkan tahun karya seni
                    text = "Tahun : ${artPieces[currentArtPieceIndex].year}",
                    // Untuk menampilkan teks medium sebagai tahun karya seni
                    style = MaterialTheme.typography.bodyMedium,
                    // Ukuran teks sebesar 18 scalable pixels
                    fontSize = 18.sp,
                    // Warna teks diatur menjadi putih
                    color = Color.White
                )
            }
        }

        // Menambahkan ruang kosong sebesar 16 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(16.dp))

        // Untuk mengatur elemen dalam row
        Row(
            // Untuk mengisi maksimum lebar yang tersedia
            modifier = Modifier.fillMaxWidth(),
            // Mengatur jarak antara dua elemen anak dalam Row sehingga terdapat ruang di antara keduanya
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Untuk membuat tombol interaktif
            Button(
                // Menentukan tindakan yang akan diambil saat tombol diklik
                onClick = {
                    // Untuk melingkari kembali ke halaman terakhir saat di klik di halaman pertama
                    currentArtPieceIndex = if (currentArtPieceIndex > 0) {
                        currentArtPieceIndex - 1
                    } else {
                        artPieces.size - 1
                    }
                }
            ) {
                // Menampilkan teks Sebelumnya di dalam button
                Text(text = "Sebelumnya")
            }

            // Untuk membuat tombol interaktif
            Button(
                // Menentukan tindakan yang akan diambil saat tombol diklik
                onClick = {
                    // Untuk melingkari kembali ke halaman pertama saat di klik di halaman terakhir
                    currentArtPieceIndex = (currentArtPieceIndex + 1) % artPieces.size
                }
            ) {
                // Menampilkan teks Selanjutnya di dalam button
                Text(text = "Selanjutnya")
            }
        }
    }
}

// Menampilkan pratinjau aplikasi art gallery dengan tema terang
@Preview(showBackground = true)
// Menampilkan pratinjau aplikasi art gallery dengan tema gelap
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name = "Dark Mode"
)
// Fungsi composable untuk menampilkan pratinjau halaman fungsi ArtGalleryApp
@Composable
fun ArtGalleryPreview() {
    ArtGalleryTheme {
        ArtGalleryApp()
    }
}