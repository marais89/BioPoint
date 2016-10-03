package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Calendrier;
import org.bio.model.Categorie;
import org.bio.model.Horaires;
import org.bio.model.HorairesId;
import org.bio.model.Sequence;
import org.bio.service.CalendrierService;
import org.bio.service.CategorieService;
import org.bio.service.HoraireService;
import org.bio.service.SequenceService;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;
import org.w3c.dom.ls.LSException;

@ManagedBean
@SessionScoped
public class HoraireBean implements Serializable {

	@ManagedProperty(value = ("#{horaireServiceImpl}"))
	HoraireService horaireService;
	@ManagedProperty(value = ("#{sequenceServiceImpl}"))
	SequenceService sequenceService;
	@ManagedProperty(value = ("#{calendrierServiceImpl}"))
	CalendrierService calendrierService;
	@ManagedProperty(value = ("#{categorieServiceImpl}"))
	CategorieService categorieService;

	private Horaires horaire;
	private Sequence sequence;
	private Sequence sequence2;
	private Calendrier calendrier;
	private Categorie categorie;
	private List<Sequence> listSequence;
	private List<Sequence> listSequence2;
	private List<Categorie> listCategorie;
	private List<Calendrier> listCalendrier;
	private int sizeliste;
	private ArrayList<ArrayList<Sequence>> listehoraire;
	private ArrayList<ArrayList<Sequence>> listehoraire2;
	private HorairesId horaireId;
	private HashMap<String,Sequence> hashlistsequences;
	private HashMap<String,Integer> hashlistsequences2;
	private ArrayList<Sequence> ls ;
	private List<Integer> indice1= new ArrayList<Integer>();
	private List<Integer> indice2= new ArrayList<Integer>();
	private List<Sequence> valeur= new ArrayList<Sequence>();
	
	@PostConstruct
	public void init() {
		this.horaire = new Horaires();
		this.horaireId= new HorairesId();
		this.sequence = new Sequence();
		this.calendrier = new Calendrier();
		this.categorie = new Categorie();
		this.listSequence = new ArrayList<Sequence>();
		this.listSequence = sequenceService.findAllSequences();
		this.listSequence2 = new ArrayList<Sequence>();
		this.listCategorie = new ArrayList<Categorie>();
		this.listCategorie = categorieService.findAllCategories();
		this.listCalendrier = new ArrayList<Calendrier>();
		this.listCalendrier = calendrierService.findAllCalendriers();
		this.listehoraire = new ArrayList<ArrayList<Sequence>>();
		this.listehoraire2 = new ArrayList<ArrayList<Sequence>>();
		this.hashlistsequences= new HashMap<String, Sequence>();
		this.hashlistsequences2= new HashMap<String,Integer>();
		this.sequence2= new Sequence();
		this.sequence2= sequenceService.findSequence("Standard").get(0);
		changeformsortie();
		remplirhash();
		this.listCategorie = new ArrayList<Categorie>();
		this.listCategorie = categorieService.findAllCategories();
		this.listCalendrier = new ArrayList<Calendrier>();
		this.listCalendrier = calendrierService.findAllCalendriers();
	}
	
	//fct ajouter la sequence 'sequence2' a la liste horaire
	public void attribuer(int i, int j)
	{
		this.listehoraire.get(i).set(j,this.sequence2);
		for(int x =0;x<this.listCategorie.size()-1;x++){
			for(int y=0;y<this.listCategorie.size()-1;y++){
				}}
	}

	//fct remplir hashlistesequences
	public void remplirhash()
	{
		for (int i=0; i<listSequence.size();i++)
		{
			if(listSequence.get(i).getTypeSeq().equals("semaine")||listSequence.get(i).getTypeSeq().equals("sans_Planing") )
			{			
				hashlistsequences.put(listSequence.get(i).getDesigSeq(),listSequence.get(i)); 
			}
		}
	}
	
	// fct pour changer la format de la table horaire a la format de sortie
	public void changeformsortie() {
		//this.listCalendrier = calendrierService.findAllCalendriers();
		//this.listCategorie = categorieService.findAllCategories();
		for (int i = 0; i < listCategorie.size(); i++) {
			 this.ls=new ArrayList<Sequence>();
			for (int j = 0; j < listCalendrier.size(); j++) {
				this.horaireId=new HorairesId();
				horaireId.setIdcal(listCalendrier.get(j).getIdcal());
				horaireId.setIdcat(listCategorie.get(i).getIdcat());
				this.horaire = new Horaires();
				this.horaire = horaireService.getByid(horaireId);
				this.sequence = new Sequence();
				if(this.horaire != null)
				{ setSequence(horaire.getSequence()); 
				ls.add(sequence);
				}
				else
				{
				sequence=sequenceService.findSequence("Standard").get(0);
				System.out.println(sequenceService.findSequence("Standard").get(0).getDesigSeq());
				ls.add(sequence);
				this.horaire = new Horaires();
				horaire.setId(horaireId);
				horaire.setSequence(sequence);
				horaireService.insertHoraire(horaire);
				}
			}
			listehoraire.add(ls);
		}
	}
	
	//fct pour annuler les modification du tableau listhoraire
	public void annuler()
	{
		this.listehoraire= new ArrayList<ArrayList<Sequence>>();
		changeformsortie();
		this.sequence2=new Sequence();
	}
	

	// fct de changement de formarmat de sortie ala la table horaire
	public void modifierhoriare()
	{
		try{
		for(int i=0;i<listCategorie.size();i++)
		{
			for(int j=0;j<listCalendrier.size();j++)
			{
								  	
				this.horaireId=new HorairesId();
				horaireId.setIdcal(listCalendrier.get(j).getIdcal());
				horaireId.setIdcat(listCategorie.get(i).getIdcat());

				this.horaire = horaireService.getByid(horaireId);
				if (this.horaire!= null)
				{
					horaire.setSequence(listehoraire.get(i).get(j));
					horaireService.updateHoraire(getHoraire());
				}
				else{}
			}}
		FacesMessage msg = new FacesMessage(" modification effectuée avec Succés");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        RequestContext.getCurrentInstance().update("form:messaget");
        RequestContext.getCurrentInstance().update("form");
		}
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur lors de la mise a jours");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();}
		
	}

	// //// GETTERS AND SETTERS
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
	public Calendrier getCalendrier() {
		return calendrier;
	}
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public HoraireService getHoraireService() {
		return horaireService;
	}
	public void setHoraireService(HoraireService horaireService) {
		this.horaireService = horaireService;
	}
	public SequenceService getSequenceService() {
		return sequenceService;
	}
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}
	public CalendrierService getCalendrierService() {
		return calendrierService;
	}
	public void setCalendrierService(CalendrierService calendrierService) {
		this.calendrierService = calendrierService;
	}
	public CategorieService getCategorieService() {
		return categorieService;
	}
	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}
	public int getSizeliste() {
		return sizeliste;
	}
	public void setSizeliste(int sizeliste) {
		this.sizeliste = sizeliste;
	}
	public ArrayList<ArrayList<Sequence>> getListehoraire() {
		//changeformsortie();
		return listehoraire;
	}
	public void setListehoraire(ArrayList<ArrayList<Sequence>> listehoraire) {
		this.listehoraire = listehoraire;
	}
	public List<Sequence> getListSequence() {
		this.listSequence = new ArrayList<Sequence>();
		this.listSequence = sequenceService.findAllSequences();
		//changeformsortie();
		return listSequence;
	}
	public void setListSequence(List<Sequence> listSequence) {
		this.listSequence = listSequence;
	}
	public List<Categorie> getListCategorie() {
		this.listCategorie = new ArrayList<Categorie>();
		this.listCategorie = categorieService.findAllCategories();
		//changeformsortie();
		return listCategorie;
	}
	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	public List<Calendrier> getListCalendrier() {
		this.listCalendrier = new ArrayList<Calendrier>();
		this.listCalendrier = calendrierService.findAllCalendriers();
		//changeformsortie();
		return listCalendrier;
	}
	public void setListCalendrier(List<Calendrier> listCalendrier) {
		this.listCalendrier = listCalendrier;
	}
	public HorairesId getHoraireId() {
		return horaireId;
	}
	public void setHoraireId(HorairesId horaireId) {
		this.horaireId = horaireId;
	}
	public HashMap<String, Sequence> getHashlistsequences() {
		return hashlistsequences;
	}
	public void setHashlistsequences(HashMap<String, Sequence> hashlistsequences) {
		this.hashlistsequences = hashlistsequences;
	}
	public ArrayList<Sequence> getLs() {
		return ls;
	}
	public void setLs(ArrayList<Sequence> ls) {
		this.ls = ls;
	}
	public Sequence getSequence2() {
		return sequence2;
	}
	public void setSequence2(Sequence sequence2) {
		this.sequence2 = sequence2;
	}
	public HashMap<String, Integer> getHashlistsequences2() {
		return hashlistsequences2;
	}
	public void setHashlistsequences2(HashMap<String, Integer> hashlistsequences2) {
		this.hashlistsequences2 = hashlistsequences2;
	}
	public List<Sequence> getListSequence2() {
		return listSequence2;
	}
	public void setListSequence2(List<Sequence> listSequence2) {
		this.listSequence2 = listSequence2;
	}
	public ArrayList<ArrayList<Sequence>> getListehoraire2() {
		return listehoraire2;
	}
	public void setListehoraire2(ArrayList<ArrayList<Sequence>> listehoraire2) {
		this.listehoraire2 = listehoraire2;
	}
	public List<Integer> getIndice1() {
		return indice1;
	}
	public void setIndice1(List<Integer> indice1) {
		this.indice1 = indice1;
	}
	public List<Integer> getIndice2() {
		return indice2;
	}
	public void setIndice2(List<Integer> indice2) {
		this.indice2 = indice2;
	}
	public List<Sequence> getValeur() {
		return valeur;
	}
	public void setValeur(List<Sequence> valeur) {
		this.valeur = valeur;
	}
}
