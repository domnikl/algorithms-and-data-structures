package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Test

class ResizingArrayTest {
    @Test
    fun `can set values`() {
        assertEquals(3, resizingArray(1, 2, 3).size)
    }

    @Test
    fun `can resize automatically`() {
        val a = resizingArray(1, 2)
        a.insert(0, 3)

        assertEquals(3, a.size)
        assertEquals(3, a.get(0))
        assertEquals(1, a.get(1))
        assertEquals(2, a.get(2))
    }

    @Test
    fun `can insert last value`() {
        val a = resizingArray(1, 2)
        a.insert(2, 3)

        assertEquals(3, a.size)
        assertEquals(1, a.get(0))
        assertEquals(2, a.get(1))
        assertEquals(3, a.get(2))
    }

    @Test
    fun `can delete element`() {
        val a = resizingArray(1, 2)
        a.delete(0)

        assertEquals(1, a.size)
        assertEquals(2, a.get(0))
        assertEquals(null, a.get(1))

        a.delete(0)

        assertEquals(0, a.size)
        assertEquals(null, a.get(0))
        assertEquals(null, a.get(1))
    }

    @Test
    fun `can get values`() {
        assertEquals(2, resizingArray(1, 2, 3).get(1))
    }
}
