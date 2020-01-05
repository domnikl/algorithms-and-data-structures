package org.domnikl.algorithms

import org.junit.Assert.assertEquals
import org.junit.Test

class MemoizationTest {
    @Test
    fun `without memoization`() {
        fun fibonacci(n: Int): Int {
            return when {
                n <= 0 -> 0
                n == 1 -> 1
                else -> fibonacci(n - 1) + fibonacci(n - 2)
            }
        }

        val memoized = ::fibonacci.memoize()

        assertEquals(0, memoized(0))
        assertEquals(1, memoized(1))
        assertEquals(1, memoized(2))
        assertEquals(2, memoized(3))
        assertEquals(3, memoized(4))
        assertEquals(5, memoized(5))
        assertEquals(8, memoized(6))
        assertEquals(13, memoized(7))
        assertEquals(21, memoized(8))
    }
}
