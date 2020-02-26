package com.zenika.tournamentgenerator.domain

data class Round(val matches: List<Match>) {
    val name: RoundName = RoundName.from(matches.size)
}

enum class RoundName {
    FINAL, SEMIFINALS, QUARTERFINALS, ROUND_OF_16, FIRST_ROUND;

    companion object {
        fun from(numberOfMatches: Int) = when (numberOfMatches) {
            1 -> FINAL
            2 -> SEMIFINALS
            4 -> QUARTERFINALS
            8 -> ROUND_OF_16
            16 -> FIRST_ROUND
            else -> throw IllegalStateException("Number of matches not supported")
        }
    }
}
