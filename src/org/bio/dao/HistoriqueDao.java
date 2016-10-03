package org.bio.dao;

import java.util.List;

import org.bio.model.Historique;

public interface HistoriqueDao {
	public void insertHistorique(Historique historique);
	public void updateHistorique(Historique historique);
	public List<Historique> findAllHistoriques();
	public void deleteHistorique(Historique historique);
	public Historique getByid(int  id);
}
