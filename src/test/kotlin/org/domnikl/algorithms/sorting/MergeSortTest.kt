package org.domnikl.algorithms.sorting

import org.junit.Assert
import org.junit.Test

class MergeSortTest {
    @Test
    fun `returns copy of already sorted array`() {
        val x = arrayOf(1, 2, 3)

        Assert.assertArrayEquals(x, x.mergeSort())
    }

    @Test
    fun `can sort integer arrays`() {
        val x = arrayOf(1, 42, 3)

        Assert.assertArrayEquals(arrayOf(1, 3, 42), x.mergeSort())
    }

    @Test
    fun `can sort arbitrary arrays`() {
        val x = arrayOf("foo", "bar")

        Assert.assertArrayEquals(arrayOf("bar", "foo"), x.mergeSort())
    }

    @Test
    fun `can sort large collection`() {
        val items = (0..10000).toList()
        val shuffled = items.shuffled().toTypedArray()

        Assert.assertArrayEquals(items.toTypedArray(), shuffled.mergeSort())
    }
}
