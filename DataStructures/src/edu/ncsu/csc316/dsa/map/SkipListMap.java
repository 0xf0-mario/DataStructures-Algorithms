package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;
    /** tails is one */
    private int tail = 1;
//    /** heads is zero */
//    private int head = 0;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    private SkipListNode<K, V> lookUp(K key) { //here
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public V get(K key) { //here
        SkipListNode<K, V> temp = lookUp(key);
        if(temp.entry == null) return null;
        
        if(compare(key, temp.entry.getKey()) != 0)
        	return null;
        return temp.getEntry().getValue();
    }
    /**
     * out a new node that stores entry e and pointers to the below and previous nodes
     * @param prev the node that should be previous
     * @param down the node that should be below
     * @param entry entry to store
     * @return a new node that stores entry e and pointers to the below and previous nodes
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) { //here
        //create a new skip list node
    	SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
    	//set the below and precious entries
    	newNode.setBelow(down);
    	newNode.setPrevious(prev);
    	//Update the next and previous entry pointers
    	if(prev != null) {
    		newNode.setNext(prev.getNext());
    		newNode.getPrevious().setNext(newNode);
    	}
    	if (newNode.next != null) {
			newNode.getNext().setPrevious(newNode);
		}
    	//update the below entry pointers
    	if ( down != null ) {
    		down.setAbove(newNode);
    	}
    	
    	return newNode;
    }

    @Override
    public V put(K key, V value) { 
        //Get the bottom-most entry immediately before the insertion location
    	SkipListNode<K, V> temp = lookUp(key);    	
    	//Entry with the key already exists in the map
    
		if (temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
			V orignialValue = temp.getEntry().getValue();
			while (temp != null) {
				temp.setEntry(new MapEntry<K, V>(key, value));
				temp = temp.getAbove();
			}	
			return orignialValue;
		}
	   
    	//Use q to represent the new entry as we move to the level "above" after inserting
        SkipListNode<K, V> q = null;
        //keep track of the current level we are at
        int currentLevel = -1;
        do {
        	currentLevel++;
        	//check if we need to add a new level to the top of the skip list
        	if (currentLevel >= height) {
				//increase the height of the skip list
        		height++;
        		//create a pointer to the current tail of the topmost list
        		SkipListNode<K, V> currentTail = start.next;
        		//insert a new sentinel start node above
        		start = insertAfterAbove(null, start, null);
        		//Insert a new sentinel tail node above
        		insertAfterAbove(start, currentTail, null);
			}
        	//Insert the new entry into current level of the list
        	q = insertAfterAbove(temp, q, new MapEntry<K, V>(key, value));
        	//backTrack to the entry immediately before the insertion location in the level "above"
        	while (temp.above == null) {
        		temp = temp.getPrevious();
        	}
        	temp = temp.above;
        } while (coinToss.nextInt(2) == tail);
        size++;
        return null;
    }

    @Override
    public V remove(K key) { //here
        SkipListNode<K, V> temp = lookUp(key);
        if (temp.getEntry() == null) { return null; }
        
        for(int i = 0; i < 1 || temp.above != null; i++) {
        //we do this once since the entry is not null
        	temp.prev.setNext(temp.next);
        	temp.next.setPrevious(temp.prev);
        	//we are at the bottom so we make a check 
        	if(temp.above != null) temp = temp.above;
        }
        
        if (isSentinel(start.below.getNext())) {
			start.setNext(null);
			start = start.below;
			start.setAbove(null);
		}
        
    
        size--;
        return temp.entry.getValue();
    }

    @Override
    public int size() { return size; }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
//    public String toFullString() {
//        StringBuilder sb = new StringBuilder("SkipListMap[\n");
//        SkipListNode<K, V> cursor = start;
//        SkipListNode<K, V> firstInList = start;
//        while (cursor != null) {
//            firstInList = cursor;
//            sb.append("-INF -> ");
//            cursor = cursor.next;
//            while (cursor != null && !isSentinel(cursor)) {
//                sb.append(cursor.getEntry().getKey() + " -> ");
//                cursor = cursor.next;
//            }
//            sb.append("+INF\n");
//            cursor = firstInList.below;
//        }
//        sb.append("]");
//        return sb.toString();
//    }

    private static class SkipListNode<K, V> {
    	/** Entry for this node which contains a Key-Value pair */
        private Entry<K, V> entry;
        /** the SkipList Node thats above THIS SkipListNode */
        private SkipListNode<K, V> above;
        /** the SkipList Node thats below THIS SkipListNode */
        private SkipListNode<K, V> below;
        /** the SkipListNode thats behind THIS SkipListNode */
        private SkipListNode<K, V> prev;
        /** the SkipListNode thats next for this SkipListNode */
        private SkipListNode<K, V> next;
        /**
         * constructor for an individual SkipListNode
         * @param entry to add for this node
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        /**
         * Retrieves the node thats directly above THIS node
         * @return the node that is above THIS node
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }
        /**
         * The entry contained within this node object
         * @return the entry contained
         */
        public Entry<K, V> getEntry() {
            return entry;
        }
        /**
         * Retrieves the next node in the list which this node points to
         * @return the next node in the list
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }
        /**
         * Retrieves the previous node in the list which this node points to
         * @return the previous node in the list
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }
        /**
         * sets the above node to the provided parameter
         * @param up SkipListNode object for THIS node object to point to
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }
        /**
         * sets the below node for this object to the provided parameter
         * @param down SkipListNode object that THIS node object points to
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }
        /**
         * sets the entry contained in the node
         * @param entry new entry to set for this node object
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }
        /**
         * sets the next SkipListNodeObject
         * @param next SkipListNode object to point to
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }
        /**
         * sets the previous SkipListNode Object
         * @param prev reference to point to
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}