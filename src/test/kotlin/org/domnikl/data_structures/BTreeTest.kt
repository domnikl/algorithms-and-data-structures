package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BTreeTest {
    @Test
    fun `keeps track of it's size`() {
        val btree = BTree<Int,String>()

        assertEquals(0, btree.size)
        assert(btree.isEmpty())

        btree[0] = "A"

        assertEquals(1, btree.size)
        assert(!btree.isEmpty())
    }

    @Test
    fun `can insert key-value pairs`() {
        val btree = BTree<Int,String>()

        val values = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        values.forEachIndexed { i, char ->
            btree[i] = char.toString()
        }

        assertEquals(3, btree.height)

        values.forEachIndexed { i, char ->
            assertEquals(char.toString(), btree[i])
        }
    }

    @Test
    fun `can insert massive amounts of ints`() {
        val btree = BTree<Int,Int>()

        (0..1_000_000).shuffled().forEach {
            btree[it] = it
        }

        (0..1_000_000).forEach {
            assertEquals(it, btree[it])
        }
    }

    @Test
    fun `returns null when value is not in tree`() {
        assertNull(BTree<Int,Int>()[0])
    }
}
