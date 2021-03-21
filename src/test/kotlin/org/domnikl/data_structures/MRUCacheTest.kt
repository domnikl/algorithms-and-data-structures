package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Test

class MRUCacheTest {
    @Test
    fun `can add new items and remove oldest`() {
        val cache = MRUCache<String, Int>(2)

        cache["B"] = 2
        cache["A"] = 1
        cache["C"] = 3

        assertEquals(null, cache["B"])
        assertEquals(1, cache["A"])
        assertEquals(3, cache["C"])
    }
}
