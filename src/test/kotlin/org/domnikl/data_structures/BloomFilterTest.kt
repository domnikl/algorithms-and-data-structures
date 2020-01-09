package org.domnikl.data_structures

import org.junit.Test

class BloomFilterTest {
    @Test
    fun `can add values and check if they are in the filter`() {
        val filter = BloomFilter<String>(100)

        filter.add("Hello")
        filter.add("World")

        assert("Hello" in filter)
        assert("World" in filter)
    }

    @Test
    fun `can check for values that are not in the filter`() {
        assert("Hello" !in BloomFilter<String>(100))
    }

    @Test
    fun `can add 10,000 values and check if they are in it`() {
        val filter = BloomFilter<Int>(1_000_000)
        val items = 0..1_000_000

        items.forEach { filter.add(it) }

        items.forEach {
            assert(it in filter)
        }

        assert(-1 !in filter)
    }
}
