package com.zenika.tournamentgenerator.domain

import com.zenika.tournamentgenerator.generatePLayersList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RoundTest {

    @Test
    fun `should be final for 2 players`() {
        assertEquals(RoundName.FINAL, Round(generateMatches(2)).name)
    }

    @Test
    fun `should be semifinals for 4 players`() {
        assertEquals(RoundName.SEMIFINALS, Round(generateMatches(4)).name)
    }

    @Test
    fun `should be quarterfinals for 8 players`() {
        assertEquals(RoundName.QUARTERFINALS, Round(generateMatches(8)).name)
    }

    @Test
    fun `should be round of 16 for 16 players`() {
        assertEquals(RoundName.ROUND_OF_16, Round(generateMatches(16)).name)
    }

    @Test
    fun `should be first round for 32 players`() {
        assertEquals(RoundName.FIRST_ROUND, Round(generateMatches(32)).name)
    }

    @Test
    fun `fail for unsupported number of matches`() {
        assertFailsWith<IllegalStateException> { RoundName.from(5) }
    }

    private fun generateMatches(numberOfPlayers: Int) =
        generatePLayersList(numberOfPlayers).chunked(2) { Match(it[0], it[1]) }
}
