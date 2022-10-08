package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author Mario Medel
 *
 */
public class ArrayBasedQueueTest {
	/** common queue used for testing */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
    	try {
    		queue.front();
    		fail("no exception thrown");
    	} catch(Exception e) {
    		assertTrue(e instanceof NoSuchElementException);
    	}
    	
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("three");
        assertEquals("one", queue.front());
        assertEquals(3, queue.size());
        queue.enqueue("four");
        assertEquals("one", queue.front());
        queue.enqueue("five");
        queue.enqueue("six");
        assertEquals("one", queue.front());
        assertEquals(6, queue.size());
        
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        queue.enqueue("one");
        assertEquals("one", queue.front());
        assertEquals("one", queue.dequeue());
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        assertEquals("two", queue.front());
        assertEquals("two", queue.dequeue());
        queue.enqueue("five");
        queue.enqueue("six");
        queue.enqueue("seven");
        assertEquals("three", queue.dequeue());
        assertEquals(4, queue.size());
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");
        assertEquals("four", queue.front());
        assertEquals("four", queue.dequeue());
        
        
    }
    
}

