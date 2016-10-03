package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.SocieteDao;
import org.bio.model.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SocieteServiceImpl implements SocieteService, Serializable {
	@Autowired
private SocieteDao societeDao;
	
	public SocieteDao getSocieteDao() {
		return societeDao;
	}

	public void setSocieteDao(SocieteDao societeDao) {
		this.societeDao = societeDao;
	}

	@Override
	@Transactional
	public void insertSociete(Societe Societe) {
		societeDao.insertSociete(Societe);
	}

	@Override
	@Transactional
	public void updateSociete(Societe Societe) {
		societeDao.updateSociete(Societe);

	}

	@Override
	@Transactional
	public List<Societe> findAllSocietes() {
		
		return societeDao.findAllSocietes();
	}

	@Override
	@Transactional
	public void deleteSociete(Societe Societe) {
		societeDao.deleteSociete(Societe);

	}

	@Override
	@Transactional
	public Societe getByid(int id) {
		
		return societeDao.getByid(id);
	}

}
