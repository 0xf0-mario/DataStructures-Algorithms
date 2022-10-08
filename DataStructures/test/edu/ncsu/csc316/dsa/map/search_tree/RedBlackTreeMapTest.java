package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 *
 */
public class RedBlackTreeMapTest {
	/** BinarySearchTree used for testing that delegates to a redBlackTreeMap */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
 
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testTree() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        
        
        
        assertNull(tree.put(8, "eight"));
        assertEquals("eight", tree.get(8));
        assertEquals(1, tree.size());
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(15, "fifteen"));
        assertNull(tree.put(2, "two"));
        assertEquals("eight", tree.root().getElement().getValue());
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(17, "seventeen"));
        assertNull(tree.put(10, "ten"));
        assertNull(tree.put(9, "nine"));
        assertNull(tree.put(11, "eleven"));
        assertNull(tree.put(12, "twelve"));
        
        
        assertEquals("nine", tree.remove(9));
        assertEquals("fifteen", tree.remove(15));
        assertEquals("seventeen", tree.remove(17));
                
    }
}
