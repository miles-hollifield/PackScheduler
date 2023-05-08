package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This class represents the custom LinkedAbstractList with specific
 * functionality suitable for the use of it in the CourseRoll class. It can be
 * used with different objects since the array is constructed based on generic
 * objects.
 * 
 * @author Victor
 * @author Miles
 * @author Thien Nguyen
 *
 * @param <E> Generic Object of the LinkedAbstractList
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** The front node of the LinkedAbstractList */
	private ListNode front;

	/** The size of the LinkedAbstractList */
	private int size;

	/** The capacity of the LinkedAbstractList */
	private int capacity;
	
	/** The field that point to the last node in the list */
	private ListNode back;
	
	/**
	 * Constructs the LinkedAbstractList object with the null front node, size of
	 * zero and the given capacity
	 * 
	 * @param capacity capacity of list
	 * @throws IllegalArgumentException if the parameter is less than 0
	 * @throws IllegalArgumentException if the capacity is less than the current
	 *                                  list's size
	 */
	public LinkedAbstractList(int capacity) {
		setCapacity(capacity);
		front = null;
		size = 0;
	}
	
	/**
	 * Set the capacity of the LinkedAbstractList to the given number
	 * 
	 * @param capacity capacity of list
	 * @throws IllegalArgumentException if the parameter is less than 0
	 * @throws IllegalArgumentException if the capacity is less than the current
	 *                                  list's size
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * Returns capacity.
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Add the object to the LinkedAbstractList at the given index.
	 * 
	 * @param index index of object
	 * @param obj   object to be added
	 * @throws NullPointerException      if the object is null
	 * @throws IllegalArgumentException  if the object to be added is already in the
	 *                                   LinkedAbstractList
	 * @throws IndexOutOfBoundsException if the index is negative or if it is larger
	 *                                   than the size of LinkedAbstractList
	 * @throws IllegalArgumentException  if the size is equal to the capacity
	 */
	@Override
	public void add(int index, E obj) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (obj == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size(); i++) {
			if (obj.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		
		ListNode newNode = new ListNode(obj);
		
		// Add object to the front
		if (index == 0) {
			newNode.next = front;
			front = newNode;
			if (size() == 0) {
				back = newNode;
			}
			// Add object to the end
		} else if (index == size()) {
			back.next = newNode;
			back = newNode;
		}
		else {
				// Add object to the middle
				ListNode current = front;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				newNode.next = current.next;
				current.next = newNode;
				ListNode backNode = front;
				for (int j = 0; j < size; j++) {
					backNode = backNode.next;
				}
				back = backNode;
			}
		size++;
	}

	/**
	 * Remove the object of the LinkedAbstractList at the given index.
	 * 
	 * @param index index of object
	 * @return object to be removed
	 * @throws IndexOutOfBoundsException if the index is negative or if it is equal
	 *                                   to or larger than the size of
	 *                                   LinkedAbstractList
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		ListNode previous = null;
		int indexCopy = index;
		while (indexCopy > 0) {
			previous = current;
			current = current.next;
			indexCopy--;
		}
		if (current == front) {
			front = front.next;
			size--;
		} else {
			previous.next = current.next;
			size--;
		}
		return current.data;
	}

	/**
	 * Returns the size of the LinkedAbstractList.
	 * 
	 * @return size of the LinkedAbstractList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Set the object to the LinkedAbstractList at the given index.
	 * 
	 * @param index index of object
	 * @return original object at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or if it is equal
	 *                                   to or larger than the size of
	 *                                   LinkedAbstractList
	 * @throws NullPointerException      if the object to be set is null
	 * @throws IllegalArgumentException  if the object to be set is already in the
	 *                                   LinkedAbstractList
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		E value = null;
		
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
			if(current.data.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		value = current.data;
		current.data = element;
		
		return value;
	}

	/**
	 * Returns the object of the LinkedAbstractList at the given index.
	 * 
	 * @param index index of object
	 * @return object of the LinkedAbstractList at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or if it is equal
	 *                                   to or larger than the size of
	 *                                   LinkedAbstractList
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * Concrete private inner class that represents the node used in the
	 * LinkedAbstractList
	 * 
	 * @author Thien Nguyen
	 */
	private class ListNode {

		/** The data in the node */
		private E data;

		/** The next node in the list */
		private ListNode next;

		/**
		 * Constructs the node object with the given data and the next null node
		 * 
		 * @param data data in the node
		 */
		public ListNode(E data) {
			this(data, null);
		}

		/**
		 * Constructs the node object with the given data and the given next node
		 * 
		 * @param data data in the node
		 * @param next next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}