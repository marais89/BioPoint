package org.bio.service;

import java.util.List;

import org.bio.dao.ModeleRapportDao;
import org.bio.model.ModeleRapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModeleRapportServiceImpl implements ModeleRapportService {

	@Autowired
	private ModeleRapportDao modeleRapportDao;
	public ModeleRapportDao getModeleRapportDao() {
		return modeleRapportDao;
	}
	public void setModeleRapportDao(ModeleRapportDao modeleRapportDao) {
		this.modeleRapportDao = modeleRapportDao;
	}

	@Override
	@Transactional
	public void insertModeleRapport(ModeleRapport mr) {
	modeleRapportDao.insertModeleRapport(mr);	

	}

	@Override
	@Transactional
	public void updateModeleRapport(ModeleRapport mr) {
		modeleRapportDao.updateModeleRapport(mr);

	}

	@Override
	@Transactional
	public List<ModeleRapport> findAllModeleRapport() {
		return modeleRapportDao.findAllModeleRapport();
	}

	@Override
	@Transactional
	public void deleteModeleRapport(ModeleRapport mr) {
		modeleRapportDao.deleteModeleRapport(mr);

	}

	@Override
	@Transactional
	public ModeleRapport getByid(int id) {
		return modeleRapportDao.getByid(id);
	}

}
