package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Trace;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class TraceDaoImpl implements TraceDao, Serializable {
@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	@Override
	public void insertTrace(Trace trace) {
	getSessionFactory().getCurrentSession().save(trace);

	}

	@Override
	public void updateTrace(Trace trace) {
		getSessionFactory().getCurrentSession().update(trace);

	}

	@Override
	public List<Trace> findAllTraces() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Trace order by dateHeure desc").list();
	}

	@Override
	public void deleteTrace(Trace trace) {
		getSessionFactory().getCurrentSession().delete(trace);
	}

	@Override
	public Trace getByid(int id) {
	
		return (Trace) getSessionFactory().getCurrentSession().get(Trace.class, id);
	}

}
