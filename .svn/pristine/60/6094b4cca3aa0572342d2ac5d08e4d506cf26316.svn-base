package org.bio.dao;

import java.util.List;

import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.Terminaux;

public interface TerminauxDao {
	
	public void insertTerminal(Terminaux terminal);
	public void updateTerminal(Terminaux terminal);
	public List<Terminaux> findAllTerminaux();
	public List<Terminaux> findAllActiveTerminaux();
	public List<Terminaux> findEnrollTerminaux();
	public List<Terminaux> findPointageTerminaux();

	public void deleteTerminal(Terminaux terminal);
	public Terminaux getByid(int  id);
	public PersonnelTerminal findAssociatedTerminal(Personnel p,Terminaux t);
	public Long terminauxToupdate();
	public void truncateAll() ;
}
