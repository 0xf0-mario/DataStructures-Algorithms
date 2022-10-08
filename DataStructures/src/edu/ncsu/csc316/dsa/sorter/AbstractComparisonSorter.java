package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
/**
 * defines the common behavior for sorters
 * @author Mario Medel
 *
 * @param <E> generic type E which extends comparable can be sorted
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/** the comparator object for the objects being compared */
    private Comparator<E> comparator;
    /**
     * public constructor for setting the comparator for the sorter 
     * @param comparator desired comparator
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    /** private method called by the constructor to set the comparator 
     * @param comparator the desired comparator to set for this sort
     * */    
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    /** defines the natural order of the E generic object based on its type */ 
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    /**
     * this is how the sorters will access the comparator field
     * @param data1 first element to compare
     * @param data2 second element to compare
     * @return the resultant of the comparison  
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}
