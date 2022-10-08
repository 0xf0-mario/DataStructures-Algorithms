package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;
import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LinkedStack.
 * Checks the expected outputs of the Stack abstract data type behaviors when using
 * a singly-linked list data structure
 *
 * @author Dr. King
 * @author Mario Medel
 *
 */
public class LinkedStackTest {
	/** stack data structure used for testing */
    private Stack<String> stack;
    
    /**
     * Create a new instance of a linked list-based stack before each test case executes
     */
    @Before
    public void setUp() {
        stack = new LinkedStack<String>();
    }
    
    /**
     * Test the output of the push(e) behavior
     */ 
    @Test
    public void testPush() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        try {
            stack.top();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        
        stack.push("two");
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
        
        for (int i = 3; i < 10; i++) {
			stack.push("" + i);
			assertEquals(i, stack.size());
			assertFalse(stack.isEmpty());
		}
        
        
    }

    /**
     * Test the output of the pop() behavior, including expected exceptions
     */
    @Test
    public void testPop() {
    	assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        try {
            stack.top();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        try {
            stack.pop();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        assertEquals("one", stack.pop());
        
        stack.push("two");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        stack.push("three");
        stack.push("four");
        assertEquals("four", stack.pop());
        
        for (int i = 4; i < 10; i++) {
			stack.push("" + i);
			assertEquals(i - 1, stack.size());
			assertFalse(stack.isEmpty());
		}
    }

    

}