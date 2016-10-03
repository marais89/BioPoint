package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Categorie;
import org.bio.model.Terminaux;
import org.bio.model.Trace;
import org.bio.service.CategorieService;
import org.bio.service.TraceService;
import org.hibernate.mapping.Array;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

@ManagedBean
@ViewScoped
public class CategorieBean implements Serializable{
	
	@ManagedProperty(value=("#{categorieServiceImpl}"))
	private CategorieService categoriService;
	private Categorie categorie;
	private Categorie categorieSelect;
	private List<Categorie> listCategorie;
	private List<Categorie> selectlistcategorie;
	private List<Categorie> filtredlistCategorie;
	private List<String> listType;
	private int sizeliste;
	private boolean flag;
	private boolean typecat;
	private boolean typecat2;
	private int val;
	@ManagedProperty("#{traceServiceImpl}")
	private TraceService traceService;
	
	
	@PostConstruct
	public void init()
	{
		this.val=1;
		categorie=  new Categorie(); 
		this.categorieSelect = new Categorie();
		this.listCategorie =  new ArrayList<Categorie>();
		this.listCategorie = getCategoriService().findAllCategories();
		this.selectlistcategorie = new ArrayList<Categorie>();	
		this.listType = new ArrayList<String>();
		flag=true;
		this.typecat=true;
		this.typecat2=true;
		
	}
	
	
	//fct pour changer  l'etat de val
	public void changevalajout()
	{System.out.println("111111111");
		setVal(1);
		System.out.println("val egal");
		System.out.println(val);
		this.categorieSelect=new Categorie();
		
	}
	public void changevalmodif()
	{System.out.println("222222");
		setVal(2);
	}
	
	public void selonexecute()
	{
		if(getVal()==1)
		{ajouterCategorie();}
		else if(getVal()==2)
		{modifierCategorie();}
		else
		{System.out.println("ni 1 ni 2");System.out.println(val);}
	}
	
	
	// fct test	
			public void test()
			{
				System.out.println("ca marche bien !");
			}
	
	public void affect(SelectEvent event)
	{
		if(event!=null)
		categorieSelect = (Categorie) event.getObject();
	}	
	
	// fct d'activation ou de désactivation de la colonne de selection // 
			public void activate ()
			{
				setFlag(!this.flag);			
			}	
			

	// fct ajout categorie
			public void ajouterCategorie(){
				try{
					categoriService.insertCategorie(getCategorieSelect());					
					FacesMessage msg = new FacesMessage(" Categorie ajouté avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			    	Trace trace = new Trace();
					 trace .setAction("Insertion Catégorie "+getCategorieSelect().getDesigCat());
					 getTraceService().insertTrace(trace);
					}
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();
				}
			
			}
			
	//fct supprimer un terminal de la liste listterminalselectionner
			public void removecategorie(Categorie cat )
			{
				this.selectlistcategorie.remove(cat);
			}
			
			//fct modification categorie
			public void modifierCategorie(){
				try{
					getCategoriService().updateCategorie(this.categorieSelect);
					
					FacesMessage msg = new FacesMessage(" Categorie modifier avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
			        Trace trace = new Trace();
					 trace .setAction("Modification Catégorie "+getCategorieSelect().getDesigCat());
					 getTraceService().insertTrace(trace);
					}
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();
				}
			
			}
			
			//fct supprimer categorie
			public void supprimCategorie()
			{
				try{
					for(Categorie c:selectlistcategorie)
					{
						getCategoriService().deleteCategorie(c);
					}
					 Trace trace = new Trace();
					 trace .setAction("Suppression Catégorie "+getCategorieSelect().getDesigCat());
					 getTraceService().insertTrace(trace);
					FacesMessage msg = new FacesMessage(" Catégorie(s) supprimé(s) avec Succés");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				   }	
				catch(DataAccessException e){
					FacesMessage msg = new FacesMessage("Erreur");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
					e.getMessage();}
			}
	
	
	//////   GETTERS AND SETTERS
	public CategorieService getCategoriService() {
		return categoriService;
	}
	public void setCategoriService(CategorieService categoriService) {
		this.categoriService = categoriService;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public int getSizeliste() {
		sizeliste = selectlistcategorie.size();
		return sizeliste;
	}
	public void setSizeliste(int sizeliste) {
		this.sizeliste = sizeliste;
	}
	public List<Categorie> getListCategorie() {
		this.listCategorie =  new ArrayList<Categorie>();
		this.listCategorie = getCategoriService().findAllCategories();
		return listCategorie;
	}
	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	public List<Categorie> getSelectlistcategorie() {
		return selectlistcategorie;
	}
	public void setSelectlistcategorie(List<Categorie> selectlistcategorie) {
		this.selectlistcategorie = selectlistcategorie;
	}
	public List<Categorie> getFiltredlistCategorie() {
		return filtredlistCategorie;
	}
	public void setFiltredlistCategorie(List<Categorie> filtredlistCategorie) {
		this.filtredlistCategorie = filtredlistCategorie;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Categorie getCategorieSelect() {
		return categorieSelect;
	}
	public void setCategorieSelect(Categorie categorieSelect) {
		this.categorieSelect = categorieSelect;
	}
	public List<String> getListType() {
		return listType;
	}
	public void setListType(List<String> listType) {
		this.listType = listType;
	}

	public boolean isTypecat() {
		return typecat;
	}

	public void setTypecat(boolean typecat) {
		this.typecat = typecat;
	}
	public boolean isTypecat2() {
		return typecat2;
	}
	public void setTypecat2(boolean typecat2) {
		this.typecat2 = typecat2;
	}

	public int getVal() {
		return val;
	}
	public void setVal(int x) {
		this.val = x;
	}


	public TraceService getTraceService() {
		return traceService;
	}


	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
	}	
	
}
