package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.OperateurRolesDao;
import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.OperateurRolesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OperateurRolesServiceImpl implements OperateurRolesService,Serializable {
@Autowired
	private OperateurRolesDao operateurRolesDao;
	@Override
	@Transactional
	public void insertOperateurRoles(OperateurRoles OperateurRoles) {
		// TODO Auto-generated method stub
		operateurRolesDao.insertOperateurRoles(OperateurRoles);
	}

	@Override
	@Transactional
	public void updateOperateurRoles(OperateurRoles OperateurRoles) {
		// TODO Auto-generated method stub
		operateurRolesDao.updateOperateurRoles(OperateurRoles);
	}

	@Override
	@Transactional
	public List<OperateurRoles> findAllOperateurRoless() {
		// TODO Auto-generated method stub
		return operateurRolesDao.findAllOperateurRoless();
	}

	@Override
	@Transactional
	public void deleteOperateurRoles(OperateurRoles OperateurRoles) {
		// TODO Auto-generated method stub
		operateurRolesDao.deleteOperateurRoles(OperateurRoles);
	}

	@Override
	@Transactional
	public OperateurRoles getByid(OperateurRolesId id) {
		// TODO Auto-generated method stub
		return operateurRolesDao.getByid(id);
	}

	public OperateurRolesDao getOperateurRolesDao() {
		return operateurRolesDao;
	}

	public void setOperateurRolesDao(OperateurRolesDao operateurRolesDao) {
		this.operateurRolesDao = operateurRolesDao;
	}

	@Override
	@Transactional
	public void deleteRolesbyoperateur(Operateur op) {
		// TODO Auto-generated method stub
		operateurRolesDao.deleteRolesbyoperateur(op);
	}

}
