// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.dessertclicker

// Import library, kelas atau file yang dibutuhkan
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.ikanurfitriani.dessertclicker.data.Datasource
import com.ikanurfitriani.dessertclicker.model.Dessert
import com.ikanurfitriani.dessertclicker.ui.theme.DessertClickerTheme

// Membuat variabel tag
private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Untuk mencatat pesan log
        Log.d(TAG, "onCreate Called")
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            DessertClickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    // Untuk mengubah warna latar belakang
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Untuk menampilkan daftar kue
                    DessertClickerApp(desserts = Datasource.dessertList)
                }
            }
        }
    }

    // Metode yang dipanggil ketika aktivitas mulai terlihat oleh pengguna
    override fun onStart() {
        // Untuk memastikan bahwa implementasi metode di kelas induk juga dijalankan jika ada
        super.onStart()
        // Pesan log dicatat ke logcat karena metode onStart telah dipanggil
        Log.d(TAG, "onStart Called")
    }

    // Metode yang dipanggil ketika aktivitas menjadi interaktif untuk pengguna
    override fun onResume() {
        // Untuk memastikan bahwa implementasi metode di kelas induk juga dijalankan jika ada
        super.onResume()
        // Pesan log dicatat ke logcat karena metode onResume telah dipanggil
        Log.d(TAG, "onResume Called")
    }

    // Metode yang dipanggil ketika aktivitas dihentikan sejenak sebelum dimulai kembali
    override fun onRestart() {
        // Untuk memastikan bahwa implementasi metode di kelas induk juga dijalankan jika ada
        super.onRestart()
        // Pesan log dicatat ke logcat karena metode onRestart telah dipanggil
        Log.d(TAG, "onRestart Called")
    }

    // Metode yang dipanggil ketika aktivitas kehilangan fokus, tetapi masih terlihat oleh pengguna
    override fun onPause() {
        // Untuk memastikan bahwa implementasi metode di kelas induk juga dijalankan jika ada
        super.onPause()
        // Pesan log dicatat ke logcat karena metode onPause telah dipanggil
        Log.d(TAG, "onPause Called")
    }

    // Metode yang dipanggil ketika aktivitas tidak lagi terlihat oleh pengguna
    override fun onStop() {
        // Untuk memastikan bahwa implementasi metode di kelas induk juga dijalankan jika ada
        super.onStop()
        // Pesan log dicatat ke logcat karena metode onStop telah dipanggil
        Log.d(TAG, "onStop Called")
    }

    // Metode yang dipanggil sebelum aktivitas dihancurkan. Ini terjadi ketika aktivitas dihentikan dan tidak akan dipulihkan
    override fun onDestroy() {
        // Untuk memastikan bahwa implementasi metode di kelas induk juga dijalankan jika ada
        super.onDestroy()
        // Pesan log dicatat ke logcat karena metode onDestroy telah dipanggil
        Log.d(TAG, "onDestroy Called")
    }
}

// Menentukan dessert mana yang akan ditampilkan
fun determineDessertToShow(
    // Daftar dessert
    desserts: List<Dessert>,
    // Jumlah dessert yang telah terjual
    dessertsSold: Int
): Dessert {
    // Memberi nilai awal untuk dessert yang akan ditampilkan
    var dessertToShow = desserts.first()
    // Loop yang mengiterasi melalui setiap elemen dalam daftar dessert
    for (dessert in desserts) {
        // Jika dessertsSold lebih besar atau sama dengan startProductionAmount
        if (dessertsSold >= dessert.startProductionAmount) {
            // Maka dessertToShow akan diubah menjadi dessert tersebut
            dessertToShow = dessert
        // Jika kurang maka loop dihentikan
        } else {
            // Daftar makanan penutup diurutkan berdasarkan startProductionAmount. Saat Anda menjual lebih banyak makanan penutup,
            // Anda akan mulai memproduksi makanan penutup yang lebih mahal sebagaimana ditentukan oleh startProductionAmount
            // Kita akan segera berhenti ketika kita melihat makanan penutup yang "startProductionAmount"-nya lebih besar
            // dari jumlah yang terjual.
            break
        }
    }

    // Dessert yang akan ditampilkan berdasarkan logika penentuan dalam fungsi
    return dessertToShow
}


// Membagikan informasi dessert yang dijual menggunakan intent ACTION_SEND
private fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
    val sendIntent = Intent().apply {
        // Tujuan intent adalah untuk mengirimkan data
        action = Intent.ACTION_SEND
        // Untuk menambahkan teks yang akan dibagikan yang diambil dari sumber daya string
        putExtra(
            Intent.EXTRA_TEXT,
            intentContext.getString(R.string.share_text, dessertsSold, revenue)
        )
        // Menetapkan tipe data yang dibagikan yaitu teks biasa
        type = "text/plain"
    }

    // Membuat intent untuk memilih aplikasi berbagi dari aplikasi yang ada di perangkat
    val shareIntent = Intent.createChooser(sendIntent, null)

    // Mencoba untuk memulai aktivitas berbagi
    try {
        ContextCompat.startActivity(intentContext, shareIntent, null)
    // Jika tidak ditemukan aplikasi berbagi maka akan menampilkan pesan Toast
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            intentContext,
            intentContext.getString(R.string.sharing_not_available),
            Toast.LENGTH_LONG
        ).show()
    }
}

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Fungsi yang menjadi bagian utama dari aplikasi penjualan dessert
private fun DessertClickerApp(
    desserts: List<Dessert>
) {

    // Variabel yang melacak pendapatan dan jumlah dessert yang terjual
    var revenue by rememberSaveable { mutableStateOf(0) }
    var dessertsSold by rememberSaveable { mutableStateOf(0) }

    // Indeks yang melacak dessert saat ini yang ditunjukkan di layar
    val currentDessertIndex by rememberSaveable { mutableStateOf(0) }

    // Variabel yang menyimpan harga dan ID gambar dari dessert saat ini
    var currentDessertPrice by rememberSaveable {
        mutableStateOf(desserts[currentDessertIndex].price)
    }
    var currentDessertImageId by rememberSaveable {
        mutableStateOf(desserts[currentDessertIndex].imageId)
    }

    // Kmponen Compose yang menyediakan struktur dasar aplikasi dengan top app bar, floating action button (FAB), dan lainnya
    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            DessertClickerAppBar(
                // Fungsi yang membagikan informasi terkait dessert yang telah terjual
                onShareButtonClicked = {
                    shareSoldDessertsInformation(
                        intentContext = intentContext,
                        dessertsSold = dessertsSold,
                        revenue = revenue
                    )
                },
                modifier = Modifier
                    // Mengatur lebar maksimum
                    .fillMaxWidth()
                    // Warna latar belakang top bar sesuai dengan skema warna utama aplikasi
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    ) { contentPadding ->
        // Komponen Compose yang menampilkan antarmuka pengguna utama
        DessertClickerScreen(
            revenue = revenue,
            dessertsSold = dessertsSold,
            dessertImageId = currentDessertImageId,

            // Menampilkan pendapatan dan jumlah dessert yang terjual diperbarui dan dessert berikutnya
            onDessertClicked = {

                // Memperbarui pendapatan
                revenue += currentDessertPrice
                dessertsSold++

                // Menampilkan dessert berikutnya
                val dessertToShow = determineDessertToShow(desserts, dessertsSold)
                currentDessertImageId = dessertToShow.imageId
                currentDessertPrice = dessertToShow.price
            },
            // Mengatur padding
            modifier = Modifier.padding(contentPadding)
        )
    }
}

// Komponen Compose (@Composable) yang digunakan untuk membuat AppBar atau bagian atas dari antarmuka pengguna aplikasi
@Composable
private fun DessertClickerAppBar(
    // Parameter fungsi yang akan dipanggil ketika tombol berbagi diklik
    onShareButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Untuk menyusun elemen-elemen secara horizontal
    Row(
        modifier = modifier,
        // Mengatur tata letak horizontal elemen-elem dengan ruang yang merata
        horizontalArrangement = Arrangement.SpaceBetween,
        // Untuk  mengatur penataan vertikal sehingga elemen-elemen berada di tengah vertikal
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Mengambil teks dari sumber daya string
            text = stringResource(R.string.app_name),
            // Untuk memberikan padding di sisi kiri teks, memberikan ruang di antara teks dan batas kiri row
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
            // Untuk menetapkan warna teks sesuai dengan skema warna yang diatur untuk teks di atas latar belakang primer
            color = MaterialTheme.colorScheme.onPrimary,
            // Memberikan gaya tipografi title large pada text
            style = MaterialTheme.typography.titleLarge,
        )
        // Untuk membuat tombol berbagi
        IconButton(
            onClick = onShareButtonClicked,
            // Untuk memberikan padding di sisi kanan tombol berbagi, memberikan ruang di antara tombol dan batas kanan Row
            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
        ) {
            // Menunjukkan ikon berbagi
            Icon(
                imageVector = Icons.Filled.Share,
                // Untuk memberikan deskripsi konten untuk aksesibilitas dan informasi tambahan
                contentDescription = stringResource(R.string.share),
                // Warna ikon ditetapkan sesuai dengan skema warna yang diatur untuk teks di atas latar belakang primer
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun DessertClickerScreen(
    // Total pendapatan yang diperoleh dari penjualan dessert
    revenue: Int,
    // Total dessert yang telah terjual
    dessertsSold: Int,
    // ID gambar untuk dessert yang akan ditampilkan
    @DrawableRes dessertImageId: Int,
    // Fungsi yang akan dipanggil ketika pengguna mengklik dessert
    onDessertClicked: () -> Unit,
    // Untuk memberikan modifikasi tambahan pada komponen ini
    modifier: Modifier = Modifier
) {
    // Untuk menyusun elemen-elemen dalam layout tumpukan
    Box(modifier = modifier) {
        // Untuk menampilkan gambar latar belakang
        Image(
            painter = painterResource(R.drawable.bakery_back),
            contentDescription = null,
            // Untuk mengatur skala kontennya agar sesuai dengan area gambar
            contentScale = ContentScale.Crop
        )
        // Untuk menyusun elemen-elemen secara vertikal
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    // Untuk mengisi lebar maksimum
                    .fillMaxWidth(),
            ) {
                // Untuk menampilkan gambar dessert
                Image(
                    painter = painterResource(dessertImageId),
                    contentDescription = null,
                    modifier = Modifier
                        // Untuk mengatur lebar dan tinggi gambar
                        .width(dimensionResource(R.dimen.image_size))
                        .height(dimensionResource(R.dimen.image_size))
                        // Untuk menetapkan posisi gambar ke tengah
                        .align(Alignment.Center)
                        // Untuk menetapkan bahwa gambar dapat diklik
                        .clickable { onDessertClicked() },
                    contentScale = ContentScale.Crop,
                )
            }
            // Untuk menampilkan informasi transaksi
            TransactionInfo(
                // Menampilkan pendapatan
                revenue = revenue,
                // Menampilkan jumlah dessert yang terjual
                dessertsSold = dessertsSold,
                // Untuk memberikan warna latar belakang
                modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    }
}

@Composable
private fun TransactionInfo(
    // Pendapatan
    revenue: Int,
    // Jumlah dessert yang terjual
    dessertsSold: Int,
    // Untuk mengonfigurasi tata letak dan tampilan elemen UI
    modifier: Modifier = Modifier
) {
    // Untuk menempatkan elemen-elemen UI secara vertikal
    Column(modifier = modifier) {
        // Komposisi lain yang menangani informasi terkait dessert yang terjual
        DessertsSoldInfo(
            dessertsSold = dessertsSold,
            modifier = Modifier
                // Untuk mengatur lebar elemen hingga penuh lebar
                .fillMaxWidth()
                // Untuk memberikan padding sesuai dengan dimensi yang diambil dari sumber daya
                .padding(dimensionResource(R.dimen.padding_medium))
        )
        RevenueInfo(
            revenue = revenue,
            modifier = Modifier
                // Untuk mengatur lebar elemen hingga penuh lebar
                .fillMaxWidth()
                // Untuk memberikan padding sesuai dengan dimensi yang diambil dari sumber daya
                .padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

// Komposisi lain yang menangani informasi pendapatan
@Composable
private fun RevenueInfo(revenue: Int, modifier: Modifier = Modifier) {
    // Untuk menyusun elemen-elemen secara horizontal
    Row(
        // Untuk mengatur tata letak atau penataan tambahan
        modifier = modifier,
        // Untuk mengatur tata letak horizontal di dalam Row sehingga elemen-elemen diatur dengan ruang yang merata
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Mengambil teks dari sumber daya string
            text = stringResource(R.string.total_revenue),
            // Memberikan gaya tipografi headline medium 
            style = MaterialTheme.typography.headlineMedium,
            // Untuk menetapkan warna teks sesuai dengan skema warna yang diatur untuk teks di atas latar belakang sekunder
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        // Untuk mengatur dan menampilkan text
        Text(
            // Untuk menampilkan nilai pendapatan dengan format mata uang
            text = "$${revenue}",
            // Untuk mengatur teks agar sejajar ke kanan dalam Row
            textAlign = TextAlign.Right,
            // Memberikan gaya headline Medium pada text
            style = MaterialTheme.typography.headlineMedium,
            // Untuk menetapkan warna teks sesuai dengan skema warna yang diatur untuk teks di atas latar belakang sekunder
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

// Komposisi lain yang menangani informasi pendapatan
@Composable
private fun DessertsSoldInfo(dessertsSold: Int, modifier: Modifier = Modifier) {
    // Untuk menyusun elemen-elemen secara horizontal
    Row(
        modifier = modifier,
        // Untuk mengatur tata letak horizontal di dalam Row sehingga elemen-elemen diatur dengan ruang yang merata
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Mengambil teks dari sumber daya string
            text = stringResource(R.string.dessert_sold),
            // Untuk emberikan gaya headline Medium large pada text
            style = MaterialTheme.typography.titleLarge,
            // Untuk menetapkan warna teks sesuai dengan skema warna yang diatur untuk teks di atas latar belakang sekunder
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        // Untuk mengatur dan menampilkan text
        Text(
            // Mengambil teks dari sumber daya string
            text = dessertsSold.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

// Menampilkan pratinjau dari Dessert Click
@Preview
@Composable
fun MyDessertClickerAppPreview() {
    DessertClickerTheme {
        DessertClickerApp(listOf(Dessert(R.drawable.cupcake, 5, 0)))
    }
}