package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.SbiOperateurAffiliationDao;
import org.bio.model.SbiOperateurAffiliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SbiOperateurAffiliationServiceImpl implements
		SbiOperateurAffiliationService,Serializable{
@Autowired
	private SbiOperateurAffiliationDao sbiDao;
	
	
	public SbiOperateurAffiliationDao getSbiDao() {
	return sbiDao;
}

public void setSbiDao(SbiOperateurAffiliationDao sbiDao) {
	this.sbiDao = sbiDao;
}

	@Override
	@Transactional
	public void insertSbiOperateurAffiliation(
			SbiOperateurAffiliation SbiOperateurAffiliation) {
		// TODO Auto-generated method stub
		sbiDao.insertSbiOperateurAffiliation(SbiOperateurAffiliation);
	}

	@Override
	@Transactional
	public void updateSbiOperateurAffiliation(
			SbiOperateurAffiliation SbiOperateurAffiliation) {
		// TODO Auto-generated method stub
		sbiDao.updateSbiOperateurAffiliation(SbiOperateurAffiliation);
	}

	@Override
	@Transactional
	public List<SbiOperateurAffiliation> findAllSbiOperateurAffiliations() {
		// TODO Auto-generated method stub
		return sbiDao.findAllSbiOperateurAffiliations();
	}

	@Override
	@Transactional
	public void deleteSbiOperateurAffiliation(
			SbiOperateurAffiliation SbiOperateurAffiliation) {
		// TODO Auto-generated method stub
		sbiDao.deleteSbiOperateurAffiliation(SbiOperateurAffiliation);
	}

	@Override
	@Transactional
	public SbiOperateurAffiliation getByid(int id) {
		// TODO Auto-generated method stub
		return sbiDao.getByid(id);
	}

	@Override
	@Transactional
	public void deletebyOperateur(int id) {
		// TODO Auto-generated method stub
		sbiDao.deletebyOperateur(id);
	}

}
