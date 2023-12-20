// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ikanurfitriani.seblak.R
import com.ikanurfitriani.seblak.data.OrderUiState
import com.ikanurfitriani.seblak.ui.components.FormattedPriceLabel

// Composable ini mengharapkan [orderUiState] yang mewakili status pesanan, [onCancelButtonClicked]
// lambda yang memicu pembatalan pesanan dan meneruskan pesanan terakhir ke [onSendButtonClicked]
// lambda
@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    // Untuk mengakses sumber daya aplikasi seperti string, gambar, dan jenis sumber daya lainnya
    val resources = LocalContext.current.resources

    // Untuk mendapatkan string terkait dengan jumlah tertentu dari suatu kuantitas
    val numberOfSeblak = resources.getQuantityString(
        R.plurals.seblak,
        orderUiState.quantity,
        orderUiState.quantity
    )
    // Memuat dan memformat string sumber daya dengan parameter
    val orderSummary = stringResource(
        R.string.order_details,
        numberOfSeblak,
        orderUiState.variant,
        orderUiState.date,
        orderUiState.quantity
    )
    // String hasil memuat string sumber daya
    val newOrder = stringResource(R.string.new_seblak_order)
    // Membuat daftar item pesanan untuk ditampilkan
    val items = listOf(
        // Baris 1: Menampilkan jumlah yang dipilih
        Pair(stringResource(R.string.quantity), numberOfSeblak),
        // Baris 2: Menampilkan variant yang dipilih
        Pair(stringResource(R.string.variant), orderUiState.variant),
        // Baris 3: Menampilkan waktu tayang yang dipilih
        Pair(stringResource(R.string.pickup_date), orderUiState.date)
    )

    // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            // Menampilkan label dan nilai untuk setiap item pesanan
            items.forEach { item ->
                Text(item.first.uppercase())
                Text(text = item.second, fontWeight = FontWeight.Bold)
                // Menampilkan garis pembatas antar item
                Divider(thickness = dimensionResource(R.dimen.thickness_divider))
            }
            // Memberikan ruang di atas dan di bawah elemen
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            // Menampilkan harga dengan format yang sudah ditentukan
            FormattedPriceLabel(
                subtotal = orderUiState.price,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            // Komposabel Column yang mengatur elemen-elemen anaknya dalam satu kolom vertikal
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                // Tombol untuk kirim pesanan
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    // Memanggil fungsi onSendButtonClicked
                    onClick = { onSendButtonClicked(newOrder, orderSummary) }
                ) {
                    Text(stringResource(R.string.send))
                }
                // Tombol untuk batalkan pesanan
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCancelButtonClicked
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}

// Pratinjau dari fungsi OrderSummaryScreen
@Preview
@Composable
fun OrderSummaryPreview(){
    OrderSummaryScreen(
        orderUiState = OrderUiState(0, "Test", "Test", "$300.00"),
        onSendButtonClicked = { subject: String, summary: String -> },
        onCancelButtonClicked = {},
        modifier = Modifier.fillMaxHeight()
    )
}