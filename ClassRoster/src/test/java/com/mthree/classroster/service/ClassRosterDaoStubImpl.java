package com.mthree.classroster.service;

import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterPersistenceException;
import com.mthree.classroster.dto.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Lambert
 * email: daniel.lambert.golf@gmail.com
 * date: date
 * purpose: Program Description
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao {

	public Student onlyStudent;
	
	public ClassRosterDaoStubImpl() {
		onlyStudent = new Student("0001");
		onlyStudent.setFirstName("Ada");
		onlyStudent.setLastName("Lovelace");
		onlyStudent.setCohort("Java-May-1845");
	}
	
	public ClassRosterDaoStubImpl(Student student) {
		onlyStudent = student;
	}

	@Override
	public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
		if(studentId.equals(onlyStudent.getStudentId()))
			return onlyStudent;
		else
			return null;
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		List<Student> studentList = new ArrayList<>();
		
		studentList.add(onlyStudent);
		return studentList;
	}

	@Override
	public Student getStudent(String studentId) throws ClassRosterPersistenceException {
		if(studentId.equals(onlyStudent.getStudentId()))
			return onlyStudent;
		else
			return null;
	}

	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
		if(studentId.equals(onlyStudent.getStudentId()))
			return onlyStudent;
		else
			return null;
	}

}
