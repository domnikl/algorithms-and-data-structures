package org.domnikl.data_structures

class DoubleLinkedList<T> {
    var size: Int = 0
        private set

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun addLast(element: T) {
        val n = Node(element)
        size++

        if (tail == null) {
            tail = n
            head = n
            return
        }

        val a = tail

        tail = n
        n.previous = a
        a?.next = n
    }

    fun addFirst(element: T) {
        val n = Node(element)
        size++

        if (head == null) {
            tail = n
            head = n
            return
        }

        val a = head

        head = n
        n.next = a
        a?.previous = n
    }

    fun toList(): List<T> {
        val l = mutableListOf<T>()
        var current = tail

        do {
            current?.data?.let { l.add(it) }
            current = current?.previous
        } while (current != null)

        return l.toList()
    }

    fun first(): T {
        if (head == null) {
            throw IllegalStateException("empty list")
        }

        return head!!.data
    }

    fun last(): T {
        if (tail == null) {
            throw IllegalStateException("empty list")
        }

        return tail!!.data
    }

    fun deleteFirst() {
        head = head?.next
        size--
    }

    fun deleteLast() {
        tail = tail?.previous
        size--
    }

    fun clear() {
        tail = null
        head = null
        size = 0
    }

    private class Node<N>(val data: N) {
        var next: Node<N>? = null
        var previous: Node<N>? = null
    }
}
