package com.zenika.lab6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FibonacciTest {

    @Test
    fun `fibonacci of 0 is 0`() {
        assertEquals(0, fibonacci(0))
    }

    @Test
    fun `fibonacci of 1 is 1`() {
        assertEquals(1, fibonacci(1))
    }

    @Test
    fun `fibonacci of 2 is 1`() {
        assertEquals(1, fibonacci(2))
    }

    @Test
    fun `fibonacci of 3 is 2`() {
        assertEquals(2, fibonacci(3))
    }
}
