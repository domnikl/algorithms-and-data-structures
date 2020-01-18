package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.quickSort(): Array<T> {
    return this.quickSort(0, this.size - 1)
}

private fun <T : Comparable<T>> Array<T>.quickSort(low: Int, high: Int): Array<T> {
    if (low < high) {
        val p = this.partition(low, high)
        this.quickSort(low, p)
        this.quickSort(p + 1, high)
    }

    return this
}

private fun <T : Comparable<T>> Array<T>.partition(low: Int, high: Int): Int {
    val pivot = this[low]
    var i = low - 1
    var j = high + 1

    while (true) {
        do {
            i++
        } while (this[i] < pivot)

        do {
            j--
        } while (this[j] > pivot)

        if (i >= j) {
            return j
        }

        this[i] = this[j].also { this[j] = this[i] }
    }
}
