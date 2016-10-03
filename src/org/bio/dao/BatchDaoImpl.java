package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Batch;
import org.bio.model.BatchId;
import org.bio.model.Personnel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BatchDaoImpl implements BatchDao,Serializable {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void insertBatch(Batch Batch) {
		// TODO Auto-generated method stub
		
getSessionFactory().getCurrentSession().saveOrUpdate(Batch);
	}

	@Override
	public void updateBatch(Batch Batch) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().update(Batch);

	}

	@Override
	public List<Batch> findAllBatch() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Batch").list();
	}

	@Override
	public void deleteBatch(Batch Batch) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().delete(Batch);
	}

	@Override
	public Batch getByid(BatchId id) {
		// TODO Auto-generated method stub
		return (Batch) getSessionFactory().getCurrentSession().get(Batch.class, id);
	}

	@Override
	public List<Personnel> findAllPersonnels() {
		// TODO Auto-generated method stub
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Batch> findAllBatch(int etat) {
		// TODO Auto-generated method stub
	return getSessionFactory().getCurrentSession().createQuery("from Batch where etat!=3").list();

	}

}
