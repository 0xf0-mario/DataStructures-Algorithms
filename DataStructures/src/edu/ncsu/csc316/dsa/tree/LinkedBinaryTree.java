package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The LinkedBinaryTree is implemented as a linked data structure to support
 * efficient Binary Tree abstract data type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The LinkedBinaryTree class is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	/** Binary Tree Node root of the linked binary tree */
    private BinaryTreeNode<E> root;
    /** total amount of nodes in the tree */
    private int size;

    /**
     * Create a new empty binary tree
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Safely casts a Position, p, to be a BinaryTreeNode.
     * 
     * @param p the position to cast to a BinaryTreeNode
     * @return a reference to the BinaryTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  BinaryTreeNode
     */
    protected BinaryTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof BinaryTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid linked binary tree node");
        }
        return (BinaryTreeNode<E>) p;
    }

    /**
     * A BinaryTreeNode stores an element and references the node's parent, left
     * child, and right child
     * 
     * @author Dr. King
     *
     * @param <E> the type of element stored in the node
     */
    public static class BinaryTreeNode<E> extends AbstractTreeNode<E> {
        /** the parent for this binary tree node */
    	private BinaryTreeNode<E> parent;
    	/** the left node, if it has one, for this binary tree node */
        private BinaryTreeNode<E> left;
        /** the right node, if it has one, for this binary tree node */
        private BinaryTreeNode<E> right;

        /**
         * Constructs a new BinaryTreeNode with the provided element
         * 
         * @param element the element to store in the node
         */
        public BinaryTreeNode(E element) {
            this(element, null);
        }

        /**
         * Constructs a new BinaryTreeNode with the provided element and provided parent
         * reference
         * 
         * @param element the element to store in the node
         * @param parent  the parent of the newly created node
         */
        public BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Returns the left child of the current node
         * 
         * @return the left child of the current node
         */
        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child of the current node
         * 
         * @return the right child of the current node
         */
        public BinaryTreeNode<E> getRight() {
            return right;
        }

        /**
         * Sets the left child of the current node
         * 
         * @param left the node to set as the left child of the current node
         */
        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child of the current node
         * 
         * @param right the node to set as the right child of the current node
         */
        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }

        /**
         * Returns the parent of the current node
         * 
         * @return the parent of the current node
         */
        public BinaryTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of the current node
         * 
         * @param parent the node to set as the parent of the current node
         */
        public void setParent(BinaryTreeNode<E> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<E> left(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> addLeft(Position<E> p, E value) {
//    	if(value == null) {
//    		throw new IllegalArgumentException("value cannot be null");
//    	}
    	//adds element as the left child of position p, then returns a reference to the new
        BinaryTreeNode<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        //create a node  
        BinaryTreeNode<E> newNode = createNode(value, node, null, null);
        node.setLeft(newNode);
        ++size;
        return newNode;
    }

    @Override
    public Position<E> addRight(Position<E> p, E value) {
//    	if(value == null) {
//    		throw new IllegalArgumentException("value cannot be null");
//    	}
    	
    	BinaryTreeNode<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        
        //create a node
        BinaryTreeNode<E> newNode = createNode(value, node, null, null);
        node.setRight(newNode);
        ++size;
        return newNode;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    @Override
    public E remove(Position<E> p) {
    	int numChildren = numChildren(p);
        if (numChildren == 2) { throw new IllegalArgumentException("The node has two children"); }
        
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
        E elementRemoved = node.getElement();
        
        //replace node with the child if it has one
        if(numChildren == 1) {
        	//special case when removing the root node (replace)
        	if(isRoot(node) && node.left != null) { //does the root have a left?
        		root = node.left;
        		root.parent = null;
        		--size;
        		return elementRemoved;
        	} else if(isRoot(node)) { //root has a right child
        		root = node.right;
        		root.parent = null;
        		--size; 
        		return elementRemoved;
        	}
        	//replace with its child
        	if(node.getLeft() != null) { //node has a left child
        		if(node.getParent().getLeft() == node) { //node is a left child
        			node.getParent().setLeft(node.getLeft());
        		} else { node.getParent().setRight(node.getLeft()); }
        		node.getLeft().setParent(node.getParent());
        	} else { //node has a right child
        		if(node.getParent().getLeft() == node) { 
        			node.getParent().setLeft(node.getRight()); 
        		} else { node.getParent().setRight(node.getRight()); }
        		node.getRight().setParent(node.getParent());
        	}
        } else { 
        	if(size == 1) { //removing the last one
        		root = null;
        		--size;
        		return elementRemoved;
        	}
        	if(node.getParent().getLeft() == node) { node.getParent().setLeft(null); }
        	else { node.getParent().setRight(null); }
        }
        --size;
        return elementRemoved;
    }

    @Override
    public int size() {
        return size;
    }

    protected BinaryTreeNode<E> createNode(E e, BinaryTreeNode<E> parent, BinaryTreeNode<E> left,
            BinaryTreeNode<E> right) {
        BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
//
//	@Override
//	public Iterable<Position<E>> inOrder() { return super.inOrder(); }
//
//	@Override
//	public Iterable<Position<E>> preOrder() { return super.preOrder(); }
////
//	@Override
//	public Iterable<Position<E>> postOrder() { return super.postOrder(); }
////
//	@Override
//	public Iterable<Position<E>> levelOrder() { return super.levelOrder(); }
}
