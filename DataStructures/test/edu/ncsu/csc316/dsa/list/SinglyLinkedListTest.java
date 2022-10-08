package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
/**
 * SinglyLinkedTest will test out the singly linked list ADT for proper functionality
 * this class should have the same behavior as ArrayBasedList so we can copy most of the methods here
 * @author Mario Medel 
 *
 */
public class SinglyLinkedListTest {
	/** List of string types used for Array Based List testing */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        //because the list initially has a capacity of zero
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        //we can now trigger our next resize by adding another element
        list.add(1,  "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        //adding three more will trigger our resize operation once again (current capacity is now at 4)
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertFalse(list.isEmpty());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        assertFalse(list.isEmpty());
        
        list.add(4, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(4));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
        	list.add(5, null);
        	fail("A NullPointerException should have been thrown");
        } catch (Exception e){
        	assertTrue(e instanceof NullPointerException); 
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    	
    	list.addLast("one");
    	assertEquals(1, list.size());
    	assertFalse(list.isEmpty());
    	//we get from size - 1 because we should be adding at the end of the list 
    	assertEquals("one", list.get(list.size() - 1));
    	
    	list.addLast("two");
    	assertEquals(2, list.size());
    	assertFalse(list.isEmpty());
    	assertEquals("two", list.get(list.size() - 1));
    	
    	//add some buffer elements in between
    	list.add(0, "three"); //add at the front via index
    	assertEquals(3, list.size());
    	assertEquals("three", list.get(0));
    	assertEquals("one", list.get(1));
    	assertEquals("two", list.get(2));
    	list.add(3,  "four");
    	assertEquals(4, list.size());
    	assertEquals("four", list.get(3));
    	//trigger a resize and add last one more time to ensure proper adding
    	list.addLast("five");
    	assertEquals(5, list.size());
    	//check all of the elements
    	assertEquals("three", list.get(0));
    	assertEquals("one", list.get(1));
    	assertEquals("two", list.get(2));
    	assertEquals("four", list.get(3));
    	assertEquals("five", list.get(4));
    	
    	
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        //the list is empty so there should be an exception when trying to access the last element
    	try { 
    		list.last();
    		fail("no exception was thrown");
    	} catch(Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	list.add(0, "first");
    	assertEquals(1, list.size());
    	assertEquals("first", list.last());
    	//check index
    	assertEquals(1, list.size());
    	
    	list.addLast("two");
    	assertEquals(2, list.size());
    	assertEquals("two", list.last());
    	assertEquals(2, list.size());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	assertTrue(list.isEmpty());
        assertEquals(0, list.size());
     	
     	list.addFirst("one");
     	assertEquals(1, list.size());
     	assertFalse(list.isEmpty());
     	//we get from size - 1 because we should be adding at the end of the list 
     	assertEquals("one", list.get(list.size() - 1));
     	
     	list.addFirst("two");
     	assertEquals(2, list.size());
     	assertFalse(list.isEmpty());
     	assertEquals("two", list.get(0));
     	
     	//add some buffer elements in between
     	list.add(0, "three"); //add at the front via index
     	assertEquals(3, list.size());
     	assertEquals("three", list.get(0));
     	assertEquals("two", list.get(1));
     	assertEquals("one", list.get(2));
     	list.add(3,  "four");
     	assertEquals(4, list.size());
     	assertEquals("four", list.get(3));
     	//trigger a resize and add last one more time to ensure proper adding
     	list.addFirst("five");
     	assertEquals(5, list.size());
     	//check all of the elements
     	assertEquals("five", list.get(0));
     	assertEquals("three", list.get(1));
     	assertEquals("two", list.get(2));
     	assertEquals("one", list.get(3));
     	assertEquals("four", list.get(4));
     	
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	//the list is empty so there should be an exception when trying to access the first element
    	try { 
    		list.first();
    		fail("no exception was thrown");
    	} catch(Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	list.add(0, "first");
    	assertEquals(1, list.size());
    	assertEquals("first", list.first());
    	//check index
    	assertEquals(1, list.size());
    	
    	list.addFirst("two");
    	assertEquals(2, list.size());
    	assertEquals("two", list.first());
    	assertEquals(2, list.size());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {  
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        it.remove();
        assertEquals(0, list.size());
        
        
        for (int i = 7; i >= 0; i--) {
			list.addFirst("" + i);
		} //0,1,2,3,4,5,6,7 as strings
        it = list.iterator();
        assertEquals(8, list.size());
        assertTrue(it.hasNext());
        assertEquals("0", it.next());
        assertTrue(it.hasNext());
        assertEquals("1", it.next());
        
        it.remove(); //0,,2,3,4,5,6,7 
        assertEquals(7, list.size());
        assertEquals("2", it.next());
        it.remove(); //0,,,3,4,5,6,7 
        assertEquals(6, list.size());
        assertEquals("3", it.next());
        it.remove(); //0,,,,4,5,6,7 
        assertEquals("4", it.next());
        it.remove(); //0,,,,,5,6,7 
        assertEquals(4, list.size());
        assertEquals("5", it.next());
        it.remove(); //0,,,,,,6,7 
        
        assertEquals("6", it.next());
        it.remove(); //0,,,,,,,7 
        assertEquals(2, list.size());
        assertEquals("7", it.next());
        it.remove(); //0
        
        assertEquals("0", list.get(0));
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	//removing from any index while the list is empty should result in an index out of bounds exception
    	try {
    		list.remove(0);
    		fail();
    	} catch (Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
        for (int i = 7; i >= 0; i--) {
			list.addFirst("" + i);
		} //0,1,2,3,4,5,6,7 as strings
        
        assertEquals(8, list.size()); 
        //remove from the middle of the list
        assertEquals("3", list.remove(3));
        assertEquals(7, list.size());
        //check if elements are shifted correctly
        assertEquals("4", list.get(3));
        assertEquals("5", list.get(4));
        assertEquals("6", list.get(5));
        assertEquals("7", list.get(6));
        //remove from the end of the list
        assertEquals("7", list.remove(6));
        assertEquals(6, list.size());
        //remove from the front of the list
        assertEquals("0", list.remove(0));
        assertEquals(5, list.size());
        
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	//removing from an empty list should throw an index out of bounds exception
    	try {
    		list.removeFirst();
    		fail("should throw an exception");
    	} catch(Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	for (int i = 7; i >= 0; i--) {
			list.addFirst("" + i);
		} //0,1,2,3,4,5,6,7 as strings
    	assertEquals(8, list.size()); 
    	
    	for (int i = 0; i < list.size(); i++) {
			assertEquals("" + i, list.removeFirst());
			assertEquals(7 - i, list.size());
		}
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	//removing from an empty list should throw an index out of bounds exception
    	try {
    		list.removeLast();
    		fail("should throw an exception");
    	} catch(Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	for (int i = 7; i >= 0; i--) {
			list.addFirst("" + i);
		} //0,1,2,3,4,5,6,7 as strings
    	assertEquals(8, list.size()); 
    	
    	for (int i = 7; i >= 0; i--) {
			assertEquals("" + i, list.removeLast());
			assertEquals(i, list.size());
		}
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	//should throw IndexOutOfBounds When attempting to set at size because its not an element
    	try {
    		list.set(0, "test");
    		fail();
    	} catch (Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	for (int i = 7; i >= 0; i--) {
			list.addFirst("" + i);
		} //0,1,2,3,4,5,6,7 as strings
    	assertEquals(8, list.size()); 
        //should throw NPE when attempting to set a null element
    	try {
    		list.set(0, null);
    		fail();
    	} catch(Exception e) {
    		assertTrue(e instanceof NullPointerException);
    	}
    	
    	list.set(0, "mario");
    	assertEquals(8, list.size());
    	assertEquals("mario", list.get(0));
    	
    	list.set(4, "medel");
    	assertEquals(8, list.size());
    	assertEquals("medel", list.get(4));
    	
    }
}
