package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Synthese;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SyntheseDaoImpl implements SyntheseDao,Serializable {
	@Autowired
private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertSynthese(Synthese synthese) {
		getSessionFactory().getCurrentSession().save(synthese);
		
	}

	@Override
	public List<Synthese> findAllSynthese() {
		return getSessionFactory().getCurrentSession().createQuery("from Synthese").list();
	}

	@Override
	public Synthese getByid(int id) {
		return (Synthese)getSessionFactory().getCurrentSession().get(Synthese.class, id);
	}

}
