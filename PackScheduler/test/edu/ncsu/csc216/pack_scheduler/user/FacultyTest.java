package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Test the methods in the Faculty class
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 * @author Victor Hermida
 *
 */
public class FacultyTest {
	
	/** Course name */
	private static final String FIRSTNAME = "John";
	/** Course title */
	private static final String LASTNAME = "Booth";
	/** Course section */
	private static final String ID = "jwbooth";
	/** Course credits */
	private static final String EMAIL = "jbooth@ncsu.edu";
	/** Course password */
	private static final String PASSWORD = "password";
	/** Max number of courses a Faculty can teach */
	private static final int MAXCOURSES = 3;
	/** Min number of courses a Faculty can teach */
	private static final int MINCOURSES = 1;
	
	/** First Faculty Object */
	Faculty fac1 = new Faculty("John", "Booth", "jwbooth", "jbooth@ncsu.edu", "password", 1);
	/** Second Faculty Object */
	Faculty fac2 = new Faculty("Robert", "Phone", "rphone", "rphone@ncsu.edu", "Clever", 3);
	/** Third Faculty Object */
	Faculty fac3 = new Faculty("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 2);
	/** Fourth Faculty Object */
	Faculty fac4 = new Faculty("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 2);
	/** Fifth Faculty Object */
	Faculty fac5 = new Faculty("Joe", "Mama", "jmama", "jmama@ncsu.edu", "Mymom23", 1);
	/** Sixth Faculty Object */
	Faculty fac6 = new Faculty("Itachi", "Uchiha", "iuchiha", "iuchiha@ncsu.edu", "Clanslayer01", 2);
	/** Seventh Faculty Object */
	Faculty fac7 = new Faculty("Rick", "Grimes", "rgrimes", "rgrimes@ncsu.edu", "Carollll1", 1);
	/** Eighth Faculty Object */
	Faculty fac8 = new Faculty("Joe", "Goldberg", "jgoldberg", "jgoldberg@ncsu.edu", "You44", 1);
	/** Ninth Faculty Object */
	Faculty fac9 = new Faculty("Joe", "Goldberg", "jgoldberg", "jgoldberg@ncsu.edu", "You44", 1);
	
	/**
	 * Tests Faculty.hashCode()
	 */
	@Test
	public void testHashCode() {
		
		assertEquals(fac3.hashCode(), fac4.hashCode());
	}
	/**
	 * Test Faculty constructor with given credit hours
	 */
	@Test
	public void testFacultyConstructorWithCourses() {
		
		assertEquals("John", fac1.getFirstName());
		assertEquals("Booth", fac1.getLastName());
		assertEquals("jwbooth", fac1.getId());
		assertEquals("jbooth@ncsu.edu", fac1.getEmail());
		assertEquals("password", fac1.getPassword());
		assertEquals(1, fac1.getMaxCourses());
	}
	
//	/**
//	 * Test Faculty constructor without putting courses (uses default courses value!)
//	 */
//	@Test
//	public void testStudentConstructorWithoutCredits() {
//		//might come back
//		assertEquals("Robert", fac2.getFirstName());
//		assertEquals("Phone", fac2.getLastName());
//		assertEquals("rphone", fac2.getId());
//		assertEquals("rphone@ncsu.edu", fac2.getEmail());
//		assertEquals("Clever", fac2.getPassword());
//		assertEquals(3, fac2.getMaxCourses());
//		Exception e = assertThrows(IllegalArgumentException.class, () -> new Faculty("Robert", "Daniels", "", "rdaniel@ncsu.edu", "Arpensas32@"));
//		assertEquals("Invalid id", e.getMessage());
//
//	}
	
	/**
	 * Test Faculty.setFirstName()
	 */
	@Test
	public void testSetFirstName() {
		
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> fac1.setFirstName(null));
		
		assertEquals("Invalid first name", e1.getMessage());
		assertEquals("John", fac1.getFirstName()); 
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> fac2.setFirstName(null));
		
		assertEquals("Invalid first name", e2.getMessage());
		assertEquals("Robert", fac2.getFirstName());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> fac3.setFirstName(null));
		
		assertEquals("Invalid first name", e3.getMessage());
		assertEquals("Ben", fac3.getFirstName());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> fac5.setFirstName(null));
		
		assertEquals("Invalid first name", e4.getMessage());
		assertEquals("Joe", fac5.getFirstName());
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> fac6.setFirstName(null));
		
		assertEquals("Invalid first name", e5.getMessage());
		assertEquals("Itachi", fac6.getFirstName());
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> fac7.setFirstName(null));
		
		assertEquals("Invalid first name", e6.getMessage());
		assertEquals("Rick", fac7.getFirstName());
		
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> fac8.setFirstName(null));
		
		assertEquals("Invalid first name", e7.getMessage());
		assertEquals("Joe", fac8.getFirstName());
		
		
	}
	/**
	 * Test Faculty.setLastName()
	 */
	@Test
	public void testSetLastName() {

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> fac1.setLastName(null));
		
		assertEquals("Invalid last name", e1.getMessage());
		assertEquals("Booth", fac1.getLastName()); 
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> fac2.setLastName(null));
		
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals("Phone", fac2.getLastName()); 
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> fac3.setLastName(null));
		
		assertEquals("Invalid last name", e3.getMessage());
		assertEquals("Dover", fac3.getLastName()); 
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> fac5.setLastName(null));
		
		assertEquals("Invalid last name", e4.getMessage());
		assertEquals("Mama", fac5.getLastName()); 
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> fac6.setLastName(null));
		
		assertEquals("Invalid last name", e5.getMessage());
		assertEquals("Uchiha", fac6.getLastName()); 
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> fac7.setLastName(null));
		
		assertEquals("Invalid last name", e6.getMessage());
		assertEquals("Grimes", fac7.getLastName()); 
		
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> fac8.setLastName(null));
		
		assertEquals("Invalid last name", e7.getMessage());
		assertEquals("Goldberg", fac8.getLastName()); 
		
	}
	
	
	/**
	 * Test Faculty.setEmail()
	 */
	@Test
	public void testSetEmail() {
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> fac1.setEmail(null));
		
		assertEquals("Invalid email", e1.getMessage());
		assertEquals("jbooth@ncsu.edu", fac1.getEmail()); 
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> fac2.setEmail(null));
		
		assertEquals("Invalid email", e2.getMessage());
		assertEquals("rphone@ncsu.edu", fac2.getEmail());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> fac4.setEmail(null));
		
		assertEquals("Invalid email", e3.getMessage());
		assertEquals("bdover@ncsu.edu", fac4.getEmail()); 
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> fac5.setEmail(null));
		
		assertEquals("Invalid email", e4.getMessage());
		assertEquals("jmama@ncsu.edu", fac5.getEmail()); 
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> fac6.setEmail(null));
		
		assertEquals("Invalid email", e5.getMessage());
		assertEquals("iuchiha@ncsu.edu", fac6.getEmail()); 
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> fac7.setEmail(null));
		
		assertEquals("Invalid email", e6.getMessage());
		assertEquals("rgrimes@ncsu.edu", fac7.getEmail()); 
		
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> fac8.setEmail(null));
		
		assertEquals("Invalid email", e7.getMessage());
		assertEquals("jgoldberg@ncsu.edu", fac8.getEmail()); 
		
		 
	}
	
	
	/**
	 * Test Faculty.setPassword()
	 */
	@Test
	public void testSetPassword() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> fac1.setPassword(null));
		
		assertEquals("Invalid password", e1.getMessage());
		assertEquals("password", fac1.getPassword()); 
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> fac2.setPassword(null));
		
		assertEquals("Invalid password", e2.getMessage());
		assertEquals("Clever", fac2.getPassword()); 
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> fac3.setPassword(null));
		
		assertEquals("Invalid password", e3.getMessage());
		assertEquals("Gamer65", fac3.getPassword()); 
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> fac5.setPassword(null));
		
		assertEquals("Invalid password", e4.getMessage());
		assertEquals("Mymom23", fac5.getPassword()); 
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> fac6.setPassword(null));
		
		assertEquals("Invalid password", e5.getMessage());
		assertEquals("Clanslayer01", fac6.getPassword()); 
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> fac7.setPassword(null));
		
		assertEquals("Invalid password", e6.getMessage());
		assertEquals("Carollll1", fac7.getPassword()); 
		
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> fac8.setPassword(null));
		
		assertEquals("Invalid password", e7.getMessage());
		assertEquals("You44", fac8.getPassword()); 
	}
	/**
	 * Test Faculty.setMaxCredits()
	 */
	@Test
	public void testSetMaxCourses() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> fac1.setMaxCourses(6));
		
		assertEquals("Invalid max courses", e1.getMessage());
		assertEquals(1, fac1.getMaxCourses()); 
	}
	/**
	 * Test Faculty.equals()
	 */
	@Test
	public void testEqualsObject() {
		assertTrue(fac3.equals(fac4));
		assertFalse(fac1.equals(fac2));
		assertTrue(fac3.equals(fac3));
		assertTrue(fac8.equals(fac9));
		
	}
	/**
	 * Test Faculty.toString()
	 */
	@Test
	public void testToString() {
		Faculty s1 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
		assertEquals("John,Booth,jwbooth,jbooth@ncsu.edu,password,3", s1.toString());
		Faculty s2 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MINCOURSES);
		assertEquals("John,Booth,jwbooth,jbooth@ncsu.edu,password,1", s2.toString());

	}
	
	/**
	 * Test Faculty.compareTo(), based on first name, then last name, and then unity id
	 */
	@Test
	public void testCompareTo() {
		Faculty f1 = new Faculty(FIRSTNAME, LASTNAME, "jrbooth", EMAIL, PASSWORD, MAXCOURSES);
		Faculty f2 = new Faculty("Harry", LASTNAME, "jrbooth", EMAIL, PASSWORD, MAXCOURSES);
		Faculty f3 = new Faculty("Harry", "Carter", "jrbooth", EMAIL, PASSWORD, MAXCOURSES);
		Faculty f4 = new Faculty("Joson", "Botson", "jtbotso", EMAIL, PASSWORD, MAXCOURSES);
		assertEquals(-1, f1.compareTo(f3));
		assertEquals(-1, f4.compareTo(f3));
		assertEquals(1, f3.compareTo(f1));
		assertEquals(1, f4.compareTo(f2));
		assertEquals(-1, f2.compareTo(f1));
		assertEquals(1, f1.compareTo(f2));
	}
}