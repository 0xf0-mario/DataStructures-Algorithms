package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {
	/** LinkedBinaryTree of strings used for testing */
    private LinkedBinaryTree<String> tree;
    /** position one of string in tree */
    private Position<String> one;
    /** position two of string in tree */
    private Position<String> two;
    /** position three of string in tree */
    private Position<String> three;
    /** position four of string in tree */
    private Position<String> four;
    /** position five of string in tree */
    private Position<String> five;
    /** position six of string in tree */
    private Position<String> six;
    /** position seven of string in tree */
    private Position<String> seven;
    /** position 8 of string in tree */
    private Position<String> eight;
    /** position 9 of string in tree */
    private Position<String> nine;
    /** position 10 of string in tree */
    private Position<String> ten;
    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test(expected = IllegalArgumentException.class)
    public void testSet() {
        createTree();
//        			one
//*                /        \
//*             two          three
//*            /   \            /
//*         six   ten          four
//*              /   \        /     \
//*            seven  five  eight nine 
        
        //test setting the root 
        assertEquals("one", tree.set(one, "1"));
        //test setting some leaf nodes
        assertEquals("seven", tree.set(seven, "7"));
        assertEquals("nine", tree.set(nine, "9"));
        assertEquals("six", tree.set(six, "6"));
        //test setting some internal nodes
        assertEquals("two", tree.set(two, "2"));
        assertEquals("three", tree.set(three, "3"));
        assertEquals("ten", tree.set(ten, "10"));
        //test setting a node that has already been set
        assertEquals("1", tree.set(one, "one"));
        assertEquals("7", tree.set(seven, "seven"));
        assertEquals("9", tree.set(nine, "nine"));
        assertEquals("6", tree.set(six, "six"));
        assertEquals("2", tree.set(two, "two"));
        
        tree.addRoot("testing to see if it will change the root");
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
//		one
//*                /        \
//*             two          three
//*            /   \            /
//*         six   ten          four
//*              /   \        /     \
//*            seven  five  eight nine 
        createTree();
        //check the number of children for the root
        assertEquals(2, tree.numChildren(one));
        //check the number of children for leaf nodes
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        //check the number of children for internal nodes
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(two));
        assertEquals(2, tree.numChildren(ten));
        assertEquals(2, tree.numChildren(four));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
//		one
//*                /        \
//*             two          three
//*            /   \            /
//*         six   ten          four
//*              /   \        /     \
//*            seven  five  eight nine 
        createTree();
        assertNull(tree.parent(one));
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        assertEquals(two, tree.parent(six));
        assertEquals(two, tree.parent(ten));
        assertEquals(three, tree.parent(four));
        assertEquals(ten, tree.parent(seven));
        assertEquals(ten, tree.parent(five));
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
//        assertNull(tree.sibling(one));
        //each node only has one more sibling based on its parent
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        
        assertEquals(ten, tree.sibling(six));
        assertEquals(six, tree.sibling(ten));
        
        //four should have no sibling
        assertNull(tree.sibling(four));
        
        assertEquals(seven, tree.sibling(five));
        assertEquals(five, tree.sibling(seven));
        
        assertEquals(eight, tree.sibling(nine));
        assertEquals(nine, tree.sibling(eight));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
//		one
//*                /        \
//*             two          three
//*            /   \            /
//*         six   ten          four
//*              /   \        /     \
//*            seven  five  eight nine 
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(ten));
        assertTrue(tree.isInternal(four));
        
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
        
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(ten));
        assertFalse(tree.isLeaf(four));
        
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(ten));
        
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        //expecting(one, two, six, ten, seven, five, three, four, eight, nine)
        Iterator<Position<String>> preOrder = tree.preOrder().iterator();
        assertEquals("one", preOrder.next().getElement());
        assertEquals("two", preOrder.next().getElement());
        assertEquals("six", preOrder.next().getElement());
        assertEquals("ten", preOrder.next().getElement());
        assertEquals("seven", preOrder.next().getElement());
        assertEquals("five", preOrder.next().getElement());
        assertEquals("three", preOrder.next().getElement());
        assertEquals("four", preOrder.next().getElement());
        assertEquals("eight", preOrder.next().getElement());
        assertEquals("nine", preOrder.next().getElement());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        // expecting(six, seven, five, ten, two, eight, nine, four, three, one)
        Iterator<Position<String>> postOrder = tree.postOrder().iterator();
        assertEquals("six", postOrder.next().getElement());
        assertEquals("seven", postOrder.next().getElement());
        assertEquals("five", postOrder.next().getElement());
        assertEquals("ten", postOrder.next().getElement());
        assertEquals("two", postOrder.next().getElement());
        assertEquals("eight", postOrder.next().getElement());
        assertEquals("nine", postOrder.next().getElement());
        assertEquals("four", postOrder.next().getElement());
        assertEquals("three", postOrder.next().getElement());
        assertEquals("one", postOrder.next().getElement());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        //expecting(six, two, seven, ten, five, one, eight, four, nine, three)
        Iterator<Position<String>> inOrder = tree.inOrder().iterator();
        assertEquals("six", inOrder.next().getElement());
        assertEquals("two", inOrder.next().getElement());
        assertEquals("seven", inOrder.next().getElement());
        assertEquals("ten", inOrder.next().getElement());
        assertEquals("five", inOrder.next().getElement());
        assertEquals("one", inOrder.next().getElement());
        assertEquals("eight", inOrder.next().getElement());
        assertEquals("four", inOrder.next().getElement()); 
        assertEquals("nine", inOrder.next().getElement());
        assertEquals("three", inOrder.next().getElement());        
    }
    /**
     * test the output of the level order traversal
     */
    @Test
    public void testLevelOrder() {
        createTree();
//		one
//*                /        \
//*             two          three
//*            /   \            /
//*         six   ten          four
//*              /   \        /     \
//*            seven  five  eight nine 
        //expecting(one, two, three, six, ten, four, seven, five, eight, nine
        Iterator<Position<String>> level = tree.levelOrder().iterator();
        assertEquals("one", level.next().getElement());
        assertEquals("two", level.next().getElement());
        assertEquals("three", level.next().getElement());
        assertEquals("six", level.next().getElement());
        assertEquals("ten", level.next().getElement());
        assertEquals("four", level.next().getElement());
        assertEquals("seven", level.next().getElement());
        assertEquals("five", level.next().getElement());
        assertEquals("eight", level.next().getElement());
        assertEquals("nine", level.next().getElement());
        
    }
    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertEquals(0, tree.size());
        assertNull(tree.root());
        assertNotNull(tree.addRoot("testing"));
        assertEquals(1, tree.size());
        assertNotNull(tree.addLeft(tree.root(), "stringleftOfRoot"));
        assertNotNull(tree.addRight(tree.root(), "stringRightOfRoot"));
        assertEquals(3, tree.size());
        assertEquals("LinkedBinaryTree[\n"
        		+ "testing\n"
        		+ " stringleftOfRoot\n"
        		+ " stringRightOfRoot\n"
        		+ "]", tree.toString());
        
        assertEquals("stringleftOfRoot", tree.remove(tree.left(tree.root())));
        assertEquals(2, tree.size());
        assertEquals("LinkedBinaryTree[\n"
        		+ "testing\n"
        		+ " stringRightOfRoot\n"
        		+ "]", tree.toString());
        
        assertEquals("testing", tree.remove(tree.root()));
        assertEquals(1, tree.size());
        assertEquals("LinkedBinaryTree[\n"
        		+ "stringRightOfRoot\n"
        		+ "]", tree.toString());
        assertEquals("stringRightOfRoot", tree.remove(tree.root()));
        assertEquals(0, tree.size());
        
        createTree();
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " three\n"
        		+ "  four\n"
        		+ "   eight\n"
        		+ "   nine\n"
        		+ "]", tree.toString());
        
        tree.remove(nine);
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " three\n"
        		+ "  four\n"
        		+ "   eight\n"
        		+ "]", tree.toString());
        tree.remove(three);
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " four\n"
        		+ "  eight\n"
        		+ "]", tree.toString());
        tree.remove(four);
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " eight\n"
        		+ "]", tree.toString());
        tree.remove(eight);
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ "]", tree.toString());
        tree.remove(six);
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ "]", tree.toString());
        tree.remove(two);
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " ten\n"
        		+ "  seven\n"
        		+ "  five\n"
        		+ "]", tree.toString());
        
    }
    
   

//    /**
//     * Test the output of the addLeft(p,e) behavior, including expected exceptions
//     */      
//    @Test
//    public void testAddLeft() {
//    }
//    
//    /**
//     * Test the output of the addRight(p,e) behavior, including expected exceptions
//     */      
//    @Test
//    public void testAddRight() {
//    }   
//    
//    /**
//     * Test the output of the remove(p) behavior, including expected exceptions
//     */         
//    @Test
//    public void testRemove() {
//        createTree();
//    }
}