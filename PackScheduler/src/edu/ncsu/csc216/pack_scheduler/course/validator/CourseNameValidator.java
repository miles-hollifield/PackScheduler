/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * This class represents the FSM of the CourseNameValidator that will check
 * whether the given Course name is valid or not. A valid course name begins
 * with 1-4 letters, followed by exactly 3 digits, followed by an optional 1
 * letter suffix. If a course name doesn’t meet the description, the course name
 * is invalid. Spaces are no longer allowed between the prefix and number.
 * 
 * @author Thien Nguyen
 *
 */
public class CourseNameValidator {

	/** The flag that will check whether the current state is in the end state */
	private boolean validEndState;

	/** The counter of letters to help checking the required length of letters */
	private int letterCount;

	/** The counter of digits to help checking the required length of digits */

	private int digitCount;

	/** The current state of the CourseNameValidator */
	private State currentState;

	/** The initial state of the CourseNameValidator */
	private final State stateInitial = new InitialState();

	/** The letter state of the CourseNameValidator */
	private final State stateLetter = new LetterState();

	/** The digit state of the CourseNameValidator */
	private final State stateNumber = new NumberState();

	/** The suffix state of the CourseNameValidator */
	private final State stateSuffix = new SuffixState();

	/**
	 * Check whether the given Course Name is valid or not. An
	 * InvalidTransitionException is thrown if there are any violation to the
	 * required length of letters or digits or if there are any other characters
	 * other than letters and digits.
	 * 
	 * @param courseName the name of the Course
	 * @return true of the given Course Name is valid or false otherwise
	 * @throws InvalidTransitionException is thrown if there are any violation to
	 *                                    the required length of letters or digits
	 *                                    or if there are any other characters other
	 *                                    than letters and digits
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		currentState = stateInitial;
		letterCount = 0;
		digitCount = 0;
		for (int i = 0; i < courseName.length(); i++) {
			if (!Character.isLetter(courseName.charAt(i)) && !Character.isDigit(courseName.charAt(i))) {
				currentState.onOther();
			}
			if (currentState == stateInitial) {
				if (Character.isLetter(courseName.charAt(i))) {
					currentState.onLetter();
				} else if (Character.isDigit(courseName.charAt(i))) {
					currentState.onDigit();
				}
			} else if (currentState == stateLetter) {
				if (Character.isLetter(courseName.charAt(i))) {
					currentState.onLetter();
				} else if (Character.isDigit(courseName.charAt(i))) {
					currentState.onDigit();
				}
			} else if (currentState == stateNumber) {
				if (Character.isLetter(courseName.charAt(i))) {
					currentState.onLetter();
				} else if (Character.isDigit(courseName.charAt(i))) {
					currentState.onDigit();
				}
			} else if (currentState == stateSuffix) {
				if (Character.isLetter(courseName.charAt(i))) {
					currentState.onLetter();
				} else if (Character.isDigit(courseName.charAt(i))) {
					currentState.onDigit();
				}
			}
		}
		return validEndState;
	}

	/**
	 * Abstract class for states in the CourseNameValidator State Pattern. All
	 * concrete CourseNameValidator states must extend the ApplicationState abstract
	 * class.
	 * 
	 * @author Thien Nguyen
	 */
	public abstract class State {

		/**
		 * Update the State from the given letter. An InvalidTransitionException is
		 * thrown if there are any violation to the required length of letters
		 * 
		 * @throws InvalidTransitionException is thrown if there are any violation to
		 *                                    the required length of letters
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Update the State from the given digit. An InvalidTransitionException is
		 * thrown if there are any violation to the required length of digits
		 * 
		 * @throws InvalidTransitionException is thrown if there are any violation to
		 *                                    the required length of digits
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Throw the InvalidTransitionException if there are any other characters other
		 * than letters and digits
		 * 
		 * @throws InvalidTransitionException is thrown if there are any other
		 *                                    characters other than letters and digits
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * Concrete private inner class that represents the initial state of the
	 * CourseNameValidator FSM.
	 * 
	 * @author Thien Nguyen
	 */
	public class InitialState extends State {

		/**
		 * Update the State and counter of letter from the given letter.
		 */
		@Override
		public void onLetter() {
			letterCount++;
			currentState = stateLetter;
		}

		/**
		 * An InvalidTransitionException is thrown if the character is the digit at this
		 * state.
		 * 
		 * @throws InvalidTransitionException if the character is the digit at this
		 *                                    state.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}

	}

	/**
	 * Concrete private inner class that represents the letter state of the
	 * CourseNameValidator FSM.
	 * 
	 * @author Thien Nguyen
	 */
	public class LetterState extends State {

		/** The maximum length of letters */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 * Update the State and counter of letter from the given letter. An
		 * InvalidTransitionException is thrown if there are any violation to the
		 * required length of letters
		 * 
		 * @throws InvalidTransitionException is thrown if there are any violation to
		 *                                    the required length of letters
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				letterCount++;
				currentState = stateLetter;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * Update the State and counter of digit from the given digit.
		 */
		@Override
		public void onDigit() {
			digitCount++;
			currentState = stateNumber;
		}

	}

	/**
	 * Concrete private inner class that represents the digit state of the
	 * CourseNameValidator FSM.
	 * 
	 * @author Thien Nguyen
	 */
	public class NumberState extends State {

		/** The required length of digits */
		private static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * Update the State from the given letter. An InvalidTransitionException is
		 * thrown if there are any violation to the required length of digits
		 * 
		 * @throws InvalidTransitionException is thrown if there are any violation to
		 *                                    the required length of digits
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount != COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			} else {
				validEndState = true;
				currentState = stateSuffix;
			}
		}

		/**
		 * Update the State and counter of digit from the given digit. An
		 * InvalidTransitionException is thrown if there are any violation to the
		 * required length of digits
		 * 
		 * @throws InvalidTransitionException is thrown if there are any violation to
		 *                                    the required length of digits
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			digitCount++;
			currentState = stateNumber;
			if (digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
			} else if (digitCount > COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}

	}

	/**
	 * Concrete private inner class that represents the suffix state of the
	 * CourseNameValidator FSM.
	 * 
	 * @author Thien Nguyen
	 */
	public class SuffixState extends State {

		/**
		 * An InvalidTransitionException is thrown if the character is the letter at
		 * this state.
		 * 
		 * @throws InvalidTransitionException is thrown if the character is the letter
		 *                                    at this state.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * An InvalidTransitionException is thrown if the character is the digit at this
		 * state.
		 * 
		 * @throws InvalidTransitionException is thrown if the character is the digit at
		 *                                    this state.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}

	}
}