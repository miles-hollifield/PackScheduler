package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This class is used to create a linked stack based on the interface.
 * @author Haoran Ma
 * @author mfhollif
 * @param <E> the generic type
 */
public class ArrayStack<E> implements Stack<E> {
	
	/** The ArrayList stack */
	private ArrayList<E> stack;
	/** Capacity of the stack */
	private int capacity;
	/** Current size of the stack */
	private int size;
	
	/**
	 * Constructs a new ArrayStack of given capacity
	 * @param capacity the capacity of the stack
	 */
	public ArrayStack(int capacity) {
		stack = new ArrayList<E>();
		setCapacity(capacity);
		size = 0;
	}

	/**
	 * Adds an element to the top of the stack and increments the size of the Stack
	 * @param element the element to be added to the top of the stack
	 */
	@Override
	public void push(E element) {
		if (stack.size() < capacity){
			stack.add(0, element);
			size++;
			} else {
				throw new IllegalArgumentException();
			}
	}

	/**
	 * Removes the element at the top of the stack and decrements the size of the stack
	 * @return the element removed from the stack
	 */
	@Override
	public E pop() {
		if(!isEmpty()){
			E removedElement = stack.remove(0);
			size--;
			return removedElement;
			} else {
				throw new EmptyStackException();
			}
	}

	/**
	 * Checks whether the stack contains elements
	 * @return true if there are no elements in the stack
	 */
	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/**
	 * Returns the size of the stack
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the Stack capacity as long as the capacity is positive and 
	 * larger than the current stack size
	 * @param capacity the new capacity to be set
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}