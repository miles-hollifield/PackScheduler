/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Student class contains the first name, last name, id, email, password, and
 * maximum credits for the student to enter in their directory while creating
 * their directory. A Student will be listed in the directory after they add the
 * student to it.
 * @author jamalmohamad
 * @author mfhollif
 */
public class Faculty extends User implements Comparable<Faculty> {

	/** Minimum courses for a Faculty can teach */
	public static final int MIN_COURSES = 1;
	/** Maximum courses for a Faculty can teach */
	public static final int MAX_COURSES = 3;
	/** Faculty's max credit hours */
	private int maxCourses;
	/** FacultySchedule field referencing FacultySchedule class */
	private FacultySchedule facultySchedule;
	
	/**
	 * Sets constructor for Faculty
	 * 
	 * @param firstName  The first name of the faculty
	 * @param lastName   The last name of the faculty
	 * @param id         The id of the faculty
	 * @param email      The email of the faculty
	 * @param hashPW     The password of the faculty
	 * @param maxCourses The total of courses of the faculty
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCourses);
		facultySchedule = new FacultySchedule(id);
	}
	
//	/**
//	 * Sets constructor for Faculty with the default courses, which is 3.
//	 * 
//	 * @param firstName The first name of the faculty
//	 * @param lastName  The last name of the faculty
//	 * @param id        The id of the faculty
//	 * @param email     The email of the faculty
//	 * @param hashPW    The password of the faculty
//	 */
//	public Faculty(String firstName, String lastName, String id, String email, String hashPW) {
//		this(firstName, lastName, id, email, hashPW, MAX_COURSES);
//	}
	
	/**
	 * Returning the max courses
	 * 
	 * @return The max courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * Setting the max courses for the faculty
	 * 
	 * @param maxCourses the max courses of the faculty
	 * @throws IllegalArgumentException if faculty is less than 1 or is greater
	 *                                  than 3
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Generates a hashCode for faculty using all fields.
	 * 
	 * @return hashCode for faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCourses);
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}
	
	/**
	 * Returns a comma separated value String of all faculty fields.
	 * 
	 * @return String representation of faculty
	 */
	@Override
	public String toString() {
		String cslist = getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword()
				+ "," + maxCourses;
		return cslist;
	}

	/**
	 * Returns a status of comparison between this faculty and the other faculty
	 * @param o Faculty object
	 */
	@Override
	public int compareTo(Faculty o) {
		String thisFirstName = this.getFirstName();
		String thisLastName = this.getLastName();
		String thisId = this.getId();
		String otherFirstName = o.getFirstName();
		String otherLastName = o.getLastName();
		String otherId = o.getId();
		if (thisLastName.compareTo(otherLastName) < 0) {
			return -1;
		} else if (thisLastName.compareTo(otherLastName) > 0) {
			return 1;
		} else {
			if (thisFirstName.compareTo(otherFirstName) < 0) {
				return -1;
			} else if (thisFirstName.compareTo(otherFirstName) > 0) {
				return 1;
			} else {
				if (thisId.compareTo(otherId) < 0) {
					return -1;
				} else if (thisId.compareTo(otherId) > 0) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
	
	/**
	 * Returns the Faculty's Schedule.
	 * 
	 * @return the Faculty's current Schedule
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}
	
	/**
	 * Checks whether the faculty member is scheduled for more classes 
	 * than their max course limit.
	 * @return true if the faculty's schedule is overloaded
	 */
	public boolean isOverloaded() {
		return facultySchedule.getScheduledCourses().length > maxCourses;
	}
}
