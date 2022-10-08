/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.io.StudentReader;

/**
 * MergerSorterTest will ensure that MergerSorter is appropriately sorting the provided data
 * 
 * @author Mario Medel
 *
 */
public class MergeSorterTest {

	/** sorts students based on ID */
	MergeSorter<Student> studentIdSort;
	/** sorts students based on GPA */
	MergeSorter<Student> studentGPASort;
	/**
	 * setup before test case
	 * @throws java.lang.Exception if the object cannot be created
	 */
	@Before
	public void setUp() throws Exception {
		StudentIDComparator idCompare = new StudentIDComparator();
		studentIdSort = new MergeSorter<Student>(idCompare);
		
		StudentGPAComparator gpaCompare = new StudentGPAComparator();
		studentGPASort = new MergeSorter<Student>(gpaCompare);
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
	}


}
