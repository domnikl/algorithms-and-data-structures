package org.domnikl.data_structures

class BTree<K : Comparable<K>, V> {
    private var root = Node<K, V>(0)

    var height = 0
        private set

    var size = 0
        private set

    fun isEmpty() = size == 0

    operator fun get(key: K): V? {
        return search(root, key, height)
    }

    private fun search(tree: Node<K, V>?, key: K, height: Int): V? {
        val children = tree?.children

        if (height == 0) {
            children
                ?.firstOrNull { it?.key == key }
                ?.let { return it.value }
        } else {
            children?.forEachIndexed { j, c ->
                if (j + 1 == tree.size || key < children[j + 1]!!.key) {
                    return search(c?.next, key, height - 1)
                }
            }
        }

        return null
    }

    operator fun set(key: K, value: V) {
        insert(root, key, value, height)?.let { splitRoot(it) }
        size++
    }

    private fun splitRoot(newNode: Node<K, V>) {
        val newRoot = Node<K, V>(2)
        newRoot.children[0] = Entry(root.children[0]!!.key, null, root)
        newRoot.children[1] = Entry(newNode.children[0]!!.key, null, newNode)
        root = newRoot
        height++
    }

    private fun insert(tree: Node<K, V>?, key: K, value: V, height: Int): Node<K, V>? {
        var j = 0
        val newEntry = Entry(key, value)

        if (height == 0) {
            while (j < tree!!.size) {
                if (key < tree.children[j]!!.key) break
                j++
            }
        } else {
            while (j < tree!!.size) {
                if (j + 1 == tree.size || key < tree.children[j + 1]!!.key) {
                    val u = insert(tree.children[j++]!!.next, key, value, height - 1) ?: return null

                    newEntry.key = u.children[0]!!.key
                    newEntry.next = u
                    break
                }

                j++
            }
        }

        for (i in tree.size downTo j + 1) {
            tree.children[i] = tree.children[i - 1]
        }

        tree.children[j] = newEntry
        tree.size++

        return if (tree.size < CHILDREN) null else split(tree)
    }

    private fun split(node: Node<K, V>): Node<K, V> {
        val movedChildren = CHILDREN / 2
        val newNode = Node<K, V>(movedChildren)

        node.children.takeLast(movedChildren).forEachIndexed { i, child ->
            newNode.children[i] = child
        }

        node.size = CHILDREN - movedChildren

        return newNode
    }

    companion object {
        const val CHILDREN = 4
    }

    private class Node<K, V>(var size: Int) {
        val children = arrayOfNulls<Entry<K, V>>(CHILDREN)
    }

    private class Entry<K, V>(
      var key: K,
      val value: V?,
      var next: Node<K, V>? = null
    )
}
