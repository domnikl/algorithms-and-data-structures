package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class HashTableTest {
    private val hashTable = HashTable<String, String>()

    @Test
    fun `can set elements in hash table`() {
        hashTable.set("A", "A")
        hashTable.set("B", "B")
        hashTable.set("C", "C")

        assertEquals(3, hashTable.size)
    }

    @Test
    fun `can get elements from hash table`() {
        hashTable.set("A", "A")
        hashTable.set("B", "B")
        hashTable.set("C", "C")

        assertEquals("A", hashTable.get("A"))
        assertEquals("B", hashTable.get("B"))
        assertEquals("C", hashTable.get("C"))
    }

    @Test
    fun `returns null if element was not found`() {
        assertNull(hashTable.get("foobar"))
    }

    @Test
    fun `can delete values`() {
        hashTable.set("A", "A")

        assertEquals("A", hashTable.get("A"))

        hashTable.delete("A")

        assertNull(hashTable.get("A"))
    }

    @Test
    fun `can build large hash table`() {
        val hashTable = HashTable<Int, Int>()
        val items = (0..1_000_000)

        for (item in items) {
            hashTable.set(item, item)
        }

        for (item in items) {
            assertEquals(item, hashTable.get(item))
        }
    }
}
