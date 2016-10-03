package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.OperateurDao;
import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.Societe;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OperateurServiceImpl implements OperateurService,Serializable {
	/**
	 * 
	 */
	@Autowired
	private OperateurDao operateurDao;
	public OperateurDao getOperateurDao() {
		return operateurDao;
	}

	public void setOperateurDao(OperateurDao operateurDao) {
		this.operateurDao = operateurDao;
	}

	@Override
	@Transactional
	public void insertOperateur(Operateur u) {

		operateurDao.insertOperateur(u);
	}

	@Override
	@Transactional
	public void updateOperateur(Operateur u) {
		operateurDao.updateOperateur(u);
	}

	@Override
	@Transactional
	public List<Operateur> findAllOperateur() {

		return operateurDao.findAllOperateurs();
	}

	@Override
	@Transactional
	public void deleteOperateur(Operateur u) {

		operateurDao.deleteOperateur(u);
	}

	@Override
	@Transactional
	public Operateur getByid(int id) {
		return operateurDao.getByid(id);
	}

	@Override
	@Transactional
	public Operateur getByLogin(String login) {
		
		return operateurDao.getByLogin(login);
	}

	@Override
	@Transactional
	public List<OperateurRoles> getopRoles(Operateur op) {
		// TODO Auto-generated method stub
		return operateurDao.getopRoles(op);
	}

	@Override
	@Transactional
	public List<Affiliation> getopaffiliation(Operateur op) {
		// TODO Auto-generated method stub
		return operateurDao.getopaffiliation(op);
	}

	@Override
	@Transactional
	public List<OperateurRoles> getopRoles(Operateur op, String pos) {
		// TODO Auto-generated method stub
		return operateurDao.getopRoles(op,pos);
	}
	@Override
	@Transactional
	public Societe getCurrentSociete(Operateur op) {
		return operateurDao.getCurrentSociete(op);
	}
	
	@Override
	@Transactional
	public List<OperateurRoles> getopRoles0(Operateur op)
	{return operateurDao.getopRoles0(op);}
	
	}

	

