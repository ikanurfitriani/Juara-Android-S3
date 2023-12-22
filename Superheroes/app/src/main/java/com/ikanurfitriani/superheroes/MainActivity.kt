// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.superheroes

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ikanurfitriani.superheroes.model.HeroesRepository
import com.ikanurfitriani.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        super.onCreate(savedInstanceState)
        setContent {
            // Mengatur tampilan konten aplikasi dengan tema Superheroes
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Untuk menampilkan fungsi utama yaitu SuperheroesApp
                    SuperheroesApp()
                }
            }
        }
    }

    // Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
    @OptIn(ExperimentalMaterial3Api::class)
    // Mendefinisikan fungsi komposabel bernama SuperheroesApp
    @Composable
    fun SuperheroesApp() {
        // Untuk membuat tata letak aplikasi dari Jetpack Compose
        Scaffold(
            // Untuk mengisi Scaffold dengan ukuran maksimum yang tersedia dalam konteksnya
            modifier = Modifier.fillMaxSize(),
            topBar = {
                // Membuat AppBar
                TopAppBar()
            }
        ) {
            /* Important: It is not a good practice to access data source directly from the UI.
            In later units you will learn how to use ViewModel in such scenarios that takes the
            data source as a dependency and exposes heroes.
             */
            // Mendeklarasikan variabel heroes dan menginisialisasinya dengan daftar pahlawan dari HeroesRepository
            val heroes = HeroesRepository.heroes
            // Memanggil komposabel HeroesList dengan menyediakan daftar pahlawan dan padding konten dari Scaffold (it)
            HeroesList(heroes = heroes, contentPadding = it)
        }
    }

    // Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
    @OptIn(ExperimentalMaterial3Api::class)
    // Mendefinisikan fungsi komposabel bernama TopAppBar
    @Composable
    // Mendefinisikan fungsi komposabel bernama TopAppBar yang menerima parameter modifier dengan nilai default Modifier
    fun TopAppBar(modifier: Modifier = Modifier) {
        // memposisikan TopAppBar ke tengah layar
        CenterAlignedTopAppBar(
            // Memulai konfigurasi untuk judul TopAppBar
            title = {
                // Mengatur dan menampilkan elemen teks
                Text(
                    // Untuk menampilkan teks dengan menggunakan sumber daya string
                    text = stringResource(R.string.app_name),
                    // Memberikan gaya tipografi display large pada text
                    style = MaterialTheme.typography.displayLarge,
                )
            },
            modifier = modifier
        )
    }

    // Menampilkan pratinjau dari aplikasi superheroes dengan menggunakan tema SuperheroesTheme dan fungsi SuperheroesApp
    @Preview(showBackground = true)
    @Composable
    fun SuperHeroesPreview() {
        SuperheroesTheme {
            SuperheroesApp()
        }
    }
}