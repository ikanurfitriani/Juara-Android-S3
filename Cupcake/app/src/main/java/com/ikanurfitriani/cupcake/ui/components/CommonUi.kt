// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.cupcake.ui.components

// Import library yang dibutuhkan
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ikanurfitriani.cupcake.R

// Anotasi yang menandakan bahwa fungsi FormattedPriceLabel adalah komponen Composable
@Composable
// Fungsi yang akan menampilkan harga yang diformat di layar
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    // Untuk membuat, mengatur dan menampilkan text
    Text(
        // Untuk menampilkan teks dari sumber daya string dan nilai subtotal
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        // Memberi gaya tipografi headlineSmall pada teks
        style = MaterialTheme.typography.headlineSmall
    )
}