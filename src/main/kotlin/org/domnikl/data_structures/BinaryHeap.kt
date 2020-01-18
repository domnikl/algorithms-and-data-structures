package org.domnikl.data_structures

@Suppress("UNCHECKED_CAST")
sealed class BinaryHeap<T : Comparable<T>>(vararg elements: T?) {
    var size = 0
        private set

    private var capacity = 16

    private var data: Array<Any?> = Array(capacity) { null }

    init {
        elements.forEach { e ->
            e?.let { insert(it) }
        }
    }

    fun insert(element: T) {
        if (size == capacity) {
            resize()
        }

        data[size] = element
        size++
        heapifyUp()
    }

    private fun heapifyUp() {
        var index = size - 1

        while (hasParent(index) && this.compare(parent(index)!!, this[index]!!)) {
            swap(parentIndex(index), index)
            index = parentIndex(index)
        }
    }

    private fun heapifyDown() {
        var index = 0

        while (hasLeftChild(index)) {
            var smallerChildIndex = leftChildIndex(index)

            if (hasRightChild(index) && !compare(rightChild(index)!!, leftChild(index)!!)) {
                smallerChildIndex = rightChildIndex(index)
            }

            if (!compare(this[index]!!, this[smallerChildIndex]!!)) {
                break
            } else {
                swap(index, smallerChildIndex)
            }

            index = smallerChildIndex
        }
    }

    private operator fun get(index: Int): T? {
        return data[index] as T?
    }

    private fun swap(index: Int, with: Int) {
        val temp = data[index]
        data[index] = data[with]
        data[with] = temp
    }

    private fun resize() {
        data = data.copyOf(capacity * 2)
        capacity *= 2
    }

    fun pop(): T? {
        if (size == 0) return null

        return this[0]?.also {
            data[0] = data[size - 1]
            size--
            heapifyDown()
        }
    }

    fun toList(): List<T> {
        val x = mutableListOf<T>()

        do {
            val b = pop()?.also { x.add(it) }
        } while (b != null)

        return x.toList()
    }

    private fun leftChildIndex(parentIndex: Int) = 2 * parentIndex + 1
    private fun rightChildIndex(parentIndex: Int) = 2 * parentIndex + 2
    private fun parentIndex(childIndex: Int) = (childIndex - 1) / 2

    private fun hasLeftChild(index: Int) = leftChildIndex(index) < size
    private fun hasRightChild(index: Int) = rightChildIndex(index) < size
    private fun hasParent(index: Int) = parentIndex(index) >= 0

    private fun leftChild(index: Int) = data[leftChildIndex(index)] as T?
    private fun rightChild(index: Int) = data[rightChildIndex(index)] as T?
    private fun parent(index: Int) = data[parentIndex(index)] as T?

    protected abstract fun compare(a: T, b: T): Boolean
}

class MaxBinaryHeap<T : Comparable<T>>(vararg elements: T?) : BinaryHeap<T>(*elements) {
    override fun compare(a: T, b: T) = a < b
}

class MinBinaryHeap<T : Comparable<T>>(vararg elements: T?) : BinaryHeap<T>(*elements) {
    override fun compare(a: T, b: T) = a > b
}

fun <T : Comparable<T>> maxBinaryHeap(vararg elements: T?): BinaryHeap<T> {
    return MaxBinaryHeap(*elements)
}

fun <T : Comparable<T>> minBinaryHeap(vararg elements: T?): BinaryHeap<T> {
    return MinBinaryHeap(*elements)
}
