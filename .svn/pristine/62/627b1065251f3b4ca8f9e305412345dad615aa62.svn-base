package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.CalendrierDao;
import org.bio.model.Calendrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CalendrierServiceImpl implements CalendrierService,Serializable{
	
	
	
	@Autowired
private CalendrierDao clendrierDao;
	@Override
	@Transactional
	public void insertCalendrier(Calendrier calendrier) {
		clendrierDao.insertCalendrier(calendrier);
		
	}

	@Override
	@Transactional
	public void updateCalendrier(Calendrier calendrier) {
		clendrierDao.updateCalendrier(calendrier);
		
	}

	@Override
	@Transactional
	public List<Calendrier> findAllCalendriers() {
	return clendrierDao.findAllCalendriers();
			
	}

	@Override
	@Transactional
	public void deleteCalendrier(Calendrier calendrier) {
		clendrierDao.deleteCalendrier(calendrier);
		
	}

	@Override
	@Transactional
	public Calendrier getByid(int id) {
		return clendrierDao.getByid(id);
	}

	public CalendrierDao getClendrierDao() {
		return clendrierDao;
	}

	public void setClendrierDao(CalendrierDao clendrierDao) {
		this.clendrierDao = clendrierDao;
	}
	  
}
