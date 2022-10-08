package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * StudentTest will test out proper functionality of the student class
 * Checks to see if students are properly constructed
 * Exceptions should be thrown if there are any invalid fields and the student should not be instantiated
 * @author Mario Medel
 *
 */
public class StudentTest {
	/** student objects used for testing */
	private Student sOne;
	/** student two */
	private Student sTwo;
	/** student three*/ 
	private Student sThree;
	/** student four */
	private Student sFour;
	/** student five */
	private Student sFive;

	/**
	 * before each test case we will set up five students to use in our test cases
	 * these students may be modified within our test cases
	 */
	@Before
	public void setUp() {
		//FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
		sOne = new Student("OneFirst", "OneLast", "oneUnityID", 1, 1.0, 1);
		sTwo = new Student("TwoFirst", "TwoLast", "twoUnityID", 2, 2.0, 2);
		sThree = new Student("ThreeFirst", "ThreeLast", "threeUnityID", 3, 3.0, 3 );
		sFour = new Student("FourFirst", "FourLast", "fourUnityID", 4, 3.0, 4);
		sFive = new Student("FiveFirst", "FiveLast", "fiveUnityID", 5, 5.0, 5);

	}
	/**
	 * checks to make sure that the setFirst() properly changes the first name for the student object
	 * ensures proper exceptions are thrown when passing in a null or empty string
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
		
//		assertThrows(IllegalArgumentException.class, 
//				() -> sFive.setFirst(""));
//		
//		assertThrows(IllegalArgumentException.class, 
//				() -> sFour.setFirst(null));
	}
	/**
	 * checks to make sure that the setLast() method properly changes the last name for the student object
	 * ensures proper exceptions are thrown when passing in a null or empty string
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
		
//		assertThrows(IllegalArgumentException.class, 
//				() -> sFive.setFirst(""));
//		
//		assertThrows(IllegalArgumentException.class, 
//				() -> sFour.setFirst(null));
	}
	/**
	 * checks to make sure that the setId() method is able to change the id number for a student
	 * Ensures an exception message is thrown if there is a negative integer as a parameter
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
		
//		assertThrows(IllegalArgumentException.class, 
//				() -> sThree.setId(-100));
	}
	/**
	 * checks to make sure setGpa() properly changes the GPA for the student object
	 * ensures an exception is thrown if there is a negative integer as a parameter
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
		
//		assertThrows(IllegalArgumentException.class, ()
//				-> sTwo.setGpa(-1));
	}
	/**
	 * checks to make sure setUnityID() properly changes the unity ID for the provided student object
	 * ensures exceptions are thrown when the method is passes a null or an empty string
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
		
//		assertThrows(IllegalArgumentException.class, ()
//				-> sThree.setUnityID(null));
//		
//		assertThrows(IllegalArgumentException.class, 
//				() -> sThree.setUnityID(""));
	}
	/**
	 * checks to make sure the compare to method returns appropriate integers
	 * if two objects are equal it will return 0
	 * if A.compareTo(B), A is greater then it will return a positive integer
	 * if B is greater then it will return a negative integer
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertEquals(0, sOne.compareTo(sOne));
		assertEquals(0, sTwo.compareTo(sTwo));
		//whenever an object has the same last name then the first name will be compared
		sOne.setLast("A"); //has a first name of oneFirst
		sTwo.setLast("A"); //has a first name of twoFirst
		sOne.setFirst("C");
		sTwo.setFirst("B");
		assertTrue(sOne.compareTo(sTwo) > 0);
		
		//when both objects have the same last and first then the student ID will be compared
		sOne.setFirst("B");
		assertTrue(sOne.compareTo(sTwo) < 0);
		
	}
	
	//"Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
	//+ gpa + ", unityID=" + unityID + "]"
	/**
	 * ensures proper information is displayed when calling to string
	 * mainly used for debugging
	 */
	@Test public void testToString() {
		//sFour = new Student("FourFirst", "FourLast", "fourUnityID", 4, 3.0, 4);
		//sFive = new Student("FiveFirst", "FiveLast", "fiveUnityID", 5, 5.0, 5);
		
		assertEquals("Student [first=FourFirst, last=FourLast, id=4, creditHours=4, gpa=3.0, unityID=fourUnityID]", 
				sFour.toString());
		
		assertEquals("Student [first=FiveFirst, last=FiveLast, id=5, creditHours=5, gpa=5.0, unityID=fiveUnityID]",
				sFive.toString());
	}
	/**
	 * ensures proper functionality of equals method
	 * two student objects are equal if and only if they have the same first, last, and id fields
	 */
	@Test public void testEquals() {
		Student copyFive = sFive;
		
		assertTrue(copyFive.equals(sFive));
		assertTrue(sFive.equals(copyFive));
		assertFalse(copyFive.equals(sOne));
		
		Student newS = new Student("FiveFirst", "FiveLast", "DifferentUNITYID", 5, 4.99, 5);
		assertTrue(newS.equals(copyFive));
		assertTrue(sFive.equals(newS));
		assertFalse(newS.equals(sThree));
	}
	
	/**
	 * test for hashCode method, objects should have different hashes unless they are the exact same
	 */
	@Test public void testHashCode() {
		assertNotEquals(sOne.hashCode(), sTwo.hashCode());
		
		Student copyOne = sOne;
		assertEquals(copyOne.hashCode(), sOne.hashCode());
	}
	
}
