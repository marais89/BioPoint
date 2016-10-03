package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Identification;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class IdentificationDaoImpl implements IdentificationDao, Serializable {
@Autowired
private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	@Override
	public void insertIdentification(Identification identification) {
		getSessionFactory().getCurrentSession().saveOrUpdate(identification);

	}

	@Override
	public void updateIdentification(Identification identification) {
		getSessionFactory().getCurrentSession().update(identification);

	}

	@Override
	public List<Identification> findAllIdentification() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Identification").list();
	}

	@Override
	public void deleteIdentification(Identification identification) {
		getSessionFactory().getCurrentSession().delete(identification);

	}

	@Override
	public Identification getByid(int id) {
		
		return (Identification) getSessionFactory().getCurrentSession().get(Identification.class, id);
	}

}
