package com.zenika.tournamentgenerator.domain

data class Round(val matches: List<Match>)

enum class RoundName {
    FINAL, SEMIFINALS, QUARTERFINALS, ROUND_OF_16, FIRST_ROUND;
}