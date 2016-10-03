package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.HistoriqueCat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class HistoriqueCatDaoImpl implements HistoriqueCatDao,Serializable {
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertHistoriqueCat(HistoriqueCat HistoriqueCat) {
		getSessionFactory().getCurrentSession().save(HistoriqueCat);
		
	}

	@Override
	public void updateHistoriqueCat(HistoriqueCat HistoriqueCat) {
getSessionFactory().getCurrentSession().update(HistoriqueCat);
	}

	@Override
	public List<HistoriqueCat> findAllHistoriqueCats() {
		return getSessionFactory().getCurrentSession().createQuery("from HistoriqueCat").list();
	}

	@Override
	public void deleteHistoriqueCat(HistoriqueCat HistoriqueCat) {
	getSessionFactory().getCurrentSession().delete(HistoriqueCat);
		
	}

	@Override
	public HistoriqueCat getByid(int id) {
		return (HistoriqueCat)getSessionFactory().getCurrentSession().get(HistoriqueCat.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public HistoriqueCat getCurrentHistoriqueCat(int idper) {
		// TODO Auto-generated method stub
		return (HistoriqueCat) getSessionFactory().getCurrentSession().createQuery("from HistoriqueCat where idper =:personnel and NOW() between Du and AU")
				.setParameter("personnel", idper).uniqueResult();
	}

}
