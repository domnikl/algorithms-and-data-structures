package org.domnikl.data_structures

/**
 * Implements a most-recently-used cache, that will remove the eldest item itself when adding a new item and the
 * capacity is reached
 */
class MRUCache<K, V>(private val capacity: Int) : LinkedHashMap<K, V>() {
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
        return size > capacity
    }
}
