package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BinaryHeapTest {
    @Test
    fun `can insert and check size`() {
        assertEquals(3, binaryHeap(39, 25, 42).size)
    }

    @Test
    fun `can insert values after creating it`() {
        val binaryHeap = binaryHeap<Int>()

        binaryHeap.insert(1)
        binaryHeap.insert(2)

        assertEquals(2, binaryHeap.extractMax())
        assertEquals(1, binaryHeap.extractMax())
    }

    @Test
    fun `can extract max`() {
        val binaryHeap = binaryHeap(25, 42, 39)

        assertEquals(42, binaryHeap.extractMax())
        assertEquals(39, binaryHeap.extractMax())
        assertEquals(25, binaryHeap.extractMax())
        assertNull(binaryHeap.extractMax())
        assertEquals(0, binaryHeap.size)
    }

    @Test
    fun `resizes automatically`() {
        val binaryHeap = binaryHeap(*((0..1000).toList().toTypedArray()))

        assertEquals(1001, binaryHeap.size)
        assertEquals(1000, binaryHeap.extractMax())
    }
}
