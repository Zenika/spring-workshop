package com.zenika.tournamentmanager.model

import com.zenika.tournamentmanager.util.generatePLayersList
import kotlin.test.Test
import kotlin.test.assertEquals

class TournamentTest {

    @Test(expected = IllegalStateException::class)
    fun `should failed with no players`() {
        Tournament(players = emptyList())
    }

    @Test(expected = IllegalStateException::class)
    fun `should failed with one player `() {
        Tournament(players = generatePLayersList(1))
    }

    @Test(expected = IllegalStateException::class)
    fun `should failed for not pow 2 number of players`() {
        Tournament(players = generatePLayersList(3))
    }

    @Test(expected = IllegalStateException::class)
    fun `should failed with number of players greater than 32`() {
        Tournament(players = generatePLayersList(64))
    }

    @Test
    fun `should succeed with 8 players`() {
        assertEquals(8, Tournament(players = generatePLayersList(8)).players.size)
    }
}
