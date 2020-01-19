package org.domnikl.data_structures

import kotlin.math.max

@Suppress("UNCHECKED_CAST")
class ResizingArray<T>(private var capacity: Int) {
    var size = 0
        private set

    private var data: Array<Any?> = Array(capacity) { null }

    operator fun set(index: Int, value: T) {
        resizeIfNeeded()

        if (size > 0) {
            size.downTo(index).forEach {
                data[it + 1] = data[it]
            }
        }

        data[index] = value
        size++
    }

    operator fun get(index: Int): T? {
        return data[index] as T?
    }

    fun toTypedArray(): Array<T> {
        return data.sliceArray(0 until size) as Array<T>
    }

    fun delete(index: Int) {
        (index until size - 1).forEach {
            data[it] = data[it + 1]
        }

        data[size - 1] = null
        size--
    }

    private fun resizeIfNeeded() {
        if (size < capacity) {
            return
        }

        val newCapacity = capacity * 2
        val newArray: Array<Any?> = Array(newCapacity) { null }

        data.forEachIndexed { index, value ->
            newArray[index] = value
        }

        capacity = newCapacity
        data = newArray
    }

    companion object {
        fun <T> fromArray(data: Array<Any?>): ResizingArray<T> {
            val capacity = max(16, data.size)
            val x = ResizingArray<T>(capacity)
            data.copyInto(x.data)
            x.size = data.size

            return x
        }
    }
}

fun <T> resizingArray(vararg value: T): ResizingArray<T> {
    val a = Array<Any?>(value.size) { null }
    value.copyInto(a)

    return ResizingArray.fromArray(a)
}
