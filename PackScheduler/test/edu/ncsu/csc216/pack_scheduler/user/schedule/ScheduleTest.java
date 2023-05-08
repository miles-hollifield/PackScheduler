/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;

/**
 * Test for Schedule class.
 * 
 * @author Thien Nguyen
 *
 */
class ScheduleTest {

	/**
	 * Tests Schedule constructor.
	 */
	@Test
	void testSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
	}

	/**
	 * Tests title functionality of Schedule.
	 */
	@Test
	void testScheduleTitle() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		schedule.setTitle("Spring 2023 Schedule");
		assertEquals("Spring 2023 Schedule", schedule.getTitle());

		// Test that exception is thrown when title to be set is null
		Exception e = assertThrows(IllegalArgumentException.class, () -> schedule.setTitle(null));
		assertEquals("Title cannot be null.", e.getMessage());
	}

	/**
	 * Tests functionality of adding course to Schedule.
	 */
	@Test
	void testAddCourseToSchedule() {
		Schedule schedule = new Schedule();
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");

		assertEquals(0, schedule.getScheduledCourses().length);
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC116", "001")));		
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC216", "002")));
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC230", "001")));
		assertEquals(3, schedule.getScheduledCourses().length);

		// Test that exception is thrown when violations appear
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC116", "001")));
		assertEquals("You are already enrolled in CSC116", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC226", "001")));
		assertEquals("The course cannot be added due to a conflict.", e2.getMessage());
		
		assertThrows(NullPointerException.class, () -> schedule.addCourseToSchedule(null));
	}
	
	/**
	 * Tests functionality of removing course from Schedule.
	 */
	@Test
	void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");

		assertEquals(0, schedule.getScheduledCourses().length);
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC116", "001")));		
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC216", "002")));
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC230", "001")));
		assertEquals(3, schedule.getScheduledCourses().length);

		assertTrue(schedule.removeCourseFromSchedule(cc.getCourseFromCatalog("CSC216", "002")));
		assertFalse(schedule.removeCourseFromSchedule(cc.getCourseFromCatalog("CSC226", "001")));
		assertEquals(2, schedule.getScheduledCourses().length);
	}
	
	/**
	 * Tests functionality of resetting the Schedule.
	 */
	@Test
	void testResetSchedule() {
		Schedule schedule = new Schedule();
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");
		
		schedule.setTitle("Spring 2023 Schedule");
		assertEquals("Spring 2023 Schedule", schedule.getTitle());
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC116", "001")));		
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC216", "002")));
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC230", "001")));
		assertEquals(3, schedule.getScheduledCourses().length);

		schedule.resetSchedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
	}
	
	/**
	 * Tests functionality of resetting the Schedule.
	 */
	@Test
	void testGetScheduleCourses() {
		Schedule schedule = new Schedule();
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");
		
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC116", "001")));		
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC216", "002")));
		assertTrue(schedule.addCourseToSchedule(cc.getCourseFromCatalog("CSC230", "001")));
		assertEquals(3, schedule.getScheduledCourses().length);

		String[][] scheduledCourses = schedule.getScheduledCourses();
		// Row 0
		assertEquals("CSC116", scheduledCourses[0][0]);
		assertEquals("001", scheduledCourses[0][1]);
		assertEquals("Intro to Programming - Java", scheduledCourses[0][2]);
		assertEquals("MW 9:10AM-11:00AM", scheduledCourses[0][3]);
		// Row 1
		assertEquals("CSC216", scheduledCourses[1][0]);
		assertEquals("002", scheduledCourses[1][1]);
		assertEquals("Software Development Fundamentals", scheduledCourses[1][2]);
		assertEquals("MW 1:30PM-2:45PM", scheduledCourses[1][3]);
		// Row 2
		assertEquals("CSC230", scheduledCourses[2][0]);
		assertEquals("001", scheduledCourses[2][1]);
		assertEquals("C and Software Tools", scheduledCourses[2][2]);
		assertEquals("MW 11:45AM-1:00PM", scheduledCourses[2][3]);
	}
}
