package org.bio.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.bio.model.Affiliation;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AffiliationDaoImpl implements AffiliationDao, Serializable {
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertAffiliation(Affiliation affiliation) {
		getSessionFactory().getCurrentSession().save(affiliation);
		
	}

	@Override
	public void updateAffiliation(Affiliation affiliation) {
		getSessionFactory().getCurrentSession().update(affiliation);
		
	}

	@Override
	public List<Affiliation> findAllAffiliation() {
			return getSessionFactory().getCurrentSession().createQuery("from Affiliation").list();
	}

	@Override
	public void deleteAffiliation(Affiliation affiliation) {
	getSessionFactory().getCurrentSession().delete(affiliation);
	}

	@Override
	public Affiliation getByid(int id) {
		return (Affiliation)getSessionFactory().getCurrentSession().get(Affiliation.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Affiliation getRoot() {
		// TODO Auto-generated method stub
		return (Affiliation) sessionFactory.getCurrentSession().createQuery("from Affiliation where type = 'R'").list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Affiliation> getAffiliation(String type) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Affiliation where type =:type").setCacheable(true).setParameter("type", type).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAffiliationTypes() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("select DISTINCT af.description from Affiliation as af where type != 'R'").list();
	}

	@Override
	public List<Affiliation> listaffiliation(Affiliation op) {
		// TODO Auto-generated method stub
			return   getSessionFactory().getCurrentSession().createQuery("from Affiliation where affiliation=:af").setEntity("af", op).list();

	}
	
}