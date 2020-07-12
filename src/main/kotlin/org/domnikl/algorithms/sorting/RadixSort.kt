package org.domnikl.algorithms.sorting

import kotlin.math.ceil
import kotlin.math.log10
import kotlin.math.pow

fun IntArray.radixSort(): IntArray {
    if (this.isEmpty()) return this
    require(this.min()!! >= 0) { "number smaller zero not allowed" }
    val base = ceil(log10(this.max()!!.toDouble())).toInt()
    repeat(base + 1) {
        countingSort(10.0.pow(it).toInt())
    }
    return this
}

private fun IntArray.countingSort(exp: Int) {
    val counts = IntArray(10)
    val output = IntArray(this.size)

    this.forEach { counts[(it / exp) % 10]++ }

    (1 until counts.size).forEach {
        counts[it] += counts[it - 1]
    }

    this.indices.reversed().forEach {
        val index = this[it] / exp
        output[counts[index % 10] - 1] = this[it]
        counts[index % 10]--
    }

    output.forEachIndexed { index, i ->
        this[index] = i
    }
}
