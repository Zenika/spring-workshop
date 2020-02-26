package com.zenika.tournamentmanager.model

import kotlin.test.Test
import kotlin.test.assertEquals

class MatchTest {

    private val player1 = Player("Player1")
    private val player2 = Player("Player2")

    @Test
    fun `should display match`() {
        assertEquals("Player1  0 - 0  Player2", Match(player1, player2).toString())
    }

    @Test
    fun `should update score to match`() {
        val match = Match(player1, player2)
        val updatedMatch = match.updateScore(Pair(4, 3))
        assertEquals(Match(player1, player2, Pair(4, 3)), updatedMatch)
    }

    @Test
    fun `should give winner`() {
        val match = Match(player1, player2, Pair(2,4))
        assertEquals(player2, match.getWinner())
    }

}
