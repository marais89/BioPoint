package org.bio.dao;

import java.util.Date;
import java.util.List;

import org.bio.model.Mvt;

public interface MvtDao {
	public void insertMvt(Mvt mvt);
	public void updateMvt(Mvt mvt);
	public List<Mvt> findAllMvts();
	public void deleteMvt(Mvt mvt);
	public Mvt getByid(int  id);
	public List<Mvt> findMvtByDates(Date a1,Date a2);
	public List<Mvt> findNewMvt();
	public List<Mvt> findByIdOk(int idPer,Date d);
	public List<Mvt> allDistinctMvt() ;
}
