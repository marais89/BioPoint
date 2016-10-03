package org.bio.dao;

import java.util.List;

import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.OperateurRolesId;

public interface OperateurRolesDao {
	public void insertOperateurRoles(OperateurRoles OperateurRoles);
	public void updateOperateurRoles(OperateurRoles OperateurRoles);
	public List<OperateurRoles> findAllOperateurRoless();
	public void deleteOperateurRoles(OperateurRoles OperateurRoles);
	public OperateurRoles getByid(OperateurRolesId  id);
	public void deleteRolesbyoperateur(Operateur op);
}
