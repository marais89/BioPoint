package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.PersonnelTerminal;
import org.bio.model.PersonnelTerminal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class PersonnelTerminalDaoImpl implements PersonnelTerminalDao, Serializable {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	
	public void insertPersonnelTerminal(PersonnelTerminal personnelTerminal){
getSessionFactory().getCurrentSession().save(personnelTerminal);

	}

	@Override
	
	public void updatePersonnelTerminal(PersonnelTerminal personnelTerminal){
		getSessionFactory().getCurrentSession().update(personnelTerminal);

	}

	@Override
	
	public List<PersonnelTerminal> findAllPersonnelTerminal() {
		
		return getSessionFactory().getCurrentSession().createQuery("from PersonnelTerminal").list();
	}



	@Override
	
	public PersonnelTerminal getByid(int id) {

		return (PersonnelTerminal) getSessionFactory().getCurrentSession().get(PersonnelTerminal.class, id);
	}

	@Override
	
	public void deletePersonnelTerminal(PersonnelTerminal personnelTerminal) {
		
		getSessionFactory().getCurrentSession().delete(personnelTerminal);
	}

}
