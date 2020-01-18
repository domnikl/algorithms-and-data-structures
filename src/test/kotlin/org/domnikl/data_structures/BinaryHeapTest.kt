package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BinaryHeapTest {
    @Test
    fun `can insert and check size`() {
        assertEquals(3, maxBinaryHeap(39, 25, 42).size)
    }

    @Test
    fun `can insert values after creating it`() {
        val binaryHeap = maxBinaryHeap<Int>()

        binaryHeap.insert(1)
        binaryHeap.insert(2)

        assertEquals(2, binaryHeap.pop())
        assertEquals(1, binaryHeap.pop())
    }

    @Test
    fun `can pop from MaxBinaryHeap`() {
        val binaryHeap = maxBinaryHeap(25, 42, 39)

        assertEquals(42, binaryHeap.pop())
        assertEquals(39, binaryHeap.pop())
        assertEquals(25, binaryHeap.pop())
        assertNull(binaryHeap.pop())
        assertEquals(0, binaryHeap.size)
    }

    @Test
    fun `can pop from MinBinaryHeap`() {
        val binaryHeap = minBinaryHeap(25, 42, 39)

        assertEquals(25, binaryHeap.pop())
        assertEquals(39, binaryHeap.pop())
        assertEquals(42, binaryHeap.pop())
        assertNull(binaryHeap.pop())
        assertEquals(0, binaryHeap.size)
    }

    @Test
    fun `resizes automatically`() {
        val binaryHeap = maxBinaryHeap(*((0..1000).toList().toTypedArray()))

        assertEquals(1001, binaryHeap.size)
        assertEquals(1000, binaryHeap.pop())
    }

    @Test
    fun `can be converted to list`() {
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), minBinaryHeap(*(1..10).toList().toTypedArray()).toList())
        assertEquals(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), maxBinaryHeap(*(1..10).toList().toTypedArray()).toList())
    }
}
