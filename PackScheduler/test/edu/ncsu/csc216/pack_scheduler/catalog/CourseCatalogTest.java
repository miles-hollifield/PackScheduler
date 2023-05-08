/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test methods in the CourseCatalog class
 * 
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 * @author Victor Hermida
 * @author Thien Nguyen
 *
 */
public class CourseCatalogTest {
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Test name */
	private static final String NAME = "CSC216";
	/** Test title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Test section */
	private static final String SECTION = "002";
	/** Test credits */
	private static final int CREDITS = 3;
	/** Test instructor id */
	private static final String INSTRUCTORID = "mlsmith";
	/** Course roll enrollment capacity */
	private static final int ENROLLMENT_CAP = 10;
	/** Test meeting days */
	private static final String MEETINGDAYS = "MWF";
	/** Test start time */
	private static final int START_TIME = 830;
	/** Test end time */
	private static final int END_TIME = 1020;

	/**
	 * Test CourseCatalog()
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		assertFalse(catalog.removeCourseFromCatalog("CSC226", "002"));
		assertEquals(0, catalog.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.testNewCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		// Test that if there are courses in the directory, they
		// are removed after calling newCourseCatalog().
		CourseCatalog cc = new CourseCatalog();

		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);

		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.loadCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();

		// Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		// Test invalid file
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> cc.loadCoursesFromFile("test-files/noFile.txt"));
		assertEquals("Unable to read file " + "test-files/noFile.txt", e.getMessage());

	}

	/**
	 * Tests CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Test Course with an invalid input - title is null
		Exception x = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(NAME, null, SECTION,
				CREDITS, INSTRUCTORID, ENROLLMENT_CAP, MEETINGDAYS, START_TIME, END_TIME));
		assertEquals("Invalid title.", x.getMessage());

		// Test valid Course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTORID, ENROLLMENT_CAP, MEETINGDAYS, START_TIME,
				END_TIME);
		String[][] courseCatalog = cc.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(Integer.toString(10), courseCatalog[0][4]);
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTORID, ENROLLMENT_CAP, "F", 830, 1200));
	}

	/**
	 * Test CourseCatalog.getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cat = new CourseCatalog();
		cat.loadCoursesFromFile(validTestFile);

		// Attempt to get a course that doesn't exist
		assertNull(cat.getCourseFromCatalog("CSC492", "001"));
		assertNull(cat.getCourseFromCatalog("CSC216", "003"));

		// Attempt to get a course that does exist
		//Course c = new Course(NAME, TITLE, SECTION, CREDITS, "ixdoming", ENROLLMENT_CAP, "MW", 1330, 1445);
		//assertEquals(c, cat.getCourseFromCatalog("CSC216", "002"));
	}

	/**
	 * Tests CourseCatalog.removeCourseFromCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Add courses and remove
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTORID, ENROLLMENT_CAP, MEETINGDAYS, START_TIME,
				END_TIME);
		cc.addCourseToCatalog("CSC226", "Discrete Math", "001", CREDITS, INSTRUCTORID, ENROLLMENT_CAP, "TH", 1630,
				1745);
		assertTrue(cc.removeCourseFromCatalog("CSC226", "001"));
		assertEquals(1, cc.getCourseCatalog().length);
		String[][] courseCatalog = cc.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(Integer.toString(10), courseCatalog[0][4]);
	}

	/**
	 * Tests CourseCatalog.saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		// Add a course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTORID, ENROLLMENT_CAP, MEETINGDAYS, START_TIME,
				END_TIME);
		assertEquals(1, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/testing_course_records.txt");
	}

}
