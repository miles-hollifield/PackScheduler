package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * This class controls the user that can interact with the Course Catalog or
 * Student Directory. The user can either be a Registrar, who is a university
 * official that maintains the directory of enrolled students, or a Student, who
 * is registered in the system by the Registrar and can create a schedule of
 * courses from courses listed in the Course Catalog that do not conflict with
 * each other.
 * 
 * @author Thien Nguyen
 *
 */
public class RegistrationManager {

	/** The single instance of RegistrationManager */
	private static RegistrationManager instance;
	/** The Course Catalog of RegistrationManager */
	private CourseCatalog courseCatalog;
	/** The Student Directory of RegistrationManager */
	private StudentDirectory studentDirectory;
	/** The Faculty Directory of RegistrationManager */
	private FacultyDirectory facultyDirectory;
	/** The registrar of RegistrationManager */
	private User registrar;
	/** The current user of RegistrationManager */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** The file that contains information about the Registrar user */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Sets the constructor for RegistrationManager.
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		
	}

	/**
	 * Create a new registrar with the parameters from the registrar.properties
	 * file.
	 * 
	 * @throws IllegalArgumentException if the registrar cannot be created
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * This method hashes the provided password.
	 * 
	 * @param pw password to be hashed
	 * @return the hashed password
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns the single instance of the RegistrationManager.
	 * 
	 * @return the single instance
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the Course Catalog.
	 * 
	 * @return the Course Catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the Student Directory.
	 * 
	 * @return the Student Directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Returns the Faculty Directory.
	 * 
	 * @return the Faculty Directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Log in the user with the provided id and password. Returns true if the user
	 * can be logged in or false if the user already logged into the system. If user
	 * doesn’t exist in the system or the user’s hashed password doesn’t match the
	 * stored hashed password, an IllegalArgumentException with message "Invalid id
	 * or password" is thrown.
	 * 
	 * @param id       id of the user
	 * @param password password of the user
	 * @return true if the user can be logged in or false if the user already logged
	 *         into the system
	 * @throws IllegalArgumentException if user doesn’t exist in the system or the
	 *                                  user’s hashed password doesn’t match the
	 *                                  stored hashed password
	 */
	public boolean login(String id, String password) {
		String localHashPW = hashPW(password);
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);

		if (currentUser != null && currentUser.getId().equals(id) && currentUser.getPassword().equals(localHashPW)) {
			return false;
		} else if (currentUser != null && !currentUser.getId().equals(id)
				&& !currentUser.getPassword().equals(localHashPW)) {
			return false;
		} else if (currentUser != null && !currentUser.getId().equals(id)
				&& currentUser.getPassword().equals(localHashPW)) {
			return false;
		} else if (s == null && f == null) {
			if (registrar.getId().equals(id) && registrar.getPassword().equals(localHashPW)) {
				this.currentUser = registrar;
				return true;
			} else if (registrar.getId().equals(id) && !registrar.getPassword().equals(localHashPW)) {
				return false;
			} else {
				throw new IllegalArgumentException("User doesn't exist.");
			}
//		} else if (!s.getPassword().equals(localHashPW)) {
//			return false;
//		} else {
//			this.currentUser = s;
//			return true;
//		}
		} else if (s != null && (f == null || !f.getId().equals(id))) {
	        if (s.getPassword().equals(localHashPW)) {
	            this.currentUser = s;
	            return true;
	        } else {
	            return false;
	        }
		} else if (f != null && (s == null || !s.getId().equals(id))) {
	        if (f.getPassword().equals(localHashPW)) {
	            this.currentUser = f;
	            return true;
	        } else {
	            return false;
	        }
	     // If id is same for Student and Faculty, then the User defaults to logging in the Student
	    } else if (s != null && f != null && s.getId().equals(f.getId())) {
	        if (s.getPassword().equals(localHashPW)) {
	            this.currentUser = s;
	            return true;
	        } else {
	            return false;
	        }
	    } else {
	        throw new IllegalArgumentException("User doesn't exist.");
	    }
		
	}

	/**
	 * Log the current user out of the RegistrationManager.
	 */
	public void logout() {
		this.currentUser = null;
	}

	/**
	 * Returns the current user of the RegistrationManager.
	 * 
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clear all of the data of the RegistrationManager.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Adds a Course to a FacultySchedule as long as the Registrar is the user
	 * who is currently logged in to the system.
	 * @param course the course to be added to the faculty schedule
	 * @param faculty the faculty who will be given the course to their schedule
	 * @return true if the course is successfully added to the faculty schedule
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser instanceof Registrar) {
			return faculty.getSchedule().addCourseToSchedule(course);
		} else if (currentUser != null && !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException();
		} else {
			return false;
		}
	}
	
	/**
	 * Removes a given course from the FacultySchedule, as long as the Registrar is the user
	 * who is currently logged in to the system.
	 * @param course the course to remove from the faculty schedule
	 * @param faculty the faculty member who will have the selected course removed
	 * from their schedule
	 * @return true if the course has been removed
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser instanceof Registrar) {
			return faculty.getSchedule().removeCourseFromSchedule(course);
		} else if (currentUser != null && !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException();
		} else {
			return false;
		}
	}
	
	/**
	 * Resets the FacultySchedule, which removes all the courses from the faculty schedule.
	 * @param faculty the faculty whose schedule should be cleared
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser instanceof Registrar) {
			faculty.getSchedule().resetSchedule();
		} else if (currentUser != null && !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Registrar represents a university official that maintains the directory of
	 * enrolled students. It extends its parent class, User, that holds the general
	 * information of every user within the RegistrationManager.
	 */
	private static class Registrar extends User {

		/**
		 * Create a Registrar user.
		 * 
		 * @param firstName The first name of the registrar user
		 * @param lastName  The last name of the registrar user
		 * @param id        The id of the registrar user
		 * @param email     The email of the registrar user
		 * @param hashPW    The password of the registrar user
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}
