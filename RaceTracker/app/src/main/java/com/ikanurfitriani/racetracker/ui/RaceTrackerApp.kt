// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.racetracker.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ikanurfitriani.racetracker.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

// Mendefinisikan fungsi komposabel bernama RaceTrackerApp
@Composable
// Composable utama yang mengatur logika perlombaan dan menampilkan RaceTrackerScreen
fun RaceTrackerApp() {
    /**
     * Catatan: Untuk bertahan dari perubahan konfigurasi seperti rotasi layar, [rememberSaveable] seharusnya
     * digunakan dengan objek Saver kustom. Tetapi untuk menjaga contoh ini sederhana, dan fokus pada
     * Coroutines, detail implementasi tersebut dihapus.
     */
    val playerOne = remember {
        RaceParticipant(name = "Player 1", progressIncrement = 1)
    }
    val playerTwo = remember {
        RaceParticipant(name = "Player 2", progressIncrement = 2)
    }
    var raceInProgress by remember { mutableStateOf(false) }

    if (raceInProgress) {
        LaunchedEffect(playerOne, playerTwo) {
            coroutineScope {
                launch { playerOne.run() }
                launch { playerTwo.run() }
            }
            raceInProgress = false
        }
    }
    RaceTrackerScreen(
        playerOne = playerOne,
        playerTwo = playerTwo,
        isRunning = raceInProgress,
        onRunStateChange = { raceInProgress = it }
    )
}

// Mendefinisikan fungsi komposabel bernama RaceTrackerScreen
@Composable
// Composable yang menampilkan status peserta balapan, indikator kemajuan, dan kontrol perlombaan
private fun RaceTrackerScreen(
    playerOne: RaceParticipant,
    playerTwo: RaceParticipant,
    isRunning: Boolean,
    onRunStateChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // Membuat kolom utama yang mengisi layar penuh dan memberikan padding
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menampilkan teks untuk label utama
        Text(
            text = stringResource(R.string.run_a_race),
            style = MaterialTheme.typography.headlineLarge,
        )
        // Membuat kolom untuk mengatur tata letak elemen di tengah layar
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Menampilkan ikon berjalan dengan memberikan padding di bagian bawah
            Icon(
                painter = painterResource(R.drawable.ic_walk),
                contentDescription = null,

                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)),
            )
            // Menampilkan indikator status untuk Player One
            StatusIndicator(
                participantName = playerOne.name,
                currentProgress = playerOne.currentProgress,
                maxProgress = stringResource(
                    R.string.progress_percentage,
                    playerOne.maxProgress
                ),
                progressFactor = playerOne.progressFactor
            )
            // Memberikan ruang kosong vertikal dengan ukuran tertentu
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))
            // Menampilkan indikator status untuk Player Two
            StatusIndicator(
                participantName = playerTwo.name,
                currentProgress = playerTwo.currentProgress,
                maxProgress = stringResource(
                    R.string.progress_percentage,
                    playerTwo.maxProgress
                ),
                progressFactor = playerTwo.progressFactor
            )
            // Memberikan ruang kosong vertikal dengan ukuran tertentu
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))
            // Menampilkan kontrol untuk menjalankan, menghentikan, dan mereset perlombaan
            RaceControls(
                isRunning = isRunning,
                onRunStateChange = onRunStateChange,
                onReset = {
                    playerOne.reset()
                    playerTwo.reset()
                    onRunStateChange(false)
                }
            )
        }
    }
}

// Mendefinisikan fungsi komposabel bernama StatusIndicator
@Composable
// Composable yang menampilkan indikator kemajuan untuk setiap peserta balapan
private fun StatusIndicator(
    participantName: String,
    currentProgress: Int,
    maxProgress: String,
    progressFactor: Float,
    modifier: Modifier = Modifier
) {
    // Membuat baris untuk menampilkan elemen secara horizontal
    Row {
        // Menampilkan teks nama peserta dengan memberikan padding di bagian akhir
        Text(participantName, Modifier.padding(end = dimensionResource(R.dimen.padding_small)))

        // Membuat kolom untuk mengatur tata letak elemen secara vertikal
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            // Menampilkan indikator kemajuan linear dengan proporsi kemajuan sesuai dengan faktor kemajuan
            LinearProgressIndicator(
                progress = progressFactor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.progress_indicator_height))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.progress_indicator_corner_radius)))
            )
            // Menampilkan dua teks dengan properti tertentu yang menunjukkan kemajuan
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Menampilkan teks persentase kemajuan
                Text(
                    text = stringResource(R.string.progress_percentage, currentProgress),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                // Menampilkan teks nilai maksimum kemajuan
                Text(
                    text = maxProgress,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// Mendefinisikan fungsi komposabel bernama RaceControls
@Composable
// Composable yang menampilkan kontrol untuk memulai, menghentikan, dan mereset perlombaan
private fun RaceControls(
    onRunStateChange: (Boolean) -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
    isRunning: Boolean = true,
) {
    // Baris digunakan untuk mengatur tata letak elemen secara horizontal dengan jarak yang merata
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Tombol untuk mengubah status berjalan atau tidaknya perlombaan
        Button(onClick = { onRunStateChange(!isRunning) }) {
            // Teks tombol berubah tergantung pada status berjalan atau tidaknya perlombaan
            Text(if (isRunning) stringResource(R.string.pause) else stringResource(R.string.start))
        }

        // Tombol untuk mereset perlombaan
        Button(onClick = onReset) {
            Text(stringResource(R.string.reset))
        }
    }
}

// Menampilkan pratinjau dari aplikasi RaceTracker dengan menggunakan tema MaterialTheme dan fungsi RaceTrackerApp
@Preview
@Composable
fun RaceTrackerAppPreview() {
    MaterialTheme {
        RaceTrackerApp()
    }
}