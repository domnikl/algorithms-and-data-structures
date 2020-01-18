package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.insertionSort(): Array<T> {
    (1 until this.size).forEach { i ->
        var j = i

        while (j > 0 && this[j - 1] > this[j]) {
            this.swap(j, j - 1)
            j--
        }
    }

    return this
}

private fun <T> Array<T>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}
