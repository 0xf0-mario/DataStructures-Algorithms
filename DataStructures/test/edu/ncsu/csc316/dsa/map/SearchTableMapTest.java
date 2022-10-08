package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 *
 */
public class SearchTableMapTest {
	/** the MAP object of integers and strings */
    private Map<Integer, String> map;
    /** the MAP object of Students and ID numbers */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() { //The natural ordering for student objects is the last, first, then ID. We also have student ID comparators and GPA comparators
        map = new SearchTableMap<Integer, String>();
//        StudentIDComparator idCompare = new StudentIDComparator();
//        idCompare
        studentMap = new SearchTableMap<Student, Integer>();
        
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        assertEquals("string3", map.put(3, "3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(2, "string5"));
        assertEquals(2, map.size());
        assertEquals("string5", map.put(2, "5"));
        assertEquals(2, map.size());
        assertEquals("SearchTableMap[2, 3]", map.toString());
        
        assertNull(map.put(1, "1"));
        assertEquals(3, map.size());
        assertEquals("SearchTableMap[1, 2, 3]", map.toString());
       
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        assertEquals("string3", map.get(3));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        assertEquals("string5", map.get(5));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertNull(map.get(10));
        
        
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        

        
        //remove from the middle, back, front, and the last element from the list
        assertEquals(5, map.size());
        assertEquals("string3", map.remove(3));
        assertEquals(4, map.size());
        assertEquals("string5", map.remove(5));
        assertEquals(3, map.size());
        assertEquals("string1", map.remove(1));
        assertEquals(2, map.size());
        map.remove(2);
        assertEquals(1, map.size());
        assertEquals("string4", map.remove(4));
        assertEquals(0, map.size());
        //attempt to remove a key which does not exist
        assertNull(map.remove(218321983));
    
    }
    
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        // Suggestions: since search table map keys are Comparable,
        // make sure the search table works with Comparable objects like Students
        assertNull(studentMap.put(s1, s1.getId()));
        assertNull(studentMap.put(s2, s2.getId()));
        assertNull(studentMap.put(s3, s3.getId()));
        assertNull(studentMap.put(s4, s4.getId()));
        assertNull(studentMap.put(s5, s5.getId()));
        //last,first,then id
        assertEquals("SearchTableMap[Student [first=L, last=B, id=5, creditHours=0, gpa=0.0, unityID=lb], "
        		+ "Student [first=S, last=H, id=3, creditHours=0, gpa=0.0, unityID=sh], "
        		+ "Student [first=J, last=J, id=4, creditHours=0, gpa=0.0, unityID=jj], "
        		+ "Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk], "
        		+ "Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]", studentMap.toString());
        
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
      
        
        int i = 2;
        while(it.hasNext()) {
        	Map.Entry<Integer, String> currentEntry = it.next();
        	assertEquals(i, (int) currentEntry.getKey());
        	assertEquals("string" + i, currentEntry.getValue());
        	
        	i++;
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        int i = 1;
        while(it.hasNext()) {
        	assertEquals("string" + i++, it.next());
        }
        
    }
}