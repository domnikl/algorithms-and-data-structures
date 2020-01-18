package org.domnikl.algorithms.sorting

import org.domnikl.data_structures.minBinaryHeap

fun <T : Comparable<T>> Array<T>.heapSort(): Array<T> {
    val binaryHeap = minBinaryHeap(*this)

    this.indices.forEach { i ->
        this[i] = binaryHeap.pop()!!
    }

    return this
}
