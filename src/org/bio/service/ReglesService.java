package org.bio.service;

import java.util.List;

import org.bio.model.Rapport;
import org.bio.model.Regles;

public interface ReglesService {
	
	public void insertRegles(Regles regles);
	public void updateRegles(Regles regles);
	public List<Regles> findAllRegles();
	public void deleteRegles(Regles regles);
	public Regles getByid(int  id);
	public List<Regles> findWithRapport(Rapport rapport);

}
