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

    fun breadthFirst(source: T): List<T> {
        val visited = mutableMapOf<T,Boolean>()
        val queue = Queue<T>()

        visited[source] = true
        queue.add(source)

        while (queue.size != 0) {
            val vertex = queue.remove()
            val connections = linkedLists[vertex]?.toList()

            connections?.forEach {
                if (!visited.getOrPut(it) { false }) {
                    visited[it] = true
                    queue.add(it)
                }
            }
        }

        return visited.filter { it.value }.map { it.key }
    }

    fun depthFirst(source: T): List<T> {
        val visited = mutableMapOf<T,Boolean>()
        val stack = Stack<T>()

        visited[source] = true
        stack.push(source)

        while (!stack.isEmpty()) {
            val vertex = stack.pop()
            val connections = linkedLists[vertex]?.toList()

            connections?.forEach {
                if (!visited.getOrPut(it) { false }) {
                    visited[it] = true
                    stack.push(it)
                }
            }
        }

        return visited.filter { it.value }.map { it.key }
    }
}
