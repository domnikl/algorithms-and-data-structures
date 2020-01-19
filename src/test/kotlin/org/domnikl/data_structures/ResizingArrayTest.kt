package org.domnikl.data_structures

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class ResizingArrayTest {
    @Test
    fun `can set values`() {
        assertEquals(3, resizingArray(1, 2, 3).size)
    }

    @Test
    fun `can resize automatically`() {
        val a = resizingArray<Int>()
        a[0] = 3
        a[1] = 1
        a[2] = 2

        assertEquals(3, a.size)
        assertEquals(3, a[0])
        assertEquals(1, a[1])
        assertEquals(2, a[2])
    }

    @Test
    fun `can insert last value`() {
        val a = resizingArray(1, 2)
        a[2] = 3

        assertEquals(3, a.size)
        assertEquals(1, a[0])
        assertEquals(2, a[1])
        assertEquals(3, a[2])
    }

    @Test
    fun `can delete element`() {
        val a = resizingArray(1, 2)
        a.delete(0)

        assertEquals(1, a.size)
        assertEquals(2, a[0])
        assertEquals(null, a[1])

        a.delete(0)

        assertEquals(0, a.size)
        assertEquals(null, a[0])
        assertEquals(null, a[1])
    }

    @Test
    fun `can get values`() {
        assertEquals(2, resizingArray(1, 2, 3)[1])
    }

    @Test
    fun `can get typed array`() {
        assertArrayEquals(arrayOf(4, 5, 9), resizingArray(4, 5, 9).toTypedArray())
    }
}
