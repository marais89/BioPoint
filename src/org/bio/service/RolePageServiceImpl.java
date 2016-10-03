package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.RolePageDao;
import org.bio.model.RolePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolePageServiceImpl implements RolePageService,Serializable {
@Autowired
	private RolePageDao rolePageDao;
	@Override
	@Transactional
	public void insertRolePage(RolePage RolePage) {
		rolePageDao.insertRolePage(RolePage);		
	}

	@Override
	@Transactional
	public void updateRolePage(RolePage RolePage) {
		// TODO Auto-generated method stub
		rolePageDao.updateRolePage(RolePage);
	}

	@Override
	@Transactional
	public List<RolePage> findAllRolePages() {
		// TODO Auto-generated method stub
		return rolePageDao.findAllRolePages();
	}

	@Override
	@Transactional
	public void deleteRolePage(RolePage RolePage) {
		// TODO Auto-generated method stub
		rolePageDao.deleteRolePage(RolePage);
	}

	@Override
	@Transactional
	public RolePage getByid(int id) {
		// TODO Auto-generated method stub
		return rolePageDao.getByid(id);
	}

	public RolePageDao getRolePageDao() {
		return rolePageDao;
	}

	public void setRolePageDao(RolePageDao rolePageDao) {
		this.rolePageDao = rolePageDao;
	}

}
