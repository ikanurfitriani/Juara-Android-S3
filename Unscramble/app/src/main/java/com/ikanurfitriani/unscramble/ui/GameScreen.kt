// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.unscramble.ui

// Import library, kelas atau file yang dibutuhkan
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikanurfitriani.unscramble.R
import com.ikanurfitriani.unscramble.ui.theme.UnscrambleTheme

// Komponen Compose (@Composable) yang digunakan untuk menampilkan layar permainan
@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
    // Untuk mendeklarasikan variabel gameUiState yang akan mengikuti perubahan state dari gameViewModel.uiState
    val gameUiState by gameViewModel.uiState.collectAsState()
    // Memberi nilai padding medium dari sumber daya dimensi
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    // Untuk menyusun elemen-elemen secara vertikal
    Column(
        // Untuk memberikan efek gulir vertikal dan padding sesuai dengan nilai mediumPaddin
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(mediumPadding),
        // Untuk mengatur penataan vertikal agar elemen-elemen diatur di tengah vertikal
        verticalArrangement = Arrangement.Center,
        // Untuk mengatur penataan horizontal agar elemen-elemen diatur di tengah horizontal
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Untuk menampilkan teks dengan menggunakan sumber daya string
        Text(
            text = stringResource(R.string.app_name),
            // Memberikan gaya tipografi title large pada text
            style = typography.titleLarge,
        )

        // Untuk mewakili tata letak permainan
        GameLayout(
            // Fungsi yang dipanggil ketika tebakan pengguna berubah
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            // Jumlah kata yang akan ditebak
            wordCount = gameUiState.currentWordCount,
            // Tebakan pengguna
            userGuess = gameViewModel.userGuess,
            // Fungsi yang dipanggil ketika pengguna selesai memasukkan tebakan
            onKeyboardDone = { gameViewModel.checkUserGuess() },
            // Kata yang teracak saat ini
            currentScrambledWord = gameUiState.currentScrambledWord,
            // Menunjukkan apakah tebakan pengguna salah
            isGuessWrong = gameUiState.isGuessedWordWrong,
            modifier = Modifier
                // Untuk memberikan lebar maksimum
                .fillMaxWidth()
                .wrapContentHeight()
                // Untuk memberikan padding sesuai dengan nilai mediumPadding
                .padding(mediumPadding)
        )
        // Untuk menambahkan elemen-elemen lain di bawah GameLayout
        Column(
            modifier = Modifier
                // Untuk memberikan lebar maksimum
                .fillMaxWidth()
                // Untuk memberikan padding sesuai dengan nilai mediumPadding
                .padding(mediumPadding),
            // Untuk mengatur penataan vertikal agar elemen-elemen diatur dengan ruang yang merata
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            // Untuk mengatur penataan horizontal agar elemen-elemen diatur di tengah horizontal
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Membuat tombol untuk memungkinkan pengguna mengirimkan tebakan mereka
            Button(
                // Untuk memberikan lebar maksimum
                modifier = Modifier.fillMaxWidth(),
                // Saat tombol diklik maka tebakan pengguna akan diperiksa
                onClick = { gameViewModel.checkUserGuess() }
            ) {
                // Untuk mengatur dan menampilkan text
                Text(
                    // Untuk menampilkan teks "Submit"
                    text = stringResource(R.string.submit),
                    // Untuk mengatur ukuran huruf menjadi 16sp
                    fontSize = 16.sp
                )
            }

            // Membuat tombol untuk memungkinkan pengguna melewati kata saat ini
            OutlinedButton(
                // Saat tombol diklik maka kata dilewati
                onClick = { gameViewModel.skipWord() },
                // Untuk memberikan lebar maksimum
                modifier = Modifier.fillMaxWidth()
            ) {
                // Untuk mengatur dan menampilkan text
                Text(
                    // Untuk menampilkan teks "Skip"
                    text = stringResource(R.string.skip),
                    // Untuk mengatur ukuran huruf menjadi 16sp
                    fontSize = 16.sp
                )
            }
        }

        // Untuk menampilkan skor saat ini dan kemajuan dalam permainan
        GameStatus(score = gameUiState.score, modifier = Modifier.padding(20.dp))

        // Untuk mengecek apakah permainan sudah berakhir
        if (gameUiState.isGameOver) {
            // Untuk menunjukkan skor akhir dan menyediakan opsi untuk bermain lagi
            FinalScoreDialog(
                // Untuk menentukan nilai skor yang akan ditampilkan di dialog
                score = gameUiState.score,
                // Untuk mereset permainan
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}

// Komponen Composable yang digunakan untuk menampilkan status permainan
@Composable
fun GameStatus(score: Int, modifier: Modifier = Modifier) {
    // Untuk memberikan tampilan kartu pada elemen-elemen di dalamnya
    Card(
        modifier = modifier
    ) {
        // Untuk mengatur dan menampilkan text
        Text(
            // Untuk menampilkan teks menggunakan sumber daya string
            text = stringResource(R.string.score, score),
            // Memberikan gaya tipografi headline medium pada text
            style = typography.headlineMedium,
            // Untuk memberikan padding sebesar 8 dp
            modifier = Modifier.padding(8.dp)
        )

    }
}

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameLayout(
    // Kata yang teracak saat ini yang akan ditampilka
    currentScrambledWord: String,
    // Jumlah kata yang akan ditebak
    wordCount: Int,
    // Untuk menunjukkan apakah tebakan pengguna salah atau tidak
    isGuessWrong: Boolean,
    // Tebakan pengguna yang akan dimasukkan
    userGuess: String,
    // Fungsi yang akan dipanggil ketika tebakan pengguna berubah
    onUserGuessChanged: (String) -> Unit,
    // Fungsi yang akan dipanggil ketika pengguna selesai memasukkan tebakan
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Mendeklarasikan nilai padding medium dari sumber daya dimensi
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    // Untuk membuat kartu visual
    Card(
        modifier = modifier,
        // Untuk mengatur elevasi kartu dengan nilai default sebesar 5 dp
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        // Untuk menyusun elemen-elemen secara vertikal
        Column(
            // Untuk mengatur penataan vertikal agar elemen-elemen diatur dengan ruang yang merata
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            // Untuk mengatur penataan horizontal agar elemen-elemen diatur di tengah horizontal
            horizontalAlignment = Alignment.CenterHorizontally,
            // Untuk memberikan padding sesuai dengan nilai mediumPadding
            modifier = Modifier.padding(mediumPadding)
        ) {
            // Untuk mengatur dan menampilkan text
            Text(
                // Untuk memberikan efek visual seperti klip, latar belakang, dan padding
                modifier = Modifier
                    .clip(shapes.medium)
                    .background(colorScheme.surfaceTint)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(alignment = Alignment.End),
                text = stringResource(R.string.word_count, wordCount),
                // Memberikan gaya tipografi title medium pada text
                style = typography.titleMedium,
                // Untuk menetapkan warna teks berdasarkan skema warna
                color = colorScheme.onPrimary
            )
            // Untuk mengatur dan menampilkan text
            Text(
                // Untuk mengatur teks yang akan ditampilkan dengan menggunakan nilai currentScrambledWord
                text = currentScrambledWord,
                // Memberikan gaya tipografi headline medium pada text
                style = typography.displayMedium
            )
            // Untuk mengatur dan menampilkan text
            Text(
                // Untuk mengatur teks yang akan ditampilkan dengan menggunakan sumber daya string
                text = stringResource(R.string.instructions),
                // Untuk mengatur penataan teks agar ditampilkan di tengah
                textAlign = TextAlign.Center,
                // Memberikan gaya tipografi headline medium pada text
                style = typography.titleMedium
            )
            // Field yang memungkinkan pengguna memasukkan tebakan
            OutlinedTextField(
                // Untuk menentukan nilai teks
                value = userGuess,
                // Untuk mengatur agar hanya satu baris teks yang dapat dimasukkan
                singleLine = true,
                // Untuk menetapkan bentuk atau gaya tepi
                shape = shapes.large,
                // Untuk mengatur agar memiliki lebar maksimum
                modifier = Modifier.fillMaxWidth(),
                // Untuk menentukan warna teks dan kursor
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = colorScheme.onSurface,
                    cursorColor = colorScheme.onSurface
                ),
                // Untuk menetapkan fungsi yang akan dipanggil ketika nilai teks berubah
                onValueChange = onUserGuessChanged,
                label = {
                    if (isGuessWrong) {
                        // Label menampilkan pesan tebakan salah
                        Text(stringResource(R.string.wrong_guess))
                    } else {
                        // Label menampilkan pesan masukkan kata
                        Text(stringResource(R.string.enter_your_word))
                    }
                },
                // Untuk mengatur apakah terjadi kesalahan
                isError = isGuessWrong,
                // Untuk mengatur opsi keyboard untuk menentukan aksi yang dilakukan saat tombol "Done" pada keyboard ditekan
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                // Untuk menetapkan aksi yang akan dilakukan ketika tombol "Done" pada keyboard ditekan
                keyboardActions = KeyboardActions(
                    onDone = { onKeyboardDone() }
                )
            )
        }
    }
}

// Membuat dan menampilkan AlertDialog dengan skor akhir
@Composable
private fun FinalScoreDialog(
    // Skor akhir yang akan ditampilkan dalam dialog
    score: Int,
    // Fungsi yang akan dipanggil ketika pengguna memilih untuk bermain lagi
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Untuk mengakhiri aktivitas setelah pengguna memilih untuk keluar
    val activity = (LocalContext.current as Activity)

    // Dialog standar dalam Material Design
    AlertDialog(
        // Fungsi yang akan dipanggil ketika pengguna menghapus dialog dengan mengeklik di luar dialog atau menekan tombol kembali
        onDismissRequest = {
            // Abaikan dialog ketika pengguna mengklik di luar dialog atau di belakang
            // tombol. Jika Anda ingin menonaktifkan fungsi itu, cukup gunakan yang kosong
            // padaCloseRequest.
        },
        // Untuk menetapkan judul dialog dengan menggunakan sumber daya string
        title = { Text(text = stringResource(R.string.congratulations)) },
        // Untuk menetapkan teks dialog dengan menggunakan sumber daya string dan memasukkan nilai skor
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        // Untuk menetapkan tombol untuk menutup dialog
        dismissButton = {
            TextButton(
                // Fungsi yang akan dipanggil ketika tombol ini ditekan
                onClick = {
                    // Fungsi ini mengakhiri aktivitas
                    activity.finish()
                }
            ) {
                // Menampilkan teks dari sumber daya string
                Text(text = stringResource(R.string.exit))
            }
        },
        // Menetapkan tombol konfirmasi untuk memulai permainan lagi
        confirmButton = {
            // Fungsi yang akan dipanggil ketika tombol ini ditekan
            TextButton(onClick = onPlayAgain) {
                // Menampilkan teks dari sumber daya string
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}

// Menampilkan pratinjau dari Unscramble dengan tema terang
@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    UnscrambleTheme {
        GameScreen()
    }
}