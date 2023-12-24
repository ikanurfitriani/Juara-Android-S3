// Nama package dari screens yang dibuat dalam aplikasi
package com.ikanurfitriani.amphibians.ui.screens

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ikanurfitriani.amphibians.R
import com.ikanurfitriani.amphibians.model.Amphibian
import com.ikanurfitriani.amphibians.ui.theme.AmphibiansTheme

// Mendefinisikan fungsi komposabel bernama HomeScreen
@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (amphibiansUiState) {
        // Jika masih loading maka akan menampilkan LoadingScreen dengan modifikasi 200dp
        is AmphibiansUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        // Jika berhasil maka akan menampilkan AmphibiansListScreen
        is AmphibiansUiState.Success ->
            AmphibiansListScreen(
                amphibians = amphibiansUiState.amphibians,
                modifier = modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    ),
                contentPadding = contentPadding
            )
        // Jika gagal maka akan menampilkan ErrorScreen
        else -> ErrorScreen(retryAction, modifier)
    }
}

// Fungsi composable yang menampilkan LoadingScreen atau layar loading
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        // Menampilkan gambar loading dengan menggunakan resource drawable loading_img
        painter = painterResource(R.drawable.loading_img),
        // Untuk menampilkan deskripsi dengan menggunakan sumber daya string
        contentDescription = stringResource(R.string.loading),
        // Menyesuaikan tata letak kolom, termasuk ukuran dan penataan
        modifier = modifier
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
        // Menampilkan pesan error dengan string resource dari resource string
        Text(stringResource(R.string.loading_failed))
        // Menambahkan button retry
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

// Komposabel yang menampilkan kartu untuk setiap Amphibian, menggunakan Card dan AsyncImage
@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    // Membuat komponen kartu dari komposisi Material Design
    Card(
        // Menerapkan modifikasi pada kartu
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        // Komponen kolom yang mengatur elemen-elemen anak secara vertikal dan memusatkan mereka secara horizonta
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Menampilkan teks dengan judul amfibi yang memanfaatkan sumber daya string dan mengatur penataan teks
            Text(
                text = stringResource(R.string.amphibian_title, amphibian.name, amphibian.type),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            // Menampilkan gambar amfibi secara asinkron menggunakan komponen gambar asinkron
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
            // Menampilkan deskripsi amfibi dengan penataan teks yang diatur
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

// Fungsi komposabel yang menampilkan daftar amfibi dalam bentuk daftar bergulir
@Composable
private fun AmphibiansListScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // Komponen kolom bergulir yang memuat daftar amfibi secara lazim (hanya elemen yang terlihat yang dimuat)
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Menampilkan setiap amfibi dalam daftar menggunakan AmphibianCard
        items(
            // Menentukan daftar amfibi yang akan ditampilkan
            items = amphibians,
            // Memberikan kunci unik untuk setiap elemen dalam daftar
            key = { amphibian ->
                amphibian.name
            }
        ) { amphibian ->
            // Menampilkan AmphibianCard untuk setiap amfibi dalam daftar dengan menggunakan modifikasi ukuran maksimum
            AmphibianCard(amphibian = amphibian, modifier = Modifier.fillMaxSize())
        }
    }
}

// Fungsi composable yang menampilkan LoadingScreen
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp)
        )
    }
}

// Fungsi composable yang menampilkan ErrorScreen
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

// Fungsi composable yang menampilkan AmphibiansListScreen
@Preview(showBackground = true)
@Composable
fun AmphibiansListScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) {
            Amphibian(
                "Lorem Ipsum - $it",
                "$it",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                        " eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad" +
                        " minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                        " ex ea commodo consequat.",
                imgSrc = ""
            )
        }
        AmphibiansListScreen(mockData, Modifier.fillMaxSize())
    }
}