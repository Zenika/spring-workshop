package com.zenika.tournamentgenerator.service

import com.zenika.tournamentgenerator.generatePLayersList
import kotlin.test.Test
import kotlin.test.assertEquals

class TournamentRoundGeneratorTest {

    @Test
    fun `should generate randomly a round of one match for two players`() {
        assertEquals(1, TournamentRoundGenerator().generate(generatePLayersList(2)).matches.size)
    }

    @Test
    fun `should generate randomly round with two matches for four players`() {
        assertEquals(2, TournamentRoundGenerator().generate(generatePLayersList(4)).matches.size)

    }
}