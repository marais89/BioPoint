package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Calendrier;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CalendrierDaoImpl implements CalendrierDao,Serializable{
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertCalendrier(Calendrier calendrier) {
		getSessionFactory().getCurrentSession().save(calendrier);
		
	}

	@Override
	public void updateCalendrier(Calendrier calendrier) {
		getSessionFactory().getCurrentSession().update(calendrier);
		
	}

	@Override
	public List<Calendrier> findAllCalendriers() {
		return 	getSessionFactory().getCurrentSession().createQuery("from Calendrier").list();
	}

	@Override
	public void deleteCalendrier(Calendrier calendrier) {
		getSessionFactory().getCurrentSession().delete(calendrier);
		
	}

	@Override
	public Calendrier getByid(int id) {
		return (Calendrier)	getSessionFactory().getCurrentSession().get(Calendrier.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
