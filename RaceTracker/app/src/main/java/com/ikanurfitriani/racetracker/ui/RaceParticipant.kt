package com.ikanurfitriani.racetracker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

/**
 * This class represents a state holder for race participant.
 */
class RaceParticipant(
    val name: String,
    val maxProgress: Int = 100,
    val progressDelayMillis: Long = 500L,
    private val progressIncrement: Int = 1,
    private val initialProgress: Int = 0
) {
    init {
        require(maxProgress > 0) { "maxProgress=$maxProgress; must be > 0" }
        require(progressIncrement > 0) { "progressIncrement=$progressIncrement; must be > 0" }
    }

    /**
     * Indicates the race participant's current progress
     */
    var currentProgress by mutableStateOf(initialProgress)
        private set

    /**
     * Updates the value of [currentProgress] by value [progressIncrement] until it reaches
     * [maxProgress]. There is a delay of [progressDelayMillis] between each update.
     */
    suspend fun run() {
        while (currentProgress < maxProgress) {
            delay(progressDelayMillis)
            currentProgress += progressIncrement
        }
    }

    /**
     * Regardless of the value of [initialProgress] the reset function will reset the
     * [currentProgress] to 0
     */
    fun reset() {
        currentProgress = 0
    }
}

/**
 * The Linear progress indicator expects progress value in the range of 0-1. This property
 * calculate the progress factor to satisfy the indicator requirements.
 */
val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()