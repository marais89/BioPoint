package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.ModeleRapport;
import org.bio.model.Rapport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ModeleRapportDaoImpl implements ModeleRapportDao, Serializable {

	@Autowired
	private SessionFactory sessionFactory;


		public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertModeleRapport(ModeleRapport mr) {
		getSessionFactory().getCurrentSession().save(mr);

	}

	@Override
	public void updateModeleRapport(ModeleRapport mr) {
		getSessionFactory().getCurrentSession().update(mr);

	}

	@Override
	public List<ModeleRapport> findAllModeleRapport() {
		return getSessionFactory().getCurrentSession().createQuery("from ModeleRapport").list();
	}

	@Override
	public void deleteModeleRapport(ModeleRapport mr) {
		getSessionFactory().getCurrentSession().delete(mr);

	}

	@Override
	public ModeleRapport getByid(int id) {
		return (ModeleRapport) getSessionFactory().getCurrentSession().get(ModeleRapport.class,id);
	}

}
