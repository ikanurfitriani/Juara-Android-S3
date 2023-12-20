// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak.ui.components

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ikanurfitriani.seblak.R

// Anotasi yang menandakan bahwa fungsi FormattedPriceLabel adalah komponen Composable
@Composable
// Fungsi yang akan menampilkan harga yang diformat di layar
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    // Untuk membuat, mengatur dan menampilkan text
    Text(
        // Mengambil text dari string.xml
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        // Menggunakan gaya teks headlineMedium
        style = MaterialTheme.typography.headlineMedium
    )
}