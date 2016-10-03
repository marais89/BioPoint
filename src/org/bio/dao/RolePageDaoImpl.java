package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Role;
import org.bio.model.RolePage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class RolePageDaoImpl implements RolePageDao,Serializable {
@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

@Override
public void insertRolePage(RolePage RolePage) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().save(RolePage);
}

@Override
public void updateRolePage(RolePage RolePage) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().update(RolePage);
}

@SuppressWarnings("unchecked")
@Override
public List<RolePage> findAllRolePages() {
	// TODO Auto-generated method stub
	return 	getSessionFactory().getCurrentSession().createQuery("from RolePage").list();

}

@Override
public void deleteRolePage(RolePage RolePage) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().delete(RolePage);
}

@Override
public RolePage getByid(int id) {
	// TODO Auto-generated method stub
	return 	(RolePage) getSessionFactory().getCurrentSession().get(RolePage.class,id);

}

	

}
