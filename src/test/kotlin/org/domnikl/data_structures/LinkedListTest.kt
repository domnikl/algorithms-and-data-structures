package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Test

class LinkedListTest {
    @Test
    fun `size is initially 0`() {
        assert(LinkedList<Int>().size == 0)
    }

    @Test
    fun `can add new elements`() {
        val list = LinkedList<String>()

        list.addFirst("A")
        list.addFirst("B")
        list.addFirst("C")

        assertEquals(3, list.size)
        assertEquals(listOf("C", "B", "A"), list.toList())
    }

    @Test
    fun `can add new elements in the back`() {
        val list = LinkedList<String>()

        list.addFirst("A")
        list.addLast("B")
        list.addLast("C")

        assertEquals(3, list.size)
        assertEquals(listOf("A", "B", "C"), list.toList())
    }

    @Test
    fun `can get first element`() {
        val list = LinkedList<Int>().also { it.addFirst(42) }

        assertEquals(42, list.first())
    }

    @Test
    fun `get last element`() {
        val list = LinkedList<Int>().also {
            it.addFirst(2)
            it.addFirst(42)
        }

        assertEquals(2, list.last())
    }

    @Test
    fun `delete first element`() {
        val list = LinkedList<Int>().also {
            it.addFirst(2)
            it.addFirst(42)
        }

        list.deleteFirst()

        assertEquals(2, list.first())
        assertEquals(1, list.size)
    }

    @Test
    fun `can delete a value from the list`() {
        val list = LinkedList<Int>().also {
            it.addFirst(3)
            it.addFirst(2)
            it.addFirst(42)
            it.addFirst(2)
        }

        list.delete(2)

        assertEquals(2, list.size)
        assertEquals(listOf(42, 3), list.toList())
    }

    @Test
    fun `can be cleared`() {
        val list = LinkedList<Int>().also {
            it.addFirst(2)
            it.addFirst(42)
        }

        list.clear()

        assertEquals(0, list.size)
        assertEquals(emptyList<Int>(), list.toList())
    }

    @Test
    fun `can be converted to a sequence`() {
        val list = LinkedList<Int>().also {
            it.addFirst(2)
            it.addFirst(1)
        }

        assertEquals(listOf(1, 2), list.toSequence().toList())
    }

    @Test
    fun `can be reversed`() {
        val list = LinkedList<Char>().also {
            it.addFirst('C')
            it.addFirst('B')
            it.addFirst('A')
        }

        assertEquals(listOf('C', 'B', 'A'), list.reverse().toSequence().toList())
    }
}
