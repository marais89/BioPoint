package org.bio.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bio.report.ReportingService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRGroupFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JasperDesignFactory;
import net.sf.jasperreports.repo.JasperDesignReportResource;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.bio.model.Affiliation;
import org.bio.model.Categorie;
import org.bio.service.AffiliationService;
import org.bio.service.CalendrierService;
import org.bio.service.CategorieService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.web.servlet.view.jasperreports.JasperReportsHtmlView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

@ManagedBean
 @ViewScoped
public class ReportDynamicBean {
	 
      private JasperPrint jasperPrint;
      @ManagedProperty(value=("#{calendrierServiceImpl}"))
  	private CalendrierService calendrierService;
  	@ManagedProperty(value=("#{affiliationServiceImpl}"))
  	private AffiliationService affiliationService;
  	@ManagedProperty(value=("#{categorieServiceImpl}"))
  	private CategorieService categorieService;
  	//   *AFFILIATION
  	private Affiliation Societe;
  	private List<Affiliation> listeSte;
  	private List<Affiliation> listeAffiliation;
  	private TreeNode toor;
  	private List<TreeNode> filtredList;
  	private List<Integer> listeId;
  	private List<String> listeSociete;		
  	//   *RAPPOTBEAN
  	private String command;
  	private Date datedebut;
  	private Date datefin;
  	private String dat1;
  	private String dat2;
  	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  	static DateFormat dateFormatParam = new SimpleDateFormat("dd/MM/yyyy");
  
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
  	private String champ;
  	private String condition;
  	private String valeur;
  	private List<String> inter;
  	private Date dateFilter;
  	private Date houreFilter;
  	private boolean flagPanel;
  	private boolean flagUnion;
  	private String dynamicQuery;
  	private List<Integer> listeIdAff = new ArrayList<Integer>();
  	private ReportingService reportingService = new ReportingService();
	@PostConstruct
	private void init()
	{
		this.inter= new ArrayList<String>();
		this.listeCat= new ArrayList<Categorie>();
		this.listeCat=categorieService.findAllCategories();
		listeAffiliation= new ArrayList<Affiliation>();
		listeId= new ArrayList<Integer>();
		listeSte=  new ArrayList<Affiliation>();
		this.listeSte= new ArrayList<Affiliation>();
		filtredList = new ArrayList<TreeNode>();
		this.listeSte= new ArrayList<Affiliation>();
		this.listeSte= affiliationService.getAffiliation("So");
		
		this.liste1= new ArrayList<String>();
		this.liste2=new ArrayList<List<String>>();
		this.liste3= new ArrayList<String>();
		this.liste21= new ArrayList<String>();
		
		Affiliation h = getAffiliationService().getRoot();
		toor = new CheckboxTreeNode("root",null);
		toor.setExpanded(true);
		displayRoot(h, toor);
		remplirListe();
		bol1=true;
		bol2=false;
		bol3=false;
		bol4=false;
		flagPanel= false;
		flagUnion= false;
	}
	
	
	public void remplirListe()
	{
		liste1.add("absent");
		liste1.add("autorisation");
		liste1.add("categorieHoraire");
		liste1.add("contrat");
		liste1.add("debutContrat");
		//liste1.add("direction");
		liste1.add("finContrat");
		liste1.add("heureSup");
		liste1.add("journee");
		liste1.add("matricule");
		liste1.add("modeleHoraire");
		liste1.add("retard");
		//liste1.add("service");
		//////////
		inter.add("=");
		liste2.add(inter);
		
		inter= new ArrayList<String>();
		inter.add("=");
		inter.add("<>");
		liste2.add(inter);
		
		inter= new ArrayList<String>();
		inter.add("=");
		inter.add("<>");
		inter.add("<");
		inter.add(">");
		inter.add("<=");
		inter.add(">=");
		liste2.add(inter);
		
	}
	
	public List<String> returnCondition(String s)
	{
		     if(s.equals("absent")){return (liste2.get(0));}
		else if(s.equals("autorisation")){return(liste2.get(0));}
		else if(s.equals("categorieHoraire")){return(liste2.get(1));}
		else if(s.equals("contrat")){return(liste2.get(0));}
		else if(s.equals("debutContrat")){return(liste2.get(2));}
		//else if(s=="direction"){return(liste2.get(1));}
		else if(s.equals("finContrat")){return(liste2.get(2));}
		else if(s.equals("heureSup")){return(liste2.get(2));}
		else if(s.equals("journee")){return(liste2.get(2));}
		else if(s.equals("matricule")){return(liste2.get(2));}
		else if(s.equals("modeleHoraire")){return(liste2.get(1));}
		else if(s.equals("retard")){return(liste2.get(2));}
		else {return(liste2.get(0));}		
	}
	
	public void aff()
	{
		this.liste21= returnCondition(this.champ);
		remplirlite3(this.champ);
		System.out.println(this.champ);
		RequestContext.getCurrentInstance().update("form:panel1");
		RequestContext.getCurrentInstance().update("form:list3");
		RequestContext.getCurrentInstance().update("form:inputTxt");
	}
	
	public void chargeOn()
	{
		this.liste3= new ArrayList<String>();
		this.liste3.add("Oui");
		this.liste3.add("Non");
	}
	public void chargeCatHoraire()
	{
		this.liste3= new ArrayList<String>();
		for(int i=0; i<listeCat.size();i++)
		{
		this.liste3.add(listeCat.get(i).getDesigCat()) ;
		}
	}
	
	public void remplirlite3(String s)
	{
			     if(s.equals("absent")){chargeOn();bol1=true;bol2=false;bol3=false;bol4=false;}
			else if(s.equals("autorisation")){chargeOn();bol1=true;bol2=false;bol3=false;bol4=false;}
			else if(s.equals("categorieHoraire")){chargeCatHoraire();bol1=true;bol2=false;bol3=false;bol4=false;}
			else if(s.equals("contrat")){chargeOn();bol1=true;bol2=false;bol3=false;bol4=false;}
			else if(s.equals("debutContrat")){;bol1=false;bol2=true;bol3=false;bol4=false;}
			else if(s.equals("finContrat")){;bol1=false;bol2=true;bol3=false;bol4=false;}
			else if(s.equals("heureSup")){;bol1=false;bol2=false;bol3=false;bol4=true;}
			else if(s.equals("journee")){;bol1=false;bol2=true;bol3=false;bol4=false;}
			else if(s.equals("matricule")){;bol1=false;bol2=false;bol3=true;bol4=false;}
			else if(s.equals("modeleHoraire")){;bol1=true;bol2=false;bol3=false;bol4=false;}
			else if(s.equals("retard")){;bol1=false;bol2=false;bol3=false;bol4=true;}
			else {liste3= new ArrayList<String>();bol1=true;bol2=false;bol3=false;bol4=false;}	
	}
	
	
	/*public void buildTree()
	{
		this.ste=Societe.getDesignation();
		Affiliation h = getAffiliationService().getRoot();
		toor = new CheckboxTreeNode("root",null);
		toor.setExpanded(true);
		displayRoot(h, toor);		
	}*/

public String genere()
{
	this.dat1 = dateFormat.format(datedebut);
	this.dat2 = dateFormat.format(datefin);
	this.command="pointage.`jour` between '"+dat1+"' AND '"+dat2+"'";
	for(int i=0;i<selectedNodes.length;i++)
	{
		Affiliation aff=(Affiliation)selectedNodes[i].getData();
		listeAffiliation.add(aff);
		listeId.add(aff.getIdaff());
		//System.out.println(findste(aff));
	}	
	//    *TEST
	System.out.println(this.command);
	//System.out.println(this.Societe.getDesignation()+" ,"+this.Societe.getSociete().getAdresse());
	for(int i=0;i<selectedNodes.length;i++){
		Affiliation aff=(Affiliation)selectedNodes[i].getData();
	System.out.println(aff.getDesignation()+","+aff.getType());}
	
	for(int i=0;i<listeId.size();i++)
	{
	System.out.println(listeId.get(i));
	}
	
	for(int i=0;i<listeSte.size();i++){
	System.out.println(listeSte.get(i).getDesignation());}
	return command;
}


public String findste(Affiliation affiliation)
{ 
	Affiliation parent=affiliation.getAffiliation();
	Affiliation parent2=affiliation.getAffiliation();
	while(parent != null)
	{
	parent2=parent;
	parent=affiliation.getAffiliation();
	}
	return(parent2.getDesignation());
}

public void displayRoot(Affiliation h, TreeNode root) {
	h.setAffiliationChildren(getAffiliationService().listaffiliation(h));
	for (Affiliation a : h.getAffiliationChildren()) {
		TreeNode node = new CheckboxTreeNode(a, root);

		node.setExpanded(true);

		displayRoot(a, node);
	}
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

public void preview(){
	
	String path = "/resources/rapport/mvtRpt.jrxml";
//	String period = "Du "+dateFormatParam.format(datedebut)+" Au "+dateFormatParam.format(datefin);
	String period = "";
	Map<Integer, Boolean> groupBreakPageMap = new HashMap<Integer, Boolean>();
	Map<Integer, String> groupMap = new HashMap<Integer, String>();
	Map<Integer, String> groupLibelleMap = new HashMap<Integer, String>();
	 groupBreakPageMap.put(1,false);
	 groupBreakPageMap.put(0,true);
	
	 groupLibelleMap.put(0, "affiliation");
	 groupLibelleMap.put(1, "matricule");
	 groupMap.put(1,"affiliation.`designation`");
	 groupMap.put(0,"personnel.`matricule`");
	 groupMap.put(2,"mvt.`jour`");
	 groupMap.put(3,"sequence.`DesigSeq`");
	 String query = new String();
	 query = null;
	 String filtre = "Filtre: Service,Direction";
	 String order = "pointage.`jour` DESC";
	 String langue = "FR";
	 String societe = null;
	 String code ="cc";
	//reportingService.previewPdf(path,query,order,period,filtre,groupMap,groupBreakPageMap);
	
	 reportingService.previewPdf(path,query,order,period,filtre,groupMap,groupLibelleMap,groupBreakPageMap,langue,societe,code);
	 



}
public void export(){
	
	String path = "/resources/rapport/mvtRpt.jrxml";
//	String period = "Du "+dateFormatParam.format(datedebut)+" Au "+dateFormatParam.format(datefin);
	String period = "";
	Map<Integer, Boolean> groupBreakPageMap = new HashMap<Integer, Boolean>();
	Map<Integer, String> groupMap = new HashMap<Integer, String>();
	Map<Integer, String> groupLibelleMap = new HashMap<Integer, String>();
	 groupBreakPageMap.put(1,false);
	 groupBreakPageMap.put(0,true);
	
	 groupLibelleMap.put(0, "affiliation");
	 groupLibelleMap.put(1, "matricule");
	 groupMap.put(1,"affiliation.`designation`");
	 groupMap.put(0,"personnel.`matricule`");
	 groupMap.put(2,"mvt.`jour`");
	 groupMap.put(3,"sequence.`DesigSeq`");
	 String query = new String();
	 query = null;
	 String filtre = "Filtre: Service,Direction";
	 String order = "pointage.`jour` DESC";
	 String langue = "FR";
	 String societe = null;
	//reportingService.previewPdf(path,query,order,period,filtre,groupMap,groupBreakPageMap);
	
	 //reportingService.exportToPdf(path,query,order,period,filtre,groupMap,groupLibelleMap,groupBreakPageMap,langue,societe);
}

  public String getDynamicQuery() {
		return dynamicQuery;
	}



	public void setDynamicQuery(String dynamicQuery) {
		this.dynamicQuery = dynamicQuery;
	}



	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}



	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}


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
		this.listeCat= new ArrayList<Categorie>();
		this.listeCat=categorieService.findAllCategories();
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


}
