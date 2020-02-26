package com.zenika.tournamentgenerator.domain

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MatchTest {

    private lateinit var match: Match

    @BeforeTest
    fun setUp() {
        match = Match(Player("Player1"), Player("Player2"))
    }

    @Test
    fun `should display match`() {
        assertEquals("Player1  0 - 0  Player2", match.toString())
    }

    @Test
    fun `should update match score`() {
        assertEquals(Match(Player("Player1"), Player("Player2"), Pair(4, 3)), match.updateScore(Pair(4, 3)))
    }

    @Test
    fun `should give winner`() {
        val match = match.updateScore(Pair(2,4))
        assertEquals(Player("Player2"), match.getWinner())
    }

}