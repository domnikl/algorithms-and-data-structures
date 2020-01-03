package org.domnikl.data_structures

class Queue<T> {
    private val doubleLinkedList = DoubleLinkedList<T>()

    val size
        get() = doubleLinkedList.size

    fun add(element: T) {
        doubleLinkedList.addLast(element)
    }

    fun remove(): T {
        val element = peek()

        doubleLinkedList.deleteFirst()

        return element
    }

    fun peek(): T {
        return doubleLinkedList.first()
    }

    fun isEmpty() = size == 0
}
