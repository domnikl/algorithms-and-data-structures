package org.domnikl.data_structures

class Stack<T> {
    private val linkedList = LinkedList<T>()

    val size
        get() = linkedList.size

    fun push(element: T) {
        linkedList.add(element)
    }

    fun pop(): T {
        val element = peek()
        linkedList.deleteFirst()

        return element
    }

    fun isEmpty() = size == 0

    fun peek(): T {
        return linkedList.first()
    }
}
