package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.selectionSort(): Array<T> {
    val copy = this.clone()

    for (i in copy.indices) {
        var jMin = i

        for (j in (i + 1 until copy.size)) {
            if (copy[j] < copy[jMin]) {
                jMin = j
            }
        }

        if (jMin != i) {
            copy.swap(i, jMin)
        }
    }

    return copy
}

private fun <T> Array<T>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}
