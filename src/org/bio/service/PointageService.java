package org.bio.service;

import java.util.Date;
import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.Personnel;
import org.bio.model.Pointage;

public interface PointageService {
	public void insertPointage(Pointage pointage);
	public void updatePointage(Pointage pointage);
	public List<Pointage> findAllPointages();
	public void deletePointage(Date d,Date d2);
	public Pointage getByid(int  id);
	public List<Pointage> FindPointageForDay(Date d,Personnel p);
	public List<Pointage> findPointages(Date d1,Date d2,Affiliation a);
	public Long findPointagesAnomalie();
	public void deleteById(int idPer, Date d);


}
