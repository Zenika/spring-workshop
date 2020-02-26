package com.zenika.tournamentgenerator

import com.zenika.tournamentgenerator.domain.Player


fun generatePLayersList(numberOfPlayers: Int) = (1..numberOfPlayers).map { Player("Player $it") }