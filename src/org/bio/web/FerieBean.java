package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Ferie;
//import org.bio.model.GlobalHistorique;
import org.bio.model.Terminaux;
import org.bio.model.Trace;
import org.bio.service.FerieService;
import org.bio.service.TraceService;
import org.primefaces.context.RequestContext;
//import org.bio.service.GlobalHistoriqueService;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

@ManagedBean
@ViewScoped
public class FerieBean implements Serializable {
	
	@ManagedProperty(value=("#{ferieServiceImpl}"))
	private FerieService ferieService;
	//@ManagedProperty(value=("#{globalHistoriqueServiceImpl}"))
	//private GlobalHistoriqueService globalHistoriqueService;
	
	//private GlobalHistorique globalhistorique;
	private  Ferie ferie;
	private  Ferie ferieselectionne;
	private List<Ferie> listFerie;
	private List<Ferie> listFerieSelectionne;
	private List<Ferie> filtredlistFerie;
	private boolean flag;
	private int sizeliste;
	private List<String> listType;
	private int val;
	private boolean tt;
	@ManagedProperty("#{traceServiceImpl}")
	private TraceService traceService;

	
	
	@PostConstruct
	private void init()
	{
		this.val=1;
	//this.globalhistorique= new GlobalHistorique();
	//this.ferie=new Ferie();
	this.ferieselectionne=new Ferie();
	this.listFerie=new ArrayList<Ferie>();
	this.listFerieSelectionne= new ArrayList<Ferie>();
	this.listFerie=new ArrayList<Ferie>();
	this.listFerie= ferieService.findAllFeries();
	flag=true;
	this.listType=new ArrayList<String>();
	remplirString();
	
	
	}
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
		ferieselectionne = (Ferie) event.getObject();
	}	
	
	//fct execution de methode selon val
		public void selonexecute()
		{
			if(this.val==1)
			{ajouterFerie();}
			else
			{modifierFerie();}
		}
		//fct pour changer  l'etat de val
		public void changevalajout()
		{
			this.val=1;
			this.ferieselectionne=new Ferie();		
		}
		public void changevalmodif()
		{
			this.val=2;		
		}
	
	//fct remplir listType par les valeur 
		public void remplirString()
		{
			this.listType.add("exceptionnel");
			this.listType.add("annuel");
		}
	
	// fct d'activation ou de désactivation de la colonne de selection // 
			public void activate ()
			{
				setFlag(!this.flag);			
			}
			
			//fct supprimer un ferie de la liste listferieselectionner
			public void removeferie(Ferie ferie )
			{
				this.listFerieSelectionne.remove(ferie);
			}
					
			
			// fct ajout Ferie
			public void ajouterFerie(){
				try{
					//Date date=this.ferieselectionne.getDateDebut();
					//if(this.globalHistoriqueService.getByid(date)==null)
					//{
					//	this.globalhistorique= new GlobalHistorique(date, null, null);
					//}
					
					getFerieService().insertFerie(ferieselectionne);
					Trace trace = new Trace();
					 trace .setAction("Insertion Jour Ferie "+ferieselectionne.getDesignation());
					 getTraceService().insertTrace(trace);
					FacesMessage msg = new FacesMessage(" définition des Jours fériés avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			        RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
					}
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();
				}
			
			}
			
			//fct mise a jour Ferie
			public void modifierFerie()
			{
				try{
					getFerieService().updateFerie(ferieselectionne);
					Trace trace = new Trace();
					 trace .setAction("Modification Jour Ferie "+ferieselectionne.getDesignation());
					 getTraceService().insertTrace(trace);
					FacesMessage msg = new FacesMessage(" Modification des jours féries avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			        RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
				   }
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();}
			}
			
			//supprimer Ferie
			public void supprimFerie()
			{
				try{
					for(Ferie f:listFerieSelectionne)
					{
					ferieService.deleteFerie(f);
					Trace trace = new Trace();
					 trace .setAction("Suppression Jour Ferie "+f.getDesignation());
					 getTraceService().insertTrace(trace);
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
	public FerieService getFerieService() {
		return ferieService;
	}
	public void setFerieService(FerieService ferieService) {
		this.ferieService = ferieService;
	}
	public Ferie getFerie() {
		return ferie;
	}
	public void setFerie(Ferie ferie) {
		this.ferie = ferie;
	}
	public List<Ferie> getListFerie() {
		this.listFerie=new ArrayList<Ferie>();
		this.listFerie= ferieService.findAllFeries();
		return listFerie;
	}
	public void setListFerie(List<Ferie> listFerie) {
		this.listFerie = listFerie;
	}
	public List<Ferie> getListFerieSelectionne() {
		return listFerieSelectionne;
	}
	public void setListFerieSelectionne(List<Ferie> listFerieSelectionne) {
		this.listFerieSelectionne = listFerieSelectionne;
	}
	public List<Ferie> getFiltredlistFerie() {
		return filtredlistFerie;
	}
	public void setFiltredlistFerie(List<Ferie> filtredlistFerie) {
		this.filtredlistFerie = filtredlistFerie;
	}
	public Ferie getFerieselectionne() {
		return ferieselectionne;
	}
	public void setFerieselectionne(Ferie ferieselectionne) {
		this.ferieselectionne = ferieselectionne;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getSizeliste() {
		sizeliste = listFerieSelectionne.size();
		return sizeliste;
	}
	public void setSizeliste(int sizeliste) {
		this.sizeliste = sizeliste;
	}
	public List<String> getListType() {
		return listType;
	}
	public void setListType(List<String> listType) {
		this.listType = listType;
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
	/*public GlobalHistoriqueService getGlobalHistoriqueService() {
		return globalHistoriqueService;
	}
	public void setGlobalHistoriqueService(
			GlobalHistoriqueService globalHistoriqueService) {
		this.globalHistoriqueService = globalHistoriqueService;
	}
	public GlobalHistorique getGlobalhistorique() {
		return globalhistorique;
	}
	public void setGlobalhistorique(GlobalHistorique globalhistorique) {
		this.globalhistorique = globalhistorique;
	}	*/

	public TraceService getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
	}
}
