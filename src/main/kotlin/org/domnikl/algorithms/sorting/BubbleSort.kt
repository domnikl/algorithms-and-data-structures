package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.bubbleSort(): Array<T> {
    val copy = this.clone()
    var swapped: Boolean

    do {
        swapped = false

        (1 until this.size).forEach {
            if (copy[it - 1] > copy[it]) {
                val bubbleA = copy[it - 1]
                val bubbleB = copy[it]

                copy[it - 1] = bubbleB
                copy[it] = bubbleA

                swapped = true
            }
        }
    } while (swapped)

    return copy
}
