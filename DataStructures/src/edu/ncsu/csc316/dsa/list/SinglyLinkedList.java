package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Mario Medel
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

	@Override
	public void add(int index, E element) {
		if (element == null) { throw new NullPointerException(); }
//		if(0 > index || index > size) { throw new IndexOutOfBoundsException(); }
		checkIndexForAdd(index);
		
		LinkedListNode<E> newNode = new LinkedListNode<E>(element);
		
		if (size == 0) { //special case
			front.next = newNode;
			tail = newNode;
			size++;
			return;
		}
		
		if (index == 0) { //special case
			newNode.next = front.next;
			front.next = newNode;
			size++;
			return;
		}
		
		LinkedListNode<E> current = front.next;
		//stop early so you can reference the next element
		for (int i = 0; i < index - 1; i++) { current = current.next; }
		
		newNode.next = current.next;
		current.next = newNode;
		size++;
		
		if (index == size - 1) { //adding to the end of the list
			tail = newNode;
		}
	}
	
	@Override
	public E get(int index) {
//		if (0 > index || index >= size) { throw new IndexOutOfBoundsException(); }
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		
		if (index == 0) {
			return front.next.data;
		}
		
		for (int i = 0; i < index; i++) { current = current.next; }
		
		return current.data;
	}

	@Override
	public E remove(int index) {
//		if (0 > index || index >= size) { throw new IndexOutOfBoundsException(); }
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		
		if (index == 0) {
			size--;
			front.next = front.next.next;
			return current.data;
		}
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		LinkedListNode<E> removed = current.next;
		current.next = current.next.next;
		
		size--;
		return removed.data;
	}

	@Override
	public E set(int index, E element) {
		if (element == null) { throw new NullPointerException(); }
//		if (0 > index || index >= size) { throw new IndexOutOfBoundsException(); }
		checkIndex(index);
		E oldData;
		
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) { current = current.next; }
		oldData = current.data;
		current.data = element;
		
		return oldData;
	}

	@Override
	public int size() { return size; }

	 /**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) { throw new IndexOutOfBoundsException("The list is empty"); }
        return tail.data;
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
    	LinkedListNode<E> newNode = new LinkedListNode<E>(element);
    	if (size == 0) {
			front.next = newNode;
			tail = newNode;
			size++;
			return;
		}
    	tail.next = newNode;
    	tail = newNode;
    	size++;
    }
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
    
    private static class LinkedListNode<E> {
        /** the data contained by this node object */
        private E data;
        /** the next node that this node refers to */
        private LinkedListNode<E> next;
        
        public LinkedListNode(E d, LinkedListNode<E> n) {
        	this.data = d;
        	this.next = n; 
        }
        
        public LinkedListNode(E d) { this(d, null); }
    }
    
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        /** 
         * Keep track of the node that was processed on the last call to 'next'
         */
        private LinkedListNode<E> previous;
        
        /** 
         * Keep track of the previous-previous node that was processed
         * so that we can update 'next' links when removing
         */
        private LinkedListNode<E> previousPrevious;
        
        /**
         * Keep track of whether it's ok to remove an element (based on whether
         * next() has been called immediately before remove())
         */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
        	previous = front;
        	previousPrevious = front;
        	current = front.next;
        	removeOK = false;
        }

        @Override
        public boolean hasNext() { return current != null; } //because the cursor is always ahead we check next to see if null

        @Override
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            E data;
            previousPrevious = previous;
            previous = current;
            data = current.data;
            current = current.next;
            removeOK = true;
            
            return data;
        }
         
        @Override    
        public void remove() {
            if (!removeOK) { throw new IllegalStateException(); }
            
            
            previousPrevious.next = current;
            previous = previousPrevious;
            removeOK = false;
            size--;
            
            if (current == null) {
				tail = previous;
			}
        }
    }

}
