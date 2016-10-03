package org.bio.dao;

import java.util.List;

import org.bio.model.Horaires;
import org.bio.model.HorairesId;

public interface HoraireDao {
	public void insertHoraire(Horaires horaire);
	public void updateHoraire(Horaires horaire);
	public List<Horaires> findAllHoraires();
	public void deleteHoraire(Horaires horaire);
	public Horaires getByid(HorairesId  id);
	
}
