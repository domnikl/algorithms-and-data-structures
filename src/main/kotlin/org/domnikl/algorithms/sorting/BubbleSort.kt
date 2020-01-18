package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.bubbleSort(): Array<T> {
    var swapped: Boolean

    do {
        swapped = false

        (1 until this.size).forEach {
            if (this[it - 1] > this[it]) {
                val bubbleA = this[it - 1]
                val bubbleB = this[it]

                this[it - 1] = bubbleB
                this[it] = bubbleA

                swapped = true
            }
        }
    } while (swapped)

    return this
}
