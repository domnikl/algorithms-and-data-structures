package org.domnikl.algorithms.sorting

import kotlin.math.ceil

fun <T : Comparable<T>> Array<T>.bucketSort(bucketSize: Int = 8): Array<T> {
    val k = ceil(this.size / bucketSize.toDouble()).toInt()
    val max = this.max().hashCode()
    val buckets = Array(k) { mutableListOf<T>() }

    this.indices.forEach { i ->
        val key = kotlin.math.max(0, k * this[i].hashCode() / max - 1)

        buckets[key].add(this[i])
    }

    var i = 0

    buckets
        .map { e -> e.sortedBy { it }}
        .forEach {
            it.forEach { e ->
                this[i] = e
                i++
            }
        }

    return this
}
