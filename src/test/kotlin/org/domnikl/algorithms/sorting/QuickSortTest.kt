package org.domnikl.algorithms.sorting

import org.junit.Assert
import org.junit.Test

class QuickSortTest {
    @Test
    fun `returns copy of already sorted array`() {
        val x = arrayOf(1, 2, 3)

        Assert.assertArrayEquals(x, x.quickSort())
    }

    @Test
    fun `can sort integer arrays`() {
        val x = arrayOf(1, 42, 3)

        Assert.assertArrayEquals(arrayOf(1, 3, 42), x.quickSort())
    }

    @Test
    fun `can sort arbitrary arrays`() {
        val x = arrayOf("foo", "bar")

        Assert.assertArrayEquals(arrayOf("bar", "foo"), x.quickSort())
    }

    @Test
    fun `can sort large collection`() {
        val x = (0..10000).toList()
        val y = x.shuffled().toTypedArray()

        Assert.assertArrayEquals(x.toTypedArray(), y.quickSort())
    }
}
