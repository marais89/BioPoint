package org.bio.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.time.DateUtils;
import org.bio.model.Document;
import org.bio.model.HistoriqueCat;
import org.bio.model.Mvt;
import org.bio.model.MvtId;
import org.bio.model.Personnel;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.model.Terminaux;
import org.bio.service.HistoriqueService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.SequenceService;
import org.bio.service.TerminauxService;
import org.bio.service.TraceService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

@ManagedBean
@SessionScoped
public class MvtBean implements Serializable{

	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;

	@ManagedProperty(value = "#{terminauxServiceImpl}")
	private TerminauxService terminauxservice;
	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	@ManagedProperty(value = "#{historiqueServiceImpl}")
	private HistoriqueService historiqueservice;

	@ManagedProperty(value = "#{sequenceServiceImpl}")
	private SequenceService sequenceService;
	private Integer idTerminaux;
	private Mvt mvt;
	private Mvt selectedMvt;
	private List<Mvt> listmvt;
	private MvtId idmvt;
	@ManagedProperty("#{traceServiceImpl}")
	private TraceService traceService;
	private List<Personnel> listPersonnel;
	private List<Personnel> storedPersonnel;
	private List<Personnel> tmpselectedPersonnels;
	private List<Mvt> filtredlistmvt;
	private HashMap<String, Integer> listTerminaux;
	private List<Mvt> tmpEditlist;
	private boolean flagd;
	private boolean flagf;
	private int e1;
	private int e2;
	private int s1;
	private int s2;
	private boolean be1;
	private boolean be2;
	private boolean bs1;
	private boolean bs2;
	private Date startDate;
	private Date endDate;
	private boolean selectMode;
	
private boolean way;
	@PostConstruct
	private void init() {
		idmvt = new MvtId();
		flagf = false;
		flagd = false;
		way=false;
		e1 = s1 = e2 = s2 = 0;
		listPersonnel = new ArrayList<Personnel>();
		listTerminaux = new HashMap<String, Integer>();
		startDate = new Date();
		endDate = new Date();
		listmvt = new ArrayList<Mvt>();
		storedPersonnel = new ArrayList<Personnel>();
		mvt = new Mvt();
		tmpEditlist = new ArrayList<Mvt>();
	}

	@SuppressWarnings("deprecation")
	public void insertMvt() {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		try {System.out.println("enter");
			for (Personnel p : storedPersonnel) {
				System.out.println("enter 2");
				Sequence s = new Sequence();
				System.out.println("enter 3");
				s = getSequenceService().getCurrentSequence(p);
				System.out.println(s.getDesigSeq());
				System.out.println("enter 4");
			
				if (s!= null) {
					System.out.println("enter 5");
					Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Tunis"));
					System.out.println("enter 5.1");
					startDate.setTime(start.getTimeInMillis());
					System.out.println(startDate);
					while (!startDate.after(endDate)) {
						System.out.println("enter 5.2");
							cal.setTime(startDate);
						System.out.println("enter 6");

						SequenceDetail d;
						if (startDate.getDay() == 0) {
							d = getSequenceService().getjourDetail(s, 7);
						} else {
							d = getSequenceService().getjourDetail(s,
									startDate.getDay());
						}
						if (d != null) {
							if ((d.getE1() != null)) {
								MvtId id = new MvtId();
								id.setIdper(p.getIdper());
								id.setIdter(p.getTerminaux().getIdter());
								Mvt m = new Mvt();
								m.setId(id);
								m.setJour(startDate);
								m.setHeure(d.getE1());
								m.getHeure().setMinutes(
								m.getHeure().getMinutes() + e1);
								m.setEtatr("E");
								m.setPosition(1);
								m.setMode("Manuel");
								m.setJourLogique(cal.getTime());
								m.setPersonnel(p);
								m.setTerminaux(p.getTerminaux());
								m.setMoyen("Application");
								m.setEtat("new");
								getMvtService().insertMvt(m);
							}
							if ((d.getS1() != null)) {
								MvtId id = new MvtId();
								id.setIdper(p.getIdper());
								id.setIdter(p.getTerminaux().getIdter());
								Mvt m = new Mvt();
								m.setId(id);
								m.setJour(startDate);
								m.setJourLogique(cal.getTime());
								if(d.getS1().before(d.getE1()))
								{
							    m.getJour().setDate(m.getJour().getDate()+1);
								}
								m.setEtatr("S");
								m.setPosition(2);
								m.setHeure(d.getS1());
								m.getHeure().setMinutes(
								m.getHeure().getMinutes() + s1);
								m.setMode("Manuel");
								m.setPersonnel(p);
								m.setTerminaux(p.getTerminaux());
								m.setMoyen("Application");
								m.setEtat("new");
								getMvtService().insertMvt(m);
							}
							if ((d.getE2() != null)) {
								MvtId id = new MvtId();
								id.setIdper(p.getIdper());
								id.setIdter(p.getTerminaux().getIdter());
								Mvt m = new Mvt();
								m.setId(id);
								m.setJour(startDate);
								m.setJourLogique(cal.getTime());
								if(d.getE2().before(d.getS1()))
								{
									m.getJour().setDate(m.getJour().getDate()+1);
								}
								m.setEtatr("E");
								m.setPosition(3);
								m.setHeure(d.getE2());
								m.getHeure().setMinutes(
								m.getHeure().getMinutes() + e2);
								m.setMode("Manuel");
								m.setPersonnel(p);
								m.setTerminaux(p.getTerminaux());
								m.setMoyen("Application");
								m.setEtat("new");
								getMvtService().insertMvt(m);
							}
							if ((d.getS2() != null)) {
								MvtId id = new MvtId();
								id.setIdper(p.getIdper());
								id.setIdter(p.getTerminaux().getIdter());
								Mvt m = new Mvt();
								m.setId(id);
								m.setJour(startDate);
								m.setJourLogique(cal.getTime());
								if(d.getS2().before(d.getS2()))
								{
									m.getJour().setDate(m.getJour().getDate()+1);
								}
								m.setEtatr("S");
								m.setPosition(4);
								m.setHeure(d.getS2());
								m.getHeure().setMinutes(
								m.getHeure().getMinutes() + s2);
								m.setMode("Manuel");
								m.setPersonnel(p);
								m.setTerminaux(p.getTerminaux());
								m.setMoyen("Application");
								m.setEtat("new");
								getMvtService().insertMvt(m);
							}
						}
						startDate.setDate(startDate.getDate() + 1);
					}

				}
				
				else if (personnelService
						.getcurrentHistoriqueCat(p) != null) {
System.out.println("enter 6");
				
					 SequenceDetail    d;
					HistoriqueCat hc = personnelService
							.getcurrentHistoriqueCat(p);
				s = hc.getSequence();
						System.out.println("enter 7");

						Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Tunis"));
						while (!startDate.after(endDate)) {
							System.out.println("enter 8");

cal.setTime(startDate);
                     int seq = (getdiffrence(startDate,hc.getDu()))-(s.getLongueurSequence()-hc.getClejour()+1);
int resultat = seq%s.getLongueurSequence();
if(resultat==0)
{
                   d = getSequenceService().getjourDetail(s,s.getLongueurSequence() );
}
else {
	 d = getSequenceService().getjourDetail(s,resultat);	
}
System.out.println("enter 8");
System.out.println(d);

             	if (d != null) {
             		System.out.println("enter 9");

					if ((d.getE1() != null)) {
						MvtId id = new MvtId();
						id.setIdper(p.getIdper());
						id.setIdter(p.getTerminaux().getIdter());
						Mvt m = new Mvt();
						m.setId(id);
						m.setJour(startDate);
						m.setJourLogique(cal.getTime());
						m.setEtatr("E");
						m.setPosition(1);
						m.setHeure(d.getE1());
						m.getHeure().setMinutes(
						m.getHeure().getMinutes() + e1);
						m.setMode("Manuel");
						m.setPersonnel(p);
						m.setTerminaux(p.getTerminaux());
						m.setMoyen("Application");
						m.setEtat("new");
						getMvtService().insertMvt(m);
					}
					if ((d.getS1() != null)) {
						MvtId id = new MvtId();
						id.setIdper(p.getIdper());
						id.setIdter(p.getTerminaux().getIdter());
						Mvt m = new Mvt();
						m.setId(id);
						m.setJourLogique(cal.getTime());
						m.setJour(startDate);
						if(d.getS1().before(d.getE1()))
						{
							m.getJour().setDate(m.getJour().getDate()+1);
						}
						m.setEtatr("S");
						m.setPosition(2);
						m.setHeure(d.getS1());
						m.getHeure().setMinutes(
						m.getHeure().getMinutes() + s1);
						m.setMode("Manuel");
						m.setPersonnel(p);
						m.setTerminaux(p.getTerminaux());
						m.setMoyen("Application");
						m.setEtat("new");
						getMvtService().insertMvt(m);
					}
					if ((d.getE2() != null)) {
						MvtId id = new MvtId();
						id.setIdper(p.getIdper());
						id.setIdter(p.getTerminaux().getIdter());
						Mvt m = new Mvt();
						m.setId(id);
						m.setJour(startDate);
						m.setJourLogique(cal.getTime());
						if(d.getE2().before(d.getS1()))
						{
							m.getJour().setDate(m.getJour().getDate()+1);
						}
						m.setEtatr("E");
						m.setPosition(3);
						m.setHeure(d.getE2());
						m.getHeure().setMinutes(
						m.getHeure().getMinutes() + e2);
						m.setMode("Manuel");
						m.setPersonnel(p);
						m.setTerminaux(p.getTerminaux());
						m.setMoyen("Application");
						m.setEtat("new");
						getMvtService().insertMvt(m);
					}
					if ((d.getS2() != null)) {
						MvtId id = new MvtId();
						id.setIdper(p.getIdper());
						id.setIdter(p.getTerminaux().getIdter());
						Mvt m = new Mvt();
						m.setId(id);
						m.setJour(startDate);
						m.setJourLogique(cal.getTime());
						if(d.getS2().before(d.getE2()))
 {
										m.getJour().setDate(
												m.getJour().getDate() + 1);
									}
						m.setEtatr("S");
						m.setPosition(4);
						m.setHeure(d.getS2());
						m.getHeure().setMinutes(
						m.getHeure().getMinutes() + s2);
						m.setMode("Manuel");
						m.setPersonnel(p);
						m.setTerminaux(p.getTerminaux());
						m.setMoyen("Application");
						m.setEtat("new");
						getMvtService().insertMvt(m);
					}
				}
             	startDate.setDate(startDate.getDate() + 1);	
						}		
					
				}
			}
			FacesMessage msg = new FacesMessage("Movement Crée avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext.getCurrentInstance().reset("formajoutt");
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getStackTrace();
		}
	}

	private int getdiffrence(Date d1,Date d2) {
		
		int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
		return diffInDays +1;
	}

	public void loadHerarchy() {
		try {
			for (Personnel p : storedPersonnel) {
				Sequence s =  getSequenceService().findCurrentDaySequence(p);

				HistoriqueCat hc = personnelService
						.getcurrentHistoriqueCat(p);
				if (hc != null) {
               int seq = (getdiffrence(new Date(),hc.getDu()))-(s.getLongueurSequence()-hc.getClejour()+1);
                 System.out.println(p.getNom()+"  "+seq+"  "+(seq%s.getLongueurSequence()));
				}

			}
		} catch (Exception e) {

		}
	}

	
	public void updateMvts() {
		try {

			for (Mvt m : listmvt) {
				getMvtService().updateMvt(m);
			}
			FacesMessage msg = new FacesMessage(
					"Movement enregistré avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
System.out.println(e.getStackTrace());
System.out.println(e.getMessage());
System.out.println(e.getCause());
		}
	}

	public void addPointage() {
		Date d = new Date();
		System.out.println("enter 1");
		System.out.println(storedPersonnel.size());
		for (Personnel p : storedPersonnel) {
			MvtId id = new MvtId();

			id.setIdper(p.getIdper());
			id.setIdter(p.getTerminaux().getIdter());
			System.out.println("enter 2");
			Mvt m = new Mvt();
			m.setMode("Manuel");
			m.setMoyen("Application");
			m.setJourLogique(d);
			System.out.println("enter 3");
            m.setEtatr("S");
			m.setJour(d);
			m.setHeure(d);
			m.setId(id);
			m.setPersonnel(p);
			System.out.println("enter 4");
			listmvt.add(m);
			System.out.println("enter 5");

		}
	}
	
public void switchway()
{
	System.out.println("enter");
	way=!way;
}
	public void addpersonnel() {
		listPersonnel = new ArrayList<Personnel>();
		listPersonnel.addAll(tmpselectedPersonnels);
		storedPersonnel = listPersonnel;
		tmpselectedPersonnels = new ArrayList<Personnel>();
	}

	public void removepersonnel() {
		listPersonnel.removeAll(storedPersonnel);
	}

	public void loadDaymvts() {
		listmvt.clear();
		flagf = false;
		flagd = false;
		Date d = new Date();
		for (Personnel p : storedPersonnel) {

			for (Mvt m : getPersonnelService().listMvt(p)) {
				if ((DateUtils.isSameDay(m.getJour(), d))
						&& (!listmvt.contains(m))) {
					listmvt.add(m);
				}
			}

		}
		System.out.println(listmvt.size());

	}

	public void removeAllMvts() {
		listmvt.clear();
	}

	public void loadmvtsbyDate(SelectEvent event) {
		System.out.println(storedPersonnel.size());

		listmvt.clear();
		if (!flagf) {

			for (Personnel p : storedPersonnel) {
				for (Mvt m : getPersonnelService().listMvt(p)) {
					if (DateUtils.isSameDay(m.getJour(), startDate)
							&& (!listmvt.contains(m))) {
						listmvt.add(m);
					}
				}

			}
		} else {

			for (Personnel p : storedPersonnel) {
				for (Mvt m : getPersonnelService().listMvt(p)) {
					if ((m.getJour().after(startDate))
							&& (m.getJour().before(endDate))
							&& (!listmvt.contains(m))) {
						listmvt.add(m);
					}
				}

			}
		}

	}

	public void activateInDatefield() {
		flagd = true;
		flagf = true;
	}

	public void activateDatefield() {
		flagd = true;
		flagf = false;
	}

	public void loadmvts() {
		flagf = false;
		flagd = false;
		listmvt.clear();
		System.out.println("er1");
		try 
		{		
			for (Personnel p : storedPersonnel) 
			{
				listmvt.addAll(getPersonnelService().listMvt(p));
				System.out.println("erii");
			}
		} catch (Exception e) {
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Tu dois choisir une personnel !", null));			
			RequestContext.getCurrentInstance().update("formmvt:msg");
			return;
		}
	}

	public String convertDate(Date d) 
	{
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(d);		
	}

	public void onEdit(RowEditEvent event) {
		tmpEditlist.add((Mvt) event.getObject());
	}

	public void removeMvt() {
		listmvt.remove(selectedMvt);
	}

	public List<Personnel> getListPersonnel() {
		return listPersonnel;
	}

	public void setListPersonnel(List<Personnel> listPersonnel) {
		this.listPersonnel = listPersonnel;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public TerminauxService getTerminauxservice() {
		for (Terminaux t : getTerminauxservice().findAllTerminaux()) {
			listTerminaux.put(t.getLibelle(), t.getIdter());
		}
		return terminauxservice;
	}

	public void setTerminauxservice(TerminauxService terminauxservice) {
		this.terminauxservice = terminauxservice;
	}

	public Integer getIdTerminaux() {
		return idTerminaux;
	}

	public void setIdTerminaux(Integer idTerminaux) {
		this.idTerminaux = idTerminaux;
	}

	public Mvt getMvt() {
		return mvt;
	}

	public void setMvt(Mvt mvt) {
		this.mvt = mvt;
	}

	public Mvt getSelectedMvt() {
		return selectedMvt;
	}

	public void setSelectedMvt(Mvt selectedMvt) {
		this.selectedMvt = selectedMvt;
	}

	public List<Mvt> getListmvt() {

		return listmvt;
	}

	public void setListmvt(List<Mvt> listmvt) {
		this.listmvt = listmvt;
	}

	public MvtId getIdmvt() {
		return idmvt;
	}

	public void setIdmvt(MvtId idmvt) {
		this.idmvt = idmvt;
	}

	public List<Mvt> getFiltredlistmvt() {
		return filtredlistmvt;
	}

	public void setFiltredlistmvt(List<Mvt> filtredlistmvt) {
		this.filtredlistmvt = filtredlistmvt;
	}

	public HashMap<String, Integer> getListTerminaux() {
		return listTerminaux;
	}

	public void setListTerminaux(HashMap<String, Integer> listTerminaux) {
		this.listTerminaux = listTerminaux;
	}

	public List<Personnel> getTmpselectedPersonnels() {
		return tmpselectedPersonnels;
	}

	public void setTmpselectedPersonnels(List<Personnel> tmpselectedPersonnels) {
		this.tmpselectedPersonnels = tmpselectedPersonnels;
	}

	public MvtService getMvtService() {
		return mvtService;
	}

	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}

	public List<Personnel> getStoredPersonnel() {
		return storedPersonnel;
	}

	public void setStoredPersonnel(List<Personnel> storedPersonnel) {
		this.storedPersonnel = storedPersonnel;
	}

	public List<Mvt> getTmpEditlist() {
		return tmpEditlist;
	}

	public void setTmpEditlist(List<Mvt> tmpEditlist) {
		this.tmpEditlist = tmpEditlist;
	}

	public boolean isFlagd() {
		return flagd;
	}

	public void setFlagd(boolean flagd) {
		this.flagd = flagd;
	}

	public boolean isFlagf() {
		return flagf;
	}

	public void setFlagf(boolean flagf) {
		this.flagf = flagf;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public HistoriqueService getHistoriqueservice() {
		return historiqueservice;
	}

	public void setHistoriqueservice(HistoriqueService historiqueservice) {
		this.historiqueservice = historiqueservice;
	}

	public boolean isSelectMode() {
		return selectMode;
	}

	public void setSelectMode(boolean selectMode) {
		this.selectMode = selectMode;
	}

	public SequenceService getSequenceService() {
		return sequenceService;
	}

	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	public int getE1() {
		return e1;
	}

	public void setE1(int e1) {
		this.e1 = e1;
	}

	public int getE2() {
		return e2;
	}

	public void setE2(int e2) {
		this.e2 = e2;
	}

	public int getS1() {
		return s1;
	}

	public void setS1(int s1) {
		this.s1 = s1;
	}

	public int getS2() {
		return s2;
	}

	public void setS2(int s2) {
		this.s2 = s2;
	}

	public boolean isBe1() {
		return be1;
	}

	public void setBe1(boolean be1) {
		this.be1 = be1;
	}

	public boolean isBe2() {
		return be2;
	}

	public void setBe2(boolean be2) {
		this.be2 = be2;
	}

	public boolean isBs1() {
		return bs1;
	}

	public void setBs1(boolean bs1) {
		this.bs1 = bs1;
	}

	public boolean isBs2() {
		return bs2;
	}

	public void setBs2(boolean bs2) {
		this.bs2 = bs2;
	}

	public TraceService getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
	}

	public boolean isWay() {
		return way;
	}

	public void setWay(boolean way) {
		this.way = way;
	}
}