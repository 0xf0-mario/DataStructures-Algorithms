package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		if (data.length < 2) { return; } // if there is only one element then we return
		
		int mid = data.length / 2; //half the array
		E[] left = Arrays.copyOfRange(data, 0, mid); //create a left 
		E[] right = Arrays.copyOfRange(data, mid, data.length); // and a right half of the array
		
		sort(left); //sort the left
		sort(right); //sort the right
		//merge the final result
		merge(left, right, data);
	}
    
	private void merge(E[] left, E[] right, E[] result) {
		int leftI = 0;
		int rightI = 0;
		
		while(leftI + rightI < result.length) { //might need to change the comparison operation here
			if ( rightI == right.length || (leftI < left.length) && (compare(left[leftI], right[rightI]) < 0) ) {
				result[leftI + rightI] = left[leftI];
				leftI++;
			} else {
				result[ leftI + rightI ] = right[rightI];
				rightI++;
			}
		}
	}
}
