package org.bio.web;

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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Affiliation;
import org.bio.model.Categorie;
import org.bio.model.Historique;
import org.bio.model.HistoriqueCat;
import org.bio.model.Personnel;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.model.Trace;
import org.bio.service.AffiliationService;
import org.bio.service.CategorieService;
import org.bio.service.HistoriqueCatService;
import org.bio.service.PersonnelService;
import org.bio.service.SequenceDetailService;
import org.bio.service.SequenceService;
import org.bio.service.TraceService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.component.timeline.TimelineUpdater;
import org.primefaces.extensions.event.timeline.TimelineRangeEvent;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.primefaces.model.TreeNode;
import org.springframework.dao.DataAccessException;

@ManagedBean
@ViewScoped
public class HistoriqueCatBean implements Serializable {
	private TimelineModel model;
	private String locale; // current locale as String, java.util.Locale is
							// possible too.
	private Date start;
	private Date end;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	@ManagedProperty(value = "#{categorieServiceImpl}")
	private CategorieService categorieservice;
	@ManagedProperty(value = "#{historiqueCatServiceImpl}")
	private HistoriqueCatService historiquecatservice;
	private int idPersonnel;
	@ManagedProperty(value = "#{affiliationServiceImpl}")
	private AffiliationService affiliationService;
	@ManagedProperty(value = "#{sequenceServiceImpl}")
	private SequenceService sequenceService;
	@ManagedProperty("#{traceServiceImpl}")
	private TraceService traceService;
	@ManagedProperty(value = ("#{sequenceDetailServiceImpl}"))
	private SequenceDetailService sequenceDetailService;
	private List<Sequence> listsequence;
	private Map<String, Integer> listaffliation;
	private Map<String, Integer> listcategorie;
	private boolean showSeq;
	private Integer idCategorie;
	private Integer idAffliation;
	private Integer range;
	private HistoriqueCat historique;
	private HistoriqueCat selectedHistorique;
	private List<Personnel> listpersonnel;
	private TimeZone timeZone = TimeZone.getTimeZone("Europe/Berlin");
	private List<SequenceDetail> detailseq;
	private TreeNode selectednode;

	@PostConstruct
	private void init() {
		listaffliation = new HashMap<String, Integer>();
		listsequence = new ArrayList<Sequence>();
		listsequence.addAll(getSequenceService().findAllDaySequence());
		showSeq = true;
		idCategorie = 0;
		for (Affiliation a : getAffiliationService().findAllAffiliations()) {
			a.setAffiliationChildren(getAffiliationService().listaffiliation(a));
			if (a.getAffiliationChildren().isEmpty()) {
				listaffliation.put(a.getDesignation(), a.getIdaff());
			}
		}
		listcategorie = new HashMap<String, Integer>();
		for (Categorie c : getCategorieservice().findAllCategories()) {
			listcategorie.put(c.getDesigCat(), c.getIdcat());
		}
		historique = new HistoriqueCat();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		cal.set(2014, Calendar.JANUARY, 1, 0, 0, 0);
		start = cal.getTime();

		cal.set(2014, Calendar.DECEMBER, 31, 0, 0, 0);
		end = cal.getTime();
		if (listsequence.size() > 0) {
			historique.setSequence(listsequence.get(0));
		}
		initialize();
	}

	protected void initialize() {
		listpersonnel = new ArrayList<Personnel>();
		listpersonnel.addAll(getPersonnelService().findAllWithoutPersonnels());

		// set initial start / end dates for the axis of the timeline
		Date now = new Date();

		// create timeline model
		model = new TimelineModel();

		for (Personnel p : listpersonnel) {
			now = new Date();
			Date end = new Date(now.getTime() - 12 * 60 * 60 * 1000);
			p.setFindHistoriqueCat(getPersonnelService().findAllHistCat(p));
			for (HistoriqueCat h : p.getFindHistoriqueCat()) {
				h.setPersonnel(p);
				Date start = h.getDu();
				end = h.getAu();
				// if(h.getCategorie()!=null)
				// h.setCurrentSeqeunce(getSequenceService().getByid(getSequenceService().loadcurrentsequence(h.getCategorie().getIdcat())));
				String color = "";
				if (h.getCategorie() != null) {
					color = "available";
				} else {
					color = "unavailable";
				}
				// create an event with content, start / end dates, editable
				// flag, group name and custom style class
				TimelineEvent event = new TimelineEvent(h, start, end, false,
						p.getCivilite() + " " + p.getPrenom() + " "
								+ p.getNom(), color);

				model.add(event);

			}
		}

	}

	public void switchSeqCat() {
		showSeq = !showSeq;
	}

	public void zoomchange(TimelineRangeEvent event) {
		// FacesMessage msg = new
		// FacesMessage("Current range"+event.getStartDate()+"   "+event.getEndDate());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
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
		
		public void vider()
		{
			historique = new HistoriqueCat();
			selectedHistorique= new HistoriqueCat();
		}

	public void insertHistorique() {
		try {
			// Personnel p = getPersonnelService().getByid(idPersonnel);
			if (isShowSeq()) {
				historique.setCategorie(getCategorieservice().getByid(
						idCategorie));
			}
			// historique.setPersonnel(p);
			System.out.println("enter1");
			historique.getPersonnel().setFindHistoriqueCat(getPersonnelService().findAllHistCat(historique.getPersonnel()));
			if (historique.getPersonnel().getFindHistoriqueCat().size() == 0) {
				getHistoriquecatservice().insertHistoriqueCat(historique);
				System.out.println("enter2");

			} 
			//////////////--
			else {
				for (HistoriqueCat h : historique.getPersonnel().getFindHistoriqueCat()) {

					if ((h.getDu().before(historique.getDu())) && (h.getAu().after(historique.getAu()))) 
					{
						Date tmp = h.getAu();
						h.setAu(soustractDate(historique.getDu(),1));
						getHistoriquecatservice().updateHistoriqueCat(h);
						//historique à inserer
						//getHistoriqueservice().insertHistorique(historique);
						HistoriqueCat h2 = new HistoriqueCat();
						h2.setPersonnel(historique.getPersonnel());
						if (h.getCategorie() != null) {
							h2.setCategorie(h.getCategorie());
						} else {
							h2.setSequence(h.getSequence());
						}
						h2.setAu(tmp);
						h2.setDu(historique.getAu());
						getHistoriquecatservice().insertHistoriqueCat(h2);

					} 
					else if(historique.getDu().after(h.getDu())&& historique.getDu().before(h.getAu())&& historique.getAu().after(h.getAu()))
					{
						h.setAu(soustractDate(historique.getDu(),1));
						getHistoriquecatservice().updateHistoriqueCat(h);;
						//getHistoriqueservice().insertHistorique(historique);
					}
					else if(historique.getDu().before(h.getDu())&& historique.getAu().before(h.getAu())&& historique.getAu().after(h.getDu()))
					{
						h.setDu(addDate(historique.getDu(),1));
						getHistoriquecatservice().updateHistoriqueCat(h);
						//getHistoriqueservice().insertHistorique(historique);
					}
					else if(historique.getDu().before(h.getDu()) && historique.getAu().after(h.getAu()) )
					{
						getHistoriquecatservice().deleteHistoriqueCat(h);
						//getHistoriqueservice().insertHistorique(historique);
					}
					
				}
				getHistoriquecatservice().insertHistoriqueCat(historique);
			}
			
		/*	else {
				for (HistoriqueCat h : historique.getPersonnel()
						.getFindHistoriqueCat()) {

					if ((h.getDu().before(historique.getDu()))
							&& (h.getAu().after(historique.getAu()))) {
						Date tmp = h.getAu();
						h.setAu(historique.getDu());
						getHistoriquecatservice().updateHistoriqueCat(h);
						getHistoriquecatservice().insertHistoriqueCat(
								historique);
						HistoriqueCat h2 = new HistoriqueCat();
						h2.setPersonnel(historique.getPersonnel());
						if (h.getCategorie() != null) {
							h2.setCategorie(h.getCategorie());
						} else {
							h2.setSequence(h.getSequence());
						}
						h2.setAu(tmp);
						h2.setDu(historique.getAu());
						getHistoriquecatservice().insertHistoriqueCat(h2);

					} else if (historique.getAu().after(h.getDu())) {
						h.setDu(historique.getAu());

						getHistoriquecatservice().updateHistoriqueCat(h);
						getHistoriquecatservice().insertHistoriqueCat(
								historique);
					}
				}
			}*/
			
			initialize();
			Trace trace = new Trace();
			if (historique.getCategorie() != null) {
				trace.setAction("Insertion Historique de catégorique "
						+ historique.getCategorie().getDesigCat() + " pour "
						+ historique.getPersonnel().getNom() + " "
						+ getHistorique().getPersonnel().getPrenom());
				trace.setObjet("Historique de categorie");

			} else {
				trace.setAction("Insertion Historique avec "
						+ historique.getSequence().getDesigSeq() + " pour "
						+ historique.getPersonnel().getNom() + " "
						+ getHistorique().getPersonnel().getPrenom());
				trace.setObjet("Historique de sequence");

			}
			getTraceService().insertTrace(trace);
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:timeline");
			context.update("form:msg");
			TimelineUpdater.getCurrentInstance("form:timeline");
			FacesMessage msg = new FacesMessage("Historique Crée avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			historique = new HistoriqueCat();
			RequestContext.getCurrentInstance().reset("form2");			
			RequestContext.getCurrentInstance().update("form2");
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void updateHistorique() {
		try {

			selectedHistorique.setCategorie(getCategorieservice().getByid(
					idCategorie));

			// selectedHistorique.setPersonnel(getPersonnelService().getByid(idPersonnel));

			getHistoriquecatservice().updateHistoriqueCat(selectedHistorique);
			initialize();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:timeline");
			context.update("form:msg");
			context.execute("undisplaymodif()");
			TimelineUpdater.getCurrentInstance("form:timeline");
			Trace trace = new Trace();
			if (selectedHistorique.getCategorie() != null) {
				trace.setAction("Insertion Historique de catégorique "
						+ selectedHistorique.getCategorie().getDesigCat()
						+ " pour " + selectedHistorique.getPersonnel().getNom()
						+ " " + selectedHistorique.getPersonnel().getPrenom());
				trace.setObjet("Historique de categorie");

			} else {
				trace.setAction("Insertion Historique avec "
						+ selectedHistorique.getSequence().getDesigSeq()
						+ " pour " + selectedHistorique.getPersonnel().getNom()
						+ " " + selectedHistorique.getPersonnel().getPrenom());
				trace.setObjet("Historique de sequence");

			}
			getTraceService().insertTrace(trace);
			FacesMessage msg = new FacesMessage(
					"Historique modifié avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void deleteHistorique() {
		try {

			getHistoriquecatservice().deleteHistoriqueCat(selectedHistorique);
			initialize();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:timeline");
			TimelineUpdater.getCurrentInstance("form:timeline");
			Trace trace = new Trace();
			if (selectedHistorique.getCategorie() != null) {
				trace.setAction("Insertion Historique de catégorique "
						+ selectedHistorique.getCategorie().getDesigCat()
						+ " pour " + selectedHistorique.getPersonnel().getNom()
						+ " " + selectedHistorique.getPersonnel().getPrenom());
				trace.setObjet("Historique de categorie");

			} else {
				trace.setAction("Insertion Historique avec "
						+ selectedHistorique.getSequence().getDesigSeq()
						+ " pour " + selectedHistorique.getPersonnel().getNom()
						+ " " + selectedHistorique.getPersonnel().getPrenom());
				trace.setObjet("Historique de sequence");

			}
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

		Affiliation af = (Affiliation) selectednode.getData();
		if (af.getIdaff() == a.getIdaff()) {
			return true;

		}

		return false;
	}

	public void filtreAffiliation() {
		if (selectednode == null) {
			initialize();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:timeline");
			return;
		}
		listpersonnel = new ArrayList<Personnel>();
		listpersonnel.addAll(getPersonnelService().findAllPersonnels());

		// set initial start / end dates for the axis of the timeline
		Date now = new Date();

		// create timeline model
		model = new TimelineModel();

		for (Personnel p : listpersonnel) {
			now = new Date();
			Date end = new Date(now.getTime() - 12 * 60 * 60 * 1000);

			for (Historique h : p.getHistoriques()) {
				Date start = h.getDu();
				end = h.getAu();
				if (nodeContains(h.getAffiliation())) {
					String availability = "";
					if (h.getAffiliation().getType().equals("Di"))
						availability = "available";
					else if (h.getAffiliation().getType().equals("Se"))
						availability = "unavailable";
					else if (h.getAffiliation().getType().equals("Eq"))
						availability = "maybe";
					// create an event with content, start / end dates, editable
					// flag, group name and custom style class
					TimelineEvent event2 = new TimelineEvent(h, start, end,
							false, p.getCivilite() + " " + p.getPrenom()
									+ p.getNom(), availability);

					model.add(event2);
				}
			}
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:timeline");
	}

	public void displayDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.YEAR, Calendar.MONTH, 1, 0, 0, 0);
		start = cal.getTime();

		cal.set(Calendar.YEAR, Calendar.MONTH + 4, 30, 0, 0, 0);

		end = cal.getTime();
		System.out.println(end);
		initialize();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:timeline");
		TimelineUpdater.getCurrentInstance("form:timeline");

	}

	public void onSelect(TimelineSelectEvent e) {
		TimelineEvent timelineEvent = e.getTimelineEvent();
		selectedHistorique = (HistoriqueCat) timelineEvent.getData();

	}

	public String displayDetail(SequenceDetail s) {
		if (s != null) {
			if (s.getNbSeance() == 0) {
				return "Ropos";
			} else if (s.getNbSeance() == 1)
				return s.getE1() + " ==> " + s.getS1();
			else
				return s.getE1() + " ==> " + s.getS1() + " | " + s.getE2()
						+ " ==> " + s.getS2();
		}
		return "";
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

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public TreeNode getSelectednode() {
		return selectednode;
	}

	public void setSelectednode(TreeNode selectednode) {
		this.selectednode = selectednode;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public HistoriqueCatService getHistoriquecatservice() {
		return historiquecatservice;
	}

	public void setHistoriquecatservice(
			HistoriqueCatService historiquecatservice) {
		this.historiquecatservice = historiquecatservice;
	}

	public List<Personnel> getListpersonnel() {
		return listpersonnel;
	}

	public void setListpersonnel(List<Personnel> listpersonnel) {
		this.listpersonnel = listpersonnel;
	}

	public void setModel(TimelineModel model) {
		this.model = model;
	}

	public HistoriqueCat getHistorique() {
		return historique;
	}

	public void setHistorique(HistoriqueCat historique) {
		this.historique = historique;
	}

	public HistoriqueCat getSelectedHistorique() {
		return selectedHistorique;
	}

	public void setSelectedHistorique(HistoriqueCat selectedHistorique) {
		this.selectedHistorique = selectedHistorique;
	}

	public SequenceService getSequenceService() {
		return sequenceService;
	}

	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	public TraceService getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
	}

	public boolean isShowSeq() {
		return showSeq;
	}

	public void setShowSeq(boolean showSeq) {
		this.showSeq = showSeq;
	}

	public SequenceDetailService getSequenceDetailService() {
		return sequenceDetailService;
	}

	public void setSequenceDetailService(
			SequenceDetailService sequenceDetailService) {
		this.sequenceDetailService = sequenceDetailService;
	}

	public List<Sequence> getListsequence() {

		return listsequence;
	}

	public void setListsequence(List<Sequence> listsequence) {
		this.listsequence = listsequence;
	}

	public List<SequenceDetail> getDetailseq() {
		detailseq = new ArrayList<SequenceDetail>();
		detailseq.addAll(getSequenceService().findAllDayDetail(
				historique.getSequence()));
		System.out.println(detailseq.size());
		return detailseq;
	}

	public void setDetailseq(List<SequenceDetail> detailseq) {
		this.detailseq = detailseq;
	}

}
