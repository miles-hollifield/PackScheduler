package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Maintains a catalog for all the courses that students can take
 * 
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 * @author Victor Hermida
 * @author Thien Nguyen
 */
public class CourseCatalog {

	/** List of courses in the catalog */
	private SortedList<Course> catalog;

	/**
	 * Creates an empty course catalog.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Creates an empty course catalog. All courses in the previous list are list
	 * unless saved by the user.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs the course catalog by reading in course information from the given
	 * file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of course
	 * @throws IllegalArgumentException if filename cannot be read
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Course to the catalog. Returns true if the course is added and false
	 * if the course is unable to be added because their name matches another
	 * course's name.
	 * 
	 * @param name          course's name
	 * @param title         course's title
	 * @param section       course's section
	 * @param credits       course's credits
	 * @param instructorId  course's instructor id
	 * @param enrollmentCap enrollment capacity for the Course roll
	 * @param meetingDays   course's meeting days
	 * @param startTime     course's start time
	 * @param endTime       course's end time
	 * @return true if courses is added to catalog, otherwise returns false if
	 *         courses is already in catalog
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime,
				endTime);
		for (int i = 0; i < this.catalog.size(); i++) {
			if (name.equals(this.catalog.get(i).getName()) && section.equals(this.catalog.get(i).getSection())) {
				return false;
			}
		}
		return this.catalog.add(course);
	}

	/**
	 * Removes the course with the given name and section from the list of courses
	 * with the given name and section. Returns true if the course is removed and
	 * false if the course is not in the list.
	 * 
	 * @param name    course's name
	 * @param section course's section
	 * @return true if removed, otherwise returns false
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course s = catalog.get(i);
			if (s.getName().equals(name) && s.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a course from the catalog based on given name and section
	 * 
	 * @param name    course's name
	 * @param section course's section
	 * @return Course if the course is found, otherwise returns null if not found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		Course currentCourse;
		for (int i = 0; i < catalog.size(); i++) {
			Course s = catalog.get(i);
			if (s.getName().equals(name) && s.getSection().equals(section)) {
				currentCourse = s;
				return currentCourse;
			}
		}
		return null;
	}

	/**
	 * Returns all courses in the catalog with a column for name, section, title,
	 * meeting information, and number of remaining seats for courses in the
	 * catalog.
	 * 
	 * @return String array containing courses name, section, title, meeting
	 *         information, and number of remaining seats for courses in the
	 *         catalog.
	 */
	public String[][] getCourseCatalog() {
		String[][] newCatalog = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			Course s = catalog.get(i);
			newCatalog[i] = s.getShortDisplayArray();
		}
		return newCatalog;
	}

	/**
	 * Saves all courses in the catalog to a file.
	 * 
	 * @param fileName name of file to save courses to.
	 * @throws IllegalArgumentException if unable to write to filename
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

}