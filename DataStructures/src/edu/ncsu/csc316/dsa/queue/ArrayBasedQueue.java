package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 * 
 * @author Dr. King
 * @author Mario Medel
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

    /**
     * Internal array to store the data within the queue
     */
    private E[] data;
    
    /**
     * A reference to the index of the first element in the queue
     */
    private int front;

    /**
     * A reference to the index immediately after the last element in the queue
     */
    private int rear;

    /**
     * The number of elements stored in the queue
     */
    private int size;

    /**
     * The initial default capacity of the internal array that stores the data
     */
    private static final int DEFAULT_CAPACITY = 0;

    /**
     * Constructs a new array-based queue with the given initialCapcity for the
     * array
     * 
     * @param initialCapacity the initial capacity to use when creating the initial
     *                        array
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int initialCapacity) {
        data = (E[]) (new Object[initialCapacity]);
        size = 0;
    }

    /**
     * Constructs a new array-based queue with the default initial capacity for the
     * array
     */
    public ArrayBasedQueue() { this(DEFAULT_CAPACITY); }
    
    /**
     * To ensure amortized O(1) cost for adding to the array-based queue, use the
     * doubling strategy on each resize. Here, we add +1 after doubling to handle
     * the special case where the initial capacity is 0 (otherwise, 0*2 would still
     * produce a capacity of 0).
     * 
     * @param minCapacity the minimum capacity that must be supported by the
     *                    internal array
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) { newCapacity = minCapacity; }
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) (new Object[newCapacity]);
            
            
            int dataIndex = front;
            for (int i = 0; i < size; i++) {
				if (dataIndex == oldCapacity - 1) { //once we reach the end of the list we must wrap
					newData[i] = data[dataIndex];
					dataIndex = 0;
					continue;
				}
				newData[i] = data[dataIndex++];
			}
            
            data = newData;
            
            front = 0;
            rear = size;
        }
    }

	@Override
	public void enqueue(E element) {
		ensureCapacity(size + 2);	
		data[rear] = element;
		rear = (rear + 1) % data.length;
//		rear = rear == data.length - 1 ? 0 : rear + 1;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) { throw new NoSuchElementException(); }
		
		
		E element = data[front];
		front = (front + 1) %  data.length;
//		data[front] = null;
//		if (size == 1) {
//			front = 0;
//			rear = 0;
//		} else {
//			front++;
//		}
//				
//		if (front == data.length - 1) { //once we reach the end of the list we must wrap
//			front = 0;
//		}
	
		size--;
		return element;
	}

	@Override
	public E front() { 
		if (isEmpty()) { throw new NoSuchElementException(); }
		return data[front]; 
	}

	@Override
	public int size() { return size; }
}   
