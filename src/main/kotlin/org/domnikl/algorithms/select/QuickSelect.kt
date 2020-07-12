package org.domnikl.algorithms.select

import kotlin.random.Random

fun <T : Comparable<T>> Array<T>.quickSelect(select: Int): T {
    return this.quickSelect(0, this.size - 1, select)
}

private fun <T : Comparable<T>> Array<T>.quickSelect(low: Int, high: Int, select: Int): T {
    require(low <= high) {
        "low: $low is smaller than high: $high"
    }

    val pivotIndex = this.partition(low, high)

    return when {
        pivotIndex == select -> {
            this[pivotIndex]
        }

        select < pivotIndex -> {
            this.quickSelect(low, pivotIndex - 1, select)
        }
        else -> {
            this.quickSelect(pivotIndex + 1, high, select)
        }
    }
}

private fun <T : Comparable<T>> Array<T>.partition(low: Int, high: Int): Int {
    require(low <= high) {
        "low: $low is smaller than high: $high"
    }

    var pivotIndex = Random.nextInt(low, high + 1)
    val pivot = this[pivotIndex]
    var i = low
    var j = high

    while (true) {
        while (this[i] < pivot) {
            i++
        }

        while (this[j] > pivot) {
            j--
        }

        if (i >= j) {
            return pivotIndex
        }

        this[i] = this[j].also { this[j] = this[i] }

        if (pivotIndex == i) {
            pivotIndex = j
        } else if (pivotIndex == j) {
            pivotIndex = i
        }
    }
}
