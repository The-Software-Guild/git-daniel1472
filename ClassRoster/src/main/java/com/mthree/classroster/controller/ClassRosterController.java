package com.mthree.classroster.controller;

import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterDaoException;
import com.mthree.classroster.dto.Student;
import com.mthree.classroster.ui.ClassRosterView;
import java.util.List;

/**
 * @author Daniel Lambert
 * email: daniel.lambert.golf@gmail.com
 * date: date
 * purpose: This class controls the flow of the program
 */
public class ClassRosterController {
	private ClassRosterDao dao;
	private ClassRosterView view;

	public ClassRosterController(ClassRosterDao dao, ClassRosterView view) {
		this.dao = dao;
		this.view = view;
	}
	
	// call this method to start the class roster program
	public void run() {
		boolean keepGoing = true;
		int menuSelection;
		
		// safely loop doing menu commands from the user till the users finishes
		try{
			while (keepGoing) {	
				
				// show menu and prompt the user for menu selection
				menuSelection = view.printMenuAndGetSelection();
			
				// exicute the right command based on the users choice
				switch(menuSelection) {
					case 1: 
						listStudents();
						break;
					case 2:
						createStudent();
						break;
					case 3:
						viewStudent();
						break;
					case 4:
						removeStudent();
						break;
					case 5:
						keepGoing = false;
						break;
					default:
						unknownCommand();
						break;
				}
			}
		
			// display a closing message before exiting the program
			exitMessage();
		}
		catch(ClassRosterDaoException e){
			// there was an error show error message and quit
			view.displayErrorMessage(e.getMessage());
		}
	}
	
	// creates a student
	private void createStudent() throws ClassRosterDaoException {
		// display create student banner
		view.displayCreateStudentBanner();
		
		// create new student from data prompted from the user
		Student newStudent = view.getNewStudentInfo();
		
		// add that student to dao
		dao.addStudent(newStudent.getStudentId(), newStudent);
		
		// tell the user that student was created successfuly
		view.displayCreateSuccessBanner();
	}
	
	// list all existing students
	private void listStudents() throws ClassRosterDaoException {
		// display list all students banner
		view.displayAllBanner();
		
		// get all students and display that data for the user
		List<Student> studentList = dao.getAllStudents();
		view.displayStudentList(studentList);
	}
	
	// view a single student
	private void viewStudent() throws ClassRosterDaoException {
		// display student banner
		view.displayDisplayStudentBanner();
		
		// get the student id that the user would like to see
		String studentId = view.getStudentIdChoice();
		
		// get the student with that id
		Student student = dao.getStudent(studentId);
		
		// display that data to the user
		view.displayStudent(student);
	}
	
	// remove a student
	private void removeStudent() throws ClassRosterDaoException {
		// display remove student banner
		view.displayRemoveStudentBanner();
		
		// get the student id of the student that the user would like to remove
		String studentId = view.getStudentIdChoice();
		
		// remove the student with that id
		Student removedStudent = dao.removeStudent(studentId);
		
		// display the results of the remove
		view.displayRemoveResult(removedStudent);
	}
	
	private void unknownCommand() {
		// display to the user that an unknown command happened
		view.displayUnknownCommandBanner();
	}
	
	private void exitMessage() {
		// display to the user an exiting message
		view.displayExitBanner();
	}
}
