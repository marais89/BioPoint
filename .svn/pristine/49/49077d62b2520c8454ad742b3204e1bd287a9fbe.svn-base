package org.bio.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.bio.dao.RelationRapportDao;
import org.bio.model.Rapport;
import org.bio.model.RelationRapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationRapportServiceImpl implements RelationRapportService, Serializable {
	
	@Autowired
	private RelationRapportDao relationRapportDao;
	
	public RelationRapportDao getRelationRapportDao() {
		return relationRapportDao;
	}
	public void setRelationRapportDao(RelationRapportDao relationRapportDao) {
		this.relationRapportDao = relationRapportDao;
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void insertRelationRapport(RelationRapport rr) {
		relationRapportDao.insertRelationRapport(rr);

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void updateRelationRapport(RelationRapport rr) {
		relationRapportDao.updateRelationRapport(rr);

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public List<RelationRapport> findAllRelationRapport() {
		
		return relationRapportDao.findAllRelationRapport();
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void deleteRelationRapport(RelationRapport rr) {
		relationRapportDao.deleteRelationRapport(rr);

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public org.bio.model.RelationRapport getByid(int id) {
		return relationRapportDao.getByid(id);
	}
	
	@Override
	@org.springframework.transaction.annotation.Transactional
	public List<RelationRapport> findByRapport(Rapport rapport)
	{
		return relationRapportDao.findByRapport(rapport);
	}

}
