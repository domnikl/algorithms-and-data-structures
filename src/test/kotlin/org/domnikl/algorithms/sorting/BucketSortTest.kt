package org.domnikl.algorithms.sorting

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class BucketSortTest {
    @Test
    fun `returns copy of already sorted array`() {
        val x = arrayOf(1, 2, 3)

        assertArrayEquals(x, x.bucketSort())
    }

    @Test
    fun `can sort large collection`() {
        val x = (0..10000).toList()
        val y = x.shuffled().toTypedArray()

        assertArrayEquals(x.toTypedArray(), y.bucketSort())
    }

    @Test
    fun `can sort integer arrays`() {
        val x = arrayOf(1, 42, 3)

        assertArrayEquals(arrayOf(1, 3, 42), x.bucketSort())
    }

    @Test
    fun `can sort arbitrary arrays`() {
        val x = arrayOf("foo", "bar")

        assertArrayEquals(arrayOf("bar", "foo"), x.bucketSort())
    }
}
