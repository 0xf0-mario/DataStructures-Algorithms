package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapPriorityQueueTest {
	/** heap implementation of our PQ */
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
//        assertTrue(heap.size() == 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(2, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals("three", heap.min().getValue());
        
        Entry<Integer, String> newEntry = heap.insert(1, "one");
        assertEquals(3, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(newEntry.getValue(), heap.deleteMin().getValue());
        
        heap.insert(5, "five");
        assertEquals(3, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(3, (int) heap.min().getKey());
        
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
//        assertTrue(heap.size() == 0);
        
        assertNull(heap.min());
        
        heap.insert(10,  "ten");
        assertEquals("ten", heap.min().getValue());
        heap.insert(12, "twelve");
        assertEquals(10, (int) heap.min().getKey());
        heap.insert(7, "seven");
        assertEquals(7, (int) heap.min().getKey());
        heap.insert(4, "four");
        assertEquals("four", heap.min().getValue());
        heap.insert(8, "eight");
        assertEquals("four", heap.min().getValue());
        //duplicate key
        heap.insert(12, "twelve");
        assertEquals("four", heap.min().getValue());
        heap.insert(11, "eleven");
        assertEquals("four", heap.min().getValue());
        heap.insert(1, "one");
        assertEquals("one", heap.min().getValue());
        
        
        
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        
        heap.insert(10,  "ten");
        heap.insert(12, "twelve");
        heap.insert(7, "seven");
        heap.insert(4, "four");
        heap.insert(8, "eight");
        //duplicate key
        heap.insert(12, "twelve");
        heap.insert(11, "eleven");
        heap.insert(1, "one");
        
        assertEquals("one", heap.deleteMin().getValue());
        assertEquals(7, heap.size());
        assertEquals(4, (int) heap.min().getKey());

        assertEquals(4, (int) heap.deleteMin().getKey());
        assertEquals(7, (int) heap.min().getKey());
        assertEquals(6, heap.size());
        
        assertEquals("seven", heap.deleteMin().getValue());
        assertEquals("eight", heap.min().getValue());
        assertEquals(8, (int) heap.deleteMin().getKey()); 
        assertEquals(10, (int) heap.deleteMin().getKey());
        assertEquals(11, (int) heap.deleteMin().getKey());
        assertEquals(2, heap.size());
        assertEquals(12, (int) heap.deleteMin().getKey());
        assertEquals(1, heap.size());
        assertEquals(12, (int) heap.deleteMin().getKey());
        assertEquals(0, heap.size());

    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1"); //id is the third param
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        assertEquals(s1, sHeap.insert(s1, s1.getFirst()).getKey());
        assertEquals(1, sHeap.size());
        assertFalse(sHeap.isEmpty());
        assertEquals(s1, sHeap.min().getKey());
        
        assertEquals(s2.getFirst(), sHeap.insert(s2, s2.getFirst()).getValue());
        assertEquals(2, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        assertEquals(s1, sHeap.deleteMin().getKey());
        assertEquals(1, sHeap.size());
        assertEquals(s2, sHeap.min().getKey());
        assertEquals(s2, sHeap.deleteMin().getKey());
        assertEquals(0, sHeap.size());
        
        assertEquals(s4.getUnityID(), sHeap.insert(s4, s4.getUnityID()).getValue());
        assertEquals(1, sHeap.size());
        assertEquals(s4, sHeap.min().getKey());
        assertEquals(s3, sHeap.insert(s3, s3.getUnityID()).getKey());
        assertEquals(2, sHeap.size()); 
        assertEquals(s3, sHeap.min().getKey());
        assertEquals(s5, sHeap.insert(s5, "string").getKey());
        assertEquals(s3, sHeap.min().getKey());
        
        
        
    }
}