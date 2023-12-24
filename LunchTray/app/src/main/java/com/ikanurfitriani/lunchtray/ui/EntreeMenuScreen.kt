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
import com.ikanurfitriani.lunchtray.model.MenuItem.EntreeItem

// Mendefinisikan fungsi komposabel bernama EntreeMenuScreen
@Composable
fun EntreeMenuScreen(
    options: List<EntreeItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (EntreeItem) -> Unit,
    modifier: Modifier = Modifier
) {
    // Memanfaatkan BaseMenuScreen dengan melewatkan parameter yang diperlukan
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        // Konversi fungsi onSelectionChanged ke tipe fungsi yang diterima oleh BaseMenuScreen
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        modifier = modifier
    )
}

// Menampilkan pratinjau dari aplikasi
@Preview
@Composable
fun EntreeMenuPreview(){
    // Menampilkan pratinjau EntreeMenuScreen dengan data palsu
    EntreeMenuScreen(
        options = DataSource.entreeMenuItems,
        onCancelButtonClicked = {},
        onNextButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}