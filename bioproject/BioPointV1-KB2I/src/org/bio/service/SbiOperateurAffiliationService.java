package org.bio.service;

import java.util.List;

import org.bio.model.SbiOperateurAffiliation;

public interface SbiOperateurAffiliationService {
	public void insertSbiOperateurAffiliation(SbiOperateurAffiliation SbiOperateurAffiliation);
	public void updateSbiOperateurAffiliation(SbiOperateurAffiliation SbiOperateurAffiliation);
	public List<SbiOperateurAffiliation> findAllSbiOperateurAffiliations();
	public void deleteSbiOperateurAffiliation(SbiOperateurAffiliation SbiOperateurAffiliation);
	public SbiOperateurAffiliation getByid(int  id);
	public void deletebyOperateur(int id);
}
