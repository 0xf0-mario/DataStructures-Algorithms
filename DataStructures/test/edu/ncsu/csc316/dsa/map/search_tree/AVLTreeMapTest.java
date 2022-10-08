package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {
	/** BinarySearchTreeMap that delegates to an AVL tree for our tests */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(8, "eight"));
        assertEquals(1, tree.size());
        assertEquals(8, (int)tree.root().getElement().getKey());
        
        //attempt to get the following configuration for a zig in one direction and then a zig in the other
        assertNull(tree.put(4,  "four"));
        assertEquals(2, tree.size());
        assertNull(tree.put(2, "two"));
        //above was a right zig rotation from 4 so t should be the new root node
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(8, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        	
        //in order to do the zig rotation in the opposite direction we will now need 
        //to add two more elements that are greater than 4
        assertNull(tree.put(10, "ten"));
        assertNull(tree.put(12, "twelve"));
        assertEquals(5, tree.size());
        //the new right of root should now be 10 
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(8, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        //the root maintains and its left child should still be the same
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        
        //inserting a nine will result in a zig zag rotation
        assertNull(tree.put(9, "nine"));
        assertEquals(6, tree.size());
        //new root is now 8
        assertEquals(8, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(2, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(9, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        tree.put(8, "8");
        tree.put(4, "4");
        tree.put(2, "2");
        assertEquals("8", tree.get(8));
        assertEquals("2", tree.get(2));
        assertEquals("4", tree.get(4));
        assertNull(tree.get(34289));
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        tree.put(10, "ten");
        assertEquals(10, (int)tree.root().getElement().getKey());
        tree.put(5, "five");
        tree.put(1, "one");
        assertEquals(5, (int)tree.root().getElement().getKey());
        tree.put(4, "four");
        tree.put(2, "two");
        assertEquals(5, tree.size());
        tree.put(12, "twelve");
        tree.put(8,  "eight");
        assertEquals(7, tree.size());
        tree.put(3, "three");
        tree.put(9, "nine");
        	
//        			5
//        		   / \
//        		  2    10
//        		 / \   /  \
//        	    1  4   8   12
//        	      /     \
//        	      3      9
        
        //remove a node with two children
        assertEquals("two", tree.remove(2));
        //remove the root
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals("five", tree.remove(5));
        assertEquals(7, tree.size());
        //remove a node with a left child and the node is a left child
        assertEquals("three", tree.remove(3));
        //tree now
//		    8
//		   / \
//		  4    10
//		 /    /  \
//	    1     9   12
        assertEquals(8, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(9, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        //remove a leaf
        assertEquals("one", tree.remove(1));
        assertEquals("four", tree.remove(4));
        assertEquals("ten", tree.root().getElement().getValue());
        assertEquals(4, tree.size());
        //remove a node with a right child and the node is a left child
        assertEquals("eight", tree.remove(8));
        assertEquals("ten", tree.remove(10));
        assertEquals("twelve", tree.root().getElement().getValue());
        assertEquals("twelve", tree.remove(12));
        assertEquals("nine", tree.root().getElement().getValue());
        assertEquals(1, tree.size()); 
        //remove the last node
        assertEquals("nine", tree.remove(9));
        assertEquals(0, tree.size());
             
    }
}