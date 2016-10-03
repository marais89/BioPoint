package org.bio.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.dao.PointageDao;
import org.bio.model.Affiliation;
import org.bio.model.Personnel;
import org.bio.model.Pointage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PointageServiceImpl implements PointageService, Serializable {
	@Autowired
private PointageDao pointageDao;
	
	public PointageDao getPointageDao() {
		return pointageDao;
	}

	public void setPointageDao(PointageDao pointageDao) {
		this.pointageDao = pointageDao;
	}

	@Override
	@Transactional
	public void insertPointage(Pointage pointage) {
		pointageDao.insertPointage(pointage);

	}

	@Override
	@Transactional
	public void updatePointage(Pointage pointage) {
		pointageDao.updatePointage(pointage);

	}

	@Override
	@Transactional
	public List<Pointage> findAllPointages() {
		
		return pointageDao.findAllPointages();
	}

	@Override
	@Transactional
	public void deletePointage(Date d1,Date d2) {
		 pointageDao.deletePointage(d1,d2);

	}

	@Override
	@Transactional
	public Pointage getByid(int id) {
		
		return pointageDao.getByid(id);
	}

	@Override
	@Transactional
	public List<Pointage> FindPointageForDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return pointageDao.FindPointageForDay(d, p);
	}

	@Override
	@Transactional
	public List<Pointage> findPointages(Date d1, Date d2, Affiliation a) {
		// TODO Auto-generated method stub
		return pointageDao.findPointages(d1, d2, a);
	}

	@Override
	@Transactional
	public Long findPointagesAnomalie() {
		// TODO Auto-generated method stub
		return pointageDao.findPointagesAnomalie();
	}
	
	@Override
	@Transactional
	public void deleteById(int idPer, Date d)
	{
		pointageDao.deleteById(idPer, d);
	}

}
