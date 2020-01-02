package org.domnikl.data_structures

import org.junit.Assert.assertEquals
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
    fun `will visit 20 nodes at max for 1000 elements`() {
        repeat(1000) {
            val items = (0..1000).shuffled()
            val tree = AvlTree<Int, Int>().also { items.forEach { i -> it.insert(i, i) } }
            var max = 0

            items.forEach { i ->
                val visited = mutableListOf<Int>()

                tree.search(i) {
                    visited.add(it)
                }

                assert(visited.size <= 20) { "visited ${visited.size} nodes: ${visited.joinToString(", ")}" }
                max = maxOf(max, visited.size)
            }

            assert(max <= 20) { "visited $max nodes at max" }
        }
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `throws exception when element was not found`() {
        val tree = AvlTree<Int, Int>()

        tree.search(1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception when duplicated key was inserted`() {
        val tree = AvlTree<Int, Int>()

        tree.insert(1, 1)
        tree.insert(1, 1)
    }
}
