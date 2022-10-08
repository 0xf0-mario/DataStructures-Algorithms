package edu.ncsu.csc316.dsa.graph;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
/**
 * Test for the two main graph traversals
 * checks for all paths 
 * @author Mario Medel (mamedelp)
 *
 */
public class GraphTraversalUtilTest {
	/** undirected graph used for testing */
	Graph<String, Integer> undirectedGraph;
	/** directed graph used for testing */
	Graph<String, Integer> directedGraph;
	/**
	 * setup before each tests
	 */
	@Before 
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<String, Integer>();
		directedGraph = new AdjacencyListGraph<String, Integer>();
	}

	/**
	 * Test for depth first search 
	 */
	@Test
	public void depthFirsttest() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Map<Vertex<String>, Edge<Integer>> undirectedSearch = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
        //graph should contain edges: e1, e5, e8, and e10
        assertEquals(e1, undirectedSearch.get(v2));
        assertEquals(e5, undirectedSearch.get(v3));
        assertEquals(e8, undirectedSearch.get(v4));
        assertEquals(e10, undirectedSearch.get(v5));
        
        assertNull(undirectedSearch.get(v1));
        assertEquals(4, undirectedSearch.size());
        
        Iterator<Entry<Vertex<String>, Edge<Integer>>> entrySet = undirectedSearch.entrySet().iterator();
        while(entrySet.hasNext()) {
        	Edge<Integer> temp = entrySet.next().getValue();
        	assertNotEquals(e4, temp);
        	assertNotEquals(e2, temp);
        	assertNotEquals(e3, temp);
        	assertNotEquals(e6, temp);
        	assertNotEquals(e7, temp);
        	assertNotEquals(e9, temp);
        }
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Map<Vertex<String>, Edge<Integer>> directedSearch = GraphTraversalUtil.depthFirstSearch(directedGraph, v1);
        //should contain edges: e1, e5, e8, e10, and e11
        assertEquals(e1, directedSearch.get(v2));
        assertEquals(e5, directedSearch.get(v3));
        assertEquals(e8, directedSearch.get(v4));
        assertEquals(e10, directedSearch.get(v5));
        assertEquals(e11, directedSearch.get(v6));
        
        assertNull(directedSearch.get(v1));
        assertEquals(5, directedSearch.size());
        
        Iterator<Entry<Vertex<String>, Edge<Integer>>> entrySet2 = directedSearch.entrySet().iterator();
        while(entrySet2.hasNext()) {
        	Edge<Integer> temp = entrySet2.next().getValue();
        	assertNotEquals(e4, temp);
        	assertNotEquals(e2, temp);
        	assertNotEquals(e3, temp);
        	assertNotEquals(e6, temp);
        	assertNotEquals(e7, temp);
        	assertNotEquals(e9, temp);
        }
	}
	
	/**
	 * Test for breadth first search
	 * checks traversal for directed and undirected graph
	 */
	@Test public void breadthFirstTest() 
	{
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Map<Vertex<String>, Edge<Integer>> undirectedSearch = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
        //graph should contain edges: e1, e2, e3, and e4
        assertEquals(e1, undirectedSearch.get(v2));
        assertEquals(e2, undirectedSearch.get(v3));
        assertEquals(e3, undirectedSearch.get(v4));
        assertEquals(e4, undirectedSearch.get(v5));
        
        assertNull(undirectedSearch.get(v1));
        assertEquals(4, undirectedSearch.size());
        
        Iterator<Entry<Vertex<String>, Edge<Integer>>> entrySet = undirectedSearch.entrySet().iterator();
        while(entrySet.hasNext()) {
        	Edge<Integer> temp = entrySet.next().getValue();
        	assertNotEquals(e5, temp);
        	assertNotEquals(e6, temp);
        	assertNotEquals(e7, temp);
        	assertNotEquals(e8, temp);
        	assertNotEquals(e9, temp);
        	assertNotEquals(e10, temp);
        }
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Map<Vertex<String>, Edge<Integer>> directedSearch = GraphTraversalUtil.breadthFirstSearch(directedGraph, v1);
        //should contain edges: e1, e2, e3, e4, and e11
        assertEquals(e1, directedSearch.get(v2));
        assertEquals(e2, directedSearch.get(v3));
        assertEquals(e3, directedSearch.get(v4));
        assertEquals(e4, directedSearch.get(v5));
        assertEquals(e11, directedSearch.get(v6));
        
        assertNull(directedSearch.get(v1));
        assertEquals(5, directedSearch.size());
        
        Iterator<Entry<Vertex<String>, Edge<Integer>>> entrySet2 = directedSearch.entrySet().iterator();
        while(entrySet2.hasNext()) {
        	Edge<Integer> temp = entrySet2.next().getValue();
        	assertNotEquals(e5, temp);
        	assertNotEquals(e6, temp);
        	assertNotEquals(e7, temp);
        	assertNotEquals(e8, temp);
        	assertNotEquals(e9, temp);
        	assertNotEquals(e10, temp);
        }
	}

}
