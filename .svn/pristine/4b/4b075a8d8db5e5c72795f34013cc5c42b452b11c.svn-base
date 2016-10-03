package org.bio.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.dao.GlobalHistoriqueDao;
import org.bio.model.GlobalHistorique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GlobalHistoriqueServiceImpl implements GlobalHistoriqueService,Serializable {

	@Autowired
	private GlobalHistoriqueDao globalhistoriquedao;
	
	@Override
	@Transactional
	public void insertGlobalHistorique(GlobalHistorique globalhistorique) {
		globalhistoriquedao.insertGlobalHistorique(globalhistorique);

	}

	@Override
	@Transactional
	public void updateGlobalHistorique(GlobalHistorique globalhistorique) {
		globalhistoriquedao.updateGlobalHistorique(globalhistorique);

	}

	@Override
	@Transactional
	public List<GlobalHistorique> findAllGlobalHistoriques() {
		 return globalhistoriquedao.findAllGlobalHistoriques();
	}

	@Override
	@Transactional
	public void deleteGlobalHistorique(GlobalHistorique globalhistorique) {
		globalhistoriquedao.deleteGlobalHistorique(globalhistorique);

	}

	@Override
	@Transactional
	public GlobalHistorique getByid(Date jour) {		
		return globalhistoriquedao.getByid(jour);
	}

	public GlobalHistoriqueDao getGlobalhistoriquedao() {
		return globalhistoriquedao;
	}

	public void setGlobalhistoriquedao(GlobalHistoriqueDao globalhistoriquedao) {
		this.globalhistoriquedao = globalhistoriquedao;
	}
	

}
