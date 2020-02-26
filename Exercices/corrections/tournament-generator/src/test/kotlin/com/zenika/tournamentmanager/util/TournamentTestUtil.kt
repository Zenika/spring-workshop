package com.zenika.tournamentmanager.util

import com.zenika.tournamentmanager.model.Player

fun generatePLayersList(numberOfPlayers: Int) = (1..numberOfPlayers).map { Player("Player $it") }