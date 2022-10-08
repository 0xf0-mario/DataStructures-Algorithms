package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree.BinaryTreeNode;


/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Mario Medel
 *
 */
public class BinarySearchTreeMapTest {
	/** Binary Search Tree representing the Map ADT for out test cases */
    private BinarySearchTreeMap<Integer, String> tree;
    
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() { 
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
 
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "A");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(2, "B");
        assertEquals(2, tree.size());
        tree.put(3, "C");
        assertEquals(3, tree.size());
        tree.put(4, "D");
        assertEquals(4, tree.size());
        assertEquals("A", tree.root().getElement().getValue());
        
        assertEquals(0, tree.getProperty(null));
        Position<Entry<Integer, String>> entry = tree.root();
        tree.setProperty(entry, 1);
        assertEquals(1, tree.getProperty(entry));
        assertTrue(tree.isRoot(entry));
        
//        assertEquals("BalanceableBinaryTree[\n"
//        		+ "edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@4667ae56\n"
//        		+ " edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@464bee09\n"
//        		+ "  edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@f6c48ac\n"
//        		+ "   edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@13deb50e\n"
//        		+ "]", tree.toString());
        
        //all nodes are leaning to the right so we must restructure d
        BinaryTreeNode<Entry<Integer, String>> res = (BinaryTreeNode<Entry<Integer, String>>)tree.right(tree.right(
        		tree.right(tree.root())));
//        assertEquals("edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@13deb50e", res.getElement().toString());
        //goal is to make res the new root
        assertFalse(tree.isRoot(res));
        assertTrue(res instanceof BinaryTreeNode);
        tree.restructure(res);
        //entry set should be in order
        //[a,b,c,d]
        Iterable<Entry<Integer, String>> it = tree.entrySet();
        String inOrder[] = new String[4];
        for(Entry<Integer, String> e : it) {
        	inOrder[e.getKey() - 1] = e.getValue();
        }
        assertEquals("A", inOrder[0]);
        assertEquals("B", inOrder[1]);
        assertEquals("C", inOrder[2]);
        assertEquals("D", inOrder[3]);
        
//        assertEquals("BalanceableBinaryTree[\n"
//        		+ "edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@4667ae56\n"
//        		+ " edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@f6c48ac\n"
//        		+ "  edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@464bee09\n"
//        		+ "  edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@13deb50e\n"
//        		+ "]", tree.toString());
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        
        assertNull(tree.get(2));
        
        assertEquals("one", tree.put(1, "A"));
        assertEquals(1, tree.size());
        assertEquals("A", tree.get(1));
        
        assertNull(tree.put(2, "B"));
        assertEquals(2, tree.size());
        assertEquals("B", tree.put(2, "B2"));
        assertEquals("B2", tree.get(2));
        
        tree.put(3, "C");
        assertEquals("C", tree.get(3));
        assertEquals(3, tree.size());
        tree.put(4, "D");
        assertEquals("D", tree.get(4));
        assertEquals(4, tree.size());
        
        
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        
        tree.put(3, "c");
        tree.put(5, "e");
        tree.put(2, "b");
        tree.put(1, "a");
        tree.put(4, "d");
        tree.put(6, "f");
        assertEquals(6, tree.size());
       
        //expected Structure for in order [1,2,3,4,5,6]

        
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(4, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
        //remove a node that has a right child
        assertEquals("b", tree.remove(2));
        //the root's left child should be one now
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(5, tree.size());
        //remove a node that has two children
        assertEquals("e", tree.remove(5));
        //the new right child of the root should be 6 (in order successor)
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(4, tree.size());
        //remove a node that has no children
        assertEquals("d", tree.remove(4));
        assertEquals(3, tree.size());
        //the right child of the root should now have no children
        assertNull(tree.left(tree.right(tree.root())).getElement());
        assertNull(tree.right(tree.right(tree.root())).getElement());
        //left child of the root should still be one
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertNull(tree.right(tree.left(tree.root())).getElement());
        assertNull(tree.left(tree.left(tree.root())).getElement());
        assertEquals(3, tree.size());
        assertEquals("a", tree.remove(1));
        assertEquals(2, tree.size());
        //removing the root node
        assertEquals("c", tree.remove(3));
        assertEquals(1, tree.size());
        //the new root should be 6
        assertEquals("f", tree.root().getElement().getValue());
        //removing the last element
        assertEquals("f", tree.remove(6));
        assertEquals(0, tree.size());
    }
}

