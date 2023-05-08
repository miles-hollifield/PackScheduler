/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This class represents the custom ArrayList with specific functionality
 * suitable for the use of it in the Schedule class. It can be used with
 * different objects since the array is constructed based on generic objects.
 * 
 * @author Miles
 * @author Victor Hermida
 * @author Thien Nguyen
 * 
 * @param <E> Generic Object of the Array
 */
public class ArrayList<E> extends AbstractList<E> {

	/** The initial capacity of the array */
	private static final int INIT_SIZE = 10;

	/** The size of the array */
	private int size;

	/** The array of generic object */
	private E[] list;

	/**
	 * Returns the size of the array.
	 * 
	 * @return size of the array
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the object of the array at the given index.
	 * 
	 * @param idx index of object
	 * @return object of the array at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or if it is equal
	 *                                   to or larger than the size of array
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[idx];
	}

	/**
	 * Constructs the ArrayList object with an empty ArrayList and a list of initial
	 * capacity.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		@SuppressWarnings("unused")
		E object = (E) new Object();
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}

	/**
	 * Add the object to the array at the given index.
	 * 
	 * @param idx index of object
	 * @param obj object to be added
	 * @throws IndexOutOfBoundsException if the index is negative or if it is larger
	 *                                   than the size of array
	 * @throws IllegalArgumentException  if the object to be added is already in the
	 *                                   array
	 * @throws NullPointerException      if the object is null
	 * 
	 */
	public void add(int idx, E obj) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (obj == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(obj)) {
				throw new IllegalArgumentException();
			}
		}
		if (size == list.length) {
			growArray();
		}

		for (int i = size; i > idx; i--) {
			list[i] = list[i - 1];
		}
		list[idx] = obj;
		size++;

	}

	/**
	 * Double the capacity of the current array.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		@SuppressWarnings("unused")
		E object = (E) new Object();
		E[] newList;
		newList = (E[]) new Object[list.length * 2];

		for (int i = 0; i < size; i++) {
			newList[i] = list[i];
		}

		this.list = newList;
	}

	/**
	 * Remove the object of the array at the given index.
	 * 
	 * @param idx index of object
	 * @return object to be removed
	 * @throws IndexOutOfBoundsException if the index is negative or if it is equal
	 *                                   to or larger than the size of array
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E result = list[idx];

		for (int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return result;
	}

	/**
	 * Set the object to the array at the given index.
	 * 
	 * @param idx index of object
	 * @param obj object to be set
	 * @return original object at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or if it is equal
	 *                                   to or larger than the size of array
	 * @throws IllegalArgumentException  if the object to be set is already in the
	 *                                   array
	 * @throws NullPointerException      if the object is null
	 */
	public E set(int idx, E obj) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (obj == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(obj)) {
				throw new IllegalArgumentException();
			}
		}
		E original = list[idx];
		list[idx] = obj;
		return original;
	}

}
