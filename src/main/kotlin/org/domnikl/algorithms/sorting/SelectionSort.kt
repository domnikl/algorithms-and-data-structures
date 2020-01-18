package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.selectionSort(): Array<T> {
    for (i in this.indices) {
        var jMin = i

        for (j in (i + 1 until this.size)) {
            if (this[j] < this[jMin]) {
                jMin = j
            }
        }

        if (jMin != i) {
            this.swap(i, jMin)
        }
    }

    return this
}

private fun <T> Array<T>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}
