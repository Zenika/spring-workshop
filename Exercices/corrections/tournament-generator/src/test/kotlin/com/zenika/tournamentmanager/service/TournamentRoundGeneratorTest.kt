package com.zenika.tournamentmanager.service

import com.zenika.tournamentmanager.util.generatePLayersList
import kotlin.test.Test
import kotlin.test.assertEquals

class TournamentRoundGeneratorTest {

    @Test
    fun `should generate randomly round with one match for two players`() {
        assertEquals(1, TournamentRoundGenerator.generate(generatePLayersList(2)).matches.size)
    }

    @Test
    fun `should generate randomly round with two matches for four players`() {
        assertEquals(2, TournamentRoundGenerator.generate(generatePLayersList(4)).matches.size)

    }
}
