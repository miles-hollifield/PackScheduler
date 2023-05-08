/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedQueue class
 * 
 * @author jamalmohamad
 *
 * @param <E> The generic type element being used in the list
 */
public class LinkedQueueTest<E> {

	/**
	 * Test method for LinkedQueue constructor
	 */
	@Test
	void testLinkedQueue() {
		LinkedQueue<E> queue1 = new LinkedQueue<E>(15);
		assertTrue(queue1 instanceof LinkedQueue);
	}

	/**
	 * Test method for enqueue
	 */
	@Test
	void testEnqueue() {
		LinkedQueue<String> queue1 = new LinkedQueue<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";

		// Inserting a single element into the queue
		try {
			queue1.enqueue(s1);
			assertEquals(1, queue1.size());

		} catch (IllegalArgumentException e) {
			fail();
		}
		// Inserting multiple elements into the queue
		try {
			queue1.enqueue(s2);
			queue1.enqueue(s3);
			queue1.enqueue(s4);
			assertEquals(4, queue1.size());
		} catch (IllegalArgumentException e) {
			fail();
		} 

		/* Removing multiple elements from the queue
		 * and adding back again
		 */
		try {
			queue1.dequeue();
			assertEquals(3, queue1.size());
			queue1.dequeue();
			assertEquals(2, queue1.size());
			queue1.dequeue();
			assertEquals(1, queue1.size());
			queue1.dequeue();
			assertEquals(0, queue1.size());
			// Add new element
			queue1.enqueue(s4);
			assertEquals(1, queue1.size());
			// Add removed element again
			queue1.enqueue(s1);
			assertEquals(2, queue1.size());

		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for dequeue
	 */
	@Test
	void testDequeue() {
		LinkedQueue<String> queue1 = new LinkedQueue<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";
		String s5 = "tofu";
		String s6 = "brisket";
		String s7 = "chicken";

		// Add multiple elements
		queue1.enqueue(s1);
		queue1.enqueue(s2);
		queue1.enqueue(s3);
		queue1.enqueue(s4);
		queue1.enqueue(s5);
		queue1.enqueue(s6);
		assertEquals(6, queue1.size());

		// Removing a single element from the queue
		try {
			queue1.dequeue();
			assertEquals(5, queue1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Removing multiple elements from the queue
		try {
			queue1.dequeue();
			assertEquals(4, queue1.size());
			queue1.dequeue();
			assertEquals(3, queue1.size());
			queue1.dequeue();
			assertEquals(2, queue1.size());
			queue1.dequeue();
			assertEquals(1, queue1.size());

		} catch (IllegalArgumentException e) {
			fail();
		}

		// Removing the last element from the queue
		try {
			queue1.dequeue();
			assertEquals(0, queue1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}

		// Adding and removing
		queue1.enqueue(s1);
		queue1.enqueue(s2);
		assertEquals(2, queue1.size());
		assertEquals("hamburger", queue1.dequeue());
		queue1.enqueue(s3);
		queue1.enqueue(s4);
		assertEquals("hotdog", queue1.dequeue());
		queue1.enqueue(s5);
		queue1.enqueue(s6);
		queue1.enqueue(s7);
		assertEquals("ramen", queue1.dequeue());

		// Remove all
		assertEquals("spaghetti", queue1.dequeue());
		assertEquals("tofu", queue1.dequeue());
		assertEquals("brisket", queue1.dequeue());
		assertEquals("chicken", queue1.dequeue());
	}

	/**
	 * Test method for if the Queue is empty
	 */
	@Test
	void testIsEmpty() {
		LinkedQueue<String> queue1 = new LinkedQueue<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";

		// New queue
		assertTrue(queue1.isEmpty());

		// Add elements
		queue1.enqueue(s1);
		queue1.enqueue(s2);
		assertFalse(queue1.isEmpty());

		// Remove all
		queue1.dequeue();
		queue1.dequeue();
		assertTrue(queue1.isEmpty());

	}

	/**
	 * Test method for testing the size of the Queue
	 */
	@Test
	void testSize() {
		LinkedQueue<String> queue1 = new LinkedQueue<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";

		// Check size
		assertEquals(0, queue1.size());
		queue1.enqueue(s1);
		assertEquals(1, queue1.size());
		queue1.enqueue(s2);
		assertEquals(2, queue1.size());
		queue1.enqueue(s3);
		assertEquals(3, queue1.size());
		queue1.enqueue(s4);
		assertEquals(4, queue1.size());
	}

	/**
	 * Test method for tests setting the capacity of the queue
	 */
	@Test
	void testSetCapacity() {
		LinkedQueue<String> queue1 = new LinkedQueue<String>(15);
		LinkedQueue<String> queue2 = new LinkedQueue<String>(15);
		LinkedQueue<String> queue3 = new LinkedQueue<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";
		String s5 = "tofu";
		String s6 = "brisket";
		String s7 = "chips";
		String s8 = "beans";

		
		// Change capacity on empty queue
		try{
			queue3.setCapacity(20);
		} catch (IllegalArgumentException e) {
			fail();
		}
		

		// Add to queues
		queue1.enqueue(s1);
		queue1.enqueue(s2);
		queue1.enqueue(s3);
		queue1.enqueue(s4);
		queue1.enqueue(s5);
		//queue1.enqueue(s6);
		
		queue2.enqueue(s1);
		queue2.enqueue(s2);
		queue2.enqueue(s3);
		queue2.enqueue(s4);
		queue2.enqueue(s5);
		//queue2.dequeue(s6);
		
		// Setting to valid lower capacity
		try{
			queue1.setCapacity(5);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Setting to valid higher capacity
		try{
			queue2.setCapacity(20);
			queue2.enqueue(s6);
			assertEquals(6, queue2.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Set capacity to size
		assertEquals(5, queue1.size());
		try{
			queue1.setCapacity(6);
			queue1.enqueue(s6);
			assertEquals(6, queue1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Increase capacity
		assertEquals(6, queue1.size());
		try{
			queue1.setCapacity(8);
			queue1.enqueue(s7);
			queue1.enqueue(s8);
			assertEquals(8, queue1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Set capacity to lower than size
		assertEquals(8, queue1.size());
		try{
			queue1.setCapacity(4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(8, queue1.size());
		}
		
		// Set capacity to negative
		try{
			queue1.setCapacity(-4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(8, queue1.size());
		}
	}
}
