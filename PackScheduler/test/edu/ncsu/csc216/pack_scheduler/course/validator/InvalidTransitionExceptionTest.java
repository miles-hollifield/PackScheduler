package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for the InvalidTransitionException class.
 * 
 * @author victorhermida
 *
 */
public class InvalidTransitionExceptionTest {
	/**
	 * Tests the method for the custom exception message when throwing ConflictException
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Tests the method for the default exception message when throwing ConflictException
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException ceDefault = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ceDefault.getMessage());
	}
}
