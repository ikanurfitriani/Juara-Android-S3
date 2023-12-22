// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.taskmanager

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikanurfitriani.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aplikasi dengan tema TaskManager
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Untuk menampilkan fungsi utama yaitu TaskManager
                    TaskManagerScreen()
                }
            }
        }
    }
}

// Mendefinisikan fungsi komposabel TaskManagerScreen
@Composable
fun TaskManagerScreen() {
    // Membuat kolom komposabel
    Column(
        // Mengisi lebar dan tinggi maksimum layar
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        // Penataan vertikal di tengah
        verticalArrangement = Arrangement.Center,
        // Penataan horizontal di tengah
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mendeklarasikan variabel image yang menyimpan Painter dari sumber daya gambar dengan ID ic_task_completed
        val image = painterResource(R.drawable.ic_task_completed)
        // Menampilkan gambar dengan menggunakan Painter yang disimpan dalam variabel image
        // Deskripsi konten diatur sebagai null karena gambar tersebut bersifat dekoratif
        Image(painter = image, contentDescription = null)
        // Mengatur dan menampilkan teks
        Text(
            // Menampilkan teks judul dari sumber daya string
            text = stringResource(R.string.all_task_completed),
            // Mengatur padding di bagian atas dan bawah
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            // Mengatur penebalan huruf
            fontWeight = FontWeight.Bold
        )
        // Mengatur dan menampilkan teks
        Text(
            // Menampilkan teks dari sumber daya string
            text = stringResource(R.string.nice_work),
            // Mengatur ukuran font 16sp
            fontSize = 16.sp
        )
    }
}

// Menampilkan pratinjau layar tugas selesai dengan menggunakan tema TaskManagerTheme dan fungsi TaskManagerScreen
@Preview(showBackground = true)
@Composable
fun TaskCompletedPreview() {
    TaskManagerTheme {
        TaskManagerScreen()
    }
}