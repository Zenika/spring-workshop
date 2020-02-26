package com.zenika.tournamentmanager.model

data class Tournament(val name: String = "Tournament", val players: List<Player>) {
    init {
        players.size.let {
            if (it !in 2..32 || (it and it - 1 != 0)) throw IllegalStateException("Players should be a power of 2 number greater or equal to 2")
        }
    }
}