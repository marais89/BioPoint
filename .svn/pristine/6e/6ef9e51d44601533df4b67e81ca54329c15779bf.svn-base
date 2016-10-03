package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Parametre;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ParametreDaoImpl implements ParametreDao,Serializable{
@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertParametre(Parametre parametre) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().save(parametre);
	}

	@Override
	public void updateParametre(Parametre parametre) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().update(parametre);
	}

	@Override
	public List<Parametre> findAllParametres() {
		// TODO Auto-generated method stub
		return 		 getSessionFactory().getCurrentSession().createQuery("from Parametre").list();
	}

	@Override
	public void deleteParametre(Parametre parametre) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().delete(parametre);
	}

	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Parametre byId(String id) {
		// TODO Auto-generated method stub
		return (Parametre) getSessionFactory().getCurrentSession().createQuery("from Parametre where nomparam=:nom").setString("nom", id).uniqueResult();

}}
