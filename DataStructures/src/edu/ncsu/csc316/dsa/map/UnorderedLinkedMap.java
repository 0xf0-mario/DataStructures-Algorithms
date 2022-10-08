package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	/** utilizes the adapter design pattern, composition of a PositionalList to implement  */ 
    private PositionalList<Entry<K, V>> list;
    /** constructor creates a new UnorderedLinkedMap object */ 
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    /**
     * private helper method to implement our searching
     * @param key to compare against every other key in the LinkedMap
     * @return the key if it was found
     */
    private Position<Entry<K, V>> lookUp(K key)
    {	
    	if (key == null) { throw new IllegalArgumentException("Key cannot be null"); }
    	//get an iterator to iterate through the positions
    	Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
    	Entry<K, V> compareKey = new MapEntry<K, V>(key, null);
        while(it.hasNext()) {
        	Position<Entry<K, V>> posFound = it.next(); //get the position
        	if (posFound.getElement().compareTo(compareKey) == 0) { //if the positions' has an entry with an equivalent key then return		
        	//old posFound.getElement().getKey().equals(key)
				return posFound;
			}
        } 
        
        return null;
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p != null) {
        	moveToFront(p);
        	return p.getElement().getValue();
        }
        return null;
    }
    /**
     * Helper method to impose a move-to-front heuristic
     * improves the average-case cost of searching for a key in our map
     * called after every get(k) or put(k,v)
     * @param position to move to the front of the linked map
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	Entry<K, V> newFront = position.getElement(); 
        list.remove(position);
    	list.addFirst(newFront);
    }

    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p != null) { //I Couldn't access the setValue method because it was an Entry obj
        	MapEntry<K, V> setKey = (MapEntry<K, V>) p.getElement(); //.getElement() return an Entry<K, V> obj
        	V oldVal = setKey.getValue();
        	setKey.setValue(value);
        	list.set(p, (Entry<K, V>) setKey);
        	moveToFront(p);
        	return oldVal;
        }
        
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
        list.addFirst(newEntry);
        return null;
    }
    
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if(p != null) { 
    	   return list.remove(p).getValue();
       }
       return null;
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;        
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
