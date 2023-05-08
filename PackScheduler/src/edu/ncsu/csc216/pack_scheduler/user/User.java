/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

/**
 * User class contains the user's first name, last name, id, email and password
 * so that it can help the RegistrationManager to hold both Student and
 * Registrar classes. This is the superclass of the Student and Registrar
 * classes that holds the general and similar information so that its subclasses
 * can expand more on their differences.
 * 
 * @author Miles, Thien Nguyen
 *
 */
public abstract class User {
	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;

	/**
	 * Sets constructor for User with all of the fields.
	 * 
	 * @param firstName The first name of the user
	 * @param lastName  The last name of the user
	 * @param id        The id of the user
	 * @param email     The email of the user
	 * @param password  The password of the user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Returns the student's first name.
	 * 
	 * @return The student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the student's last name.
	 * 
	 * @return The student's lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the student's id.
	 * 
	 * @return The student's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the student's first name.
	 * 
	 * @param firstName The first name of the student
	 * @throws IllegalArgumentException if first name is null or empty
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name.
	 * 
	 * @param lastName The last name of the student
	 * @throws IllegalArgumentException if last name is null or empty
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the id for a Student.
	 * 
	 * @param id - id for the Student
	 * @throws IllegalArgumentException if the inputed id is invalid
	 */
	public void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the student's email.
	 * 
	 * @return The student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets up the student's email.
	 * 
	 * @param email The email of the student
	 * @throws IllegalArgumentException with the message Invalid Email if the email
	 *                                  is null, an empty string, the string does
	 *                                  not contain '@' or '.', and finally if '.'
	 *                                  comes before '@' in the string.
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email) || !email.contains("@") || !email.contains(".")
				|| email.lastIndexOf('.') < email.indexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the student's password.
	 * 
	 * @return The student's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password.
	 * 
	 * @param password The password of the student
	 * @throws IllegalArgumentException with the message Invalid password if
	 *                                  password is null or an empty string
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + email.hashCode();
		result = prime * result + firstName.hashCode();
		result = prime * result + id.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + password.hashCode();
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
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (!email.equals(other.email)) {
			return false;
		} else if (!firstName.equals(other.firstName)) {
			return false;
		} else if (!id.equals(other.id)) {
			return false;
		} else if (!lastName.equals(other.lastName)) {
			return false;
		} else if (!password.equals(other.password)) {
			return false;
		}
		return true;
	}

}
