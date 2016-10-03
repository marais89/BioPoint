package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Motif;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MotifDaoImpl implements MotifDao, Serializable{
@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertMotif(Motif motif) {
	getSessionFactory().getCurrentSession().save(motif);
		
	}

	@Override
	public void updateMotif(Motif motif) {
		getSessionFactory().getCurrentSession().update(motif);
		
	}

	@Override
	public List<Motif> findAllMotifs() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Motif").list();
	}

	@Override
	public void deleteMotif(Motif motif) {
getSessionFactory().getCurrentSession().delete(motif);
		
	}

	@Override
	public Motif getByid(int id) {
	
		return (Motif)getSessionFactory().getCurrentSession().get(Motif.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
