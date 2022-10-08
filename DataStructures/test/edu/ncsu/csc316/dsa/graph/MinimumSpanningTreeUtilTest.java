package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
/**
 * Test for MinimumSpanningTreeUtil
 * test all available public methods
 * within the class
 * @author Mario Medel (mamedelp)
 *
 */
public class MinimumSpanningTreeUtilTest {
	/** undirected graph used for testing */
	Graph<String, Highway> undirectedGraph;
	/**
	 * sets up graphs before testing
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<String, Highway>();
	}
	/**
	 * Test for Kruskal algorithm
	 * test to make sure we have the minimum spanning tree
	 */
	@Test
	public void testKruskal() {
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
        
        PositionalList<Edge<Highway>> list = MinimumSpanningTreeUtil.kruskal(undirectedGraph); 
        assertEquals(4, list.size());
        Iterator<Edge<Highway>> it = list.iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        assertFalse(it.hasNext());
        
        
	}
	/**
	 * Test for PrimJarnik algorithm
	 * test to make sure we have the min spanning tree
	 */
	@Test
	public void testPrimJarnik() {
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
        
        PositionalList<Edge<Highway>> list = MinimumSpanningTreeUtil.primJarnik(undirectedGraph); 
        assertEquals(4, list.size());
        Iterator<Edge<Highway>> it = list.iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        assertFalse(it.hasNext());
	}

}
