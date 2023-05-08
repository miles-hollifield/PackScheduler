package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedList class.
 * 
 * @author Miles
 * @author Victor Hermida
 * @author Thien Nguyen
 * 
 * @param <E> Generic Object of the Array
 */
class LinkedListTest<E> {

	/** Testing the constructor of LinkedList */
	@Test
	void testLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
	}

	/** Testing LinkedList.add() */
	@Test
	void testAdd() {
		LinkedList<String> list = new LinkedList<String>();
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

	/** Testing LinkedList.remove() */
	@Test
	void testRemove() {
		LinkedList<String> list = new LinkedList<String>();
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

	/** Testing LinkedList.set() */
	@Test
	void testSet() {
		LinkedList<String> list = new LinkedList<String>();
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
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "hello123"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "hello123"));
		assertThrows(IllegalArgumentException.class, () -> list.set(1, "hello"));
	}

	/** Testing LinkedList.get() */
	@Test
	void testGet() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "word");
		assertEquals(1, list.size());
		list.set(0, "hello");
		assertEquals("hello", list.get(0));
	}

	/**
	 * Test previous and next methods in the iterator
	 * as well as has next and previous
	 */
	@Test
	void testPreviousAndNext() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "hello");
		list.add(1, "word");
		list.add(2, "java");
		list.add(3, "goku");
		list.add(4, "denji");
		list.add(5, "eren");
		list.add(6, "levi");
		list.add(7, "vegeta");
		list.add(8, "deku");
		list.add(9, "ash");
		list.add(10, "naruto");
		assertEquals(list.size(), 11);

		list.add(5, "spike");

		assertEquals("spike", list.get(5));
		assertEquals("denji", list.listIterator(5).previous());
		assertEquals("goku", list.listIterator(4).previous());
		assertEquals("ash", list.listIterator(11).previous());
		assertEquals("denji", list.listIterator(4).next());
		assertEquals("ash", list.listIterator(10).next());
		assertEquals(6, list.listIterator(7).previousIndex());
		assertEquals(7, list.listIterator(7).nextIndex());

		assertTrue(list.listIterator(8).hasNext());
		assertTrue(list.listIterator(8).hasPrevious());

		list.set(4, "mob");
		assertEquals("mob", list.get(4));

	}	
	
	@Test
	void testAddIntEJenkins() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "apple");
		list.add(0, "orange");
		assertEquals("orange", list.get(0));
	}
	
	@Test
	void testLastIndexOf() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("orange");
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		assertEquals("orange", list.get(0));
		assertEquals(0, list.lastIndexOf("orange"));

	}
	
}
