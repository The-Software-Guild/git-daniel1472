/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.classroster.service;

import com.mthree.classroster.dao.ClassRosterAuditDao;
import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterPersistenceException;
import com.mthree.classroster.dto.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author danie
 */
public class ClassRosterServiceLayerImplTest {
	
	private ClassRosterServiceLayer service;
	
	public ClassRosterServiceLayerImplTest() {
		/*
		ClassRosterDao dao = new ClassRosterDaoStubImpl();
		ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
		
		service = new ClassRosterServiceLayerImpl(dao, auditDao);
		*/
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testCreateValidStudent() {
		// arrange
		Student student = new Student("0002");
		student.setFirstName("Charles");
		student.setLastName("Babbage");
		student.setCohort(".NET-May-1845");
		
		// act
		try {
			service.createStudent(student);
		} catch (ClassRosterPersistenceException | ClassRosterDataValidationException | ClassRosterDuplicateIdException e) {
			fail("Student was valid. No exception should have been thrown.");
		}
	}
	
	@Test
	public void testCreateDuplicateIdStudent() {
		// arrange
		Student student = new Student("0001");
		student.setFirstName("Charles");
		student.setLastName("Babbage");
		student.setCohort(".NET-May-1845");
		
		// act
		try {
			service.createStudent(student);
			fail("Expected Duplicate Id exception was not thrown.");
		} catch (ClassRosterPersistenceException | ClassRosterDataValidationException e) {
			fail("Incorrent exception was thrown.");
		}
		catch(ClassRosterDuplicateIdException e){
			return;
		}
	}
	
	@Test
	public void testCreateStudentInvalidData() throws Exception {
		// arrange
		Student student = new Student("0002");
		student.setFirstName("");
		student.setLastName("Babbage");
		student.setCohort(".NET-May-1845");
		
		// act
		try {
			service.createStudent(student);
			fail("Expected ValidationException was not thrown.");
		} catch (ClassRosterPersistenceException | ClassRosterDuplicateIdException e) {
			fail("Incorrect exception was thrown.");
		}
		catch (ClassRosterDataValidationException e){
			return;
		}	
	}
	
	@Test
	public void testGetAllStudents() throws Exception {
		// arrange
		Student testClone = new Student("0001");
		testClone.setFirstName("Ada");
		testClone.setLastName("Lovelace");
		testClone.setCohort("Java-May-1845");
		
		// act and assert
		assertEquals("Should only have one student.", 1, service.getAllStudents().size());
	
		assertTrue("The one student should be Ada.", service.getAllStudents().contains(testClone));
	}
	
	@Test
	public void testGetStudent() throws Exception {
		// arrange
		Student testClone = new Student("0001");
		testClone.setFirstName("Ada");
		testClone.setLastName("Lovelace");
		testClone.setCohort("Java-May-1845");
		
		// act and assert
		Student shouldBeAda = service.getStudent("0001");
		
		assertNotNull("Getting 0001 should not be null", shouldBeAda);
		assertEquals("Student stored under 0001 should be Ada", testClone, shouldBeAda);
		
		Student shouldBeNull = service.getStudent("0042");
		
		assertNull("Getting 0042 should be null.", shouldBeNull);
	}
	
	@Test
	public void testRemoveSTudent() throws Exception {
		// arrange
		Student testClone = new Student("0001");
		testClone.setFirstName("Ada");
		testClone.setLastName("Lovelace");
		testClone.setCohort("Java-May-1845");
		
		// act and assert
		Student shouldBeAda = service.removeStudent("0001");
		assertNotNull("Removing 0001 should not be null.", shouldBeAda);
		assertEquals("Student removed from 0001 should be Ada.", testClone, shouldBeAda);
		
		Student shouldBeNull = service.removeStudent("0042");
		assertNull("Removing 0042 should be null.", shouldBeNull);
	}
}
