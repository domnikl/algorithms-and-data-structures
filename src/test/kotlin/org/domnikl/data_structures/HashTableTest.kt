package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class HashTableTest {
    private val hashTable = HashTable<String, String>()

    @Test
    fun `can set elements in hash table`() {
        hashTable["A"] = "A"
        hashTable["B"] = "B"
        hashTable["C"] = "C"

        assertEquals(3, hashTable.size)
    }

    @Test
    fun `can get elements from hash table`() {
        hashTable["A"] = "A"
        hashTable["B"] = "B"
        hashTable["C"] = "C"

        assertEquals("A", hashTable["A"])
        assertEquals("B", hashTable["B"])
        assertEquals("C", hashTable["C"])
    }

    @Test
    fun `returns null if element was not found`() {
        assertNull(hashTable["foobar"])
    }

    @Test
    fun `can delete values`() {
        hashTable["A"] = "A"

        assertEquals("A", hashTable["A"])

        hashTable.delete("A")

        assertNull(hashTable["A"])
    }

    @Test
    fun `can build large hash table`() {
        val hashTable = HashTable<Int, Int>()
        val items = (0..1_000_000)

        for (item in items) {
            hashTable[item] = item
        }

        for (item in items) {
            assertEquals(item, hashTable[item])
        }
    }
}
