/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for LinkedListRecursive.
 * @author mfhollif
 * @author Jamal Mohamad
 */
class LinkedListRecursiveTest {
	
	/** Test string a */
	String a = "apple";
	/** Test string b */
	String b = "banana";
	/** Test string c */
	String c = "cherry";
	/** Test string d */
	String d = "dragonfruit";
	/** Test string a */
	String f = "fig";

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#LinkedListRecursive()}.
	 */
	@Test
	void testLinkedListRecursive() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#size()}.
	 */
	@Test
	void testSize() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertEquals(0, list.size());
		list.add(a);
		assertEquals(1, list.size());
		list.add(b);
		assertEquals(2, list.size());
		list.add(c);
		assertEquals(3, list.size());
		list.add(d);
		assertEquals(4, list.size());
		list.remove(a);
		assertEquals(3, list.size());
		list.remove(c);
		assertEquals(2, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertTrue(list.isEmpty());
		list.add(a);
		assertFalse(list.isEmpty());
		list.remove(a);
		assertTrue(list.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertFalse(list.contains(a));
		assertFalse(list.contains(b)); 
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d); 
		
		assertTrue(list.contains(a));
		assertTrue(list.contains(b));
		assertTrue(list.contains(c));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {
		LinkedListRecursive<String> list	= new LinkedListRecursive<String>();
		//valid add to empty list
		try{
			list.add(a);
			assertEquals(1, list.size());
		} catch(Exception e){
			fail();
		}
		
		//valid add to list with contents
		try{
			list.add(b);
			list.add(c);
			list.add(d);
			assertEquals(4, list.size());
		} catch(Exception e){
			fail();
		}
		
		//invalid add - duplicate element
		try{
			list.add(a);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(4, list.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		//valid add to empty list
		try{
			list.add(0, a);
			assertEquals(1, list.size());
			assertEquals(a, list.get(0));
		} catch(Exception e){
			fail();
		}
//		list.add(0, a);
//		assertEquals(1, list.size());
		
		//valid add to front
		try{
			list.add(0, b);
			assertEquals(2, list.size());
			assertEquals(b, list.get(0));
			assertEquals(a, list.get(1));
		} catch(Exception e){
			
			fail();
		}
		
		//list.add(2, c);
		
		
		
		//valid add to end
		try{
			list.add(2, c);
			assertEquals(3, list.size());
			assertEquals(c, list.get(2));
		} catch(Exception e){
			
			fail();
		}
		
		//valid add to middle
		try{
			list.add(1, d);
			assertEquals(4, list.size());
			assertEquals(d, list.get(1));
		} catch(Exception e){
			fail();
		}
		
		//add to front with longer list
		try{
			list.add(0, f);
			assertEquals(5, list.size());
			assertEquals(f, list.get(0));
		} catch(Exception e){
			fail();
		}
		//invalid add - duplicate
		try{
			list.add(1, d);
			fail();
		} catch(Exception e){
			assertEquals(5, list.size());
		}
		
		//invalid add - null
				try{
					list.add(1, null);
					fail();
				} catch(Exception e){
					assertEquals(5, list.size());
				}
		
		//invalid add - index low
		try{
			list.add(-1, d);
			fail();
		} catch(Exception e){
			assertEquals(5, list.size());
		}
		//invalid add - index greater than size
		try{
			list.add(7, d);
			fail();
		} catch(Exception e){
			assertEquals(5, list.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#get(int)}.
	 */
	@Test
	void testGet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		//invalid get - empty list
		try{
			list.get(0);
			fail();
		} catch (Exception e){
			assertEquals(0, list.size());
		}
		//valid get
		list.add(a);
		assertEquals(a, list.get(0));
		//invalid get - index low
		try{
			list.get(-1);
			fail();
		} catch (Exception e){
			assertEquals(1, list.size());
		}
		//invalid get - index high
		try{
			list.get(3);
			fail();
		} catch (Exception e){
			assertEquals(1, list.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		//try to remove from empty list
		try{
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(0, list.size());
		}
		
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(f);
		
		//remove from end
		assertEquals(f, list.remove(4));
		
		//remove from front
		assertEquals(a, list.remove(0));
		
		//remove from middle
		assertEquals(c, list.remove(1));
		
		//remove from after point of previous remove
		assertEquals(d, list.remove(1));
		
		//invalid remove - index low
		try{
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(1, list.size());
		}
		//invalid remove - index = size
		try{
			list.remove(1);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(1, list.size());
		}
		
		//invalid remove - index high
		try{
			list.remove(5);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(1, list.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		//try to remove from empty list
		assertFalse(list.remove(a));
		
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		
		//valid remove
		assertTrue(list.remove(a));
		assertTrue(list.remove(c));
		
		//invalid remove - not present
		assertFalse(list.remove(f));
		
		//invalid remove - null
		assertFalse(list.remove(null));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(a);
		list.add(b);
		list.add(c);
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		//valid set
		assertEquals(b, list.set(1, d));
		
		//invalid set - index low
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, f));

		//invalid set - index high
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, f));
	}

}
