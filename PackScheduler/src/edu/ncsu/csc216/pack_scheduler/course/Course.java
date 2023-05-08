package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class contains the course name, section number, the number of credit
 * hours, instructor’s unity id for a course record. A Course will be listed in
 * the catalog for the student to add to their schedule if it is valid. The
 * Course will provide all of the necessary information for the student to
 * consider while creating their schedule.
 * 
 * @author Hari Vemulapalli, Thien Nguyen, Miles Hollifield
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's name validator */
	private CourseNameValidator validator;
	/** Course roll */
	private CourseRoll roll;

	/**
	 * Creates a Course object with all defined parameters except startTime and
	 * endTime, assuming they are 0 since there is no meeting time.
	 * 
	 * @param name          - Name of Course
	 * @param title         - Title of Course
	 * @param section       - Section of Course
	 * @param credits       - Number of credit hours of Course
	 * @param instructorId  - Instructor ID for the Course
	 * @param meetingDays   - Meeting Days for the Course
	 * @param enrollmentCap - Enrollment capacity for the Course roll
	 * @throws IllegalArgumentException Throws when input is null or empty string
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Sets the parameters of the Course object.
	 * 
	 * @param name          - Name of Course
	 * @param title         - Title of Course
	 * @param section       - Section of Course
	 * @param credits       - Number of credit hours of Course
	 * @param instructorId  - Instructor ID for the Course
	 * @param enrollmentCap - Enrollment capacity for the Course roll
	 * @param meetingDays   - Meeting Days for the Course
	 * @param endTime       - Ending time for the Course
	 * @param startTime     - Starting time for the Course
	 * @throws IllegalArgumentException Throws when input is null or empty string
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a Length of less than 5 or
	 * more than 8, does not contain a space between Letter characters and number
	 * characters, has less than 1 or more than 4 Letter characters, or not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception if the name is null or is an empty string
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		// Throw exception if the name contains less than 4 character or greater than 8
		// characters
		if (name.length() < 4 || name.length() > 8) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		validator = new CourseNameValidator();
		try {
			if (validator.isValid(name)) {
				this.name = name;
			} else {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. If the section number is NOT exactly three digits,
	 * an IllegalArgumentException will be thrown.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {
		// Checks if section length is NOT 3 or the string is null
		if (section == null || section.length() != 3 || "".equals(section)) {
			throw new IllegalArgumentException("Invalid section.");
		} else {
			// Checks if any part of the String section is NOT a digit
			for (int i = 0; i < section.length(); i++) {
				char s = section.charAt(i);
				if (!Character.isDigit(s)) {
					throw new IllegalArgumentException("Invalid section.");
				}
			}
			this.section = section;
		}

	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits. If the credit hours are not a number or are less
	 * than 1 or greater than 5, an IllegalArgumentException will be thrown.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		// Checks if credits are less than 1 or more than 5 and throws an exception if
		// true
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		// Sets the credits if valid
		this.credits = credits;
	}

	/**
	 * Returns the Course's Instructor ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's Instructor ID. If the Instructor ID is a null or empty
	 * string, an IllegalArgumentException will be thrown.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructorId parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		// Checks if instructorId String is null or an empty String
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		// Sets the instructorId if the string is valid
		this.instructorId = instructorId;
	}

	/**
	 * Sets the Course's meeting days, start time, and end time. NOTE: This is an
	 * overwritten method from the Activity superclass. Any details for exceptions
	 * for particular inputs are in the Activity superclass.
	 * 
	 * @param meetingDays - days when the course will meet
	 * @param startTime   - time when the course starts
	 * @param endTime     - time when the course ends
	 * @throws IllegalArgumentException when the parameters meetingDays, startTime,
	 *                                  and/or endTime is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Checks if course's meeting days are on Saturday or Sunday
		if (meetingDays.contains("U") || meetingDays.contains("S")) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} else {
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}
	
	/**
	 * Returns the Course roll of this Class object.
	 * 
	 * @return the Course roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		// Concatenates information into a string from the individual state variables of
		// the Course object
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Provides some of the information about a course
	 * 
	 * @return shortDisplayArray
	 */
	public String[] getShortDisplayArray() {
		String openSeats = Integer.toString(roll.getOpenSeats());
		String[] shortDisplayArray = { name, section, getTitle(), getMeetingString(), openSeats };
		return shortDisplayArray;
	}

	/**
	 * Provides all of the information about a course
	 * 
	 * @return longDisplayArray
	 */
	public String[] getLongDisplayArray() {

		String[] longDisplayArray = { name, section, getTitle(), Integer.toString(credits), instructorId,
				getMeetingString(), "" };
		return longDisplayArray;
	}

	/**
	 * Checks if the given Course object is a duplicate
	 * 
	 * @param activity - the Course object as input
	 * @return duplicate
	 */
	public boolean isDuplicate(Activity activity) {
		boolean duplicate = false;
		String thisName = this.name;
		String courseName = ((Course) activity).getName();
		if (thisName.equals(courseName)) {
			duplicate = true;
		}
		return duplicate;
	}

	/**
	 * Compares first the Course name and then section of this course and the given
	 * course and returns a result based on which is first
	 * 
	 * @param o - given course
	 * @return int result
	 */
	@Override
	public int compareTo(Course o) {
		String thisCourseName = this.name;
		String thisCourseSection = this.section;
		String otherCourseName = o.getName();
		String otherCourseSection = o.getSection();
		if (thisCourseName.compareTo(otherCourseName) < 0) {
			return -1;
		} else if (thisCourseName.compareTo(otherCourseName) > 0) {
			return 1;
		} else {
			if (thisCourseSection.compareTo(otherCourseSection) < 0) {
				return -1;
			} else if (thisCourseSection.compareTo(otherCourseSection) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}