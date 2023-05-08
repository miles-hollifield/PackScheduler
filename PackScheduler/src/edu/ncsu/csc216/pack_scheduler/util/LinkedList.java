package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Generic LinkedList object extends the AbstractSequentialList class
 * @author mfhollif
 * @param <E> The generic type parameter
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	
	/** Front of the LinkedList */
	private ListNode front;
	/** Back of the LinkedList */
	private ListNode back;
	/** Size of the LinkedList */
	private int size;
	
	/**
	 * Constructs a new empty LinkedList object
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		size = 0;
	}

	/**
	 * Returns the size of the LinkedList
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Adds an element in the LinkedList at the given index.
	 * The element cannot be added if there is a duplicate already in the list.
	 * @param index the index to place the element
	 * @param element the element to be added
	 * @throws IllegalArgumentException if the element to be added already exists in the list
	 */
	@Override
	public void add(int index, E element) {
		ListNode current = front;
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < size; i++) {
			if (get(i).equals(element)){
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
			super.add(index, element);
		
	}
	
	
	
	@Override
	public E remove(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}		
		return super.remove(index);
	}

	/**
	 * Sets the element at the given index to the element passed into the method.
	 * The element cannot be added if there is a duplicate already in the list.
	 * @param index the index to place the element
	 * @param element the element to be added
	 * @throws IllegalArgumentException if the element to be added already exists in the list
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater than the size of the list
	 */
	@Override
	public E set(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		return super.set(index, element);
	}
	
	@Override
	public E get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return super.get(index);
	}

	
	
	/**
	 * Creates a list iterator at the given index
	 * @param index the index to insert the iterator
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		LinkedListIterator i = new LinkedListIterator(0);
		i.previous = current;
		i.next = current.next;
		i.previousIndex = index - 1;
		i.nextIndex = index;
		i.lastRetrieved = null;
		return i;
	}
	
	/**
	 * ListNode class that represents an element in the LinkedList.
	 * A ListNode will comprise of a previous element, a next element, 
	 * and it's own data.
	 * @author mfhollif
	 */
	private class ListNode {
		
		/** Data of the ListNode */
		private E data;
		/** The next element of the ListNode */
		private ListNode next;
		/** The previous element of the ListNode */
		private ListNode previous;
		
		/**
		 * Constructs a new ListNode object with default next and previous fields 
		 * set to null, and the data field set to the data passed into it.
		 * @param data the data to be set in the ListNode
		 */
		public ListNode(E data) {
			this(data, null, null);
		}

		/**
		 * Constructs a new ListNode object that sets the data, next, and previous fields
		 * to those that are passed into the constructor.
		 * @param data the data of the ListNode
		 * @param next the next ListNode
		 * @param previous the previous ListNode
		 */
		public ListNode(E data, ListNode previous, ListNode next) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
		
	}
	
	/**
	 * LinkedListIterator class that traverses the LinkedList and
	 * represents the current location of the LinkedListIterator in the
	 * LinkedList. The LinkedListIterator can move forward or backward 
	 * in the list during traversal, meaning it is always between two ListNodes.
	 * @author mfhollif
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** The previous node of the LinkedList */
		private ListNode previous;
		/** The next node of the LinkedList */
		private ListNode next;
		/** The previous index of the LinkedList */
		private int previousIndex;
		/** The next index of the LinkedList */ 
		private int nextIndex;
		/** The last retrieved ListNode of the LinkedList */
		private ListNode lastRetrieved;
		
		/**
		 * Constructs a new LinkedListIterator object that adds
		 * the iterator at the given index of the LinkedList.
		 * @param index the index to add the LinkedListIterator
		 * @throws IndexOutOfBoundsException if the index is less than 0
		 * or greater than the size of the LinkedList
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			previous = current;
			next = previous.next;
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null; 
		}
		
		/**
		 * Returns true if there is a next node in the list.
		 * Returns false if there is not a next node in the list.
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}
		
		/**
		 * Returns the element in the next ListNode.
		 * @throws NoSuchElementException if there is not an element in the next ListNode
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
		    lastRetrieved = next;
		    previous = next;
		    next = next.next;
		    nextIndex++;
		    previousIndex++;
		    return lastRetrieved.data;
		}
		
		/**
		 * Returns true if there is a previous node in the list.
		 * Returns false if there is not a previous node in the list.
		 */
		@Override
		public boolean hasPrevious() {
			return previous != null;
		}
		
		/**
		 * Returns the element in the previous ListNode.
		 * @throws NoSuchElementException if there is not an element in the previous ListNode
		 */
		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
            lastRetrieved = previous;
            next = previous;
			previous = previous.previous;
		    nextIndex--;
		    previousIndex--;
			return lastRetrieved.data;
		}
		
		/**
		 * Returns the index of the next ListNode in the list.
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}
		
		/**
		 * Returns the index of the previous ListNode in the list.
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}
		
		/**
		 * Removes the element returned by the last call
		 * to previous() or next()
		 * @throws IllegalStateException if the last retrieved node is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.previous.next = next;
			next = next.next;
			size--;
		}
		
		/**
		 * Sets the element returned by the last class to previous()
		 * or next() to the element passed into the method
		 * @throws IllegalArgumentException if the last retrieved node is null
		 * @throws NullPointerException if the element to be added is null
		 */
		@Override
		public void set(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			if (lastRetrieved == null) {
				throw new IllegalArgumentException();
			}
			lastRetrieved.data = e;
		}
		
		/**
		 * Inserts the element before the element that would be returned by next()
		 * @throws NullPointerException if the element to be added is null
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode newNode = new ListNode(e);
			newNode.previous = previous;
			newNode.next = next;
			previous.next = newNode;
			next.previous = newNode;
			lastRetrieved = null;
			size++;	
		}
		
	}

}
