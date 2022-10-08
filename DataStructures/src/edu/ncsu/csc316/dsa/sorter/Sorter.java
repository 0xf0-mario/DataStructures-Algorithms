package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * 
 * @param <E> used to sort generic objects
 */
public interface Sorter<E> {
	/**
	 * sort method which will take an unsorted array of integers
	 * there is no return type for this method, the array will be sorted
	 * in ascending order
	 * @param data list of integers which are unsorted
	 */
	void sort(E[] data);
	
}
