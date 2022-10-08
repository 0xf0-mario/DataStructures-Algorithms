package edu.ncsu.csc316.dsa.map.hashing;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Mario Medel
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	/** table which uses TABLE ENTRY key value pair with linear probing for hashing */
    private TableEntry<K, V>[] table;
    /** total number of elements being stored within this hash map */ 
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
    	for (int i = 0; i < table.length; i++) {
			if(table[i] != null && !table[i].isDeleted) {
				collection.add(table[i]);
			}
		}
    	return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    @Override
    public V bucketGet(int hash, K key) {
    	int idx = findBucket(hash, key);
    	if(idx < 0) {
    		return null;
    	}
    	
    	return table[idx].getValue();
    	
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
    	int idx = findBucket(hash, key);
    	if(idx < 0) { 
    		table[-(idx + 1)] = new TableEntry<K, V>(key, value);
    		size++;
    		return null;
    	}
//    	if(table[idx].isDeleted) 
//    		table[idx].setDeleted(false);
    	V val = table[idx].getValue();
//    	table[idx] = new TableEntry<K, V>(key, value);
    	table[idx].setValue(value);
//    	size++;
    	return val;
    }
    /**
     * helper method which is used to find the bucket that contains
     * the specified key.
     * @param index hash index
     * @param key searching for this key
     * @return index of the bucket that contains the entry if it exists
//     * @return if entry not in map, return -(a + 1) where a is the index where the entry
     * should be inserted
     */
    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	do {
    		if(isAvailable(j)) {
    			if(avail == -1) { 
    				avail = j;
    			}
    			if(table[j] == null) {
    				return -(avail + 1);
    			}
    		} else if (table[j].getKey().equals(key))
    			return j;
    		j = (j + 1) % table.length;
    	} while (j != index);
    	return -(avail + 1);
    }	

    @Override
    public V bucketRemove(int hash, K key) {
        int idx = findBucket(hash, key);
        if(idx < 0) {
        	return null;
        }
        
        V val = table[idx].getValue();
    	table[idx].setDeleted(true);
    	size--;
    	return val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	/** boolean to indicate that the table entry hath been removeth */
        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}
