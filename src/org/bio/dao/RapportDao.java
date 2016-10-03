package org.bio.dao;

import java.util.Date;
import java.util.List;

import org.bio.model.Rapport;


public interface RapportDao {
	public void insertRapport(Rapport rapport);
	public void updateRapport(Rapport rapport);
	public List<Rapport> findAllRapport();
	public void deleteRapport(Rapport rapport);
	public Rapport getByid(int  id);
	public List<Rapport> findRapportPre(int x);
}
