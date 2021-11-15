package com.mthree.classroster.service;

/**
 * @author Daniel Lambert
 * email: daniel.lambert.golf@gmail.com
 * date: date
 * purpose: Program Description
 */
public class ClassRosterDuplicateIdException extends  Exception {

	public ClassRosterDuplicateIdException(String message) {
		super(message);
	}

	public ClassRosterDuplicateIdException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
