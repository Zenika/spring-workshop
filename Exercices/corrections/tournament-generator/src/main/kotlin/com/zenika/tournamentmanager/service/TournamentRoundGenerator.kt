package com.zenika.tournamentmanager.service

import com.zenika.tournamentmanager.model.Match
import com.zenika.tournamentmanager.model.Player
import com.zenika.tournamentmanager.model.Round

object TournamentRoundGenerator {

    fun generate(players: List<Player>) = players.shuffled().chunked(2) { Match(it[0], it[1]) }.let { Round(it) }

}