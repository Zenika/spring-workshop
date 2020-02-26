package com.zenika.tournamentgenerator.service

import com.zenika.tournamentgenerator.domain.Match
import com.zenika.tournamentgenerator.domain.Player
import com.zenika.tournamentgenerator.domain.Round

object TournamentRoundGenerator {

    fun generate(players: List<Player>): Round {
        val matchesPLayers = players.chunked(2)
        val matches = mutableListOf<Match>()
        for(matchPlayers in matchesPLayers) {
            matches.add(Match(matchPlayers[0], matchPlayers[1]))
        }
        return Round(matches)
    }

}
