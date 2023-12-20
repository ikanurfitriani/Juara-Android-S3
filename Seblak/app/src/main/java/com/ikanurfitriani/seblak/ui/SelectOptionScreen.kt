// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ikanurfitriani.seblak.R
import com.ikanurfitriani.seblak.ui.components.FormattedPriceLabel

// Anotasi yang menandakan bahwa fungsi SelectOptionScreen adalah komponen Composable
@Composable
fun SelectOptionScreen(
    subtotal: String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
){
    // Untuk menyimpan dan mengembalikan nilai selectedValue selama siklus hidup komponen
    var selectedValue by rememberSaveable { mutableStateOf("") }

    // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Mendefinisikan column dengan menambahkan padding dari dimensi sumber daya yang ada
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))){
            // Penggunaan forEach untuk looping atau iterasi
            options.forEach { item ->
                // Membuat baris untuk setiap elemen dalam daftar options
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    // Membuat tombol radio untuk setiap elemen dalam daftar options
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    // Mengatur dan menampilkan text untuk setiap elemen dalam daftar options
                    Text(item)
                }
            }
            // Menambahkan garis pembatas
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            // Menampilkan label harga yang diformat pada bagian bawah
            FormattedPriceLabel(
                subtotal = subtotal,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            )
        }
        // Row yang berisi dua tombol yaitu Cancel dan Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ){
            // Membuat tombol Cancel
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel))
            }
            // Membuat tombol Next dan akan aktif jika options tidak kosong
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }

}

// Pratinjau dari fungsi SelectOptionScreen
@Preview
@Composable
fun SelectOptionPreview(){
    SelectOptionScreen(
        subtotal = "Rp 15000",
        options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5"),
        modifier = Modifier.fillMaxHeight()
    )
}