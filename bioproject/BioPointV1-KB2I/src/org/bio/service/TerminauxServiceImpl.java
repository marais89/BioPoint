package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.TerminauxDao;
import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.Terminaux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TerminauxServiceImpl implements TerminauxService,Serializable{
	@Autowired
private TerminauxDao terminauxDao;
	@Override
	@Transactional
	public void insertTerminal(Terminaux terminal) {
		terminauxDao.insertTerminal(terminal);

	}

	@Override
	@Transactional
	public void updateTerminal(Terminaux terminal) {
		terminauxDao.updateTerminal(terminal);

	}

	@Override
	@Transactional
	public List<Terminaux> findAllTerminaux() {

		return terminauxDao.findAllTerminaux();
	}

	@Override
	@Transactional
	public void deleteTerminal(Terminaux terminal) {
		terminauxDao.deleteTerminal(terminal);

	}

	@Override
	@Transactional
	public Terminaux getByid(int id) {
		
		return terminauxDao.getByid(id);
	}

	public TerminauxDao getTerminauxDao() {
		return terminauxDao;
	}

	public void setTerminauxDao(TerminauxDao terminauxDao) {
		this.terminauxDao = terminauxDao;
	}

	@Override
	@Transactional
	public List<Terminaux> findAllActiveTerminaux() {
		// TODO Auto-generated method stub
		return terminauxDao.findAllActiveTerminaux();
	}

	@Override
	@Transactional
	public List<Terminaux> findEnrollTerminaux() {
		// TODO Auto-generated method stub
		return terminauxDao.findEnrollTerminaux();
	}

	@Override
	@Transactional
	public List<Terminaux> findPointageTerminaux() {
		// TODO Auto-generated method stub
		return terminauxDao.findPointageTerminaux();
	}

	@Override
	@Transactional
	public PersonnelTerminal findAssociatedTerminal(Personnel p,
			Terminaux t) {
		// TODO Auto-generated method stub
		return terminauxDao.findAssociatedTerminal(p, t);
	}

	@Override
	@Transactional
	public Long terminauxToupdate() {
		return terminauxDao.terminauxToupdate();
	}
	
	@Override
	@Transactional
	public void truncateAll()
	{terminauxDao.truncateAll();}

}
