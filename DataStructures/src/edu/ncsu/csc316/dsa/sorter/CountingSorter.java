package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author Mario Medel
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * implements the counting sort algorithm
	 * sorts integer data only
	 * @param data the integer data to sort
	 */ 
	public void sort(E[] data){
		//assume first is min and max
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 0; i < data.length; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		//calculate the range of the elements
		int k = max - min + 1;
		
		//new array with length k
		int[] b = new int[k];
		for (int i = 0; i < data.length; i++) {
			b[data[i].getId() - min] = b[data[i].getId() - min] + 1;
		}
		
		for (int i = 1; i <= k - 1; i++) {
			b[i] = b[i - 1] + b[i];
		}
		@SuppressWarnings("unchecked")
		E[] f = (E[])(new Identifiable[ data.length ]);
		
//		 int[] f = new int[data.length];
		
		for (int i = data.length - 1; i >= 0; i--) {
			f[ b[data[i].getId() - min] - 1] = data[i];
			b[ data[i].getId() - min] = b[ data[i].getId() - min] - 1;
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = f[i];
		}
	}
	
	
}
