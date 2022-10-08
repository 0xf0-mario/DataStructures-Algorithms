package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }
    
    @Override
    public boolean isLeaf(Position<E> p) {  return numChildren(p) == 0; }
    
    @Override
    public boolean isRoot(Position<E> p) { return p == root(); }
    
    @Override
    public boolean isEmpty() { return size() == 0; }
    
    @Override
    public E set(Position<E> p, E value) {
        //check to make sure the data is not null

//    	if(value == null) { throw new IllegalArgumentException("value cannot be null."); }
    	
    	AbstractTreeNode<E> currentNode = validate(p);
    	E oldElement = currentNode.getElement();
    	currentNode.setElement(value);
    	
    	return oldElement;
    }   
    //visit the current node and then the children
    @Override
    public Iterable<Position<E>> preOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
    	if(p != null) { traversal.add(p); }
        
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }    	
    }
    
    //visit the children then the current node
    @Override
    public Iterable<Position<E>> postOrder() {
       PositionCollection traversal = new PositionCollection();
       if(!isEmpty()) {
    	   postOrderHelper(root(), traversal);
       }
       return traversal;
    }
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
//    	if(isLeaf(p)) { return; }
    	for(Position<E> c : children(p)) {
    		postOrderHelper(c, traversal);
    	}
    	if(p != null) { traversal.add(p); } 
    }
    //use queue for level order traversals
    @Override
    public Iterable<Position<E>> levelOrder() {
    	//we set to 10 as the initial capacity but it will resize if needed
    	ArrayBasedQueue<Position<E>> nodesQ = new ArrayBasedQueue<Position<E>>(10);
        PositionCollection traversal = new PositionCollection();
        if(root() == null) { return traversal; }
        nodesQ.enqueue(root());
        while(!nodesQ.isEmpty()) {
        	Position<E> current = nodesQ.dequeue();
        	if(current != null) { traversal.add(current); }
        	for(Position<E> c : children(current)) {
        		nodesQ.enqueue(c);
        	}
        }
        
        return traversal;
    }
    
    /**
     * Safely casts a Position, p, to be an AbstractTreeNode.
     * 
     * @param p the position to cast to a AbstractTreeNode
     * @return a reference to the AbstractTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  AbstractTreeNode
     */
    protected abstract AbstractTreeNode<E> validate(Position<E> p);
    
    protected abstract static class AbstractTreeNode<E> implements Position<E> {
    	/** the element for this tree node object */
        private E element;
        
        public AbstractTreeNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * PositionCollection implements the {@link Iterable} interface to allow traversing
     * through the positions of the tree. PositionCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class PositionCollection implements Iterable<Position<E>> {
    	/** list of positions which allows us to encapsulate the remove method */
        private List<Position<E>> list;

        /**
         * Construct a new PositionCollection
         */
        public PositionCollection() {
            list = new SinglyLinkedList<Position<E>>();
        }

        /**
         * Add a position to the collection. Null positions or positions with null
         * elements are not added to the collection
         * 
         * @param position the position to add to the collection
         */
        public void add(Position<E> position) {
            if (position != null && position.getElement() != null) {
                list.addLast(position);
            }
        }

        /**
         * Return an iterator for the PositionCollection
         * @return Iterator of positions 
         */
        public Iterator<Position<E>> iterator() {
            return new PositionSetIterator();
        }

        private class PositionSetIterator implements Iterator<Position<E>> {
        	/** position iterator */
            private Iterator<Position<E>> it;

            public PositionSetIterator() {
                it = list.iterator();
            }

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Position<E> next() {
                return it.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }
}
