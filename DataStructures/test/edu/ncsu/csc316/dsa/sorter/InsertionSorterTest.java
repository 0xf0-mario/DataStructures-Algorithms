package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Insertion Sorter Test used to ensure proper functionality of the sort algorithm
 * based on the defined natural order of the object
 * @author Mario Medel
 *
 */
public class InsertionSorterTest {
	/** integer data in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** integer data in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** integer data in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** insertion sorter object for the integer type */
	private InsertionSorter<Integer> integerSorter;
	/**
	 * used before each test case
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<Integer>();
	}
	/**
	 * test case for sorting integers
	 * @test for InsertionSorter class.
	 * will test ascending, descending, random, and duplicate cases. 
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(0, dataAscending[0].compareTo(1));
		assertEquals(0, dataAscending[1].compareTo(2));
		assertEquals(0, dataAscending[2].compareTo(3));
		assertEquals(0, dataAscending[3].compareTo(4));
		assertEquals(0, dataAscending[4].compareTo(5));

		integerSorter.sort(dataDescending);
		assertEquals(0, dataDescending[0].compareTo(1));
		assertEquals(0, dataDescending[1].compareTo(2));
		assertEquals(0, dataDescending[2].compareTo(3));
		assertEquals(0, dataDescending[3].compareTo(4));
		assertEquals(0, dataDescending[4].compareTo(5));

		integerSorter.sort(dataRandom);
		assertEquals(0, dataRandom[0].compareTo(1));
		assertEquals(0, dataRandom[1].compareTo(2));
		assertEquals(0, dataRandom[2].compareTo(3));
		assertEquals(0, dataRandom[3].compareTo(4));
		assertEquals(0, dataRandom[4].compareTo(5));
		
		//sorting with duplicate in a list
		Integer[] duplicates = {1, 2, 3, 3, 2, 1};
		integerSorter.sort(duplicates);
		assertEquals(0, duplicates[0].compareTo(1));
		assertEquals(0, duplicates[1].compareTo(1));
		assertEquals(0, duplicates[2].compareTo(2));
		assertEquals(0, duplicates[3].compareTo(2));
		assertEquals(0, duplicates[4].compareTo(3));
		assertEquals(0, duplicates[5].compareTo(3));
		
	}
}
