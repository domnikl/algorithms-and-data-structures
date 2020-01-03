package org.domnikl.data_structures

import kotlin.math.absoluteValue

class HashTable<K,V> {
    private var capacity = 16
    private var nonNullIndices = 0

    var size: Int = 0
        private set

    private var data: Array<LinkedList<Node<K,V>>?> = Array(capacity) { null }

    operator fun set(key: K, value: V) {
        if (nonNullIndices == capacity) {
            resize()
        }

        val n = Node(key, value)
        val hash = indexFor(key, capacity)

        if (data[hash] != null) {
            data[hash]?.addFirst(n)
            nonNullIndices++
        } else {
            val linkedList = LinkedList<Node<K,V>>()
            linkedList.addFirst(n)

            data[hash] = linkedList
        }

        size++
    }

    operator fun get(key: K): V? {
        return nodeForKey(key)?.value
    }

    private fun resize() {
        val newCapacity = capacity * 2
        val newData: Array<LinkedList<Node<K,V>>?> = Array(newCapacity) { null }
        var newNonNullIndices = 0

        data.forEach { list ->
            list?.toList()?.forEach {
                val hash = indexFor(it.key, newCapacity)

                if (newData[hash] != null) {
                    newData[hash]?.addFirst(it)
                    newNonNullIndices++
                } else {
                    val linkedList = LinkedList<Node<K,V>>()
                    linkedList.addFirst(it)

                    newData[hash] = linkedList
                }
            }
        }

        nonNullIndices = newNonNullIndices
        capacity = newCapacity
        data = newData
    }

    fun delete(key: K) {
        nodeForKey(key)?.let { linkedListForKey(key)?.delete(it) }
    }

    private fun nodeForKey(key: K): Node<K,V>? {
        linkedListForKey(key)?.toSequence()?.forEach {
            if (it.key == key) {
                return it
            }
        }

        return null
    }

    private fun linkedListForKey(key: K): LinkedList<Node<K,V>>? {
        return data[indexFor(key, capacity)]
    }

    private fun indexFor(key: K, capacity: Int): Int {
        return key.hashCode().absoluteValue % capacity
    }

    private data class Node<K,V>(val key: K, val value: V)
}
