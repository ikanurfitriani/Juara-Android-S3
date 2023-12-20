// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.unscramble.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ikanurfitriani.unscramble.data.MAX_NO_OF_WORDS
import com.ikanurfitriani.unscramble.data.SCORE_INCREASE
import com.ikanurfitriani.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    // Kumpulan kata-kata yang digunakan dalam game
    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    init {
        resetGame()
    }

    // Inisialisasi ulang data game untuk memulai ulang game.
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    // Perbarui tebakan pengguna
    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    // Memeriksa apakah tebakan pengguna benar.
    // Meningkatkan skor yang sesuai.
    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            // Tebakan pengguna benar, tambah skornya
            // dan panggil updateGameState() untuk mempersiapkan game untuk putaran berikutnya
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // Tebakan pengguna salah, tampilkan kesalahan
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        // Setel ulang tebakan pengguna
        updateUserGuess("")
    }

    // Lewati ke kata berikutnya
    fun skipWord() {
        updateGameState(_uiState.value.score)
        // Setel ulang tebakan pengguna
        updateUserGuess("")
    }

    // Memilih CurrentWord dan CurrentScrambledWord baru dan memperbarui UiState sesuai dengan
    // keadaan permainan saat ini.
    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS){
            // Babak terakhir dalam game, pembaruan GameOver menjadi benar, jangan pilih kata baru
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else{
            // Putaran normal dalam permainan
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Mengacak kata
        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    private fun pickRandomWordAndShuffle(): String {
        // Lanjutkan mengambil kata acak baru hingga Anda mendapatkan kata yang belum pernah digunakan sebelumnya
        currentWord = allWords.random()
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }
}