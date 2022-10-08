package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    //visit left child then current and then right child
    @Override
    public Iterable<Position<E>> inOrder() {
        PositionCollection traversal = new PositionCollection();
        if(!isEmpty()) {
        	inOrderHelper(root(), traversal);
        } 
        return traversal;
    }
    
    private void inOrderHelper(Position<E> p, PositionCollection traversal) { 
    	if(p == null) {  return; }
    	inOrderHelper(left(p), traversal);
    	traversal.add(p);
    	inOrderHelper(right(p), traversal);
    }
    
    @Override
    public int numChildren(Position<E> p) {
    	//check if valid position
//    	if(p == null) { throw new IllegalArgumentException("Position is null"); }
    	
//    	Iterable<Position<E>> positionChildren = children(p);
    	int totalChildren = 0;
//    	for(Position<E> child : positionChildren) {
//    		if(child != null) {
//    			totalChildren++;
//    		}
//    	}
    	totalChildren += left(p) != null ? 1 : 0;
    	totalChildren += right(p) != null ? 1 : 0;
    	
    	return totalChildren;
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {    	
    	Position<E> parent = parent(p);
    	if(left(parent) != p) {
    		return left(parent);
    	} else {
    		return right(parent);
    	}
    	
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}