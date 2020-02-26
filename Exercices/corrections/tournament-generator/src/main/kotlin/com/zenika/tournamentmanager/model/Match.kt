package com.zenika.tournamentmanager.model

data class Match(
    private val player1: Player, private val player2: Player, private val score: Pair<Int, Int> = Pair(0, 0)
) {

    fun updateScore(score: Pair<Int, Int>) =
        this.copy(score = score)

    fun getWinner(): Player = when {
        score.first > score.second -> player1
        score.first < score.second -> player2
        else -> throw IllegalStateException("Even score is not possible here")
    }

    override fun toString(): String {
        return "${this.player1.pseudo}  ${score.first} - ${score.second}  ${this.player2.pseudo}"
    }
}