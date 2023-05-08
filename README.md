# PackScheduler

The PackScheduler project is a course project developed as a part of the CSC 217 course at North Carolina State University.<br>
It is a comprehensive course registration system designed to streamline the registration process for university students, faculty, and staff.

The application's user interface serves as the main control center, allowing users to log in and access features based on their user class.<br>
The system caters to three different user classes: Registrar, Faculty, and Student.<br>
Each user class has access to a range of features, including:

- Managing student directory (adding, modifying, removing, and saving)
- Managing faculty directory (adding, modifying, removing, and saving)
- Managing course catalog (adding, modifying, removing, and saving)
- Assigning faculty to teach a course
- Enrolling or dropping a student from a course

The PackScheduler project is thoroughly tested using JUnit tests and fully documented using Javadoc.<br>
For more information on the application and its features, please refer to the Javadoc located in the PackScheduler/doc directory.

If you wish to test the Registrar user functionalities in the PackScheduler project, follow these steps before testing:
1. Download the project and navigate to the top level of the PackScheduler project.
2. Create a new file called "registrar.properties" in the same directory.
3. Add the following information to the "registrar.properties" file:
```
first=Mark
last=Horner
id=mfhorner
email=mfhorner@ncsu.edu
pw=javadoc
```
This properties file contains the necessary information about the Registrar user, which the program uses in its test cases.<br>
By creating this file, you can simulate the Registrar user's actions and test the functionalities of the program from their perspective. 
