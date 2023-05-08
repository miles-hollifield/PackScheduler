package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
/**
 * We will be looking in the files for each Faculty to see if their firstName,
 * lastName, id, email, password, maxCourses is correctly ordered
 * 
 * @author Jamal Mohamad
 *
 */

public class FacultyRecordIO {
	/**
	 * Reads a file that includes information about Facultys and processes into an
	 * SortedList of Faculty objects
	 * 
	 * @param fileName - file where Faculty information is kept
	 * @return Facultys Facultys object to be returned
	 * @throws FileNotFoundException if a file cannot be read or found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		LinkedList<Faculty> facultys = new LinkedList<Faculty>(); // Create an empty array of Faculty objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try {
				// Read the line, process it in the processFaculty() method, and get the object
				Faculty faculty = processFaculty(fileReader.nextLine());
				facultys.add(faculty);
			} catch (Exception e) {
				// This line is invalid where the Faculty object cannot be created, mainly due
				// to invalid parameters
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the SortedList with all the Facultys we read!
		return facultys;
	}

	/**
	 * Process information from String line into a Faculty object
	 * 
	 * @param line The line we will be using to scan through each line in the file
	 * @return FacultyObject
	 * @throws IllegalArgumentException if any parameters for the Faculty object are invalid
	 */
	private static Faculty processFaculty(String line) {
		// We created a scanner to scan through the files
		Scanner facultyProcess = new Scanner(line);
		// Using the Delimiter will break apart each string to see if their correct
		facultyProcess.useDelimiter(",");
		try {
			// We will be scanning each line to see if the Strings and integers are in the
			// file
			String firstName = facultyProcess.next();
			String lastName = facultyProcess.next();
			String id = facultyProcess.next();
			String email = facultyProcess.next();
			String password = facultyProcess.next();
			int maxCourses = facultyProcess.nextInt();
			// This is the format that the file should be in in order to return
			Faculty facultyObject = new Faculty(firstName, lastName, id, email, password, maxCourses);
			facultyProcess.close();
			return facultyObject;

		} catch (Exception e) {
			facultyProcess.close();
			// throw if the inputs are Invalid to the parameters
			throw new IllegalArgumentException("Invalid inputs for the parameters!");
		}
	}

	/**
	 * Writes the Faculty objects from FacultyDirectory into a given file
	 * 
	 * @param fileName         - name of file to save Faculty objects in
	 * @param facultyDirectory - SortedList where Faculty objects are from
	 * @throws IOException if unable to read the file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		// this for loop will allow us to print out the file for FacultyDirectory
		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		// Closing the fileWriter is important
		fileWriter.close();

	}

}
