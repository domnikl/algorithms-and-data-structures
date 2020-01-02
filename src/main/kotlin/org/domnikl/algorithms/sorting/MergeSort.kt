package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.mergeSort(): Array<T> {
    if (this.size <= 1) {
        return this
    }

    val countLeft = this.size / 2

    val left = this.sliceArray(0 until countLeft).mergeSort()
    val right = this.sliceArray(countLeft until this.size).mergeSort()

    return left.merge(right)
}

private fun <T : Comparable<T>> Array<T>.merge(other: Array<T>): Array<T> {
    var left = this
    var right = other
    var index = 0
    val result = left + right

    while (left.isNotEmpty() && right.isNotEmpty()) {
        if (left[0] <= right[0]) {
            result[index++] = left[0]
            left = left.sliceArray(1 until left.size)
        } else {
            result[index++] = right[0]
            right = right.sliceArray(1 until right.size)
        }

        while (left.isNotEmpty()) {
            result[index++] = left[0]
            left = left.sliceArray(1 until left.size)
        }

        while (right.isNotEmpty()) {
            result[index++] = right[0]
            right = right.sliceArray(1 until right.size)
        }
    }

    return result
}
