package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for LinkedAbstractList class.
 * 
 * @author Thien Nguyen
 *
 */

public class LinkedAbstractListTest {

	/** Testing the constructor of LinkedAbstractList */
	@Test
	void testLinkedAbstractList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(3);
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		assertThrows(IllegalArgumentException.class, () -> new LinkedAbstractList<String>(-1));
	}
	
	/** Testing LinkedAbstractList.setCapacity() */
	@Test
	void testSetCapacity() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(3);
		assertEquals(0, list.size());
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		assertEquals(3, list.size());
		
		list.setCapacity(5);
		list.add(3, "eclipse");
		list.add(4, "github");
		assertEquals(5, list.size());
		assertThrows(IllegalArgumentException.class, () -> list.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> list.setCapacity(3));
	}

	/** Testing LinkedAbstractList.add() */
	@Test
	void testAdd() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(3);
		// Test adding strings at multiple positions
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		assertEquals(3, list.size());
		assertEquals("hello", list.get(0));
		assertEquals("word", list.get(1));
		assertEquals("java", list.get(2));


		// Test that exceptions are thrown for invalid inputs
		assertThrows(IllegalArgumentException.class, () -> list.add(3, "eclipse"));
		assertThrows(NullPointerException.class, () -> list.add(1, null));
		assertThrows(IllegalArgumentException.class, () -> list.add(2, "hello"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "eclipse"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(4, "eclipse"));
	}

	/** Testing LinkedAbstractList.remove() */
	@Test
	void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(3);
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		assertEquals(3, list.size());

		assertEquals("word", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("hello", list.get(0));
		assertEquals("java", list.get(1));

		assertEquals("hello", list.remove(0));
		assertEquals(1, list.size());
		assertEquals("java", list.get(0));

		// Test that exceptions are thrown for invalid inputs
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
	}

	/** Testing LinkedAbstractList.set() */
	@Test
	void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(3);
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		assertEquals(3, list.size());
		assertEquals("hello", list.get(0));
		assertEquals("word", list.get(1));
		assertEquals("java", list.get(2));

		assertEquals("word", list.set(1, "eclipse"));
		assertEquals(3, list.size());
		assertEquals("hello", list.get(0));
		//assertEquals("eclipse", list.get(1));
		assertEquals("eclipse", list.get(1));

		// Test that exceptions are thrown for invalid inputs
		assertThrows(NullPointerException.class, () -> list.set(0, null));
		assertThrows(IllegalArgumentException.class, () -> list.set(2, "java"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "hello"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "hello"));
	}

	/** Testing LinkedAbstractList.get() */
	@Test
	void testGet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(3);
		list.add(0, "word");
		assertEquals("word", list.get(0));
		list.set(0, "hello");
		assertEquals("hello", list.get(0));

		// Test that exceptions are thrown for invalid inputs
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
	}
}