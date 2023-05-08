/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * This class represents the CourseRoll object that holds the enrolled students
 * in the course. It has the functionality of adding students to the roll or
 * removing students from the roll. It can also check if the given student is
 * appropriate to be added or removed from the roll.
 * 
 * @author Victor Hermida
 * @author Thien Nguyen
 *
 */
public class CourseRoll {
	/** This private represents as the roll of a student */
	private LinkedAbstractList<Student> roll;
	/** This private represents as enrollmentCap */
	private int enrollmentCap;
	/** This private represents the minimum enrollment */
	private static final int MIN_ENROLLMENT = 10;
	/** This private represent the maximum enrollment */
	private static final int MAX_ENROLLMENT = 250;
	/** Maximum capacity on wait list */
	private static final int WAITLIST_SIZE = 10;
	/** The waitlist */
	LinkedQueue<Student> waitlist;
	/** Course object */
	private Course c;

	/**
	 * Constructs the CourseRoll object with the given enrollmentCap and create the
	 * empty LinkedAbstractList of Students.
	 * @param c course that goes into course roll
	 * @param enrollmentCap roll's enrollment capacity
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
		waitlist = new LinkedQueue<>(WAITLIST_SIZE);
		this.c = c;
	}

	/**
	 * Set the roll's enrollment capacity. Throw the IllegalArgumentException
	 * exception if the given parameter is less than minimum enrollment value or
	 * greater than maximum enrollment value.
	 * 
	 * @param enrollmentCap roll's enrollment capacity
	 * @throws IllegalArgumentException if enrollmentCap is less than minimum
	 *                                  enrollment value or greater than maximum
	 *                                  enrollment value
	 * @throws IllegalArgumentException if enrollmentCap is less than number of
	 *                                  currently enrolled students
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		// If roll is null, set enrollment cap
		if (roll == null) {
			this.enrollmentCap = enrollmentCap;
		} else {
			if (enrollmentCap > roll.getCapacity()) {
				roll.setCapacity(enrollmentCap);
			}
			if (enrollmentCap >= roll.size()) {
				this.enrollmentCap = enrollmentCap;
			} else if (enrollmentCap < roll.size()) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Returns the roll's enrollment capacity.
	 * 
	 * @return the roll's enrollment capacity
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Returns the difference between the enrollmentCap and the size of the roll.
	 * 
	 * @return the difference between the enrollmentCap and the size of the roll
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Add the given student to the CourseRoll. Throw the IllegalArgumentException
	 * exception if the given student is null, if there is no more room in the
	 * class, if the given student is already enrolled, or if adding the student to
	 * the LinkedAbstractList generates an exception.
	 * 
	 * @param s student to be enrolled
	 * @throws IllegalArgumentException if the given student is null
	 * @throws IllegalArgumentException if there is no more room in the class
	 * @throws IllegalArgumentException if the given student is already enrolled
	 * @throws IllegalArgumentException if adding the student to the
	 *                                  LinkedAbstractList generates an exception
	 */
	public void enroll(Student s) {
//		if (s == null) {
//			throw new IllegalArgumentException();
//		}
//
//		if (!canEnroll(s)) {
//			throw new IllegalArgumentException();
//		}
//		// check if there are seats available on the CourseRoll
//		if (!(getOpenSeats() > 0)) {
//			// if not, check if there is space on the wait list
//			if (waitlist.size() < 10) {
//				try {
//					waitlist.enqueue(s);
//				} catch (Exception e) {
//					throw new IllegalArgumentException();
//				}
//			} else {
//				// if there is no room on CourseRoll or wait list
//				throw new IllegalArgumentException();
//			}
//		} else {
//			// try adding to the CourseRoll
//			try {
//				roll.add(roll.size(), s);
//			} catch (Exception e) {
//				throw new IllegalArgumentException();
//			}
//		}
		if (roll == null || s == null) {
			throw new IllegalArgumentException();
		}

		// Check that student s isn't already in the list
		if (roll.contains(s)) {
			throw new IllegalArgumentException();
		}

		if (roll.size() == enrollmentCap) {

			if (waitlist.size() < WAITLIST_SIZE) {
				waitlist.enqueue(s);
			} else {
				throw new IllegalArgumentException();
			}
		} else {

			roll.add(roll.size(), s);

		}
	}

	/**
	 * Remove the given student from the CourseRoll. Throw the
	 * IllegalArgumentException exception if the given student is null or if
	 * removing the student from the LinkedAbstractList generates an exception.
	 * 
	 * @param s student to be removed
	 * @throws IllegalArgumentException if the given student is null
	 * @throws IllegalArgumentException if removing the student from the
	 *                                  LinkedAbstractList generates an exception
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}

		if (roll.contains(s)) {
			try {
				roll.remove(s);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}

			if (!waitlist.isEmpty()) {
				Student fromWaitList = waitlist.dequeue();
				roll.add(roll.size(), fromWaitList);
				fromWaitList.getSchedule().addCourseToSchedule(c);
			}
		} else {
			for (int i = 0; i < waitlist.size(); i++) {
				Student inLine = waitlist.dequeue();
				if (!s.equals(inLine)) {
					waitlist.enqueue(inLine);
				}
			}
		}
	}

	/**
	 * Returns true if the Student can be added to the CourseRoll and false if there
	 * is no more room in the class or if the given student is already enrolled.
	 * 
	 * @param s student to be removed
	 * @return true if the Student can be added to the CourseRoll and false if there
	 *         is no more room in the class or if the given student is already
	 *         enrolled.
	 */
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0) {
			return false;
		}

		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Retrieves the number of students on the wait list
	 * @return the number of students currently on the wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}