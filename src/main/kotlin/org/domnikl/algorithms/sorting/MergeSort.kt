package org.domnikl.algorithms.sorting

fun <T : Comparable<T>> Array<T>.mergeSort(): Array<T> {
    if (this.size <= 1) {
        return this
    }

    val countLeft = this.size / 2

    val left = this.sliceArray(0 until countLeft).mergeSort()
    val right = this.sliceArray(countLeft until this.size).mergeSort()

    return this.merge(left, right)
}

private fun <T : Comparable<T>> Array<T>.merge(l: Array<T>, r: Array<T>): Array<T> {
    var i = 0
    var j = 0
    var k = 0

    while (i < l.size && j < r.size) {
        if (l[i] <= r[j]) {
            this[k] = l[i]
            i++
        } else {
            this[k] = r[j]
            j++
        }

        k++
    }

    while (i < l.size) {
        this[k] = l[i]
        i++
        k++
    }

    while (j < r.size) {
        this[k] = r[j]
        j++
        k++
    }

    return this
}
