// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.lunchtray.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.ikanurfitriani.lunchtray.R
import com.ikanurfitriani.lunchtray.datasource.DataSource
import com.ikanurfitriani.lunchtray.model.MenuItem

// Mendefinisikan fungsi komposabel bernama AccompanimentMenuScreen
@Composable
fun AccompanimentMenuScreen(
    // Mewakili opsi-opsi yang dapat dipilih dalam menu accompaniment
    options: List<MenuItem.AccompanimentItem>,
    // Fungsi tanpa parameter yang akan dipanggil ketika tombol pembatalan pada layar menu accompaniment diklik
    onCancelButtonClicked: () -> Unit,
    // Fungsi tanpa parameter yang akan dipanggil ketika tombol berikutnya pada layar menu accompaniment diklik
    onNextButtonClicked: () -> Unit,
    // Fungsi dengan parameter MenuItem.AccompanimentItem yang akan dipanggil ketika pilihan accompaniment berubah
    onSelectionChanged: (MenuItem.AccompanimentItem) -> Unit,
    // Untuk menyesuaikan tata letak dan penampilan
    modifier: Modifier = Modifier
) {
    // Untuk menampilkan layar menu dasar
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        modifier = modifier
    )
}

// Menampilkan pratinjau dari aplikasi dengan fungsi AccompanimentMenuScreen
@Preview
// Menandakan bahwa fungsi AccompanimentMenuPreview adalah komposabel
@Composable
fun AccompanimentMenuPreview(){
    AccompanimentMenuScreen(
        // Memberikan nilai dari data source DataSource.accompanimentMenuItems kepada parameter options dari AccompanimentMenuScreen
        options = DataSource.accompanimentMenuItems,
        // Memberikan fungsi tanpa aksi
        onNextButtonClicked = {},
        // Memberikan fungsi tanpa aksi
        onCancelButtonClicked = {},
        // Memberikan fungsi tanpa aksi
        onSelectionChanged = {},
        modifier = Modifier
            // Menambahkan padding menggunakan nilai dimensi dari resource dengan ID R.dimen.padding_medium
            .padding(dimensionResource(R.dimen.padding_medium))
            // Mengatur scroll vertical untuk menyimpan dan mengingat posisi scroll.
            .verticalScroll(rememberScrollState())
    )
}