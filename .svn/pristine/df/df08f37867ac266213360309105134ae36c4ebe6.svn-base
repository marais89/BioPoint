package org.bio.web;

import java.awt.Desktop;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javassist.expr.NewArray;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.bio.model.Affiliation;
import org.bio.model.Categorie;
import org.bio.model.GroupOrder;
import org.bio.model.ModeleRapport;
import org.bio.model.Operateur;
import org.bio.model.Personnel;
import org.bio.model.Rapport;
import org.bio.model.Regles;
import org.bio.model.RelationRapport;
import org.bio.model.Sequence;
import org.bio.report.ReportingService;
import org.bio.service.AffiliationService;
import org.bio.service.CalendrierService;
import org.bio.service.CategorieService;
import org.bio.service.GroupOrderService;
import org.bio.service.ModeleRapportService;
import org.bio.service.OperateurService;
import org.bio.service.PersonnelService;
import org.bio.service.RapportService;
import org.bio.service.ReglesService;
import org.bio.service.RelationRapportService;
import org.bio.service.SequenceService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.result.ModelResultMatchers;

import com.portlandwebworks.commons.dao.DataAccessException;

@ManagedBean
@SessionScoped
public class RapportBean implements Serializable {

	// *SERVICE
	@ManagedProperty(value = ("#{calendrierServiceImpl}"))
	private CalendrierService calendrierService;
	@ManagedProperty(value = ("#{modeleRapportServiceImpl}"))
	private ModeleRapportService modeleRapportService;
	@ManagedProperty(value = ("#{rapportServiceImpl}"))
	private RapportService rapportService;
	@ManagedProperty(value = ("#{relationRapportServiceImpl}"))
	private RelationRapportService relationRapportService;
	@ManagedProperty(value = ("#{groupOrderServiceImpl}"))
	private GroupOrderService groupOrderService;
	@ManagedProperty(value = ("#{reglesServiceImpl}"))
	private ReglesService reglesService;
	@ManagedProperty(value = ("#{affiliationServiceImpl}"))
	private AffiliationService affiliationService;
	@ManagedProperty(value = ("#{categorieServiceImpl}"))
	private CategorieService categorieService;
	@ManagedProperty(value = ("#{sequenceServiceImpl}"))
	private SequenceService sequenceService;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	@ManagedProperty(value = "#{operateurServiceImpl}")
	private OperateurService operateurService;
//	@ManagedProperty(value = "#{contextBean}")
//	private ContextBean contextbean;
	
	
	private ReportingService reportingService = new ReportingService();

	// *AFFILIATION
	private Affiliation Societe;
	private List<Affiliation> listeSte;
	private List<Affiliation> listeAffiliation;
	private TreeNode toor;
	private List<TreeNode> filtredList;
	private List<TreeNode> listeTouNodes;
	private List<Integer> listeId;
	private List<String> listeSociete;
	// *date
	private Date datedebut;
	private Date datefin;
	private String dat;
	private String dat1;
	private String dat2;
	private String dat3;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat heureFormat = new SimpleDateFormat("HH:mm");
	private String period;
	private List<SelectItem> categories;
	private String selection;
	private List<String> listeVal;
	private List<Date> listeDate;
	private String strAffiche = null;

	// *RAPPOTBEAN
	private String ste;
	private TreeNode[] selectedNodes;
	private List<String> liste1;
	private List<List<String>> liste2;
	private List<String> liste3;
	private List<String> liste21;
	private List<Categorie> listeCat;
	private boolean bol1;
	private boolean bol2;
	private boolean bol3;
	private boolean bol4;
	private String champ = null;
	private String condition = null;
	private String valeur = null;
	private String heararchiOptim = null;
	private List<String> inter;
	private Date dateFilter;
	private Date houreFilter;
	private boolean flagPanel;
	private boolean flagUnion;
	private boolean flagAjout;
	private boolean flagtable;
	private boolean flagAff;
	private boolean flagPers;
	private boolean flagAvance;
	private List<String> ListeFormule;

	// private List<String> prefix;
	private List<Sequence> listeSequence;
	private String filtre;
	private String path;
	private String SteAffiche = null;
	// *PERSONNEL
	private String strPersonnel;
	private List<String> listeStrPersonnel;
	private List<Personnel> listePersonnel;
	private List<Personnel> listePersonnel2;
	private List<Personnel> listePersonnel3;
	private List<Personnel> listePersonnel4;
	private Personnel personnelselectionne;
	private List<Integer> listeIdP;
	// *ORDER_GROUP
	private String[][] grouOr;
	private List<String> groupOrAffich;
	private List<String> listegroupOrSelectionne;
	private String strGrouOr;
	private List<Boolean> listeBolValeur;
	private List<Boolean> listeBolGroupOr;
	private List<Boolean> listeBolSaut;
	private List<Boolean> listeBolAjour;
	private HashMap<Integer, String> groupMap;
	private HashMap<Integer, Boolean> groupSautPageMap;
	private List<String> ListeOrder;
	private List<Boolean> listeBolOrder;
	private List<String> groupOrPrefix;
	private List<String> groupOrPrefixSelectionne;
	private HashMap<Integer, String> groupLibelleMap;
	private String ord;
	// *COMMANDE
	private String command;
	private String str1;
	private String str2;
	private String str3;
	private String str4;
	private List<String> listePath;
	// *Modele
	private ModeleRapport modele;
	private List<ModeleRapport> listeModele;
	private String designationRapport;
	private Rapport rapport;
	private int idmodeleRapport;
	private List<String> listeGroupRapport;
	private String rapportSelected;
	private String groupSelected;
	private ArrayList<ArrayList<GrRapport>> listeRapport;
	private ArrayList<GrRapport> listeRapportAff;
	private List<ModeleRapport> listeGroupRapportT;
	private int indice;
	private Boolean bolAcc;
	private List<Rapport> listeRapportpre;
	private Boolean bolSElect;
	// **CHARGEMENT
	private List<RelationRapport> listeRR;
	private List<TreeNode> listeNodes;
	private List<Affiliation> listeAffiliation2;
	private List<Affiliation> listeAffiliation3;
	private List<Affiliation> listeTouAff;
	private int a1;
	private int a2;
	private Boolean bolR;
	private List<Affiliation> societes;
	private ArrayList<ArrayList<Affiliation>> listeStr2;
	private List<String> listeStr;
	private List<String> listeCMD;
	private Operateur currentUser;
	private String lur;
	private String code;
	

	@PostConstruct
	public void init() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		currentUser = getOperateurService().getByLogin(auth.getName());
	
		//System.out.println("@@"+currentUser.getLogin());
		societes = new ArrayList<Affiliation>();
		listeStr2 = new ArrayList<ArrayList<Affiliation>>();
		this.bolR = false;
		listePath = new ArrayList<String>();
		listeTouNodes = new ArrayList<TreeNode>();
		listeTouAff = new ArrayList<Affiliation>();
		listeTouAff = affiliationService.findAllAffiliations();
		listeAffiliation2 = new ArrayList<Affiliation>();
		listeAffiliation3 = new ArrayList<Affiliation>();
		listeRR = new ArrayList<RelationRapport>();
		listeNodes = new ArrayList<TreeNode>();
		this.bolSElect = false;
		listeRapportpre = new ArrayList<Rapport>();
		idmodeleRapport = 1;
		this.groupSelected = "Pointage";
		bolAcc = true;
		listeGroupRapportT = new ArrayList<ModeleRapport>();
		listeGroupRapportT = modeleRapportService.findAllModeleRapport();
		this.indice = 0;
		listeRapportAff = new ArrayList<GrRapport>();
		listeRapport = new ArrayList<ArrayList<GrRapport>>();
		listeGroupRapport = new ArrayList<String>();
		listeModele = new ArrayList<ModeleRapport>();
		listeModele = modeleRapportService.findAllModeleRapport();
		listeDate = new ArrayList<Date>();
		listeVal = new ArrayList<String>();
		groupLibelleMap = new HashMap<Integer, String>();
		groupOrPrefixSelectionne = new ArrayList<>();
		groupOrPrefix = new ArrayList<>();
		listeBolOrder = new ArrayList<Boolean>();
		ListeOrder = new ArrayList<String>();
		groupMap = new HashMap<Integer, String>();
		groupSautPageMap = new HashMap<Integer, Boolean>();
		listeIdP = new ArrayList<Integer>();
		listePersonnel2 = new ArrayList<Personnel>();
		listePersonnel3 = new ArrayList<Personnel>();
		listePersonnel4 = new ArrayList<Personnel>();
		listePersonnel = new ArrayList<Personnel>();
		listeStrPersonnel = new ArrayList<String>();
		listePersonnel = personnelService.findAllPersonnels();
		listeBolValeur = new ArrayList<Boolean>();
		listeBolGroupOr = new ArrayList<Boolean>();
		listeBolSaut = new ArrayList<Boolean>();
		listeBolAjour = new ArrayList<Boolean>();
		listegroupOrSelectionne = new ArrayList<String>();
		this.groupOrAffich = new ArrayList<String>();
		this.grouOr = new String[100][100];
		this.listeSequence = new ArrayList<Sequence>();
		this.listeSequence = sequenceService.findAllSequences();
		this.flagAjout = false;
		this.inter = new ArrayList<String>();
		this.listeCat = new ArrayList<Categorie>();
		this.listeCat = categorieService.findAllCategories();
		listeAffiliation = new ArrayList<Affiliation>();
		listeId = new ArrayList<Integer>();
		listeSte = new ArrayList<Affiliation>();
		this.listeSte = new ArrayList<Affiliation>();
		filtredList = new ArrayList<TreeNode>();
		this.listeSte = new ArrayList<Affiliation>();
		this.listeSte = affiliationService.getAffiliation("So");
		this.liste1 = new ArrayList<String>();
		this.liste2 = new ArrayList<List<String>>();
		this.liste3 = new ArrayList<String>();
		this.liste21 = new ArrayList<String>();
		Affiliation h = getAffiliationService().getRoot();
		toor = new CheckboxTreeNode("root", null);
		toor.setExpanded(true);
		displayRoot(h, toor, true);
		remplirListe();
		bol1 = true;
		bol2 = false;
		bol3 = false;
		bol4 = false;
		flagPanel = true;
		flagUnion = false;
		flagtable = false;
		this.flagAff = true;
		this.flagPers = true;
		this.flagAvance = false;
		this.ListeFormule = new ArrayList<String>();
		this.listeSociete = new ArrayList<String>();
		listeSociete = affiliationService.getAffiliationTypes();
		// function init
		chargerListegrouOr();
		ajouterGroupOr();
		constructDate();
		genereStringDate();
		remplirListeGrRap();
		remplirListRapport();
		chageindice(0);
		ajouterPath();
		initDate();
		this.lur="Str";
		
		System.out.println("test rapport ** ");
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		int x1 = 00;
		int x2 = calendar.getMaximum(calendar.DAY_OF_MONTH);
		System.out.println(x1);
		System.out.println(x2);
		System.out.println(calendar.DAY_OF_MONTH);
		System.out.println("------------------");
		Date d = new Date();
		System.out.println(generate(5));
	}

	public void test() {
		System.out.println("ok, executer");
	}

	public void initDate() {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar = new java.util.GregorianCalendar();
		this.strAffiche = "( Année en cours )";
		calendar.set(calendar.get(calendar.YEAR), calendar.JANUARY, 1);
		datedebut = calendar.getTime();
		calendar = new java.util.GregorianCalendar();
		calendar.set(calendar.get(calendar.YEAR), calendar.DECEMBER, 31);
		datefin = calendar.getTime();
		this.dat1 = dateFormat.format(datedebut);
		datedebut = null;
		this.dat2 = dateFormat.format(datefin);
		datefin = null;
	}

	public void remise() {
		this.datedebut = null;
		this.datefin = null;
		this.heararchiOptim = new String();
		this.champ = new String();
		this.condition = new String();
		this.valeur = new String();
		this.dateFilter = null;
		this.houreFilter = null;
		this.ListeFormule = new ArrayList<String>();
		this.listegroupOrSelectionne = new ArrayList<String>();
		strGrouOr = new String();
		listeBolValeur = new ArrayList<Boolean>();
		listeBolGroupOr = new ArrayList<Boolean>();
		listeBolSaut = new ArrayList<Boolean>();
		listeBolAjour = new ArrayList<Boolean>();
		remplirGroupOr();
		ajouterGroupOr();
		bol1 = true;
		bol2 = false;
		bol3 = false;
		bol4 = false;
		flagPanel = true;
		flagUnion = false;
		flagtable = false;
		this.flagAff = true;
		this.flagPers = true;
		this.flagAvance = false;
		effacerOptim();
		listePersonnel2 = new ArrayList<Personnel>();
		listePersonnel3 = new ArrayList<Personnel>();
		RequestContext.getCurrentInstance().update("form:pan1");
		groupLibelleMap = new HashMap<>();
		groupMap = new HashMap<>();
		listeBolOrder = new ArrayList<Boolean>();
		ListeOrder = new ArrayList<String>();
		initDate();
	}

	public void ajouterPath() {
		listePath.add("/resources/rapport/absenceRpt.jrxml");
		listePath.add("/resources/rapport/autorisationRpt.jrxml");
		listePath.add("/resources/rapport/congeRpt.jrxml");
		listePath.add("/resources/rapport/mvtRpt.jrxml");
		listePath.add("/resources/rapport/personnelRpt.jrxml");
		listePath.add("/resources/rapport/pointageRpt.jrxml");
		listePath.add("/resources/rapport/retardRpt.jrxml");
	}

	public void viderStr() {
		strAffiche = new String();
	}

	public void parinit() {
		designationRapport = new String();
		RequestContext.getCurrentInstance().update("enregister");
	}

	public void changeBolAcc1(String s) {
		this.bolAcc = false;
		this.rapportSelected = s;
		this.bolSElect = true;
		remise();
		RequestContext.getCurrentInstance().update("form:pan1");
	}

	public void changeBolAcc2() {
		this.bolAcc = true;
		RequestContext.getCurrentInstance().update("form:groupe");
		remise();
		RequestContext.getCurrentInstance().update("form:pan1");
	}

	public void remplirListRapport() {
		ArrayList l = new ArrayList<GrRapport>();
		GrRapport gr = new GrRapport("/resources/img_rapport/stopwatch.png",
				"Pointage");
		l.add(gr);
		GrRapport gr1 = new GrRapport("/resources/img_rapport/coffee.png",
				"Retard");
		l.add(gr1);
		GrRapport gr2 = new GrRapport("/resources/img_rapport/single_Bed.png",
				"Absence");
		l.add(gr2);
		GrRapport gr3 = new GrRapport("/resources/img_rapport/world.png",
				"Mouvement");
		l.add(gr3);
		listeRapport.add(l);
		l = new ArrayList<GrRapport>();
		gr = new GrRapport("/resources/img_rapport/users.png", "Personnels");
		l.add(gr);
		listeRapport.add(l);
		l = new ArrayList<GrRapport>();
		gr = new GrRapport("/resources/img_rapport/departures.png", "Congé");
		l.add(gr);
		gr1 = new GrRapport("/resources/img_rapport/clock.png", "Autorisation");
		l.add(gr1);
		listeRapport.add(l);
	}

	public void chageindice(int x) {
		this.indice = x;
		idmodeleRapport = listeModele.get(x).getIdmot();
		this.bolSElect = false;
		this.listeRapportpre = rapportService.findRapportPre(idmodeleRapport);
		remplirListe();
	}

	public void remplirListeGrRap() {
		for (ModeleRapport m : listeGroupRapportT) {
			listeGroupRapport.add(m.getDesignationModele());
		}
	}

	public String findDay(int x) {
		switch (x) {
		case 1:
			return "Lundi";
		case 2:
			return "Mardi";
		case 3:
			return "Mercredi";
		case 4:
			return "Jeudi";
		case 5:
			return "Vendredi";
		case 6:
			return "Samedi";
		default:
			return "Dimanche";
		}
	}

	public String findMenth(int x) {
		switch (x) {
		case 1:
			return "Jan";
		case 2:
			return "Fiv";
		case 3:
			return "Mar";
		case 4:
			return "Avr";
		case 5:
			return "Mai";
		case 6:
			return "Juin";
		case 7:
			return "Jui";
		case 8:
			return "Aout";
		case 9:
			return "Seb";
		case 10:
			return "Oct";
		case 11:
			return "Nov";
		default:
			return "Déc";
		}
	}

	public Date increment() {
		Date dx = new Date();
		{
			dx.setTime(dx.getTime() + 86400000);
		}
		return (dx);
	}

	public Date decrement() {
		Date dx = new Date();
		{
			dx.setTime(dx.getTime() - 86400000);
		}
		return (dx);
	}

	public Date incrementX(int x) {
		Date dx = new Date();

		{
			dx.setTime(dx.getTime() + (86400000 * x));
		}
		return (dx);
	}

	public Date decrementX(int x) {
		Date dx = new Date();
		{
			dx.setTime(dx.getTime() - (86400000 * x));
		}
		return (dx);
	}

	public int incrementM() {
		Date d = new Date();
		int x = d.getMonth() + 1;
		if (x == 13) {
			x = 1;
		}
		return x;
	}

	public int decrementM() {
		Date d = new Date();
		int x = d.getMonth() - 1;
		if (x == 0) {
			x = 12;
		}
		return x;
	}

	public List<Date> findWeek() {
		List<Date> l = new ArrayList<Date>();
		Date d = new Date();
		int x = d.getDay();
		Date d1 = decrementX((x + 7) - 1);
		Date d2 = decrementX(x);
		Date d3 = decrementX(x - 1);
		Date d4 = incrementX(7 - x);
		Date d5 = incrementX((7 - x) + 1);
		Date d6 = incrementX((7 - x) + 7);
		l.add(d1);
		l.add(d2);
		l.add(d3);
		l.add(d4);
		l.add(d5);
		l.add(d6);
		return l;
	}

	public Date dernierDate(int i) {
		Date d = new Date();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		int x = calendar.getMaximum(calendar.DAY_OF_MONTH - 1);
		int y = calendar.DATE;
		Date dx = new Date();
		if (i == 1) {
			dx.setTime(dx.getTime() - ((86400000 * y) + 1));
		} else {
			dx.setTime(dx.getTime() + (86400000 * (x - y + 1)));
		}
		return dx;
	}

	public void affectDate(int a1, int a2) {
		this.a1 = a1;
		this.a2 = a2;
		Date d = new Date();
		List<Date> lt = findWeek();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		if (a1 == 1) {
			if (a2 == 1) {
				datedebut = decrement();
				datefin = decrement();
				this.strAffiche = "( Hier )";
			} else if (a2 == 2) {
				datedebut = d;
				datefin = d;
				this.strAffiche = "( Aujourd'hui )";
			} else {
				datedebut = increment();
				datefin = increment();
			}
		} else if (a1 == 2) {
			if (a2 == 1) {
				datedebut = lt.get(0);
				datefin = lt.get(1);
				this.strAffiche = "( Semaine derniére )";
			} else if (a2 == 2) {
				datedebut = lt.get(2);
				datefin = lt.get(3);
				this.strAffiche = "( Semaine en cours )";
			} else {
				datedebut = lt.get(4);
				datefin = lt.get(5);
			}
		} else if (a1 == 3) {
			if (a2 == 1) {
				calendar = new java.util.GregorianCalendar();
				this.strAffiche = "( Mois précedent )";
				calendar.set(calendar.get(calendar.YEAR), decrementM(), 1);
				datedebut = calendar.getTime();
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR), decrementM(),calendar.getMaximum(calendar.DAY_OF_MONTH));
				datefin = calendar.getTime();
			} else if (a2 == 2) {
				calendar = new java.util.GregorianCalendar();
				this.strAffiche = "( mois en cours )";
				calendar.set(calendar.get(calendar.YEAR),
						calendar.get(calendar.MONTH), 1);
				datedebut = (calendar.getTime());
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR),
						calendar.get(calendar.MONTH),
						calendar.getActualMaximum(calendar.DAY_OF_MONTH));
				datefin = (calendar.getTime());
			} else {
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR), incrementM(), 1);
				datedebut = calendar.getTime();
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR), incrementM(),
						calendar.getMaximum(calendar.DAY_OF_MONTH + 1));
				datefin = calendar.getTime();
			}
		} else {
			if (a2 == 1) {
				calendar = new java.util.GregorianCalendar();
				this.strAffiche = "( Année précedent )";
				calendar.set(calendar.get(calendar.YEAR) - 1, calendar.JANUARY,
						1);
				datedebut = calendar.getTime();
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR) - 1,
						calendar.DECEMBER, 31);
				datefin = calendar.getTime();
			} else if (a2 == 2) {
				calendar = new java.util.GregorianCalendar();
				this.strAffiche = "( Année en cours )";
				calendar.set(calendar.get(calendar.YEAR), calendar.JANUARY, 1);
				datedebut = calendar.getTime();
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR), calendar.DECEMBER, 31);
				datefin = calendar.getTime();
			} else {
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR) + 1, calendar.JANUARY,
						1);
				datedebut = calendar.getTime();
				calendar = new java.util.GregorianCalendar();
				calendar.set(calendar.get(calendar.YEAR) + 1,
						calendar.DECEMBER, 31);
				datefin = calendar.getTime();
			}
		}
		RequestContext.getCurrentInstance().update("form:pan1");
	}

	public void genereStringDate() {
		Date d = new Date();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(d);
		List l = findWeek();

		calendar = new java.util.GregorianCalendar();
		calendar.set(2014, decrementM(), 1);

		String val1 = "";
		String val2 = "";
		String val3 = "";
		val1 = " " + findDay(decrement().getDay()) + " "
				+ decrement().getDate() + "/" + (d.getMonth() + 1);
		val2 = " " + findDay(d.getDay()) + " " + d.getDate() + "/"
				+ (d.getMonth() + 1);
		val3 = " " + findDay(increment().getDay()) + " "
				+ increment().getDate() + "/" + (d.getMonth() + 1);
		listeVal.add(val1);
		listeVal.add(val2);
		listeVal.add(val3);

		List<Date> lt = findWeek();
		val1 = " " + lt.get(0).getDate() + "/"
				+ findMenth(lt.get(0).getMonth() + 1) + " -> "
				+ lt.get(1).getDate() + "/"
				+ findMenth(lt.get(1).getMonth() + 1);
		val2 = " " + lt.get(2).getDate() + "/"
				+ findMenth(lt.get(2).getMonth() + 1) + " -> "
				+ lt.get(3).getDate() + "/"
				+ findMenth(lt.get(3).getMonth() + 1);
		val3 = " " + lt.get(4).getDate() + "/"
				+ findMenth(lt.get(4).getMonth() + 1) + " -> "
				+ lt.get(5).getDate() + "/"
				+ findMenth(lt.get(5).getMonth() + 1);
		listeVal.add(val1);
		listeVal.add(val2);
		listeVal.add(val3);

		val1 = findMenth(decrementM() + 1);
		val2 = findMenth(d.getMonth() + 1);
		val3 = findMenth(incrementM() + 1);
		listeVal.add(val1);
		listeVal.add(val2);
		listeVal.add(val3);

		val1 = " " + (calendar.get(calendar.YEAR) - 1);
		val2 = " " + (calendar.get(calendar.YEAR));
		val3 = " " + (calendar.get(calendar.YEAR) + 1);
		listeVal.add(val1);
		listeVal.add(val2);
		listeVal.add(val3);
	}

	public void constructDate() {
		categories = new ArrayList<SelectItem>();
		SelectItemGroup group1 = new SelectItemGroup("Group 1");
		SelectItemGroup group2 = new SelectItemGroup("Group 2");
		SelectItemGroup group3 = new SelectItemGroup("Group 3");

		SelectItem option11 = new SelectItem("Option 1.1", "Option 1.1");
		SelectItem option12 = new SelectItem("Option 1.2", "Option 1.2");
		SelectItem option13 = new SelectItem("Option 1.3", "Option 1.3");

		SelectItem option21 = new SelectItem("Option 2.1", "Option 2.1");
		SelectItem option22 = new SelectItem("Option 2.2", "Option 2.2");
		SelectItem option23 = new SelectItem("Option 2.3", "Option 2.3");

		SelectItem option31 = new SelectItem("Option 3.1", "Option 3.1");
		SelectItem option32 = new SelectItem("Option 3.2", "Option 3.2");
		SelectItem option33 = new SelectItem("Option 3.3", "Option 3.3");

		group1.setSelectItems(new SelectItem[] { option11, option12, option13 });
		group2.setSelectItems(new SelectItem[] { option21, option22, option23 });
		group3.setSelectItems(new SelectItem[] { option31, option32, option33 });

		categories.add(group1);
		categories.add(group2);
		categories.add(group3);
	}

	public void chargerIdpersonnel() {
		for (int i = 0; i < listePersonnel3.size(); i++) {
			listeIdP.add(listePersonnel3.get(i).getIdper());
		}
	}

	public void collect() {
		try {
			this.heararchiOptim = "";
			if (listePersonnel2.size() > 0) {
				for (int i = 0; i < listePersonnel2.size() - 1; i++) {
					for (int j = i + 1; j < listePersonnel2.size(); j++) {
						if (listePersonnel2.get(i) == listePersonnel2.get(j)) {
							listePersonnel2.remove(j);
							j--;
						}
					}
				}
				for (int i = 0; i < listePersonnel2.size(); i++) {
					this.heararchiOptim = this.heararchiOptim
							+ listePersonnel2.get(i).getNom() + " "
							+ listePersonnel2.get(i).getPrenom() + " ; ";
					RequestContext.getCurrentInstance().update("form:str1");
				}
				for (int i = 0; i < listePersonnel2.size(); i++) {
					listePersonnel3.add(listePersonnel2.get(i));
				}
				listePersonnel2 = new ArrayList<Personnel>();
				chargerIdpersonnel();
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(
					"ATTENTION! vous n'avez pas inserer des personnels");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			flagAff = false;
			this.flagPers = false;
			RequestContext.getCurrentInstance().update("form:messaget");

			e.getMessage();
		}
	}

	public void collect2() {
		for (int i = 0; i < listePersonnel2.size(); i++) {
			listePersonnel3.add(listePersonnel2.get(i));
		}
		for (int i = 0; i < listePersonnel3.size() - 1; i++) {
			for (int j = i + 1; j < listePersonnel3.size(); j++) {
				if (listePersonnel3.get(i) == listePersonnel3.get(j)) {
					listePersonnel3.remove(j);
					j--;
				}
			}
		}
		this.heararchiOptim = "";
		for (int i = 0; i < listePersonnel3.size(); i++) {
			this.heararchiOptim = this.heararchiOptim
					+ listePersonnel3.get(i).getNom() + " "
					+ listePersonnel3.get(i).getPrenom() + " ; ";
			RequestContext.getCurrentInstance().update("form:str1");
		}
		listePersonnel2 = new ArrayList<Personnel>();
		chargerIdpersonnel();
	}

	public void permut2(int indice) {
		if (indice == 1) {
			this.flagAff = true;
			this.flagPers = false;
		} else if (indice == 2) {
			this.flagAff = false;
			this.flagPers = true;
			listePersonnel2 = new ArrayList<Personnel>();
		} else {
			this.flagAff = true;
			this.flagPers = true;
			effacerOptim();
			listePersonnel2 = new ArrayList<Personnel>();
			listePersonnel3 = new ArrayList<Personnel>();
		}
		this.heararchiOptim = "";
	}

	public void permut3() {
		this.flagAvance = !this.flagAvance;
		RequestContext.getCurrentInstance().update("form:legendAvance");
	}

	// *-*--*-*-*-*-
	// a verier groupage par direction/service/equipe
	// *-*-*-*-*-*--
	public void chargerListegrouOr() {

		grouOr[0][0] = "Catégorie Horaire";
		grouOr[0][1] = "categorie.`desigCat`";
		grouOr[1][0] = "Contrat";
		grouOr[1][1] = "personnel.`typeContrat`";
		grouOr[2][0] = "Jour";
		grouOr[2][1] = "pointage.`jour`";
		grouOr[3][0] = "Matricule";
		grouOr[3][1] = "personnel.`matricule`";
		grouOr[4][0] = "Modéle Horaire";
		grouOr[4][1] = "sequence.`desigSeq`";

		// for(int i=1;i<listeSociete.size();i++)
		// {grouOr[4+i][0]=listeSociete.get(i);
		// grouOr[4+i][1]="affiliation.`description`";}

		grouOr[5][0] = "Affiliation";
		grouOr[5][1] = "affiliation.`designation`";
		grouOr[6][0] = "Absent";
		grouOr[6][1] = "pointage.`absent`";
		grouOr[7][0] = "Autorisation";
		grouOr[7][1] = "pointage.`autorisation`";
		remplirGroupOr();
	}

	public String findMot(String s) {// int i=5;
		if (s.equals("Absent")) {
			return ("pointage.`absent`");
		} else if (s.equals("Autorisation")) {
			return ("pointage.`autorisation`");
		} else if (s.equals("Catégorie Horaire")) {
			return ("categorie.`desigCat`");
		} else if (s.equals("Contrat")) {
			return ("personnel.`typeContrat`");
		} else if (s.equals("Jour")) {
			if (this.groupSelected.equals("Congé")) {
				return ("conge.`debut`");
			} else if (this.groupSelected.equals("Personnels")) {
				return ("personnel.`debutContrat`");
			} else if (rapportSelected.equals("Mouvement")) {
				return ("mvt.`jour`");
			} else {
				return ("pointage.`jour`");
			}
		} else if (s.equals("Matricule")) {
			return ("personnel.`matricule`");
		} else if (s.equals("Modéle Horaire")) {
			return ("sequence.`desigSeq`");
		} else if (s.equals("Affiliation")) {
			return ("affiliation.`designation`");
		} else {
			// while(i<8){
			// if(s.equals(grouOr[i][0])){System.out.println(grouOr[i][1]);
			// return grouOr[i][1];}}
			return "ERRUUUUUUUUUUUR !";
		}
	}

	public void remplirGroupOr() {
		groupOrAffich = new ArrayList<String>();
		groupOrPrefix = new ArrayList<String>();
		int x;
		if (this.groupSelected.equals("Congé")) {
			x = 5;
		} else {
			x = 7;
		}
		for (int i = 0; i < x; i++) {
			groupOrAffich.add(grouOr[i][0]);
		}
		for (int i = 0; i < x; i++) {
			groupOrPrefix.add(grouOr[i][1]);
		}
		System.out.println("insertionterminer" + x);
		RequestContext.getCurrentInstance().update("form:data:panGroup");
		RequestContext.getCurrentInstance().update("form:data:selectGroup");
		RequestContext.getCurrentInstance().update("form:data:panalGroup");
	}

	public List<Personnel> completePersonnel(String s) {
		List<Personnel> suggestions = new ArrayList<Personnel>();
		for (Personnel p : listePersonnel) {
			String x;
			x = p.getNom();
			x = x.toLowerCase();
			s = s.toLowerCase();
			if (x.startsWith(s)) {
				suggestions.add(p);
			}
		}
		return suggestions;
	}

	public void ajouterGroupOr() {
		listegroupOrSelectionne.add("||Nouveau||");
		listeBolValeur.add(true);
		listeBolGroupOr.add(false);
		listeBolSaut.add(false);
		listeBolAjour.add(false);
	}

	public void insertBol(List<Boolean> l, boolean b) {
		l.add(false);
		int i = l.size() - 2;
		l.set(i + 1, l.get(i));
		l.set(i, b);
	}

	public int findInlist(String s) {
		for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
			if (listegroupOrSelectionne.get(i).equals(s)) {
				return i;
			}
		}
		return 99;
	}

	public int nbOccurence(String s) {
		int x = 0;
		for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
			if (listegroupOrSelectionne.get(i).equals(s)) {
				x++;
			}
		}
		return x;
	}

	public int nbFalse() {
		int x = 0;
		for (int i = 0; i < this.listeBolGroupOr.size(); i++) {
			if (this.listeBolGroupOr.get(i) == false) {
				x++;
			}
		}
		return x;
	}

	public void testana() {
		System.out.println("test reussi");
	}

	public void ajouterGroupOrVal(int x, String s) {
		System.out.println("niveau 1");
		if (s.equals("||Nouveau||")) {
			System.out.println("niveau 2");
			if (listegroupOrSelectionne.size() > 6) {
				FacesMessage msg = new FacesMessage(
						"Vous allez depasser le ne nomre Max d'ajout, le nombre est limité a '6'");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().update("form:messaget");
			} else {
				if (nbOccurence(this.strGrouOr) == 1) {
					System.out.println("niveau 3");
					FacesMessage msg = new FacesMessage(
							"Vous avez deja ajouter l'attribut '"
									+ this.strGrouOr);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					RequestContext.getCurrentInstance().update("form:messaget");
				} else if (nbOccurence(this.strGrouOr) == 0) {
					System.out.println("niveau 4");
					if (listeBolGroupOr.size() == 1) {
						listegroupOrSelectionne.add("new");
						int i = listegroupOrSelectionne.size() - 2;
						listegroupOrSelectionne.set(i + 1,
								listegroupOrSelectionne.get(i));
						listegroupOrSelectionne.set(i, this.strGrouOr);
						this.groupOrAffich.remove(this.strGrouOr);
						insertBol(listeBolValeur, false);
						insertBol(listeBolGroupOr, false);
						insertBol(listeBolSaut, false);
						insertBol(listeBolAjour, false);
						this.strGrouOr = null;
					}

					else if (listeBolGroupOr.get(listeBolGroupOr.size() - 2) == false) {
						listegroupOrSelectionne.add("new");
						int i = listegroupOrSelectionne.size() - 2;
						listegroupOrSelectionne.set(i + 1,
								listegroupOrSelectionne.get(i));
						listegroupOrSelectionne.set(i, this.strGrouOr);
						this.groupOrAffich.remove(this.strGrouOr);
						insertBol(listeBolValeur, false);
						if (nbFalse() >= 5) {
							insertBol(listeBolGroupOr, true);
						} else {
							insertBol(listeBolGroupOr, false);
							insertBol(listeBolSaut, false);
							insertBol(listeBolAjour, false);
							this.strGrouOr = null;
						}
					} else {
						listegroupOrSelectionne.add("new");
						int i = listegroupOrSelectionne.size() - 2;
						listegroupOrSelectionne.set(i + 1,
								listegroupOrSelectionne.get(i));
						listegroupOrSelectionne.set(i, this.strGrouOr);
						this.groupOrAffich.remove(this.strGrouOr);

						insertBol(listeBolValeur, false);
						insertBol(listeBolGroupOr, true);
						insertBol(listeBolSaut, false);
						insertBol(listeBolAjour, false);
						this.strGrouOr = null;
					}
				} else {
					FacesMessage msg = new FacesMessage(
							"Vous ne pouvé pas ajouter l'attribut '"
									+ this.strGrouOr + "' une autre fois");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					RequestContext.getCurrentInstance().update("form:messaget");
				}
			}
		} else {
			// en cours
			if (nbOccurence(this.strGrouOr) == 0) {
				listegroupOrSelectionne.set(listegroupOrSelectionne.indexOf(s),
						strGrouOr);
				this.strGrouOr = null;
				// this.groupOrAffich.add(this.listegroupOrSelectionne.get(x));
				this.groupOrAffich.remove(this.strGrouOr);
				listeBolValeur.set(x, false);
				this.groupOrAffich = new ArrayList<String>();
				remplirGroupOr();
				for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
					this.groupOrAffich.remove(listegroupOrSelectionne.get(i));
				}
			}

			else if (nbOccurence(this.strGrouOr) == 1) {
				boolean tBol = listeBolGroupOr.get(findInlist(this.strGrouOr));
				if (tBol == false) {
					if (x == 0) {
						if (listeBolGroupOr.get(x + 1) == false) {
							listeBolValeur.set(x, false);
							FacesMessage msg = new FacesMessage(
									"Impossible de changer la valeur à '"
											+ this.strGrouOr + "'");
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							RequestContext.getCurrentInstance().update(
									"form:messaget");
							this.strGrouOr = null;
						} else {
							// ok avec true
							listegroupOrSelectionne.set(
									listegroupOrSelectionne.indexOf(s),
									strGrouOr);
							this.strGrouOr = null;
							listeBolGroupOr.set(x, true);
							listeBolValeur.set(x, false);

							this.groupOrAffich = new ArrayList<String>();
							remplirGroupOr();
							for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
								this.groupOrAffich
										.remove(listegroupOrSelectionne.get(i));
							}
						}
					} else if (x == listegroupOrSelectionne.size() - 2) {
						// ok avec true
						// this.groupOrAffich.add(s);
						listegroupOrSelectionne.set(
								listegroupOrSelectionne.indexOf(s), strGrouOr);
						this.strGrouOr = null;
						listeBolGroupOr.set(x, true);
						listeBolValeur.set(x, false);

						this.groupOrAffich = new ArrayList<String>();
						remplirGroupOr();
						for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
							this.groupOrAffich.remove(listegroupOrSelectionne
									.get(i));
						}
					} else {
						if (listeBolGroupOr.get(x + 1) == false) {
							listeBolValeur.set(x, false);
							FacesMessage msg = new FacesMessage(
									"Impossible de changer la valeur à '"
											+ this.strGrouOr + "'");
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							RequestContext.getCurrentInstance().update(
									"form:messaget");
							this.strGrouOr = null;
						} else {
							// ok avec true
							listegroupOrSelectionne.set(
									listegroupOrSelectionne.indexOf(s),
									strGrouOr);
							this.strGrouOr = null;
							listeBolGroupOr.set(x, true);
							listeBolValeur.set(x, false);

							this.groupOrAffich = new ArrayList<String>();
							remplirGroupOr();
							for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
								this.groupOrAffich
										.remove(listegroupOrSelectionne.get(i));
							}
						}
					}
				} else {
					if (x == 0) {
						// ok avec false
						listegroupOrSelectionne.set(
								listegroupOrSelectionne.indexOf(s), strGrouOr);
						this.strGrouOr = null;

						listeBolGroupOr.set(x, false);
						listeBolValeur.set(x, false);
					} else if (x == listegroupOrSelectionne.size() - 2) {
						if (listeBolGroupOr.get(x - 1) == true) {
							listeBolValeur.set(x, false);

							FacesMessage msg = new FacesMessage(
									"Impossible de changer la valeur à '"
											+ this.strGrouOr + "'");
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							RequestContext.getCurrentInstance().update(
									"form:messaget");
							this.strGrouOr = null;
						} else {
							// ok avec false
							listegroupOrSelectionne.set(
									listegroupOrSelectionne.indexOf(s),
									strGrouOr);
							this.strGrouOr = null;
							// ---------------------------------------------------------------------------------------
							// INSERTION2
							if (nbFalse() >= 5) {
								FacesMessage msg = new FacesMessage(
										"vous ne pouvez pas ajouter un autre groupe");
								FacesContext.getCurrentInstance().addMessage(
										null, msg);
								RequestContext.getCurrentInstance().update(
										"form:messaget");
							} else {
								listeBolGroupOr.set(x, false);
								listeBolValeur.set(x, false);
							}
						}
					} else {
						if (listeBolGroupOr.get(x - 1) == true) {
							listeBolValeur.set(x, false);
							FacesMessage msg = new FacesMessage(
									"Impossible de changer la valeur à '"
											+ this.strGrouOr + "'");
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							RequestContext.getCurrentInstance().update(
									"form:messaget");
							this.strGrouOr = null;
						} else {
							// ok avec false
							listegroupOrSelectionne.set(
									listegroupOrSelectionne.indexOf(s),
									strGrouOr);
							if (nbFalse() >= 5) {
								FacesMessage msg = new FacesMessage(
										"vous ne pouvez pas ajouter un autre groupe");
								FacesContext.getCurrentInstance().addMessage(
										null, msg);
								RequestContext.getCurrentInstance().update(
										"form:messaget");
							} else {
								listeBolGroupOr.set(x, false);
								listeBolValeur.set(x, false);
								this.strGrouOr = null;
							}
						}
					}
				}
			} else {
				FacesMessage msg = new FacesMessage("Impossible d'ajouter '"
						+ this.strGrouOr + "' une autre fois");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().update("form:messaget");
				listeBolValeur.set(x, false);
				this.strGrouOr = null;
			}
		}
	}

	public void suprim(String s, int indice) {
		this.groupOrAffich.add(s);
		listegroupOrSelectionne.remove(s);
		listeBolValeur.remove(indice);
		listeBolGroupOr.remove(indice);
		listeBolSaut.remove(indice);
		listeBolAjour.remove(indice);
		groupLibelleMap.remove(indice);
		groupMap.remove(indice);
		groupSautPageMap.remove(indice);
	}

	public void changeVal(int x, int y) {
		if (y == 0) {
			listeBolValeur.set(x, !listeBolValeur.get(x));
		} else if (y == 1) {
			if (listeBolGroupOr.get(x) == true && nbFalse() >= 5) {
				FacesMessage msg = new FacesMessage(
						"Vous ne pouvé ajouter que 4 groupe");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().update("form:messaget");
				return;
			}

			if (nbOccurence(listegroupOrSelectionne.get(x)) == 1
					|| nbOccurence(listegroupOrSelectionne.get(x)) == 0) {
				if (listegroupOrSelectionne.size() == 2) {
					listeBolGroupOr.set(x, !listeBolGroupOr.get(x));
					// modif
					if (listeBolGroupOr.get(x) == true) {
						groupLibelleMap.remove(x);
						groupMap.remove(x);
						groupSautPageMap.remove(x);
						listeBolSaut.remove(x);
						listeBolSaut.add(false);
					}
				} else {
					if (x == 0) {
						if (listeBolGroupOr.get(x + 1) == false) {
							FacesMessage msg = new FacesMessage(
									"Vous ne pouvé pas changer l'attribut '"
											+ this.listegroupOrSelectionne
													.get(x)
											+ "' pour cette valeur");
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							RequestContext.getCurrentInstance().update(
									"form:messaget");
						} else {
							listeBolGroupOr.set(x, !listeBolGroupOr.get(x));
							// modif
							if (listeBolGroupOr.get(x) == true) {
								groupLibelleMap.remove(x);
								groupMap.remove(x);
								groupSautPageMap.remove(x);
								listeBolSaut.remove(x);
								listeBolSaut.add(false);
							}
						}
					} else if (x == listegroupOrSelectionne.size() - 2) {
						if (listeBolGroupOr.get(x - 1) == false) {
							listeBolGroupOr.set(x, !listeBolGroupOr.get(x));
							// modif
							if (listeBolGroupOr.get(x) == true) {
								groupLibelleMap.remove(x);
								groupMap.remove(x);
								groupSautPageMap.remove(x);
								listeBolSaut.remove(x);
								listeBolSaut.add(false);
							}
						} else {
							FacesMessage msg = new FacesMessage(
									"Vous ne pouvé pas changer l'attribut '"
											+ this.listegroupOrSelectionne
													.get(x)
											+ "' pour cette valeur");
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							RequestContext.getCurrentInstance().update(
									"form:messaget");
						}
					} else {
						if (listeBolGroupOr.get(x - 1) == true
								|| listeBolGroupOr.get(x + 1) == false) {
							FacesMessage msg = new FacesMessage(
									"Vous ne pouvé pas changer l'attribut '"
											+ this.listegroupOrSelectionne
													.get(x)
											+ "' pour cette valeur");
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							RequestContext.getCurrentInstance().update(
									"form:messaget");
						} else {
							listeBolGroupOr.set(x, !listeBolGroupOr.get(x));
							// modif
							if (listeBolGroupOr.get(x) == true) {
								groupLibelleMap.remove(x);
								groupMap.remove(x);
								groupSautPageMap.remove(x);
								listeBolSaut.remove(x);
								listeBolSaut.add(false);
							}
						}
					}
				}
			} else {
			}
		} else if (y == 2) {
			listeBolSaut.set(x, !listeBolSaut.get(x));
		} else if (y == 3) {
			listeBolAjour.set(x, !listeBolAjour.get(x));
		} else {
			System.out.println("ERREUR DE SAISIE");
		}
	}

	public String returnSuffix(String s) {
		if (s.equals("absent")) {/* this.prefix.add("pointage.`absent`"); */
			return "pointage.`absent`";
		} else if (s.equals("autorisation")) {/*
											 * this.prefix.add(
											 * "pointage.`autorisation`");
											 */
			return "pointage.`autorisation`";
		} else if (s.equals("hSupHrMn")) {/*
										 * this.prefix.add("pointage.`hSupHrMn`")
										 * ;
										 */
			return "pointage.`hSupHrMn`";
		} else if (s.equals("jour")) {/* this.prefix.add("pointage.`jour`"); */
			return "pointage.`jour`";
		} else if (s.equals("retard")) {/*
										 * this.prefix.add("pointage.`retardHrMn`"
										 * );
										 */
			return "pointage.`retardHrMn`";
		} else if (s.equals("categoriehoraire")) {/*
												 * this.prefix.add(
												 * "categorie.`desigCat`");
												 */
			return "categorie.`desigCat`";
		} else if (s.equals("typeContrat")) {/*
											 * this.prefix.add(
											 * "personnel.`typeContrat`");
											 */
			return "personnel.`typeContrat`";
		} else if (s.equals("debutContrat")) {/*
											 * this.prefix.add(
											 * "personnel.`debutContrat`");
											 */
			return "personnel.`debutContrat`";
		} else if (s.equals("finContrat")) {/*
											 * this.prefix.add(
											 * "personnel.`finContrat`");
											 */
			return "personnel.`finContrat`";
		} else if (s.equals("matricule")) {/*
											 * this.prefix.add(
											 * "personnel.`matricule`");
											 */
			return "personnel.`matricule`";
		} else if (s.equals("modeleHoraire")) {/*
												 * this.prefix.add(
												 * "sequence.`desigSeq`");
												 */
			return "sequence.`desigSeq`";
		} else {/* this.prefix.add("ERREUUUR !"); */
			return "ERREUUUR";
		}
	}

	public void remplirListe() {
		liste1 = new ArrayList<String>();
		System.out.println("tabele modifier ");
		liste1.add("matricule");
		liste1.add("modeleHoraire");
		liste1.add("categoriehoraire");

		if (this.groupSelected.equals("Congé")) {
			liste1.add("typeContrat");
			liste1.add("debutContrat");
			liste1.add("finContrat");
			liste1.add("jour");
		}

		else if (this.groupSelected.equals("Pointage")) {
			liste1.add("typeContrat");
			liste1.add("debutContrat");
			liste1.add("finContrat");
			liste1.add("jour");
			liste1.add("absent");
			liste1.add("autorisation");
			liste1.add("hSupHrMn");
			liste1.add("retard");
		}

		inter.add("=");
		liste2.add(inter);

		inter = new ArrayList<String>();
		inter.add("=");
		inter.add("<>");
		liste2.add(inter);

		inter = new ArrayList<String>();
		inter.add("=");
		inter.add("<>");
		inter.add("<");
		inter.add(">");
		inter.add("<=");
		inter.add(">=");
		liste2.add(inter);
	}

	public boolean trouver(TreeNode x) {
		for (int i = 0; i < selectedNodes.length; i++) {
			if (selectedNodes[i].equals(x)) {
				return true;
			}
		}
		return false;
	}

	public void effacerOptim() {
		this.selectedNodes = null;
		this.heararchiOptim = "";
		Affiliation h = getAffiliationService().getRoot();
		toor = new CheckboxTreeNode("root", null);
		toor.setExpanded(true);
		displayRoot(h, toor, true);
		RequestContext.getCurrentInstance().update("form:str1");
		RequestContext.getCurrentInstance().update("form:tree");
	}

	public void rechargefiltre() {
		rechargerPersAffil(rapport);
		editDate();
		chargerregle();
		chargerGroupOr();
		this.bolR = true;
		listeStr2 = new ArrayList<ArrayList<Affiliation>>();
		societes = new ArrayList<Affiliation>();
		listeStr = new ArrayList<String>();
		listeCMD = new ArrayList<String>();

		RequestContext.getCurrentInstance().update("form:pan1");
		RequestContext.getCurrentInstance().update("form:legendAvance");
		RequestContext.getCurrentInstance().update("form:generalPanel");
		RequestContext.getCurrentInstance().update("form:dataGlob");
	}

	// recharger affiliation/Personnel dans les liste
	// ListeAffiliation2;ListePersonnel
	public int rechargerPersAffil(Rapport rapport)

	{
		listeAffiliation3 = new ArrayList<Affiliation>();
		listeAffiliation = new ArrayList<Affiliation>();
		System.out.println("executée !!");
		listeAffiliation2 = new ArrayList<Affiliation>();
		listeRR = relationRapportService.findByRapport(rapport);
		heararchiOptim = "";
		if (listeRR.size() == 0) {
			return 0;
		} else if (listeRR.get(0).getType().equals("affiliation")) {
			listeRR = relationRapportService.findByRapport(rapport);
			int val = 0;
			listeId = new ArrayList<Integer>();
			for (int i = 0; i < listeRR.size(); i++) {
				val = listeRR.get(i).getIdPersAff();
				listeId.add(val);
				Affiliation a = affiliationService.getByid(val);
				listeAffiliation.add(a);
				if (listeRR.get(i).getIndiceSelection() == true) {
					listeAffiliation3.add(a);
				}
			}
			this.filtre = "";
			for (int i = 0; i < listeAffiliation3.size(); i++) {
				heararchiOptim = heararchiOptim
						+ listeAffiliation3.get(i).getDesignation() + ",";
				if (i < 3) {
					this.filtre = this.filtre + listeAffiliation3.get(i) + ",";
				}
				if (i == 3) {
					this.filtre = this.filtre + "...";
				}
			}
			this.flagAff = true;
			this.flagPers = false;
			RequestContext.getCurrentInstance().update("form:panelg1");
			// il manque le chargement de la liste des id afiliation a imprimer
			// ------------------------------------
			return 1;
		} else {
			listeRR = relationRapportService.findByRapport(rapport);
			listePersonnel2 = new ArrayList<Personnel>();
			listePersonnel3 = new ArrayList<Personnel>();
			int val = 0;
			listeIdP = new ArrayList<Integer>();
			for (int i = 0; i < listeRR.size(); i++) {
				val = listeRR.get(i).getIdPersAff();
				listeIdP.add(val);
				Personnel a = personnelService.getByid(val);
				listePersonnel2.add(a);
				if (listeRR.get(i).getIndiceSelection() == true) {
					listePersonnel3.add(a);
				}
			}
			for (int i = 0; i < listePersonnel3.size(); i++) {
				heararchiOptim = heararchiOptim
						+ listePersonnel3.get(i).getNom() + " "
						+ listePersonnel3.get(i).getPrenom() + ",";
			}
			this.flagAff = false;
			this.flagPers = true;
			RequestContext.getCurrentInstance().update("form:panelg1");
			// /// il manque le charge ment de la liste a imprimer
			// ---------------------------------------------------------
			return 2;
		}
	}

	public void editDate() {
		if (this.rapport == null) {
		} else if (this.rapport.getPeriode() == 0
				&& this.rapport.getIndication() == 0) {
			this.datedebut = rapport.getDateDebut();
			this.datefin = rapport.getDateFin();
		} else {
			affectDate(rapport.getPeriode(), rapport.getIndication());
		}
	}

	public void chargerregle() {
		ListeFormule = new ArrayList<String>();
		List<Regles> listeR = new ArrayList<Regles>();
		if (this.rapport == null) {
		} else {
			listeR = reglesService.findWithRapport(rapport);
			if (listeR.size() == 0) {
				this.flagtable = false;
			} else {
				for (int i = 0; i < listeR.size(); i++) {
					ListeFormule.add(listeR.get(i).getchamps());
				}
				this.flagtable = true;
				this.flagAvance = true;
			}
		}
	}

	public void chargerGroupOr() {
		listegroupOrSelectionne = new ArrayList<String>();
		listeBolGroupOr = new ArrayList<Boolean>();
		listeBolOrder = new ArrayList<Boolean>();
		listeBolSaut = new ArrayList<Boolean>();
		listeBolValeur = new ArrayList<Boolean>();
		List<GroupOrder> listeGR = new ArrayList<GroupOrder>();
		if (this.rapport == null) {
		} else {
			listeGR = groupOrderService.findByRapport(rapport);
			if (listeGR.size() == 0) {
				System.out.println("la liste est vide");
			} else {
				for (int i = 0; i < listeGR.size(); i++) {
					listegroupOrSelectionne
							.add(listeGR.get(i).getDesignation());
					listeBolValeur.add(false);
					listeBolGroupOr.add(listeGR.get(i).getGroupOrder());
					listeBolSaut.add(listeGR.get(i).getEtat());
					listeBolAjour.add(false);
				}
				ajouterGroupOr();
			}
		}
	}

	// retourner la liste optemiser des affiliation slectionner
	public void optimListe() {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		Affiliation affil = new Affiliation();
		for (int i = 0; i < selectedNodes.length; i++) {
			nodes.add(selectedNodes[i]);

		}

		for (int i = nodes.size() - 1; i >= 0; i--) {
			if (nodes.get(i).getParent().getType().equals("r")) {
			} else if (trouver(nodes.get(i).getParent())) {
				nodes.remove(i);
			}
		}
		this.heararchiOptim = "";
		this.filtre = "";
		for (int i = 0; i < nodes.size(); i++) {
			affil = (Affiliation) nodes.get(i).getData();
			this.listeAffiliation2.add(affil);
			this.heararchiOptim = heararchiOptim + nodes.get(i);

			if (i < 3) {
				this.filtre = this.filtre + nodes.get(i) + ",";
			}
			if (i == 3) {
				this.filtre = this.filtre + "...";
			}
			if (i != nodes.size() - 1) {
				this.heararchiOptim = this.heararchiOptim + " ,";
			}

		}
		for (int i = 0; i < listeAffiliation2.size(); i++) {
			System.out.println(listeAffiliation2.get(i).getDesignation());
		}

		RequestContext.getCurrentInstance().update("form:str1");

	}

	// fct pour gider le bouton ajouter
	public void modifAjout() {
		this.flagAjout = false;
		RequestContext.getCurrentInstance().update("form:panel");
		if (this.champ == null || this.condition == null || this.valeur == null) {
			this.flagAjout = false;
		} else {
			this.flagAjout = true;
		}
		RequestContext.getCurrentInstance().update("form:panel2");
	}

	public void modifAjout2() {
		if (this.champ == null || this.condition == null) {
			this.flagAjout = false;
		} else {
			this.flagAjout = true;
		}
		RequestContext.getCurrentInstance().update("form:panel2");
	}

	// fct pour remplir les formule dans un table
	public void ajouterFilter() {
		String sm = "";
		this.flagPanel = true;
		RequestContext.getCurrentInstance().update("form:panel1");
		if (this.bol2 == true) {
			this.valeur = dateFormat.format(dateFilter);
		} else if (this.bol4 == true) {
			this.valeur = heureFormat.format(houreFilter);
		}
		if (this.valeur.equals("oui")) {
			this.valeur = "true";
		} else if (this.valeur.equals("non")) {
			this.valeur = "false";
		}
		String s = returnSuffix(this.champ) + " " + this.condition + " '"
				+ this.valeur + "'";
		this.ListeFormule.add(s);
		this.flagtable = true;
		this.flagUnion = true;
		this.flagAjout = false;
		this.valeur = null;
		this.champ = null;
		this.dateFilter = null;
		this.houreFilter = null;
		this.condition = null;
		this.flagUnion = true;
		RequestContext.getCurrentInstance().update("form:formule");
		RequestContext.getCurrentInstance().update("form:panel2");
	}

	public List<String> returnCondition(String s) {
		if (s.equals("absent")) {
			return (liste2.get(0));
		} else if (s.equals("autorisation")) {
			return (liste2.get(0));
		} else if (s.equals("categoriehoraire")) {
			return (liste2.get(1));
		} else if (s.equals("typeContrat")) {
			return (liste2.get(1));
		} else if (s.equals("debutContrat")) {
			return (liste2.get(2));
		} else if (s.equals("finContrat")) {
			return (liste2.get(2));
		} else if (s.equals("hSupHrMn")) {
			return (liste2.get(2));
		} else if (s.equals("jour")) {
			return (liste2.get(2));
		} else if (s.equals("matricule")) {
			return (liste2.get(2));
		} else if (s.equals("modeleHoraire")) {
			return (liste2.get(1));
		} else if (s.equals("retard")) {
			return (liste2.get(2));
		} else {
			return (liste2.get(0));
		}
	}

	// fct supprimer une regle de la liste des regle
	public void removeregle(String s) {
		this.ListeFormule.remove(s);
		RequestContext.getCurrentInstance().update("form:formule");
		if (this.ListeFormule.size() == 0) {
			this.flagUnion = false;
			this.flagtable = false;
			RequestContext.getCurrentInstance().update("form:panel2");
			RequestContext.getCurrentInstance().update("form:formule");
		} else if (this.ListeFormule.get(ListeFormule.size() - 1).equals(
				"UNION")) {
			this.ListeFormule.remove(ListeFormule.size() - 1);
			if (this.ListeFormule.size() == 0) {
				this.flagUnion = false;
				this.flagtable = false;
				this.valeur = null;
				this.champ = null;
				this.dateFilter = null;
				this.houreFilter = null;
				this.condition = null;
			}
		}
		RequestContext.getCurrentInstance().update("form:panel2");
		RequestContext.getCurrentInstance().update("form:formule");
	}

	public void union() {
		this.ListeFormule.add("UNION");
		this.flagUnion = false;
		this.valeur = null;
		this.champ = null;
		this.dateFilter = null;
		this.houreFilter = null;
		this.condition = null;
		RequestContext.getCurrentInstance().update("form:panel2");
	}

	public void aff() {
		this.flagAjout = false;
		RequestContext.getCurrentInstance().update("form:panel");
		this.valeur = null;
		this.liste21 = returnCondition(this.champ);
		remplirlite3(this.champ);
		RequestContext.getCurrentInstance().update("form:panel1");
		RequestContext.getCurrentInstance().update("form:list3");
		RequestContext.getCurrentInstance().update("form:inputTxt");
	}

	public void chargeOn() {
		this.liste3 = new ArrayList<String>();
		this.liste3.add("oui");
		this.liste3.add("non");
	}

	public void chargeCatHoraire() {
		this.liste3 = new ArrayList<String>();
		for (int i = 0; i < listeCat.size(); i++) {
			this.liste3.add(listeCat.get(i).getDesigCat());
		}
	}

	public void chargerType() {
		liste3 = new ArrayList<String>();
		liste3.add("SIVP");
		liste3.add("SIVP2");
		liste3.add("CDI");
		liste3.add("CDD");
		liste3.add("Stage");
	}

	public void chargerSequence() {
		liste3 = new ArrayList<String>();
		for (int i = 0; i < listeSequence.size(); i++) {
			liste3.add(listeSequence.get(i).getDesigSeq());
		}
	}

	public void remplirlite3(String s) {
		if (s.equals("absent")) {
			chargeOn();
			bol1 = true;
			bol2 = false;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("autorisation")) {
			chargeOn();
			bol1 = true;
			bol2 = false;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("categoriehoraire")) {
			chargeCatHoraire();
			bol1 = true;
			bol2 = false;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("typeContrat")) {
			chargerType();
			bol1 = true;
			bol2 = false;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("debutContrat")) {
			bol1 = false;
			bol2 = true;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("finContrat")) {
			;
			bol1 = false;
			bol2 = true;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("hSupHrMn")) {
			;
			bol1 = false;
			bol2 = false;
			bol3 = false;
			bol4 = true;
		} else if (s.equals("jour")) {
			;
			bol1 = false;
			bol2 = true;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("matricule")) {
			;
			bol1 = false;
			bol2 = false;
			bol3 = true;
			bol4 = false;
		} else if (s.equals("modeleHoraire")) {
			chargerSequence();
			bol1 = true;
			bol2 = false;
			bol3 = false;
			bol4 = false;
		} else if (s.equals("retard")) {
			;
			bol1 = false;
			bol2 = false;
			bol3 = false;
			bol4 = true;
		} else {
			liste3 = new ArrayList<String>();
			bol1 = true;
			bol2 = false;
			bol3 = false;
			bol4 = false;
		}
	}

	public void sauvgardeDonnee() {
		Rapport rapport = new Rapport();
		rapport.setnomRapport(designationRapport);
		rapport.setPeriode(a1);
		rapport.setIndication(a2);
		modele = modeleRapportService.getByid(idmodeleRapport);
		rapport.setidrapportGroup(modele);
		if (this.datedebut != null) {
			rapport.setDateDebut(datedebut);
			rapport.setDateFin(datefin);
		}
		try {
			rapportService.insertRapport(rapport);
			ajouterAffPers(rapport);
			ajouterRegles(rapport);
			ajoutergrouporder(rapport);
			RequestContext.getCurrentInstance().execute(
					"diag_enregistre.hide()");
			FacesMessage msg = new FacesMessage(
					"Rapport enregistré avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext.getCurrentInstance().update("form:messaget");
			RequestContext.getCurrentInstance().update("formheader:datatable:j_idt37");
			parinit();
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur !");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getMessage();
		}
	}

	public Boolean findinList(int val) {
		for (int i = 0; i < listeAffiliation2.size(); i++) {
			if (val == listeAffiliation2.get(i).getIdaff()) {
				return true;
			}
		}
		return false;
	}

	// fct pouajouter affiliation/personnel
	public void ajouterAffPers(Rapport rapport) {
		if (this.flagAff == true && this.flagPers == false) {
			for (int i = 0; i < selectedNodes.length; i++) {
				try {
					Affiliation aff = (Affiliation) selectedNodes[i].getData();

					RelationRapport relationrapport = new RelationRapport();
					relationrapport.setIndiceSelection(findinList(aff
							.getIdaff()));
					relationrapport.setIdPersAff(aff.getIdaff());
					relationrapport.setRapport(rapport);
					relationrapport.setType("affiliation");
					relationRapportService
							.insertRelationRapport(relationrapport);
				} catch (DataAccessException e) {
					FacesMessage msg = new FacesMessage("Erreur !");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();
				}
			}
		} else if (this.flagAff == false && this.flagPers == true) {
			try {
				for (int i = 0; i < listePersonnel3.size(); i++) {
					RelationRapport relationrapport = new RelationRapport();
					relationrapport.setIdPersAff(listePersonnel3.get(i)
							.getIdper());
					if (i < 4) {
						relationrapport.setIndiceSelection(true);
					} else {
						relationrapport.setIndiceSelection(false);
					}
					relationrapport.setRapport(rapport);
					relationrapport.setType("personnel");
					relationRapportService
							.insertRelationRapport(relationrapport);
				}
			} catch (DataAccessException e) {
				FacesMessage msg = new FacesMessage("Erreur !");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				e.getMessage();
			}
		} else {
		}
	}

	// fct savgarde des regles
	public void ajouterRegles(Rapport rapport) {
		System.out.println(ListeFormule.size());
		for (String s : ListeFormule) {
			Regles regles = new Regles();
			regles.setchamps(s);
			regles.setRapport(rapport);
			System.out.println("insertionregleexécutée1 !");
			reglesService.insertRegles(regles);
		}
	}

	// fct sauvgarde des group/order
	public void ajoutergrouporder(Rapport rapport) {
		for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
			if (listegroupOrSelectionne.get(i).equals("||Nouveau||")) {
			} else {
				GroupOrder go = new GroupOrder();
				go.setDesignation(listegroupOrSelectionne.get(i));
				go.setGroupOrder(listeBolGroupOr.get(i));
				go.setEtat(listeBolSaut.get(i));
				go.setRapport(rapport);
				groupOrderService.insertGroupOrder(go);
			}
		}
	}

	// fct qui affiche les donner sauvgarder , dans le filtre
	public void returnfiltre() {
		listeRR = relationRapportService.findByRapport(rapport);
		if (listeRR.size() == 0) {
			return;
		} else if (listeRR.get(0).getType().equals("affiliation")) {
			for (int i = 0; i < listeRR.size(); i++) {
				listeAffiliation2.add(affiliationService.getByid(listeRR.get(i)
						.getIdPersAff()));
			}
		} else {
			// traitement pour les personnel
		}
	}

	public String chargePath() {
		switch (rapportSelected) {
		case "Absence":
			return listePath.get(0);
		case "Autorisation":
			return listePath.get(1);
		case "Congé":
			return listePath.get(2);
		case "Mouvement":
			return listePath.get(3);
		case "Personnels":
			return listePath.get(4);
		case "Pointage":
			return listePath.get(5);
		case "Retard":
			return listePath.get(6);
		default:
			return "null";
		}
	}

	public String chargePeriod() {
		switch (rapportSelected) {
		case "Absence":
			return "pointage.`jour`";
		case "Autorisation":
			return "conge.`debut`";
		case "Congé":
			return "conge.`debut`";
		case "Mouvement":
			return "mvt.`jour`";
		case "Personnels":
			return "personnel.`dateNaissance`";
		case "Pointage":
			return "pointage.`jour`";
		case "Retard":
			return "pointage.`jour`";
		default:
			return "null";
		}
	}

	public String contructString(ArrayList<Affiliation> filtre) {
		String str = "";
		for (int i = 0; i < filtre.size(); i++) {
			if (i < 6) {
				str = str + filtre.get(i).getDesignation() + " ,";
			} else {
				str = str + " ...";
				i = filtre.size();
			}
		}
		return str;
	}

	// fct principal qui geenere la requette
	// ***************************************************************
	public void genere(int valX) {
		System.out.println(listeTouAff.size());

		List<Affiliation> listAffPers = new ArrayList<Affiliation>();
		// ******date
		this.command = new String();
		command = "";
		str1 = "";
		if (this.datedebut == null) {
			affectDate(4, 2);
		}
		this.dat1 = dateFormat.format(datedebut);
		this.dat2 = dateFormat.format(datefin);
		String s = chargePeriod();
		this.str1 = s + " between '" + dat1 + "' AND '" + dat2 + "'";
		this.period = "Du '" + dat1 + "'Au'" + dat2 + "'";
		System.out.println(str1);
		// ******affiliation/group personne

		str2 = "";
		if (this.flagAff == true && this.flagPers == false) {
			System.out.println("*final*");
			System.out.println(listeAffiliation.size());
			for (int i = 0; i < listeAffiliation.size(); i++) {
				System.out.println(listeAffiliation.get(i).getDesignation());
			}

			if (this.bolR == false) {
				listeId = new ArrayList<Integer>();
				listeAffiliation = new ArrayList<Affiliation>();
				listeAffiliation3 = new ArrayList<Affiliation>();
				filtre = "";
				for (int i = 0; i < selectedNodes.length; i++) {
					Affiliation aff = (Affiliation) selectedNodes[i].getData();
					listeAffiliation.add(aff);
					listeId.add(aff.getIdaff());
				}
			}

			// -------------------------------------- groupage par ste
			// {//separation des affiliation par ste//}
			listeStr2 = new ArrayList<ArrayList<Affiliation>>();
			societes = new ArrayList<Affiliation>();
			listeStr = new ArrayList<String>();
			listeCMD = new ArrayList<String>();

			if (listeAffiliation.size() == 0) {
			} else if (listeAffiliation.size() == 1) {
				System.out.println("** listeAffiliation==1 ** ");
				;
				societes.add(findste2(listeAffiliation.get(0)));
				ArrayList<Affiliation> al = new ArrayList<Affiliation>();
				al.add(listeAffiliation.get(0));
				listeStr2.add(al);
			} else {
				System.out.println("** listeAffiliation >1 ** ");
				societes.add(findste2(listeAffiliation.get(0)));
				ArrayList<Affiliation> al = new ArrayList<Affiliation>();
				al.add(listeAffiliation.get(0));
				listeStr2.add(al);
				for (int i = 1; i < listeAffiliation.size(); i++) {
					Affiliation af = listeAffiliation.get(i);
					Affiliation afMer = findste2(af);
					int indiceX = 0;
					for (int j = 0; j < societes.size(); j++) {
						al = new ArrayList<Affiliation>();
						if (societes.get(j).getIdaff() == afMer.getIdaff()) {
							listeStr2.get(j).add(af);
							j = societes.size();
							indiceX = 1;
						}
					}

					if (indiceX == 0) {
						al = new ArrayList<>();
						societes.add(afMer);
						al.add(af);
						listeStr2.add(al);
					}
				}
			}//

			// ---------------------------------------------------------
			listeStr = new ArrayList<>();
			for (int i = 0; i < listeStr2.size(); i++) {
				this.str2 = "affiliation.`idaff` IN (";
				for (int j = 0; j < listeStr2.get(i).size(); j++) {
					if (j != listeStr2.get(i).size() - 1) {
						str2 = str2 + listeStr2.get(i).get(j).getIdaff() + ",";
					} else {
						str2 = str2 + listeStr2.get(i).get(j).getIdaff() + ")";
					}
				}
				listeStr.add(str2);
			}

			/*
			 * for(int i=0;i<listeId.size();i++) { if(i!=listeId.size()-1){
			 * str2=str2+listeId.get(i)+",";}
			 * else{str2=str2+listeId.get(i)+")";} }
			 */
			// remplir la chaine SteAffiche par les ste selectionnné
			SteAffiche = returnstrSte(listeAffiliation);
		} else if (this.flagAff == false && this.flagPers == true) {
			filtre = "";
			this.str2 = "personnel.`idper` IN (";
			for (int i = 0; i < listeIdP.size(); i++) {
				if (i != listeIdP.size() - 1) {
					str2 = str2 + listeIdP.get(i) + ",";
				} else {
					str2 = str2 + listeIdP.get(i) + ")";
				}
			}
			for (int i = 0; i < listePersonnel3.size(); i++) {
				listAffPers.add(personnelService.getcurrentHistorique(
						listePersonnel3.get(i)).getAffiliation());
				if (i < 3) {
					this.filtre = filtre + listePersonnel3.get(i).getNom()
							+ " " + listePersonnel3.get(i).getPrenom() + " ,";
				}
				if (i == 3) {
					this.filtre = filtre + "...";
				} else {
				}
			}
			SteAffiche = returnstrSte(listAffPers);
		} else {
			// TRAITEMENT SUPPLIMENTAIRE SI PAS DE FILTRE
			listeStr2 = new ArrayList<ArrayList<Affiliation>>();
			societes = new ArrayList<Affiliation>();
			listeStr = new ArrayList<String>();
			listeCMD = new ArrayList<String>();
			listeAffiliation = listeTouAff;
			listeAffiliation.remove(0);
			societes.add(findste2(listeAffiliation.get(0)));
			ArrayList<Affiliation> al = new ArrayList<Affiliation>();
			al.add(listeAffiliation.get(0));
			listeStr2.add(al);
			for (int i = 1; i < listeAffiliation.size(); i++) {
				Affiliation af = listeAffiliation.get(i);
				Affiliation afMer = findste2(af);
				int indiceX = 0;
				for (int j = 0; j < societes.size(); j++) {
					al = new ArrayList<Affiliation>();
					if (societes.get(j).getIdaff() == afMer.getIdaff()) {
						listeStr2.get(j).add(af);
						j = societes.size();
						indiceX = 1;
					} else {
					}
				}
				if (indiceX == 0) {
					al = new ArrayList<>();
					societes.add(afMer);
					al.add(af);
					listeStr2.add(al);
				}
			}
			listeStr = new ArrayList<>();

			for (int i = 0; i < listeStr2.size(); i++) {
				this.str2 = "affiliation.`idaff` IN (";
				for (int j = 0; j < listeStr2.get(i).size(); j++) {
					if (j != listeStr2.get(i).size() - 1) {
						str2 = str2 + listeStr2.get(i).get(j).getIdaff() + ",";
					} else {
						str2 = str2 + listeStr2.get(i).get(j).getIdaff() + ")";
					}
				}
				listeStr.add(str2);
			}
			SteAffiche = returnstrSte(listeAffiliation);

			this.filtre = "tout les afilliations";
		}
		// ******* filtre avancé
		boolean bol = true;
		str3 = "";
		if (ListeFormule.size() == 0) {
		} else {
			String si = "(";
			for (int i = 0; i < ListeFormule.size(); i++) {
				if (this.ListeFormule.get(i).equals("UNION")) {
					bol = true;
					str3 = str3 + si + ") OR ";
					si = "(";
				} else {
					if (bol == true) {
						si = si + ListeFormule.get(i);
						bol = false;
					} else {
						si = si + " AND " + ListeFormule.get(i);
					}
				}
			}
			str3 = str3 + si + ")";
		}
		System.out.println(str3);

		// ******* group
		int x = 0;
		ListeOrder = new ArrayList<String>();
		listeBolOrder = new ArrayList<Boolean>();
		for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
			if (listegroupOrSelectionne.get(i).equals("||Nouveau||")) {
			} else {
				if (listeBolGroupOr.get(i) == false) {
					groupLibelleMap.put(x, listegroupOrSelectionne.get(i));
					groupMap.put(x, findMot(listegroupOrSelectionne.get(i)));
					groupSautPageMap.put(x, listeBolSaut.get(i));
					x++;
				} else {
					ListeOrder.add(findMot(listegroupOrSelectionne.get(i)));
					listeBolOrder.add(listeBolSaut.get(i));
				}
			}
		}

		String ad = "";
		if (ListeOrder.size() > 0) {
			str4 = "";
			for (int i = 0; i < ListeOrder.size(); i++) {
				if (listeBolOrder.get(i) == false) {
					ad = "ASC";
				} else {
					ad = "DESC";
				}
				if (i == ListeOrder.size() - 1) {
					str4 = str4 + " " + ListeOrder.get(i) + " " + ad;
					ord = str4;
				} else {
					str4 = str4 + " " + ListeOrder.get(i) + " " + ad + ",";
				}
			}
		} else {
			str4 = "";
			this.ord = null;
		}
		int in = 0;
		// //
		ArrayList<String> listeCommand = new ArrayList<String>();
		if (!str1.equals("")) {
			listeCommand.add(str1);
		}
		// if(!str2.equals("")){listeCommand.add(str2);}
		if (!str3.equals("")) {
			listeCommand.add(str3);
		}
		System.out.println("niveau 5");

		// GROUP DE PERSONNE
		if (flagAff == false && flagPers == true) {
			listeCommand.add(str2);
			System.out.println("niveau 6");
		}

		if (listeCommand.size() != 0) {
			for (int i = 0; i < listeCommand.size(); i++) {
				if (i == 0) {
					command = listeCommand.get(0);
				} else {
					command = command + " AND ";
					command = command + listeCommand.get(i);
				}
			}
		} else {
			in = 1;
		}
		System.out.println("niveau 7");

		listeCMD = new ArrayList<>();
		if (flagAff == true && flagPers == false) {
			for (int t = 0; t < listeStr.size(); t++) {
				listeCMD.add(command + " AND " + listeStr.get(t));
			}
		} else if (flagAff == false && flagPers == true) {
			listeCMD.add(command);
			listeAffiliation = new ArrayList<Affiliation>();
			for (int i = 0; i < listePersonnel3.size(); i++) {
				listeAffiliation.add(personnelService
						.findcurrentAffiliation(listePersonnel3.get(i)));
			}

			// fct de test de personnel
			if (listeAffiliation.size() == 0) {
			} else if (listeAffiliation.size() == 1) {
				societes.add(findste2(listeAffiliation.get(0)));
				ArrayList<Affiliation> al = new ArrayList<Affiliation>();
				al.add(listeAffiliation.get(0));
				listeStr2.add(al);
			} else {
				societes.add(findste2(listeAffiliation.get(0)));
				ArrayList<Affiliation> al = new ArrayList<Affiliation>();
				al.add(listeAffiliation.get(0));
				listeStr2.add(al);
				for (int i = 1; i < listeAffiliation.size(); i++) {
					Affiliation af = listeAffiliation.get(i);
					Affiliation afMer = findste2(af);
					int indiceX = 0;
					for (int j = 0; j < societes.size(); j++) {
						al = new ArrayList<Affiliation>();
						if (societes.get(j).getIdaff() == afMer.getIdaff()) {
							listeStr2.get(j).add(af);
							j = societes.size();
							indiceX = 1;
						}
					}
					if (indiceX == 0) {
						al = new ArrayList<>();
						societes.add(afMer);
						al.add(af);
						listeStr2.add(al);
					}
				}
			}

		}
		else{listeCMD.add(command);}
		System.out.println("niveau 8");
		for (int i = 0; i < listeCMD.size(); i++) {
			System.out.println(listeCMD.get(i));
		}
		
		

		//
		/*
		 * System.out.println("##############"); for(int
		 * i=0;i<listeAffiliation.size();i++) {
		 * System.out.println(listeAffiliation.get(i).getDesignation()); }
		 * 
		 * 
		 * for(int i=0;i<societes.size();i++)
		 * {System.out.println(societes.get(i));}
		 * System.out.println("+++++++++++++++"); for(int
		 * i=0;i<listeStr2.size();i++) {System.out.println("_-_-_-_-"); for(int
		 * j=0 ;j<listeStr2.get(i).size();j++) {
		 * System.out.println(listeStr2.get(i).get(j).getDesignation());} }
		 * System.out.println("+++++++++++++++"); for(int
		 * i=0;i<listeStr.size();i++) {System.out.println(listeStr.get(i));}
		 * System.out.println("+++++++++++++++"); for(int
		 * i=0;i<listeCMD.size();i++) {System.out.println(listeCMD.get(i));}
		 * System.out.println("##############");
		 */
		//
		String strx = "";
		// if(listeStr.size()==0 && in==1){}
		//this.code=generate(5);
		//this.code="hello";

		if (listeCMD.size() > 0) {
			strx = "";
			for (int t = 0; t < listeCMD.size(); t++) {

				if (flagPers == true || (flagPers == false && flagAff == false)) {
					strx = filtre;
				} else {
					strx = contructString(listeStr2.get(t));
				}
				this.path = chargePath();
				if (valX == 0) {
					reportingService.previewPdf(path, listeCMD.get(t), ord,	period, strx, groupMap, groupLibelleMap,groupSautPageMap, "FR", societes.get(t)
									.getDesignation(),code);
				} else {
					reportingService.exportToPdf(path, listeCMD.get(t), ord,period, strx, groupMap, groupLibelleMap,groupSautPageMap, "FR", societes.get(t).getDesignation(),currentUser.getLogin(),code);
					//JasperPrintManager.printReport(jp,false);
				}
			}
		}

		System.out.println("-----------------------");
		System.out.println(path);
		System.out.println(command);
		System.out.println(period);
		System.out.println(filtre);
		System.out.println("-----------------------");
		for (int i = 1; i < groupMap.size(); i++) {
			System.out.println(groupMap.get(i));
			System.out.println(groupLibelleMap.get(i));
		}
		System.out.println("------------------------");
		for (int i = 0; i < groupSautPageMap.size(); i++) {
			groupSautPageMap.get(i);
		}
		System.out.println("------------------------");
		System.out.println("--------- STR ----------");
		System.out.println(listeCMD.size());
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
			System.out.println(listegroupOrSelectionne.get(i));
		}
		System.out.println("**************");
		System.out.println(listegroupOrSelectionne.size());
		System.out.println(groupLibelleMap.size());
		System.out.println(groupMap.size());
		System.out.println(groupSautPageMap.size());
		System.out.println(listeBolSaut.size());
		System.out.println(ListeOrder.size());
		System.out.println("*******************");
		for (int i = 0; i < listegroupOrSelectionne.size(); i++) {
			System.out.println(listegroupOrSelectionne.get(i));
		}

		for (int i = 0; i < groupLibelleMap.size(); i++) {
			System.out.println(groupLibelleMap.get(i));
		}

		for (int i = 0; i < groupMap.size(); i++) {
			System.out.println(groupMap.get(i));
		}

		for (int i = 0; i < groupSautPageMap.size(); i++) {
			System.out.println(groupSautPageMap.get(i));
		}

		for (int i = 0; i < listeBolSaut.size(); i++) {
			System.out.println(listeBolSaut.get(i));
		}
	}

	/*
	 * public ArrayList<String> annulOccurence(List<String> listeStr) {
	 * ArrayList<String> liste = new ArrayList<String>(); for(int
	 * i=0;i<listeStr.size();i++) { String s=listeStr.get(i); int indice=0;
	 * for(int j=0;j<liste.size();j++) {
	 * if(ste.equals(listeSte.get(j))){indice=1;} } if(indice==0){liste.add(s);}
	 * }return liste; }
	 */
	

	public String returnstrSte(List<Affiliation> LS) {
		String chaine = "";
		ArrayList<String> listeSte = new ArrayList<String>();
		for (int i = 0; i < LS.size(); i++) {
			String ste = findste(LS.get(i));
			int indice = 0;
			for (int j = 0; j < listeSte.size(); j++) {
				if (ste.equals(listeSte.get(j))) {
					indice = 1;
				}
			}
			if (indice == 0) {
				listeSte.add(ste);
			}
		}
		for (int i = 0; i < listeSte.size(); i++) {
			chaine = chaine + listeSte.get(i) + "\n";
		}
		return chaine;
	}

	public String findste(Affiliation affiliation) {
		Affiliation parent = affiliation;
		while (!parent.getType().equals("So")) {
			parent = parent.getAffiliation();
		}
		return (parent.getDesignation());
	}

	// / fct
	public Affiliation findste2(Affiliation affiliation) {
		Affiliation parent = affiliation;
		while (!parent.getType().equals("So")) {
			parent = parent.getAffiliation();
		}
		return (parent);
	}

	/*
	 * public void displayRoot(Affiliation h, TreeNode root) {
	 * h.setAffiliationChildren(getAffiliationService().listaffiliation(h)); for
	 * (Affiliation a : h.getAffiliationChildren()) { TreeNode node = new
	 * CheckboxTreeNode(a, root); listeTouNodes.add(node);
	 * node.setExpanded(true); displayRoot(a, node); } }
	 * 
	 * public void selectNode(NodeSelectEvent event) { filtredList.clear();
	 * List<TreeNode> listnodes = new ArrayList<TreeNode>(); for (TreeNode node
	 * : getSelectedNodes()) { listnodes.add(node); }
	 * listnodes.removeAll(filtredList); for (TreeNode node : listnodes) { if
	 * ((!filtredList.contains(node)) ||
	 * (!filtredList.contains(node.getParent()))) { if
	 * (listnodes.containsAll(node.getChildren())) {
	 * filtredList.removeAll(node.getChildren()); filtredList.add(node); } else
	 * if (node.getChildren() == null) { filtredList.add(node); } else if
	 * (!filtredList.contains(node.getParent())) { filtredList.add(node); } } }
	 * }
	 */

	public void displayRoot(Affiliation h, TreeNode root, boolean ok) {
		if (ok) {
			h.setAffiliationChildren(getAffiliationService().getAffiliation(
					findAffType()));
			System.out.println("type " + findAffType() + " "
					+ h.getAffiliationChildren().size());
		} else {
			System.out.println("false");
			h.setAffiliationChildren(getAffiliationService().listaffiliation(h));

		}
		for (Affiliation a : h.getAffiliationChildren()) {
			if (containsAffiliation(a)) {
				TreeNode node = new CheckboxTreeNode(a, root);
				node.setExpanded(true);
				displayRoot(a, node, false);
			}

		}
	}

	private boolean containsAffiliation(Affiliation a) {
		List<Affiliation> af = getOperateurService().getopaffiliation(
				currentUser);

		for (Affiliation afli : af) {
			if (afli.getIdaff() == a.getIdaff())
				return true;
		}
		return false;
	}

	private String findAffType()

	{
		List<Affiliation> listaffop = getOperateurService().getopaffiliation(
				currentUser);
		String s = "all";
		if (listaffop.size() > 0) {
			for (Affiliation a : listaffop) {
				if (!listaffop.contains(a.getAffiliation())) {
					s = a.getType();
				}
			}
			return s;
		} else
			return "all";
	}

	public void selectNode(NodeSelectEvent event) {
		filtredList.clear();

		List<TreeNode> listnodes = new ArrayList<TreeNode>();

		for (TreeNode node : getSelectedNodes()) {
			listnodes.add(node);
		}
		listnodes.removeAll(filtredList);
		for (TreeNode node : listnodes) {
			if ((!filtredList.contains(node))
					|| (!filtredList.contains(node.getParent()))) {
				if (listnodes.containsAll(node.getChildren())) {
					filtredList.removeAll(node.getChildren());
					filtredList.add(node);
				} else if (node.getChildren() == null) {
					filtredList.add(node);
				} else if (!filtredList.contains(node.getParent())) {
					filtredList.add(node);
				}
			}
		}
	}

	public void unSelectNode(NodeUnselectEvent event) {
		filtredList.clear();
		List<TreeNode> listnodes = new ArrayList<TreeNode>();

		for (TreeNode node : getSelectedNodes()) {
			listnodes.add(node);
		}
		listnodes.removeAll(filtredList);
		for (TreeNode node : listnodes) {
			if ((!filtredList.contains(node))
					|| (!filtredList.contains(node.getParent()))) {
				if (listnodes.containsAll(node.getChildren())) {
					filtredList.removeAll(node.getChildren());
					filtredList.add(node);
				} else if (node.getChildren() == null) {
					filtredList.add(node);
				} else if (!filtredList.contains(node.getParent())) {
					filtredList.add(node);
				}
			}
		}
	}
	// ******************************************  GENERATION DU NOM DU RAPPORT ******************************************
	public String generate(int length)
	{
		    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
		    String pass = "RPT#";
		    for(int x=0;x<length;x++)
		    {
		       int i = (int)Math.floor(Math.random() * 62); 
		       pass += chars.charAt(i);
		    }
		    
		    
		    Date d= new Date();
			Calendar c= new GregorianCalendar();
			c.setTime(d);	
			pass +="M";
			pass += c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH)+1)+""+(c.get(Calendar.DAY_OF_MONTH)-1);
		   
//			Random r = new Random();
//			int valeur = r.nextInt(9999);
//			pass+=valeur;
		   
		    return pass;
	}

	// ///////////// GETTERS AND SETTERS //////////////////////
	public CalendrierService getCalendrierService() {
		return calendrierService;
	}

	public void setCalendrierService(CalendrierService calendrierService) {
		this.calendrierService = calendrierService;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public AffiliationService getAffiliationService() {
		return affiliationService;
	}

	public void setAffiliationService(AffiliationService affiliationService) {
		this.affiliationService = affiliationService;
	}

	public static DateFormat getDateFormat() {
		return dateFormat;
	}

	public static void setDateFormat(DateFormat dateFormat) {
		RapportBean.dateFormat = dateFormat;
	}

	public List<Affiliation> getListeSte() {
		return listeSte;
	}

	public void setListeSte(List<Affiliation> listeSte) {
		this.listeSte = listeSte;
	}

	public String getSte() {
		return ste;
	}

	public void setSte(String ste) {
		this.ste = ste;
	}

	public String getDat1() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		return getOperateurService().getByLogin(auth.getName()).getLogin();
		return dat1;
	}

	public void setDat1(String dat1) {
		this.dat1 = dat1;
	}

	public String getDat2() {
		return dat2;
	}

	public void setDat2(String dat2) {
		this.dat2 = dat2;
	}

	public Affiliation getSociete() {
		return Societe;
	}

	public void setSociete(Affiliation societe) {
		Societe = societe;
	}

	public TreeNode getToor() {
		return toor;
	}

	public void setToor(TreeNode toor) {
		this.toor = toor;
	}

	public List<TreeNode> getFiltredList() {
		return filtredList;
	}

	public void setFiltredList(List<TreeNode> filtredList) {
		this.filtredList = filtredList;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public List<Affiliation> getListeAffiliation() {
		return listeAffiliation;
	}

	public void setListeAffiliation(List<Affiliation> listeAffiliation) {
		this.listeAffiliation = listeAffiliation;
	}

	public List<Integer> getListeId() {
		return listeId;
	}

	public void setListeId(List<Integer> listeId) {
		this.listeId = listeId;
	}

	public List<String> getListeSociete() {
		return listeSociete;
	}

	public void setListeSociete(List<String> listeSociete) {
		this.listeSociete = listeSociete;
	}

	public List<String> getListe1() {
		return liste1;
	}

	public void setListe1(List<String> liste1) {
		this.liste1 = liste1;
	}

	public List<List<String>> getListe2() {
		return liste2;
	}

	public void setListe2(List<List<String>> liste2) {
		this.liste2 = liste2;
	}

	public List<String> getListe3() {
		return liste3;
	}

	public void setListe3(List<String> liste3) {
		this.liste3 = liste3;
	}

	public CategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public List<Categorie> getListeCat() {
		this.listeCat = new ArrayList<Categorie>();
		this.listeCat = categorieService.findAllCategories();
		return listeCat;
	}

	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
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

	public boolean isBol4() {
		return bol4;
	}

	public void setBol4(boolean bol4) {
		this.bol4 = bol4;
	}

	public List<String> getListe21() {
		return liste21;
	}

	public void setListe21(List<String> liste21) {
		this.liste21 = liste21;
	}

	public String getChamp() {
		return champ;
	}

	public void setChamp(String champ) {
		this.champ = champ;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public List<String> getInter() {
		return inter;
	}

	public void setInter(List<String> inter) {
		this.inter = inter;
	}

	public Date getDateFilter() {
		return dateFilter;
	}

	public void setDateFilter(Date dateFilter) {
		this.dateFilter = dateFilter;
	}

	public Date getHoureFilter() {
		return houreFilter;
	}

	public void setHoureFilter(Date houreFilter) {
		this.houreFilter = houreFilter;
	}

	public boolean isFlagPanel() {
		return flagPanel;
	}

	public void setFlagPanel(boolean flagPanel) {
		this.flagPanel = flagPanel;
	}

	public boolean isFlagUnion() {
		return flagUnion;
	}

	public void setFlagUnion(boolean flagUnion) {
		this.flagUnion = flagUnion;
	}

	public List<String> getListeFormule() {
		return ListeFormule;
	}

	public void setListeFormule(List<String> listeFormule) {
		ListeFormule = listeFormule;
	}

	public String getDat3() {
		return dat3;
	}

	public void setDat3(String dat3) {
		this.dat3 = dat3;
	}

	public static DateFormat getHeureFormat() {
		return heureFormat;
	}

	public static void setHeureFormat(DateFormat heureFormat) {
		RapportBean.heureFormat = heureFormat;
	}

	public boolean isFlagtable() {
		return flagtable;
	}

	public void setFlagtable(boolean flagtable) {
		this.flagtable = flagtable;
	}

	public boolean isFlagAjout() {
		return flagAjout;
	}

	public void setFlagAjout(boolean flagAjout) {
		this.flagAjout = flagAjout;
	}

	public String getHeararchiOptim() {
		return heararchiOptim;
	}

	public void setHeararchiOptim(String heararchiOptim) {
		this.heararchiOptim = heararchiOptim;
	}

	public SequenceService getSequenceService() {
		return sequenceService;
	}

	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	public List<Sequence> getListeSequence() {
		return listeSequence;
	}

	public void setListeSequence(List<Sequence> listeSequence) {
		this.listeSequence = listeSequence;
	}

	public String[][] getGrouOr() {
		return grouOr;
	}

	public void setGrouOr(String[][] grouOr) {
		this.grouOr = grouOr;
	}

	public List<String> getGroupOrAffich() {
		return groupOrAffich;
	}

	public void setGroupOrAffich(List<String> groupOrAffich) {
		this.groupOrAffich = groupOrAffich;
	}

	public List<String> getListegroupOrSelectionne() {
		return listegroupOrSelectionne;
	}

	public void setListegroupOrSelectionne(List<String> listegroupOrSelectionne) {
		this.listegroupOrSelectionne = listegroupOrSelectionne;
	}

	public String getStrGrouOr() {
		return strGrouOr;
	}

	public void setStrGrouOr(String strGrouOr) {
		this.strGrouOr = strGrouOr;
	}

	public List<Boolean> getListeBolValeur() {
		return listeBolValeur;
	}

	public void setListeBolValeur(List<Boolean> listeBolValeur) {
		this.listeBolValeur = listeBolValeur;
	}

	public List<Boolean> getListeBolGroupOr() {
		return listeBolGroupOr;
	}

	public void setListeBolGroupOr(List<Boolean> listeBolGroupOr) {
		this.listeBolGroupOr = listeBolGroupOr;
	}

	public List<Boolean> getListeBolSaut() {
		return listeBolSaut;
	}

	public void setListeBolSaut(List<Boolean> listeBolSaut) {
		this.listeBolSaut = listeBolSaut;
	}

	public List<Boolean> getListeBolAjour() {
		return listeBolAjour;
	}

	public void setListeBolAjour(List<Boolean> listeBolAjour) {
		this.listeBolAjour = listeBolAjour;
	}

	public String getStrPersonnel() {
		return strPersonnel;
	}

	public void setStrPersonnel(String strPersonnel) {
		this.strPersonnel = strPersonnel;
	}

	public List<String> getListeStrPersonnel() {
		return listeStrPersonnel;
	}

	public void setListeStrPersonnel(List<String> listeStrPersonnel) {
		this.listeStrPersonnel = listeStrPersonnel;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public List<Personnel> getListePersonnel() {
		return listePersonnel;
	}

	public void setListePersonnel(List<Personnel> listePersonnel) {
		this.listePersonnel = listePersonnel;
	}

	public Personnel getPersonnelselectionne() {
		return personnelselectionne;
	}

	public void setPersonnelselectionne(Personnel personnelselectionne) {
		this.personnelselectionne = personnelselectionne;
	}

	public boolean isFlagAff() {
		return flagAff;
	}

	public void setFlagAff(boolean flagAff) {
		this.flagAff = flagAff;
	}

	public boolean isFlagPers() {
		return flagPers;
	}

	public void setFlagPers(boolean flagPers) {
		this.flagPers = flagPers;
	}

	public boolean isFlagAvance() {
		return flagAvance;
	}

	public void setFlagAvance(boolean flagAvance) {
		this.flagAvance = flagAvance;
	}

	public List<Personnel> getListePersonnel2() {
		return listePersonnel2;
	}

	public void setListePersonnel2(List<Personnel> listePersonnel2) {
		this.listePersonnel2 = listePersonnel2;
	}

	public List<Personnel> getListePersonnel3() {
		return listePersonnel3;
	}

	public void setListePersonnel3(List<Personnel> listePersonnel3) {
		this.listePersonnel3 = listePersonnel3;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public List<Integer> getListeIdP() {
		return listeIdP;
	}

	public void setListeIdP(List<Integer> listeIdP) {
		this.listeIdP = listeIdP;
	}

	public HashMap<Integer, String> getGroupMap() {
		return groupMap;
	}

	public void setGroupMap(HashMap<Integer, String> groupMap) {
		this.groupMap = groupMap;
	}

	public HashMap<Integer, Boolean> getGroupSautPageMap() {
		return groupSautPageMap;
	}

	public void setGroupSautPageMap(HashMap<Integer, Boolean> groupSautPageMap) {
		this.groupSautPageMap = groupSautPageMap;
	}

	public List<String> getListeOrder() {
		return ListeOrder;
	}

	public void setListeOrder(List<String> listeOrder) {
		ListeOrder = listeOrder;
	}

	public List<Boolean> getListeBolOrder() {
		return listeBolOrder;
	}

	public void setListeBolOrder(List<Boolean> listeBolOrder) {
		this.listeBolOrder = listeBolOrder;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFiltre() {
		return filtre;
	}

	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}

	public ReportingService getReportingService() {
		return reportingService;
	}

	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getGroupOrPrefix() {
		return groupOrPrefix;
	}

	public void setGroupOrPrefix(List<String> groupOrPrefix) {
		this.groupOrPrefix = groupOrPrefix;
	}

	public List<String> getGroupOrPrefixSelectionne() {
		return groupOrPrefixSelectionne;
	}

	public void setGroupOrPrefixSelectionne(
			List<String> groupOrPrefixSelectionne) {
		this.groupOrPrefixSelectionne = groupOrPrefixSelectionne;
	}

	public List<Personnel> getListePersonnel4() {
		return listePersonnel4;
	}

	public void setListePersonnel4(List<Personnel> listePersonnel4) {
		this.listePersonnel4 = listePersonnel4;
	}

	public HashMap<Integer, String> getGroupLibelleMap() {
		return groupLibelleMap;
	}

	public void setGroupLibelleMap(HashMap<Integer, String> groupLibelleMap) {
		this.groupLibelleMap = groupLibelleMap;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public List<SelectItem> getCategories() {
		return categories;
	}

	public void setCategories(List<SelectItem> categories) {
		this.categories = categories;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public List<String> getListeVal() {
		return listeVal;
	}

	public void setListeVal(List<String> listeVal) {
		this.listeVal = listeVal;
	}

	public List<Date> getListeDate() {
		return listeDate;
	}

	public void setListeDate(List<Date> listeDate) {
		this.listeDate = listeDate;
	}

	public ModeleRapportService getModeleRapportService() {
		return modeleRapportService;
	}

	public void setModeleRapportService(
			ModeleRapportService modeleRapportService) {
		this.modeleRapportService = modeleRapportService;
	}

	public ModeleRapport getModele() {
		return modele;
	}

	public void setModele(ModeleRapport modele) {
		this.modele = modele;
	}

	public List<ModeleRapport> getListeModele() {
		return listeModele;
	}

	public void setListeModele(List<ModeleRapport> listeModele) {
		this.listeModele = listeModele;
	}

	public String getDesignationRapport() {
		return designationRapport;
	}

	public void setDesignationRapport(String designationRapport) {
		this.designationRapport = designationRapport;
	}

	public RapportService getRapportService() {
		return rapportService;
	}

	public void setRapportService(RapportService rapportService) {
		this.rapportService = rapportService;
	}

	public int getIdmodeleRapport() {
		return idmodeleRapport;
	}

	public void setIdmodeleRapport(int idmodeleRapport) {
		this.idmodeleRapport = idmodeleRapport;
	}

	public RelationRapportService getRelationRapportService() {
		return relationRapportService;
	}

	public void setRelationRapportService(
			RelationRapportService relationRapportService) {
		this.relationRapportService = relationRapportService;
	}

	public ReglesService getReglesService() {
		return reglesService;
	}

	public void setReglesService(ReglesService reglesService) {
		this.reglesService = reglesService;
	}

	public GroupOrderService getGroupOrderService() {
		return groupOrderService;
	}

	public void setGroupOrderService(GroupOrderService groupOrderService) {
		this.groupOrderService = groupOrderService;
	}

	public List<String> getListeGroupRapport() {
		return listeGroupRapport;
	}

	public void setListeGroupRapport(List<String> listeGroupRapport) {
		this.listeGroupRapport = listeGroupRapport;
	}

	public String getRapportSelected() {
		return rapportSelected;
	}

	public void setRapportSelected(String rapportSelected) {
		this.rapportSelected = rapportSelected;
	}

	public ArrayList<ArrayList<GrRapport>> getListeRapport() {
		return listeRapport;
	}

	public void setListeRapport(ArrayList<ArrayList<GrRapport>> listeRapport) {
		this.listeRapport = listeRapport;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public ArrayList<GrRapport> getListeRapportAff() {
		listeRapportAff = new ArrayList<>();
		for (int i = 0; i < listeRapport.get(indice).size(); i++) {
			listeRapportAff.add(listeRapport.get(indice).get(i));
		}
		return listeRapportAff;
	}

	public void setListeRapportAff(ArrayList<GrRapport> listeRapportAff) {
		this.listeRapportAff = listeRapportAff;
	}

	public List<ModeleRapport> getListeGroupRapportT() {
		return listeGroupRapportT;
	}

	public void setListeGroupRapportT(List<ModeleRapport> listeGroupRapportT) {
		this.listeGroupRapportT = listeGroupRapportT;
	}

	public Boolean getBolAcc() {
		return bolAcc;
	}

	public void setBolAcc(Boolean bolAcc) {
		this.bolAcc = bolAcc;
	}

	public String getGroupSelected() {
		return groupSelected;
	}

	public void setGroupSelected(String groupSelected) {
		this.groupSelected = groupSelected;
	}

	public List<Rapport> getListeRapportpre() {
		return listeRapportpre;
	}

	public void setListeRapportpre(List<Rapport> listeRapportpre) {
		this.listeRapportpre = listeRapportpre;
	}

	public Rapport getRapport() {
		return rapport;
	}

	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}

	public Boolean getBolSElect() {
		return bolSElect;
	}

	public void setBolSElect(Boolean bolSElect) {
		this.bolSElect = bolSElect;
	}

	public String getStrAffiche() {
		return strAffiche;
	}

	public void setStrAffiche(String strAffiche) {
		this.strAffiche = strAffiche;
	}

	public List<RelationRapport> getListeRR() {
		return listeRR;
	}

	public void setListeRR(List<RelationRapport> listeRR) {
		this.listeRR = listeRR;
	}

	public List<TreeNode> getListeNodes() {
		return listeNodes;
	}

	public void setListeNodes(List<TreeNode> listeNodes) {
		this.listeNodes = listeNodes;
	}

	public List<Affiliation> getListeAffiliation2() {
		return listeAffiliation2;
	}

	public void setListeAffiliation2(List<Affiliation> listeAffiliation2) {
		this.listeAffiliation2 = listeAffiliation2;
	}

	public List<Affiliation> getListeTouAff() {
		return listeTouAff;
	}

	public void setListeTouAff(List<Affiliation> listeTouAff) {
		this.listeTouAff = listeTouAff;
	}

	public List<Affiliation> getListeAffiliation3() {
		return listeAffiliation3;
	}

	public void setListeAffiliation3(List<Affiliation> listeAffiliation3) {
		this.listeAffiliation3 = listeAffiliation3;
	}

	public List<TreeNode> getListeTouNodes() {
		return listeTouNodes;
	}

	public void setListeTouNodes(List<TreeNode> listeTouNodes) {
		this.listeTouNodes = listeTouNodes;
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public List<String> getListePath() {
		return listePath;
	}

	public void setListePath(List<String> listePath) {
		this.listePath = listePath;
	}

	public Boolean getBolR() {
		return bolR;
	}

	public void setBolR(Boolean bolR) {
		this.bolR = bolR;
	}

	public String getSteAffiche() {
		return SteAffiche;
	}

	public void setSteAffiche(String steAffiche) {
		SteAffiche = steAffiche;
	}

	public List<Affiliation> getSocietes() {
		return societes;
	}

	public void setSocietes(List<Affiliation> societes) {
		this.societes = societes;
	}

	public ArrayList<ArrayList<Affiliation>> getListeStr2() {
		return listeStr2;
	}

	public void setListeStr2(ArrayList<ArrayList<Affiliation>> listeStr2) {
		this.listeStr2 = listeStr2;
	}

	public List<String> getListeStr() {
		return listeStr;
	}

	public void setListeStr(List<String> listeStr) {
		this.listeStr = listeStr;
	}

	public List<String> getListeCMD() {
		return listeCMD;
	}

	public void setListeCMD(List<String> listeCMD) {
		this.listeCMD = listeCMD;
	}

	public OperateurService getOperateurService() {
		return operateurService;
	}
	public void setOperateurService(OperateurService operateurService) {
		this.operateurService = operateurService;
	}
	public Operateur getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(Operateur currentUser) {
		this.currentUser = currentUser;
	}
	public String getLur() {
		return lur;
	}
	public void setLur(String lur) {
		this.lur = lur;
	}

	public String getDat() {
		return "fff";
	}

	public void setDat(String dat) {
		this.dat = dat;
	}
	public String getCode() {
		this.code=generate(5);
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}	

}
