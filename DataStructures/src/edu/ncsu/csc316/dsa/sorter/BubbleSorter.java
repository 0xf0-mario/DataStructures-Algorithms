/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter implements the Bubble Sort algorithm to sort comparable objects
 * 
 * @author Mario Medel
 * 
 * @param <E> Generic type E may be sorted if it extends comparable
 *
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * Constructor allows selection of comparator
	 * Calls super constructor
	 * @param comparator desired comparator
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	/**
	 * Default constructor for setting the default comparison
	 */
	public BubbleSorter() {
		this(null);
	}
	
	@Override
	public void sort(E[] data) {
		boolean r = true;
		E tempE;
		while(r) {
			r = false;
			for (int i = 1; i < data.length; i++) {
				if (compare(data[i], data[i - 1]) <  0) {
					tempE = data[i - 1];
					data[i - 1] = data[i];
					data[i] = tempE;
					r = true;
				}
			}
		}
		
	}

}
