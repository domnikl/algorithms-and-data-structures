package org.domnikl.algorithms.search

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BinarySearchTest {
    @Test
    fun `can find a value in a sorted array`() {
        val data = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)

        data.forEach {
            assertEquals(it - 1, data.binarySearch(it))
        }
    }

    @Test
    fun `can't find a value in a non-sorted array`() {
        val data = arrayOf(42, 1, 2, 3, 6, 5)

        assertNull(data.binarySearch(1))
    }

    @Test
    fun `can't find a value in an empty array`() {
        assertNull(emptyArray<Int>().binarySearch(6))
    }
}
