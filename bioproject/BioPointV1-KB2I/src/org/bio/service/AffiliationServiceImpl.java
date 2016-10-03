package org.bio.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.bio.dao.AffiliationDao;
import org.bio.model.Affiliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AffiliationServiceImpl implements AffiliationService, Serializable {
	@Autowired
private AffiliationDao affiliationDao;
	
	@Override
	@Transactional
	public void insertAffiliation(Affiliation affiliation) {
		affiliationDao.insertAffiliation(affiliation);
		
	}

	@Override
	@Transactional
	public void updateAffiliation(Affiliation affiliation) {
		affiliationDao.updateAffiliation(affiliation);
		
	}

	@Override
	@Transactional
	public List<Affiliation> findAllAffiliations() {
		return affiliationDao.findAllAffiliation();
	}

	@Override
	@Transactional
	public void deleteAffiliation(Affiliation affiliation) {
		affiliationDao.deleteAffiliation(affiliation);
		
	}

	@Override
	@Transactional
	public Affiliation getByid(int id) {
		return affiliationDao.getByid(id);
	}

	public AffiliationDao getAffiliationDao() {
		return affiliationDao;
	}

	public void setAffiliationDao(AffiliationDao affiliationDao) {
		this.affiliationDao = affiliationDao;
	}
	@Override
	@Transactional
	public Affiliation getRoot() {
		// TODO Auto-generated method stub
		return affiliationDao.getRoot();
	}

	@Override
	@Transactional
	public List<Affiliation> getAffiliation(String type) {
		// TODO Auto-generated method stub
		return affiliationDao.getAffiliation(type);
	}

	@Override
	@Transactional
	public List<String> getAffiliationTypes() {
		// TODO Auto-generated method stub
		return affiliationDao.getAffiliationTypes();
	}

	@Override
	@Transactional
	public List<Affiliation> listaffiliation(Affiliation op) {
		// TODO Auto-generated method stub
		return affiliationDao.listaffiliation(op);
	}
}
