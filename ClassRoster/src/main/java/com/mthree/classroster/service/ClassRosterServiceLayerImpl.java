package com.mthree.classroster.service;

import com.mthree.classroster.dao.ClassRosterAuditDao;
import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterPersistenceException;
import com.mthree.classroster.dto.Student;
import java.util.List;

/**
 * @author Daniel Lambert
 * email: daniel.lambert.golf@gmail.com
 * date: date
 * purpose: Program Description
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

	private ClassRosterDao dao;
	private ClassRosterAuditDao auditDao;

	public ClassRosterServiceLayerImpl(ClassRosterDao dao , ClassRosterAuditDao auditDao) {
		this.dao = dao;
		this.auditDao = auditDao;
	}
	
	@Override
	public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
		// validate that a student with this id does not already exist
		if(dao.getStudent(student.getStudentId()) != null){
			throw  new ClassRosterDuplicateIdException("ERROR: Could not create student. Student Id " + student.getStudentId() + " already exits");
		}
		
		// validate that all the student data is in the right format
		ValidateStudentData(student);
		
		// add the student it is valid
		dao.addStudent(student.getStudentId(), student);
		
		// the student was create, log that to the audit log
		auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED.");
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		return dao.getAllStudents();
	}

	@Override
	public Student getStudent(String studentId) throws ClassRosterPersistenceException {
		return dao.getStudent(studentId);
	}

	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
		// do the student remove
		Student removedStudent = dao.removeStudent(studentId);
		
		// write to the log that a student was removed
		auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
		
		return removedStudent;
	}
	
	private void ValidateStudentData(Student student) throws 
			  ClassRosterDataValidationException {
		if(student.getFirstName() == null
			|| student.getFirstName().trim().length() == 0
			|| student.getLastName() == null
			|| student.getLastName().trim().length() == 0
			|| student.getCohort() == null 
			|| student.getCohort().trim().length() == 0) {
			throw new ClassRosterDataValidationException("Error: All fields [First name, Last Name, Cohort] are required");
		}
			
	}
	
}
