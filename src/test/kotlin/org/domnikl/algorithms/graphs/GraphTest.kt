package org.domnikl.algorithms.graphs

import org.junit.Assert.assertEquals
import org.junit.Test

class GraphTest {
    @Test
    fun `can search breadth first`() {
        val graph = Graph<Int>()

        graph.addEdge(0, 1)
        graph.addEdge(0, 3)
        graph.addEdge(0, 4)
        graph.addEdge(1, 2)
        graph.addEdge(3, 4)
        graph.addEdge(5, 6)

        val visited = graph.breadthFirst(0)

        assertEquals(listOf(0, 4, 3, 1, 2), visited)
    }

    @Test
    fun `can search depth first`() {
        val graph = Graph<String>()

        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("D", "E")
        graph.addEdge("A", "E")

        val visited = graph.depthFirst("A")

        assertEquals(listOf("A", "E", "B", "C"), visited)
    }
}
