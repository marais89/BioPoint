package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Terminaux;
import org.bio.service.TerminauxService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;


@ManagedBean
@SessionScoped
public class TerminauxBean implements Serializable{
	
	@ManagedProperty(value=("#{terminauxServiceImpl}"))
	private TerminauxService terminauxService;
	private Terminaux terminal;
	private Terminaux terminalselectionner;
	private List<Terminaux> listeterminalselectionner;
	private List<Terminaux> listeterminaux;
	private List<Terminaux> filtredlistTerminaux;
	private List<Integer> listeVitesse;
	private List<String> listType;
	private boolean flag;
	private int bradio;
	private int sizeliste;
	private int val;
	private boolean ipcom1;
	private boolean ipcom2;
	private boolean mode;	
	
	@PostConstruct
	private void init()
	{
		listType=new ArrayList<String>();
		terminal= new Terminaux();
		terminalselectionner= new Terminaux();
		this.listeterminaux = new ArrayList<Terminaux>();
		listeterminaux= terminauxService.findAllTerminaux();
		this.listeterminalselectionner= new ArrayList<Terminaux>();
		flag=true;
		this.ipcom1=true;
		this.ipcom2=true;
		this.listeVitesse= new ArrayList<Integer>();
		remplitvitesse();
		remplirString();
		mode=true;
	}
	
	public void switchmode()
	{
		if(terminalselectionner.getType().equals("IP"))
		{
			mode=true;
		}
		else {mode=false;}
	}
	public void changeipcom()
	{
		if(this.terminalselectionner.getType()=="IP"){this.ipcom1=false;}
		//if(this.terminalselectionner.getType()=="RS"){this.ipcom2=false;this.ipcom1=true;}
		else {this.ipcom1=true;}
	}
	
	 public void reset() {  
		 RequestContext.getCurrentInstance().reset(":form_ajout_term"); 
		 //RequestContext.getCurrentInstance().reset("terminal:form_ajout_term");  
	         
	    }  
	
	//fct execution de methode selon val
	public void selonexecute()
	{
		if(this.val==1)
		{ajouterterminal();}
		else
		{modifierterminal();}
	}
	//fct pour changer  l'etat de val
	public void changevalajout()
	{
		this.val=1;
		this.terminalselectionner=new Terminaux();		
	}
	public void changevalmodif()
	{
		this.val=2;		
	}
	
	
	//fct remplir listString
	public void remplirString()
	{
		this.listType.add("IP");
		this.listType.add("RS");
	}
	
	
	//fct remplir liste vitesse 
	public void remplitvitesse()
	{
		this.listeVitesse.add(9600);
		this.listeVitesse.add(14400);
		this.listeVitesse.add(28800);
		this.listeVitesse.add(57600);
		this.listeVitesse.add(115200);
		
	}
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
		terminalselectionner = (Terminaux) event.getObject();
	}	
	
	
	//fct de nettoyage terminaux
	public void nv()
	{
		this.terminal= new Terminaux();	
	}
	
	// fct d'activation ou de désactivation de la colonne de selection // 
		public void activate ()
		{
			setFlag(!this.flag);			
		}
	
		//fct qui retourne la nature (pointage /enroullement de la terminal)	
		public String natureterminal(Boolean p)
		{
			String nature="";
			if (p)
			{nature="pointage";}
			else 
			{nature="enrollement";}
			return nature;
		}
		
		
	// fct test	
		public void test()
		{System.out.println("ca marche bien !");}
		
		//fct vider
		public void vider()
		{
			this.terminal=new Terminaux();
		}
		
	// fct ajout terminal
		public void ajouterterminal(){
			try{
				if (this.bradio== 1){terminalselectionner.setPointage(true);terminalselectionner.setEnrollement(false);}
			      else if (this.bradio==2){terminalselectionner.setPointage(false);terminalselectionner.setEnrollement(true);}
				getTerminauxService().insertTerminal(this.terminalselectionner);
				FacesMessage msg = new FacesMessage(" terminal ajouter avec Succés");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			catch(DataAccessException e){
				FacesMessage msg = new FacesMessage("Erreur");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
				e.getMessage();
			}
		}
		
		//fct mise a jour terminal
		public void modifierterminal()
		{
			try{
				if (this.bradio== 1){terminalselectionner.setPointage(true);terminalselectionner.setEnrollement(false);}
			      else if (this.bradio==2){terminalselectionner.setPointage(false);terminalselectionner.setEnrollement(true);}
				getTerminauxService().updateTerminal(terminalselectionner);
				FacesMessage msg = new FacesMessage(" terminal modifié avec Succés");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			   }
			catch(DataAccessException e){
				FacesMessage msg = new FacesMessage("Erreur");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
				e.getMessage();}
		}
		
		//supprimer terminaux
		public void supprimterminaux()
		{
			try{
				for(Terminaux t:listeterminalselectionner)
				{
					System.out.println(t.getLibelle());
				getTerminauxService().deleteTerminal(t);
				}
				FacesMessage msg = new FacesMessage(" element(s) supprimé(s) avec Succés");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			   }	
			catch(DataAccessException e){
				FacesMessage msg = new FacesMessage("Erreur");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
				e.getMessage();}
		}
		
		// fct pour modifier l'etat de bradio et attribuer les valeur adequat a (pointage et enrollement)
		public void changeradio()
		{
	      if (this.bradio== 1){terminal.setPointage(true);terminal.setEnrollement(false);}
	      else if (this.bradio==2){terminal.setPointage(false);terminal.setEnrollement(true);}
		}
		
		//fct supprimer un terminal de la liste listterminalselectionner
		public void removeterminal(Terminaux terminal )
		{
			this.listeterminalselectionner.remove(terminal);
		}
		

	////////////// GETTERS AND SETTERS //////////////////

		public TerminauxService getTerminauxService() {
			return terminauxService;
		}

		public void setTerminauxService(TerminauxService terminauxService) {
			this.terminauxService = terminauxService;
		}

		public Terminaux getTerminal() {
			return terminal;
		}

		public void setTerminal(Terminaux terminal) {
			this.terminal = terminal;
		}

		public Terminaux getTerminalselectionner() {
			return terminalselectionner;
		}

		public void setTerminalselectionner(Terminaux terminalselectionner) {
			this.terminalselectionner = terminalselectionner;
		}

		public List<Terminaux> getListeterminaux() {
			this.listeterminaux = new ArrayList<Terminaux>();
			listeterminaux= terminauxService.findAllTerminaux();
			return listeterminaux;
		}

		public void setListeterminaux(List<Terminaux> listeterminaux) {
			this.listeterminaux = listeterminaux;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public List<Terminaux> getListeterminalselectionner() {
			return listeterminalselectionner;
		}

		public void setListeterminalselectionner(
				List<Terminaux> listeterminalselectionner) {
			this.listeterminalselectionner = listeterminalselectionner;
		}

		public List<Terminaux> getFiltredlistTerminaux() {
			return filtredlistTerminaux;
		}

		public void setFiltredlistTerminaux(List<Terminaux> filtredlistTerminaux) {
			this.filtredlistTerminaux = filtredlistTerminaux;
		}

		public int getBradio() {
			return bradio;
		}
		public void setBradio(int bradio) {
			this.bradio = bradio;
		}
		public int getSizeliste() {
			sizeliste = listeterminalselectionner.size();
			return sizeliste;
		}
		public void setSizeliste(int sizeliste) {
			this.sizeliste = sizeliste;
		}
		public List<Integer> getListeVitesse() {
			return listeVitesse;
		}
		public void setListeVitesse(List<Integer> listeVitesse) {
			this.listeVitesse = listeVitesse;
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
		public boolean isIpcom1() {
			return ipcom1;
		}
		public void setIpcom1(boolean ipcom1) {
			this.ipcom1 = ipcom1;
		}
		public boolean isIpcom2() {
			return ipcom2;
		}
		public void setIpcom2(boolean ipcom2) {
			this.ipcom2 = ipcom2;
		}


		public boolean isMode() {
			return mode;
		}


		public void setMode(boolean mode) {
			this.mode = mode;
		}			
				
}
