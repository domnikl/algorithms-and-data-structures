package org.domnikl.data_structures

@Suppress("UNCHECKED_CAST")
class ResizingArray<T>(private var capacity: Int) {
    var size = 0
        private set

    private var data: Array<Any?> = Array(capacity) { null }

    fun insert(index: Int, value: T) {
        resizeIfNeeded()

        size.downTo(index).forEach {
            data[it + 1] = data[it]
        }

        data[index] = value
        size++
    }

    fun delete(index: Int) {
        (index until size - 1).forEach {
            data[it] = data[it + 1]
        }

        data[size - 1] = null
        size--
    }

    fun get(index: Int): T? {
        return data[index] as T?
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
            val x = ResizingArray<T>(data.size)
            x.data = data
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
