package org.bio.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.model.Ferie;
import org.bio.model.GlobalHistorique;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GlobalHistoriqueDaoImpl implements GlobalHistoriqueDao,Serializable {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void insertGlobalHistorique(GlobalHistorique globalhistorique) {
		getSessionFactory().getCurrentSession().save(globalhistorique);

	}

	@Override
	public void updateGlobalHistorique(GlobalHistorique globalhistorique) {
		getSessionFactory().getCurrentSession().update(globalhistorique);

	}

	@Override
	public List<GlobalHistorique> findAllGlobalHistoriques() {
		return getSessionFactory().getCurrentSession().createQuery("from GlobalHistorique").list();
	}

	@Override
	public void deleteGlobalHistorique(GlobalHistorique globalhistorique) {
		getSessionFactory().getCurrentSession().delete(globalhistorique);

	}

	@Override
	public GlobalHistorique getByid(Date jour) {
		return (GlobalHistorique)	getSessionFactory().getCurrentSession().get(GlobalHistorique.class, jour);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
