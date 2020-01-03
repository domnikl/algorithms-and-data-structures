package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Test

class DoubleLinkedListTest {
    @Test
    fun `size is initially 0`() {
        assert(DoubleLinkedList<Int>().size == 0)
    }

    @Test
    fun `can add elements to tail`() {
        val list = DoubleLinkedList<String>()
        list.addLast("A")
        list.addLast("B")
        list.addLast("C")

        assertEquals(listOf("C", "B", "A"), list.toList())
        assertEquals(3, list.size)
    }

    @Test
    fun `can add elements to head`() {
        val list = DoubleLinkedList<String>()
        list.addFirst("A")
        list.addFirst("B")
        list.addFirst("C")

        assertEquals(listOf("A", "B", "C"), list.toList())
        assertEquals(3, list.size)
    }

    @Test
    fun `can get first element`() {
        val list = DoubleLinkedList<Int>().also { it.addLast(42) }

        assertEquals(42, list.first())
    }

    @Test
    fun `can get last element`() {
        val list = DoubleLinkedList<Int>().also { it.addFirst(42) }

        assertEquals(42, list.last())
    }


    @Test
    fun `can be cleared`() {
        val list = DoubleLinkedList<Int>().also {
            it.addFirst(2)
            it.addFirst(42)
        }

        list.clear()

        assertEquals(0, list.size)
        assertEquals(emptyList<Int>(), list.toList())
    }

    @Test
    fun `delete first element`() {
        val list = DoubleLinkedList<Int>().also {
            it.addFirst(2)
            it.addFirst(42)
        }

        list.deleteFirst()

        assertEquals(2, list.first())
        assertEquals(1, list.size)
    }

    @Test
    fun `delete last element`() {
        val list = DoubleLinkedList<Int>().also {
            it.addFirst(2)
            it.addFirst(42)
        }

        list.deleteLast()

        assertEquals(42, list.first())
        assertEquals(1, list.size)
    }
}
