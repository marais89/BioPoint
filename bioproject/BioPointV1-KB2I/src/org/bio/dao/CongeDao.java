package org.bio.dao;

import java.util.List;

import org.bio.model.Conge;

public interface CongeDao {
	public void insertConge(Conge conge);
	public void updateConge(Conge conge);
	public List<Conge> findAllConges();
	public void deleteConge(Conge conge);
	public Conge getByid(int  id);
}
