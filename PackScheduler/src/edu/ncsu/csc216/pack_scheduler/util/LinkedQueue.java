/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * This class is used to create a linked queue based on the interface.
 * @author jamalmohamad
 * @param <E> generic element used in the queue
 */
public class LinkedQueue<E> implements Queue<E> {
	
	/** The LinkedAbstractList queue */
	private LinkedAbstractList<E> linkedQueue;
	/** Capacity of the stack */
	private int capacity;
	/** Current size of the stack */
	private int size;
	
	/**
	 * Constructs a new LinkedQueue of given queue
	 * @param capacity the capacity of the linked queue
	 */
	public LinkedQueue(int capacity) {
		linkedQueue = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
		size = 0;
	}
	
	/**
	 * Adds the element to the back of the Queue
	 * If there is no room (capacity has been reached),
	 * an IllegalArgumentException is thrown
	 * @param element the element that is being added to the queue
	 * @throws IllegalArgumentException If there is no room (capacity has been reached)
	 */
	@Override
	public void enqueue(E element) {
		if (linkedQueue.size() < capacity) {
			linkedQueue.add(element);
			size++;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Removes and returns the element at the front of the Queue
	 * If the Queue is empty, an NoSuchElementException is thrown
	 * @throws EmptyStackException If the Queue is empty
	 * @return  return the element at the front of the queue
	 */
	@Override
	public E dequeue() {
		if (size > 0) {
			E removedElement = linkedQueue.remove(0);
			size--;
			return removedElement;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public boolean isEmpty() {
		return linkedQueue.size() == 0;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the Queue’s capacity
	 * @param capacity the capacity of the list
	 * @throws IllegalArgumentException * If the actual parameter is negative or if it is 
	 * less than the number of elements in the Queue, an 
	 * IllegalArgumentException is thrown
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
