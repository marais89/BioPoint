package org.bio.dao;

import java.util.List;

import org.bio.model.HistoriqueCat;

public interface HistoriqueCatDao {
	public void insertHistoriqueCat(HistoriqueCat HistoriqueCat);
	public void updateHistoriqueCat(HistoriqueCat HistoriqueCat);
	public List<HistoriqueCat> findAllHistoriqueCats();
	public void deleteHistoriqueCat(HistoriqueCat HistoriqueCat);
	public HistoriqueCat getByid(int  id);
    public HistoriqueCat getCurrentHistoriqueCat(int idper);
}
