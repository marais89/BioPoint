package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.CongeDao;
import org.bio.model.Conge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CongeServiceImpl implements CongeService,Serializable{
	@Autowired
private CongeDao congeDao;
	@Override
	@Transactional
	public void insertConge(Conge conge) {
		congeDao.insertConge(conge);
		
	}

	@Override
	@Transactional
	public void updateConge(Conge conge) {
		congeDao.updateConge(conge);
		
	}

	@Override
	@Transactional
	public List<Conge> findAllConges() {
	return congeDao.findAllConges();
	}

	@Override
	@Transactional
	public void deleteConge(Conge conge) {
		congeDao.deleteConge(conge);
		
	}

	@Override
	@Transactional
	public Conge getByid(int id) {
		return congeDao.getByid(id);
	}

	public CongeDao getCongeDao() {
		return congeDao;
	}

	public void setCongeDao(CongeDao congeDao) {
		this.congeDao = congeDao;
	}

}
