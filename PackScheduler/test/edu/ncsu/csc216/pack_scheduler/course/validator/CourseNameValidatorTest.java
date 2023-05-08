/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests CourseNameValidator class.
 * 
 * @author Thien Nguyen
 *
 */
class CourseNameValidatorTest {

	/**
	 * Test method for CourseNameValidator.isValid() with initial state
	 */
	@Test
	void testIsValidInitialState() {
		CourseNameValidator c = new CourseNameValidator();
		try {
			assertTrue(c.isValid("C216"));
			assertTrue(c.isValid("DA216"));
			assertTrue(c.isValid("CSC216"));
			assertTrue(c.isValid("CSCL216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}

		Exception e1 = assertThrows(InvalidTransitionException.class, () -> c.isValid("1"));
		assertEquals("Course name must start with a letter.", e1.getMessage());

		Exception e2 = assertThrows(InvalidTransitionException.class, () -> c.isValid("!"));
		assertEquals("Course name can only contain letters and digits.", e2.getMessage());
	}

	/**
	 * Test method for CourseNameValidator.isValid() with letter state
	 */
	@Test
	void testIsValidLetterState() {
		CourseNameValidator c = new CourseNameValidator();
		try {
			assertTrue(c.isValid("C216"));
			assertTrue(c.isValid("DA216"));
			assertTrue(c.isValid("CSC216"));
			assertTrue(c.isValid("CSCL216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}

		Exception e1 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSCLA216"));
		assertEquals("Course name cannot start with more than 4 letters.", e1.getMessage());

		Exception e2 = assertThrows(InvalidTransitionException.class, () -> c.isValid("C!CL216"));
		assertEquals("Course name can only contain letters and digits.", e2.getMessage());
	}

	/**
	 * Test method for CourseNameValidator.isValid() with number state
	 */
	@Test
	void testIsValidNumberState() {
		CourseNameValidator c = new CourseNameValidator();
		try {
			assertTrue(c.isValid("C216"));
			assertTrue(c.isValid("DA217"));
			assertTrue(c.isValid("CSC216"));
			assertTrue(c.isValid("CSCL216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}

		Exception e1 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC2A"));
		assertEquals("Course name must have 3 digits.", e1.getMessage());

		Exception e2 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC21A"));
		assertEquals("Course name must have 3 digits.", e2.getMessage());

		Exception e3 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC2168"));
		assertEquals("Course name can only have 3 digits.", e3.getMessage());

		Exception e4 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC2!8"));
		assertEquals("Course name can only contain letters and digits.", e4.getMessage());
	}

	/**
	 * Test method for CourseNameValidator.isValid() with suffix state
	 */
	@Test
	void testIsValidSuffixState() {
		CourseNameValidator c = new CourseNameValidator();
		try {
			assertTrue(c.isValid("C216A"));
			assertTrue(c.isValid("DA217A"));
			assertTrue(c.isValid("CSC216A"));
			assertTrue(c.isValid("CSCL216A"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}

		Exception e1 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC216AB"));
		assertEquals("Course name can only have a 1 letter suffix.", e1.getMessage());

		Exception e2 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC216A8"));
		assertEquals("Course name cannot contain digits after the suffix.", e2.getMessage());

		Exception e3 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC216!"));
		assertEquals("Course name can only contain letters and digits.", e3.getMessage());
	}

	/**
	 * Test method for CourseNameValidator.isValid() with invalid course names
	 */
	@Test
	void testIsValidWithInvalidName() {
		CourseNameValidator c = new CourseNameValidator();
		try {
			assertFalse(c.isValid("C"));
			assertFalse(c.isValid("CS"));
			assertFalse(c.isValid("CSC"));
			assertFalse(c.isValid("CSCL"));
			assertFalse(c.isValid("CSCL2"));
			assertFalse(c.isValid("CSCL21"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}

	}

}
