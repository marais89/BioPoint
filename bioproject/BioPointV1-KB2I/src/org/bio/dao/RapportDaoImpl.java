package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Rapport;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RapportDaoImpl implements RapportDao, Serializable{

	@Autowired
	private SessionFactory sessionFactory;


		public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public void insertRapport(Rapport rapport) {
		getSessionFactory().getCurrentSession().save(rapport);

	}

	@Override
	public void updateRapport(Rapport rapport) {
		getSessionFactory().getCurrentSession().update(rapport);

	}

	@Override
	public List<Rapport> findAllRapport() {
		return getSessionFactory().getCurrentSession().createQuery("from Rapport").list();
	}

	@Override
	public void deleteRapport(Rapport rapport) {
		getSessionFactory().getCurrentSession().delete(rapport);

	}

	@Override
	public Rapport getByid(int id) {
		return (Rapport) getSessionFactory().getCurrentSession().get(Rapport.class,id);
	}
	
	@Override
	public List<Rapport> findRapportPre(int x) {
		return getSessionFactory().getCurrentSession().createQuery("from Rapport as rap where rap.idrapportGroup=:i").setInteger("i", x).list();
	}

}
