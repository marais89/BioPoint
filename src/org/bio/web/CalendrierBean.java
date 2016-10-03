package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Calendrier;
import org.bio.model.Categorie;
import org.bio.model.Ferie;
import org.bio.model.GlobalHistorique;
import org.bio.model.Horaires;
import org.bio.model.Sequence;
import org.bio.model.Terminaux;
import org.bio.service.CalendrierService;
import org.bio.service.CategorieService;
import org.bio.service.FerieService;
import org.bio.service.GlobalHistoriqueService;
import org.bio.service.HoraireService;
import org.bio.service.SequenceService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

@ManagedBean
@SessionScoped
public class CalendrierBean implements Serializable {
	
	///////////////////////////////////////
	/////////// PARAMETRE DE SEQUENCE /////
	// IL FAUT AJOUTER UNE SEQUENCE NOMÉ Standard LORS DE L'INSTALLATIONDE L'APPLICATION
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////

	@ManagedProperty(value = ("#{calendrierServiceImpl}"))
	private CalendrierService calendrierService;
	@ManagedProperty(value = ("#{globalHistoriqueServiceImpl}"))
	private GlobalHistoriqueService globalHistoriqueService;
	@ManagedProperty(value = ("#{ferieServiceImpl}"))
	private FerieService ferieService;
	@ManagedProperty(value = ("#{categorieServiceImpl}"))
	private CategorieService categorieService;
	@ManagedProperty(value = ("#{sequenceServiceImpl}"))
	private SequenceService sequenceService;
	@ManagedProperty(value = ("#{horaireServiceImpl}"))
	private HoraireService horaireService;
//	@Autowired
//	private HoraireBean horairebean;
	private org.bio.model.HorairesId horaireId;
	private Horaires horaire;
	private Sequence sequence;
	private Calendrier clendrier;
	private Calendrier clendrierSelectionne;
	private GlobalHistorique globalHistorique;
	private List<Calendrier> listeCalendrier;
	private List<Calendrier> listeCalendrierSelectionne;
	private List<Calendrier> filtredlistCalendrier;
	private int sizeliste;
	private boolean flag;
	private List<String> listType;
	private Boolean bolval;
	private Date date = new Date();
	private Date Datedepart;
	private Date datefin;
	private List<GlobalHistorique> listeglobalhist;
	private String txt;
	private boolean flagbar;
	private float progress;
	private TimelineModel model;
	private String locale;
	private Date start;
	private Date end;
	private List<Date> listdatedcal;
	private List<Date> listdatefcal;
	private List<String> listnomcal;
	private GlobalHistorique gh1;
	private Date startt;
	private Date endt;
	private List<Calendrier> listCal;
	// // ferier////
	private Ferie ferie;
	private List<Ferie> listeferie;
	private List<String> couleurNom;
	private List<String> couleurValeur;
	// 
	private List<Calendrier> listeC;
	private List<Categorie> listeCategorie;

	@PostConstruct
	private void init() {
		List<Calendrier> listCal= new ArrayList<Calendrier>();
		bolval=true;
		listeCategorie= new ArrayList<Categorie>();
		listeCategorie= categorieService.findAllCategories();
		listeC= new ArrayList<Calendrier>();
		this.ferie = new Ferie();
		this.listeferie = new ArrayList<Ferie>();
		this.listeferie = ferieService.findAllFeries();
		this.locale = new String();
		this.clendrier = new Calendrier();
		this.clendrierSelectionne = new Calendrier();
		this.globalHistorique = new GlobalHistorique();
		
		this.listeCalendrier = new ArrayList<Calendrier>();
		this.listeCalendrier = calendrierService.findAllCalendriers();
		
		this.listeCalendrierSelectionne = new ArrayList<Calendrier>();
		this.listType = new ArrayList<String>();
		listType.add("Excep");
		listType.add("Annuel");
		this.date = new Date();
		//this.val = 0;
		initial();
		this.listeglobalhist = new ArrayList<GlobalHistorique>();
		this.listeglobalhist = globalHistoriqueService.findAllGlobalHistoriques();
		this.listdatedcal = new ArrayList<Date>();
		this.listdatefcal = new ArrayList<Date>();
		this.listnomcal = new ArrayList<String>();
		this.progress = 0;
		this.flagbar = false;
		couleurNom = new ArrayList<String>();
		couleurValeur = new ArrayList<String>();
		this.gh1 = new GlobalHistorique();
		setProgress(0);
		//initAnnee();
	}	
	// fct d'initialisation de l'année
	public void initAnnee()
	{
		Date d1=listeglobalhist.get(listeglobalhist.size()-1).getJour();
		Date DateNow = new Date();
		if(d1.before(DateNow))
		{	
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			Calendar calendar3 = Calendar.getInstance();
			Calendar calendar4 = Calendar.getInstance();

			calendar1.setTime(this.date);
			calendar2.set(calendar1.get(Calendar.YEAR), 0, 1);
			calendar3.set(calendar1.get(Calendar.YEAR), 11, 31);
			System.out.println("etape 1");
			
			for (Date d = this.Datedepart; d.before(increment2(datefin)); increment(d)) {
				this.globalHistorique.setJour(d);
				this.globalHistorique.setIdcal(359);
				this.globalHistorique.setIdfer(-1);
				globalHistoriqueService.insertGlobalHistorique(globalHistorique);
			}		
			
			System.out.println("etape 2");
			List<Calendrier> listCal= new ArrayList<Calendrier>();
			for(int i=0;i<listeCalendrier.size();i++)
				{if(listeCalendrier.get(i).getTypeCal().equals("Annuel")){listCal.add(listeCalendrier.get(i));}}
			System.out.println("etape 3");
			if(listCal.size()>0)
			{System.out.println("etape 4");
				for(int j=0;j<listCal.size();j++)
				{Date inter= new Date();System.out.println("boucle 1");
				calendar2.setTime(listCal.get(j).getDateDebut());
				calendar3.set(calendar1.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),calendar2.get(Calendar.DAY_OF_MONTH));
				inter =calendar3.getTime();
				listCal.get(j).setDateDebut(inter);
				
				calendar2.setTime(listCal.get(j).getDateFin());
				calendar3.set(calendar1.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),calendar2.get(Calendar.DAY_OF_MONTH));
				inter =calendar3.getTime();
				listCal.get(j).setDateFin(inter);
				calendrierService.updateCalendrier(listCal.get(j));
			for (Date d = listCal.get(j).getDateDebut(); d.before(increment2(listCal.get(j).getDateFin())); increment(d)) {
				clendrierSelectionne=listCal.get(j);				
				System.out.println("boucle 2");
				Maj();
//				calendar2.setTime(d);
//				calendar3.set(calendar1.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),calendar2.get(Calendar.DAY_OF_MONTH));
//				inter =calendar3.getTime();
			//	globalHistorique = globalHistoriqueService.getByid(d);
			//this.globalHistorique.setJour();
		//	this.globalHistorique.setIdcal(listCal.get(j).getIdcal());
			//this.globalHistorique.setIdfer(-1);
//			System.out.println("update"+inter);
//			System.out.println("++"+globalHistorique.getJour());		
		
			//globalHistoriqueService.updateGlobalHistorique(globalHistorique);
			
			}	}		
		}}
		
	}
	
	
	public void Maj() {
		try {
			
			Date datelim = this.clendrierSelectionne.getDateFin();			
			increment(datelim);			
			//getCalendrierService().updateCalendrier(this.clendrierSelectionne);			
			for (Date d = this.clendrierSelectionne.getDateDebut(); d.before(datelim); increment(d)) {
				globalHistorique = globalHistoriqueService.getByid(d);				
				Calendrier cal = calendrierService.getByid(globalHistorique.getIdcal());				
				if (cal.getTypeCal().equals("Excep")) {	}
				else {					
					globalHistorique.setIdcal(clendrierSelectionne.getIdcal());					
					globalHistoriqueService.insertGlobalHistorique(globalHistorique);
				}
			}
			//FacesMessage msg = new FacesMessage(" Calendrier modifié avec Succés");
			//FacesContext.getCurrentInstance().addMessage(null, msg);
			//RequestContext.getCurrentInstance().update("form");
			//RequestContext.getCurrentInstance().execute("diag_modif.hide()");
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getMessage();
		}
	}
	
	

	public void initchron() {
		this.listeglobalhist = new ArrayList<GlobalHistorique>();
		this.listeglobalhist = globalHistoriqueService.findAllGlobalHistoriques();

		Date datelim = this.datefin;
		increment(datelim);
		listdatedcal.add(Datedepart);
		for (int i = 1; i < listeglobalhist.size(); i++) {
			globalHistorique = listeglobalhist.get(i);
			this.gh1 = listeglobalhist.get(i - 1);
			if (this.globalHistorique.getIdcal() != gh1.getIdcal()) {
				listdatefcal.add(globalHistorique.getJour());

				if (gh1.getIdcal() == -1) {
					listnomcal.add("RegimeNormal");
				} else {
					String nom = getCalendrierService().getByid(gh1.getIdcal()).getDesignation();
					listnomcal.add(nom);
				}
				listdatedcal.add(globalHistorique.getJour());
			}
		}
		listdatefcal.add(globalHistorique.getJour());
		int id = listeglobalhist.get(listeglobalhist.size() - 1).getIdcal();
		if (id == -1) {
			listnomcal.add("RegimeNormal");
		} else {
			listnomcal.add(getCalendrierService().getByid(id).getDesignation());
		}
		chron();
	}

	public String findColor(String s) {
		if (couleurNom.size() > 0) {
			for (int i = 0; i < couleurNom.size(); i++) {
				if (couleurNom.get(i).equals(s)) {
					return couleurValeur.get(i);
				}
			}
			return ("vide");
		} else {
			return ("vide");
		}
	}

	public void chron() {
		String[] NAMES = new String[] { "Calendrier", "Ferier" };
		model = new TimelineModel();

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Date now = new Date();

		cal.setTimeInMillis(now.getTime());
		startt = cal.getTime();

		cal.setTimeInMillis(now.getTime() + 60 * 60 * 1000);
		endt = cal.getTime();

		for (int i = 0; i < listdatedcal.size(); i++)
		{
			String availability = "";
			if (listnomcal.get(i) == "RegimeNormal") {
				availability = "Unavailable";
			} else if (!findColor(listnomcal.get(i)).equals("vide")) {
				availability = findColor(listnomcal.get(i));
			} else {
				if (i < 12) {
					availability = "o" + i;
					couleurNom.add(listnomcal.get(i));
					couleurValeur.add("o" + i);
				} else {
					int y = 0;
					while (i > 11) {
						y = i - 11;
					}
					availability = "o" + y;
					couleurNom.add(listnomcal.get(i));
					couleurValeur.add("o" + i);
				}
			}
			TimelineEvent event = new TimelineEvent(listnomcal.get(i),
					listdatedcal.get(i), listdatefcal.get(i), true,
					"Calendrier", availability.toLowerCase());
			model.add(event);
		}
		for (int j = 0; j < listeferie.size(); j++) {
			long r = 2;
			String availability = (r == 0 ? "available" : (r == 1 ? "available"
					: "available"));
			TimelineEvent event = new TimelineEvent(listeferie.get(j)
					.getDesignation(), listeferie.get(j).getDateDebut(),
					listeferie.get(j).getDateFin(), true, "ferie",
					availability.toLowerCase());
			model.add(event);
		}

	}

	public void cancel() {
		progress = 0;
	}

	public void affect(SelectEvent event) {
		if (event != null)
			clendrierSelectionne = (Calendrier) event.getObject();
	}

	// fct initialisation
	public void initial() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		Calendar calendar3 = Calendar.getInstance();

		calendar1.setTime(this.date);
		calendar2.set(calendar1.get(Calendar.YEAR), 0, 1);
		calendar3.set(calendar1.get(Calendar.YEAR), 11, 31);

		this.Datedepart = calendar2.getTime();
		this.datefin = calendar3.getTime();
	}

	// fct qui pour incremente les jours
	public void increment(Date day) {
		Long l = day.getTime() + 86400000;
		day.setTime(l);
	}
	
	public Date increment2(Date day) {
		Date d = new Date();
		Long l = day.getTime() + 86400000;
		d.setTime(l);
		return d;
	}

	// fct execution de methode selon val
	public void selonexecute() {
		if (this.clendrierSelectionne.getDateFin().before(
				this.clendrierSelectionne.getDateDebut())) {
			FacesMessage msg = new FacesMessage(
					" 'Date fin' doit étre aprés 'date début' ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			if (bolval==true) { 
				
				ajouterCalendrier();
				System.out.println("ajout--");
			} else {
				modifierCalendrier();
				System.out.println("modif--");
			}
		}
	}

	// fct pour changer l'etat de val
	public void changevalajout() {
	    bolval=true;
		this.clendrierSelectionne = new Calendrier();
	}

	public void changevalmodif() {
		bolval=false;
	}

	// fct d'activation ou de désactivation de la colonne de selection //
	public void activate() {
		setFlag(!this.flag);
	}

	public void vider() {
		this.clendrierSelectionne = new Calendrier();
		this.clendrier = new Calendrier();
		this.progress = 0;
		this.flagbar = false;
		this.txt = "";
	}

	// fct ajout calendrier
	public void ajouterCalendrier() {
		try {
			System.out.println("ajouter calendrier");
			// ///////////BARCHARGEMENT///////////////
			if (clendrierSelectionne.getTypeCal().equals(null)
					|| clendrierSelectionne.getTypeCal().equals("")) {
				clendrierSelectionne.setTypeCal("Excep");
			}
			Long nbjours1 = clendrierSelectionne.getDateFin().getTime()- clendrierSelectionne.getDateDebut().getTime();
			nbjours1 = nbjours1 / 86400000;
			float logeurbar = 100;

			// //////////////////////////////////////
			this.txt = "Initialisation calendrier en cours...";
			this.flagbar = true;
			if (globalHistoriqueService.getByid(clendrierSelectionne.getDateDebut()) == null) {
				nbjours1 = nbjours1 + 365;
				logeurbar = 60;
				RequestContext.getCurrentInstance().update("form_modif_cal:out1");
				for (Date d = this.Datedepart; d.before(datefin); increment(d)) {
					this.globalHistorique.setJour(d);
					this.globalHistorique.setIdcal(-1);
					this.globalHistorique.setIdfer(-1);
					globalHistoriqueService.insertGlobalHistorique(globalHistorique);
					progress = progress
							+ (float) ((float) 60 / (float) nbjours1);
					System.out.println(progress);
					RequestContext.getCurrentInstance().update("bar");
				}
				this.globalHistorique.setJour(datefin);
				this.globalHistorique.setIdcal(-1);
				this.globalHistorique.setIdfer(-1);
				globalHistoriqueService.insertGlobalHistorique(globalHistorique);

			}
			getCalendrierService().insertCalendrier(this.clendrierSelectionne);

			// //////////////////////////////////////

			RequestContext.getCurrentInstance().update("form_modif_cal:out1");

			Date datelim = this.clendrierSelectionne.getDateFin();
			increment(datelim);
			this.txt = "synchronisation calendrier en cours...";
			this.flagbar = true;
			RequestContext.getCurrentInstance().update("form_modif_cal:out1");
			for (Date d = this.clendrierSelectionne.getDateDebut(); d.before(datelim); increment(d)) {
				progress = progress
						+ (float) ((float) logeurbar / (float) nbjours1);
				if (progress > 80) {
					this.txt = "Verification des données...";
					this.flagbar = true;
				}
				if (progress > 100) {
					progress = 100;
				}
			
				globalHistorique = globalHistoriqueService.getByid(d);
				if (globalHistorique.getIdcal() == -1) {
					globalHistorique.setIdcal(clendrierSelectionne.getIdcal());
					globalHistoriqueService.updateGlobalHistorique(globalHistorique);

				} else {
					Calendrier cal = calendrierService.getByid(globalHistorique.getIdcal());
					if (cal.getTypeCal().equals("Excep")) {
					} else {
						globalHistorique.setIdcal(clendrierSelectionne
								.getIdcal());
						globalHistoriqueService
								.updateGlobalHistorique(globalHistorique);
					}
				}
			}
			this.progress = 100;
			this.flagbar = false;
			RequestContext.getCurrentInstance().update("form_modif_cal:out1");
			FacesMessage msg = new FacesMessage(
					" Calendrier ajouter avec Succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext.getCurrentInstance().update("form:messaget");
			
			vider();
			progress = 0;
			RequestContext.getCurrentInstance().update("form_modif_cal");
			RequestContext.getCurrentInstance().update("form");
			RequestContext.getCurrentInstance().execute("diag_modif.hide()");
			sequence=sequenceService.findSequence("Standard").get(0);
			for( Categorie c:listeCategorie)
			{
				horaireId.setIdcal(clendrierSelectionne.getIdcal());
				horaireId.setIdcat(c.getIdcat());
				horaire.setId(horaireId);
				horaire.setSequence(sequence);
				horaireService.insertHoraire(horaire);	
				RequestContext.getCurrentInstance().execute("diag_modif.hide()");
				//horairebean.init();
			}
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getMessage();
		}
	}

	// fct mise a jour terminal
	public void modifierCalendrier() {
		try {
			
			Date datelim = this.clendrierSelectionne.getDateFin();			
			increment(datelim);			
			getCalendrierService().updateCalendrier(this.clendrierSelectionne);			
			for (Date d = this.clendrierSelectionne.getDateDebut(); d.before(datelim); increment(d)) {
				globalHistorique = globalHistoriqueService.getByid(d);
				
				Calendrier cal = calendrierService.getByid(globalHistorique.getIdcal());
				
				if (cal.getTypeCal().equals("Excep")) {	}
				else {					
					globalHistorique.setIdcal(clendrierSelectionne.getIdcal());					
					globalHistoriqueService.updateGlobalHistorique(globalHistorique);
				}
			}
			FacesMessage msg = new FacesMessage(" Calendrier modifié avec Succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext.getCurrentInstance().update("form");
			RequestContext.getCurrentInstance().execute("diag_modif.hide()");
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getMessage();
		}
	}

	// supprimer terminaux
	public void supprimcalendrier() {
		try {
			this.txt = "supprission des  données en cours...";
			this.flagbar = true;
			RequestContext.getCurrentInstance().update("formsupprim");
			int totaljours = 0;
			for (Calendrier c : listeCalendrierSelectionne) {
				Long nbjours = c.getDateFin().getTime()- c.getDateDebut().getTime();
				nbjours = nbjours / 86400000;
				totaljours = (int) (totaljours + nbjours);
			}
			progress = (float) (progress + 5);
			for (Calendrier c : listeCalendrierSelectionne) {
				Date datelim = c.getDateFin();
				increment(datelim);

				for (Date d = c.getDateDebut(); d.before(datelim); increment(d)) {
					float n = (float) 50 / (float) totaljours;
					progress = (float) (progress + n);
					globalHistorique = globalHistoriqueService.getByid(d);
					globalHistorique.setIdcal(-1);
					globalHistoriqueService
							.updateGlobalHistorique(globalHistorique);
				}
				getCalendrierService().deleteCalendrier(c);
			}
			this.listeCalendrier = new ArrayList<Calendrier>();
			this.listeCalendrier = calendrierService.findAllCalendriers();
			// /
			int totaljours3 = 0;
			for (Calendrier cal : this.listeCalendrier) {
				Long nbjours = cal.getDateFin().getTime()
						- cal.getDateDebut().getTime();
				nbjours = nbjours / 86400000;
				totaljours3 = (int) (totaljours3 + nbjours);
			}
			this.txt = "synchronisation calendrier en cours...";
			this.flagbar = true;
			RequestContext.getCurrentInstance().update("formsupprim");

			for (Calendrier cal : this.listeCalendrier) {
				Date datelim = cal.getDateFin();
				increment(datelim);
				for (Date d = cal.getDateDebut(); d.before(datelim); increment(d)) {
					float v = (float) 65 / (float) totaljours3;
					progress = (float) (progress + v);

					if (progress > 100) {
						progress = 100;
					}

					globalHistorique = globalHistoriqueService.getByid(d);
					if (globalHistorique.getIdcal() == -1) {
						globalHistorique.setIdcal(cal.getIdcal());
						globalHistoriqueService
								.updateGlobalHistorique(globalHistorique);
					} else {
						Calendrier call = calendrierService
								.getByid(globalHistorique.getIdcal());
						if (call.getTypeCal().equals("Excep")) {
						} else {
							globalHistorique.setIdfer(cal.getIdcal());
							globalHistoriqueService
									.updateGlobalHistorique(globalHistorique);
						}
					}
				}
			}
			
			vider();
			progress = 0;
			FacesMessage msg = new FacesMessage(
					" calendrier(s) supprimé(s) avec Succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext.getCurrentInstance().update("form");
			RequestContext.getCurrentInstance().execute("diag_supprim.hide()");
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getMessage();
		}
	}

	// fct supprimer un terminal de la liste listterminalselectionner
	public void removecalendrier(Calendrier cal) {
		this.listeCalendrierSelectionne.remove(cal);
	}

	// / GETTERS AND SETTERS

	public Calendrier getClendrier() {
		return clendrier;
	}
	public CalendrierService getCalendrierService() {
		return calendrierService;
	}
	public void setCalendrierService(CalendrierService calendrierService) {
		this.calendrierService = calendrierService;
	}
	public void setClendrier(Calendrier clendrier) {
		this.clendrier = clendrier;
	}
	public Calendrier getClendrierSelectionne() {
		return clendrierSelectionne;
	}
	public void setClendrierSelectionne(Calendrier clendrierSelectionné) {
		this.clendrierSelectionne = clendrierSelectionné;
	}
	public List<Calendrier> getListeCalendrier() {
		this.listeCalendrier = new ArrayList<Calendrier>();
		this.listeCalendrier = calendrierService.findAllCalendriers();
		return listeCalendrier;
	}
	public void setListeCalendrier(List<Calendrier> listeCalendrier) {
		this.listeCalendrier = listeCalendrier;
	}
	public List<Calendrier> getListeCalendrierSelectionne() {
		return listeCalendrierSelectionne;
	}
	public void setListeCalendrierSelectionne(
			List<Calendrier> listeCalendrierSelectionne) {
		this.listeCalendrierSelectionne = listeCalendrierSelectionne;
	}
	public List<Calendrier> getFiltredlistCalendrier() {
		return filtredlistCalendrier;
	}
	public void setFiltredlistCalendrier(List<Calendrier> filtredlistCalendrier) {
		this.filtredlistCalendrier = filtredlistCalendrier;
	}
	public int getSizeliste() {
		sizeliste = listeCalendrierSelectionne.size();
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
	public List<String> getListType() {
		return listType;
	}
	public void setListType(List<String> listType) {
		this.listType = listType;
	}	
	public Boolean getBolval() {
		return bolval;
	}
	public void setBolval(Boolean bolval) {
		this.bolval = bolval;
	}
	public GlobalHistorique getGlobalHistorique() {
		return globalHistorique;
	}
	public void setGlobalHistorique(GlobalHistorique globalHistorique) {
		this.globalHistorique = globalHistorique;
	}
	public GlobalHistoriqueService getGlobalHistoriqueService() {
		return globalHistoriqueService;
	}
	public void setGlobalHistoriqueService(
			GlobalHistoriqueService globalHistoriqueService) {
		this.globalHistoriqueService = globalHistoriqueService;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public FerieService getFerieService() {
		return ferieService;
	}
	public void setFerieService(FerieService ferieService) {
		this.ferieService = ferieService;
	}
	public Date getDatedepart() {
		return Datedepart;
	}
	public void setDatedepart(Date datedepart) {
		Datedepart = datedepart;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	public List<GlobalHistorique> getListeglobalhist() {
		this.listeglobalhist = new ArrayList<GlobalHistorique>();
		this.listeglobalhist = globalHistoriqueService.findAllGlobalHistoriques();
		return listeglobalhist;
	}
	public void setListeglobalhist(List<GlobalHistorique> listeglobalhist) {
		this.listeglobalhist = listeglobalhist;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public boolean isFlagbar() {
		return flagbar;
	}
	public void setFlagbar(boolean flagbar) {
		this.flagbar = flagbar;
	}
	public float getProgress() {
		return progress;
	}
	public void setProgress(float progress) {
		this.progress = progress;
	}
	public TimelineModel getModel() {
		return model;
	}
	public void setModel(TimelineModel model) {
		this.model = model;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public List<String> getListnomcal() {
		return listnomcal;
	}
	public void setListnomcal(List<String> listnomcal) {
		this.listnomcal = listnomcal;
	}
	public List<Date> getListdatedcal() {
		return listdatedcal;
	}
	public void setListdatedcal(List<Date> listdatedcal) {
		this.listdatedcal = listdatedcal;
	}
	public List<Date> getListdatefcal() {
		return listdatefcal;
	}
	public void setListdatefcal(List<Date> listdatefcal) {
		this.listdatefcal = listdatefcal;
	}
	public GlobalHistorique getGh1() {
		return gh1;
	}
	public void setGh1(GlobalHistorique gh1) {
		this.gh1 = gh1;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Date getStartt() {
		return startt;
	}
	public void setStartt(Date startt) {
		this.startt = startt;
	}
	public Date getEndt() {
		return endt;
	}
	public void setEndt(Date endt) {
		this.endt = endt;
	}
	public Ferie getFerie() {
		return ferie;
	}
	public void setFerie(Ferie ferie) {
		this.ferie = ferie;
	}
	public List<Ferie> getListeferie() {
		return listeferie;
	}
	public void setListeferie(List<Ferie> listeferie) {
		this.listeferie = listeferie;
	}
	public List<String> getCouleurNom() {
		return couleurNom;
	}
	public void setCouleurNom(List<String> couleurNom) {
		this.couleurNom = couleurNom;
	}
	public List<String> getCouleurValeur() {
		return couleurValeur;
	}
	public void setCouleurValeur(List<String> couleurValeur) {
		this.couleurValeur = couleurValeur;
	}
	public List<Calendrier> getListeC() {
		return listeC;
	}
	public void setListeC(List<Calendrier> listeC) {
		this.listeC = listeC;
	}
	public CategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}
	public SequenceService getSequenceService() {
		return sequenceService;
	}
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}
	public HoraireService getHoraireService() {
		return horaireService;
	}
	public void setHoraireService(HoraireService horaireService) {
		this.horaireService = horaireService;
	}
	public org.bio.model.HorairesId getHoraireId() {
		return horaireId;
	}
	public void setHoraireId(org.bio.model.HorairesId horaireId) {
		this.horaireId = horaireId;
	}
	public Horaires getHoraire() {
		return horaire;
	}
	public void setHoraire(Horaires horaire) {
		this.horaire = horaire;
	}
	public Sequence getSequence() {
		return sequence;
	}
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}
	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}
	public List<Calendrier> getListCal() {
		return listCal;
	}
	public void setListCal(List<Calendrier> listCal) {
		this.listCal = listCal;
	}
	
}
