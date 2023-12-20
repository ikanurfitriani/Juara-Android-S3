// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.diceroller

// Import library yang akan digunakan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ikanurfitriani.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil fungsi utama yaitu DiceRollerApp dari aplikasi
                    DiceRollerApp()
                }
            }
        }
    }
}

// Menampilkan pratinjau aplikasi dice roller
@Preview
// Anotasi yang menandakan bahwa fungsi DiceRollerApp adalah komponen Composable
@Composable
// Fungsi utama yang akan membangun UI aplikasi "Dice Roller"
fun DiceRollerApp() {
    // Untuk menampilkan dadu dan tombol di tengah tampilan aplikasi
    DiceWithButtonAndImage(modifier = Modifier
        // Untuk mengisi seluruh ruang yang tersedia dalam komposisi
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // Untuk menyimpan hasil dari lemparan dadu
    var result by remember { mutableStateOf( 1) }
    // Untuk menentukan gambar dadu yang akan ditampilkan berdasarkan nilai result
    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // Untuk mengatur elemen-elemen dalam tampilan secara vertikal
    Column(
        // Untuk meneruskan modifikasi yang diterima dari pemanggilan komponen DiceRollerApp
        modifier = modifier,
        // Untuk mengatur agar elemen-elemen dalam kolom berada di tengah secara horizontal
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Untuk menampilkan gambar/foto
        Image(
            // Untuk menampilkan gambar dadu berdasarkan imageResource
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        // Button yang digunakan untuk membuat tombol "Roll"
        Button(
            onClick = { result = (1..6).random() },
        ) {
            // Teks tombol diatur sebagai "Roll" dengan ukuran font 24sp
            Text(text = stringResource(R.string.roll),
                fontSize = 24.sp
            )
        }
    }
}