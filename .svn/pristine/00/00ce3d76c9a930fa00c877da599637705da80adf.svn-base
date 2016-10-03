package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Historique;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class HistoriqueDaoImpl implements HistoriqueDao,Serializable {
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertHistorique(Historique historique) {
		getSessionFactory().getCurrentSession().save(historique);
		
	}

	@Override
	public void updateHistorique(Historique historique) {
getSessionFactory().getCurrentSession().update(historique);
	}

	@Override
	public List<Historique> findAllHistoriques() {
		return getSessionFactory().getCurrentSession().createQuery("from Historique").list();
	}

	@Override
	public void deleteHistorique(Historique historique) {
	getSessionFactory().getCurrentSession().delete(historique);
		
	}

	@Override
	public Historique getByid(int id) {
		return (Historique)getSessionFactory().getCurrentSession().get(Historique.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
