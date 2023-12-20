// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.woof

// Import library yang akan digunakan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ikanurfitriani.woof.data.Dog
import com.ikanurfitriani.woof.data.dogs
import com.ikanurfitriani.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            WoofTheme {
                // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Memanggil fungsi utama yaitu WoofApp dari aplikasi
                    WoofApp()
                }
            }
        }
    }
}

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
// Anotasi yang menandakan bahwa fungsi WoofApp adalah komponen Composable
@Composable
fun WoofApp() {
    // Untuk menyusun antarmuka pengguna aplikasi dengan komponen lain
    Scaffold(
        // Komponen TopAppBar yang menampilkan baris atas aplikasi
        topBar = {
            WoofTopAppBar()
        }
    ) { it ->
        // Untuk menampilkan daftar elemen secara vertikal
        LazyColumn(contentPadding = it) {
            // Untuk menampilkan elemen-elemen dari koleksi dogs dalam LazyColumn
            items(dogs) {
                // Untuk menampilkan informasi tentang seekor anjing
                DogItem(
                    // Objek yang mewakili informasi tentang anjing tertentu
                    dog = it,
                    // Untuk padding dengan menggunakan nilai yang diambil dari sumber dimensi (dimension resource) dengan ID R.dimen.padding_small
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

// Anotasi yang menandakan bahwa fungsi DogItem adalah komponen Composable
@Composable
fun DogItem(
    // Menerima parameter bernama dog dengan tipe data Dog
    dog: Dog,
    // Menerima parameter opsional bernama modifier dengan tipe data Modifier
    modifier: Modifier = Modifier
) {
    // State untuk melacak apakah item diperluas atau tidak
    var expanded by remember { mutableStateOf(false) }
    // Animasi untuk mengubah warna latar belakang kartu berdasarkan status expanded
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )

    // Untuk membuat kartu sebagai wadah utama
    Card(
        modifier = modifier
    ) {
        // Kolom sebagai anak dari kartu
        Column(
            modifier = Modifier
                // Menggunakan animasi dengan spesifikasi tertentu, yaitu dengan efek spring (pantulan) yang diatur dengan parameter dampingRatio dan stiffness
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                // Memberikan latar belakang pada Column dengan menggunakan warna yang diberikan oleh variabel color
                .background(color = color)
        ) {
            // Baris yang berisi ikon, informasi anjing, dan tombol
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                // Komposabel DogIcon untuk menampilkan ikon anjing
                DogIcon(dog.imageResourceId)
                // Komposabel DogInformation untuk menampilkan nama dan usia anjing
                DogInformation(dog.name, dog.age)
                // Spacer untuk memberikan bobot dan membuat tombol berada di sebelah kanan
                Spacer(Modifier.weight(1f))
                // Komposabel DogItemButton untuk menampilkan tombol ekspansi
                DogItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            // Jika item diperluas, tampilkan komposabel DogHobby
            if (expanded) {
                DogHobby(
                    dog.hobbies, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

// Anotasi yang menandakan bahwa fungsi DogItemButton adalah komposabel dalam Jetpack Compose
@Composable
// Mendeklarasikan fungsi dengan nama DogItemButton
private fun DogItemButton(
    // Boolean yang menunjukkan apakah elemen yang terkait DogItem sedang diperluas atau tidak
    expanded: Boolean,
    // Lambda yang akan dijalankan ketika tombol diklik
    onClick: () -> Unit,
    // Parameter opsional yang memungkinkan untuk menentukan modifikasi atau properti tambahan pada tombol
    modifier: Modifier = Modifier
) {
    // Untuk menyediakan suatu tombol yang berisi ikon
    IconButton(
        // Untuk menangani kondisi klik
        onClick = onClick,
        modifier = modifier
    ) {
        // Komposabel Icon yang menampilkan ikon pada tombol
        Icon(
            // Menunjukkan ikon "ExpandLess" jika expanded adalah true, dan "ExpandMore" jika false
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            // Menyediakan deskripsi aksesibilitas untuk ikon
            contentDescription = stringResource(R.string.expand_button_content_description),
            // Menentukan warna ikon
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
// Anotasi yang menandakan bahwa fungsi WoofTopAppBar adalah komponen Composable
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    // Untuk penyesuaian dan penempatan yang lebih fleksibel
    CenterAlignedTopAppBar(
        // Menentukan judul (title) untuk CenterAlignedTopAppBar
        title = {
            // Untuk menentukan susunan horizontal dari elemen-elemen anaknya
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Untuk menampilkan logo gambar
                Image(
                    modifier = Modifier
                        // Untuk menentukan ukuran dan padding dari gambar
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),

                    // Content description diatur sebagai null, yang menunjukkan bahwa gambar ini bersifat dekoratif
                    // dan dapat diabaikan oleh layanan aksesibilitas selama navigasi
                    contentDescription = null
                )
                // Untuk mengatur dan menampilkan text
                Text(
                    // Untuk menunjukkan nama aplikasi
                    text = stringResource(R.string.app_name),
                    // Menampilkan teks dengan gaya teks dari tema Material Design
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        // Penyesuaian modifikasi atau properti lainnya yang dapat diterapkan pada WoofTopAppBar
        modifier = modifier
    )
}

// Anotasi yang menandakan bahwa fungsi DogIcon adalah komponen Composable
@Composable
fun DogIcon(
    // Parameter yang menerima ID sumber daya Drawable yang akan digunakan sebagai gambar ikon anjing
    @DrawableRes dogIcon: Int,
    // Parameter opsional yang memungkinkan untuk menentukan modifikasi atau properti lainnya pada DogIcon menggunakan nilai default Modifier
    modifier: Modifier = Modifier
) {
    // Untuk menampilkan gambar ikon anjing
    Image(
        modifier = modifier
            // Untuk mengatur ukuran
            .size(dimensionResource(R.dimen.image_size))
            // Untuk mengatur padding
            .padding(dimensionResource(R.dimen.padding_small))
            // Memberikan efek pemotongan pada gambar dengan menggunakan bentuk kecil
            .clip(MaterialTheme.shapes.small),
        // Menentukan cara gambar akan diukur dan diposisikan dalam kotak yang diberikan
        contentScale = ContentScale.Crop,
        // Menentukan gambar yang akan ditampilkan di dalam Image dengan menggunakan ID sumber daya Drawable
        painter = painterResource(dogIcon),

        // Menetapkan deskripsi konten gambar sebagai null.
        // Ini menandakan bahwa gambar ini bersifat dekoratif dan dapat diabaikan oleh layanan aksesibilitas selama navigasi
        contentDescription = null
    )
}

// Anotasi yang menandakan bahwa fungsi DogInformation adalah komponen Composable
@Composable
fun DogInformation(
    // Parameter yang menerima ID sumber daya string yang mewakili nama anjing
    @StringRes dogName: Int,
    // Parameter yang menerima usia anjing sebagai integer
    dogAge: Int,
    // Parameter opsional yang memungkinkan untuk menentukan modifikasi atau properti lainnya pada DogInformation menggunakan nilai default Modifier
    modifier: Modifier = Modifier
) {
    // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
    Column(modifier = modifier) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Menampilkan nama anjing dengan menggunakan sumber daya string yang diambil dari dogName
            text = stringResource(dogName),
            // Menggunakan gaya teks displayMedium
            style = MaterialTheme.typography.displayMedium,
            // Memberikan padding di bagian atas
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        // Untuk mengatur dan menampilkan text
        Text(
            // Untuk menampilkan usia anjing dengan menggunakan sumber daya string
            text = stringResource(R.string.years_old, dogAge),
            // Menggunakan gaya teks bodyLarge
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// Anotasi yang menandakan bahwa fungsi DogHobby adalah komponen Composable
@Composable
fun DogHobby(
    // Parameter yang menerima ID sumber daya string yang mewakili hobi anjing
    @StringRes dogHobby: Int,
    // Parameter opsional yang memungkinkan untuk menentukan modifikasi atau properti lainnya pada DogHobby menggunakan nilai default Modifier
    modifier: Modifier = Modifier
) {
    // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
    Column(
        modifier = modifier
    ) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Menampilkan teks "About" dengan menggunakan sumber daya string
            text = stringResource(R.string.about),
            // Menggunakan gaya teks labelSmall
            style = MaterialTheme.typography.labelSmall
        )
        // Untuk mengatur dan menampilkan text
        Text(
            // Menampilkan teks yang mengandung informasi tentang hobi anjing dengan menggunakan sumber daya string
            text = stringResource(dogHobby),
            // Menggunakan gaya teks bodyLarge
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// Menampilkan pratinjau aplikasi Woof dengan tema terang
@Preview
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}

// Menampilkan pratinjau aplikasi Woof dengan tema gelap
@Preview
@Composable
fun WoofDarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}