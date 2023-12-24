// Nama package dari screens yang dibuat dalam aplikasi
package com.ikanurfitriani.marsphotos.ui.screens

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ikanurfitriani.marsphotos.R
import com.ikanurfitriani.marsphotos.model.MarsPhoto
import com.ikanurfitriani.marsphotos.ui.theme.MarsPhotosTheme

// Mendefinisikan fungsi komposabel bernama HomeScreen
@Composable
fun HomeScreen(
    marsUiState: MarsUiState, retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    when (marsUiState) {
        // Jika masih loading maka akan menampilkan LoadingScreen dengan modifikasi penuh ukuran (full size)
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        // Jika berhasil maka akan menampilkan ResultScreen dengan modifikasi penuh lebar (full width)
        is MarsUiState.Success -> PhotosGridScreen(
            marsUiState.photos, modifier = modifier.fillMaxWidth()
        )

        // Jika gagal maka akan menampilkan ErrorScreen dengan modifikasi penuh ukuran (full size)
        is MarsUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

// Fungsi composable yang menampilkan LoadingScreen atau layar loading
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        // Mengatur lebar dan tinggi gambar menjadi 200 * 200 density-independent pixels
        modifier = modifier.size(200.dp),
        // Menampilkan gambar loading dengan menggunakan resource drawable loading_img
        painter = painterResource(R.drawable.loading_img),
        // Untuk menampilkan deskripsi dengan menggunakan sumber daya string
        contentDescription = stringResource(R.string.loading)
    )
}

// Fungsi composable yang menampilkan ErrorScreen atau layar gagal memuat data
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    // Menyusun komponen secara vertikal
    Column(
        // Menyesuaikan tata letak kolom, termasuk ukuran dan penataan
        modifier = modifier,
        // Menyusun elemen secara vertikal dan menempatkannya di tengah vertikal kolom
        verticalArrangement = Arrangement.Center,
        // Menyusun elemen secara horizontal dan menempatkannya di tengah horizontal kolom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            // Menampilkan ikon error dari resource drawable
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        // Menampilkan pesan error dengan string resource dari resource string
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        // Menambahkan button retry
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * Layar utama yang menampilkan grid foto.
 */
@Composable
fun PhotosGridScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    // Komponen yang menyusun item secara vertikal dengan jumlah kolom yang dapat disesuaikan
    LazyVerticalGrid(
        // Menentukan bahwa lebar setiap item akan disesuaikan agar ukuran lebar kolom menjadi sekitar 150dp
        columns = GridCells.Adaptive(150.dp),
        // Menerapkan modifikasi pada tata letak grid
        modifier = modifier,
        // Menambahkan padding sekitar grid
        contentPadding = PaddingValues(4.dp)
    ) {
        // Untuk membuat item-grid berdasarkan daftar foto
        items(items = photos, key = { photo -> photo.id }) { photo ->
            // Menerima foto dan menerapkannya ke dalam kartu dengan modifikasi tertentu
            MarsPhotoCard(
                photo,
                modifier = modifier
                    // Memberikan padding 4dp
                    .padding(4.dp)
                    // Memberikan lebar maksimum
                    .fillMaxWidth()
                    // Memberikan aspek rasio 1.5f
                    .aspectRatio(1.5f)
            )
        }
    }
}

// Komposabel yang menampilkan kartu untuk setiap foto Mars, menggunakan Card dan AsyncImage
@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
    // Membuat komponen kartu dari komposisi Material Design
    Card(
        // Menerapkan modifikasi pada kartu
        modifier = modifier,
        // Menentukan bentuk kartu menggunakan bentuk medium dari tema Material
        shape = MaterialTheme.shapes.medium,
        // Memberikan elevasi default pada kartu
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        // Komponen yang memuat gambar secara asinkron
        AsyncImage(
            // Membangun permintaan gambar dengan menggunakan konteks lokal (LocalContext.current) dan URL gambar dari objek foto (photo.imgSrc
            model = ImageRequest.Builder(context = LocalContext.current).data(photo.imgSrc)
                .crossfade(true).build(),
            // Menentukan gambar yang akan ditampilkan jika terjadi kesalahan dalam memuat gambar
            error = painterResource(R.drawable.ic_broken_image),
            // Menentukan gambar placeholder yang akan ditampilkan selama gambar dimuat
            placeholder = painterResource(R.drawable.loading_img),
            // Memberikan deskripsi konten untuk gambar
            contentDescription = stringResource(R.string.mars_photo),
            // Menentukan bagaimana gambar akan diubah ukurannya untuk memenuhi kotak gambar tanpa merusak rasio aspeknya
            contentScale = ContentScale.Crop,
            // Mengisi lebar maksimum pada kartu
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// Fungsi composable yang menampilkan LoadingScreen
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MarsPhotosTheme {
        LoadingScreen()
    }
}

// Fungsi composable yang menampilkan ErrorScreen
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen({})
    }
}

// Fungsi composable yang menampilkan PhotosGridScreen
@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    MarsPhotosTheme {
        val mockData = List(10) { MarsPhoto("$it", "") }
        PhotosGridScreen(mockData)
    }
}