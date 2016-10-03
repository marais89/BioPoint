package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.RegleDao;
import org.bio.model.Rapport;
import org.bio.model.Regles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReglesServiceImpl implements ReglesService, Serializable {

	@Autowired
	private RegleDao regleDao;
	
	public RegleDao getRegleDao() {
		return regleDao;
	}

	public void setRegleDao(RegleDao regleDao) {
		this.regleDao = regleDao;
	}

	@Override
	@Transactional
	public void insertRegles(Regles regles) {
		regleDao.insertRegle(regles);

	}

	@Override
	@Transactional
	public void updateRegles(Regles regles) {
		regleDao.updateRegle(regles);

	}

	@Override
	@Transactional
	public List<Regles> findAllRegles() {
		return regleDao.findAllRegles();
		
	}

	@Override
	@Transactional
	public void deleteRegles(Regles regles) {
		regleDao.deleteRegle(regles);

	}

	@Override
	@Transactional
	public Regles getByid(int id) {
		return regleDao.getByid(id);
	}
	
	@Override
	@Transactional
	public List<Regles> findWithRapport(Rapport rapport)
	{
		return regleDao.findWithIdRapport(rapport);
	}

}
