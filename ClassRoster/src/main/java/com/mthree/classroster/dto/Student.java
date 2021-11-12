package com.mthree.classroster.dto;

/**
 * @author Daniel Lambert
 * email: daniel.lambert.golf@gmail.com
 * date: date
 * purpose: Program Description
 */
public class Student {
	private String firstName;
	private String lastName;
	private String studentId;
    // Programming Language + cohort month/year
	private String cohort;
	
	public Student(String studentId){
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCohort() {
		return cohort;
	}

	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
	 
}
