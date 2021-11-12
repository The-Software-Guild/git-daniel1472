package com.mthree.classroster;

import com.mthree.classroster.controller.ClassRosterController;
import com.mthree.classroster.dao.*;
import com.mthree.classroster.ui.*;

/**
 * @author Daniel Lambert
 * email: daniel.lambert.golf@gmail.com
 * date: date
 * purpose: Program Description
 */
public class App {
	public static void main(String[] args) {
		
		// create the UserIO impl that we are going to use
		UserIO myIO = new UserIOConsoleImpl();
		
		// create the ClassRosterView that we are going to use
		ClassRosterView myView = new ClassRosterView(myIO);
		
		// create the ClassRoster DAO impl that we are going to use
		ClassRosterDao myDao = new ClassRosterDaoFileImpl();
		
		// create the controller with the impl we want to use and start runing it
		ClassRosterController controller = new ClassRosterController(myDao, myView);
		controller.run();
	}
	
}
