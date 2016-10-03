package org.bio.web;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.LostMvt;
import org.bio.model.Mvt;
import org.bio.model.MvtId;
import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.PersonnelTerminalId;
import org.bio.model.Terminaux;
import org.bio.service.LostMvtService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.PersonnelTerminalService;
import org.bio.service.PointageService;
import org.bio.service.TerminauxService;
import org.bio.util.Alert;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;


@ManagedBean
@ViewScoped
public class AlertBean implements Serializable {
	private List<Personnel> listpersonnel;
	@ManagedProperty(value="#{personnelTerminalServiceImpl}")
	private PersonnelTerminalService persTerminaService;
	private List<LostMvt> listmvt;
	@ManagedProperty(value="#{lostMvtServiceImpl}")
	private LostMvtService lostmvtService;
	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	
	private List<Alert> listalert;
	@ManagedProperty(value="#{personnelServiceImpl}")
	private PersonnelService personnelService;
	private List<Mvt> listMvtCorrect;
	
	private List<PersonnelTerminal> listterminaux;
	private List<Mvt> mvtTosave;
	private List<Personnel> listPersonnels;
	private Mvt selectedMvt;
	@ManagedProperty(value=("#{terminauxServiceImpl}"))
	private TerminauxService terminauxService;
	@ManagedProperty(value="#{pointageServiceImpl}")
	private PointageService pointageService;
	@PostConstruct
	private void init()
	{
		listMvtCorrect = new ArrayList<Mvt>();
		listmvt = new ArrayList<LostMvt>();
		mvtTosave=new ArrayList<Mvt>();
		listPersonnels= new ArrayList<Personnel>();
		listPersonnels.addAll(getPersonnelService().findAllWithoutPersonnels());
		listalert=new ArrayList<Alert>();
		listpersonnel= getPersonnelService().PersonnelswithoutEmpreinte();
		if(listpersonnel.size()>0)
		{
			Alert alert=new Alert();
	if(listpersonnel.size()<10)
	{
		alert.setNbalert("1+");
	}
	else if(listpersonnel.size()>10)
	{
		alert.setNbalert("10+");

	}
	else {
		alert.setNbalert("100+");

	}
	alert.setUrl(Faces.evaluateExpressionGet("#{request.contextPath}")+"/pages/personel.xhtml");
			alert.setIcon("/resources/img/personal.png");
			alert.setDescription("Empreinte(s) manquant(s)");
			listalert.add(alert);
		}
		listmvt=getLostmvtService().findAllLostMvts();
		if(listmvt.size()>0)
		{
			Alert alert=new Alert();
			if(listmvt.size()<10)
			{
				alert.setNbalert("1+");
			}
			else if(listmvt.size()>10)
			{
				alert.setNbalert("10+");

			}
			else {
				alert.setNbalert("100+");
			}			alert.setIcon("/resources/img/hand.png");
			alert.setAction("PF('correctDiag').show()");
			alert.setDescription("Pointage(s) non identifiés");
			convertTomvt();
			listalert.add(alert);
		}
		Long nb=getTerminauxService().terminauxToupdate();
		if(nb>0)
		{
			Alert alert=new Alert();
			if(nb<10)
			{
				alert.setNbalert("1+");
			}
			else if(nb>10)
			{
				alert.setNbalert("10+");

			}
			else {
				alert.setNbalert("100+");

			}			alert.setIcon("/resources/img/finger.png");
			alert.setUrl(Faces.evaluateExpressionGet("#{request.contextPath}")+"/pages/console.xhtml");

			alert.setDescription("Terminaux non synchronizer");
			listalert.add(alert);
		}
		 nb=getPointageService().findPointagesAnomalie();
		if(nb>0)
		{
			Alert alert=new Alert();
			if(nb<10)
			{
				alert.setNbalert("1+");
			}
			else if(nb>10)
			{
				alert.setNbalert("10+");

			}
			else {
				alert.setNbalert("100+");
				alert.setUrl("#");

			}			alert.setIcon("/resources/img/alert.png");
			alert.setDescription("Anomalie au niveau des pointages");
			listalert.add(alert);
		}
		
	}
	public void onedit(RowEditEvent e)
	{Mvt m =(Mvt) e.getObject();
	System.out.println("e1");
	m.getId().setIdper(m.getPersonnel().getIdper());
	System.out.println("e2");		
	mvtTosave.add(m);
	System.out.println("e3");
			
	
	}
	public List<Personnel> getListpersonnel() {
	
		return listpersonnel;
	}
	private int find(Mvt m)
	{
		for(Mvt mvt:listMvtCorrect)
		{
			if((mvt.getBckId()==m.getBckId())&&(mvt.getId().getIdter()==m.getId().getIdter()))
			{
				return listMvtCorrect.indexOf(mvt);
			}
		}
	return -1;
	}
	private void updateLostMvt()
	{
		try {
		for(LostMvt lm:listmvt)	
		{
			for(Mvt mvt:mvtTosave)
			{
				for(Mvt m:mvt.getListmvt())
				{
					if((m.getBckId()==lm.getId().getBckId())&&(lm.getTerminaux().getIdter()==m.getId().getIdter()))
					{
						getLostmvtService().deleteLostMvt(lm);
					}
				}
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void convertTomvt()
	{ listMvtCorrect.clear();
		if(listmvt.size()>0)
		{System.out.println("ici");
			System.out.println("enter 2");
			for(LostMvt lm:listmvt)
			{
			Mvt m =new Mvt();
			MvtId id= new MvtId();
			id.setIdter(lm.getTerminaux().getIdter());
			m.setBckId(lm.getId().getBckId());
			m.setHeure(lm.getId().getHeure());
			m.setJourLogique(lm.getJourLogique());
			m.setJour(lm.getId().getJour());
			m.setMoyen(lm.getMoyen());
			m.setMode(lm.getMode());

			m.setId(id);
			m.setBcketat(lm.getBcketat());
			m.setTerminaux(lm.getTerminaux());
			int x = find(m);
			if(x==-1)
			{
				m.getListmvt().add(m);
			listMvtCorrect.add(m);

			}
			else
			{
				m.setTerminaux(listMvtCorrect.get(x).getTerminaux());
				m.setPersonnel(listMvtCorrect.get(x).getPersonnel());
	m.setId(listMvtCorrect.get(x).getId() );
				listMvtCorrect.get(x).getListmvt().add(m);
			}
			}
			
		}
	}
	public void insertMVt()
	{System.out.println("enter 3");
		try {
			for(Mvt m:mvtTosave)
			{
				for(Mvt mvt:m.getListmvt())
				{mvt.getId().setIdper(m.getId().getIdper());
				mvt.setPersonnel(m.getPersonnel());
					getMvtService().insertMvt(mvt);
					PersonnelTerminal pt = new PersonnelTerminal();
					PersonnelTerminalId id= new PersonnelTerminalId(m.getId().getIdper(),m.getId().getIdter());
					pt.setId(id);
					pt.setBckId(m.getBckId());
					pt.setPersonnel(m.getPersonnel());
					pt.setUploaded(true);
					pt.setTerminaux(m.getTerminaux());
					getPersTerminaService().insertPersonnelTerminal(pt);;
				}
			}
			updateLostMvt();
			FacesMessage msg = new FacesMessage("Mouvements courrigés avec succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			// TODO: handle exception
System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage("erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public void setListpersonnel(List<Personnel> listpersonnel) {
		this.listpersonnel = listpersonnel;
	}
	public List<LostMvt> getListmvt() {
		return listmvt;
	}
	public void setListmvt(List<LostMvt> listmvt) {
		this.listmvt = listmvt;
	}
	public LostMvtService getLostmvtService() {
		return lostmvtService;
	}
	public void setLostmvtService(LostMvtService lostmvtService) {
		this.lostmvtService = lostmvtService;
	}
	public List<Alert> getListalert() {
		
		return listalert;
	}
	public void setListalert(List<Alert> listalert) {
		this.listalert = listalert;
	}
	public PersonnelService getPersonnelService() {
		return personnelService;
	}
	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}
	
	public List<PersonnelTerminal> getListterminaux() {
		return listterminaux;
	}
	public void setListterminaux(List<PersonnelTerminal> listterminaux) {
		this.listterminaux = listterminaux;
	}

	public List<Mvt> getListMvtCorrect() {
		return listMvtCorrect;
	}

	public void setListMvtCorrect(List<Mvt> listMvtCorrect) {
		this.listMvtCorrect = listMvtCorrect;
	}
	public MvtService getMvtService() {
		return mvtService;
	}
	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}
	public List<Personnel> getListPersonnels() {
		return listPersonnels;
	}
	public void setListPersonnels(List<Personnel> listPersonnels) {
		this.listPersonnels = listPersonnels;
	}
	public Mvt getSelectedMvt() {
		return selectedMvt;
	}
	public void setSelectedMvt(Mvt selectedMvt) {
		this.selectedMvt = selectedMvt;
	}
	public List<Mvt> getMvtTosave() {
		return mvtTosave;
	}
	public void setMvtTosave(List<Mvt> mvtTosave) {
		this.mvtTosave = mvtTosave;
	}
	public TerminauxService getTerminauxService() {
		return terminauxService;
	}
	public void setTerminauxService(TerminauxService terminauxService) {
		this.terminauxService = terminauxService;
	}
	public PersonnelTerminalService getPersTerminaService() {
		return persTerminaService;
	}
	public void setPersTerminaService(PersonnelTerminalService persTerminaService) {
		this.persTerminaService = persTerminaService;
	}
	public PointageService getPointageService() {
		return pointageService;
	}
	public void setPointageService(PointageService pointageService) {
		this.pointageService = pointageService;
	}


}
