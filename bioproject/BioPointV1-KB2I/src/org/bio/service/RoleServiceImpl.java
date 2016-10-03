package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.RoleDao;
import org.bio.model.OperateurRoles;
import org.bio.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RoleServiceImpl implements RoleService, Serializable {
@Autowired
private RoleDao roleDao;
	@Override
	@Transactional
	public void insertRole(Role role) {
		System.out.println("avant insertRole service");
		roleDao.insertRole(role);
	}

	@Override
	@Transactional
	public void updateRole(Role role) {
		roleDao.updateRole(role);

	}

	@Override
	@Transactional
	public List<Role> findAllRole() {

		return roleDao.findAllRoles();
	}

	@Override
	@Transactional
	public void deleteRole(Role role) {
		roleDao.deleteRole(role);

	}

	@Override
	@Transactional
	public Role getByid(int id) {

		return roleDao.getByid(id);
	}

	@Override
	@Transactional
	public List<String> findRebrique() {
		// TODO Auto-generated method stub
		return roleDao.findRebrique();
	}

	@Override
	@Transactional
	public List<Role> getByType(String type) {
		// TODO Auto-generated method stub
		return roleDao.getByType(type);
	}

	@Override
	@Transactional
	public List<Integer> findRolesbytype(int idop, String type) {
		// TODO Auto-generated method stub
		return roleDao.findRolesbytype(idop, type);
	}

}
