package org.domnikl.algorithms.select

import org.junit.Assert
import org.junit.Test

class QuickSelectTest {
    @Test
    fun `can select integer arrays`() {
        val x = arrayOf(1, 42, 3)

        Assert.assertEquals(1, x.quickSelect(0))
    }

    @Test
    fun `can select arbitrary arrays`() {
        val x = arrayOf("foo", "bar")

        Assert.assertEquals("foo", x.quickSelect(1))
    }

    @Test
    fun `can select large collection`() {
        val x = (0..10000).toList()
        val y = x.shuffled().toTypedArray()

        Assert.assertEquals(x.toTypedArray()[x.size / 2], y.quickSelect(x.size / 2))
    }

    @Test
    fun `test permutations`() {
        repeat(100) {
            `can select large collection`()
        }
    }
}
