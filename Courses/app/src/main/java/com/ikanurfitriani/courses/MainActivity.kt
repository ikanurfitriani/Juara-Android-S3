// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.courses

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.courses.data.DataSource
import com.ikanurfitriani.courses.model.Topic
import com.ikanurfitriani.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil fungsi utama yaitu TopicGrid dari aplikasi
                    TopicGrid(
                        // Untuk mengambil nilai padding dari file dimen yang memberi jarak 8dp
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

// Anotasi composable fungsi TopicGrid
@Composable
// Fungsi untuk menampilkan daftar topik dalam bentuk grid
fun TopicGrid(modifier: Modifier = Modifier) {
    // Untuk membuat grid vertikal
    LazyVerticalGrid(
        // Menentukan jumlah kolom grid
        columns = GridCells.Fixed(2),
        // Menentukan penataan vertikal antara item-item grid agar ada ruang antara setiap baris
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        // Menentukan penataan vertikal antara item-item grid agar ada ruang antara setiap kolom
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        // Untuk menyesuaikan tata letak atau penataan visual dari grid
        modifier = modifier
    ) {
        // Untuk menampilkan item-item dalam grid
        items(DataSource.topics) { topic ->
            // Untuk menampilkan kartu topik yang sesuai dengan topik tersebut
            TopicCard(topic)
        }
    }
}

// Blok fungsi composable TopicCard untuk menampilkan dan mengatur bagian dalam Card
@Composable
// Fungsi untuk menampilkan kartu visual yang merepresentasikan suatu topik
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    // Untuk membuat kartu visual
    Card {
        // Untuk menyusun elemen-elemen secara horizontal
        Row {
            // Untuk mengelompokkan elemen-elemen ke dalam sebuah kotak
            Box {
                // Untuk menampilkan gambar/foto
                Image(
                    // Untuk menampilkan gambar yang diambil dari sumber daya gambar
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        // Modifikator diatur untuk mengatur ukuran gambar menjadi 68x68 dp
                        .size(width = 68.dp, height = 68.dp)
                        // Untuk mempertahankan rasio aspek menjadi 1:1
                        .aspectRatio(1f),
                    // Untuk mengubah ukuran gambar agar sesuai dengan kotak tanpa mengubah rasio aspek
                    contentScale = ContentScale.Crop
                )
            }

            // Untuk menyusun elemen-elemen secara vertikal
            Column {
                // Untuk menampilkan nama topik
                Text(
                    // Mengambil nilai teks dari sumber daya string
                    text = stringResource(id = topic.name),
                    // Memberikan gaya tipografi body medium pada text
                    style = MaterialTheme.typography.bodyMedium,
                    // Untuk memberikan ruang antara teks dan batas kartu
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                // Mengatur elemen-elemen di dalam baris secara vertikal sehingga berada di tengah secara vertikal
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Untuk menampilkan ikon
                    Icon(
                        // Mengambil ikon dari sumber daya gambar
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            // Untuk Untuk memberikan jarak dari sisi kiri dengan nilai dimensi yang diambil dari sumber daya dimensi
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    // Untuk mengatur dan menampilkan teks
                    Text(
                        // Untuk mengambil teks dan diubah menjadi string
                        text = topic.availableCourses.toString(),
                        // Memberikan gaya tipografi label medium pada text
                        style = MaterialTheme.typography.labelMedium,
                        // Untuk Untuk memberikan jarak dari sisi kiri dengan nilai dimensi yang diambil dari sumber daya dimensi
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

// Menampilkan pratinjau dari fungsi CoursesTheme dengan tema terang
@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    CoursesTheme {
        // Membuat variabel topic untuk ditampilkan
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        // Untuk menyusun elemen-elemen secara vertikal
        Column(
            // Untuk membuat Column mengisi seluruh ruang yang tersedia
            modifier = Modifier.fillMaxSize(),
            // Mengatur penataan vertikal elemen-elemen di dalam Column di tengah layar
            verticalArrangement = Arrangement.Center,
            // Mengatur penataan horizontal elemen-elemen di dalam Column di tengah layar
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Untuk membuat dan menampilkan kartu topik berdasarkan objek topik
            TopicCard(topic = topic)
        }
    }
}