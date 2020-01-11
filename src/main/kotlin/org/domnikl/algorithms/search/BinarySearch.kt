package org.domnikl.algorithms.search

fun <T : Comparable<T>> Array<T>.binarySearch(element: T): Int? {
    var left = 0
    var right = this.size - 1

    while (left <= right) {
        val compare = (left + right) / 2

        when {
            element > this[compare] -> left = compare + 1
            element < this[compare] -> right = compare - 1
            else -> return compare
        }
    }

    return null
}
