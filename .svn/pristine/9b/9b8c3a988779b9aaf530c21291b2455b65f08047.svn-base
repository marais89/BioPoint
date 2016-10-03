package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.OperateurRolesId;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class OperateurRolesDaoImpl implements OperateurRolesDao,Serializable {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void insertOperateurRoles(OperateurRoles OperateurRoles) {
		getSessionFactory().getCurrentSession().save(OperateurRoles);
		
	}

	@Override
	public void updateOperateurRoles(OperateurRoles OperateurRoles) {
		getSessionFactory().getCurrentSession().saveOrUpdate(OperateurRoles);
		
	}

	@Override
	public List<OperateurRoles> findAllOperateurRoless() {
		return getSessionFactory().getCurrentSession().createQuery("from OperateurRoles").list();
	
	}

	@Override
	public void deleteOperateurRoles(OperateurRoles OperateurRoles) {
		getSessionFactory().getCurrentSession().delete(OperateurRoles);
		
	}

	@Override
	public OperateurRoles getByid(OperateurRolesId id) {
		return (OperateurRoles)	getSessionFactory().getCurrentSession().get(OperateurRoles.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void deleteRolesbyoperateur(Operateur op) {
		// TODO Auto-generated method stub*
		getSessionFactory().getCurrentSession().createQuery("delete From OperateurRoles where id.idop=:op").setInteger("op", op.getIdop());
		
	}

}