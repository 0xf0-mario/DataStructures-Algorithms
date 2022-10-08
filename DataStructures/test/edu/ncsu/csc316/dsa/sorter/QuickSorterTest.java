package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.io.StudentReader;
/**
 * QuickSorterTest.java: tests out the quick sorter algorithm with various comparators
 * 
 * @author Mario Medel
 *
 */
public class QuickSorterTest {

	/** sorts students based on ID */
	QuickSorter<Student> studentIdSort;
	/** sorts students based on GPA */
	QuickSorter<Student> studentGPASort;
	/**
	 * setup before test case
	 * @throws java.lang.Exception if the object cannot be created
	 */
	@Before
	public void setUp() throws Exception {
		StudentIDComparator idCompare = new StudentIDComparator();
		studentIdSort = new QuickSorter<Student>(idCompare);
		
		StudentGPAComparator gpaCompare = new StudentGPAComparator();
		studentGPASort = new QuickSorter<Student>(gpaCompare);
	}
	/**
	 * test the sort method to ensure the students are properly ordered
	 */
	@Test
	public void testSort() {
		Student[] sortStudents = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		studentIdSort.sort(sortStudents);
		
		assertEquals("Amber", sortStudents[0].getFirst()); 
		assertEquals("Ara", sortStudents[1].getFirst()); 
		assertEquals("Lacie", sortStudents[2].getFirst());
		assertEquals("Idalia", sortStudents[3].getFirst());
		assertEquals("Evelin", sortStudents[4].getFirst());
		assertEquals("Lewis", sortStudents[5].getFirst());
		assertEquals("Alicia", sortStudents[6].getFirst());
		assertEquals("Tyree", sortStudents[7].getFirst());
		assertEquals("Loise", sortStudents[8].getFirst());
		assertEquals("Roxann", sortStudents[9].getFirst());
		assertEquals("Nichole", sortStudents[10].getFirst());
		assertEquals("Charlene", sortStudents[11].getFirst());
		assertEquals("Shanti", sortStudents[12].getFirst());
		assertEquals("Cristine", sortStudents[13].getFirst());
		assertEquals("Tanner", sortStudents[14].getFirst());
		assertEquals("Dante", sortStudents[15].getFirst());
		
		Student[] sortStudents2 = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		studentGPASort.sort(sortStudents2);
		
		assertEquals("Nichole", sortStudents2[0].getFirst()); 
		assertEquals("Alicia", sortStudents2[1].getFirst()); 
		assertEquals("Charlene", sortStudents2[2].getFirst());
		assertEquals("Cristine", sortStudents2[3].getFirst());
		assertEquals("Dante", sortStudents2[4].getFirst());
		assertEquals("Lacie", sortStudents2[5].getFirst());
		assertEquals("Idalia", sortStudents2[6].getFirst());
		assertEquals("Ara", sortStudents2[7].getFirst());
		assertEquals("Loise", sortStudents2[8].getFirst());
		assertEquals("Tanner", sortStudents2[9].getFirst());
		assertEquals("Amber", sortStudents2[10].getFirst());
		assertEquals("Roxann", sortStudents2[11].getFirst());
		assertEquals("Tyree", sortStudents2[12].getFirst());
		assertEquals("Evelin", sortStudents2[13].getFirst());
		assertEquals("Shanti", sortStudents2[14].getFirst());
		assertEquals("Lewis", sortStudents2[15].getFirst());
		
		Student[] sortStudents3 = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		QuickSorter<Student> frontSelector = new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		frontSelector.sort(sortStudents3);
		
		assertEquals("Tanner", sortStudents3[0].getFirst());
		assertEquals("Roxann", sortStudents3[1].getFirst());
		assertEquals("Shanti", sortStudents3[2].getFirst());
		assertEquals("Dante", sortStudents3[3].getFirst());
		assertEquals("Cristine", sortStudents3[4].getFirst());
		assertEquals("Ara", sortStudents3[5].getFirst());
		assertEquals("Lewis", sortStudents3[6].getFirst());
		assertEquals("Charlene", sortStudents3[7].getFirst());
		assertEquals("Amber", sortStudents3[8].getFirst());
		assertEquals("Lacie", sortStudents3[9].getFirst());
		assertEquals("Idalia", sortStudents3[10].getFirst());
		assertEquals("Tyree", sortStudents3[11].getFirst());
		assertEquals("Evelin", sortStudents3[12].getFirst());
		assertEquals("Alicia", sortStudents3[13].getFirst());
		assertEquals("Loise", sortStudents3[14].getFirst());
		assertEquals("Nichole", sortStudents3[15].getFirst());
		
		Student[] sortStudents31 = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		QuickSorter<Student> middleSelector = new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		middleSelector.sort(sortStudents31);
		assertEquals("Tanner", sortStudents31[0].getFirst());
		assertEquals("Roxann", sortStudents31[1].getFirst());
		assertEquals("Shanti", sortStudents31[2].getFirst());
		assertEquals("Dante", sortStudents31[3].getFirst());
		assertEquals("Cristine", sortStudents31[4].getFirst());
		assertEquals("Ara", sortStudents31[5].getFirst());
		assertEquals("Lewis", sortStudents31[6].getFirst());
		assertEquals("Charlene", sortStudents31[7].getFirst());
		assertEquals("Amber", sortStudents31[8].getFirst());
		assertEquals("Lacie", sortStudents31[9].getFirst());
		assertEquals("Idalia", sortStudents31[10].getFirst());
		assertEquals("Tyree", sortStudents31[11].getFirst());
		assertEquals("Evelin", sortStudents31[12].getFirst());
		assertEquals("Alicia", sortStudents31[13].getFirst());
		assertEquals("Loise", sortStudents31[14].getFirst());
		assertEquals("Nichole", sortStudents31[15].getFirst());
		
		Student[] sortStudents32 = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		QuickSorter<Student> lastSelector = new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR);
		lastSelector.sort(sortStudents32);
		assertEquals("Tanner", sortStudents32[0].getFirst());
		assertEquals("Roxann", sortStudents32[1].getFirst());
		assertEquals("Shanti", sortStudents32[2].getFirst());
		assertEquals("Dante", sortStudents32[3].getFirst());
		assertEquals("Cristine", sortStudents32[4].getFirst());
		assertEquals("Ara", sortStudents32[5].getFirst());
		assertEquals("Lewis", sortStudents32[6].getFirst());
		assertEquals("Charlene", sortStudents32[7].getFirst());
		assertEquals("Amber", sortStudents32[8].getFirst());
		assertEquals("Lacie", sortStudents32[9].getFirst());
		assertEquals("Idalia", sortStudents32[10].getFirst());
		assertEquals("Tyree", sortStudents32[11].getFirst());
		assertEquals("Evelin", sortStudents32[12].getFirst());
		assertEquals("Alicia", sortStudents32[13].getFirst());
		assertEquals("Loise", sortStudents32[14].getFirst());
		assertEquals("Nichole", sortStudents32[15].getFirst());
		
		Student[] sortStudents33 = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		QuickSorter<Student> selector = new QuickSorter<Student>();
		selector.sort(sortStudents33);
		assertEquals("Tanner", sortStudents33[0].getFirst());
		assertEquals("Roxann", sortStudents33[1].getFirst());
		assertEquals("Shanti", sortStudents33[2].getFirst());
		assertEquals("Dante", sortStudents33[3].getFirst());
		assertEquals("Cristine", sortStudents33[4].getFirst());
		assertEquals("Ara", sortStudents33[5].getFirst());
		assertEquals("Lewis", sortStudents33[6].getFirst());
		assertEquals("Charlene", sortStudents33[7].getFirst());
		assertEquals("Amber", sortStudents33[8].getFirst());
		assertEquals("Lacie", sortStudents33[9].getFirst());
		assertEquals("Idalia", sortStudents33[10].getFirst());
		assertEquals("Tyree", sortStudents33[11].getFirst());
		assertEquals("Evelin", sortStudents33[12].getFirst());
		assertEquals("Alicia", sortStudents33[13].getFirst());
		assertEquals("Loise", sortStudents33[14].getFirst());
		assertEquals("Nichole", sortStudents33[15].getFirst());
	}
}
