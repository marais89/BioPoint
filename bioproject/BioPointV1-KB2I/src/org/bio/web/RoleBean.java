package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Personnel;
import org.bio.model.Role;
import org.bio.model.Trace;
import org.bio.service.RoleService;
import org.bio.service.TraceService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;

@ManagedBean
@ViewScoped
public class RoleBean implements Serializable {

	private Role role;
	@ManagedProperty(value=("#{roleServiceImpl}"))
	private RoleService roleService;
	@ManagedProperty(value = ("#{traceServiceImpl}"))
	private TraceService traceService;
	private List<Role> listRole;
	private List<Role> selectedRoles;
	private List<Role> filtredRoles;
	private Role selectedRole;
	private int sizelist;
	private boolean action=true;
	private String msg;
@PostConstruct	
private void init()
{selectedRoles = new ArrayList<Role>();
	role = new Role();
	sizelist= 0 ;
}
public void switchcreate()
{
	System.out.println("reaction d'un role");
	role = new Role();
	action =true;
	msg="Création d'un Role";
}
public void switchUpdate()
{
	System.out.println("modification d'un role");
	action =false;
	msg="Modification du Role :"+role.getRoleName();
	//key = "Modification d'un personnel";

}
public void affect(SelectEvent event)
{
	role = (Role) event.getObject();
}
public void affect2(UnselectEvent event)
{
}
public void insertRole()
{
	if(action)
	{
	try {
		System.out.println("avant avant avant");
		role.setDateCreation(new Date());
		System.out.println("avant insertRole web");
		getRoleService().insertRole(role);

		FacesMessage msg = new FacesMessage("Role Crée avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		selectedRoles.clear();
		RequestContext.getCurrentInstance().update("form:messages");
		Trace trace = new Trace();
		 trace.setObjet("Role");
		 trace .setAction("Insertion Role "+role.getRoleName());
		 getTraceService().insertTrace(trace);
	} catch (DataAccessException e) {
		FacesMessage msg = new FacesMessage("Role Crée avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update("form:messages");

	}
	}
	else{
		try {
			System.out.println("select2");

			getRoleService().updateRole(role);
			Trace trace = new Trace();
			 trace .setAction("Modification Role "+role.getRoleName());
			 trace.setObjet("Role");
			 getTraceService().insertTrace(trace);
					FacesMessage msg = new FacesMessage("Role modifié avec succés");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					selectedRoles.clear();
					RequestContext.getCurrentInstance().update("form:messages");
				} catch (DataAccessException e) {
					FacesMessage msg = new FacesMessage("erreur");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					RequestContext.getCurrentInstance().update("form:messages");

				}	
	}
}

public void deleteRole()
{
	try {
		for(Role r:selectedRoles)
getRoleService().deleteRole(r);

		FacesMessage msg = new FacesMessage("Role supprimé avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Trace trace = new Trace();
		 trace .setAction("Suppression Role "+role.getRoleName());
		 trace.setObjet("Role");

		 getTraceService().insertTrace(trace);
	} catch (DataAccessException e) {
		FacesMessage msg = new FacesMessage("Erreur");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println(e.getMessage());
	}
}
public String code()
{
	return "<security:intercept-url pattern="+role.getUrl()+"access=hasRole('"+role.getNom()+"') />"; 
}
public void removerole(Role r)
{
	selectedRoles.remove(r);
}
public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public RoleService getRoleService() {
	return roleService;
}

public void setRoleService(RoleService roleService) {
	this.roleService = roleService;
}

public List<Role> getListRole() {
	listRole = new ArrayList<Role>();
	listRole.addAll(getRoleService().findAllRole());
	return listRole;
}

public void setListRole(List<Role> listRole) {
	this.listRole = listRole;
}
public List<Role> getSelectedRoles() {
	return selectedRoles;
}
public void setSelectedRoles(List<Role> selectedRoles) {
	this.selectedRoles = selectedRoles;
}
public List<Role> getFiltredRoles() {
	return filtredRoles;
}
public void setFiltredRoles(List<Role> filtredRoles) {
	this.filtredRoles = filtredRoles;
}
public Role getSelectedRole() {
	return selectedRole;
}
public void setSelectedRole(Role selectedRole) {
	this.selectedRole = selectedRole;
}
public int getSizelist() {
	sizelist = selectedRoles.size();
	return sizelist;
}
public void setSizelist(int sizelist) {
	this.sizelist = sizelist;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public TraceService getTraceService() {
	return traceService;
}
public void setTraceService(TraceService traceService) {
	this.traceService = traceService;
}


}
