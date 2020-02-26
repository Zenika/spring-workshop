package com.zenika.tournamentgenerator.service

import com.zenika.tournamentgenerator.domain.Match
import com.zenika.tournamentgenerator.domain.Player
import com.zenika.tournamentgenerator.domain.Round

object TournamentRoundGenerator {

    fun generate(players: List<Player>) = players.shuffled().chunked(2) { Match(it[0], it[1]) }.let { Round(it) }

}
