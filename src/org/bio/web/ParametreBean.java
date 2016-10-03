package org.bio.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Parametre;
import org.bio.service.ParametreService;

@ManagedBean
@SessionScoped
public class ParametreBean implements Serializable{
	@ManagedProperty(value="#{parametreServiceImpl}")
	private ParametreService parametreService;
	private List<Parametre> listparam;
@PostConstruct
private void init()
{
	listparam=parametreService.findAllParametres();
	System.out.println(listparam.size());
}
public void display()
{
	try{
	for(Parametre p:listparam)
	{
		getParametreService().updateParametre(p);

	}
	 FacesMessage msg = new FacesMessage("Parametre modifié avec succés");  
     FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	catch(Exception e)
	{
		 FacesMessage msg = new FacesMessage("erreur");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
	public List<Parametre> getListparam() {
		return listparam;
	}

	public void setListparam(List<Parametre> listparam) {
		this.listparam = listparam;
	}

	public ParametreService getParametreService() {
		return parametreService;
	}

	public void setParametreService(ParametreService parametreService) {
		this.parametreService = parametreService;
	}
}
