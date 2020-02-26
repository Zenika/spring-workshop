package com.zenika.tournamentgenerator.domain

import com.zenika.tournamentgenerator.generatePLayersList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TournamentTest {

    @Test
    fun `should failed with no players`() {
        assertFailsWith<IllegalStateException> {
            Tournament(players = emptyList())
        }
    }

    @Test
    fun `should failed with one player`() {
        assertFailsWith<IllegalStateException> {
            Tournament(players = generatePLayersList(1))
        }
    }

    @Test
    fun `should failed for not pow 2 number of players`() {
        assertFailsWith<IllegalStateException> {
            Tournament(players = generatePLayersList(3))
        }
    }

    @Test
    fun `should failed with number of players greater than 32`() {
        assertFailsWith<IllegalStateException> {
            Tournament(players = generatePLayersList(64))
        }
    }

    @Test
    fun `should succeed with 8 players`() {
        assertEquals(8, Tournament(players = generatePLayersList(8)).players.size)
    }
}