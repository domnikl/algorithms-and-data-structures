package org.domnikl.algorithms.graphs

import org.junit.Assert.assertEquals
import org.junit.Test

class WeightedGraphTest {
    @Test
    fun `can find shortest path in graph`() {
        val graph = WeightedGraph<String>()

        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 3.0)
        graph.addEdge("B", "G", 4.0)
        graph.addEdge("B", "D", 6.0)
        graph.addEdge("G", "D", 3.0)
        graph.addEdge("D", "E", 3.0)
        graph.addEdge("C", "E", 8.0)

        val result = graph.dijkstra("A", "E")

        assertEquals(10.0, result.weight, 0.0)
        assertEquals(listOf("A", "B", "D", "E"), result.nodes)
    }
}
