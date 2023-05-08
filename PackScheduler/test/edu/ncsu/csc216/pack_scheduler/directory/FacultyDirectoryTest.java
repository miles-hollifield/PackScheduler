/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests FacultyDirectory
 * @author mfhollif
 */
class FacultyDirectoryTest {
	
	/** Valid faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Faculty";
	/** Test last name */
	private static final String LAST_NAME = "Person";
	/** Test id */
	private static final String ID = "fperson";
	/** Test email */
	private static final String EMAIL = "fperson@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max courses */
	private static final int MAX_COURSES = 3;

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#FacultyDirectory()}.
	 */
	@Test
	void testFacultyDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#newFacultyDirectory()}.
	 */
	@Test
	void testNewFacultyDirectory() {
		// Test that if there are faculty in the directory, they
		// are removed after calling newFacultyDirectory().
		FacultyDirectory fd = new FacultyDirectory();

		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);

		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#loadFacultyFromFile(java.lang.String)}.
	 */
	@Test
	void testLoadFacultyFromFile() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		// Test invalid file
		Exception e = assertThrows(IllegalArgumentException.class, () -> fd.loadFacultyFromFile("test-files/noFile.txt"));
		assertEquals("Unable to read file " + "test-files/noFile.txt", e.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#addFaculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		
		// Test Faculty with invalid password - null
		Exception x = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, null, MAX_COURSES));
		assertEquals("Invalid password", x.getMessage());
		
		// Test Faculty with invalid password - empty string
		Exception xx = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", "", MAX_COURSES));
		assertEquals("Invalid password", xx.getMessage());
							
		//Test Faculty with invalid first name - null.
		Exception x1 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(null, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid first name", x1.getMessage());
		
		//Test Faculty with invalid first name -empty string.
		Exception x11 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty("", LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid first name", x11.getMessage());
		
		//Test Faculty with invalid last name - null.
		Exception x2 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid last name", x2.getMessage());
		
		//Test Faculty with invalid last name - empty string.
		Exception x22 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, "", ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid last name", x22.getMessage());
		
		//Test Faculty with invalid id - null.
		Exception x3 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid id", x3.getMessage());
		
		//Test Faculty with invalid id - empty string.
		Exception x33 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid id", x33.getMessage());
		
		//Test Faculty with invalid email - null.
		Exception x4 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid email", x4.getMessage());
		
		//Test Faculty with invalid email - null.
		Exception x44 = assertThrows(IllegalArgumentException.class, () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals("Invalid email", x44.getMessage());
		
		
		// Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#removeFaculty(java.lang.String)}.
	 */
	@Test
	void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add Faculty and remove
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		assertTrue(fd.removeFaculty("awitt"));
		String[][] facultyDirectory = fd.getFacultyDirectory();
//		for (int i = 0; i < facultyDirectory.length; i++) {
//			System.out.println(facultyDirectory[i][0]);
//		}
//		System.out.println(facultyDirectory);
		assertEquals(7, facultyDirectory.length);
		assertEquals("Norman", facultyDirectory[5][0]);
		assertEquals("Brady", facultyDirectory[5][1]);
		assertEquals("nbrady", facultyDirectory[5][2]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#saveFacultyDirectory(java.lang.String)}.
	 */
	@Test
	void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add a faculty
		fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		fd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		fd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, fd.getFacultyDirectory().length);
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#getFacultyById(String)}.
	 */
	@Test
	void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 
				PASSWORD, MAX_COURSES);
		fd.addFaculty("Mark", "Horner", "mfhorner", "mfhorner@ncsu.edu", "javadoc", "javadoc", 3);
		
		assertEquals(2, fd.getFacultyDirectory().length);
		
		// Check that faculty can be retrieved
		Faculty f = fd.getFacultyById("mfhorner");
		
		assertEquals("mfhorner", f.getId());
	}

}
