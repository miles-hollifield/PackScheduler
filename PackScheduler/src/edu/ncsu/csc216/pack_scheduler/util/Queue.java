/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This class is used to create queue list to store elements
 * 
 * @author jamalmohamad
 * @param <E> the generic element that used in this class.
 */
public interface Queue<E> {
	
	/**
	 * Adds the element to the back of the Queue
	 * If there is no room (capacity has been reached),
	 *  an IllegalArgumentException is thrown
	 * @param element the element that is being added to the queue
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the element at the front of the Queue
	 * If the Queue is empty, an NoSuchElementException is thrown
	 * @return  return the element at the front of the queue
	 */
	E dequeue();
	
	/**
	 * Returns true if the Queue is empty
	 * @return true if the Queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the Queue
	 * 
	 * @return return the int number of elements in the queue
	 */
	int size();
	
	/**
	 * Sets the Queue’s capacity
	 * If the actual parameter is negative or if it is 
	 * less than the number of elements in the Queue, an 1IllegalArgumentException is thrown
	 * @param capacity the capacity of the list
	 */
	void setCapacity(int capacity);
}
