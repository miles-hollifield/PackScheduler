/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for ArrayStack
 * @author mfhollif
 * @param <E> the generic type
 */
class ArrayStackTest<E> {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#ArrayStack()}.
	 */
	@Test
	void testArrayStack() {
		ArrayStack<E> stack1 = new ArrayStack<E>(15);
		assertTrue(stack1 instanceof ArrayStack);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	void testPush() {
		ArrayStack<String> stack1 = new ArrayStack<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";

		// Inserting a single element into the stack
		try {
			stack1.push(s1);
			assertEquals(1, stack1.size());

		} catch (IllegalArgumentException e) {
			fail();
		}
		// Inserting multiple elements into the stack
		try {
			stack1.push(s2);
			stack1.push(s3);
			stack1.push(s4);
			assertEquals(4, stack1.size());
		} catch (IllegalArgumentException e) {
			fail();
		} 

		/* Removing multiple elements from the stack
		 * and adding back again
		 */
		try {
			stack1.pop();
			assertEquals(3, stack1.size());
			stack1.pop();
			assertEquals(2, stack1.size());
			stack1.pop();
			assertEquals(1, stack1.size());
			stack1.pop();
			assertEquals(0, stack1.size());
			// Add new element
			stack1.push(s4);
			assertEquals(1, stack1.size());
			// Add removed element again
			stack1.push(s1);
			assertEquals(2, stack1.size());

		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#pop()}.
	 */
	@Test
	void testPop() {
		ArrayStack<String> stack1 = new ArrayStack<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";
		String s5 = "tofu";
		String s6 = "brisket";
		String s7 = "chicken";

		// Add multiple elements
		stack1.push(s1);
		stack1.push(s2);
		stack1.push(s3);
		stack1.push(s4);
		stack1.push(s5);
		stack1.push(s6);
		assertEquals(6, stack1.size());

		// Removing a single element from the stack
		try {
			stack1.pop();
			assertEquals(5, stack1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Removing multiple elements from the stack
		try {
			stack1.pop();
			assertEquals(4, stack1.size());
			stack1.pop();
			assertEquals(3, stack1.size());
			stack1.pop();
			assertEquals(2, stack1.size());
			stack1.pop();
			assertEquals(1, stack1.size());

		} catch (IllegalArgumentException e) {
			fail();
		}

		// Removing the last element from the stack
		try {
			stack1.pop();
			assertEquals(0, stack1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}

		// Adding and removing
		stack1.push(s1);
		stack1.push(s2);
		assertEquals(2, stack1.size());
		assertEquals("hotdog", stack1.pop());
		stack1.push(s3);
		stack1.push(s4);
		assertEquals("spaghetti", stack1.pop());
		stack1.push(s5);
		stack1.push(s6);
		stack1.push(s7);
		assertEquals("chicken", stack1.pop());

		// Remove all
		assertEquals("brisket", stack1.pop());
		assertEquals("tofu", stack1.pop());
		assertEquals("ramen", stack1.pop());
		assertEquals("hamburger", stack1.pop());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		ArrayStack<String> stack1 = new ArrayStack<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";

		// New stack
		assertTrue(stack1.isEmpty());

		// Add elements
		stack1.push(s1);
		stack1.push(s2);
		assertFalse(stack1.isEmpty());

		// Remove all
		stack1.pop();
		stack1.pop();
		assertTrue(stack1.isEmpty());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#size()}.
	 */
	@Test
	void testSize() {
		ArrayStack<String> stack1 = new ArrayStack<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";

		// Check size
		assertEquals(0, stack1.size());
		stack1.push(s1);
		assertEquals(1, stack1.size());
		stack1.push(s2);
		assertEquals(2, stack1.size());
		stack1.push(s3);
		assertEquals(3, stack1.size());
		stack1.push(s4);
		assertEquals(4, stack1.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#setCapacity(int)}.
	 */
	@Test
	void testSetCapacity() {
		ArrayStack<String> stack1 = new ArrayStack<String>(15);
		ArrayStack<String> stack2 = new ArrayStack<String>(15);
		ArrayStack<String> stack3 = new ArrayStack<String>(15);
		String s1 = "hamburger";
		String s2 = "hotdog";
		String s3 = "ramen";
		String s4 = "spaghetti";
		String s5 = "tofu";
		String s6 = "brisket";
		String s7 = "chips";
		String s8 = "beans";

		
		// Change capacity on empty stack
		try{
			stack3.setCapacity(20);
		} catch (IllegalArgumentException e) {
			fail();
		}
		

		// Add to stacks
		stack1.push(s1);
		stack1.push(s2);
		stack1.push(s3);
		stack1.push(s4);
		stack1.push(s5);
		//stack1.push(s6);
		
		stack2.push(s1);
		stack2.push(s2);
		stack2.push(s3);
		stack2.push(s4);
		stack2.push(s5);
		//stack2.push(s6);
		
		// Setting to valid lower capacity
		try{
			stack1.setCapacity(5);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Setting to valid higher capacity
		try{
			stack2.setCapacity(20);
			stack2.push(s6);
			assertEquals(6, stack2.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Set capacity to size
		assertEquals(5, stack1.size());
		try{
			stack1.setCapacity(6);
			stack1.push(s6);
			assertEquals(6, stack1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Increase capacity
		assertEquals(6, stack1.size());
		try{
			stack1.setCapacity(8);
			stack1.push(s7);
			stack1.push(s8);
			assertEquals(8, stack1.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Set capacity to lower than size
		assertEquals(8, stack1.size());
		try{
			stack1.setCapacity(4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(8, stack1.size());
		}
		
		// Set capacity to negative
		try{
			stack1.setCapacity(-4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(8, stack1.size());
		}
	}

}
