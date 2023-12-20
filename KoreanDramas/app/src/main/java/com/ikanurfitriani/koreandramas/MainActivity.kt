// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.koreandramas

// Import library yang akan digunakan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.koreandramas.data.KoreanDramas
import com.ikanurfitriani.koreandramas.model.Drama
import com.ikanurfitriani.koreandramas.ui.theme.KoreanDramasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            KoreanDramasTheme {
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang menjadi abu-abu
                    color = Color.Gray
                ) {
                    // Memanggil fungsi utama yaitu KoreanDramasGrid dari aplikasi
                    KoreanDramasGrid(
                        // Untuk mengambil nilai padding dari file dimen yang memberi jarak 8dp
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

// Blok fungsi composable KoreanDramasGrid untuk menampilkan semua bagian halaman
@Composable
fun KoreanDramasGrid(modifier: Modifier = Modifier) {
    // Deklarasi variabel agar item yang ditampilkan 6
    val itemsPerPage = 6

    // Untuk melacak halaman saat ini yang sedang ditampilkan dalam daftar drama
    var currentPage by remember { mutableStateOf(0) }

    // Untuk menyimpan daftar drama Korea dari SourceData yang akan ditampilkan dalam daftar
    val dramas = KoreanDramas.dramas
    // Untuk menghitung indeks awal dan akhir drama yang akan ditampilkan pada halaman saat ini
    val start = currentPage * itemsPerPage
    val end = (currentPage + 1) * itemsPerPage

    // Untuk mengatur elemen dalam column
    Column(
        modifier = modifier
            // Untuk mengisi seluruh ruang yang tersedia dalam komposisi
            .fillMaxSize()
            // Membuat kotak dengan sudut yang dibulatkan, sehingga tepinya tidak tajam
            .clip(RoundedCornerShape(12.dp))
    ) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Menampilkan judul aplikasi yaitu Daftar Rekomendasi Drama Korea Detektif Terbaik
            text = "Daftar Rekomendasi Drama Korea Detektif Terbaik",
            // Untuk menampilkan teks sedang sebagai judul
            style = MaterialTheme.typography.headlineMedium,
            // Membuat teks menjadi tebal
            fontWeight = FontWeight.Bold,
            // Warna teks diatur menjadi putih
            color = Color.White,
            modifier = Modifier
                // Untuk mengisi maksimum lebar yang tersedia
                .fillMaxWidth()
                // Untuk memberikan latar belakang warna hitam
                .background(Color.Black)
                // Untuk mengambil nilai padding dari file dimen yang memberi jarak 16dp
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
                // Untuk mengambil nilai padding dari file dimen yang memberi jarak 8dp
                .padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                ),
            // Untuk mengatur teks berada di tengah
            textAlign = TextAlign.Center
        )

        // Menggunakan LazyVerticalGrid untuk menampilkan daftar drama
        LazyVerticalGrid(
            columns = GridCells.Fixed(1), // Hanya satu kolom dalam grid
            contentPadding = PaddingValues(
                start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)), // Jarak vertikal antara daftar drama
            modifier = Modifier.weight(1f)
        ) {
            // Mengisi grid dengan daftar drama
            items(dramas.subList(start, end)) { drama ->
                KoreanDramasCard(drama)
            }
        }

        // Tombol "Sebelumnya" dan "Selanjutnya" berada di bawah daftar drama
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_medium),
                    vertical = dimensionResource(R.dimen.padding_small)
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Untuk mengatur dan menampilkan tombol
            Button(
                onClick = {
                    // Jika currentPage > 0 maka akan dikurang satu atau mengarah ke halaman sebelumnya
                    if (currentPage > 0) {
                        currentPage--
                    }
                },
                // Tombol akan dinonaktifkan (disabled) jika currentPage kurang dari atau sama dengan 0
                enabled = currentPage > 0
            ) {
                Text(text = "Sebelumnya")
            }

            // Untuk mengatur dan menampilkan tombol
            Button(
                onClick = {
                    // Jika end < 0 maka akan ditambah satu atau mengarah ke halaman selanjutnya
                    if (end < dramas.size) {
                        currentPage++
                    }
                },
                // Tombol dinonaktifkan jika end sudah mencapai atau melebihi jumlah total drama yang tersedia
                enabled = end < dramas.size
            ) {
                Text(text = "Selanjutnya")
            }
        }
        // Untuk menampilkan informasi pembuat aplikasi
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_medium),
                    vertical = dimensionResource(R.dimen.padding_small)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Untuk menampilkan gambar/foto
            Image(
                painter = painterResource(id = R.drawable.profile_ika_n), // Ganti dengan resource ID foto Anda
                contentDescription = null,
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            // Untuk mengatur dan menampilkan informasi pembuat aplikasi
            Text(
                text = "APK dibuat oleh : Ika Nurfitriani (205410116)",
                // Untuk menampilkan teks kecil
                style = MaterialTheme.typography.bodySmall,
                // Membuat teks menjadi tebal
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

// Blok fungsi composable KoreanDramasCard untuk menampilkan dan mengatur bagian dalam Card
@Composable
fun KoreanDramasCard(drama: Drama, modifier: Modifier = Modifier) {
    // Untuk membuat kartu visual
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        // Untuk mengatur elemen dalam row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Untuk mengelompokkan elemen-elemen UI ke dalam sebuah kotak
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                // Untuk menampilkan gambar/foto
                Image(
                    painter = painterResource(id = drama.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit
                )
            }

            // Menambahkan ruang kosong sebesar 16 dp diantara elemen-elemen tata letak
            Spacer(modifier = Modifier.width(16.dp))

            // Untuk mengatur elemen dalam column
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Untuk mengatur dan menampilkan nama drama
                Text(
                    text = stringResource(id = drama.name),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        bottom = 4.dp
                    )
                )
                // Untuk mengatur dan menampilkan deskripsi drama
                Text(
                    text = stringResource(id = drama.descriptionRes),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(
                        bottom = 4.dp
                    )
                )
                // Untuk mengatur elemen dalam row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Untuk mengatur dan menampilkan Icon bintang
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(48.dp)) // Untuk membuat jarak antara bintang dan teks
                    // Untuk mengatur dan menampilkan tahun rilis drama
                    Text(
                        text = drama.availableCourses.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }
}

// Menampilkan pratinjau aplikasi Korean Dramas dengan tema terang
@Preview(showBackground = true)
@Composable
fun KoreanDramasPreview() {
    KoreanDramasTheme {
        KoreanDramasGrid(
            // Untuk mengambil nilai padding dari file dimen yang memberi jarak 8dp
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    }
}