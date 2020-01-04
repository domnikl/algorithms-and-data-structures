package org.domnikl.data_structures

import kotlin.math.max

class AvlTree<K : Comparable<K>,V>(vararg init: Pair<K,V>) {
    private var root: Node<K,V>? = null

    init {
        init.forEach {
            insert(it.first, it.second)
        }
    }

    fun insert(key: K, value: V) {
        root = add(root, Node(key, value, 1))
    }

    fun search(key: K, visited: ((K) -> Unit)? = null): V? {
        return search(key, root) { visited?.invoke(it.key) }?.value
    }

    private fun search(key: K, node: Node<K,V>?, visited: ((Node<K,V>) -> Unit)? = null): Node<K,V>? {
        if (node == null) {
            return null
        }

        visited?.let { it(node) }

        if (key < node.key) {
            return search(key, node.left, visited)
        } else if (key > node.key) {
            return search(key, node.right, visited)
        }

        return node
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

    fun delete(key: K) {
        delete(key, root)
    }

    private fun delete(key: K, tree: Node<K,V>?) {
        if (tree == null) {
            return
        }

        val visited = mutableListOf<Node<K,V>>()
        val node = search(key, tree) {
            visited.add(it)
        } ?: return

        val parent = when {
            visited.size >= 2 -> visited.elementAt(visited.size - 2)
            node == tree -> tree
            else -> null
        }

        if (node.left == null && node.right == null) {
            if (node == root && tree == root) root = null else parent?.removeChild(node)
        } else if (node.left == null) {
            if (node == root && tree == root) root = node.right else parent?.replaceChild(node, node.right)
        } else if (node.right == null) {
            if (node == root && tree == root) root = node.left else parent?.replaceChild(node, node.left)
        } else {
            val min = node.right!!.min()
            val newNode = Node(min.key, min.value, node.height)
            newNode.left = node.left
            newNode.right = node.right
            newNode.height = node.height - 1

            if (node == root && tree == root) root = newNode else parent?.replaceChild(node, newNode)

            delete(min.key, node.right)
        }
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

        override fun equals(other: Any?) = if (other is Node<*,*>) other.key == key else false

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

            updateHeight()
            other.updateHeight()

            return other
        }

        fun removeChild(node: Node<K, V>) {
            if (right == node) {
                right = null
            } else if (left == node) {
                left = null
            }
        }

        fun replaceChild(node: Node<K, V>, with: Node<K,V>?) {
            if (right == node) {
                right = with
            } else if (left == node) {
                left = with
            }
        }

        fun min(): Node<K,V> {
            return if (left == null) {
                this
            } else {
                left!!.min()
            }
        }

        override fun hashCode() = key.hashCode()
    }
}

fun <K : Comparable<K>,V> avlTree(items: Map<K,V>): AvlTree<K,V> {
    return AvlTree(*items.map { Pair(it.key, it.value) }.toTypedArray())
}
