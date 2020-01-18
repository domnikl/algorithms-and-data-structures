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
    var i = 0
    var j = 0
    var k = 0

    val results = this + other

    while (i < this.size && j < other.size) {
        if (this[i]<= other[j]) {
            results[k] = this[i]
            i++
        } else {
            results[k] = other[j]
            j++
        }

        k++
    }

    while (i < this.size) {
        results[k] = this[i]
        i++
        k++
    }

    while (j < other.size) {
        results[k] = other[j]
        j++
        k++
    }

    return results
}
