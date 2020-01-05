package org.domnikl.algorithms.graphs

import org.domnikl.data_structures.LinkedList
import org.domnikl.data_structures.Queue

class Graph<T : Any> {
    private val linkedLists = mutableMapOf<T, LinkedList<T>>()

    fun addEdge(x : T, y : T) {
        val l = linkedLists.getOrPut(x) { LinkedList() }
        l.addFirst(y)
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
}
