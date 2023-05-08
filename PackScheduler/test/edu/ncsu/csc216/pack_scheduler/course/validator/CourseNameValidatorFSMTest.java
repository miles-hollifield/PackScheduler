/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * We are testing to check to see if the course name is valid
 * @author Thien Nguyen
 * @author Victor Hermida
 *
 */
public class CourseNameValidatorFSMTest {
	
	/** This private represent FSM */
	private CourseNameValidatorFSM fsm;
	
	
	/**
	 * Test method for the first letter State in the FSM.
	 */
	@Test
	public void testFirstLetterState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the first letter is in the course name
		assertTrue(fsm.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
		//testing to see if any letters or numbers are in the course name 
		Exception e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("!"));
		assertEquals("Course name can only contain letters and digits.", e.getMessage());
		// testing to see if the course name start with a letter
		Exception e1 = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("1"));
		assertEquals("Course name must start with a letter.", e1.getMessage());
		
		
	}	
	
	/**
	 * Test method for the second letter State in the FSM.
	 */
	@Test
	public void testSecondLetterState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the second letter is in the course name
		assertTrue(fsm.isValid("CSC216"));
		assertTrue(fsm.isValid("C216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
	}
	
	/**
	 * Test method for the third letter State in the FSM.
	 */
	@Test
	public void testThirdLetterState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the third letter is in the course name
		assertTrue(fsm.isValid("CSC216"));
		assertTrue(fsm.isValid("SC216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
	}
	
	/**
	 * Test method for the fourth letter State in the FSM.
	 */
	@Test
	public void testFourthLetterState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the fourth letter is in the course name
		assertTrue(fsm.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
		// testing to see if the course name has more than 5 letters
		Exception e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("CSCAA216"));
		assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
	}
	
	
	/**
	 * Test method for the first digit State in the FSM.
	 */
	@Test
	public void testFirstDigitState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the course name has one digit
		assertTrue(fsm.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
		//testing to see if Course name has 3 digits
		Exception e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("CSC3C"));
		assertEquals("Course name must have 3 digits.", e.getMessage());
	}
	
	/**
	 * Test method for the second digit State in the FSM.
	 */
	@Test
	public void testSecondDigitState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the course name has two digit
		assertTrue(fsm.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
		//testing to see if Course name has 3 digits
		Exception e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("CSCC33C"));
		assertEquals("Course name must have 3 digits.", e.getMessage());
	}
	
	/**
	 * Test method for the third digit State in the FSM.
	 */
	@Test
	public void testThirdDigitState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the course name has three digit
		assertTrue(fsm.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
		//testing to see if Course name has more than 3 digits
		Exception e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("CSCC3333C"));
		assertEquals("Course name can only have 3 digits.", e.getMessage());
	}
	
	/**
	 * Test method for the suffix State in the FSM.
	 */
	@Test
	public void testSuffixState() {
		this.fsm = new CourseNameValidatorFSM();
		try {
		//testing to see if the course name has a suffix
		assertTrue(fsm.isValid("CSC216A"));
		} catch (InvalidTransitionException e) {
			fail("Invalid FSM Transition.");
		}
		// testing to see if course name has more than 1 letter suffix
		Exception e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("CSC216AA"));
		assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		//testing to see if course name has a digit after the suffix
		Exception e1 = assertThrows(InvalidTransitionException.class, () -> fsm.isValid("CSC216A1"));
		assertEquals("Course name cannot contain digits after the suffix.", e1.getMessage());
	}
	
}
