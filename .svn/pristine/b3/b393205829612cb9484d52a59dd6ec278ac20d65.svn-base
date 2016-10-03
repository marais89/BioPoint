package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.IdentificationDao;
import org.bio.model.Identification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class IdentificationServiceImpl implements IdentificationService, Serializable {
@Autowired
private IdentificationDao identificationDao;

	public IdentificationDao getIdentificationDao() {
	return identificationDao;
}

public void setIdentificationDao(IdentificationDao identificationDao) {
	this.identificationDao = identificationDao;
}

	@Override
	@Transactional
	public void insertIdentification(Identification identification) {
		identificationDao.insertIdentification(identification);

	}

	@Override
	@Transactional
	public void updateIdentification(Identification identification) {
		identificationDao.updateIdentification(identification);

	}

	@Override
	@Transactional
	public List<Identification> findAllIdentifications() {
		
		return identificationDao.findAllIdentification();
	}

	@Override
	@Transactional
	public void deleteIdentification(Identification identification) {
		identificationDao.deleteIdentification(identification);

	}

	@Override
	@Transactional
	public Identification getByid(int id) {
		
		return identificationDao.getByid(id);
	}

}
