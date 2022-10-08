package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Mario Medel
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    /**
     * Concrete implementation of List interface add method
     * @throws NullPointerException if the element is null
     * @throws IndexOutOfBoundsException if the index is not in the correct bounds
     */
	@Override
	public void add(int index, E element) {
		//ensure that we are able to add to the list using the size field
		ensureCapacity(size + 1);
		
		if (element == null) { throw new NullPointerException(); }
//		if (0 > index || index > size) { throw new IndexOutOfBoundsException(); }
		checkIndexForAdd(index);
		
		
		//shift elements to the right make room for the new one
		for (int i = size - 1; i >= index; i--) { data[i + 1] = data[i]; }
		//add the element to the list
		data[index] = element;
		//maintain size
		size++;
	}
	
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = 2 * oldCapacity + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    /**
     * getter method for the data in the list
     * @throws IndexOfBoundsException if the index to retrieve is not valid
     */
	@Override
	public E get(int index) {
//		if (0 > index || index >= size) { throw new IndexOutOfBoundsException(); }
		checkIndex(index);
		return data[index];
	}
	/**
	 * removes and returns the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is not valid
	 * @return the removed element
	 */
	@Override
	public E remove(int index) {
		//check to see if valid index
//		if (0 > index || index >= size) { throw new IndexOutOfBoundsException(); }
		checkIndex(index);
		E removedE = data[index];
		
		if (size == 1) {
			data[index] = null;
			--size;
			return removedE;
		}
		//we must now start at the index and shift everything to the left
		for (int i = index; i < size - 1; i++) { data[i] = data[i + 1]; }
		//maintain size
		--size;
		return removedE;
	}
	/**
	 * sets the element at the specified index to the provided element
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is not in the correct bound
	 * @return the old element
	 */
	@Override
	public E set(int index, E element) {
		//ensure that there are no null elements being set
		if (element == null) { throw new NullPointerException(); }
		//check if valid index (cannot set at size because it is not an element)
//		if (0 > index || index >= size) { throw new IndexOutOfBoundsException(); }
		checkIndex(index);
		
		E oldElement = data[index];
		data[index] = element;
		
		return oldElement;
	}

	@Override
	public int size() { return size; }

	@Override
	public Iterator<E> iterator() { return new ElementIterator(); }
	/**
	 * element iterator for the ArrayBasedList class which iterates through generic elements
	 * implements Iterator class in java
	 * @author Mario Medel
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    /** the current "index" position in the iteration */
		private int position;
		/** results in true if it is okay to remove the element */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	    	position = 0;
	    	removeOK = false;
	    }

	    @Override
	    public boolean hasNext() { return position < size; }

	    @Override
	    public E next() {
	    	if(!hasNext()) { throw new NoSuchElementException(); }
	    	removeOK = true;
	    	return get(position++);
	    }
	        
	    @Override
	    public void remove() { 
	    	if (removeOK) { 
	    		removeOK = false;
	    		ArrayBasedList.this.remove(--position); 
	    	} else {
	    		throw new IllegalStateException();
	    	}
	    }
	}
    
    
}