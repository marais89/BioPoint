package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.RapportDao;
import org.bio.dao.SequenceDao;
import org.bio.model.Rapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RapportServiceImpl implements RapportService, Serializable{

	@Autowired
	private RapportDao rapportDao;
	public RapportDao getRapportDao() {
		return rapportDao;
	}

	public void setRapportDao(RapportDao rapportDao) {
		this.rapportDao = rapportDao;
	}

	@Override
	@Transactional
	public void insertRapport(Rapport rapport) {
		rapportDao.insertRapport(rapport);

	}

	@Override
	@Transactional
	public void updateRapport(Rapport rapport) {
		rapportDao.updateRapport(rapport);
	}

	@Override
	@Transactional
	public List<Rapport> findAllRapport() {
		return rapportDao.findAllRapport();
	}

	@Override
	@Transactional
	public void deleteRapport(Rapport rapport) {
		rapportDao.deleteRapport(rapport);

	}

	@Override
	@Transactional
	public Rapport getByid(int id) {
		return rapportDao.getByid(id);
	}

	@Override
	@Transactional
	public List<Rapport> findRapportPre(int x)
	{
		return rapportDao.findRapportPre(x);
	}
	
}
