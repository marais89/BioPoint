package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.Societe;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperateurDaoImpl implements OperateurDao,Serializable{

	
	@Autowired 
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertOperateur(Operateur u) {

		getSessionFactory().getCurrentSession().save(u);
	}

	@Override
	public void updateOperateur(Operateur u) {

		getSessionFactory().getCurrentSession().saveOrUpdate(u);	
	}

	@Override
	public List<Operateur> findAllOperateurs() {
	
		return 	getSessionFactory().getCurrentSession().createQuery("from Operateur").list();
	}

	@Override
	public void deleteOperateur(Operateur u) {

		getSessionFactory().getCurrentSession().delete(u);
	}

	@Override
	public Operateur getByid(int id) {

		return (Operateur)getSessionFactory().getCurrentSession().get(Operateur.class, id);
	}

	@Override
	public Operateur getByLogin(String login) {
		Query query=getSessionFactory().getCurrentSession().createQuery("from Operateur where login =:login").setParameter("login", login);
		return (Operateur)query.list().get(0);
		
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperateurRoles> getopRoles(Operateur op,String pos) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from OperateurRoles  where operateur=:op and r=:right and role.position=:pos")
				.setBoolean("right", true).setString("pos", pos).setEntity("op", op).list();
	}

	@Override
	public List<Affiliation> getopaffiliation(Operateur op) {
		// TODO Auto-generated method stub
	return getSessionFactory().getCurrentSession().createQuery("from Affiliation as af where :id in (select idop from af.operateurs)")
			.setInteger("id", op.getIdop()).list();

	}

	@Override
	public List<OperateurRoles> getopRoles(Operateur op) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from OperateurRoles  where operateur=:op and r=1")
				.setEntity("op", op).list();
	}
	
	@Override
	public List<OperateurRoles> getopRoles0(Operateur op) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from OperateurRoles  where operateur=:op and r=0")
				.setEntity("op", op).list();
	}
	
	

	@Override
	public Societe getCurrentSociete(Operateur op) {
		// TODO Auto-generated method stub
		return (Societe) getSessionFactory().getCurrentSession().createQuery("select af.societe from Affiliation as af where af.type='So' and :id in (select id from af.operateurs)")
				.setInteger("id", op.getIdop()).uniqueResult();
	}

}
