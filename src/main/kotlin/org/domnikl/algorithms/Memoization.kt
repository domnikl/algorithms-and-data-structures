package org.domnikl.algorithms

fun <K,V> ((K) -> V).memoize(): ((K) -> V) {
    val memo = mutableMapOf<K,V>()

    return { memo.getOrPut(it) { this(it) } }
}
