package org.domnikl.data_structures

import org.junit.Assert
import org.junit.Test

class QueueTest {
    @Test
    fun `can get size of queue`() {
        val queue = Queue<String>()

        Assert.assertEquals(0, queue.size)

        queue.add("A")
        queue.add("B")
        queue.add("C")

        Assert.assertEquals(3, queue.size)
    }

    @Test
    fun `can pop element from queue`() {
        val queue = Queue<String>()
        queue.add("A")
        queue.add("B")
        queue.add("C")

        Assert.assertEquals("A", queue.remove())
        Assert.assertEquals("B", queue.remove())
        Assert.assertEquals("C", queue.remove())

        Assert.assertEquals(0, queue.size)
    }

    @Test
    fun `can check if queue is empty`() {
        val queue = Queue<Int>()

        assert(queue.isEmpty())

        queue.add(1)

        assert(!queue.isEmpty())
    }

    @Test
    fun `can peek on the queue`() {
        val queue = Queue<Int>().also { it.add(1) }

        Assert.assertEquals(1, queue.peek())
    }

    @Test(expected = IllegalStateException::class)
    fun `throws exception when trying to peek on empty queue`() {
        Queue<Int>().peek()
    }
}
