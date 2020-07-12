package org.domnikl.algorithms.sorting

import org.junit.Assert
import org.junit.Test

class RadixSortTest {
    @Test
    fun `returns copy of already sorted array`() {
        val x = intArrayOf(1, 2, 3)

        Assert.assertArrayEquals(x, x.radixSort())
    }

    @Test
    fun `can sort integer arrays`() {
        val x = intArrayOf(1, 42, 3)
        val expected = intArrayOf(1, 3, 42)

        Assert.assertArrayEquals(expected, x.radixSort())
    }

    @Test
    fun `can sort large collection`() {
        val x = (0..10000).toList()
        val y = x.shuffled().toIntArray()

        Assert.assertArrayEquals(x.toIntArray(), y.radixSort())
    }
}
