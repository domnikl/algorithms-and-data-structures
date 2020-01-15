package org.domnikl.algorithms.graphs

import org.domnikl.data_structures.LinkedList

class WeightedGraph<T : Any> {
    private val graph = mutableMapOf<T, LinkedList<Edge<T>>>()

    fun addEdge(from: T, to: T, weight: Double) {
        graph.getOrPut(from) { LinkedList() }.addFirst(Edge(to, weight))
        graph.getOrPut(to) { LinkedList() }
    }

    private fun adjacentVertices(element: T): List<Edge<T>> {
        return graph[element]?.toList() ?: emptyList()
    }

    fun dijkstra(from: T, destination: T): ShortestPath<T> {
        var current = from
        val unvisited = graph.keys
        val distances = graph.map { it.key to Double.POSITIVE_INFINITY }.toMap().toMutableMap()
        val paths = mutableMapOf<T, List<Edge<T>>>()

        distances[from] = 0.0

        while (unvisited.isNotEmpty() && unvisited.contains(destination)) {
            adjacentVertices(current).forEach { edge ->
                if (distances[current]!! + edge.weight < distances[edge.to]!!) {
                    distances[edge.to] = distances[current]!! + edge.weight
                    paths[edge.to] = paths.getOrDefault(current, listOf(Edge(current, 0.0))) + listOf(edge)
                }
            }

            unvisited.remove(current)

            if (current == destination || unvisited.all { distances[it]!!.isInfinite() }) {
                break
            }

            if (unvisited.isNotEmpty()) {
                current = unvisited.minBy { distances[it]!! }!!
            }
        }

        val shortestPath = paths[destination]!!

        return ShortestPath(
            shortestPath.map { it.to },
            shortestPath.sumBy { it.weight }
        )
    }

    data class ShortestPath<T>(val nodes: List<T>, val weight: Double)

    private data class Edge<T>(val to: T, val weight: Double)

    private fun List<Edge<T>>.sumBy(selector: (Edge<T>) -> Double): Double {
        return this.fold(0.0) { r, t -> r + selector(t) }
    }
}
