package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Page;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class PageDaoImpl implements PageDao,Serializable {
@Autowired	
private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	@Override
	public void insertPage(Page page) {
		getSessionFactory().getCurrentSession().save(page);

	}

	@Override
	public void updatePage(Page page) {
		getSessionFactory().getCurrentSession().update(page);

	}

	@Override
	public List<Page> findAllPage() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Page").list();
	}

	@Override
	public void deletePage(Page page) {
		getSessionFactory().getCurrentSession().delete(page);

	}

	@Override
	public Page getByid(int id) {
	
		return (Page) getSessionFactory().getCurrentSession().get(Page.class, id);
	}

}
