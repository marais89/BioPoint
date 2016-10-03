package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Categorie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CategorieDaoImpl implements CategorieDao, Serializable {
@Autowired	
private SessionFactory sessionFactory;

public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

@Override
public void insertCategorie(Categorie categorie){
	getSessionFactory().getCurrentSession().save(categorie);
	
}
@Override
public void updateCategorie(Categorie categorie) {
getSessionFactory().getCurrentSession().update(categorie);

	}
@Override
public List<Categorie> findAllCategories() {
	return getSessionFactory().getCurrentSession().createQuery("from Categorie").list();
	
	}
@Override
public void deleteCategorie(Categorie categorie) {
getSessionFactory().getCurrentSession().delete(categorie);

	}
@Override
public Categorie getByid(int id) {
	
	return(Categorie) getSessionFactory().getCurrentSession().get(Categorie.class, id);
}




}
