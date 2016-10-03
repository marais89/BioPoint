package org.bio.dao;

import java.util.List;

import org.bio.model.RolePage;

public interface RolePageDao {
	public void insertRolePage(RolePage RolePage);
	public void updateRolePage(RolePage RolePage);
	public List<RolePage> findAllRolePages();
	public void deleteRolePage(RolePage RolePage);
	public RolePage getByid(int  id);
}
