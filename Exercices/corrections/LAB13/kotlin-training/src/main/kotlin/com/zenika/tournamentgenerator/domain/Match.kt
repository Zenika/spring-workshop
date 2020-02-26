package com.zenika.tournamentgenerator.domain

data class Match(val player1: Player, val player2: Player, val score: Pair<Int, Int> = Pair(0, 0)) {

    override fun toString(): String {
        return "${this.player1.pseudo}  ${score.first} - ${score.second}  ${this.player2.pseudo}"
    }

    fun updateScore(score: Pair<Int, Int>) =
        this.copy(score = score)

    fun getWinner(): Player = when {
        score.first > score.second -> player1
        score.first < score.second -> player2
        else -> throw IllegalStateException("Even score is not possible here")
    }
}
