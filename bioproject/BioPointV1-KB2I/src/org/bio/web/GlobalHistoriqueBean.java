/*package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.GlobalHistorique;
import org.bio.model.Terminaux;
import org.bio.service.CalendrierService;
import org.bio.service.GlobalHistoriqueService;
import org.bio.service.GlobalHistoriqueService;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;


@ManagedBean
@ViewScoped
public class GlobalHistoriqueBean  implements Serializable {
	
	@ManagedProperty(value=("#{GlobalHistoriqueServiceImpl}"))
	private GlobalHistoriqueService GlobalHistoriqueService;
	@ManagedProperty(value=("#{calendrierServiceImpl}"))
	private CalendrierService calendrierService;
	@ManagedProperty(value=("#{globalHistoriqueServiceImpl}"))
	private GlobalHistoriqueService globalHistoriqueService;
	
	private  GlobalHistorique globalhistorique;
	private  GlobalHistorique globalhistoriqueselectionne;
	private List<GlobalHistorique> listGlobalHistorique;
	private List<GlobalHistorique> listGlobalHistoriqueSelectionne;
	private List<GlobalHistorique> filtredlistGlobalHistorique;
	private boolean flag;
	private int sizeliste;
	private int val;
	private boolean tt;

	
	
	@PostConstruct
	private void init()
	{
	this.globalhistorique=new GlobalHistorique();
	this.globalhistoriqueselectionne=new GlobalHistorique();
	this.listGlobalHistorique=new ArrayList<GlobalHistorique>();
	this.listGlobalHistoriqueSelectionne= new ArrayList<GlobalHistorique>();
	this.listGlobalHistorique=new ArrayList<GlobalHistorique>();
	this.listGlobalHistorique= GlobalHistoriqueService.findAllGlobalHistoriques();
	flag=true;	
	}
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
			globalhistoriqueselectionne = (GlobalHistorique) event.getObject();
	}		
	//fct execution de methode selon val
		public void selonexecute()
		{
			if(this.val==1)
			{ajouterGlobalHistorique();}
			else
			{modifierGlobalHistorique();}
		}
		//fct pour changer  l'etat de val
		public void changevalajout()
		{
			this.val=1;
			this.globalhistoriqueselectionne=new GlobalHistorique();		
		}
		public void changevalmodif()
		{
			this.val=2;		
		}
	
	
	// fct d'activation ou de désactivation de la colonne de selection // 
			public void activate ()
			{
				setFlag(!this.flag);			
			}
			
			//fct supprimer un GlobalHistorique de la liste listGlobalHistoriqueselectionner
			public void removeGlobalHistorique(GlobalHistorique GlobalHistorique )
			{
				this.listGlobalHistoriqueSelectionne.remove(GlobalHistorique);
			}
					
			
			// fct ajout GlobalHistorique
			public void ajouterGlobalHistorique(){
				try{
					getGlobalHistoriqueService().insertGlobalHistorique(globalhistoriqueselectionne);
					FacesMessage msg = new FacesMessage(" définition des Jours fériés avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();
				}
			
			}
			
			//fct mise a jour GlobalHistorique
			public void modifierGlobalHistorique()
			{
				try{
					getGlobalHistoriqueService().updateGlobalHistorique(globalhistoriqueselectionne);
					FacesMessage msg = new FacesMessage(" Modification des jours féries avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				   }
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();}
			}
			
			//supprimer GlobalHistorique
			public void supprimGlobalHistorique()
			{
				try{
					for(GlobalHistorique f:listGlobalHistoriqueSelectionne)
					{
					GlobalHistoriqueService.deleteGlobalHistorique(f);
					}
					FacesMessage msg = new FacesMessage(" element(s) supprimé(s) avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				   }	
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();}
			}
	
	
	//GETTERS AND SETTERS
	public GlobalHistoriqueService getGlobalHistoriqueService() {
		return GlobalHistoriqueService;
	}
	public void setGlobalHistoriqueService(GlobalHistoriqueService GlobalHistoriqueService) {
		this.GlobalHistoriqueService = GlobalHistoriqueService;
	}
	public GlobalHistorique getGlobalHistorique() {
		return globalhistorique;
	}
	public void setGlobalHistorique(GlobalHistorique globalhistorique) {
		this.globalhistorique = globalhistorique;
	}
	public List<GlobalHistorique> getListGlobalHistorique() {
		this.listGlobalHistorique=new ArrayList<GlobalHistorique>();
		this.listGlobalHistorique= GlobalHistoriqueService.findAllGlobalHistoriques();
		return listGlobalHistorique;
	}
	public void setListGlobalHistorique(List<GlobalHistorique> listGlobalHistorique) {
		this.listGlobalHistorique = listGlobalHistorique;
	}
	public List<GlobalHistorique> getListGlobalHistoriqueSelectionne() {
		return listGlobalHistoriqueSelectionne;
	}
	public void setListGlobalHistoriqueSelectionne(List<GlobalHistorique> listGlobalHistoriqueSelectionne) {
		this.listGlobalHistoriqueSelectionne = listGlobalHistoriqueSelectionne;
	}
	public List<GlobalHistorique> getFiltredlistGlobalHistorique() {
		return filtredlistGlobalHistorique;
	}
	public void setFiltredlistGlobalHistorique(List<GlobalHistorique> filtredlistGlobalHistorique) {
		this.filtredlistGlobalHistorique = filtredlistGlobalHistorique;
	}
	public GlobalHistorique getglobalhistoriqueselectionne() {
		return globalhistoriqueselectionne;
	}
	public void setGlobalHistoriqueselectionne(GlobalHistorique globalhistoriqueselectionne) {
		this.globalhistoriqueselectionne = globalhistoriqueselectionne;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getSizeliste() {
		sizeliste = listGlobalHistoriqueSelectionne.size();
		return sizeliste;
	}
	public void setSizeliste(int sizeliste) {
		this.sizeliste = sizeliste;
	}
	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public boolean getTt() {
		return tt;
	}

	public void setTt(boolean tt) {
		this.tt = tt;
	}	
	
}*/
