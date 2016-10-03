package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Role;
import org.bio.model.OperateurRoles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class RoleDaoImpl implements RoleDao,Serializable{
	@Autowired 
private SessionFactory sessionFactory;
	
	
	@Override
	public void insertRole(Role role) {
		System.out.println("avant insertRole dao");
		getSessionFactory().getCurrentSession().save(role);
		System.out.println("aprés insertRole dao");

	}

	@Override
	public void updateRole(Role role) {
getSessionFactory().getCurrentSession().update(role);

	}

	@Override
	public List<Role> findAllRoles() {
		return getSessionFactory().getCurrentSession().createQuery("from Role order by type").list();
	}

	@Override
	public void deleteRole(Role role) {
getSessionFactory().getCurrentSession().delete(role);

	}

	@Override
	public Role getByid(int id) {

		return (Role)getSessionFactory().getCurrentSession().get(Role.class,id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findRebrique() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("select DISTINCT type from Role").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getByType(String type) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Role where type = :type").setParameter("type", type).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findRolesbytype(int idop, String type) {
		// TODO Auto-generated method stub
		return  getSessionFactory().getCurrentSession().createQuery("select r.idrol from Operateur as o,Role as r, OperateurRoles as op where op.operateur.idop=o.idop and r.idrol = op.role.idrol"
				+ " and o.idop= :idop and r.type= :type").setInteger("idop", Integer.valueOf(idop)).setParameter("type", type).list();
	}

}
