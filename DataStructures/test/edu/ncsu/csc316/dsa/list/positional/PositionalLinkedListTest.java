package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest { 
	/** Positional list field used to contain string object */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        
        Position<String> third = list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals(third, list.first());
        
        try {
        	list.addFirst(null);
        	fail();
        } catch(Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());
         
         assertNull(list.last());
         
         Position<String> first = list.addLast("one");
         assertEquals(1, list.size());
         assertEquals(first, list.last());
         
         Position<String> second = list.addLast("two");
         assertEquals(2, list.size());
         assertEquals(second, list.last());
         
         Position<String> third = list.addLast("three");
         assertEquals(3, list.size());
         assertEquals(third, list.last());
         
         try {
         	list.addLast(null);
         	fail();
         } catch(Exception e) {
         	assertTrue(e instanceof IllegalArgumentException);
         }
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first, list.first());
        
        //used in the above tests (above test checked for exception and added first multiple elements)
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        //used in the above tests (above test check for exception and added last multiple elements )
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */ 
    @Test
    public void testAddBefore() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());
         
         assertNull(list.first());
         Position<String> zero = list.addFirst("0");
         //add the second element
         Position<String> first = list.addBefore(zero, "first");
         assertEquals(2, list.size());
         assertEquals(first, list.first());
         assertEquals(zero, list.last());
         //call before to add the second element to add before the first element
         Position<String> second = list.addBefore(first, "second");
         assertEquals(3, list.size());
         assertEquals(second, list.before(first));
         assertEquals(second.getElement(), list.before(first).getElement());
         //add before the last element
         Position<String> third = list.addBefore(list.last(), "third");
         assertEquals(4, list.size());
         assertEquals(third, list.before(list.last()));
         assertEquals(third.getElement(), list.before(list.last()).getElement());
         try {
         	list.addBefore(list.last(), null);
         	fail();
         } catch(Exception e) {
         	assertTrue(e instanceof IllegalArgumentException);
         }
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        Position<String> zero = list.addFirst("0");
        //add the second element
        Position<String> first = list.addAfter(zero, "first");
        assertEquals(2, list.size());
        assertEquals(zero, list.first());
        assertEquals(first, list.last());
        //call before to add the second element to add before the first element
        Position<String> second = list.addAfter(first, "second");
        assertEquals(3, list.size());
        assertEquals(second, list.after(first));
        assertEquals(second.getElement(), list.after(first).getElement());
        //add before the last element
        Position<String> third = list.addAfter(list.last(), "third");
        assertEquals(4, list.size());
        assertEquals(third, list.after(second));
        assertEquals(third.getElement(), list.after(second).getElement());
        try {
        	list.addAfter(list.last(), null);
        	fail();
        } catch(Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
        
		Position<String> first = list.addLast("0");
		Position<String> second = list.addLast("1");
		Position<String> third = list.addLast("2");
		 //0,1,2
        assertEquals(3, list.size());
        assertEquals("0", list.set(first, "4"));
        assertEquals("4", list.before(second).getElement());
        assertEquals(3, list.size());
        assertEquals("1", list.set(second, "3"));
        assertEquals("3", list.before(third).getElement());
        assertEquals(3, list.size());
        assertEquals("2", list.set(third, "10"));
        assertEquals("10", list.after(second).getElement());
        assertEquals(3, list.size());
        
        
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> first = list.addLast("0");
		Position<String> second = list.addLast("1");
		Position<String> third = list.addLast("2");
		Position<String> fourth = list.addLast("3");
		assertEquals(4, list.size());
		//remove middle
		assertEquals("1", list.remove(second));
		assertEquals("2", list.after(first).getElement());
		assertEquals(3, list.size());
		//remove from the front
		assertEquals("0", list.remove(first));
		assertEquals("2", list.before(fourth).getElement());
		assertEquals(2, list.size());
		//remove from the end of the list
		assertEquals("3", list.remove(fourth));
		assertEquals("2", list.last().getElement());
		assertEquals(1, list.size());
		//remove the last in the list
		assertEquals("2", list.remove(third));
		assertEquals(0, list.size());
		
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
    	 assertEquals(0, list.size());
         Position<String> first = list.addFirst("one");
         Position<String> second = list.addLast("two");
         Position<String> third = list.addLast("three");
         assertEquals(3, list.size());
         
         Iterator<String> it = list.iterator();
         assertTrue(it.hasNext());
         assertEquals(first.getElement(), it.next());
         it.remove();
         assertEquals(2, list.size());
         assertEquals(second.getElement(), it.next());
         it.remove();
         assertEquals(1, list.size());
         assertEquals(third.getElement(), it.next());
         it.remove();
         assertEquals(0, list.size());
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        it.remove();
        assertEquals(2, list.size());
        assertEquals(second, it.next());
        it.remove();
        assertEquals(1, list.size());
        assertEquals(third, it.next());
        it.remove();
        assertEquals(0, list.size());        
       
    }

}
