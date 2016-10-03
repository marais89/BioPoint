package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Rapport;
import org.bio.model.RelationRapport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RelationRapportDaoImpl implements RelationRapportDao, Serializable {

	@Autowired
	private SessionFactory sessionFactory;


		public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public void insertRelationRapport(RelationRapport r) {
		getSessionFactory().getCurrentSession().save(r);

	}

	@Override
	public void updateRelationRapport(RelationRapport r) {
		getSessionFactory().getCurrentSession().update(r);

	}

	@Override
	public List<RelationRapport> findAllRelationRapport() {
		return getSessionFactory().getCurrentSession().createQuery("from relationRapport").list();
	}

	@Override
	public void deleteRelationRapport(RelationRapport r) {
		getSessionFactory().getCurrentSession().delete(r);

	}

	@Override
	public RelationRapport getByid(int id) {
		return (RelationRapport) getSessionFactory().getCurrentSession().get(RelationRapport.class,id);
	}
	
	@Override
	public List<RelationRapport> findByRapport(Rapport rapport)
	{
		return   getSessionFactory().getCurrentSession().createQuery("from RelationRapport as r where r.rapport=:rapport").setEntity("rapport",rapport).list();
	}

}
