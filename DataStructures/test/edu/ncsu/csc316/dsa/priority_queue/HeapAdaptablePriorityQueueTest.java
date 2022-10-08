package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapAdaptablePriorityQueueTest {
	/** adaptable priority queue used for testing */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
    
        heap.replaceKey(e8, 8);
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e0, 100);
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e1, 99);
        assertEquals("two", heap.min().getValue());
        heap.replaceKey(e2, 98);
        assertEquals("three", heap.min().getValue());
        heap.replaceKey(e3, 97);
        assertEquals("four", heap.min().getValue());
        heap.replaceKey(e4, 96);
        heap.replaceKey(e5, 96);
        heap.replaceKey(e6, 96);
        heap.replaceKey(e7, 96);
        heap.replaceKey(e8, 97);
        assertEquals("seven", heap.min().getValue()); 
        
      //this should throw
        heap.replaceKey(null, 100);
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        
        heap.replaceValue(e7, "SEVEN");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals(9, heap.size());
        assertEquals("SEVEN", e7.getValue());
        
        heap.replaceValue(e6, "SIX");
        assertEquals("SIX", e6.getValue());
        assertEquals("five", e5.getValue());
        assertEquals(e0, heap.min());
        heap.replaceValue(e4, "FOUR");
        assertEquals("FOUR", e4.getValue());
        heap.replaceValue(e3, "THREE");
        assertEquals("THREE", e3.getValue());
        assertEquals("two", e2.getValue());
        assertEquals("one", e1.getValue());
        heap.replaceValue(e1, "ONE");
        assertEquals("ONE", e1.getValue());
        
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
 
        heap.remove(e8);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(7, heap.size());
        
        heap.remove(e5);
        assertEquals(e1, heap.min());
        
        heap.remove(e7);
        assertEquals(e1, heap.min());
        assertEquals(5, heap.size());
        
        heap.remove(e1);
        assertEquals(e2, heap.min());
        assertEquals(4, heap.size());
        
        heap.remove(e4);
        assertEquals(3, heap.size());
        assertEquals(e2, heap.min());
        
        heap.remove(e3);
        assertEquals(2, heap.size());
        assertEquals(e2, heap.min());
        
        heap.remove(e6);
        assertEquals(1, heap.size());
        assertEquals(e2, heap.min());
        heap.remove(e2);
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
        assertNull(heap.min());
        
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
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
