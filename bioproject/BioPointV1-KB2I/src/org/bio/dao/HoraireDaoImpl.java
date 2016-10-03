package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Horaires;
import org.bio.model.HorairesId;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class HoraireDaoImpl implements HoraireDao, Serializable {
	@Autowired
private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertHoraire(Horaires horaire) {
	getSessionFactory().getCurrentSession().save(horaire);

	}

	@Override
	public void updateHoraire(Horaires horaire) {
		getSessionFactory().getCurrentSession().update(horaire);

	}

	@Override
	public List<Horaires> findAllHoraires() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Horaires").list();
	}

	@Override
	public void deleteHoraire(Horaires horaire) {
		getSessionFactory().getCurrentSession().delete(horaire);

	}

	@Override
	public Horaires getByid(HorairesId id) {
		
		return ((Horaires) getSessionFactory().getCurrentSession().get(Horaires.class,id));
		
	}

}
