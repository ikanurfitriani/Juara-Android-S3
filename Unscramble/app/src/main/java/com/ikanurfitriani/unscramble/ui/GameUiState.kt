// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.unscramble.ui

// Membuat data class GameUiState
data class GameUiState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 1,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)
