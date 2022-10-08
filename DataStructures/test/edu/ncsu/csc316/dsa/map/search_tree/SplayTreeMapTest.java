package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
//import edu.ncsu.csc316.dsa.map.search_tree.*;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {
	/**BinarySearchTreeMap using the move to front heuristic by delegating to a SPLAY trees*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        //ZIG rotation from the left
        assertNull(tree.put(2, "two"));
        assertEquals(1, tree.size());
        assertEquals("two", tree.root().getElement().getValue());
        assertNull(tree.put(3, "three"));
        assertEquals(2, tree.size());
        assertEquals("three", tree.root().getElement().getValue());
        assertEquals("two", tree.left(tree.root()).getElement().getValue());
        //ZIG ZIG rotation (right)
        assertNull(tree.put(1, "one"));
        assertEquals(3, tree.size());
        assertEquals("one", tree.root().getElement().getValue());
        assertEquals("two", tree.right(tree.root()).getElement().getValue());
        assertNull(tree.left(tree.root()).getElement());
        assertEquals("three", tree.right(tree.right(tree.root())).getElement().getValue());
        assertNull(tree.left(tree.right(tree.root())).getElement());
        //ZIG ZIG rotation (left)
        assertNull(tree.put(5, "five"));
        assertEquals("five", tree.root().getElement().getValue());
        assertEquals("one", tree.left(tree.root()).getElement().getValue());
        assertNull(tree.left(tree.left(tree.root())).getElement());
        assertEquals("three", tree.right(tree.left(tree.root())).getElement().getValue());
        assertNull(tree.right(tree.right(tree.left(tree.root()))).getElement());
        assertEquals("two", tree.left(tree.right(tree.left(tree.root()))).getElement().getValue());
        assertEquals(4, tree.size());
        //ZIG ZAG rotation will be tested in the get method        
        
       
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	assertNull(tree.put(2, "two"));
    	assertNull(tree.put(3, "three"));
    	assertNull(tree.put(1, "one"));
    	assertNull(tree.put(5, "five"));
    	//ZIG ZAG left rotation and ZIG right
    	assertEquals("two", tree.get(2));
    	assertEquals("two", tree.root().getElement().getValue());
    	//get the root (no splay)
    	assertEquals("two", tree.get(2));
    	assertEquals("two", tree.root().getElement().getValue());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	assertNull(tree.put(2, "two"));
    	assertNull(tree.put(3, "three"));
    	assertNull(tree.put(1, "one"));
    	assertNull(tree.put(5, "five"));
    	assertEquals(4, tree.size());
    	//delete the root
    	assertEquals("five", tree.remove(5));
    	assertEquals("one", tree.root().getElement().getValue());
    	//remove a leaf
    	assertEquals("two", tree.remove(2));
    	assertEquals("three", tree.root().getElement().getValue());
    	//remove a node with a left child
    	assertEquals("three", tree.remove(3));
    	assertEquals("one", tree.root().getElement().getValue());
    	//removing the last element
    	assertEquals("one", tree.remove(1));
    	assertEquals(0, tree.size());

    }
}