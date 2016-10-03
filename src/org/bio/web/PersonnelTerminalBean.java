package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.PersonnelTerminalId;
import org.bio.model.Terminaux;
import org.bio.service.PersonnelService;
import org.bio.service.PersonnelTerminalService;
import org.bio.service.TerminauxService;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

@ManagedBean
@ViewScoped
public class PersonnelTerminalBean implements Serializable{
	
	@ManagedProperty(value=("#{personnelTerminalServiceImpl}"))
	private PersonnelTerminalService personnelTerminalService;
	@ManagedProperty(value=("#{terminauxServiceImpl}"))
	private TerminauxService terminauxService;
	@ManagedProperty(value="#{personnelServiceImpl}")
	private  PersonnelService personnelService;
	
	private Personnel personnel;
	private Terminaux terminal;
	private PersonnelTerminal personnelterminal;
	private PersonnelTerminalId id;
	private List<PersonnelTerminal> listpersonnelterminal;
	private int sizeliste;
	private List<PersonnelTerminal> listetpselectionner;
	private List<Terminaux> filtredlisttp;
	private PersonnelTerminal tpselectionner;
	private boolean flag;
	private List<Terminaux> listterminaux;
	private List<Terminaux> selectListTerminaux;
	private List<Personnel> listpersonnels;
	private List<Personnel> selectListpersonnels;
	private List<Integer> listIdPer;
	private List<Integer> listIdTer;
	
	private HashMap<String,Integer>  hlistpersonnel;
	private HashMap<String,Integer>  hlistterminaux;
	
	private PersonnelTerminalId perstermId;
	
	@PostConstruct
	public void init()
	{
		this.listetpselectionner = new ArrayList<PersonnelTerminal>();
		this.personnelterminal=new PersonnelTerminal();
		this.listpersonnelterminal= new ArrayList<PersonnelTerminal>();
		this.listpersonnelterminal=personnelTerminalService.findAllPersonnelTerminals();
		flag=true;
		this.selectListTerminaux= new ArrayList<Terminaux>();
		this.selectListpersonnels= new ArrayList<Personnel>();
		this.listpersonnels=personnelService.findAllPersonnels();
		this.listterminaux=terminauxService.findAllTerminaux();	
		this.hlistpersonnel = new HashMap<String,Integer>();
		this.hlistterminaux = new HashMap<String,Integer>();
		listIdPer = new ArrayList<Integer>();
		listIdTer = new ArrayList<Integer>();
		remplirhlistpers();
		remplirhlistterm();
		this.perstermId = new PersonnelTerminalId();
	}
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
		tpselectionner = (PersonnelTerminal) event.getObject();
	}
	
	//fct detection des personnel par id de (listidper) pou remplir listpersonnels
	public void detectIdPer()
	{ 
		selectListpersonnels.clear();
		for(int i=0; i<listIdPer.size();i++)
		{
			selectListpersonnels.add(personnelService.getByid(listIdPer.get(i)));
		}
	}
	
	//fct detection des terminaux par id de listidterm pour remplir listterminaux
	public void detectIdterm()
	{ 
		selectListTerminaux.clear();
		for(int i=0; i<listIdTer.size();i++)
		{
			selectListTerminaux.add(terminauxService.getByid(listIdTer.get(i)));
		}
	}
	
	//fct emplissage hlistterminaux
	public void remplirhlistterm()
	{
		for(int i=0;i<this.listterminaux.size();i++)
		{
			this.hlistterminaux.put(listterminaux.get(i).getLibelle()+":"+listterminaux.get(i).getNserie(),listterminaux.get(i).getIdter());
		}
	}
	
	//fct remplissage hlistpersonnel
	public void remplirhlistpers()
	{
		for(int i=0;i<this.listpersonnels.size();i++)
		{
			this.hlistpersonnel.put(listpersonnels.get(i).getMatricule()+":"+listpersonnels.get(i).getNom()+listpersonnels.get(i).getPrenom(),listpersonnels.get(i).getIdper());
		}
	}
	
	// fct d'activation ou de désactivation de la colonne de selection // 
			public void activate ()
			{
				setFlag(!this.flag);			
			}
			
	//fct ajouter liste des relations personnels -terminaux 
			public void ajouterPerTer()
			{
				for(int i=0;i<selectListpersonnels.size();i++)
				{
					for(int j=0;j<selectListTerminaux.size();j++)
					{
						try{
						getPersonnelterminal().setPersonnel(getSelectListpersonnels().get(i));
						getPersonnelterminal().setTerminaux(selectListTerminaux.get(j));
						getPerstermId().setIdper(selectListpersonnels.get(i).getIdper());
						getPerstermId().setIdter(selectListTerminaux.get(j).getIdter());
						getPersonnelterminal().setId(perstermId);
						getPersonnelTerminalService().insertPersonnelTerminal(personnelterminal);
						FacesMessage msg = new FacesMessage("Affectation terminé avec Succés");  
				        FacesContext.getCurrentInstance().addMessage(null, msg);
						}
						catch(DataAccessException e){
							FacesMessage msg = new FacesMessage("Erreur");  
					        FacesContext.getCurrentInstance().addMessage(null, msg);
							e.getMessage();
						}
					}
				}
			}
	
	
	///////////////// gatters and sertters  //////////////
	
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public Terminaux getTerminal() {
		return terminal;
	}
	public void setTerminal(Terminaux terminal) {
		this.terminal = terminal;
	}
	public PersonnelTerminal getPersonnelterminal() {
		return personnelterminal;
	}
	public void setPersonnelterminal(PersonnelTerminal personnelterminal) {
		this.personnelterminal = personnelterminal;
	}
	public PersonnelTerminalId getId() {
		return id;
	}
	public void setId(PersonnelTerminalId id) {
		this.id = id;
	}
	public PersonnelTerminalService getPersonnelTerminalService() {
		return personnelTerminalService;
	}
	public void setPersonnelTerminalService(
			PersonnelTerminalService personnelTerminalService) {
		this.personnelTerminalService = personnelTerminalService;
	}
	public List<PersonnelTerminal> getListpersonnelterminal() {
		return listpersonnelterminal;
	}
	public void setListpersonnelterminal(
			List<PersonnelTerminal> list) {
		this.listpersonnelterminal = list;
	}

	public List<PersonnelTerminal> getListetpselectionner() {
		return listetpselectionner;
	}
	public void setListetpselectionner(List<PersonnelTerminal> listetpselectionner) {
		this.listetpselectionner = listetpselectionner;
	}
	public List<Terminaux> getFiltredlisttp() {
		return filtredlisttp;
	}
	public void setFiltredlisttp(List<Terminaux> filtredlisttp) {
		this.filtredlisttp = filtredlisttp;
	}

	public PersonnelTerminal getTpselectionner() {
		return tpselectionner;
	}

	public void setTpselectionner(PersonnelTerminal tpselectionner) {
		this.tpselectionner = tpselectionner;
	}

	public int getSizeliste() {
		sizeliste = listetpselectionner.size();
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

	public List<Terminaux> getListterminaux() {
		return listterminaux;
	}

	public void setListterminaux(List<Terminaux> listterminaux) {
		this.listterminaux = listterminaux;
	}

	public List<Personnel> getListpersonnels() {
		return listpersonnels;
	}
	public void setListpersonnels(List<Personnel> listpersonnels) {
		this.listpersonnels = listpersonnels;
	}

	public TerminauxService getTerminauxService() {
		return terminauxService;
	}

	public void setTerminauxService(TerminauxService terminauxService) {
		this.terminauxService = terminauxService;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public List<Terminaux> getSelectListTerminaux() {
		return selectListTerminaux;
	}

	public void setSelectListTerminaux(List<Terminaux> selectListTerminaux) {
		this.selectListTerminaux = selectListTerminaux;
	}

	public List<Personnel> getSelectListpersonnels() {
		return selectListpersonnels;
	}

	public void setSelectListpersonnels(List<Personnel> selectListpersonnels) {
		this.selectListpersonnels = selectListpersonnels;
	}

	public HashMap<String, Integer> getHlistpersonnel() {
		return hlistpersonnel;
	}

	public void setHlistpersonnel(HashMap<String, Integer> hlistpersonnel) {
		this.hlistpersonnel = hlistpersonnel;
	}

	public List<Integer> getListIdPer() {
		return listIdPer;
	}

	public void setListIdPer(List<Integer> listIdPer) {
		this.listIdPer = listIdPer;
	}

	public List<Integer> getListIdTer() {
		return listIdTer;
	}

	public void setListIdTer(List<Integer> listIdTer) {
		this.listIdTer = listIdTer;
	}

	public HashMap<String, Integer> getHlistterminaux() {
		return hlistterminaux;
	}

	public void setHlistterminaux(HashMap<String, Integer> hlistterminaux) {
		this.hlistterminaux = hlistterminaux;
	}

	public PersonnelTerminalId getPerstermId() {
		return perstermId;
	}

	public void setPerstermId(PersonnelTerminalId perstermId) {
		this.perstermId = perstermId;
	}


}
