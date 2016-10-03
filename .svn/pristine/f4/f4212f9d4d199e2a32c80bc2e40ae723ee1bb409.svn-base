package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.SyntheseDao;
import org.bio.model.Synthese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SyntheseServiceImpl implements SyntheseService, Serializable {
@Autowired
private SyntheseDao syntheseDao;
	@Override
	@Transactional
	public void insertSynthese(Synthese synthese) {
		syntheseDao.insertSynthese(synthese);
		
	}

	@Override
	@Transactional
	public List<Synthese> findAllSynthese() {
		return  syntheseDao.findAllSynthese();
	}

	@Override
	@Transactional
	public Synthese getByid(int id) {

		return syntheseDao.getByid(id);
	}

}
