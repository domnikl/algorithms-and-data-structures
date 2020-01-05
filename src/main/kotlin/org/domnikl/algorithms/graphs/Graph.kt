package org.domnikl.algorithms.graphs

import org.domnikl.data_structures.LinkedList
import org.domnikl.data_structures.Queue
import org.domnikl.data_structures.Stack

class Graph<T : Any> {
    private val linkedLists = mutableMapOf<T, LinkedList<T>>()

    fun addEdge(from: T, to: T) {
        val l = linkedLists.getOrPut(from) { LinkedList() }
        l.addFirst(to)
    }

    fun addUndirectedEdge(a: T, b: T) {
        addEdge(a, b)
        addEdge(b, a)
    }

    fun breadthFirst(source: T): List<T> {
        val visited = mutableListOf<T>()
        val queue = Queue<T>()

        visited.add(source)
        queue.add(source)

        while (queue.size != 0) {
            val vertex = queue.remove()
            val connections = linkedLists[vertex]?.toList()

            connections?.forEach {
                if (it !in visited) {
                    visited.add(it)
                    queue.add(it)
                }
            }
        }

        return visited
    }

    fun depthFirst(source: T): List<T> {
        val visited = mutableListOf<T>()
        val stack = Stack<T>()

        stack.push(source)

        while (!stack.isEmpty()) {
            val vertex = stack.pop()
            visited.add(vertex)
            val connections = linkedLists[vertex]?.toList()

            connections?.forEach {
                if (it !in visited) {
                    stack.push(it)
                }
            }
        }

        return visited
    }
}
