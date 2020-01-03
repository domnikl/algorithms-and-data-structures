package org.domnikl.data_structures

import org.junit.Assert.assertEquals
import org.junit.Test

class StackTest {
    @Test
    fun `can get size of stack`() {
        val stack = Stack<String>()

        assertEquals(0, stack.size)

        stack.push("A")
        stack.push("B")
        stack.push("C")

        assertEquals(3, stack.size)
    }

    @Test
    fun `can pop element from stack`() {
        val stack = Stack<String>()
        stack.push("A")
        stack.push("B")
        stack.push("C")

        assertEquals("C", stack.pop())
        assertEquals("B", stack.pop())
        assertEquals("A", stack.pop())

        assertEquals(0, stack.size)
    }

    @Test
    fun `can check if stack is empty`() {
        val stack = Stack<Int>()

        assert(stack.isEmpty())

        stack.push(1)

        assert(!stack.isEmpty())
    }

    @Test
    fun `can peek on the stack`() {
        val stack = Stack<Int>().also { it.push(1) }

        assertEquals(1, stack.peek())
    }

    @Test(expected = IllegalStateException::class)
    fun `throws exception when trying to peek on empty stack`() {
        Stack<Int>().peek()
    }
}
