package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.HistoriqueCatDao;
import org.bio.model.HistoriqueCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoriqueCatServiceImpl implements HistoriqueCatService,Serializable{
@Autowired
private HistoriqueCatDao HistoriqueCatDao;
	@Override
	@Transactional
	public void insertHistoriqueCat(HistoriqueCat HistoriqueCat) {
		HistoriqueCatDao.insertHistoriqueCat(HistoriqueCat);
		
	}

	@Override
	@Transactional
	public void updateHistoriqueCat(HistoriqueCat HistoriqueCat) {
		HistoriqueCatDao.updateHistoriqueCat(HistoriqueCat);
		
	}

	@Override
	@Transactional
	public List<HistoriqueCat> findAllHistoriqueCats() {
		return HistoriqueCatDao.findAllHistoriqueCats();
	}

	@Override
	@Transactional
	public void deleteHistoriqueCat(HistoriqueCat HistoriqueCat) {
		HistoriqueCatDao.deleteHistoriqueCat(HistoriqueCat);
		
	}

	@Override
	@Transactional
	public HistoriqueCat getByid(int id) {
		return HistoriqueCatDao.getByid(id);
	}

	@Override
	@Transactional
	public HistoriqueCat getCurrentHistoriqueCat(int idper) {
		// TODO Auto-generated method stub
		return HistoriqueCatDao.getCurrentHistoriqueCat(idper);
	}

}


