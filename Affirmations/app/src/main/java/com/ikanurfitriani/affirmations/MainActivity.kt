// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.affirmations

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.affirmations.data.Datasource
import com.ikanurfitriani.affirmations.model.Affirmation
import com.ikanurfitriani.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil fungsi utama yaitu AffirmationsApp dari aplikasi
                    AffirmationsApp()
                }
            }
        }
    }
}

// Anotasi composable fungsi AffirmationsApp
@Composable
// Untuk menampilkan aplikasi penggunaan afirmasi
fun AffirmationsApp() {
    // Untuk menampilkan daftar afirmasi
    AffirmationList(
        // Untuk mengambil data afirmasi dari Datasource
        affirmationList = Datasource().loadAffirmations(),
    )
}

// Anotasi composable fungsi AffirmationsList
@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    // Untuk membuat scrollable list
    LazyColumn(modifier = modifier) {
        // Untuk membuat item-item dalam daftar
        items(affirmationList) { affirmation ->
            AffirmationCard(
                // Setiap item diwakili oleh objek affirmation
                affirmation = affirmation,
                // Untuk memberikan ruang antara kartu-kartu
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// Anotasi composable fungsi AffirmationsCard
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    // Untuk membuat kartu visual
    Card(modifier = modifier) {
        // Untuk menempatkan elemen-elemen secara vertikal
        Column {
            // Untuk mengatur dan menampilkan gambar/foto
            Image(
                // Mengambil gambar dari sumber daya
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    // Untuk mengatur lebar menjadi maksimal
                    .fillMaxWidth()
                    // Untuk mengatur tinggi menjadi 194dp
                    .height(194.dp),
                // Untuk menyesuaikan gambar dengan proporsinya
                contentScale = ContentScale.Crop
            )
            // Untuk mengatur dan menampilkan text
            Text(
                // Mengambil text dari sumber daya
                text = LocalContext.current.getString(affirmation.stringResourceId),
                // Untuk memberi jarak padding sebesar 16dp
                modifier = Modifier.padding(16.dp),
                // Memberikan gaya tipografi headline small pada text
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

// Menampilkan pratinjau affirmationcard
@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}