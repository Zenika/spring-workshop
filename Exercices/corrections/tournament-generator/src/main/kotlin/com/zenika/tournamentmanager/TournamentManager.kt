package com.zenika.tournamentmanager

import com.zenika.tournamentmanager.model.Player
import com.zenika.tournamentmanager.model.Round
import com.zenika.tournamentmanager.model.Tournament
import com.zenika.tournamentmanager.service.TournamentRoundGenerator
import java.util.*

fun main(args: Array<String>) {
    var listOfPlayers = (1..4).map { Player("Player$it") }

    val tournament = Tournament("Zenika tournament", listOfPlayers).also {
    println(
        """
        === ${it.name} ===
        """
    )
    }

    do {
        val round = TournamentRoundGenerator.generate(listOfPlayers)
        val playedRound = playMatches(round)
        listOfPlayers = getListOfWinners(playedRound)
        println(playedRound)
    } while (listOfPlayers.size != 1)
    println("WINNER is ${listOfPlayers[0].pseudo}")
}

private fun playMatches(round: Round) = round.matches.map {
    it.updateScore(getScore())
}.let { Round(it) }

private fun getListOfWinners(round: Round) = round.matches.map { it.getWinner() }

private fun getScore(): Pair<Int, Int> {
    var score: Pair<Int, Int>
    do {
        val scorePlayer1 = Random().nextInt(10)
        val scorePlayer2 = Random().nextInt(10)
        score = Pair(scorePlayer1, scorePlayer2)
    } while (scorePlayer1 == scorePlayer2)
    return score
}