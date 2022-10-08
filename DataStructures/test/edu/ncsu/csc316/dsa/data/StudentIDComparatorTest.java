package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * StudentIDComparatorTest will test the Student ID comparisions as defined by the class itself
 * @author Mario Medel
 *
 */
public class StudentIDComparatorTest {
	/**
	 * Student objects used during the compare test cases
	 */
	private Student sOne;
	/** student two */ 
	private Student sTwo;
	/** student three */ 
	private Student sThree;
	/** student four */
	private Student sFour;
	/** student five */
	private Student sFive;
	/** comparator object used to compare student ID */
	private StudentIDComparator comparator;
	/**
	 * setup before each test case, we will have full scope over this information
	 * initilizes all of the student objects and comparator object
	 */
	@Before
	public void setUp() {
		//FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
		sOne = new Student("OneFirst", "OneLast", "oneUnityID", 1, 1.0, 1);
		sTwo = new Student("TwoFirst", "TwoLast", "twoUnityID", 2, 2.0, 2);
		sThree = new Student("ThreeFirst", "ThreeLast", "threeUnityID", 3, 3.0, 3 );
		sFour = new Student("FourFirst", "FourLast", "fourUnityID", 4, 3.0, 4);
		sFive = new Student("FiveFirst", "FiveLast", "fiveUnityID", 5, 5.0, 5);


		comparator = new StudentIDComparator();
	}
	/**
	 * test all cases for the compare method using student ID
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		
		sOne.setId(5);
		sFour.setId(10);
		assertEquals(comparator.compare(sOne, sFive), 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sOne, sFour) < 0);
		assertTrue(comparator.compare(sThree, sTwo) > 0);
		assertFalse(comparator.compare(sThree, sTwo) < 0);
		
	}


}
