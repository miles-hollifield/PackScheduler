package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * This class represents the schedule that the user can use to interact with the
 * Courses. It can create a schedule for the current user and provides
 * functionality for naming the schedule, adding a Course to the schedule,
 * removing a Course from the schedule, getting information of Courses in the
 * schedule, and resetting the schedule.
 * 
 * @author Thien Nguyen
 * @author Miles Hollifield
 * @author Victor Hermida
 *
 */
public class Schedule {
	/** Schedule with Courses. */
	private ArrayList<Course> schedule;
	/** Schedule title. */
	private String title;

	/**
	 * Constructs a Schedule object with the empty schedule list and the default
	 * schedule title.
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}

	/**
	 * Returns the Schedule's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Schedule's title.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Adding the course to the schedule. Returns true if the given Course is
	 * successfully added to the student’s schedule. Throw exception if the given
	 * Course is a duplicate or is in conflict of another Course in the schedule.
	 * 
	 * @param course the course to be added
	 * @return true if the Course can be added to the schedule
	 * @throws IllegalArgumentException if the Course is in conflict of one another
	 * @throws IllegalArgumentException if the Course is a duplicate of another
	 *                                  Course already in the schedule
	 */
	public boolean addCourseToSchedule(Course course) {
		boolean flagCatalog = false;
		if (schedule.size() == 0) {
			flagCatalog = true;
			schedule.add(course);
		} else {
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).isDuplicate(course)) {
					throw new IllegalArgumentException("You are already enrolled in " + course.getName());
				}
				try {
					schedule.get(i).checkConflict(course);
				} catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
			flagCatalog = true;
			schedule.add(course);
		}
		return flagCatalog;
	}

	/**
	 * Removing the Course from the schedule. Returns true if the given Course can
	 * be removed from the schedule or false if the Course isn’t in the schedule.
	 * 
	 * @param course course to be removed
	 * 
	 * @return true if the given Course can be removed from the schedule or false if
	 *         the Course isn’t in the schedule
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (course == schedule.get(i)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}

	/**
	 * Returns the 2D array of Courses information in the schedule.
	 * 
	 * @return the 2D array of Courses information
	 */
	public String[][] getScheduledCourses() {
		String[][] schedules;
		if (schedule.size() == 0) {
			schedules = new String[0][0];
			return schedules;
		} else {
			schedules = new String[schedule.size()][5];
			for (int i = 0; i < schedule.size(); i++) {
				Course c = schedule.get(i);
				schedules[i] = c.getShortDisplayArray();
			}
		}
		return schedules;
	}
	
	/**
	 * Returns the credits out of the schedule 
	 * @return scheduleCredits credits from schedule
	 */
	public int getScheduleCredits() {
		int scheduleCredits = 0;
		for(int i = 0; i < schedule.size(); i++) {
			scheduleCredits += schedule.get(i).getCredits();
		}
		return scheduleCredits;
	}
	
	/**
	 * Returns true if course can be added to schedule
	 * @param course course from the schedule
	 * @return true if course can be add to the schedule, return false if it's null or already contains 
	 */
	public boolean canAdd(Course course) {
		//if(course == null || schedule.contains(course)) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (course.isDuplicate(schedule.get(i))) {
				return false;
			}
		}
		try {
			for(int i = 0; i < schedule.size(); i++) {
				course.checkConflict(schedule.get(i));
			}
		} catch(ConflictException e ) {
			return false;
		}
		return true;
	}

}
