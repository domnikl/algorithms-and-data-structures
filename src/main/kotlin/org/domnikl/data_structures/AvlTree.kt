package org.domnikl.data_structures

import kotlin.math.max

class AvlTree<K : Comparable<K>,V> {
    private var root: Node<K,V>? = null

    fun insert(key: K, value: V) {
        root = add(root, Node(key, value, 1))
    }

    fun search(key: K, visited: ((K) -> Unit)? = null): V {
        return search(key, root, visited)
    }

    private fun search(key: K, node: Node<K,V>?, visited: ((K) -> Unit)? = null): V {
        if (node == null) {
            throw IndexOutOfBoundsException("Could not find key '${key}'")
        }

        visited?.let { it(node.key) }

        if (key < node.key) {
            return search(key, node.left, visited)
        } else if (key > node.key) {
            return search(key, node.right, visited)
        }

        return node.value
    }

    private fun add(node: Node<K,V>?, newNode: Node<K,V>): Node<K,V>? {
        if (node == null) {
            return newNode
        }

        when {
            newNode.key < node.key -> {
                node.left = add(node.left, newNode)
            }
            newNode.key > node.key -> {
                node.right = add(node.right, newNode)
            }
            else -> {
                throw IllegalArgumentException("duplicated key '${newNode.key}' given")
            }
        }

        val heightLeft = node.leftHeight
        val heightRight = node.rightHeight
        val balance = node.balance

        val leftGreater = balance > 1
        val rightGreater = balance < -1

        node.height = 1 + max(heightLeft, heightRight)

        if (leftGreater && newNode.key < node.left!!.key) {
            return node.rightRotate()
        }

        if (rightGreater && newNode.key > node.right!!.key) {
            return node.leftRotate()
        }

        if (leftGreater && newNode.key > node.left!!.key) {
            node.left = node.left!!.leftRotate()

            return node.rightRotate()
        }

        if (rightGreater && newNode.key < node.right!!.key) {
            node.right = node.right!!.rightRotate()

            return node.leftRotate()
        }

        return node
    }

    private class Node<K : Comparable<K>,V>(val key: K, val value: V, var height: Int) {
        var left: Node<K,V>? = null
        var right: Node<K,V>? = null

        val leftHeight
            get() = left?.height ?: 0

        val rightHeight
            get() = right?.height ?: 0

        val balance
            get() = leftHeight - rightHeight

        override fun toString() = "$key"

        private fun updateHeight() {
            height = 1 + max(leftHeight, rightHeight)
        }

        fun leftRotate(): Node<K,V>? {
            val other = right ?: return this

            other.left = this.also { it.right = other.left }

            updateHeight()
            other.updateHeight()

            return other
        }

        fun rightRotate(): Node<K,V>? {
            val other = this.left ?: return this

            other.right = this.also { it.left = other.right }

            other.updateHeight()
            this.updateHeight()

            return other
        }
    }
}
