	

package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;




/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 *
 */
public class UnorderedLinkedMapTest {
	/** map used to for testing with integers as keys and strings as the value for the entry */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        assertEquals("string3", map.put(3, "newValue"));
        assertEquals(1, map.size());
        assertNull(map.put(5, "string5"));
        assertEquals(2, map.size());
        assertEquals("string5", map.put(5, "newValue2"));
        assertEquals(2, map.size());
        assertNull(map.put(6, "string6"));
        assertEquals(3, map.size());
        assertEquals("UnorderedLinkedMap[6, 5, 3]", map.toString());
        assertEquals("newValue2", map.put(5, "newValue2New"));
        assertEquals(3, map.size());
        
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        //attempt to get a key which does not exist
        assertNull(map.get(573924527));
        assertEquals(5, map.size()); 
        
        assertEquals("string2", map.get(2));
        assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
        assertEquals(5, map.size());
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 2, 4, 5, 3]", map.toString());
        assertEquals(5, map.size());
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 1, 2, 4, 5]", map.toString());
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 1, 2, 5]", map.toString());
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 4, 3, 1, 2]", map.toString());

        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        //attempt to remove something that does not exist
        assertNull(map.remove(238989238));
        assertEquals(5, map.size());
        //removing from the middle of the list
        assertEquals("string2", map.remove(2));
        assertEquals(4, map.size());
        //removing from the front of the list
        assertEquals("string1", map.remove(1));
        assertEquals(3, map.size());
        //removing from the end of the list
        assertEquals("string3", map.remove(3));
        assertEquals(2, map.size());
        assertEquals("string5", map.remove(5));
        assertEquals(1, map.size());
        //removing the last element in the list
        assertEquals("string4", map.remove(4));
        assertEquals(0, map.size());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        

        Iterator<Integer> keyIt = map.iterator();
        while(keyIt.hasNext()) {
        	Integer currKey = keyIt.next();
        	switch(currKey) {
        		case 1:
        		case 2:
        		case 3:
        		case 4:
        		case 5:
        			break;
        		default:
        			fail("the key does not match");
        	}
        }
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        //attempt to set all of the entries to be in order from 1 to 5 for our next test
        map.get(5);
        map.get(4);
        map.get(3);
        map.get(2);
        map.get(1);
        assertEquals("UnorderedLinkedMap[1, 2, 3, 4, 5]", map.toString());
        
        int i = 1;
        Iterable<Map.Entry<Integer, String>> entrySet = map.entrySet();
        for(Map.Entry<Integer, String> entry : entrySet) {
        	assertEquals((Integer) i++, entry.getKey());
        }
        
        i = 1;
        Iterator<Map.Entry<Integer, String>> keyIt = entrySet.iterator();
        while(keyIt.hasNext()) {
        	assertEquals((Integer) i++,  keyIt.next().getKey());
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
      //attempt to set all of the entries to be in order from 1 to 5 for our next test
        map.get(5);
        map.get(4);
        map.get(3);
        map.get(2);
        map.get(1);
        assertEquals("UnorderedLinkedMap[1, 2, 3, 4, 5]", map.toString());
        
        Iterable<String> values = map.values();
        int i = 1;
        for (String value : values) {
        	assertEquals("string" + i++, value);
		}

    }
}

