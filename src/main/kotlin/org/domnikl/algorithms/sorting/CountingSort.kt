package org.domnikl.algorithms.sorting

fun IntArray.countingSort(): IntArray {
    val min = this.min()!!
    val max = this.max()!!
    val counts = IntArray(max - min + 1)

    this.forEach { counts[it-min]++ }

    var pointer = 0
    counts.forEachIndexed { index, i ->
        repeat(i){
            this[pointer] = index+min
            pointer++
        }
    }

    return this
}
