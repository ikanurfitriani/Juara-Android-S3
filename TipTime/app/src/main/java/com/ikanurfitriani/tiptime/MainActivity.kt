// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.tiptime

// Import library, kelas atau file yang dibutuhkan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // Memanggil fungsi utama yaitu TipTimeLayout dari aplikasi
                    TipTimeLayout()
                }
            }
        }
    }
}

// Anotasi composable fungsi TipTimeLayout
@Composable
// Fungsi yang mendefinisikan tata letak atau layout untuk menghitung tip
fun TipTimeLayout() {
    // Untuk menyimpan dan melacak tiga variabel keadaan
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    // Menghitung jumlah tip berdasarkan input pengguna
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, roundUp)

    // Untuk menempatkan elemen-elemen secara vertikal
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Mengambil teks dari sumber daya string
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        // Untuk memasukkan jumlah tagihan
        EditNumberField(
            label = R.string.bill_amount,
            // Menggunakan ikon uang
            leadingIcon = R.drawable.money,
            // Mengatur properti keyboard sesuai
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = amountInput,
            onValueChanged = { amountInput = it },
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
        )
        // Untuk memasukkan persentase tip
        EditNumberField(
            label = R.string.how_was_the_service,
            // Menggunakan ikon persen
            leadingIcon = R.drawable.percent,
            // Mengatur properti keyboard sesuai
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = tipInput,
            onValueChanged = { tipInput = it },
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
        )
        // Menampilkan baris pilihan pembulatan tip
        RoundTheTipRow(
            // Untuk menentukan apakah pembulatan diaktifkan
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )
        // Menampilkan teks yang menunjukkan jumlah tip yang dihitung
        Text(
            // Mengambil text dari sumber daya string dan menampilkan nilai tip
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        // Menambahkan ruang kosong di bagian bawah layout
        Spacer(modifier = Modifier.height(150.dp))
    }
}

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
// Anotasi yang menandakan bahwa fungsi EditNumberField adalah komponen Composable
@Composable
fun EditNumberField(
    // Untuk menggunakan sumber daya string untuk label yang akan ditampilkan di sebelah field input
    @StringRes label: Int,
    // Untuk menggunakan sumber daya drawable untuk ikon yang akan ditampilkan di sebelah field input
    @DrawableRes leadingIcon: Int,
    // Untuk mengonfigurasi keyboard yang muncul saat pengguna berinteraksi dengan field input
    keyboardOptions: KeyboardOptions,
    // Nilai saat ini dari field input, diikat ke variabel value
    value: String,
    //  Fungsi yang akan dipanggil ketika nilai field input berubah
    onValueChanged: (String) -> Unit,
    // Modifikator untuk menyesuaikan tata letak atau penataan visual dari field input
    modifier: Modifier = Modifier
) {
    // Untuk membuat field input
    TextField(
        // Nilai saat ini dari field input
        value = value,
        // Menentukan apakah field input hanya satu baris
        singleLine = true,
        // Menambahkan ikon di sebelah kiri field input
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
         // Modifikator yang diwariskan dari parameter fungsi
        modifier = modifier,
        // Fungsi yang dipanggil ketika nilai field input berubah
        onValueChange = onValueChanged,
        // Label yang ditentukan oleh sumber daya string
        label = { Text(stringResource(label)) },
        // Konfigurasi keyboard yang diambil dari parameter
        keyboardOptions = keyboardOptions
    )
}

// Anotasi yang menandakan bahwa fungsi RoundTheTipRow adalah komponen Composable
@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // Untuk menyusun elemen-elemen secara horizontal
    Row(
        modifier = modifier
            // Membuat baris mengisi lebar maksimal yang tersedia
            .fillMaxWidth()
            // Menetapkan tinggi baris menjadi 48 density-independent pixels
            .size(48.dp),
        // Menetapkan penataan vertikal sehingga elemen-elemen berada di tengah secara vertikal
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Menampilkan teks yang diambil dari sumber daya string
        Text(text = stringResource(R.string.round_up_tip))
        // Untuk memungkinkan pengguna mengaktifkan atau menonaktifkan pembulatan tip
        Switch(
            modifier = Modifier
                // Untuk mengisi lebar maksimal
                .fillMaxWidth()
                // Untuk meletakkan switch di ujung kanan baris
                .wrapContentWidth(Alignment.End),
            // Menentukan apakah switch dalam keadaan aktif atau tidak, diikat ke variabel roundUp
            checked = roundUp,
            // Fungsi yang dipanggil ketika status switch berubah, yang akan mengubah nilai roundUp
            onCheckedChange = onRoundUpChanged
        )
    }
}

// Untuk mendefinisikan fungsi calculateTip
// amount : Jumlah tagihan yang akan dihitung tip-nya
// tipPercent: Persentase tip yang akan dihitung. Defaultnya adalah 15.0%, tetapi nilai ini dapat diubah oleh pengguna
// roundUp: Menentukan apakah tip akan dibulatkan ke atas atau tidak
private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    // Untuk menghitung jumlah tip berdasarkan persentase tip yang diberikan
    var tip = tipPercent / 100 * amount
    // Untuk mengecek apakah opsi pembulatan (roundUp) diaktifkan
    if (roundUp) {
        // Jika diaktifkan, nilai tip akan dibulatkan ke atas menggunakan fungsi kotlin.math.ceil
        tip = kotlin.math.ceil(tip)
    }
    // Untuk mengembalikan nilai tip yang telah dihitung dalam bentuk format mata uang
    return NumberFormat.getCurrencyInstance().format(tip)
}

// Menampilkan pratinjau aplikasi Tip Time
@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}