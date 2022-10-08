package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Mario Medel
 * 
 * @param <E> Generic type which extends comparable can be sorted
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
//	/** comparator for the insertion sorter */ 
//	private Comparator<E> comparator;
	/** empty constructor able to set the comparator a default*/
	public InsertionSorter() {
		this(null); 
	}
	/** 
	 * constructor allows setting desired comparator
	 * @param c desired comparator to set
	 */
	public InsertionSorter(Comparator<E> c) {
		super(c);
	}
	
	@Override
	public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && super.compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = x;
		}
	}
//	/**
//	 * inner class which defines the natural ordering of the objects
//	 * as defined by the compareTo method
//	 * @author Mario Medel
//	 *
//	 */
//	public class NaturalOrder implements Comparator<E> {
//		/**
//		 * compare method calls the compareTo method 
//		 * obj1.compareTo(obj2)
//		 * 
//		 * @param obj1 the first object to compare
//		 * @param obj2 the second object to compare
//		 * @return integer value of the comparison
//		 */
//		public int compare(E obj1, E obj2) {
//	        return ((Comparable<E>) obj1).compareTo(obj2);		}
//	}
}
