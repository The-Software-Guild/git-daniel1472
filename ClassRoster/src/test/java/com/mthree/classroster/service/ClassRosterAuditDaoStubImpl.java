package com.mthree.classroster.service;

import com.mthree.classroster.dao.ClassRosterAuditDao;
import com.mthree.classroster.dao.ClassRosterPersistenceException;

/**
 * @author Daniel Lambert
 * email: daniel.lambert.golf@gmail.com
 * date: date
 * purpose: Program Description
 */
public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

	@Override
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
		// do nothing...
	}

}
