package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * StudentGPAComparatorTest will test out the sorting students by GPA functionality
 * Students should be order in descending order based on GPA
 * @author Mario Medel
 *
 */
public class StudentGPAComparatorTest {
	/** student one used during testing */
	private Student sOne;
	/** student two used during testing */
	private Student sTwo;
	/** student three used during testing */
	private Student sThree;
	/** student four used during testing */
	private Student sFour;
	/** student five used during testing */
	private Student sFive;
	/** comparator for comparing students based on GPA */
	private StudentGPAComparator comparator;
	/**
	 * setup before every test method
	 * Initializes the students
	 */
	@Before
	public void setUp() {
		//FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
		sOne = new Student("OneFirst", "OneLast", "oneUnityID", 1, 1.0, 1);
		sTwo = new Student("TwoFirst", "TwoLast", "twoUnityID", 2, 2.0, 2);
		sThree = new Student("ThreeFirst", "ThreeLast", "threeUnityID", 3, 3.0, 3 );
		sFour = new Student("FourFirst", "FourLast", "fourUnityID", 4, 3.0, 4);
		sFive = new Student("FiveFirst", "FiveLast", "fiveUnityID", 5, 5.0, 5);

		comparator = new StudentGPAComparator();
	}
	/** 
	 * Test case will test out if students are being sorted properly by GPA
	 * Also tests to see the case where students having the same GPA should be compared based on the natural ordering
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sThree, sFour) > 0);
		//if students have the same gpa then we compare by last, first, and then id
		sOne.setGpa(5.0);
		assertTrue(comparator.compare(sOne, sFive) > 0);
		
		Student newS = new Student("FiveFirst", "FiveLast", "fiveUnityID", 5, 5.0, 5);
		assertEquals(comparator.compare(newS, sFive), 0);
		newS.setLast("a");
		assertTrue(comparator.compare(newS, sFive) > 0);
		
	}

}
