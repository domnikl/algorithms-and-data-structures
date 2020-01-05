package org.domnikl.algorithms.graphs

import org.junit.Assert.assertEquals
import org.junit.Test

class GraphTest {
    @Test
    fun `can search breadth first`() {
        val graph = Graph<Int>()

        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(3, 4)

        val visited = graph.breadthFirst(0)

        assertEquals(listOf(0, 1, 2), visited)
    }
}
