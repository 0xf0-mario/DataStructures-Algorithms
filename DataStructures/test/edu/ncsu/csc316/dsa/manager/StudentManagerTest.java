package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * StudentManagerTest connects StudentReader to the .sort() method Student
 * objects should be properly sorted based on the comparator
 * 
 * @author Mario Medel
 *
 */
public class StudentManagerTest {
	/** manager object used for testing */
	private StudentManager sm;

	/**
	 * instantiates the StudentManager object and reads in the input from a CSV file
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
	}

	/**
	 * Test the default sort with insertion sorter
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());

	}

	// Suggestions:
	// -> Test that custom comparators sort the data correctly

}
