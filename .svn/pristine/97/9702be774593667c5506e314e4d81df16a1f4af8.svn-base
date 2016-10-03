package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.SbiOperateurAffiliation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SbiOperateurAffiliationDaoImpl implements SbiOperateurAffiliationDao,Serializable{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertSbiOperateurAffiliation(
			SbiOperateurAffiliation SbiOperateurAffiliation) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(SbiOperateurAffiliation);
	}

	@Override
	public void updateSbiOperateurAffiliation(
			SbiOperateurAffiliation SbiOperateurAffiliation) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(SbiOperateurAffiliation);
	}

	@Override
	public List<SbiOperateurAffiliation> findAllSbiOperateurAffiliations() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from SbiOperateurAffiliation").list();
	}

	@Override
	public void deleteSbiOperateurAffiliation(
			SbiOperateurAffiliation SbiOperateurAffiliation) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(SbiOperateurAffiliation);
	}

	@Override
	public SbiOperateurAffiliation getByid(int id) {
		// TODO Auto-generated method stub
		return (SbiOperateurAffiliation) sessionFactory.getCurrentSession().get(SbiOperateurAffiliation.class, id);
	}

	@Override
	public void deletebyOperateur(int id) {
		// TODO Auto-generated method stub
	sessionFactory.getCurrentSession().createQuery("delete from SbiOperateurAffiliation where idop=:op").setInteger("op", id);

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
