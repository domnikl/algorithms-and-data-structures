package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AvlTreeTest {
    @Test
    fun `can add elements to the tree`() {
        val items = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val tree = AvlTree<Int, Char>()

        items.forEachIndexed { k, v ->
            tree.insert(k, v)
        }

        items.forEachIndexed { k, v ->
            assertEquals(v, tree.search(k))
        }
    }

    @Test
    fun `will right rotate tree`() {
        val items = (0..1000).shuffled()
        val tree = AvlTree<Int, Int>()

        for (i in items) {
            tree.insert(i, i)
        }

        items.forEach {
            assertEquals(it, tree.search(it))
        }
    }

    @Test
    fun `will visit 22 nodes at max for 1000 elements`() {
        repeat(1000) {
            val items = (0..1000).shuffled()
            val tree = AvlTree<Int, Int>().also { items.forEach { i -> it.insert(i, i) } }
            var max = 0

            items.forEach { i ->
                val visited = mutableListOf<Int>()

                tree.search(i) {
                    visited.add(it)
                }

                assert(visited.size <= 22) { "visited ${visited.size} nodes: ${visited.joinToString(", ")}" }
                max = maxOf(max, visited.size)
            }

            assert(max <= 22) { "visited $max nodes at max" }
        }
    }

    @Test
    fun `returns null when element was not found`() {
        assertNull(AvlTree<Int, Int>().search(1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception when duplicated key was inserted`() {
        val tree = AvlTree<Int, Int>()

        tree.insert(1, 1)
        tree.insert(1, 1)
    }

    @Test
    fun `can delete root node`() {
        val tree = AvlTree<Int, String>()

        tree.insert(1, "A")

        assertEquals("A", tree.search(1))

        tree.delete(1)

        assertNull(tree.search(1))
    }

    @Test
    fun `can delete node without children`() {
        val tree = AvlTree<Int, String>()

        tree.insert(2, "A")
        tree.insert(1, "B")
        tree.insert(3, "C")

        assertEquals("C", tree.search(3))
        tree.delete(3)
        assertNull(tree.search(3))

        assertEquals("B", tree.search(1))
        tree.delete(1)
        assertNull(tree.search(1))
    }

    @Test
    fun `can delete node with left child`() {
        val tree = AvlTree<Int, String>()

        tree.insert(2, "A")
        tree.insert(1, "B")

        tree.delete(2)
        assertNull(tree.search(2))
        assertEquals("B", tree.search(1))
    }

    @Test
    fun `can delete node with right child`() {
        val tree = AvlTree<Int, String>()

        tree.insert(2, "A")
        tree.insert(3, "C")

        tree.delete(2)
        assertNull(tree.search(2))
        assertEquals("C", tree.search(3))
    }

    @Test
    fun `can delete node with two children`() {
        val tree = AvlTree<Int, String>()

        tree.insert(4, "A")
        tree.insert(2, "B")
        tree.insert(6, "C")
        tree.insert(1, "D")
        tree.insert(3, "E")
        tree.insert(5, "F")
        tree.insert(7, "G")

        tree.delete(6)
        assertNull(tree.search(6))
        assertEquals("A", tree.search(4))
        assertEquals("B", tree.search(2))
        assertEquals("D", tree.search(1))
        assertEquals("E", tree.search(3))
        assertEquals("F", tree.search(5))
        assertEquals("G", tree.search(7))
    }

    @Test
    fun `can delete root node with two children`() {
        val tree = AvlTree<Int, String>()

        tree.insert(4, "A")
        tree.insert(2, "B")
        tree.insert(6, "C")
        tree.insert(1, "D")
        tree.insert(3, "E")
        tree.insert(5, "F")
        tree.insert(7, "G")

        tree.delete(4)
        assertNull(tree.search(4))
        assertEquals("B", tree.search(2))
        assertEquals("C", tree.search(6))
        assertEquals("D", tree.search(1))
        assertEquals("E", tree.search(3))
        assertEquals("F", tree.search(5))
        assertEquals("G", tree.search(7))
    }

    @Test
    fun `can delete a single key and still find all other keys`() {
        val items = (0..1000).shuffled()

        for (item in items) {
            val tree = avlTree(items.map { Pair(it, it) }.toMap())
            val remainingItems = items.toMutableList() - item

            tree.delete(item)

            for (remaining in remainingItems) {
                assertEquals(remaining, tree.search(remaining))
            }
        }
    }

    @Test
    fun `can delete value with 1 child`() {
        val items = mapOf(2 to 2, 1 to 1, 0 to 0, 4 to 4, 3 to 3, 5 to 5)
        val tree = avlTree(items)

        tree.delete(4)

        assertEquals(0, tree.search(0))
        assertEquals(1, tree.search(1))
        assertEquals(2, tree.search(2))
        assertEquals(3, tree.search(3))
        assertEquals(5, tree.search(5))
        assertNull(tree.search(4))
    }

    @Test
    fun `can delete value with 2 children`() {
        val items = mapOf(2 to 2, 1 to 1, 0 to 0, 4 to 4, 3 to 3, 5 to 5)
        val tree = avlTree(items)

        tree.delete(1)

        assertEquals(0, tree.search(0))
        assertEquals(4, tree.search(4))
        assertEquals(2, tree.search(2))
        assertEquals(3, tree.search(3))
        assertEquals(5, tree.search(5))
        assertNull(tree.search(1))
    }
}
