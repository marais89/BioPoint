package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Document;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class DocumentDaoImpl implements DocumentDao,Serializable{
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertDocument(Document document) {
		getSessionFactory().getCurrentSession().save(document);
		
	}

	@Override
	public void updateDocument(Document document) {
		getSessionFactory().getCurrentSession().update(document);
		
	}

	@Override
	public List<Document> findAllDocuments() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Document").list();
	}

	@Override
	public void deleteDocument(Document document) {
		getSessionFactory().getCurrentSession().delete(document);
		
	}

	@Override
	public Document getByid(int id) {
		
		return (Document)getSessionFactory().getCurrentSession().get(DocumentDao.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
