package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {
	/** Contiguous Memory representation of a Map */
    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SearchTableMap() {
        this(null);
    }
    
    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}
     * 
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */ 
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }
    /**
     * attempts to locate the given key within the ArrayBasedMap.
     * If the key exists then a positive value will be returned
     * @param key to search for
     * @return a negative number which tells us the index to insert by adding one and negating
     */
    private int lookUp(K key) {
    	if (key == null) { throw new IllegalArgumentException("Key should not be null"); }
    	return binarySearchHelper(0, list.size() - 1, key);
    }
    /**
     * helps to determine the insertion index to maintain a sorted order within the list
     * @param min minimum index to consider 
     * @param max maximum index to consider
     * @param key to compare
     * @return insertion index as a negative integer the actual index is -1(x+1)
     */
    private int binarySearchHelper(int min, int max, K key) {
    	if( min > max ) {
    		return -1 * (min + 1);
    	}
    	int mid = (max + min) / 2;
    	if(compare(list.get(mid).getKey(), key) == 0) {
    		return mid;
    	} else if (compare(list.get(mid).getKey(), key) > 0 ) {
    		return binarySearchHelper(min, mid - 1, key);
    	} else {
    		return binarySearchHelper(mid + 1, max, key);
    	}
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public V get(K key) {
        int index = lookUp(key);
        if (index >= 0) { //if the index is greater than or equal to zero then it means the value exists
        	return list.get(index).getValue();
		}
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
    	if (key == null || value == null) { throw new IllegalArgumentException("The key and/or the value may not be null."); }
    	
        int index = lookUp(key);
        if (index >= 0) {
			V oldVal = list.get(index).getValue();
			
			MapEntry<K, V> newVal = (MapEntry<K, V>) list.get(index);
			newVal.setValue(value);
			list.set(index, newVal);
			
			return oldVal;
		} //else it does not exist, so insert it using -1(index + 1)
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
        list.add(-1 * (index + 1), newEntry);
        return null;
    }

    @Override
    public V remove(K key) {
    	if (key == null) { throw new IllegalArgumentException("I will not allow the removal of a null key!!!"); }
    	
        int index = lookUp(key);
        if (index >= 0) {
			return list.remove(index).getValue();
		}
        
        return null;
        
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
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
