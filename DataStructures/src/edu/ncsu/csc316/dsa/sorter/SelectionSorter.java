package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;


/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author Mario Medel
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
//	private Comparator<E> c;
    /** public constructor which defines a comparator object\
     * 
     * @param comparator the desired comparator to set for this sort
     *  */
    public SelectionSorter(Comparator<E> comparator) {
//        setComparator(comparator);
        super(comparator);
    }
    /**
     * default constructor for the comparator class 
     */
    public SelectionSorter() {
    	this(null);
    }

//    private void setComparator(Comparator<E> comparator) {
//        if(comparator == null) {
//            this.c = new NaturalOrder();
//        } else {
//            this.c = comparator;
//        }
//    }   
    /** Selection Sort algorithm using the comparator defined above 
     * @param data desired items to sort
     *  
     *  */
    public void sort(E[] data) {
        for (int i = 0; i < data.length; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++) {
				if (super.compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			if ( i != min ) {
				E temp = data[i];
				data[i] = data[min];
				data[min] = temp;
			}
		}
    }
    
//    private class NaturalOrder implements Comparator<E> {
//        public int compare(E first, E second) {
//            return ((Comparable<E>) first).compareTo(second);
//        }
//    }
}
