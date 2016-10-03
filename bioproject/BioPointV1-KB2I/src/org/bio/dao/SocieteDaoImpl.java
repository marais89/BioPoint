package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Societe;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SocieteDaoImpl implements SocieteDao, Serializable {
@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	@Override
	public void insertSociete(Societe societe) {
		getSessionFactory().getCurrentSession().save(societe);

	}

	@Override
	public void updateSociete(Societe societe) {
		getSessionFactory().getCurrentSession().update(societe);

	}

	@Override
	public List<Societe> findAllSocietes() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Societe").list();
	}

	@Override
	public void deleteSociete(Societe societe) {
		getSessionFactory().getCurrentSession().delete(societe);

	}

	@Override
	public Societe getByid(int id) {
		
		return (Societe) getSessionFactory().getCurrentSession().get(Societe.class, id);
	}

}
