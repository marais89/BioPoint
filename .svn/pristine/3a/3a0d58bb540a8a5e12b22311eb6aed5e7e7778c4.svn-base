package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Ferie;
import org.bio.model.Motif;
import org.bio.service.FerieService;
import org.bio.service.MotifService;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;






@ManagedBean
@ViewScoped
public class MotifBean  implements Serializable {
	
	@ManagedProperty(value=("#{motifServiceImpl}"))
	private MotifService motifService;
	
	private  Motif motif;
	private  Motif motifselectionne;
	private List<Motif> listMotif;
	private List<Motif> listMotifSelectionne;
	private List<Motif> filtredlistMotif;
	private boolean flag;
	private int sizeliste;
	private int val;
	private boolean tt;

	
	
	@PostConstruct
	private void init()
	{
		this.val=1;
	this.motif=new Motif();
	this.motifselectionne=new Motif();
	this.listMotif=new ArrayList<Motif>();
	this.listMotifSelectionne= new ArrayList<Motif>();
	this.listMotif=new ArrayList<Motif>();
	this.listMotif= motifService.findAllMotifs();
	flag=true;
	
	}
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
		motifselectionne = (Motif) event.getObject();
	}	
	
	//fct execution de methode selon val
		public void selonexecute()
		{
			if(this.val==1)
			{ajouterMotif();}
			else
			{modifierMotif();}
		}
		//fct pour changer  l'etat de val
		public void changevalajout()
		{
			this.val=1;
			this.motifselectionne=new Motif();		
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
			
			//fct supprimer un ferie de la liste listferieselectionner
			public void removemotif(Motif motif )
			{
				this.listMotifSelectionne.remove(motif);
			}
					
			
			// fct ajout Ferie
			public void ajouterMotif(){
				try{
					getMotifService().insertMotif(motifselectionne);
					FacesMessage msg = new FacesMessage(" motif ajouté avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();
				}
			
			}
			
			//fct mise a jour Ferie
			public void modifierMotif()
			{
				try{
					getMotifService().updateMotif(motifselectionne);
					FacesMessage msg = new FacesMessage(" Motif modifier avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				   }
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();}
			}
			
			//supprimer Ferie
			public void supprimMotif()
			{
				try{
					for(Motif m:listMotifSelectionne)
					{
					motifService.deleteMotif(m);
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
			public MotifService getMotifService() {
				return motifService;
			}
			public void setMotifService(MotifService motifService) {
				this.motifService = motifService;
			}
			public Motif getMotif() {
				return motif;
			}
			public void setMotif(Motif motif) {
				this.motif = motif;
			}
			public Motif getMotifselectionne() {
				return motifselectionne;
			}
			public void setMotifselectionne(Motif motifselectionne) {
				this.motifselectionne = motifselectionne;
			}
			public List<Motif> getListMotif() {
				this.listMotif=new ArrayList<Motif>();
				this.listMotif= motifService.findAllMotifs();
				return listMotif;
			}
			public void setListMotif(List<Motif> listMotif) {
				this.listMotif = listMotif;
			}
			public List<Motif> getListMotifSelectionne() {
				return listMotifSelectionne;
			}
			public void setListMotifSelectionne(List<Motif> listMotifSelectionne) {
				this.listMotifSelectionne = listMotifSelectionne;
			}
			public List<Motif> getFiltredlistMotif() {
				return filtredlistMotif;
			}
			public void setFiltredlistMotif(List<Motif> filtredlistMotif) {
				this.filtredlistMotif = filtredlistMotif;
			}
			public boolean isFlag() {
				return flag;
			}
			public void setFlag(boolean flag) {
				this.flag = flag;
			}
			public int getSizeliste() {   /*  */
				sizeliste = listMotifSelectionne.size();
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
			public boolean isTt() {
				return tt;
			}
			public void setTt(boolean tt) {
				this.tt = tt;
			}	
			
}
