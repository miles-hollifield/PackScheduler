/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Manages and constructs the recursive linked list.
 * @author jamalmohamad
 * @author mfhollif
 * @param <E> element that can be added to the list
 */
public class LinkedListRecursive<E> {
	
	/** Front of the list */
	private ListNode front;
	/** The	 size of the list */
	private int size;
	
	/**
	 * Constructs an empty list.
	 */
	public LinkedListRecursive(){
		front = null;
		size = 0;
	}
	
	/**
	 * Returns the size of the list.
	 * @return size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Checks whether or not a list has any elements.
	 * @return true if the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Checks whether a given element exists in the list.
	 * @param element the element to check against the list
	 * @return true if the element exists in the list
	 */
	public boolean contains(E element) {
		if (isEmpty()) {
			return false;
		}

		return front.contains(element);
	}
	
	/**
	 * Adds the given element to the end of the list.
	 * @param element the element to be added
	 * @return true if the element was successfully added to the list
	 */
	public boolean add(E element) {
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()){
			front = new ListNode(element, null);
			size++;
			return true;
		}	
		return front.add(element);
		
	}
	
	/**
	 * Adds the given element to the specified position of the list.
	 * @param idx index of the list to add the element
	 * @param element the element to be added to the list
	 * @throws IllegalArgumentException if the index is less than 0 or greater than
	 * the size of the list
	 */
	public void add(int idx, E element) {
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (idx == 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			if (front == null) {
				front = new ListNode(element, front);
				size++;
			} else {
				front.add(idx, element);
				size++;
			}
		}
	}
	 
	/**
	 * Retrieves the element at the given index.
	 * @param idx index of the list
	 * @return the element at the position of the list
	 * @throws IllegalArgumentException if the index is less than 0 or greater than
	 * the size of the list
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}

		E e = front.get(idx);

		return e;
		
	}
	
	/**
	 * Removes the element at the given index from the list.
	 * @param idx index of the list
	 * @return the element removed from the list
	 * @throws IllegalArgumentException if the index is less than 0 or greater than
	 * the size of the list
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
	        throw new IndexOutOfBoundsException();
	    }
		E e;
		if (idx == 0) {
			e = front.data;
			front = front.next;
			size--;
		} else {
	        e = front.remove(idx - 1);
	        size--;
	    }
	    return e;
	}
	
	/**
	 * Removes the element from the list if it exists in the list.
	 * @param element the element to be removed
	 * @return true if the element was successfully removed from the list
	 */
	public boolean remove(E element) {
		if (element == null) {
			return false;
		}
		if (isEmpty()) {
			return false;
		}
		
		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}

		return front.remove(element);
	}
	
	/**
	 * Sets the value at the given index of the list to the element passed in.
	 * @param idx the index of the list
	 * @param element the element to be set
	 * @return the element that was replaced in the list
	 * @throws IllegalArgumentException if the index is less than 0 or greater than
	 * the size of the list
	 */
	public E set(int idx, E element) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
//		if (isEmpty()) {
//			front = new ListNode(element, null);
//			size++;
//			return null;
//		}
		
		return front.set(idx, element);
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
		
		/**
		 * Constructs a new ListNode object with default next and previous fields 
		 * set to null, and the data field set to the data passed into it.
		 * @param data the data to be set in the ListNode
		 * @param next the next ListNode
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Recursive inner method of contains(E). Determines whether the list
		 * contains a certain element.
		 * @param element element we are looking for in the list.
		 * @return true if the element already exists in the list.
		 */
		public boolean contains(E element) {
			if (next == null) {
				return false;
			}
			if (data.equals(element)) {
				return true;
			}

			return next.contains(element);
		}
		
		/**
		 * The recursive inner function of add(E). 
		 * @param element element to be added to the list
		 * @return true if added successfully
		 */
		public boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			} else {
				return next.add(element);
			}
		}
		
		/**
		 * The recursive inner function of add(int,E). An element is added at
		 * the index location provided.
		 * @param idx location to add the new element
		 * @param element element to be added to the list
		 */
		public void add(int idx, E element) {
			if (idx == 1) {
				next = new ListNode(element, next);
			} else {
				if (next == null) {
					next = new ListNode(element, null);
				} else {
					next.add(idx - 1, element);
				}
			}
		}
		
		/**
		 * Recursive inner method of get(int). Returns the element located at
		 * the index location wihout removing it.
		 * @param idx location to get element
		 * @return the data in the listnode
		 */
		public E get(int idx) {
			if (idx == 0) {
		        return data;
		    } else {
		        return next.get(idx - 1);
		    }
		}
		
		/**
		 * Recursive inner method of remove(int). Removes an element at a
		 * particular index.
		 * @param idx location of element to be removed.
		 * @return the element that was removed.
		 */
		public E remove(int idx) {
			if (idx == 0) {
		        E e = this.next.data;
		        this.next = this.next.next;
		        return e;
		    } else {
		        return this.next.remove(idx - 1);
		    }
		}
		
		/**
		 * Removes a particular element from the list if it exists.
		 * @param element element we are looking for to remove.
		 * @return true if the element is able to be removed; false if otherwise.
		 */
		public boolean remove(E element) {
			if (next == null) {
		        return false;
		    } else if (next.data.equals(element)) {
		        next = next.next;
		        size--;
		        return true;
		    } else {
		        return next.remove(element);
		    }
		}
		
		/**
		 * Recursive inner method of the set(int,E) method. Sets the element at
		 * the index position to the element from the parameters.
		 * @param idx location where element should be set
		 * @param element new element to be set
		 * @return data previously in the listnode
		 */
		public E set(int idx, E element) {
			if (idx == 0) {
		        E old = data;
		        data = element;
		        return old;
		    } else {
		        return next.set(idx - 1, element);
		    }
		}
		
	}
}
