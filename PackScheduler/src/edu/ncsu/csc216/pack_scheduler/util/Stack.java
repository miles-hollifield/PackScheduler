package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This class is used to create stack list to store elements
 * @author Haoran Ma
 *
 * @param <E> the generic element that used in this class.
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the stack 
	 * @param element the generic element
	 */
	void push(E element);
	/**
	 * Removes and returns the element at the top of the stack.
	 * @return returns the element at the top of the stack
	 */
	E pop();
	/**
	 * Returns true if the stack is empty 
	 * @return true if its empty
	 */
	boolean isEmpty();
	/**Returns the number of elements in the stack 
	 * 
	 * @return Returns the number of elements in the stack
	 */
	int size();
	/**Sets the stack’s capacity
	 * 
	 * @param capacity the capacity need to be set.
	 */
	void setCapacity(int capacity);
}
