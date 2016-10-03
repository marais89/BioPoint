package org.bio.dao;

import java.util.List;
import java.util.Set;

import org.bio.model.Affiliation;
import org.bio.model.Operateur;

public interface AffiliationDao {
	public void insertAffiliation(Affiliation affiliation);
	public void updateAffiliation(Affiliation affiliation);
	public List<Affiliation> findAllAffiliation();
	public void deleteAffiliation(Affiliation affiliation);
	public Affiliation getByid(int  id);
	public Affiliation getRoot();
	public List<Affiliation> getAffiliation(String type);
	public List<String> getAffiliationTypes();
	public List<Affiliation>listaffiliation(Affiliation op);

}
