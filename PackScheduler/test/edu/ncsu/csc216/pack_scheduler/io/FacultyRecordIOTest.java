package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Test class for FacultyRecordIO
 * @author mfhollif
 * @author Jamal Mohamad
 */
public class FacultyRecordIOTest {
	
	/** String variable for checking file was read in correctly */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	/** String variable for checking file was read in correctly */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** String variable for checking file was read in correctly */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** String variable for checking file was read in correctly */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** String variable for checking file was read in correctly */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** String variable for checking file was read in correctly */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** String variable for checking file was read in correctly */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** String variable for checking file was read in correctly */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	
	/**
	 * String List used to store the String variables
	 */
	private String[] validFacultys = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5,
	        validFaculty6, validFaculty7};
	
	
	/**
	 * Test FacultyRecordIO.readFacultyRecords()
	 * @throws FileNotFoundException if the file is not readable or doesn't exist
	 */
	@Test
	public void testReadFacultyRecords() throws FileNotFoundException{
		try {
			//Checks if the Facultys in a file are correct
			//Using Faculty_records.txt and validFacultys array to check correct information!
			// Changed code to LinkedList<Faculty>
			LinkedList<Faculty> validFacultyRecords = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(8, validFacultyRecords.size());
			
			for(int i = 0; i < validFacultyRecords.size(); i++) {
				if(!validFacultyRecords.get(i).toString().equals(validFacultys[i])) {
					fail("Unmatched records!");
				}
			}
			//Makes sure that nothing is written from invalid_Faculty_records.txt
			// Changed code to LinkedList<Faculty>
			LinkedList<Faculty> invalidFacultyRecords = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
			if(invalidFacultyRecords.size() != 0) {
				fail("Cannot have invalid information");
			}
			//Checks Faculty information from a file that doesn't exist
			FacultyRecordIO.readFacultyRecords("test-files/no_found_file.txt");
		}
		catch (FileNotFoundException e) {
			
			
			//This line is invalid where the file is not found!!
		}
			
	}
	
	/**
	 * Test FacultyRecordIO.writeFacultyRecords()
	 * @throws IOException if specified file or directory does not exist
	 */
	@Test
	public void testWriteFacultyRecords() throws IOException {
		//Creates a Faculty LinkedList to use to test the writing of Faculty records
		LinkedList<Faculty> facultys = new LinkedList<Faculty>();
		facultys.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 2));
		facultys.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 3));
		facultys.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 1));
		FacultyRecordIO.writeFacultyRecords("test-files/new_faculty_records.txt", facultys);
		LinkedList<Faculty> expectingFacultys = FacultyRecordIO.readFacultyRecords("test-files/expected_faculty_records.txt");
		//Checks if the output is as expected!
		for(int i = 0; i < facultys.size(); i++) {
			if(!facultys.get(i).equals(expectingFacultys.get(i))) {
				fail("Not expected output!");
			}
		}
		//Checking for an invalid file or directory
				assertThrows(IOException.class, 
				() -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_Faculty_records.txt", facultys));
	}
	
}
