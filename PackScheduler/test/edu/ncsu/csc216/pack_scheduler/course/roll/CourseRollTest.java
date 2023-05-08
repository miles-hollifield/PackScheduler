/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests for CourseRoll class.
 * 
 * @author Thien Nguyen
 *
 */
public class CourseRollTest {

	/** Testing the constructor of CourseRoll */
	@Test
	void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		try {
			assertEquals(10, roll.getEnrollmentCap());
			assertEquals(10, roll.getOpenSeats());
		} catch (Exception e) {
			fail();
		}
	}

	/** Testing CourseRoll.setEnrollmentCap() */
	@Test
	void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		roll.setEnrollmentCap(50);
		assertEquals(50, roll.getEnrollmentCap());

		assertEquals(50, roll.getEnrollmentCap());
		assertEquals(50, roll.getOpenSeats());

		Student stu1 = new Student("John", "Booth", "jwbooth", "jbooth@ncsu.edu", "password", 17);
		Student stu2 = new Student("Robert", "Phone", "rphone", "rphone@ncsu.edu", "Clever");
		Student stu3 = new Student("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 7);
		roll.enroll(stu1);
		roll.enroll(stu2);
		roll.enroll(stu3);
		roll.setEnrollmentCap(15);
		assertEquals(15, roll.getEnrollmentCap());
		assertEquals(12, roll.getOpenSeats());

		// Test that exceptions are thrown for invalid inputs
		assertThrows(IllegalArgumentException.class, () -> roll.setEnrollmentCap(9));
		assertThrows(IllegalArgumentException.class, () -> roll.setEnrollmentCap(251));
		assertThrows(IllegalArgumentException.class, () -> roll.setEnrollmentCap(2));
	}

	/** Testing CourseRoll.enroll() */
	@Test
	void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		Student stu1 = new Student("John", "Booth", "jwbooth", "jbooth@ncsu.edu", "password", 17);
		Student stu2 = new Student("Robert", "Phone", "rphone", "rphone@ncsu.edu", "Clever");
		Student stu3 = new Student("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 7);
		Student stu4 = new Student("Brian", "Man", "bman", "bman@ncsu.edu", "testpass", 8);
		Student stu5 = new Student("Sally", "Sew", "ssow", "ssow@ncsu.edu", "testing", 9);
		Student stu6 = new Student("Rachel", "Pots", "rpots", "rpots@ncsu.edu", "moretest", 10);
		Student stu7 = new Student("Jim", "Brew", "jbrew", "jbrew@ncsu.edu", "sectest", 10);
		Student stu8 = new Student("Ally", "Johnson", "ajohnson", "ajohnson@ncsu.edu", "anothertest", 10);
		Student stu9 = new Student("Craig", "Hope", "chope", "chope@ncsu.edu", "againtest", 10);
		Student stu10 = new Student("Arnold", "Reed", "areed", "areed@ncsu.edu", "hitest", 10);
		roll.enroll(stu1);
		roll.enroll(stu2);
		roll.enroll(stu3);
		roll.enroll(stu4);
		roll.enroll(stu5);
		roll.enroll(stu6);
		roll.enroll(stu7);
		roll.enroll(stu8);
		roll.enroll(stu9);
		roll.enroll(stu10);
		assertEquals(0, roll.getOpenSeats());
		
		try {
			roll.enroll(stu3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, roll.getEnrollmentCap());
			assertEquals(0, roll.getOpenSeats());
		}
		


		// Test that exceptions are thrown for invalid inputs
		assertThrows(IllegalArgumentException.class, () -> roll.enroll(null));
		assertThrows(IllegalArgumentException.class, () -> roll.enroll(stu2));
	}
	
	/** Testing CourseRoll.drop() */
	@Test
	void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		Student stu1 = new Student("John", "Booth", "jwbooth", "jbooth@ncsu.edu", "password", 17);
		Student stu2 = new Student("Robert", "Phone", "rphone", "rphone@ncsu.edu", "Clever");
		Student stu3 = new Student("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 7);
		Student stu4 = new Student("Brian", "Man", "bman", "bman@ncsu.edu", "testpass", 8);
		Student stu5 = new Student("Sally", "Sew", "ssow", "ssow@ncsu.edu", "testing", 9);
		Student stu6 = new Student("Rachel", "Pots", "rpots", "rpots@ncsu.edu", "moretest", 10);
		Student stu7 = new Student("Jim", "Brew", "jbrew", "jbrew@ncsu.edu", "sectest", 10);
		Student stu8 = new Student("Ally", "Johnson", "ajohnson", "ajohnson@ncsu.edu", "anothertest", 10);
		Student stu9 = new Student("Craig", "Hope", "chope", "chope@ncsu.edu", "againtest", 10);
		Student stu10 = new Student("Arnold", "Reed", "areed", "areed@ncsu.edu", "hitest", 10);
		Student stu11 = new Student("Greg", "Smith", "gsmtih", "gsmtih@ncsu.edu", "oktest", 10);
		
		try {
			roll.drop(stu1);
		} catch (IllegalArgumentException e) {
			assertEquals(roll.getOpenSeats(), roll.getEnrollmentCap());
		}
		
		roll.enroll(stu1);
		roll.enroll(stu2);
		roll.enroll(stu3);
		assertEquals(7, roll.getOpenSeats());
		
		roll.enroll(stu4);
		roll.enroll(stu5);
		roll.enroll(stu6);
		assertEquals(4, roll.getOpenSeats());
		
		roll.enroll(stu7);
		roll.enroll(stu8);
		roll.enroll(stu9);
		roll.enroll(stu10);
		assertEquals(0, roll.getOpenSeats());
		
		roll.enroll(stu11);
		assertEquals(1, roll.getNumberOnWaitlist());
		
		roll.drop(stu1);
		roll.drop(stu2);
		assertEquals(1, roll.getOpenSeats());
		
		roll.drop(stu3);
		roll.drop(stu4);
		assertEquals(3, roll.getOpenSeats());
		
		roll.drop(stu5);
		roll.drop(stu6);
		assertEquals(5, roll.getOpenSeats());
		
		assertEquals(0, roll.getNumberOnWaitlist());
		
		try {
			roll.drop(stu3);
		} catch (IllegalArgumentException e) {
			assertEquals(1, roll.getOpenSeats());
		}

		// Test that exceptions are thrown for invalid inputs
		assertThrows(IllegalArgumentException.class, () -> roll.drop(null));
	}
	
	/** Testing CourseRoll.canEnroll() */
	@Test
	void testCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		Student stu1 = new Student("John", "Booth", "jwbooth", "jbooth@ncsu.edu", "password", 17);
		Student stu2 = new Student("Robert", "Phone", "rphone", "rphone@ncsu.edu", "Clever");
		Student stu3 = new Student("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 7);
		Student stu4 = new Student("Benjamin", "Dover", "bedover", "bedover@ncsu.edu", "Gamer66", 7);
		Student stu5 = new Student("Joe", "Mama", "jmama", "jmama@ncsu.edu", "Mymom23");
		Student stu6 = new Student("Sasuke", "Uchiha", "suchiha", "suchiha@ncsu.edu", "Clanslayer01", 6);
		Student stu7 = new Student("Itachi", "Uchiha", "iuchiha", "iuchiha@ncsu.edu", "Clanslayer02", 6);
		Student stu8 = new Student("Rick", "Grimes", "rgrimes", "rgrimes@ncsu.edu", "Carollll1");
		Student stu9 = new Student("Joe", "Goldberg", "jgoldberg", "jgoldberg@ncsu.edu", "You44", 10);
		Student stu10 = new Student("Zoe", "Goldberg", "zgoldberg", "zgoldberg@ncsu.edu", "You45", 10);
		Student stu11 = new Student("Greg", "Smith", "gsmtih", "gsmtih@ncsu.edu", "oktest", 10);
		
		assertTrue(roll.canEnroll(stu1));
		roll.enroll(stu1);
		assertTrue(roll.canEnroll(stu2));
		roll.enroll(stu2);
		assertTrue(roll.canEnroll(stu3));
		roll.enroll(stu3);
		assertTrue(roll.canEnroll(stu4));
		roll.enroll(stu4);
		assertTrue(roll.canEnroll(stu5));
		roll.enroll(stu5);
		assertTrue(roll.canEnroll(stu6));
		roll.enroll(stu6);
		assertTrue(roll.canEnroll(stu7));
		roll.enroll(stu7);
		assertTrue(roll.canEnroll(stu8));
		roll.enroll(stu8);
		assertTrue(roll.canEnroll(stu9));
		roll.enroll(stu9);		
		assertTrue(roll.canEnroll(stu10));
		roll.enroll(stu10);
		assertEquals(0, roll.getOpenSeats());
		
		assertFalse(roll.canEnroll(stu11));
		assertFalse(roll.canEnroll(null));
		assertFalse(roll.canEnroll(stu1));

		// Test that exceptions are thrown for invalid inputs
//		assertThrows(IllegalArgumentException.class, () -> roll.enroll(null));
//		assertThrows(IllegalArgumentException.class, () -> roll.enroll(stu6));
//		Student stu11 = new Student("Madara", "Uchiha", "muchiha", "muchiha@ncsu.edu", "Clanslayer03", 6);
		//assertThrows(IllegalArgumentException.class, () -> roll.enroll(stu11));
	}
	
	/**
	 * Tests getNumberOnWaitlist()
	 */
	@Test
	public void testGetNumberOnWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		Student stu1 = new Student("John", "Booth", "jwbooth", "jbooth@ncsu.edu", "password", 17);
		Student stu2 = new Student("Robert", "Phone", "rphone", "rphone@ncsu.edu", "Clever");
		Student stu3 = new Student("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 7);
		Student stu4 = new Student("Brian", "Man", "bman", "bman@ncsu.edu", "testpass", 8);
		Student stu5 = new Student("Sally", "Sew", "ssow", "ssow@ncsu.edu", "testing", 9);
		Student stu6 = new Student("Rachel", "Pots", "rpots", "rpots@ncsu.edu", "moretest", 10);
		Student stu7 = new Student("Jim", "Brew", "jbrew", "jbrew@ncsu.edu", "sectest", 10);
		Student stu8 = new Student("Ally", "Johnson", "ajohnson", "ajohnson@ncsu.edu", "anothertest", 10);
		Student stu9 = new Student("Craig", "Hope", "chope", "chope@ncsu.edu", "againtest", 10);
		Student stu10 = new Student("Arnold", "Reed", "areed", "areed@ncsu.edu", "hitest", 10);
		Student stu11 = new Student("Greg", "Smith", "gsmtih", "gsmtih@ncsu.edu", "oktest", 10);
		roll.enroll(stu1);
		roll.enroll(stu2);
		roll.enroll(stu3);
		assertEquals(7, roll.getOpenSeats());
		
		roll.enroll(stu4);
		roll.enroll(stu5);
		roll.enroll(stu6);
		assertEquals(4, roll.getOpenSeats());
		
		roll.enroll(stu7);
		roll.enroll(stu8);
		roll.enroll(stu9);
		roll.enroll(stu10);
		assertEquals(0, roll.getOpenSeats());

		assertEquals(roll.getNumberOnWaitlist(), 0);
		
		roll.enroll(stu11);
		assertEquals(roll.getNumberOnWaitlist(), 1);
		
	}
}