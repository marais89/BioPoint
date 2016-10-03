package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.HoraireDao;
import org.bio.model.Horaires;
import org.bio.model.HorairesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class HoraireServiceImpl implements HoraireService, Serializable {
	@Autowired
private HoraireDao horaireDao;
	
	public HoraireDao getHoraireDao() {
		return horaireDao;
	}

	public void setHoraireDao(HoraireDao horaireDao) {
		this.horaireDao = horaireDao;
	}

	@Override
	@Transactional
	public void insertHoraire(Horaires horaire) {
		horaireDao.insertHoraire(horaire);

	}

	@Override
	@Transactional
	public void updateHoraire(Horaires horaire) {
	horaireDao.updateHoraire(horaire);

	}

	@Override
	@Transactional
	public List<Horaires> findAllHoraires() {
		
		return horaireDao.findAllHoraires();
	}

	@Override
	@Transactional
	public void deleteHoraire(Horaires horaire) {
		horaireDao.deleteHoraire(horaire);

	}

	@Override
	@Transactional
	public Horaires getByid(HorairesId id) {
		
		return horaireDao.getByid(id);
	}

}
