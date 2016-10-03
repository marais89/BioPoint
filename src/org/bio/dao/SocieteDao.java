package org.bio.dao;

import java.util.List;

import org.bio.model.Societe;;

public interface SocieteDao {
	public void insertSociete(Societe societe);
	public void updateSociete(Societe societe);
	public List<Societe> findAllSocietes();
	public void deleteSociete(Societe societe);
	public Societe getByid(int  id);
}
