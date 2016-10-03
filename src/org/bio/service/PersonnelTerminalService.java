package org.bio.service;

import java.util.List;

import org.bio.model.PersonnelTerminal;

public interface PersonnelTerminalService {
	public void insertPersonnelTerminal(PersonnelTerminal personnelTerminal);
	public void updatePersonnelTerminal(PersonnelTerminal personnelTerminal);
	public List<PersonnelTerminal> findAllPersonnelTerminals();
	public void deletePersonnelTerminal(PersonnelTerminal personnelTerminal);
	public PersonnelTerminal getByid(int  id);
}


