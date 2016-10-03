package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.MotifDao;
import org.bio.model.Motif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class MotifServiceImpl implements MotifService,Serializable{
	@Autowired
private MotifDao motifDao;
	@Override
	@Transactional
	public void insertMotif(Motif motif) {
		motifDao.insertMotif(motif);
		
	}

	@Override
	@Transactional
	public void updateMotif(Motif motif) {
		motifDao.updateMotif(motif);
		
	}

	@Override
	@Transactional
	public List<Motif> findAllMotifs() {
	
		return motifDao.findAllMotifs();
	}

	@Override
	@Transactional
	public void deleteMotif(Motif motif) {
		motifDao.deleteMotif(motif);
		
	}

	@Override
	@Transactional
	public Motif getByid(int id) {
		
		return motifDao.getByid(id);
	}

	public MotifDao getMotifDao() {
		return motifDao;
	}

	public void setMotifDao(MotifDao motifDao) {
		this.motifDao = motifDao;
	}

}
