package org.bio.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.model.Ferie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class FerieDaoImpl implements FerieDao,Serializable{
	@Autowired
private SessionFactory sessionFactory;

	@Override
	public void insertFerie(Ferie ferie) {
		getSessionFactory().getCurrentSession().save(ferie);
		
	}

	@Override
	public void updateFerie(Ferie ferie) {
		getSessionFactory().getCurrentSession().update(ferie);
		
	}

	@Override
	public List<Ferie> findAllFeries() {
		return getSessionFactory().getCurrentSession().createQuery("from Ferie").list();
	
	}

	@Override
	public void deleteFerie(Ferie ferie) {
		getSessionFactory().getCurrentSession().delete(ferie);
		
	}

	@Override
	public Ferie getByid(int id) {
		return (Ferie)	getSessionFactory().getCurrentSession().get(Ferie.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean isFerie(Date d) {
		// TODO Auto-generated method stub
		Ferie f= (Ferie) getSessionFactory().getCurrentSession().createQuery("from Ferie where :jour between dateDebut and DateFin").setDate("jour", d).uniqueResult();
		if(f==null)
			return false;
		else
		return true;
	}

}
