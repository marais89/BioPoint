package org.bio.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.bio.model.Affiliation;
import org.bio.model.Categorie;
import org.bio.model.Historique;
import org.bio.model.HistoriqueCat;
import org.bio.model.Personnel;
import org.bio.model.Trace;
import org.bio.service.AffiliationService;
import org.bio.service.CategorieService;
import org.bio.service.HistoriqueService;
import org.bio.service.PersonnelService;
import org.bio.service.TraceService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.component.timeline.TimelineUpdater;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.primefaces.model.TreeNode;
import org.springframework.dao.DataAccessException;

@ManagedBean
@SessionScoped
public class HistoriqueBean implements Serializable {
	private TimelineModel model;
	private String locale; // current locale as String, java.util.Locale is
							// possible too.
	private Date start;
	private Date end;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	@ManagedProperty(value = "#{categorieServiceImpl}")
	private CategorieService categorieservice;
	@ManagedProperty(value = "#{historiqueServiceImpl}")
	private HistoriqueService historiqueservice;
	private int idPersonnel;
	@ManagedProperty(value = "#{affiliationServiceImpl}")
	private AffiliationService affiliationService;
	@ManagedProperty("#{traceServiceImpl}")
	private TraceService traceService;
	private Map<String, Integer> listaffliation;
	private Map<String, Integer> listcategorie;
	private Integer idCategorie;
	private Integer idAffliation;
	private Integer range;
	private Historique historique;
	private Historique selectedHistorique;
	private List<Personnel> listpersonnel;
	private TimeZone timeZone = TimeZone.getTimeZone("Africa/Tunis");

	private TreeNode[] selectednode;

	@PostConstruct
	private void init() {
		listpersonnel = new ArrayList<Personnel>();
		listpersonnel.addAll(getPersonnelService().findAllWithoutPersonnels());
		listaffliation = new HashMap<String, Integer>();
		for (Affiliation a : getAffiliationService().findAllAffiliations()) {
			a.setAffiliationChildren(getAffiliationService().listaffiliation(a));
			if (a.getAffiliationChildren().isEmpty()) {
				listaffliation.put(a.getDesignation(), a.getIdaff());
			}
		}

		historique = new Historique();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		cal.set(2014, Calendar.JANUARY, 1, 0, 0, 0);
		start = cal.getTime();

		cal.set(2016, Calendar.DECEMBER, 5, 0, 0, 0);
		end = cal.getTime();

		initialize();
		
	}

	protected void initialize() {

		// set initial start / end dates for the axis of the timeline
		Date now = new Date();

		// create timeline model
		model = new TimelineModel();

		for (Personnel p : listpersonnel) {
			now = new Date();
			Date end = new Date(now.getTime() - 12 * 60 * 60 * 1000);
			p.setFindHistorique(getPersonnelService().listHistorique(p));

			for (Historique h : p.getFindHistorique()) {
				h.setPersonnel(p);
				Date start = h.getDu();
				end = h.getAu();
				long r = Math.round(Math.random() * 2);
				String availability = "";
				if (h.getAffiliation().getType().equals("Di"))
					availability = "available";
				else if (h.getAffiliation().getType().equals("Se"))
					availability = "unavailable";
				else if (h.getAffiliation().getType().equals("Eq"))
					availability = "maybe";
				// create an event with content, start / end dates, editable
				// flag, group name and custom style class
				TimelineEvent event = new TimelineEvent(h, start, end, false,
						p.getCivilite() + " " + p.getPrenom() + " "
								+ p.getNom(), availability);

				model.add(event);

			}
		}

	}
	
	// soustraire d'une date i jours **
	public Date soustractDate(Date d,int i)
	{
		Date ds= new Date();;
		Long l= d.getTime();
		l=l-(86400000*i);
		ds.setTime(l);
		return ds;
	}
	
	// ajouter a une jours i jours ** 
	public Date addDate(Date d,int i)
	{
		Date ds= new Date();;
		Long l= d.getTime();
		l=l+(86400000*i);
		ds.setTime(l);
		return ds;
	}

	public void insertHistorique() {
		try {
			// Personnel p = getPersonnelService().getByid(idPersonnel);
			historique.setAffiliation(getAffiliationService().getByid(idAffliation));

			// ** ATTENTION VALEUR TEST **
			historique.setIdste(9);
			historique.setDesigSte("KB2i");
			getcurrentParametre(historique.getAffiliation());
			// historique.setCategorie(getCategorieservice().getByid(idCategorie));
			historique.getPersonnel().setFindHistorique(getPersonnelService().listHistorique(historique.getPersonnel()));

			if (historique.getPersonnel().getFindHistorique().size() == 0) {

				getHistoriqueservice().insertHistorique(historique);
			} else {
				for (Historique h : historique.getPersonnel().getFindHistorique()) {

					if ((h.getDu().before(historique.getDu())) && (h.getAu().after(historique.getAu()))) 
					{
						Date tmp = h.getAu();
						h.setAu(soustractDate(historique.getDu(),1));
						getHistoriqueservice().updateHistorique(h);
						//historique à inserer
						//getHistoriqueservice().insertHistorique(historique);
						Historique h2 = new Historique();
						h2.setPersonnel(historique.getPersonnel());
						h2.setAffiliation(h.getAffiliation());

						// ATTENTION VALEUR TEST ***
						h2.setDesigSte("KB2I");
						h2.setIdste(9);
						// /////////
						h2.setAu(tmp);
						h2.setDu(addDate(historique.getAu(),1));
						getHistoriqueservice().insertHistorique(h2);

					} else if (historique.getAu().before(h.getDu())) 
						{
						//h.setDu(addDate(historique.getAu(),1));
						//getHistoriqueservice().updateHistorique(h);
						//getHistoriqueservice().insertHistorique(historique);
					}
					else if(historique.getDu().after(h.getAu()))
					{
						//h.setAu(soustractDate(historique.getDu(),1));
						//getHistoriqueservice().updateHistorique(h);
						//getHistoriqueservice().insertHistorique(historique);
					}
					else if(historique.getDu().after(h.getDu())&& historique.getDu().before(h.getAu())&& historique.getAu().after(h.getAu()))
					{
						h.setAu(soustractDate(historique.getDu(),1));
						getHistoriqueservice().updateHistorique(h);
						//getHistoriqueservice().insertHistorique(historique);
					}
					else if(historique.getDu().before(h.getDu())&& historique.getAu().before(h.getAu())&& historique.getAu().after(h.getDu()))
					{
						h.setDu(addDate(historique.getDu(),1));
						getHistoriqueservice().updateHistorique(h);
						//getHistoriqueservice().insertHistorique(historique);
					}
					else if(historique.getDu().before(h.getDu()) && historique.getAu().after(h.getAu()) )
					{
						getHistoriqueservice().deleteHistorique(h);
						//getHistoriqueservice().insertHistorique(historique);
					}
					
				}
				getHistoriqueservice().insertHistorique(historique);
			}
			Trace trace = new Trace();
			trace.setAction("Insertion Historique d'affiliation "
					+ historique.getAffiliation().getDesignation() + " pour "
					+ historique.getPersonnel().getNom() + " "
					+ getHistorique().getPersonnel().getPrenom());
			trace.setObjet("Historique");
			getTraceService().insertTrace(trace);
			initialize();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:timeline");
			context.update("form:msg");
			TimelineUpdater.getCurrentInstance("form:timeline");

			// ---- AJOUTER PAR DALI
			historique = new Historique();
			RequestContext.getCurrentInstance().reset("form2");
			RequestContext.getCurrentInstance().reset("form2:grid");
			RequestContext.getCurrentInstance().update("form2");
			RequestContext.getCurrentInstance().update("form2:grid");

			// ----
			FacesMessage msg = new FacesMessage("Historique Crée avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void updateHistorique() {
		try {
			selectedHistorique.setAffiliation(getAffiliationService().getByid(
					idAffliation));
			getcurrentupdateSociete(selectedHistorique.getAffiliation());
			// selectedHistorique.setCategorie(getCategorieservice().getByid(idCategorie));

			// selectedHistorique.setPersonnel(getPersonnelService().getByid(idPersonnel));

			getHistoriqueservice().updateHistorique(selectedHistorique);
			initialize();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:timeline");
			context.update("form:msg");
			context.execute("undisplaymodif()");
			TimelineUpdater.getCurrentInstance("form:timeline");
			FacesMessage msg = new FacesMessage(
					"Historique modifié avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			Trace trace = new Trace();
			trace.setObjet("Historique");
			trace.setAction("Modification Historique d'affiliation "
					+ selectedHistorique.getAffiliation().getDesignation()
					+ " pour " + selectedHistorique.getPersonnel().getNom()
					+ " " + selectedHistorique.getPersonnel().getPrenom());
			getTraceService().insertTrace(trace);
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void deleteHistorique() {
		try {

			getHistoriqueservice().deleteHistorique(selectedHistorique);
			initialize();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:timeline");
			TimelineUpdater.getCurrentInstance("form:timeline");
			Trace trace = new Trace();
			trace.setObjet("Historique");
			trace.setAction("Suppression Historique d'affiliation "
					+ selectedHistorique.getAffiliation().getDesignation()
					+ " pour " + selectedHistorique.getPersonnel().getNom()
					+ " " + selectedHistorique.getPersonnel().getPrenom());
			getTraceService().insertTrace(trace);
			FacesMessage msg = new FacesMessage(
					"Historique supprimé avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void handleDateSelect(SelectEvent event) {

		initialize();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:timeline");
		TimelineUpdater.getCurrentInstance("formhisto:timeline");
	}

	public void displayYear() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.set(2014, Calendar.JANUARY, 1, 0, 0, 0);
		start = cal.getTime();

		cal.set(2023, Calendar.DECEMBER, 5, 0, 0, 0);
		end = cal.getTime();
		initialize();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:timeline");
		TimelineUpdater.getCurrentInstance("form:timeline");

	}

	public void displaymonth() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.set(2014, Calendar.JANUARY, 1, 0, 0, 0);
		start = cal.getTime();

		cal.set(2016, Calendar.DECEMBER, 5, 0, 0, 0);
		end = cal.getTime();
		initialize();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:timeline");
		TimelineUpdater.getCurrentInstance("form:timeline");

	}

	public boolean nodeContains(Affiliation a) {

		for (int i = 0; i < selectednode.length; i++) {
			Affiliation a2 = (Affiliation) selectednode[i].getData();
			if (a2.getIdaff() == a.getIdaff()) {
				return true;

			}
		}

		return false;
	}

	private void getcurrentParametre(Affiliation af) {
		System.out.println(af.getType());
		if (af.getType().equalsIgnoreCase("So")) {
			historique.setSociete(af);
			historique.setDesigSte(af.getDesignation());
		} else {
			getcurrentParametre(af.getAffiliation());

		}
	}

	private void getcurrentupdateSociete(Affiliation af) {
		System.out.println(af.getType());
		if (af.getType().equalsIgnoreCase("So")) {
			selectedHistorique.setSociete(af);
			selectedHistorique.setDesigSte(af.getDesignation());
		} else {
			getcurrentParametre(af.getAffiliation());

		}
	}

	public void filtreAffiliation() {
		if (selectednode == null) {
			initialize();
			RequestContext context = RequestContext.getCurrentInstance();
			System.out.println("entré");
			context.update("form:timeline");
			return;
		}
		initialize();
		List<TimelineEvent> listtoremove = new ArrayList<TimelineEvent>();
		for (TimelineEvent e : model.getEvents()) {
			Historique a1 = (Historique) e.getData();
			for (int i = 0; i < selectednode.length; i++) {
				Affiliation a2 = (Affiliation) selectednode[i].getData();
				if (!a1.getAffiliation().getIdaff().equals(a2.getIdaff())) {
					listtoremove.add(e);
					System.out.println("succés");

				}
			}
		}

		model.deleteAll(listtoremove);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:timeline");

	}

	public void onSelect(TimelineSelectEvent e) {
		TimelineEvent timelineEvent = e.getTimelineEvent();
		selectedHistorique = (Historique) timelineEvent.getData();

	}

	public TimelineModel getModel() {

		return model;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getStart() {
		return start;
	}

	public AffiliationService getAffiliationService() {
		return affiliationService;
	}

	public void setAffiliationService(AffiliationService affiliationService) {
		this.affiliationService = affiliationService;
	}

	public Date getEnd() {
		return end;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public CategorieService getCategorieservice() {
		return categorieservice;
	}

	public void setCategorieservice(CategorieService categorieservice) {
		this.categorieservice = categorieservice;
	}

	public int getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public Map<String, Integer> getListaffliation() {
		for (Affiliation a : getAffiliationService().findAllAffiliations()) {
			a.setAffiliationChildren(getAffiliationService().listaffiliation(a));
			if (a.getAffiliationChildren().isEmpty()) {
				listaffliation.put(a.getDesignation(), a.getIdaff());
			}
		}
		return listaffliation;
	}

	public void setListaffliation(Map<String, Integer> listaffliation) {
		this.listaffliation = listaffliation;
	}

	public Map<String, Integer> getListcategorie() {
		return listcategorie;
	}

	public void setListcategorie(Map<String, Integer> listcategorie) {
		this.listcategorie = listcategorie;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public Integer getIdAffliation() {
		return idAffliation;
	}

	public void setIdAffliation(Integer idAffliation) {
		this.idAffliation = idAffliation;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public HistoriqueService getHistoriqueservice() {
		return historiqueservice;
	}

	public void setHistoriqueservice(HistoriqueService historiqueservice) {
		this.historiqueservice = historiqueservice;
	}

	public Historique getSelectedHistorique() {
		return selectedHistorique;
	}

	public void setSelectedHistorique(Historique selectedHistorique) {
		this.selectedHistorique = selectedHistorique;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public TraceService getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
	}

	public List<Personnel> getListpersonnel() {
		return listpersonnel;
	}

	public void setListpersonnel(List<Personnel> listpersonnel) {
		this.listpersonnel = listpersonnel;
	}

	public TreeNode[] getSelectednode() {
		return selectednode;
	}

	public void setSelectednode(TreeNode[] selectednode) {
		this.selectednode = selectednode;
	}

}
