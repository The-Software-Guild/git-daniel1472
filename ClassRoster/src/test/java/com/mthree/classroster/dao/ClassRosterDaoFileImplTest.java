/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.classroster.dao;

import com.mthree.classroster.dto.Student;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author danie
 */
public class ClassRosterDaoFileImplTest {
	
	ClassRosterDao testDao;
	
	public ClassRosterDaoFileImplTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() throws Exception{
        String testFile = "testroster.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
    }
	
	@After
	public void tearDown() {
	}

	@Test
	public void testAddGetStudent() throws Exception {
		// create our method test inputs
		String studentId = "0001";
		Student student = new Student(studentId);
		student.setFirstName("Ada");
		student.setLastName("Lovelace");
		student.setCohort("Java-May-1845");
		
		// Add the student to the DAO
		testDao.addStudent(studentId, student);
		// get the student from the DAO
		Student retrievedStudent = testDao.getStudent(studentId);
		
		// Check the data is equal
		assertEquals(student.getStudentId(), retrievedStudent.getStudentId(), "Checking student id.");
		assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "Checking student first name.");
		assertEquals(student.getLastName(), retrievedStudent.getLastName(), "Checking student last name.");
		assertEquals(student.getCohort(), retrievedStudent.getCohort(), "Checking student cohort.");
	}
	
	@Test
	public void testAddGetAllStudents() throws Exception {
		// Create our first student
		Student firstStudent = new Student("0001");
		firstStudent.setFirstName("Ada");
		firstStudent.setLastName("Lovelace");
		firstStudent.setCohort("Java-May-1845");
		
		// Create our second student
		Student secondStudent = new Student("0002");
		secondStudent.setFirstName("Charles");
		secondStudent.setLastName("Babbage");
		secondStudent.setCohort(".NET-May-1845");
		
		// add both our student to the DAO
		testDao.addStudent(firstStudent.getStudentId(), firstStudent);
		testDao.addStudent(secondStudent.getStudentId(), secondStudent);
	
		// Retrieve the list of all students within the DAO
		List<Student> allStudents = testDao.getAllStudents();

		// First check the general contents of the list
		assertNotNull(allStudents, "The list of students must not null");
		assertEquals(2, allStudents.size(),"List of students should have 2 students.");

		// Then the specifics
		assertTrue(testDao.getAllStudents().contains(firstStudent),
						"The list of students should include Ada.");
		assertTrue(testDao.getAllStudents().contains(secondStudent),
				  "The list of students should include Charles.");
	}
	
	@Test
	public void testRemoveStudent() throws Exception {
		// create two new students
		Student firstStudent = new Student("0001");
		firstStudent.setFirstName("Ada");
		firstStudent.setLastName("Lovelace");
		firstStudent.setCohort("Java-May-1945");
		
		Student secondStudent = new Student("0002");
		secondStudent.setFirstName("Charles");
		secondStudent.setLastName("Babbage");
		secondStudent.setCohort(".NET-May-1945");
		
		// Add both to the DAO
		testDao.addStudent(firstStudent.getStudentId(), firstStudent);
		testDao.addStudent(secondStudent.getStudentId(), secondStudent);
		
		// remove the first student - Ada
		Student removedStudent = testDao.removeStudent(firstStudent.getStudentId());
		
		// check that the correct object was removed
		assertEquals(removedStudent, firstStudent, "The removed student should be Ada.");
		
		// get all the students
		List<Student> allStudents = testDao.getAllStudents();
		
		// First check the general contents of the list
		assertNotNull(allStudents, "All students list should be not null.");
		assertEquals(allStudents.size(), 1, "All students should only have 1 student.");
		
		// then the specifics
		assertFalse("All students should NOT include Ada.", allStudents.contains(firstStudent));
		assertTrue(allStudents.contains(secondStudent), "All students should include Charles");
		
		// Remove the second student
		removedStudent = testDao.removeStudent(secondStudent.getStudentId());
		
		// check that the correct object was removed
		assertEquals(removedStudent, secondStudent, "The removed student should be Charles.");
		
		// retrieve all of the students again, and check the list.
		allStudents = testDao.getAllStudents();

		// Check the contents of the list - it should be empty
		assertTrue(allStudents.isEmpty(), "The retrieved list of students should be empty.");

		// Try to 'get' both students by their old id - they should be null!
		Student retrievedStudent = testDao.getStudent(firstStudent.getStudentId());
		assertNull("Ada was removed, should be null.", retrievedStudent);

		retrievedStudent = testDao.getStudent(secondStudent.getStudentId());
		assertNull("Charles was removed, should be null.", retrievedStudent);
	}
}