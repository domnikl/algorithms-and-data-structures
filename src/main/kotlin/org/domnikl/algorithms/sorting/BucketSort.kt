package org.domnikl.algorithms.sorting

import kotlin.math.ceil
import kotlin.math.floor

fun <T : Comparable<T>> Array<T>.bucketSort(bucketSize: Int = 2): Array<T> {
    val k = ceil(this.size / bucketSize.toDouble()).toInt()
    val max = this.max()

    val buckets = arrayOfNulls<MutableList<T?>>(k)

    this.indices.forEach { i ->
        val bucketKey = kotlin.math.max(0, bucketKey(k, this[i], max) - 1)

        if (buckets[bucketKey] == null) {
            buckets[bucketKey] = mutableListOf()
        }

        buckets[bucketKey]?.add(this[i])
    }

    var i = 0

    buckets.filterNotNull().map { e ->
        e.filterNotNull().sortedBy { it }
    }.forEach {
        it.forEach { e ->
            this[i] = e
            i++
        }
    }

    return this
}

private fun <T> Array<out T>.max(): T? {
    if (this.isEmpty()) {
        return null
    }

    return when (this[0]) {
        is Number -> this.maxBy { (it as Number).toDouble() }
        else -> this.maxBy { it.hashCode() }
    }
}

private fun <T> bucketKey(k: Int, e: T, max: T): Int {
    return when (e) {
        is Number -> floor(k * e.toDouble() / (max as Number).toDouble()).toInt()
        else -> k * e.hashCode() / max.hashCode()
    }
}
