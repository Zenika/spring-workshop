package com.zenika.lab4

import kotlin.test.Test
import kotlin.test.assertEquals

class CircleAreaCalculatorTest {

    @Test
    fun `calculate circle area`() {
        assertEquals(12.56, area(2))
    }
}