package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Rapport;
import org.bio.model.Regles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegleDaoImpl implements RegleDao, Serializable {

	@Autowired
	private SessionFactory sessionFactory;


		public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public void insertRegle(Regles regles) {
		getSessionFactory().getCurrentSession().save(regles);

	}

	@Override
	public void updateRegle(Regles regles) {
		getSessionFactory().getCurrentSession().update(regles);

	}

	@Override
	public List<Regles> findAllRegles() {
		return getSessionFactory().getCurrentSession().createQuery("from regles").list();
	}

	@Override
	public void deleteRegle(Regles regles) {
		getSessionFactory().getCurrentSession().delete(regles);

	}

	@Override
	public Regles getByid(int id) {
		return (Regles) getSessionFactory().getCurrentSession().get(Regles.class,id);
	}
	
	@Override
	public List<Regles> findWithIdRapport(Rapport rapport) {
		return getSessionFactory().getCurrentSession().createQuery("from Regles where rapport=:rapport").setEntity("rapport", rapport).list();
	}
	

}
