package org.domnikl.data_structures

import java.util.BitSet
import java.util.Random
import kotlin.math.absoluteValue
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.roundToInt

class BloomFilter<T>(n: Int, m: Int = 1024 * 1024 * 8) {
    private val hashes = BitSet(m)
    private val numberOfHashes = max((ln(2.0) * m / n).roundToInt(), 1)
    private val random = Random()

    fun add(value: T) {
        randomBitSequence(value).forEach { hashes.set(it) }
    }

    operator fun contains(value: T): Boolean {
        randomBitSequence(value).firstOrNull { !hashes[it] } ?: return true

        return false
    }

    private fun randomBitSequence(value: T): List<Int> {
        random.setSeed(value.hashCode().toLong())

        return (0..numberOfHashes).map {
            random.nextInt().absoluteValue % hashes.size()
        }
    }
}
