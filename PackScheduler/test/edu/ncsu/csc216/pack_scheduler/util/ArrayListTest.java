/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Arraylist class.
 * 
 * @author Miles
 * @author Victor Hermida
 * @author Thien Nguyen
 * 
 * @param <E> Generic Object of the Array
 */
class ArrayListTest<E> {

	/** Testing the constructor of ArrayList */
	@Test
	void testArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
	}

	/** Testing ArrayList.add() */
	@Test
	void testAdd() {
		ArrayList<String> list = new ArrayList<String>();
		// Test adding strings at multiple positions
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		assertEquals(3, list.size());
		assertEquals("hello", list.get(0));
		assertEquals("word", list.get(1));
		assertEquals("java", list.get(2));
		list.add(1, "eclipse");
		assertEquals(4, list.size());
		assertEquals("hello", list.get(0));
		assertEquals("eclipse", list.get(1));
		assertEquals("word", list.get(2));
		assertEquals("java", list.get(3));

		// Test that the array capacity is grown when size is equal to array's length
		list.add(4, "apple");
		list.add(5, "banana");
		list.add(6, "citrus");
		list.add(7, "durian");
		list.add(8, "eagle");
		list.add(9, "food");
		list.add(10, "orange");
		assertEquals(11, list.size());

		// Test that exceptions are thrown for invalid inputs
		assertThrows(NullPointerException.class, () -> list.add(0, null));
		assertThrows(IllegalArgumentException.class, () -> list.add(0, "hello"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "hello"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(12, "hello"));

	}

	/** Testing ArrayList.remove() */
	@Test
	void testRemove() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		list.add(3, "eclipse");
		assertEquals(4, list.size());
		assertEquals("word", list.remove(1));
		assertEquals("hello", list.get(0));
		assertEquals("java", list.get(1));
		assertEquals("eclipse", list.get(2));

		assertEquals("hello", list.remove(0));
		assertEquals("java", list.get(0));
		assertEquals("eclipse", list.get(1));

		// Test that exceptions are thrown for invalid inputs
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
	}

	/** Testing ArrayList.set() */
	@Test
	void testSet() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		assertEquals(3, list.size());
		assertEquals("word", list.get(1));
		assertEquals("word", list.set(1, "eclipse"));
		assertEquals("eclipse", list.get(1));
		assertEquals(3, list.size());

		// Test that exceptions are thrown for invalid inputs
		assertThrows(NullPointerException.class, () -> list.set(0, null));
		assertThrows(IllegalArgumentException.class, () -> list.set(2, "hello"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "hello"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "hello"));
	}

	/** Testing ArrayList.get() */
	@Test
	void testGet() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "word");
		assertEquals(1, list.size());
		list.set(0, "hello");
		assertEquals("hello", list.get(0));
	}

}
