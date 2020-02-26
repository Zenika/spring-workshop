package com.zenika.tournamentgenerator

import com.zenika.tournamentgenerator.domain.Player


fun generatePLayersList(numberOfPlayers: Int): List<Player> {
    val players = mutableListOf<Player>()
    for (index in 1..numberOfPlayers) {
        players.add(Player("Player$index"))
    }
    return players.toList()
}
