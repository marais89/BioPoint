package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Conge;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CongeDaoImpl implements CongeDao,Serializable{
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertConge(Conge conge) {
		getSessionFactory().getCurrentSession().save(conge);
		
	}

	@Override
	public void updateConge(Conge conge) {
		getSessionFactory().getCurrentSession().update(conge);
		
	}

	@Override
	public List<Conge> findAllConges() {
		return getSessionFactory().getCurrentSession().createQuery("from Conge").list();
	}

	@Override
	public void deleteConge(Conge conge) {
		getSessionFactory().getCurrentSession().delete(conge);
		
	}

	@Override
	public Conge getByid(int id) {
		return (Conge) getSessionFactory().getCurrentSession().get(Conge.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
