package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
/**
 * Test for the ShortestPathUtil class
 * checks all methods for appropriate output
 * @author Mario Medel
 *
 */
public class ShortestPathUtilTest {
	/** undirected graph used for testing */
	Graph<String, Highway> undirectedGraph;
//	/** directed graph used for testing */
//	Graph<String, Highway> directedGraph;
	/**
	 * initialize the graphs used within this test
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<String, Highway>();
//		directedGraph = new AdjacencyListGraph<String, Highway>();
	}
	/**
	 * Test for Dijkstra shortest path algorithm
	 */
	@Test public void testDijkstra() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, new Highway("RalToAsh", 5));
        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, new Highway("RalToWil", 10));
        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, new Highway("RalToDur", 15));
        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, new Highway("RalToGville", 20));
//        Edge<Highway> e5 = undirectedGraph.insertEdge(v2, v3, new Highway("AshToWil", 25));
//        Edge<Highway> e6 = undirectedGraph.insertEdge(v2, v4, new Highway("AshToDur", 30));
//        Edge<Highway> e7 = undirectedGraph.insertEdge(v2, v5, new Highway("AshToGVille", 35));
//        Edge<Highway> e8 = undirectedGraph.insertEdge(v3, v4, new Highway("WilToDur", 40));
//        Edge<Highway> e9 = undirectedGraph.insertEdge(v3, v5, new Highway("WilToGVille", 45));
//        Edge<Highway> e10 = undirectedGraph.insertEdge(v4, v5, new Highway("DurToGVille", 50));
//		
        Map<Vertex<String>, Integer> map = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        assertEquals((Integer) e1.getElement().getWeight(), map.get(v2));
        assertEquals((Integer) e2.getElement().getWeight(), map.get(v3));
        assertEquals((Integer) e3.getElement().getWeight(), map.get(v4));
        assertEquals((Integer) e4.getElement().getWeight(), map.get(v5));
        assertEquals(5, map.size());
        
	}
	/**
	 * uses dijsktra to test for shortestPathTree
	 */
	@Test public void testShortestPathTree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, new Highway("RalToAsh", 5));
        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, new Highway("RalToWil", 10));
        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, new Highway("RalToDur", 15));
        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, new Highway("RalToGville", 20));
		
        Map<Vertex<String>, Integer> map = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        assertEquals((Integer) e1.getElement().getWeight(), map.get(v2));
        assertEquals((Integer) e2.getElement().getWeight(), map.get(v3));
        assertEquals((Integer) e3.getElement().getWeight(), map.get(v4));
        assertEquals((Integer) e4.getElement().getWeight(), map.get(v5));
        assertEquals(5, map.size());
        
        Map<Vertex<String>, Edge<Highway>> map2 = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, map);
        assertEquals(4, map2.size());
        assertEquals(e1, map2.get(v2));
        assertEquals(e2, map2.get(v3));
        assertEquals(e3, map2.get(v4));
        assertEquals(e4, map2.get(v5));
	}

}
