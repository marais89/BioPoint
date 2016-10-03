package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.PersonnelTerminalDao;
import org.bio.model.PersonnelTerminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PersonnelTerminalServiceImpl implements PersonnelTerminalService, Serializable {
@Autowired
	private PersonnelTerminalDao personnelTerminalDao;

	public PersonnelTerminalDao getPersonnelTerminalDao() {
	return personnelTerminalDao;
}

public void setPersonnelTerminalDao(
		PersonnelTerminalDao personnelTerminalDao) {
	this.personnelTerminalDao = personnelTerminalDao;
}

	@Override
	@Transactional
	public void insertPersonnelTerminal(PersonnelTerminal personnelTerminal) {
		personnelTerminalDao.insertPersonnelTerminal(personnelTerminal);
	}

	@Override
	@Transactional
	public void updatePersonnelTerminal(
			PersonnelTerminal personnelTerminal) {
	personnelTerminalDao.updatePersonnelTerminal(personnelTerminal);

	}

	@Override
	@Transactional
	public List<PersonnelTerminal> findAllPersonnelTerminals() {
	
		return personnelTerminalDao.findAllPersonnelTerminal();
	}

	@Override
	@Transactional
	public void deletePersonnelTerminal(
			PersonnelTerminal personnelTerminal) {
		personnelTerminalDao.deletePersonnelTerminal(personnelTerminal);

	}

	@Override
	@Transactional
	public PersonnelTerminal getByid(int id) {
		
		return personnelTerminalDao.getByid(id);

	}

}
