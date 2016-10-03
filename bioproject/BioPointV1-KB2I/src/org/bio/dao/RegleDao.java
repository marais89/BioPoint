package org.bio.dao;

import java.util.List;

import org.bio.model.Rapport;
import org.bio.model.Regles;

public interface RegleDao {
	
	public void insertRegle(Regles regles);
	public void updateRegle(Regles regles);
	public List<Regles> findAllRegles();
	public void deleteRegle(Regles regles);
	public Regles getByid(int  id);
	public List<Regles> findWithIdRapport(Rapport rapport);

}
