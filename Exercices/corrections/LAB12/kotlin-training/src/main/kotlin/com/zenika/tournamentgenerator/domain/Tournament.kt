package com.zenika.tournamentgenerator.domain

data class Tournament(val name: String = "Z Tournament", val players: List<Player>) {
    init {
        players.size.let {
            check(!(it !in 2..32 || it and it - 1 != 0)) { "Players should be a power of 2 number greater or equal to 2" }
        }
    }
}