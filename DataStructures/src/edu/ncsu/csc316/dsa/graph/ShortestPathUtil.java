package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        //new adaptable PQ to sort vertices in ascending order by current path cost (Q)
    	HeapAdaptablePriorityQueue<Integer, Vertex<V>> adaptableQ = new HeapAdaptablePriorityQueue<>();
    	//new map to track current known path costs (C)
    	Map<Vertex<V>, Integer> costs = new LinearProbingHashMap<>();
    	//new empty set of 'found' vertices (S)
    	Set<Vertex<V>> foundSet = new HashSet<>();
    	//new map to keep track of the associated APQ entry for each vertex (E) 
    	Map<Vertex<V>, Entry<Integer, Vertex<V>>> entries = new LinearProbingHashMap<>();
    	
    	for(Vertex<V> v : graph.vertices()) {
    		if( v == start )
    			costs.put(v, 0);
    		else
    			costs.put(v, Integer.MAX_VALUE);
    		entries.put(v, adaptableQ.insert(costs.get(v), v));
    	}
    	while(!adaptableQ.isEmpty()) {
    		Entry<Integer, Vertex<V>> entry = adaptableQ.deleteMin();
    		Vertex<V> u = entry.getValue();
    		foundSet.add(u);
    		for(Edge<E>	e : graph.outgoingEdges(u)) {
    			Vertex<V> z = graph.opposite(u, e);
    			int r = e.getElement().getWeight() + costs.get(u);
    			if(!foundSet.contains(z) && r < costs.get(z)) {
    				costs.put(z, r);
    				adaptableQ.replaceKey(entries.get(z), r);
    			}
    		}
    	}
    	return costs;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
    	// Create a map to store edges in the shortest path tree
    	Map<Vertex<V>, Edge<E>> shortestPathMap = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
    	for(Vertex<V> v : costs) {
    		if( v != start ) {
    			for(Edge<E> e : graph.incomingEdges(v)) {
    				Vertex<V> u = graph.opposite(v, e);
    				if(costs.get(v).equals(costs.get(u) + e.getElement().getWeight()))
    					shortestPathMap.put(v, e);	
    			}
    		}
    	}
    	return shortestPathMap;
    }
}
