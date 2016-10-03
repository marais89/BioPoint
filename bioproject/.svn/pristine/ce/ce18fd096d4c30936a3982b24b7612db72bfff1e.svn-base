package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.LostMvtDao;
import org.bio.dao.MvtDao;
import org.bio.model.LostMvt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class LostMvtServiceImpl implements LostMvtService,Serializable {
	@Autowired
	private LostMvtDao lostMvtDao;

	
	

	public LostMvtDao getLostMvtDao() {
		return lostMvtDao;
	}

	public void setLostMvtDao(LostMvtDao lostMvtDao) {
		this.lostMvtDao = lostMvtDao;
	}

	@Override
	@Transactional
	public void insertLostMvt(LostMvt LostMvt) {
		lostMvtDao.insertLostMvt(LostMvt);

	}

	@Override
	@Transactional
	public void updateLostMvt(LostMvt LostMvt) {
		lostMvtDao.updateLostMvt(LostMvt);

	}

	@Override
	@Transactional
	public List<LostMvt> findAllLostMvts() {
		
		return lostMvtDao.findAllLostMvts();
	}

	@Override
	@Transactional
	public void deleteLostMvt(LostMvt LostMvt) {
		lostMvtDao.deleteLostMvt(LostMvt);

	}

	@Override
	@Transactional
	public LostMvt getByid(int id) {
		
		return lostMvtDao.getByid(id);
	}
	
	@Override
	@Transactional
	public List<LostMvt> getByBckid(int bckid)
	{
		return lostMvtDao.getByBckid(bckid);
	}

}
