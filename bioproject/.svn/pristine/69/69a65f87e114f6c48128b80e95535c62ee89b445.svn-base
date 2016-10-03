package org.bio.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.time.DateUtils;
import org.bio.model.Conge;
import org.bio.model.HistoriqueCat;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.bio.model.Pointage;
import org.bio.model.PointageId;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.service.CategorieService;
import org.bio.service.FerieService;
import org.bio.service.HistoriqueCatService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.PointageService;
import org.bio.service.SequenceService;
import org.bio.util.PointageEngine;
import org.primefaces.context.RequestContext;


@ManagedBean
@ViewScoped
public class PointageBean2 implements Serializable {
	
	// ** LES INJECTIONS
	@ManagedProperty(value = ("#{ferieServiceImpl}"))
	private FerieService ferieService;
	@ManagedProperty(value = "#{pointageServiceImpl}")
	private PointageService pointageService;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	@ManagedProperty(value = "#{sequenceServiceImpl}")
	private SequenceService sequenceService;	
	@ManagedProperty(value = "#{historiqueCatServiceImpl}")
	private HistoriqueCatService historiquecatservice;
	private List<HistoriqueCat> listHistorique;	
	
	// ** LES ATTRIBUTS
	private List<Personnel> listePersonnel;
	private Mvt mvt;
	private List<Mvt> listMvt;
	private Pointage pointage;
	private List<Pointage> listpointage;
	private ArrayList<ArrayList<Mvt>> listMvtSep;
	private ArrayList<HashSet<Personnel>> listPersSep;
	private int nb=0;
	private boolean flag1=true;
	private boolean flag2=false;
	private boolean flag3=false;
	private List<Mvt> listMvt2;
	public boolean indice=true;
	public int valx=0;
	public String s="10/10/2014";
	public Date df;
	private String msg ="Traitement des données en cours...";
	private Date dateInter;
	
		public void execut()
		{
			{
				System.out.println("timer executer");
				listMvt2 = new ArrayList<Mvt>();
				// modifer from findnew to find all
				listMvt2= mvtService.findNewMvt();
				if(listMvt2.size()>0)
				{setFlag1(false);setFlag2(true);setFlag3(false);this.valx=listMvt2.size();
				System.out.println(flag1+""+flag2+" "+flag3);
				//RequestContext.getCurrentInstance().update("formheader");
				}}		
		}	
	
	
@PostConstruct
public void init()
{	listePersonnel= new ArrayList<Personnel>();
	listePersonnel= personnelService.findAllPersonnels();
	listMvt= new ArrayList<Mvt>();
	listMvt=getMvtService().findAllMvts(); 
	//distinct(listMvt);
	listPersSep= new ArrayList<HashSet<Personnel>>();
	listMvtSep= new ArrayList<ArrayList<Mvt>>();
	listHistorique = new ArrayList<HistoriqueCat>();
	listHistorique.addAll(getHistoriquecatservice().findAllHistoriqueCats());
	listMvt2= new ArrayList<Mvt>();
	listMvt2=mvtService.findNewMvt();
	//timer.scheduleAtFixedRate(task,3000,15000);
	//mettrnew();
	//fct init
	//preparMvt();
	detectMvt();
}

//fct qui filtre les donnée de mvt est elimine les redandance
public void filtreMvt()
{
	List<Mvt> inter = new ArrayList<Mvt>();
	inter.addAll(mvtService.findAllMvts());
	Date d;Date t;Personnel p;
	
	if(inter.size()>1)
	{ 		
		for(int k=0;k<inter.size()-1;k++)
		{	
			d=inter.get(k).getJourLogique();
			t=inter.get(k).getHeure();
			p=inter.get(k).getPersonnel();
	for(int i=k+1;i<inter.size();i++)
	{
		if(inter.get(i).getJourLogique().equals(d) && inter.get(i).getHeure().equals(t) && inter.get(i).getPersonnel().getIdper()==p.getIdper())
		{
			mvtService.deleteMvt(inter.get(i));
			inter.remove(i);
			i--;
		}
		}
	}}
	
}

//public void distinct(List<Mvt> l)
//{
//	ArrayList<Mvt> inter = new ArrayList<Mvt>();
//	Date d;Date t;Personnel p;
//	if(l.size()>1)
//	{
//		d=l.get(0).getJourLogique();
//		t=l.get(0).getHeure();
//		p=l.get(0).getPersonnel();
//	for(int i=1;i<l.size();i++)
//	{
//		if(l.get(i).getJourLogique().equals(d) && l.get(i).getHeure().equals(t) && l.get(i).getPersonnel().getIdper()==p.getIdper())
//		{
//			mvtService.deleteMvt(l.get(i));
//			l.remove(i);
//			i--;
//		}
//		else
//		{
//			d=l.get(i).getJourLogique();
//			t=l.get(i).getHeure();
//			p=l.get(i).getPersonnel();			
//		}
//	}
//	}
//	else{}
//}

public void fr()
{
	this.flag1=false;this.flag2=false;this.flag3=true;
	RequestContext.getCurrentInstance().update("formheader:loadpointage formheader:gif1 formheader:gif2 formheader:pbc");	
}

public void test()
{
	init();
	//Long l= new Long(10000);
	this.flag1=false;this.flag2=false;this.flag3=true;
	RequestContext.getCurrentInstance().update("formheader:loadpointage formheader:gif1 formheader:gif2 formheader:gif3");	
	try {
	chargerPointage();
	} catch (Exception e) {
		// TODO: handle exception
	}
}

public void detectMvt()
{
	listMvt2= new ArrayList<Mvt>();
	listMvt2=getMvtService().findNewMvt();
	//distinct(listMvt);
	if(listMvt2.size()>0)
	{this.nb=listMvt2.size();
	this.flag1=false; this.flag2=true; this.flag3=false;this.valx=listMvt2.size();
	RequestContext.getCurrentInstance().update("formheader:loadpointage");
	RequestContext.getCurrentInstance().update("formheader");	
	}
}

public void mettrnew()
{
	for(int i=0;i<listMvt2.size();i++)
	{
		listMvt2= new ArrayList<Mvt>();
		listMvt2.addAll(mvtService.findNewMvt());
		listMvt2.get(i).setEtat("new");
		mvtService.updateMvt(listMvt2.get(i));
		System.out.println("make element new "+i);
	}
}

public void mettrOk(Mvt m)
{
	m.setEtat("ok");
	mvtService.updateMvt(m);
}

public void chargerPointage() 
{	
	try{		
		//RequestContext.getCurrentInstance().execute("PF('loadpointage').block()");
		filtreMvt();
		
		listMvt= new ArrayList<Mvt>();
		listMvt=getMvtService().findAllMvts(); 
		listMvt2= new ArrayList<Mvt>();
		listMvt2=mvtService.findNewMvt();
		
	this.indice=false;	
	this.listMvtSep= new ArrayList<>();	
	if(listMvt.size()==listMvt2.size())
	{preparMvt();}
	else if(listMvt2.size()==0)
	{System.out.println("LISTMVT2 EST VIDE ---");
	this.flag3=false;this.flag2=false;this.flag1=true;
	this.indice=true;
	RequestContext.getCurrentInstance().update("formheader:loadpointage");
	return ;}
	else
	{		
	preparMvt();
	collectMvt(listMvtSep,listPersSep);
	listMvtSep= new ArrayList<ArrayList<Mvt>>();
	listPersSep= new ArrayList<HashSet<Personnel>>();
	preparMvt();
	}
	System.out.println("succée execution :!!");
	System.out.println("listMvt:"+listMvt.size());
	System.out.println("listMvtSep:"+listMvtSep.size());
	System.out.println("-----------------------");
	
	listpointage= new ArrayList<Pointage>();
	List<Mvt> listMvtPersonnalise= new ArrayList<Mvt>();
	for(int i=0;i<listMvtSep.size();i++)
	{ System.out.println(listMvtSep.get(i).get(0).getJourLogique());System.out.println(listMvtSep.get(i).size());}
		
	
	for(int i=0;i<listMvtSep.size();i++)
	{			
		System.out.println(listMvtSep.get(i).get(0).getJourLogique());
		Date dateInter=listMvtSep.get(i).get(0).getJourLogique();
		HashSet<Personnel> sp=listPersSep.get(i);
		Iterator it=sp.iterator();
		while (it.hasNext())
		{System.out.println(it.next());}
		
		List<Personnel> Lpa =new ArrayList<Personnel>();
		if(listMvt.size()==listMvt2.size())
		{Lpa.addAll(listePersonnel);}
		else{Lpa.addAll(listPersSep.get(i));}
		for(Personnel p :Lpa)
		{
			System.out.println("-------------");
			System.out.println(p.getNom());
			System.out.println(p);
			System.out.println(listMvtSep.size());
			System.out.println(listMvtSep.get(i).size());
			chargerDetailSequencePersonnel(p,listMvtSep.get(i).get(0).getJourLogique());
			listMvtPersonnalise= new ArrayList<Mvt>();
			listMvtPersonnalise.addAll(filtrePersonnel(listMvtSep.get(i), p))  ;
			System.out.println("/+/+/+"+listMvtPersonnalise.size());
			insertPointage(listMvtPersonnalise,p,dateInter);
			checkforDepedencies();
			defineAutorisation(p,listMvtSep.get(i));
			loadRetard();
			loadHoursTowork();
			//hoursworked();
			heureTravail();
			Supplyhours();
			isAbsent();
		listpointage.add(this.pointage);
		System.out.println("boucle terminer");
		}
		
		
		System.out.println("----------------------------------------");
		System.out.println("-------------  JOUR TERMINÉ ------------");
		System.out.println("----------------------------------------");
	}
	saveAll();
	for(int i=0;i<listMvt2.size();i++)
	{ 
		mettrOk(listMvt2.get(i));
		System.out.println("update Mvt"+i);
	}
	this.flag3=false;this.flag2=false;this.flag1=true;
	this.indice=true;
	//RequestContext.getCurrentInstance().update("formheader");
	RequestContext.getCurrentInstance().update("formheader:loadpointage");
	}
catch(Exception e)
{	e.printStackTrace(); }
}


public void collectMvt(ArrayList<ArrayList<Mvt>> llm,ArrayList<HashSet<Personnel>> lsp)
{ System.out.println("collectMvt executé ***");
	listMvt2= new ArrayList<Mvt>();
	for(int i=0;i<llm.size();i++)
	{
		for(Personnel p:lsp.get(i))
		{
			pointageService.deleteById(p.getIdper(),llm.get(i).get(0).getJourLogique());
			listMvt2.addAll(mvtService.findByIdOk(p.getIdper(), llm.get(i).get(0).getJourLogique()));
		}
	}
	
	for(int i=0;i<listMvt2.size();i++)
	{System.out.println(listMvt2.get(i));
	}
	System.out.println("fin de collectmvt ***");
	System.out.println(listMvt2.size());
	
}

public List<Mvt> filtrePersonnel(List<Mvt> l,Personnel p )
{
	List<Mvt> inter= new ArrayList<Mvt>();
	for(int i=0;i<l.size();i++)
	{
		if(l.get(i).getPersonnel().getIdper()==p.getIdper())
		{
			inter.add(l.get(i));
		}
	}
	if(inter.size()==0)
	{System.out.println("========<>>> cet personnel n'ap pas de mvt dans cette partie de ListMvtSep ");}
	return inter;
}

public void insertPointage(List<Mvt> lm,Personnel p,Date dateInter)
{ System.out.println("=>>> inserPointage");
	pointage= new Pointage();
	pointage.setPersonnel(p);
	if(lm.size()==0){pointage.setId(new PointageId(dateInter,p.getIdper()));}
	else{
	pointage.setId(new PointageId(lm.get(0).getJourLogique(),p.getIdper()));	
	if ((p.getCurrentSequence() != null)&&(p.getCurrentListDetails().size() > 0)) 
	{  System.out.println("--etap 1");
		if (p.getCurrentSequence().getTypeSeq().equalsIgnoreCase("semaine")) 
		{System.out.println("--etap 2");
			SequenceDetail d = findSequenceDetail(p, lm.get(0).getJourLogique());
			pointage.setSequenceDetail(d);

			if (d != null) 
			{ if((d.getE1() == null)&&(d.getS1() == null)&&(d.getE2() == null)&&(d.getS2() == null))
			{pointage.setE1(d.getE1());pointage.setS1(d.getE1());pointage.setE2(d.getE1());pointage.setS2(d.getE1());}
			else{
				if(d.getNbSeance()>0)
				{
				if (d.getE1() != null) 
				{pointage.setE1(FindE1(p, d,lm));     }
				if (d.getS1() != null) 
				{pointage.setS1(FindS1(p, d,lm));	 }
				if (d.getNbSeance() == 2) 
				{
					if (d.getE2() != null)
					{pointage.setE2(FindE2(p, d,lm)); }
					if (d.getS2() != null) 
					{pointage.setS2(FindS2(p, d,lm)); }

				}
				}
		    }}

		} else 
		{ System.out.println("--etap 6");
			SequenceDetail d = findDaySequenceDetail(p, lm.get(0).getJourLogique());
			pointage.setSequenceDetail(d);
			if (d != null) {
				if(d.getNbSeance()>0)
				{				
					if (d.getE1() != null) 
					{	pointage.setE1(FindE1(p, d,lm));	}
					if (d.getS1() != null) 
					{	pointage.setS1(FindS1(p, d,lm));	}
				if (d.getNbSeance() == 2) 
				{
					if (d.getE2() != null)
					{	pointage.setE2(FindE2(p, d,lm));	}
					if (d.getS2() != null) 
					{	pointage.setS2(FindS2(p, d,lm));	}

				}
				}
			}
		}

	}}
	System.out.println("Insertion terminé");
}

//fct calcul du retatrd
public void loadRetard() 
{
	try {
		System.out.println("=>>> loadRetard");
		
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
	Date dif1 = f.parse("00:00:00");
	Date dif2 = f.parse("00:00:00");
	Date dif11 = f.parse("00:00:00");
	Date dif12 = f.parse("00:00:00");
	Date dif22 = f.parse("00:00:00");
	
	
	Date diffTotal = new Date();
	Date diffTotalHM = new Date();

	boolean f1=false,f2=false;
	if (pointage.getSequenceDetail() != null) 
	{
		System.out.println(pointage.getSequenceDetail().getE1());
		System.out.println(pointage.getE1());
		System.out.println(pointage.getSequenceDetail().getS1());
		System.out.println(pointage.getS1());
		
		if ((pointage.getSequenceDetail().getE1() != null)&&(pointage.getE1() != null)) 			
		{ 
			
			Date d= new Date() ;d.setTime(pointage.getSequenceDetail().getE1().getTime()-2*60*60*1000);
			
			System.out.println("************ DIF1 **************");
			System.out.println(pointage.getE1()+"++"+d);
			System.out.println("******************************");
			
			if (d.before(pointage.getE1())) 
			{
				dif1.setTime(pointage.getE1().getTime()-(pointage.getSequenceDetail().getE1().getTime()));
			dif11.setTime(dif1.getTime()+60*60*1000);
			System.out.println("**--**--**--**--** dif11 =====>"+dif11);
				pointage.setRetard1(dif11);
				//diffTotal.setTime(dif1.getTime());
				f1=true;
			}
		}
		
		if ((pointage.getSequenceDetail().getE2() != null)&&(pointage.getE2() != null)) 
		{			Date d2= new Date(); d2.setTime(pointage.getSequenceDetail().getE2().getTime());
		
		System.out.println("************ DIF1 **************");
		System.out.println(pointage.getE2()+"++"+d2);
		System.out.println("******************************");
		
			if (d2.before(pointage.getE2())) 
			{
					dif2.setTime(pointage.getE2().getTime()	- (pointage.getSequenceDetail().getE2().getTime())+60*60*1000);
					dif12.setTime(dif2.getTime()-2*60*60*1000);
					System.out.println("**--**--**--**--** dif12 =====>"+dif12);
				pointage.setRetard2(dif12);
				f2=true;
			}
		}  
		if(f1 && f2){diffTotal.setTime(dif1.getTime()+dif2.getTime());}
		else
		{
			if(f1){diffTotal.setTime(dif1.getTime()+60*60*1000);}
			else if(f2){diffTotal.setTime(dif2.getTime()-2*60*60*1000);}
			else{diffTotal.setTime(dif11.getTime());}		
		
		}
		
		//diffTotal.setTime(dif1.getTime()+dif2.getTime());
		//diffTotalHM.setTime(dif1.getTime()+dif2.getTime());
		dif22.setTime(dif11.getTime()+dif12.getTime()+60*60*1000);
		pointage.setRetardTotal(diffTotal);pointage.setRetardHrMn(dif11);
	}
	} catch (ParseException e) {e.printStackTrace();}
}

//fct les heur travaiiler 
public void heureTravail()
{
	try {
	SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
	
		Date dif1 = f.parse("00:00:00");
		Date dif2 = f.parse("00:00:00");
		Date difTotal = f.parse("00:00:00");
		Long diffInt1;Long diffInt2;
		Boolean f1= false,f2=false;
		
	if((pointage.getE1()!= null) && (pointage.getS1()!= null))
	{
		diffInt1= pointage.getS1().getTime()-pointage.getE1().getTime();
		dif1.setTime(diffInt1);
		f1=true;
	}
	if((pointage.getE2()!= null) && (pointage.getS2()!= null))
	{
		diffInt2= pointage.getS2().getTime()-pointage.getE2().getTime();
		dif2.setTime(diffInt2);
		f2=true;
	}
	if(f1==true && f2== true)
	{difTotal.setTime(dif1.getTime()+dif2.getTime()-60*60*1000);}
	else if(f1== true && f2== false)
	{difTotal.setTime(dif1.getTime()-60*60*1000);}
	else if(f1== false && f2==true)
	{difTotal.setTime(dif2.getTime()-60*60*1000);}
	
	//pointage.setBudgHrMn(difTotal);
	pointage.setPresenceHrMn(difTotal);
	
	} catch (ParseException e) {e.printStackTrace();}
}

//fct qui retourn le jour de la sequence 
private SequenceDetail findDaySequenceDetail(Personnel p, Date start) 
{
	System.out.println("=>>> findDaySequenceDetail");
	int seq = (getDaydiffrence(start, p.getCurrentHisto().getDu()))
			- (p.getCurrentSequence().getLongueurSequence()
					- p.getCurrentHisto().getClejour() + 1);
	int resultat = seq % p.getCurrentSequence().getLongueurSequence();
	if (resultat == 0) {
		resultat = p.getCurrentSequence().getLongueurSequence();
	}
	pointage.setJour(resultat);
	for (SequenceDetail sd : p.getCurrentListDetails()) {
		if (sd.getJour() == resultat) {
			return sd;
		}
	}
	return null;
}

//fct complementaire de finddaysequencedetail
private int getDaydiffrence(Date d1, Date d2) 
{
	int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
	return diffInDays + 1;
}

// fct pour charger les detail sequence à 1 personnel p 
private void chargerDetailSequencePersonnel(Personnel p, Date start) 
	{
	try {
		
	
	System.out.println("=>>> chargerDetailSequencePersonnel");
		Sequence s = getSequenceService().getcurrentSequencebyDay(start, p);
		if (s != null) {
		p.setCurrentListDetails(getSequenceService().findAllDayDetail(s));
		p.setCurrentSequence(s);
		} 
		else {System.out.println("** ATTENTION !! LE PERSONNEL NOMÉ:"+p.getNom()+"N'A PAS DE SEQEUENCE AFFECTÉ  //>>> chargerDetailSequencePersonnel");
		findcurrentSequenceHist(p, start);
		if (p.getCurrentHisto() != null) 
		{
			s = p.getCurrentHisto().getSequence();
			p.setCurrentSequence(s);
			p.setCurrentListDetails(getSequenceService().findAllDayDetail(s));
		}
	}
	} catch (Exception e) {
		System.out.println("Sequence not found !!!!!!");
	}
	}

//search for current historique with sequence
	private void findcurrentSequenceHist(Personnel p, Date start) {

		System.out.println("loading sequence by day for " + p.getPrenom());
		System.out.println("while");
		for (HistoriqueCat hc : listHistorique) {
			System.out.println("for");

			if ((hc.getPersonnel().getIdper() == p.getIdper())
					&& ((hc.getDu().before(start)) || (DateUtils.isSameDay(
							hc.getDu(), start)))
					&& ((hc.getAu().after(start)) || (DateUtils.isSameDay(
							hc.getAu(), start))) && (hc.getSequence() != null)) {
				System.out.println("if");

				p.setCurrentHisto(hc);
				return;
			}
			System.out.println("endfor");
		}
		System.out.println("loading completed");
	}


// fct chercher les mvt d'un personnel
public List<Mvt> chargerPointagepersonnel(List<Mvt> lm,Personnel p)
{
	System.out.println("=>>> chargerPointagepersonnel");
	List tmp = new ArrayList<Mvt>();
	for(Mvt m :lm)
	{
		if(m.getPersonnel().getIdper()==p.getIdper())
		{	tmp.add(m);	}
	}
	if(tmp.size()==0)
	{return null;}
	else {return tmp;}
}

// séparer les mvt par date et les stocker dans listmvtSep
public void preparMvt()
{		
	//listMvt2.addAll(mvtService.findNewMvt());
	if(listMvt2.size()==0){System.out.println("list Mvt est vide");}
	else{
	System.out.println("=>>> preparMvt");
	ArrayList<Mvt> inter= new ArrayList<Mvt>();
	HashSet<Personnel> interSet= new HashSet<>();
	filtre(listMvt2);
		//	*SEPARATION DES MVT PAR DATE ET PAR PERSONNEL*
	Date d= listMvt2.get(0).getJourLogique();
	for(int i=0;i<listMvt2.size();i++)
	{  Mvt m= listMvt2.get(i);
		if(m.getJourLogique().compareTo(d)==0)
		{
			inter.add(m);
			interSet.add(m.getPersonnel());
		}
		else
		{
			listMvtSep.add(inter);
			listPersSep.add(interSet);
			d=listMvt2.get(i).getJourLogique();
			inter=new ArrayList<Mvt>();
			interSet= new HashSet<Personnel>();
			inter.add(m);
			interSet.add(m.getPersonnel());
		}
	}
	listMvtSep.add(inter);
	listPersSep.add(interSet);}
}

//fct qui retourn le detail sequence courant du personnel
public SequenceDetail findSequenceDetail(Personnel p, Date start) 
{	
	System.out.println("=>>> findSequenceDetail");
	int x = start.getDay();
	if (start.getDay() == 0) {x = 7;}
	pointage.setJour(x);
	for (SequenceDetail sd : p.getCurrentListDetails()) {
		if (sd.getJour() == x) {
			return sd;
		}
	}
	return null;
}

//fct pour filtrer la liste des mouvement 
public void filtre(List<Mvt> l)
{
 	for(int i=0;i<l.size()-1;i++)
 	{ 		
 		for(int j=i+1;j<l.size();j++)
 		{
 			if(l.get(i).getPersonnel().getIdper()==l.get(j).getPersonnel().getIdper() && (l.get(i).getJourLogique().compareTo(l.get(j).getJourLogique())==0) && (l.get(i).getHeure().compareTo(l.get(j).getHeure())==0))
 			{l.remove(j);j--;}
 		}
 	}
}

public Mvt retounMin(List<Mvt> lm)
{
	if(lm.size()>0)
	{
	Mvt m= lm.get(0);
	for(int i=0;i<lm.size();i++)
	{
		if(lm.get(i).getHeure().before(m.getHeure()))
		{m=lm.get(i);}
	}return m;}
	else{return null;}
}

public Mvt retounMax(List<Mvt> lm)
{
	if(lm.size()>0)
	{
	Mvt m= lm.get(0);
	for(int i=0;i<lm.size();i++)
	{
		if(lm.get(i).getHeure().after(m.getHeure()))
		{m=lm.get(i);}
	}return m;}
	else{return null;}
}

@SuppressWarnings("deprecation")
public Date FindE1(Personnel p, SequenceDetail d,List<Mvt> lm) 
{
	System.out.println("=>>> FindE1");
	Date tmp = new Date();
	List<Mvt> selectedlm= new ArrayList<Mvt>();
//	tmp = d.getE1();
//	tmp.setHours(tmp.getHours() - 1);
	Date tmp2 = new Date();
//	tmp2 = d.getS1();
//	tmp2.setHours(tmp2.getHours() - 1);	
	tmp = new Date();
	tmp = d.getE1();
	tmp.setHours(tmp.getHours() - 1);
	
	Calendar c = Calendar.getInstance();
	c.setTime(d.getE1());			
	c.setTimeInMillis(c.getTimeInMillis()+3*60*60*1000);
	tmp2 = c.getTime();
	
System.out.println("*****E1*******");
for (Mvt m :lm)
{ 	System.out.println(m.getHeure()); }
System.out.println("1->"+tmp);
System.out.println("2->"+tmp2);
System.out.println("**************");
	
	
	if(lm.size()==0)
	{return null;}
	for (Mvt m :lm)
	{ 
		if(between(m.getHeure(),tmp,tmp2))
		{selectedlm.add(m);}
	}
	if(selectedlm.size()==0)
	{return null;}
	else
	{ //lm.remove(selectedlm.get(0));
	//return selectedlm.get(0).getHeure();
	return retounMin(selectedlm).getHeure();
	}
}
@SuppressWarnings("deprecation")
public Date FindE2(Personnel p, SequenceDetail d,List<Mvt> lm) 
{
	System.out.println("=>>> FindE2");
	Date tmp = new Date();
	Date tmp2 = new Date();
	List<Mvt> selectedlm= new ArrayList<Mvt>();
	
	//tmp = new Date();
	tmp = d.getS1();
	tmp.setHours(tmp.getHours() - 2);
	tmp2 = d.getS2();
	tmp2.setHours(tmp2.getHours() - 2);
	
	System.out.println("*****E2*******");
	for (Mvt m :lm)
	{ 	System.out.println(m.getHeure()); }
	System.out.println("1->"+tmp);
	System.out.println("2->"+tmp2);
	System.out.println("**************");
	
	if(lm.size()==0)
	{return null;}
	for (Mvt m :lm)
	{ 
		if(between(m.getHeure(),tmp,tmp2))
		{selectedlm.add(m);}
	}
	if(selectedlm.size()==0)
	{return null;}
	else
	{	//lm.remove(selectedlm.get(0));
	//return selectedlm.get(selectedlm.size()-1).getHeure(); 
	return retounMax(selectedlm).getHeure();
	}

}

@SuppressWarnings("deprecation")
public Date FindS1(Personnel p, SequenceDetail d,List<Mvt> lm) 
{
	System.out.println("=>>> FindS1");
		
	ArrayList<Mvt> selectedlm= new ArrayList<Mvt>();
	
//	Calendar c = Calendar.getInstance();
//	c.setTime(d.getS1());			
//	c.setTimeInMillis(c.getTimeInMillis()-120*60*1000);
//	tmp = c.getTime();
//	=
	
//	Calendar c2 = Calendar.getInstance();
//	c2.setTime(d.getS1());			
//	c2.setTimeInMillis(c2.getTimeInMillis()-2*60*60*1000);
//	tmp2=c2.getTime();
//	tmp2.setHours(tmp2.getHours());
	Date tmp = new Date();
	tmp = d.getE1();
	tmp.setHours(tmp.getHours() + 3);
	
	Date tmp2 = new Date();
	tmp2 = d.getS1();
	tmp2.setHours(tmp2.getHours()+2);
	
	System.out.println("*****S1*******");
	for (Mvt m :lm)
	{ 	System.out.println(m.getHeure()); }
	System.out.println("1->"+tmp);
	System.out.println("2->"+tmp2);
	System.out.println("**************");
	
//	Date tmp = d.getS1();
//	tmp.setHours(tmp.getHours() -1);
	
	if(lm.size()==0)
	{  return null;}
	else{
	for (Mvt m :lm)
	{ 
		if(between(m.getHeure(),tmp,tmp2))
		{selectedlm.add(m);}
	}
	if(selectedlm.size()==0)
	{;return null;}
	else{//lm.remove(selectedlm.size()-1);
	//return selectedlm.get(0).getHeure();
		if(d.getNbSeance()==2)
	{return retounMin(selectedlm).getHeure();}
		else
		{return retounMax(selectedlm).getHeure();}
	}
}}

@SuppressWarnings("deprecation")
public Date FindS2(Personnel p, SequenceDetail d,List<Mvt> lm) 
{
	Date dt= new Date();
	dt.setTime(d.getE2().getTime()+60*60*1000);
	System.out.println("=>>> FindS2");
	Date tmp2 = new Date();
	List<Mvt> selectedlm= new ArrayList<Mvt>();
	
//	Calendar c = Calendar.getInstance();
//			c.setTime(d.getS2());
//			c.setTimeInMillis(c.getTimeInMillis()+3*60*60*1000);//	
//	tmp2 = c.getTime();	
	
	Date tmp = new Date();
	tmp = d.getE2();
	tmp.setHours(tmp.getHours() + 0);
	//Date tmp2 = new Date();
	tmp2 = d.getS2();
	tmp2.setHours(tmp2.getHours() + 3);
	
	System.out.println("*****S2*******");
	for (Mvt m :lm)
	{ 	System.out.println(m.getHeure()); }
	System.out.println("1->"+d.getE2());
	System.out.println("2->"+tmp2);
	System.out.println("**************");
	
	if(lm.size()==0)
	{return null;}
	for (Mvt m :lm)
	{ 
		if(between(m.getHeure(),d.getE2(),tmp2))
		{selectedlm.add(m);}
	}
	if(selectedlm.size()==0)
	{return null;}
	else
	{ // lm.remove(selectedlm.size()-1);
	//return selectedlm.get(selectedlm.size()-1).getHeure();
	return retounMax(selectedlm).getHeure();
	}
}

//fct pour boolean pour verifier si une heure se trouve entre 2 autre heure ou nn 
public boolean between(Date heureCible,Date debut,Date fin)
{
	if(heureCible.equals(debut)|| heureCible.equals(fin)||(heureCible.before(fin)&& heureCible.after(debut)))
	{System.out.println("BETWEEN terminé avec true");return true;}
	else {System.out.println("BETWEEN terminé avec false");return false;}
}

//fct pour calcul des heure travaillé
public void hoursworked() throws Exception 
{
	System.out.println("=>>> hoursworked");
	SimpleDateFormat f = new SimpleDateFormat("HH:mm");
	Date dif = f.parse("00:00");
	Date dif2 = f.parse("00:00");
	boolean present = false;

		if ((pointage.getE1()!=null)&& (pointage.getS1()!=null)) 
		{
			if (pointage.getE1().after(pointage.getS1())) 
			{
				dif.setTime(pointage.getS1().getTime()- pointage.getE1().getTime()+ (24 * 1000 * 60*60));
				present = true;
			} else 
			{
				dif.setTime(pointage.getS1().getTime()- pointage.getE1().getTime());
				present = true;
			}

		}
		if ((pointage.getE2()!=null)&& (pointage.getS2()!=null)) 
		{
			if (pointage.getE2().after(pointage.getS2())) {
				dif2.setTime(pointage.getS2().getTime()	- pointage.getE2().getTime()+ (24 * 1000 * 60*60));
				present = true;
			} else 
			{
				dif2.setTime(pointage.getS2().getTime()	- pointage.getE2().getTime()-(60*60*1000));
				present = true;
			}
		}
		if(present)
		{
				Date hourstowork = new Date();
				hourstowork.setTime(dif.getTime()+dif2.getTime());
				pointage.setPresenceHrMn(hourstowork);
		}
}

//fct pour calcul des heur q'il doit travaillé
public void loadHoursTowork() throws Exception 
{
	System.out.println("=>>> loadHoursTowork");
	if (pointage.getSequenceDetail() != null) 
	{
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		Date dif = f.parse("00:00");
		Date dif2 = f.parse("00:00");

		if ((pointage.getSequenceDetail().getE1() != null)	&& (pointage.getSequenceDetail().getS1() != null)) {
			if (pointage.getSequenceDetail().getE1().after(pointage.getSequenceDetail().getS1())) {
				dif.setTime(pointage.getSequenceDetail().getS1().getTime()- pointage.getSequenceDetail().getE1().getTime()+ (24*1000 * 60*60));
			} else 
			{
				dif.setTime(pointage.getSequenceDetail().getS1().getTime()- pointage.getSequenceDetail().getE1().getTime());
			}
		}
		if ((pointage.getSequenceDetail().getE2() != null)&& (pointage.getSequenceDetail().getS2() != null)) 
		{
			if (pointage.getSequenceDetail().getE2().after(pointage.getSequenceDetail().getS2())) 
			{
				dif2.setTime(pointage.getSequenceDetail().getS2().getTime()	- pointage.getSequenceDetail().getE2().getTime()+ (24 * 1000 * 60*60));
			} else 
			{
				dif2.setTime(pointage.getSequenceDetail().getS2().getTime()	- pointage.getSequenceDetail().getE2().getTime());
			}
		}	
			Date hourstowork = new Date();
			hourstowork.setTime(dif.getTime()+dif2.getTime());
			pointage.setBudgHrMn(hourstowork);
	}
}

// fct calcul heur supplimntaire
private void Supplyhours() 
{
	System.out.println("=>>> Supplyhours");
	if (pointage.getSequenceDetail() != null) 
	{
		if((pointage.getPresenceHrMn()!=null)&&(pointage.getBudgHrMn()!=null))
		{
			Date dif = new Date();
			if (pointage.getPresenceHrMn().after(pointage.getBudgHrMn()))
			 {
				dif.setTime(pointage.getPresenceHrMn().getTime()-pointage.getBudgHrMn().getTime()-(60*60*1000));
				pointage.setHsupHrMn(dif);
			 }
		}
	}
}


// fct pour verifier l'absence
private void isAbsent()
{
	System.out.println("=>>> isAbsent");
	if(pointage.getSequenceDetail()!=null)
	{
		if((pointage.getSequenceDetail().getNbSeance()!=0))	
		{
			if((!pointage.getFerie())&&(!pointage.getConge()))
			{
				if((pointage.getE1()==null)&& (pointage.getS1()==null)&& (pointage.getE2()==null)&&(pointage.getS2()==null))
					{pointage.setAbsent(true);}
			}
		}
		else if((pointage.getSequenceDetail().getNbSeance()==0))	
		{	pointage.setAbsent(false); }
	}
	else {pointage.setAnomalie(true);}
	System.out.println("//> isAbsent() TERMINÉ AVEC SUCCÉ");
}


private void defineAutorisation(Personnel p,List<Mvt> lm)
{
	System.out.println("=>>> defineAutorisation");
	if((pointage.getAutorisation()!=null)&&(!pointage.getAbsent())&&(pointage.getSequenceDetail().getNbSeance()!=0))
	{	
		if(pointage.getSequenceDetail().getE1().getTime()==pointage.getAutorisation().getDebut().getTime())
		{
			pointage.setRa(FindRA(p,lm));
			pointage.setE1(pointage.getRa());
			pointage.setSa(pointage.getAutorisation().getDebut());
		}
		else  if(pointage.getSequenceDetail().getS1().compareTo(pointage.getAutorisation().getFin())==0)
		{	pointage.setS1(pointage.getSa());}
		else  if(pointage.getSequenceDetail().getS2().compareTo(pointage.getAutorisation().getFin())==0)
		{	pointage.setS2(pointage.getSa());	}
		else  if(pointage.getSequenceDetail().getE2().compareTo(pointage.getAutorisation().getDebut())==0)
		{	pointage.setE2(pointage.getRa());	}
	}
}


private Date FindSA(Personnel p,List<Mvt> lm)
{
	System.out.println("=>>> FindSA");
	int dif = 1000000;
	Mvt mvt = null;
	Date tmp =new Date();
	tmp.setTime(pointage.getAutorisation().getDebut().getTime()-60*60*1000);
	List<Mvt> listToscan = getmvtbyDate(p,tmp,pointage.getAutorisation().getFin(),lm);
	if(listToscan.size()==0)
	{	return null;	}
	if (pointage.getAutorisation().getFin().before(pointage.getAutorisation().getDebut())) 
	{
		for (Mvt m : listToscan) {
		if ((getdiffrence(tmp, m.getHeure()) < dif)) {
			dif = getdiffrence(tmp, m.getHeure());
			mvt = m;
		}
	}
} else {
for (Mvt m : listToscan) {
	if ((getdiffrence(m.getHeure(),tmp) < dif)) {
		dif = getdiffrence(m.getHeure(),tmp);
		mvt = m;
	}
}
}
tmp.setTime(pointage.getAutorisation().getDebut().getTime()+60*60*1000);
if(mvt==null) return null;
return mvt.getHeure();
}

//fct pour les calcul de la diffrence entre 2 date
private int getdiffrence(Date d1, Date d2) 
{
	int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60));
	return Math.abs(diffInDays);
}

//fct qui retourn une liste de mvt a paartir les mvt pré selectionné é qui sont dans l'interval deb fin
public List<Mvt> getmvtbyDate(Personnel p, Date deb, Date fin,List<Mvt> lm) 
{
	System.out.println("=>>> getmvtbyDate");
	List<Mvt> listfilter = new ArrayList<Mvt>();
	for (Mvt m : lm) 
	{
		if ((m.getHeure().compareTo(deb) == 0) || (m.getHeure().compareTo(fin) == 0) || ((m.getHeure().after(deb) && (m.getHeure().before(fin))))) 
		{
			listfilter.add(m);
		}
	}
	return listfilter;
}


private Date FindRA(Personnel p,List<Mvt> lm)
{
	System.out.println("=>>> FindRA");
int dif = 1000000;
Mvt mvt = null;
Date tmp2 = new Date();

Date tmp = new Date();
tmp.setTime(pointage.getAutorisation().getFin().getTime()-60*60*1000);
tmp2.setTime(pointage.getAutorisation().getFin().getTime()+60*60*1000);
System.out.println("i1 "+tmp+" i2 "+tmp2);
System.out.println("enter0");
List<Mvt> listToscan = getmvtbyDate(p,tmp,tmp2,lm);
System.out.println("list size"+listToscan.size());
if(listToscan.size()==0)
{
	return null;
}
if (pointage.getAutorisation().getFin().before(pointage.getAutorisation().getDebut())) {
	System.out.println("step 1");

	for (Mvt m : listToscan) 
	{
		if ((getdiffrence(tmp, m.getHeure()) < dif)) 
		{
			dif = getdiffrence(tmp, m.getHeure());
			mvt = m;
		}
	}
} else {
for (Mvt m : listToscan) 
{
	if ((getdiffrence(m.getHeure(),tmp) < dif)) 
	{
		dif = getdiffrence(tmp, m.getHeure());
		mvt = m;
	}
}
}
pointage.getAutorisation().getDebut().setHours(pointage.getAutorisation().getDebut().getHours() -1);
if(mvt==null) return null;
return mvt.getHeure();
}

// fct pour sauvgarder la liste des pointage realisé
private void saveAll()
{
	try{
		//int x=(listpointage.size()*35)/100;
		//progress=progress+x;
		//RequestContext.getCurrentInstance().update("formheader:pbc");
		for(Pointage po:listpointage)
		{// this.progress=progress+x;
			
			getPointageService().insertPointage(po);
		}
	   }
	catch(Exception e)
		{	e.getStackTrace(); }
}

// fct qui verifier les congés
public void checkforDepedencies() 
{
	System.out.println("=>>> checkforDepedencies");
	Conge c = getPersonnelService().findCongeByDay(	pointage.getId().getJour(), pointage.getPersonnel());
	if (c != null) 
	{
		pointage.setConge(true);
	} else 
	{
		pointage.setConge(false);
	}	
		pointage.setAutorisation(getPersonnelService().findAutorisationByDay(pointage.getId().getJour(),pointage.getPersonnel()));
	
	if (getFerieService().isFerie(pointage.getId().getJour())) 
	{
		pointage.setFerie(true);
	}
}


//  ** GETTERS AND SETTERS **
public Mvt getMvt() {
	return mvt;
}
public void setMvt(Mvt mvt) {
	this.mvt = mvt;
}
public List<Mvt> getListMvt() {
	return listMvt;
}
public void setListMvt(List<Mvt> listMvt) {
	this.listMvt = listMvt;
}
public FerieService getFerieService() {
	return ferieService;
}
public void setFerieService(FerieService ferieService) {
	this.ferieService = ferieService;
}
public PointageService getPointageService() {
	return pointageService;
}
public void setPointageService(PointageService pointageService) {
	this.pointageService = pointageService;
}
public PersonnelService getPersonnelService() {
	return personnelService;
}
public void setPersonnelService(PersonnelService personnelService) {
	this.personnelService = personnelService;
}
public MvtService getMvtService() {
	return mvtService;
}
public void setMvtService(MvtService mvtService) {
	this.mvtService = mvtService;
}
public SequenceService getSequenceService() {
	return sequenceService;
}
public void setSequenceService(SequenceService sequenceService) {
	this.sequenceService = sequenceService;
}
public HistoriqueCatService getHistoriquecatservice() {
	return historiquecatservice;
}
public void setHistoriquecatservice(HistoriqueCatService historiquecatservice) {
	this.historiquecatservice = historiquecatservice;
}
public List<HistoriqueCat> getListHistorique() {
	return listHistorique;
}
public void setListHistorique(List<HistoriqueCat> listHistorique) {
	this.listHistorique = listHistorique;
}
public Pointage getPointage() {
	return pointage;
}
public void setPointage(Pointage pointage) {
	this.pointage = pointage;
}
public List<Pointage> getListpointage() {
	return listpointage;
}
public void setListpointage(List<Pointage> listpointage) {
	this.listpointage = listpointage;
}
public ArrayList<ArrayList<Mvt>> getListMvtSep() {
	return listMvtSep;
}
public void setListMvtSep(ArrayList<ArrayList<Mvt>> listMvtSep) {
	this.listMvtSep = listMvtSep;
}
public ArrayList<HashSet<Personnel>> getListPersSep() {
	return listPersSep;
}
public void setListPersSep(ArrayList<HashSet<Personnel>> listPersSep) {
	this.listPersSep = listPersSep;
}
public int getNb() {
	return nb;
}
public void setNb(int nb) {
	this.nb = nb;
}
public boolean isFlag1() {
	return flag1;
}
public void setFlag1(boolean flag1) {
	this.flag1 = flag1;
}
public boolean isFlag2() {
	return flag2;
}
public void setFlag2(boolean flag2) {
	this.flag2 = flag2;
}
public boolean isFlag3() {
	return flag3;
}
public void setFlag3(boolean flag3) {
	this.flag3 = flag3;
}
public List<Mvt> getListMvt2() {
	return listMvt2;
}
public void setListMvt2(List<Mvt> listMvt2) {
	this.listMvt2 = listMvt2;
}

public boolean isIndice() {
	return indice;
}
public void setIndice(boolean indice) {
	this.indice = indice;
}
public int getValx() {
	return valx;
}
public void setValx(int valx) {
	this.valx = valx;
	}
public String getS() {
	return s;
}
public void setS(String s) {
	this.s = s;
}
public Date getDf() {
	return df;
}
public void setDf(Date df) {
	this.df = df;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public List<Personnel> getListePersonnel() {
	return listePersonnel;
}
public void setListePersonnel(List<Personnel> listePersonnel) {
	this.listePersonnel = listePersonnel;
}
public Date getDateInter() {
	return dateInter;
}
public void setDateInter(Date dateInter) {
	this.dateInter = dateInter;
}

}
