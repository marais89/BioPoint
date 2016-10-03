package org.bio.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.bio.model.Conge;
import org.bio.model.Motif;
import org.bio.model.Personnel;
import org.bio.service.CongeService;
import org.bio.service.MotifService;
import org.bio.service.PersonnelService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;


@ManagedBean
@SessionScoped
public class CongeBeans implements Serializable {
	
	@ManagedProperty(value=("#{congeServiceImpl}"))
	private CongeService congeService;
	@ManagedProperty(value=("#{motifServiceImpl}"))
	private MotifService motifService;
	@ManagedProperty(value=("#{personnelServiceImpl}"))
	private PersonnelService personnelService;
	
	
	private  Conge conge;
	private  Conge congeselectionne;
	private List<Conge> listconge;
	private List<Conge> listcongeSelectionne;
	private List<Conge> filtredlistconge;
	private boolean flag;
	private int sizeliste;
	private List<String> listType;
	private int val;
	private boolean indice;
	private List<Personnel> listPersonnelSelectionne; 
	private HashMap<String, Motif> hlist;
	private HashMap<String,Personnel> hpersonnel;
	private List<Personnel> listepersonnel;
	private List<Motif> listemotif;
	private Motif motif;
	private boolean flagConge;
	private boolean flagAutorisation;
	private boolean flagRecuperation;
	private boolean flagmsg;
	private String msgout ;
	int x;
	private boolean alert;
	private boolean stop;
	private boolean validbutton;
	private boolean flagcalendar;
	private Date date;
	private Date Mdate;
	private Date Mhoure;
	private String color="color2";
	private Personnel pers;
	private String strMotif;
	private Date dateLimite= new Date();
	private Date maxDate= new Date();
	private Date minDate= new Date();
	private Boolean flagMotif;
	private String strAffiche;
	private Personnel personnel;
	private int valheure;
	
		
	@PostConstruct
	private void init()
	
	{this.val=1;
		personnel= new Personnel();
		this.strAffiche="Selectionnez...";
		this.flagMotif=false;
		hpersonnel= new HashMap<String,Personnel>();
	this.conge=new Conge();
	this.motif= new Motif();
	this.congeselectionne=new Conge();
	this.listcongeSelectionne= new ArrayList<Conge>();
	this.listconge=new ArrayList<Conge>();
	this.listconge= congeService.findAllConges();
	flag=true;
	indice=true;
	this.listType=new ArrayList<String>();
	this.listPersonnelSelectionne=new ArrayList<Personnel>();
	this.listepersonnel= new ArrayList<Personnel>();
	listepersonnel= personnelService.findAllPersonnels();
	this.hlist= new HashMap<String, Motif>();
	this.listemotif= new ArrayList<Motif>();
	listemotif=motifService.findAllMotifs();
	this.hlist= new HashMap<String, Motif>();
	remplirlistetype();
	remplirhash();
	this.flagAutorisation=false;
	this.flagConge=false;
	this.flagRecuperation=false;
	setMsgout("vide");;
	this.x=0;
	this.alert=false;
	this.stop=false;
	this.flagcalendar=true;
	this.validbutton=true;
	}	
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
		congeselectionne = (Conge) event.getObject();
	}	
	 
	public void remplirhash()
	{
		for(Motif m:listemotif)
		{
			hlist.put(m.getDesignation(),m);
		}	
		for(Personnel p:listepersonnel)
		{
			hpersonnel.put(p.getNom()+" "+p.getPrenom(), p);
		}
	}
	
	//fct remplit liste type par les type de congés
	public void remplirlistetype()
	{
		this.listType.add("Congé");
		this.listType.add("Récupération");
		this.listType.add("Autorisation");		
	}
	
	
	
	//fct modification flag du congé selon la variable type
	/*	public void selonflag()
		{
			
			if(this.congeselectionne.getType().equals("Congé"))
			{				
				if(this.congeselectionne.getPersonnel()==null){this.msgout="vous n'avez pas selectionné une personnel";this.stop=true;this.alert=false;this.flagcalendar=true;this.color="color1";this.validbutton=false;}
				else if ((x=(this.congeselectionne.getPersonnel().getDroitConge()-this.congeselectionne.getPersonnel().getCongeAccorde()))>0){this.msgout="vous ne pouvé prendre au maximum que " +x+ " jours";this.stop=false;this.alert=true;this.flagcalendar=true;this.color="color2";this.validbutton=true;}
				else if ((x=(this.congeselectionne.getPersonnel().getDroitConge()-this.congeselectionne.getPersonnel().getCongeAccorde()))==0){this.msgout="vous avez terminée les jours de congé, vous n'avais pas le droit de prendre plus !!!";this.stop=true;this.alert=false;this.flagcalendar=true;this.color="color1";this.validbutton=false;}
				 setFlagConge(true);setFlagAutorisation(false);setFlagRecuperation(false);setFlagmsg(true);
				
			}
			else if(this.congeselectionne.getType().equals("Autorisation"))
			{
				 setFlagConge(false);setFlagAutorisation(true);setFlagRecuperation(false);setFlagmsg(false);this.flagcalendar=false;this.msgout="Autorisation";this.alert=false;this.stop=false;this.validbutton=true;
			}
			
			else if(this.congeselectionne.getType().equals("Récupération"))
			{
				if(this.congeselectionne.getPersonnel()==null){this.msgout="vous n'avez pas selectionné une personnel";this.stop=true;this.flagcalendar=true;this.color="color1";this.validbutton=false;}
				else if ((x=(this.congeselectionne.getPersonnel().getDroitRecuperation()-this.congeselectionne.getPersonnel().getRecuperationAccorde()))>0){this.msgout="vous ne pouvé prendre au maximum que " +x+ " jours";this.stop=false;this.alert=true;this.flagcalendar=true;this.color="color2";this.validbutton=true;}
				else if ((x=(this.congeselectionne.getPersonnel().getDroitRecuperation()-this.congeselectionne.getPersonnel().getRecuperationAccorde()))==0){this.msgout="vous avez terminée les jours de recupération, vous n'avais pas le droit de prendre plus !!!";this.stop=true;this.alert=false;this.flagcalendar=true;this.color="color1";this.validbutton=false;}
			 	setFlagConge(false);setFlagAutorisation(false);setFlagRecuperation(true);setFlagmsg(true);
				
			}
			RequestContext.getCurrentInstance().update("form_ajout_conge:msg");
			RequestContext.getCurrentInstance().update("form_ajout_conge:vb");
			RequestContext.getCurrentInstance().update("form_ajout_conge:cal");
			RequestContext.getCurrentInstance().update("form_ajout_conge:cal3");
			 
		}*/
		
	//fct execution de methode selon val
		public void selonexecute()
		{
			
			if(this.val==1)
			{ajouterconge();System.out.println("+++ajout");}
			else if(this.val==2)
			{modifierconge();System.out.println("+++modif");}
			else{}}
		
		//fct pour changer  l'etat de val
		public void changevalajout()
		{
			this.val=1;
			this.indice=true;
			this.congeselectionne=new Conge();	
			RequestContext.getCurrentInstance().update("form_ajout_conge");
			RequestContext.getCurrentInstance().update("form");
		}
		public void changevalmodif()
		{
			System.out.println("--maintenant modification");
			this.val=2;	
			this.indice=false;
			
			if(this.congeselectionne.getType().equals("Autorisation")){this.flagcalendar=false;this.date=congeselectionne.getDebut();}
			else{this.flagcalendar=true;}
			System.out.println("--maintenant modification2");
			RequestContext.getCurrentInstance().update("form_ajout_conge:cal");
			RequestContext.getCurrentInstance().update("form_ajout_conge");
			RequestContext.getCurrentInstance().update("form");
		}
	
		//fct vider pour annuler
		public void vider()
		{
			this.congeselectionne=new Conge();
			//this.motif= new Motif();
			this.date=null;
			this.x=0;
			RequestContext.getCurrentInstance().reset("form_ajout_conge");
		}
		//fct vider motif pour annuler
		public void vidermotif()
		{
			this.motif= new Motif();
			RequestContext.getCurrentInstance().reset("motif");
		}
		
	
	// fct d'activation ou de désactivation de la colonne de selection // 
			public void activate ()
			{
				setFlag(!this.flag);			
			}
			
			//fct supprimer un conge de la liste listcongeselectionner
			public void removeconge(Conge conge )
			{
				this.listcongeSelectionne.remove(conge);
			}
			
			/*public Boolean comparerDate()
			{
				maximumDate();
				System.out.println("Avant execution");
				if(congeselectionne.getDebut().after(congeselectionne.getFin()))
					{
						FacesMessage msg = new FacesMessage("Date début doit préceder date fin");  
						FacesContext.getCurrentInstance().addMessage(null, msg);
						RequestContext.getCurrentInstance().update("form_ajout_conge:messagef");
						return false;
					}
				else{return true;}
			}*/
			
			public void test()
			{
				System.out.println("*** test reussi ***");
			}
			
			public void returnDernierDateConge(int idP) throws ParseException
			{System.out.println("executer");
				if(congeselectionne.getPersonnel()==null)
				{System.out.println("personnel null");
					FacesMessage msg = new FacesMessage("ATTENTION!! Vous n'avez pas selectionner une personnel ");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			        RequestContext.getCurrentInstance().update("form_ajout_conge:c1");
			        RequestContext.getCurrentInstance().update("form:messaget");
			        return;
				}
				List<Conge> listeCongeFiltrer= new ArrayList<Conge>();
	System.out.println("personnennel existe");
				Date df = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
				for(Conge c:listconge)
				{
					if(c.getPersonnel().getIdper()==idP)
					{listeCongeFiltrer.add(c);}
				}
				if(listeCongeFiltrer.size()>0)
				{					
					for(Conge cf:listeCongeFiltrer)
					{
						if(cf.getFin().after(df)){df=cf.getFin();System.out.println("select");System.out.println(df);}
					}
					}
				df.setTime(df.getTime()+86400000);
				this.minDate=df;
				int difference=(this.congeselectionne.getPersonnel().getDroitConge()-this.congeselectionne.getPersonnel().getCongeAccorde());
				if(difference==0){dateLimite.setTime(df.getTime()-86400000);}
				
				if(this.congeselectionne.getType()==null){}
				else if(this.congeselectionne.getType().equals("Autorisation"))
				{this.flagcalendar=false;}
				else{this.flagcalendar=true;}
				//RequestContext.getCurrentInstance().update("form_ajout_conge:cal");
			}
			
			
			public void maximumDate()
			{
				if(this.congeselectionne.getPersonnel()==null){System.out.println("pde personnel !!!");}	
				else{
				x=(this.congeselectionne.getPersonnel().getDroitConge()-this.congeselectionne.getPersonnel().getCongeAccorde());
				long dateFinal= this.congeselectionne.getDebut().getTime()+((x-1)*86400000);
				maxDate.setTime(dateFinal);
				//RequestContext.getCurrentInstance().update("form_ajout_conge:cal");
			}}
			
			
			
						public void ajouterMotif(){
							try{
								motif= new Motif();
								motif.setDesignation(strMotif);
								getMotifService().insertMotif(motif);
								this.congeselectionne.setMotif(motif);
								this.listemotif= new ArrayList<Motif>();
								listemotif=motifService.findAllMotifs();
								//this.flagMotif=true;
								this.strAffiche=motif.getDesignation();
								//RequestContext.getCurrentInstance().update("form_ajout_conge:panelmotif");
								FacesMessage msg = new FacesMessage(" Un nouveau motif ajouté ");  
						        FacesContext.getCurrentInstance().addMessage(null, msg);
						     	}
							catch(DataAccessException e){
								FacesMessage msg = new FacesMessage("Erreur");  
						        FacesContext.getCurrentInstance().addMessage(null, msg);
								e.getMessage();
							}
							
							
						
						}
						public void chargemotif()
						{
							this.congeselectionne.setMotif(motif);
						}
						public void changebol()
						{
							this.flagMotif=false;
							RequestContext.getCurrentInstance().update("form_ajout_conge:panelmotif");
						}
						public void changebol2()
						{
							this.flagMotif=true;
							RequestContext.getCurrentInstance().update("form_ajout_conge:panelmotif");
						}
						
						public void returnHour()
						{
							this.valheure=congeselectionne.getDebut().getHours();
							RequestContext.getCurrentInstance().update(":form_ajout_conge:tp");
							
						}
						
			
			// fct ajout conge
			public void ajouterconge(){
				try{
					if(this.congeselectionne.getDebut()==null||this.congeselectionne.getFin()==null) 
					{
						FacesMessage msg = new FacesMessage(" Valeur date invalide !");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);					        
			        return;}
					
					
					this.flagmsg=false;
					this.alert=false;
					if(this.congeselectionne.getType().equals("Autorisation"))
					{
						// ajouterMotif();
						Calendar calendar1 = Calendar.getInstance();
						Calendar calendar2 = Calendar.getInstance();
						Calendar calendar3 = Calendar.getInstance();
						calendar1.setTime(this.congeselectionne.getDebut());
						calendar2.setTime(this.congeselectionne.getFin());
						calendar3.setTime(this.date);
					
						calendar1.set(calendar3.get(Calendar.YEAR), calendar3.get(Calendar.MONTH),calendar3.get(Calendar.DAY_OF_MONTH));
						calendar2.set(calendar3.get(Calendar.YEAR), calendar3.get(Calendar.MONTH),calendar3.get(Calendar.DAY_OF_MONTH));
						this.congeselectionne.setDebut(calendar1.getTime());
						this.congeselectionne.setFin(calendar2.getTime());
					   
					}
					
					else if(this.congeselectionne.getType().equals("Congé"))
					{
						x=(this.congeselectionne.getPersonnel().getDroitConge()-this.congeselectionne.getPersonnel().getCongeAccorde());
						long diff = Math.abs(this.congeselectionne.getFin().getTime() - this.congeselectionne.getDebut().getTime());
						long numberOfDay = (long)(diff/86400000)+1;
						if(x<numberOfDay)
						{
							//this.msgout="Vous avez depasser le nombre de jours autorisé";
							//this.color="color1";
							//this.flagmsg=true;
							//this.stop=true;
							FacesMessage msg = new FacesMessage(" Vous avez depassé le nombre des jours autorisé");  
					        FacesContext.getCurrentInstance().addMessage(null, msg);					        
					        return;
						}
						else
						{
							//ajouterMotif();
							int somme=this.congeselectionne.getPersonnel().getCongeAccorde()+(int)numberOfDay;
							this.congeselectionne.getPersonnel().setCongeAccorde(somme);
							personnelService.updatePersonnel(congeselectionne.getPersonnel());
						    //this.motif= new Motif();
						}
						
					}
					
					else if(this.congeselectionne.getType().equals("Récupération"))
					{
						long diff = Math.abs(this.congeselectionne.getFin().getTime() - this.congeselectionne.getDebut().getTime());
						
						long numberOfDay = (long)(diff/86400000)+1;
						
						x=(this.congeselectionne.getPersonnel().getDroitRecuperation()-this.congeselectionne.getPersonnel().getRecuperationAccorde());
					
								if(x<numberOfDay)
								{
									//this.flagmsg=true;
									//this.stop=true;
									FacesMessage msg = new FacesMessage(" Vous avez depassé le nombre des jours autorisé");  
							        FacesContext.getCurrentInstance().addMessage(null, msg);
						
							        return;
							        
								}
								else
								{
									
									ajouterMotif();
									int somme=this.congeselectionne.getPersonnel().getRecuperationAccorde()+(int)numberOfDay;
									this.congeselectionne.getPersonnel().setRecuperationAccorde(somme);
									personnelService.updatePersonnel(congeselectionne.getPersonnel());
									//this.motif= new Motif();
									
								    
								}
					}
					
					//
					else {;FacesMessage msg = new FacesMessage("probleme with else");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			        RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
			        this.motif= new Motif();}
					//
					int somme =this.congeselectionne.getPersonnel().getCongeAccorde()+x;
						personnelService.updateconge(somme,congeselectionne.getPersonnel().getIdper());
						getCongeService().insertConge(congeselectionne);
					FacesMessage msg = new FacesMessage(this.congeselectionne.getType()+" affecté avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			        RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
			        this.motif= new Motif();
			        flagcalendar=true;
			        RequestContext.getCurrentInstance().update("form_ajout_conge:cal3 form_ajout_conge:calendar form_ajout_conge:cal form_ajout_conge:tp");
					}
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();
				}			
			}
						
			//fct mise a jour conge
			public void modifierconge()
			{
				try{
					getCongeService().updateConge(congeselectionne);
					FacesMessage msg = new FacesMessage(" Congé modifié avec Succés"); 
					 RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			       
				   }
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();}
			}
			
			//supprimer conge
			public void supprimconge()
			{
				try{
					for(Conge c:listcongeSelectionne)
					{
						long diff = Math.abs(c.getFin().getTime() - c.getDebut().getTime());
						long numberOfDay = (long)(diff/86400000)+1;
						Personnel p=c.getPersonnel();
						
						if(c.getType().equals("Congé"))
						{							
							p.setCongeAccorde(p.getCongeAccorde()-(int)numberOfDay);
							
						}
						else if(c.getType().equals("Autorisation"))
						{
							p.setEncoursDroitRecuperation(p.getEncoursDroitRecuperation()-(int)numberOfDay);
							
						}
						personnelService.updatePersonnel(p);
							congeService.deleteConge(c);
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
			
			public CongeService getCongeService() {
				return congeService;
			}
			public void setCongeService(CongeService congeService) {
				this.congeService = congeService;
			}
			public Conge getConge() {
				return conge;
			}
			public void setConge(Conge conge) {
				this.conge = conge;
			}
			public Conge getCongeselectionne() {
				return congeselectionne;
			}
			public void setCongeselectionne(Conge congeselectionne) {
				this.congeselectionne = congeselectionne;
			}
			public List<Conge> getListconge() {
				this.listepersonnel= new ArrayList<Personnel>();
				listepersonnel= personnelService.findAllPersonnels();
				this.listconge=new ArrayList<Conge>();
				this.listconge= congeService.findAllConges();
				return listconge;
			}
			public void setListconge(List<Conge> listconge) {
				this.listconge = listconge;
			}
			public List<Conge> getListcongeSelectionne() {
				return listcongeSelectionne;
			}
			public void setListcongeSelectionne(List<Conge> listcongeSelectionne) {
				this.listcongeSelectionne = listcongeSelectionne;
			}
			public List<Conge> getFiltredlistconge() {
				return filtredlistconge;
			}
			public void setFiltredlistconge(List<Conge> filtredlistconge) {
				this.filtredlistconge = filtredlistconge;
			}
			public boolean isFlag() {
				return flag;
			}
			public void setFlag(boolean flag) {
				this.flag = flag;
			}
			public int getSizeliste() {
				sizeliste = listcongeSelectionne.size();
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

			public List<Personnel> getListPersonnelSelectionne() {
				return listPersonnelSelectionne;
			}
			public void setListPersonnelSelectionne(List<Personnel> listPersonnelSelectionne) {
				this.listPersonnelSelectionne = listPersonnelSelectionne;
			}
			public MotifService getMotifService() {
				return motifService;
			}
			public void setMotifService(MotifService motifService) {
				this.motifService = motifService;
			}
			public HashMap<String, Motif> getHlist() {
				this.listemotif= new ArrayList<Motif>();
				listemotif=motifService.findAllMotifs();
				
				return hlist;
			}
			public void setHlist(HashMap<String, Motif> hlist) {
				this.hlist = hlist;
			}
			public List<Motif> getListemotif() {
				
				return listemotif;
			}
			public void setListemotif(List<Motif> listemotif) {
				this.listemotif = listemotif;
			}

			public boolean isIndice() {
				return indice;
			}
			public void setIndice(boolean indice) {
				this.indice = indice;
			}
			public Motif getMotif() {
				return motif;
			}
			public void setMotif(Motif motif) {
				this.motif = motif;
			}

			public boolean isFlagConge() {
				return flagConge;
			}

			public void setFlagConge(boolean flagConge) {
				this.flagConge = flagConge;
			}

			public boolean isFlagAutorisation() {
				return flagAutorisation;
			}
			public void setFlagAutorisation(boolean flagAutorisation) {
				this.flagAutorisation = flagAutorisation;
			}
			public boolean isFlagRecuperation() {
				return flagRecuperation;
			}
			public void setFlagRecuperation(boolean flagRecuperation) {
				this.flagRecuperation = flagRecuperation;
			}
		public String getMsgout() {
				return msgout;
			}
			public void setMsgout(String msgout) {
				this.msgout = msgout;
			}
			public boolean isFlagmsg() {
				return flagmsg;
			}
			public void setFlagmsg(boolean flagmsg) {
				this.flagmsg = flagmsg;
			}
			public int getX() {
				return x;
			}
			public void setX(int x) {
				this.x = x;
			}
			public boolean isAlert() {
				return alert;
			}
			public void setAlert(boolean alert) {
				this.alert = alert;
			}
			public boolean isStop() {
				return stop;
			}
			public void setStop(boolean stop) {
				this.stop = stop;
			}
			public boolean isFlagcalendar() {
				return flagcalendar;
			}
			public void setFlagcalendar(boolean flagcalendar) {
				this.flagcalendar = flagcalendar;
			}
			public Date getDate() {
				return date;
			}
			public void setDate(Date date) {
				this.date = date;
			}
			public Date getMdate() {
				return Mdate;
			}
			public void setMdate(Date mdate) {
				Mdate = mdate;
			}
			public Date getMhoure() {
				return Mhoure;
			}
			public void setMhoure(Date mhoure) {
				Mhoure = mhoure;
			}
			public String getColor() {
				return color;
			}
			public void setColor(String color) {
				this.color = color;
			}
			public PersonnelService getPersonnelService() {
				return personnelService;
			}
			public void setPersonnelService(PersonnelService personnelService) {
				this.personnelService = personnelService;
			}
			public boolean isValidbutton() {
				return validbutton;
			}
			public void setValidbutton(boolean validbutton) {
				this.validbutton = validbutton;
			}
			public Personnel getPers() {
				return pers;
			}
			public void setPers(Personnel pers) {
				this.pers = pers;
			}
			public List<Personnel> getListepersonnel() {
				return listepersonnel;
			}
			public void setListepersonnel(List<Personnel> listepersonnel) {
				this.listepersonnel = listepersonnel;
			}
			public HashMap<String, Personnel> getHpersonnel() {
				return hpersonnel;
			}
			public void setHpersonnel(HashMap<String, Personnel> hpersonnel) {
				this.hpersonnel = hpersonnel;
			}
			public String getStrMotif() {
				return strMotif;
			}
			public void setStrMotif(String strMotif) {
				this.strMotif = strMotif;
			}
			public Date getMaxDate() {
				return maxDate;
			}
			public void setMaxDate(Date maxDate) {
				this.maxDate = maxDate;
			}
			public Date getMinDate() {
				return minDate;
			}
			public void setMinDate(Date minDate) {
				this.minDate = minDate;
			}
			public Date getDateLimite() {
				return dateLimite;
			}
			public void setDateLimite(Date dateLimite) {
				this.dateLimite = dateLimite;
			}
			public Boolean getFlagMotif() {
				return flagMotif;
			}
			public void setFlagMotif(Boolean flagMotif) {
				this.flagMotif = flagMotif;
			}
			public String getStrAffiche() {
				return strAffiche;
			}
			public void setStrAffiche(String strAffiche) {
				this.strAffiche = strAffiche;
			}
			public Personnel getPersonnel() {
				return personnel;
			}
			public void setPersonnel(Personnel personnel) {
				this.personnel = personnel;
			}
			public int getValheure() {
				return valheure;
			}
			public void setValheure(int valheure) {
				this.valheure = valheure;
			}		
			
}
