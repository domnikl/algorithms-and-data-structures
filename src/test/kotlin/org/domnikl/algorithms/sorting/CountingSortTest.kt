package org.domnikl.algorithms.sorting

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class CountingSortTest {
    @Test
    fun `does not throw Exception with empty array`() {
        val x = intArrayOf()

        assertArrayEquals(x, x.countingSort())
    }

    @Test
    fun `returns copy of already sorted array`() {
        val x = intArrayOf(1, 2, 3)

        assertArrayEquals(x, x.countingSort())
    }

    @Test
    fun `can sort large collection`() {
        val x = (0..100).toList()
        val y = x.shuffled().toTypedArray().toIntArray()

        assertArrayEquals(x.toIntArray(), y.countingSort())
    }

    @Test
    fun `can sort large collection starting non zero`() {
        val x = (1000..10000).toList()
        val y = x.shuffled().toTypedArray().toIntArray()

        assertArrayEquals(x.toIntArray(), y.countingSort())
    }
}
