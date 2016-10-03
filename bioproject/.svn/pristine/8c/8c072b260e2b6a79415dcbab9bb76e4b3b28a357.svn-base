package org.bio.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.dao.ParametreDao;
import org.bio.model.Ferie;
import org.bio.model.Parametre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParametreServiceImpl implements Serializable,ParametreService{

	@Autowired
	private ParametreDao parametreDao;
	public ParametreDao getParametreDao() {
		return parametreDao;
	}

	public void setParametreDao(ParametreDao parametreDao) {
		this.parametreDao = parametreDao;
	}

	

	@Override
	@Transactional
	public void insertParametre(Parametre parametre) {
		// TODO Auto-generated method stub
		parametreDao.insertParametre(parametre);
	}

	@Override
	@Transactional
	public void updateParametre(Parametre parametre) {
		// TODO Auto-generated method stub
		parametreDao.updateParametre(parametre);
	}

	@Override
	@Transactional
	public List<Parametre> findAllParametres() {
		// TODO Auto-generated method stub
		return parametreDao.findAllParametres();
	}

	@Override
	@Transactional
	public void deleteParametre(Parametre parametre) {
		// TODO Auto-generated method stub
		parametreDao.deleteParametre(parametre);
	}


	@Override
	@Transactional
	public Parametre byId(String id) {
		// TODO Auto-generated method stub
		return parametreDao.byId(id);
	}

	
}
