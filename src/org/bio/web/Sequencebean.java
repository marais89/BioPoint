package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Categorie;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.model.SequenceDetailId;
import org.bio.model.Terminaux;
import org.bio.service.SequenceDetailService;
import org.bio.service.SequenceService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;


@ManagedBean
@ViewScoped
public class Sequencebean implements Serializable {
	
	@ManagedProperty(value=("#{sequenceServiceImpl}"))
	private SequenceService sequenceService;
	
	@ManagedProperty(value=("#{sequenceDetailServiceImpl}"))
	private SequenceDetailService sequenceDetailService;
	
	private Sequence sequence;
	private Sequence sequenceSelectinne;
	private List<Sequence> listeSequence;
	private List<Sequence> listeSequencesemaine;
	private List<Sequence> listeSequenceSelectionne;
	private List<Sequence> filtredListSequence;
	private int sizeliste;
	private boolean flag;
	private boolean typeseqq;
	private Date dateDebut;
	private Date datesuivant;
	private SequenceDetail sequenceDetail;
	private SequenceDetailId sequenceDetailId;
	private List<SequenceDetail> listesequencedetail;
	private List<Date> listedate;
	private int j;
	private boolean vistable;
	private boolean vistable2;
	private boolean vistable3;
	private boolean bolSans;
	private boolean bolsanscroix;
	private boolean bol1;
	private boolean bol2;
	private boolean bol3;
	private boolean flagSansPlaning;
	private Long pause;
	private Date pausedate;
	private boolean autogenerate;
	private List<SequenceDetail> listesequencedetailselectionne; 
	private List<SequenceDetail> listesequencedetailselectionne2; 
	private SequenceDetail sequencedetailselectionne;
	private int val;
	private List<Sequence> listeInit;
	
	
	
	@PostConstruct
	public void init()
	{
		listeInit= new ArrayList<Sequence>();
		this.sequence= new Sequence();
		this.listeSequencesemaine= new ArrayList<Sequence>();
		this.sequenceSelectinne=new Sequence();
		this.listeSequence=new ArrayList<Sequence>();
		this.listeSequence=getSequenceService().findAllSequences();
		this.listeSequenceSelectionne=new ArrayList<Sequence>();
		flag=true;
		this.typeseqq=false;
		this.sequence.setTypeSeq("semaine");
		this.sequenceSelectinne.setTypeSeq("semaine");
		this.dateDebut= new Date(2014-06-05);
		this.listesequencedetail= new ArrayList<SequenceDetail>();
		this.sequenceDetail=new SequenceDetail();
		this.sequenceDetailId=new SequenceDetailId();
		this.sequence.setLongueurSequence(1);
		this.listedate=new ArrayList<Date>();
		this.vistable=false;
		this.vistable2=false;
		this.vistable3=false;
		this.bolSans=false;
		this.bolsanscroix=false;
		this.flagSansPlaning=false;
		this.bol1=true;
		this.bol2=false;
		this.bol3=false;
		pausedate= new Date();
		listesequencedetailselectionne=new ArrayList<SequenceDetail>();
		listesequencedetailselectionne2=new ArrayList<SequenceDetail>();
		sequencedetailselectionne = new SequenceDetail();
		j=0;
		filter();
		initialparam();
	}
	
	public void initialparam()
	{
		listeInit=sequenceService.findSequence("Standard");
		if(listeInit.size()==0)
		{Sequence seq = new Sequence();
		seq.setDesigSeq("Standard");
		seq.setLongueurSequence(5);
		seq.setTypeSeq("Global");
		sequenceService.insertSequence(seq);
		listeInit=sequenceService.findSequence("Standard");
		int id = listeInit.get(0).getIdseq();
		Date d1= new Date();
		d1.setTime(60*60*8*1000);
		Date d2= new Date();
		d2.setTime(60*60*12*1000);
		Date d3= new Date();
		d3.setTime(60*60*14*1000);
		Date d4= new Date();
		d4.setTime(60*60*18*1000);
		for(int i=1;i<6;i++)
		{
			SequenceDetailId seqId = new SequenceDetailId();
			seqId.setIdseqd(id);
			seqId.setJour(i);
			SequenceDetail seqDet = new SequenceDetail();
			seqDet.setId(seqId);
			seqDet.setE1(d1);
			seqDet.setE2(d3);
			seqDet.setS1(d2);
			seqDet.setS2(d4);
			seqDet.setNbSeance(2);
			sequenceDetailService.insertDetailSequence(seqDet);
		}
	     for(int i=6;i<8;i++)
		{
			SequenceDetailId seqId = new SequenceDetailId();
			seqId.setIdseqd(id);
			seqId.setJour(i);
			SequenceDetail seqDet = new SequenceDetail();
			seqDet.setId(seqId);
           seqDet.setNbSeance(0);
			sequenceDetailService.insertDetailSequence(seqDet);
		}
		}
		
	}
	
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
		sequenceSelectinne = (Sequence) event.getObject();
		this.listesequencedetailselectionne=sequenceDetailService.findByIdSeq(sequenceSelectinne);
		this.listesequencedetailselectionne2=sequenceDetailService.findByIdSeq(sequenceSelectinne);
	}	
	public void filter()
	{
		for(int i=0;i<listeSequence.size();i++)
		{ 
			if(listeSequence.get(i).getTypeSeq().equals("semaine")||listeSequence.get(i).getTypeSeq().equals("sans_Planing")){listeSequencesemaine.add(listeSequence.get(i));}			
		}
	}
	
	
	//fct execution de methode selon val
		public void selonexecute()
		{
			if(this.val==1)
			{ajouterSequence();}
			else
			{modifierSequence();}
		}
		//fct pour changer  l'etat de val
		public void changevalajout()
		{
			this.val=1;
			this.sequenceSelectinne=new Sequence();
		}
		public void changevalmodif()
		{
			this.val=2;	
			if(sequenceSelectinne.getTypeSeq().equals("sans_Planing")){this.flagSansPlaning=true;}
			else{this.flagSansPlaning=false;}
		}
	
		//fct de creation de la liste de sans planing
		public void remplirdetailsansplaning()
		{
			//GregorianCalendar calendar = new java.util.GregorianCalendar(); 
			this.listesequencedetail=new ArrayList<SequenceDetail>();
			setVistable(true);	
			this.bolSans=true;
			setVistable3(false);	
			for(int i=0;i<7;i++)
			{
				if(i==5){this.sequenceDetail.setNbSeance(1);}
				else if(i==6){this.sequenceDetail.setNbSeance(0);}
				else{this.sequenceDetail.setNbSeance(2);}
				this.sequenceDetailId.setJour(i+1);
				this.sequenceDetail.setId(this.getSequenceDetailId());
				listesequencedetail.add(this.sequenceDetail);
				sequenceDetail=new SequenceDetail();
				sequenceDetailId=new SequenceDetailId();
				
			}	
			this.vistable2=false;
			this.sequence.setTypeSeq("sans_Planing");
			this.sequence.setLongueurSequence(7);
		}
	
	//fct de creation de la list sequence detail
	public void remplirdetailsemaine()
	{

		//GregorianCalendar calendar = new java.util.GregorianCalendar(); 
		this.listesequencedetail=new ArrayList<SequenceDetail>();
		setVistable(true);	
		this.bolSans=false;
		setVistable3(true);	
		for(int i=0; i<7;i++)
		{
			if(i==5){this.sequenceDetail.setNbSeance(1);}
			else if(i==6){this.sequenceDetail.setNbSeance(0);}
			else{this.sequenceDetail.setNbSeance(2);}
			this.sequenceDetailId.setJour(i+1);
			this.sequenceDetail.setId(this.getSequenceDetailId());
			listesequencedetail.add(this.sequenceDetail);
			sequenceDetail=new SequenceDetail();
			sequenceDetailId=new SequenceDetailId();
			
		}	
		this.vistable2=false;
		this.sequence.setTypeSeq("semaine");
		this.sequence.setLongueurSequence(7);
	 }
	
	public void remplirdetailjours()
	{
		this.listesequencedetail=new ArrayList<SequenceDetail>();
		//GregorianCalendar calendar = new java.util.GregorianCalendar(); 
		setVistable(true);	
		setVistable3(true);
		this.bolSans=false;
			j=0;
			this.sequenceDetail.setNbSeance(2);
			this.sequenceDetailId.setJour(j+1);
			this.sequenceDetail.setId(this.getSequenceDetailId());
			listesequencedetail.add(this.sequenceDetail);
			sequenceDetail=new SequenceDetail();
			sequenceDetailId=new SequenceDetailId();			
			j++;
			this.vistable2=true;
			this.sequence.setLongueurSequence(1);
			this.sequence.setTypeSeq("seq_jours");
	 }
	
	public void ajouterdetailjours()
	{
			this.sequenceDetail.setNbSeance(2);
			this.sequenceDetailId.setJour(j+1);
			this.sequenceDetail.setId(this.getSequenceDetailId());
			listesequencedetail.add(this.sequenceDetail);
			sequenceDetail=new SequenceDetail();
			sequenceDetailId=new SequenceDetailId();				
			for (int i=0;i<listesequencedetail.size();i++)
			{listesequencedetail.get(i).getId().setJour(i+1);}
			this.sequence.setTypeSeq("seq_jours");
			this.sequence.setLongueurSequence(this.sequence.getLongueurSequence()+1);
			j++;
	 }
	
	public void ajouterdetailjoursselectionne()
	{
			this.sequenceDetail.setNbSeance(2);
			this.sequenceDetailId.setJour(j+1);
			this.sequenceDetail.setId(this.getSequenceDetailId());
			listesequencedetailselectionne.add(this.sequenceDetail);
			sequenceDetail=new SequenceDetail();
			sequenceDetailId=new SequenceDetailId();			
			j++;	
			for (int i=0;i<listesequencedetailselectionne.size();i++)
			{listesequencedetailselectionne.get(i).getId().setJour(i+1);}
			this.sequenceSelectinne.setTypeSeq("seq_jours");
			this.sequenceSelectinne.setLongueurSequence(this.sequenceSelectinne.getLongueurSequence()+1);
	 }
	
	public void retour()
	{
		this.listesequencedetail=new ArrayList<SequenceDetail>();
		this.vistable=false;
		this.vistable3=false;
		this.vistable2=false;
		this.bolSans=false;
	}
	
	// fct delete a line from table seqence detail
	public void removeseqdet(SequenceDetail seqdet )
	{
		this.listesequencedetail.remove(seqdet);
		this.sequence.setLongueurSequence(this.sequence.getLongueurSequence()-1);
		for (int i=0;i<listesequencedetail.size();i++)
		{listesequencedetail.get(i).getId().setJour(i+1);}
	}	
	
	//fct delate pour la table Listesequencedetailselectionne
	public void removeseqdet2(SequenceDetail seqdet )
	{
		this.listesequencedetailselectionne.remove(seqdet);
		this.sequenceSelectinne.setLongueurSequence(this.sequenceSelectinne.getLongueurSequence()-1);
		for (int i=0;i<listesequencedetailselectionne.size();i++)
		{listesequencedetailselectionne.get(i).getId().setJour(i+1);}
	}	
	
	
	public String affichejours (SequenceDetail seqdet)
	{
		if(seqdet.getId().getJour()==1){return "Lundi";}
		else if(seqdet.getId().getJour()==2){return "Mardi";}
		else if(seqdet.getId().getJour()==3){return "Mercredi";}
		else if(seqdet.getId().getJour()==4){return "Jeudi";}
		else if(seqdet.getId().getJour()==5){return "Vendredi";}
		else if(seqdet.getId().getJour()==6){return "Samedi";}
		else return "Dimanche";
	}
	
	public boolean affcroix(int lib)
	{
		if(lib==1||lib==0){return true;}
		else return false;
	}
	
	///////////////////////   fct pour le bouton a 3 niveaux /////////
	public void changeme1(SequenceDetail seqdet)
	{
		seqdet.setNbSeance(2);		
	}
	
	public void changeme2(SequenceDetail seqdet)
	{
		seqdet.setNbSeance(0);
	}
	
	public void changeme3(SequenceDetail seqdet)
	{
		seqdet.setNbSeance(1);
	}
	
	
	
	
	public boolean undeuxlib1(int lib)
	{
		if(lib==0){return true;}
		else {return false;}
	}
	
	public boolean undeuxlib2(int lib)
	{
		if(lib==1){return true;}
		else {return false;}
		
	}
	public boolean undeuxlib3(int lib)
	{
		if(lib==2){return true;}
		else {return false;}		
	}
	public boolean undeuxlib4(int lib)
	{
		if(lib==2 || lib==1){return true;}
		else {return false;}		
	}
	///////////////////////////////////////////////////////
	
	// fct test	
				public void test()
				{
					System.out.println("ca marche bien !");
				}
		
		// fct d'activation ou de désactivation de la colonne de selection // 
				public void activate ()
				{
					setFlag(!this.flag);			
				}	
				
		//fct active typecat
				public void activatetype()
				{
					if(this.sequence.getTypeSeq().toString().equals("semaine"))
					{setTypeseqq(true);}
					else if(this.sequence.getTypeSeq().toString().equals("seq_jours"))
					{setTypeseqq(false);}
				}
				
				public boolean activatetype2()
				{
					if(this.sequenceSelectinne.getTypeSeq().toString().equals("semaine")|| this.sequenceSelectinne.getTypeSeq().toString().equals("sans_Planing") )
					{return false;}
					else 
					{return true;}
				}
			
				 
				 //fct pour génere
				 public void autogenere(SequenceDetail seqdet)
				 {
					 if(seqdet.getId().getJour() >1){
					  listesequencedetail.get(seqdet.getId().getJour()-1).setE1((listesequencedetail.get((seqdet.getId().getJour())-2).getE1()));
					  listesequencedetail.get(seqdet.getId().getJour()-1).setS1((listesequencedetail.get((seqdet.getId().getJour())-2).getS1()));
					  listesequencedetail.get(seqdet.getId().getJour()-1).setE2((listesequencedetail.get((seqdet.getId().getJour())-2).getE2())); 
					  listesequencedetail.get(seqdet.getId().getJour()-1).setS2((listesequencedetail.get((seqdet.getId().getJour())-2).getS2())); 
					  listesequencedetail.get(seqdet.getId().getJour()-1).setPause(listesequencedetail.get((seqdet.getId().getJour())-2).getPause());
					  listesequencedetail.get(seqdet.getId().getJour()-1).setNbSeance((listesequencedetail.get((seqdet.getId().getJour())-2).getNbSeance()));
					  listesequencedetail.get(seqdet.getId().getJour()-1).setNbHeures((listesequencedetail.get((seqdet.getId().getJour())-2).getNbHeures())); 
				 }}
				 
				 //fct pour genere pour la listesequencedetailselectionne
				 public void autogenere2(SequenceDetail seqdet)
				 {
					 if(seqdet.getId().getJour() >1){
					  listesequencedetailselectionne.get(seqdet.getId().getJour()-1).setE1((listesequencedetailselectionne.get((seqdet.getId().getJour())-2).getE1()));
					  listesequencedetailselectionne.get(seqdet.getId().getJour()-1).setS1((listesequencedetailselectionne.get((seqdet.getId().getJour())-2).getS1()));
					  listesequencedetailselectionne.get(seqdet.getId().getJour()-1).setE2((listesequencedetailselectionne.get((seqdet.getId().getJour())-2).getE2())); 
					  listesequencedetailselectionne.get(seqdet.getId().getJour()-1).setS2((listesequencedetailselectionne.get((seqdet.getId().getJour())-2).getS2())); 
					  listesequencedetailselectionne.get(seqdet.getId().getJour()-1).setPause(listesequencedetailselectionne.get((seqdet.getId().getJour())-2).getPause());
					  listesequencedetailselectionne.get(seqdet.getId().getJour()-1).setNbSeance((listesequencedetailselectionne.get((seqdet.getId().getJour())-2).getNbSeance()));
					  listesequencedetail.get(seqdet.getId().getJour()-1).setNbHeures((listesequencedetail.get((seqdet.getId().getJour())-2).getNbHeures())); 
				 }}
				 
				 public void vider()
				 {
					 this.listesequencedetail=new ArrayList<SequenceDetail>();
					 this.sequence = new Sequence();
					 this.sequenceDetail=new SequenceDetail();
					 this.sequenceDetailId= new SequenceDetailId();
					 this.vistable=false;
					 this.vistable3=false;
					 this.vistable2=false;
					 
					 this.sequence= new Sequence();
						this.sequenceSelectinne=new Sequence();
						this.listeSequence=new ArrayList<Sequence>();
						this.listeSequenceSelectionne=new ArrayList<Sequence>();
						this.listeSequence=getSequenceService().findAllSequences();
						flag=true;
						this.typeseqq=false;
						this.sequence.setTypeSeq("semaine");
						this.sequenceSelectinne.setTypeSeq("semaine");
						this.dateDebut= new Date(2014-06-05);
						this.listesequencedetail= new ArrayList<SequenceDetail>();
						this.sequenceDetail=new SequenceDetail();
						this.sequenceDetailId=new SequenceDetailId();
						//this.setJourslibres(1);
						this.sequence.setLongueurSequence(1);
						this.listedate=new ArrayList<Date>();
						this.bol1=true;
						this.bol2=false;
						this.bol3=false;
						pausedate= new Date();
						listesequencedetailselectionne=new ArrayList<SequenceDetail>();
						listesequencedetailselectionne2=new ArrayList<SequenceDetail>();
						sequencedetailselectionne = new SequenceDetail();
						j=0;
				 }
				 ////////////////////////*********//////////////////////////
				 //fct pour génerer la table de la semaine a modifier 
				 public void genereModifSemaine()
				 {
					 if (this.listesequencedetailselectionne.size()<8)
					 {
						 setVistable(true);	
						 setVistable3(true);	
							for(int i=listesequencedetailselectionne.size();i<7;i++)
							{
								if(i==5){this.sequenceDetail.setNbSeance(1);}
								else if(i==6){this.sequenceDetail.setNbSeance(0);}
								else{this.sequenceDetail.setNbSeance(2);}
								this.sequenceDetailId.setJour(i+1);
								this.sequenceDetail.setId(this.getSequenceDetailId());
								listesequencedetailselectionne.add(this.sequenceDetail);
								sequenceDetail=new SequenceDetail();
								sequenceDetailId=new SequenceDetailId();
								
							}	
							this.vistable2=false;
							this.sequence.setTypeSeq("semaine");
							this.sequence.setLongueurSequence(7);}
						 
						 
					 
					 else
					 {this.listesequencedetailselectionne=new ArrayList<SequenceDetail>();
						setVistable(true);	
						setVistable3(true);	
						for(int i=0; i<7;i++)
						{
							if(i==5){this.sequenceDetail.setNbSeance(1);}
							else if(i==6){this.sequenceDetail.setNbSeance(0);}
							else{this.sequenceDetail.setNbSeance(2);}
							this.sequenceDetailId.setJour(i+1);
							this.sequenceDetail.setId(this.getSequenceDetailId());
							listesequencedetailselectionne.add(this.sequenceDetail);
							sequenceDetail=new SequenceDetail();
							sequenceDetailId=new SequenceDetailId();
							
						}}	
					 for (int i=0;i<listesequencedetailselectionne.size();i++)
						{listesequencedetailselectionne.get(i).getId().setJour(i+1);}
				     }
				 
				
	    ////////////////////// CRUD ///////////////////////////
				
				// fct ajout sequence
				public void ajouterSequence(){
					
					try{if (this.bolSans==false){
						for(int i=0;i<this.listesequencedetail.size();i++)
						{
							if(this.listesequencedetail.get(i).getNbSeance()==2)
							{if(this.listesequencedetail.get(i).getE1()==null ||
								this.listesequencedetail.get(i).getE2()==null ||
								this.listesequencedetail.get(i).getS1()==null ||
								this.listesequencedetail.get(i).getS2()==null)
								{FacesMessage msg = new FacesMessage("Vous avez oublier une case d'une séance double vide !!");  
						        FacesContext.getCurrentInstance().addMessage(null, msg);
						        return;}}
							else if(this.listesequencedetail.get(i).getNbSeance()==1)
							{if(this.listesequencedetail.get(i).getE1()==null ||
							this.listesequencedetail.get(i).getS1()==null)
							{FacesMessage msg = new FacesMessage("Vous avez oublier une case d'une séance unique vide !!");  
					        FacesContext.getCurrentInstance().addMessage(null, msg);
					        return;}}
						}
						for(int i=0;i<this.listesequencedetail.size();i++)
						{ SequenceDetail sq=listesequencedetail.get(i);
							if(this.vistable2==false && sq.getE1()!= null && sq.getE2()!= null && sq.getS1()!= null && sq.getS2()!= null){
							if(this.listesequencedetail.get(i).getE1().after(this.listesequencedetail.get(i).getS1())||
									this.listesequencedetail.get(i).getS1().after(this.listesequencedetail.get(i).getE2())||
											this.listesequencedetail.get(i).getE2().after(this.listesequencedetail.get(i).getS2()))
											{FacesMessage msg = new FacesMessage("Vous n'avais pas respecté l'enchainnement des heures !!");  
									        FacesContext.getCurrentInstance().addMessage(null, msg);
									        return;}
						}}
						}
					else
					{
						for(SequenceDetail sd:listesequencedetail)
						{
							if(sd.getNbSeance()!=0){
							if(sd.getE1()==null|| sd.getS2()==null||sd.getNbHeures()==null){FacesMessage msg = new FacesMessage("Vous avez oublier une case vide !!");  
					        FacesContext.getCurrentInstance().addMessage(null, msg);
					        return;}}
						}
					}
						}
					
					
					catch(DataAccessException e){
						FacesMessage msg = new FacesMessage("Erreur");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						e.getMessage();}
					
					try{
						getSequenceService().insertSequence(sequence);
						}
					catch(DataAccessException e){
						FacesMessage msg = new FacesMessage("Erreur lors de l'ajout de la sequence");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						e.getMessage();}
					
					try{
						for(int i=0;i<listesequencedetail.size();i++)
						{
						listesequencedetail.get(i).setIdseq(sequence);
						if(listesequencedetail.get(i).getE2()!=null)
						{
						 setPause((listesequencedetail.get(i).getE2().getTime())-(listesequencedetail.get(i).getS1().getTime()));
						 pausedate.setTime(pause);
						listesequencedetail.get(i).setPause(pausedate);
						}
						 sequenceDetailService.insertDetailSequence(listesequencedetail.get(i));
						}
						FacesMessage msg = new FacesMessage("  sequence ajouté avec Succés");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						}
					catch(DataAccessException e){
						FacesMessage msg = new FacesMessage("Erreur lors de l'ajout de la details sequence");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						e.getMessage();}
					
				        vider();
				        RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
				
				     }
				
		//fct supprimer un sequence de la liste listsequencelselectionner
				public void removesequence(Sequence seq )
				{
					this.listeSequenceSelectionne.remove(seq);
				}
				
				//fct modification categorie
				public void modifierSequence(){
					/*try{if (this.flagSansPlaning==false){
						for(int i=0;i<this.listesequencedetailselectionne.size();i++)
						{
							if(this.listesequencedetailselectionne.get(i).getNbSeance()==2)
							{if(this.listesequencedetailselectionne.get(i).getE1()==null ||
								this.listesequencedetailselectionne.get(i).getE2()==null ||
								this.listesequencedetailselectionne.get(i).getS1()==null ||
								this.listesequencedetailselectionne.get(i).getS2()==null)
								{FacesMessage msg = new FacesMessage("Vous avez oublier une case d'une séance double vide !!");  
						        FacesContext.getCurrentInstance().addMessage(null, msg);
						        return;}}
							else if(this.listesequencedetailselectionne.get(i).getNbSeance()==1)
							{if(this.listesequencedetailselectionne.get(i).getE1()==null ||
							this.listesequencedetailselectionne.get(i).getS1()==null)
							{FacesMessage msg = new FacesMessage("Vous avez oublier une case d'une séance unique vide !!");  
					        FacesContext.getCurrentInstance().addMessage(null, msg);
					        return;}}
						}
						for(int i=0;i<this.listesequencedetailselectionne.size();i++)
						{SequenceDetail sq=listesequencedetail.get(i);
						if(this.vistable2==false && sq.getE1()!= null && sq.getE2()!= null && sq.getS1()!= null && sq.getS2()!= null){
							if(this.listesequencedetailselectionne.get(i).getE1().after(this.listesequencedetailselectionne.get(i).getS1())||
									this.listesequencedetailselectionne.get(i).getS1().after(this.listesequencedetailselectionne.get(i).getE2())||
											this.listesequencedetailselectionne.get(i).getE2().after(this.listesequencedetailselectionne.get(i).getS2()))
											{FacesMessage msg = new FacesMessage("Vous n'avais pas respecté l'enchainnement des heures !!");  
									        FacesContext.getCurrentInstance().addMessage(null, msg);
									        return;}
						}}}
					else
					{
						for(SequenceDetail sd:listesequencedetailselectionne)
						{
							if(sd.getNbSeance()!=0){
								long l1,l2,diff;
								l1=sd.getE1().getTime();
								l2=sd.getS2().getTime();
								//diff= l2-l1;
								//Date diffD= new Date();
							    //diffD.setTime(diff);
							if(sd.getE1()==null|| sd.getS2()==null||sd.getNbHeures()==null){FacesMessage msg = new FacesMessage("Vous avez oublier une case vide !!");  
							FacesContext.getCurrentInstance().addMessage(null, msg);
					        return;}
							else if (sd.getS2().before(sd.getE1())){FacesMessage msg = new FacesMessage("Date debut doit etre inferieur a la date fin !!!");  
							FacesContext.getCurrentInstance().addMessage(null, msg);
					        return;}
							else if(sd.getNbHeures().before(diffD)){FacesMessage msg = new FacesMessage(" La périod declaré est moin que le nombre d'heure choisi !!!");  
							FacesContext.getCurrentInstance().addMessage(null, msg);
					        return;}}
						}
					   }
						}
					
					catch(DataAccessException e){
						FacesMessage msg = new FacesMessage("Erreur");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						e.getMessage();}*/
					try{
						for(int i=0;i<listesequencedetailselectionne2.size();i++)
						{getSequenceDetailService().deleteDetailSequence(listesequencedetailselectionne2.get(i));}
						getSequenceService().updateSequence(this.sequenceSelectinne);
						for(int i=0;i<listesequencedetailselectionne.size();i++)
						{   listesequencedetailselectionne.get(i).setIdseq(sequenceSelectinne);
							getSequenceDetailService().insertDetailSequence(listesequencedetailselectionne.get(i));}
						RequestContext.getCurrentInstance().execute(" diag_modif.hide()");
						FacesMessage msg = new FacesMessage(" Sequence modifier avec Succés");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						}
					catch(DataAccessException e){
						FacesMessage msg = new FacesMessage("Erreur");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						e.getMessage();
					}
				vider();
				 RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
				}
				
				//fct supprimer sequence
				public void supprimSequence()
				{
					try{
						for(Sequence s:listeSequenceSelectionne)
						{
							this.listesequencedetailselectionne= sequenceDetailService.findByIdSeq(s);
							for(SequenceDetail sd:listesequencedetailselectionne)
							{sequenceDetailService.deleteDetailSequence(sd);}
							getSequenceService().deleteSequence(s);
						}
						FacesMessage msg = new FacesMessage(" Sequence(s) supprimé(s) avec Succés");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
					   }	
					catch(DataAccessException e){
						FacesMessage msg = new FacesMessage("Attention vous ne pouvez pas supprimer cette séquence, Car elle est affecté à d'autres personnels");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						e.getMessage();}
					vider();
				}		

	
	
	/// GETTERS AND SETTERS // 
	public SequenceService getSequenceService() {
		return sequenceService;
	}
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}
	public Sequence getSequence() {
		return sequence;
	}
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
	public Sequence getSequenceSelectinne() {
		return sequenceSelectinne;
	}
	public void setSequenceSelectinne(Sequence sequenceSelectinne) {
		this.sequenceSelectinne = sequenceSelectinne;
	}
	public List<Sequence> getListeSequence() {
		this.listeSequence=new ArrayList<Sequence>();
		this.listeSequence=getSequenceService().findAllSequences();
		for(int i =0;i<listeSequence.size();i++)
		{if(listeSequence.get(i).getDesigSeq().equals("Standard")){listeSequence.remove(i);}}
		return listeSequence;
	}
	public void setListeSequence(List<Sequence> listeSequence) {
		this.listeSequence = listeSequence;
	}
	public List<Sequence> getListeSequenceSelectionne() {
		return listeSequenceSelectionne;
	}
	public void setListeSequenceSelectionne(List<Sequence> listeSequenceSelectionne) {
		this.listeSequenceSelectionne = listeSequenceSelectionne;
	}
	public List<Sequence> getFiltredListSequence() {
		return filtredListSequence;
	}
	public void setFiltredListSequence(List<Sequence> filtredListSequence) {
		this.filtredListSequence = filtredListSequence;
	}
	public int getSizeliste() {
		sizeliste = listeSequenceSelectionne.size();
		return sizeliste;
	}
	public void setSizeliste(int sizeliste) {
		this.sizeliste = sizeliste;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isTypeseqq() {
		return typeseqq;
	}
	public void setTypeseqq(boolean typeseqq) {
		this.typeseqq = typeseqq;
	}
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDatesuivant() {
		return datesuivant;
	}
	public void setDatesuivant(Date datesuivant) {
		this.datesuivant = datesuivant;
	}
	public List<SequenceDetail> getListesequencedetail() {
		return listesequencedetail;
	}
	public void setListesequencedetail(List<SequenceDetail> listesequencedetail) {
		this.listesequencedetail = listesequencedetail;
	}
	public SequenceDetail getSequenceDetail() {
		return sequenceDetail;
	}
	public void setSequenceDetail(SequenceDetail sequenceDetail) {
		this.sequenceDetail = sequenceDetail;
	}
	public SequenceDetailId getSequenceDetailId() {
		return sequenceDetailId;
	}
	public void setSequenceDetailId(SequenceDetailId sequenceDetailId) {
		this.sequenceDetailId = sequenceDetailId;
	}	
	public List<Date> getListedate() {
		return listedate;
	}
	public void setListedate(List<Date> listedate) {
		this.listedate = listedate;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public SequenceDetailService getSequenceDetailService() {
		return sequenceDetailService;
	}
	public void setSequenceDetailService(SequenceDetailService sequenceDetailService) {
		this.sequenceDetailService = sequenceDetailService;
	}

	public boolean isVistable() {
		return vistable;
	}

	public void setVistable(boolean vistable) {
		this.vistable = vistable;
	}
	public boolean isVistable2() {
		return vistable2;
	}
	public void setVistable2(boolean vistable2) {
		this.vistable2 = vistable2;
	}
	public boolean isBol1() {
		return bol1;
	}
	public void setBol1(boolean bol1) {
		this.bol1 = bol1;
	}
	public boolean isBol2() {
		return bol2;
	}
	public void setBol2(boolean bol2) {
		this.bol2 = bol2;
	}
	public boolean isBol3() {
		return bol3;
	}
	public void setBol3(boolean bol3) {
		this.bol3 = bol3;
	}

	public Long getPause() {
		return pause;
	}
	public void setPause(Long pause) {
		this.pause = pause;
	}
	public Date getPausedate() {
		return pausedate;
	}
	public void setPausedate(Date pausedate) {
		this.pausedate = pausedate;
	}
	public boolean isAutogenerate() {
		return autogenerate;
	}
	public void setAutogenerate(boolean autogenerate) {
		this.autogenerate = autogenerate;
	}

	public List<SequenceDetail> getListesequencedetailselectionne() {
		return listesequencedetailselectionne;
	}
	public void setListesequencedetailselectionne(
			List<SequenceDetail> listesequencedetailselectionne) {
		this.listesequencedetailselectionne = listesequencedetailselectionne;
	}
	public SequenceDetail getSequencedetailselectionne() {
		return sequencedetailselectionne;
	}
	public void setSequencedetailselectionne(
			SequenceDetail sequencedetailselectionne) {
		this.sequencedetailselectionne = sequencedetailselectionne;
	}

	public List<SequenceDetail> getListesequencedetailselectionne2() {
		return listesequencedetailselectionne2;
	}

	public void setListesequencedetailselectionne2(
			List<SequenceDetail> listesequencedetailselectionne2) {
		this.listesequencedetailselectionne2 = listesequencedetailselectionne2;
	}

	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public boolean isVistable3() {
		return vistable3;
	}
	public void setVistable3(boolean vistable3) {
		this.vistable3 = vistable3;
	}
	public List<Sequence> getListeSequencesemaine() {
		return listeSequencesemaine;
	}
	public void setListeSequencesemaine(List<Sequence> listeSequencesemaine) {
		this.listeSequencesemaine = listeSequencesemaine;
	}
	public boolean isBolSans() {
		return bolSans;
	}
	public void setBolSans(boolean bolSans) {
		this.bolSans = bolSans;
	}
	public boolean isBolsanscroix() {
		return bolsanscroix;
	}
	public void setBolsanscroix(boolean bolsanscroix) {
		this.bolsanscroix = bolsanscroix;
	}
	public List<Sequence> getListeInit() {
		return listeInit;
	}
	public void setListeInit(List<Sequence> listeInit) {
		this.listeInit = listeInit;
	}
	public boolean isFlagSansPlaning() {
		return flagSansPlaning;
	}
	public void setFlagSansPlaning(boolean flagSansPlaning) {
		this.flagSansPlaning = flagSansPlaning;
	}	
}
