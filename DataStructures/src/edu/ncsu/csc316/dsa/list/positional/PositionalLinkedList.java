package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Mario Medel
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }
     
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        if (element == null) { throw new IllegalArgumentException(); }
    	
    	PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
        
        prev.setNext(newNode);
        next.setPrevious(newNode);
        
        size++;
        return newNode;
    }

	@Override
	public Iterator<E> iterator() { return new ElementIterator(); }

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> newP = validate(p);
		return addBetween(element, newP.next, newP);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> newP = validate(p);
		return addBetween(element, newP, newP.previous);
	}

	@Override
	public Position<E> addFirst(E element) { return addBetween(element, front.next, front); }

	@Override
	public Position<E> addLast(E element) { return addBetween(element, tail, tail.previous); }

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> newP = validate(p);
		if (newP.getNext().equals(tail)) {
			return null;
		}
		
		return newP.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> newP = validate(p);
		if (newP.getPrevious().equals(front)) {
			return null;
		}
		return newP.getPrevious();
	}

	@Override
	public Position<E> first() {
		if (isEmpty()) { return null; }
		PositionalNode<E> newP = validate(front.next);
		return newP;
	}

	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public Position<E> last() {
		if (isEmpty()) {
			return null;
		}
		PositionalNode<E> newP = validate(tail.previous);
		return newP;
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> newP = validate(p);
		E removed = newP.getElement();
		
		if (isEmpty()) { return null; }
		
		if (size == 1) {
			front.next = tail;
			tail.previous = front;
			front.previous = null;
			tail.next = null;
			size--;
			return removed;
		}
		
		newP.previous.setNext(newP.next);
		newP.next.setPrevious(newP.previous);
		
		
		size--;
		return removed;
	}

	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> newP = validate(p);
		E oldData = newP.getElement();
		
		newP.setElement(element);
		
		return oldData;
	}

	@Override
	public int size() { return size; }
	
	/**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }

	 private static class PositionalNode<E> implements Position<E> {
		 	/** element stored by this positional node */
	        private E element;
	        /** next element that this node points to */
	        private PositionalNode<E> next;
	        /** the previous element that this node points to */
	        private PositionalNode<E> previous;

	        public PositionalNode(E value) {
	            this(value, null);
	        }

	        public PositionalNode(E value, PositionalNode<E> next) {
	            this(value, next, null);
	        }

	        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
	            setElement(value);
	            setNext(next);
	            setPrevious(prev);
	        }

	        public void setPrevious(PositionalNode<E> prev) {
	            previous = prev;
	        }

	        public PositionalNode<E> getPrevious() {
	            return previous;
	        }
	        
	        public void setNext(PositionalNode<E> next) {
	            this.next = next;
	        }

	        public PositionalNode<E> getNext() {
	            return next;
	        }

	        @Override
	        public E getElement() {
	            return element;
	        }
	        
	        public void setElement(E element) {
	            this.element = element;
	        }
	    }
	 	/**
	 	 * PostionIterator class will iterate and return the positions rather than elements like the iterator
	 	 * @author Mario Medel
	 	 *
	 	 */
	    private class PositionIterator implements Iterator<Position<E>> {
	    	/** current position cursor (this is returned after the next call to next) */
	        private Position<E> current;
	        /** boolean to see if it is okay to remove */
	        private boolean removeOK;

	        public PositionIterator() {
	           current = front.next;
	           removeOK = false;
	        }

	        @Override
	        public boolean hasNext() { 
	        	PositionalNode<E> newP = validate(current);
	        	return !newP.equals(tail); 
	        }

	        @Override
	        public Position<E> next() {
	            if (!hasNext()) { throw new NoSuchElementException(); }
	            Position<E> pos = current;
	            PositionalNode<E> newP = validate(pos);
	            current = newP.next;
	            removeOK = true;
	            return pos;
	        }

	        @Override
	        public void remove() {
	            if (!removeOK) { throw new IllegalStateException(); }
	            removeOK = false;
	            PositionalNode<E> newP = validate(current);
	            PositionalLinkedList.this.remove(newP);
	            
	        }
	        
	    }
	    
	    private class PositionIterable implements Iterable<Position<E>> {
	        
	        @Override
	        public Iterator<Position<E>> iterator() {
	            return new PositionIterator();
	        }
	    }
	    /**
	     * ElementIterator will iterator through the elements and return them
	     * @author Mario Medel
	     *
	     */
	    private class ElementIterator implements Iterator<E> {
	    	/** iterates through the elements in the positions */
	        private Iterator<Position<E>> it;

	        public ElementIterator() {
	            it = new PositionIterator();
	        }

	        @Override
	        public boolean hasNext() {
	            return it.hasNext();
	        }

	        @Override
	        public E next() {
	            return it.next().getElement();
	        }

	        @Override
	        public void remove() {
	            it.remove();
	        }
	    }

}