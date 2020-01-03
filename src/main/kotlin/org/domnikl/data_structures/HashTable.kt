package org.domnikl.data_structures

class HashTable<K,V> {
    private var capacity = 1

    var size: Int = 0
        private set

    private var data: Array<LinkedList<Node<K,V>>?> = Array(capacity) { null }

    fun set(key: K, value: V) {
        if (size == capacity) { // collisions may occur nonetheless
            resize()
        }

        val n = Node(key, value)
        val hash = hash(key, capacity)

        if (data[hash] != null) {
            data[hash]?.addFirst(n)
        } else {
            val linkedList = LinkedList<Node<K,V>>()
            linkedList.addFirst(n)

            data[hash] = linkedList
        }

        size++
    }

    private fun resize() {
        val newCapacity = capacity * 2
        val newData: Array<LinkedList<Node<K,V>>?> = Array(newCapacity) { null }

        data.forEach { list ->
            list?.toList()?.forEach {
                val hash = hash(it.key, newCapacity)

                if (newData[hash] != null) {
                    newData[hash]?.addFirst(it)
                } else {
                    val linkedList = LinkedList<Node<K,V>>()
                    linkedList.addFirst(it)

                    newData[hash] = linkedList
                }
            }
        }

        capacity = newCapacity
        data = newData
    }

    fun delete(key: K) {
        nodeForKey(key)?.let { linkedListForKey(key)?.delete(it) }
    }

    fun get(key: K): V? {
        return nodeForKey(key)?.value
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
        return data[hash(key, capacity)]
    }

    private fun hash(key: K, capacity: Int): Int {
        return key.hashCode() % capacity
    }

    private data class Node<K,V>(val key: K, val value: V)
}
