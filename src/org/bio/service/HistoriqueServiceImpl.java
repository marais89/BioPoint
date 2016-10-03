package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.HistoriqueDao;
import org.bio.model.Historique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class HistoriqueServiceImpl implements HistoriqueService,Serializable{
@Autowired
private HistoriqueDao historiqueDao;
	@Override
	@Transactional
	public void insertHistorique(Historique historique) {
		historiqueDao.insertHistorique(historique);
		
	}

	@Override
	@Transactional
	public void updateHistorique(Historique historique) {
		historiqueDao.updateHistorique(historique);
		
	}

	@Override
	@Transactional
	public List<Historique> findAllHistoriques() {
		return historiqueDao.findAllHistoriques();
	}

	@Override
	@Transactional
	public void deleteHistorique(Historique historique) {
		historiqueDao.deleteHistorique(historique);
		
	}

	@Override
	@Transactional
	public Historique getByid(int id) {
		return historiqueDao.getByid(id);
	}

}
