// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.seblak.R
import com.ikanurfitriani.seblak.data.DataSource

// Composable dapat disusun yang memungkinkan pengguna memilih jumlah cupcake yang diinginkan dan diharapkan
// Lambda [onNextButtonClicked] yang mengharapkan kuantitas yang dipilih dan memicu navigasi ke sana
// layar selanjutnya
@Composable
fun StartOrderScreen(
    quantityOptions: List<Pair<Int, Int>>,
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
    Column(
        // Menyesuaikan tata letak dan penataan visual
        modifier = modifier,
        // Terdapat ruang yang merata antara elemen-elemen di dalam column
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
        Column(
            // Mengisi lebar maksimum
            modifier = Modifier.fillMaxWidth(),
            // Konten posisi horizontal diatur ke tengah
            horizontalAlignment = Alignment.CenterHorizontally,
            // Diberi ruang antara elemen-elemen dalam kolom
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            // Memberikan ruang di atas dan di bawah elemen
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            // Menampilkan gambar seblak dengan lebar 280 dp
            Image(
                painter = painterResource(R.drawable.seblak),
                contentDescription = null,
                modifier = Modifier.width(280.dp)
            )
            // Memberikan ruang di atas dan di bawah elemen
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            // Menampilkan judul "Order Seblak" dengan gaya tipografi headlineSmall
            Text(
                text = stringResource(R.string.order_seblak),
                style = MaterialTheme.typography.headlineSmall
            )
            // Memberikan ruang setelah teks
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        }
        // Baris yang didalamnya terdapat pilihan jumlah seblak
        Row(modifier = Modifier.weight(1f, false)) {
            // Mengisi lebar maksimum dan berposisi di tengah
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                // Mengatur ruang antar elemen dalam kolom
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            ) {
                // Membuat tombol kuantitas menggunakan komponen SelectQuantityButton
                quantityOptions.forEach { item ->
                    SelectQuantityButton(
                        labelResourceId = item.first,
                        onClick = { onNextButtonClicked(item.second) }
                    )
                }
            }
        }
    }
}

// Tombol yang dapat disesuaikan yang menampilkan [labelResourceId]
// dan memicu lambda [onClick] saat composable ini diklik
@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    // Tombol yang dapat disesuaikan yang menampilkan teks dari sumber daya string
    Button(
        // Lambda yang akan dijalankan ketika tombol diklik
        onClick = onClick,
        // Lebar minimum sebesar 250 dp
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(stringResource(labelResourceId))
    }
}

// Pratinjau dari fungsi StartOrderScreen
@Preview
@Composable
fun StartOrderPreview(){
    StartOrderScreen(
        quantityOptions = DataSource.quantityOptions,
        onNextButtonClicked = {},
        modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
    )
}