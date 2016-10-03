package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.Parametre;
import org.bio.model.Societe;
import org.bio.model.Trace;
import org.bio.service.AffiliationService;
import org.bio.service.ParametreService;
import org.bio.service.SocieteService;
import org.bio.service.TraceService;
import org.bio.util.FileConverter;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@SessionScoped
public class HirarachyBean implements Serializable {

	@ManagedProperty(value="#{affiliationServiceImpl}")
	private AffiliationService affiliationService ;
		@ManagedProperty(value="#{societeServiceImpl}")
		private SocieteService societeService ;
		@ManagedProperty("#{traceServiceImpl}")
		private TraceService traceService;
		@ManagedProperty(value="#{parametreServiceImpl}")
		private ParametreService parametreService;
		public ParametreService getParametreService() {
			return parametreService;
		}
		public void setParametreService(ParametreService parametreService) {
			this.parametreService = parametreService;
		}
		private List<Affiliation> selectedAffiliations;
		private List<Affiliation> listsociete;
		private Operateur currentUser;
		private Affiliation selectedAffiliation ;
		private boolean showSecondTable ;
		private String currentType;
		private List<Affiliation> listaffiliations;
		private Affiliation affiliation;
		private TreeNode rootDisplay;
private boolean showSeq;
		private Affiliation parent;
		private Societe societe;
		private boolean showTree;
@PostConstruct
		private void init()
		{
	societe = new Societe();
	parent =  new Affiliation();
	showSecondTable = false;
	affiliation = new Affiliation();
currentType = "Société";
showTree = false;
	loadAffiliation(null);
	rootDisplay = new DefaultTreeNode("Root", null);
	showSeq = true;
	affiliation.setAffiliation(getAffiliationService().getRoot());
		}

public void initDiag()
{
	societe = new Societe();
	parent =  new Affiliation();
	showSecondTable = false;
	affiliation = new Affiliation();
currentType = "Société";
showTree = false;
	loadAffiliation(null);
	rootDisplay = new DefaultTreeNode("Root", null);
	showSeq = true;
	affiliation.setAffiliation(getAffiliationService().getRoot());
}

public void showAppercu()
{rootDisplay = new DefaultTreeNode();
	Affiliation h = getAffiliationService().getRoot();
	rootDisplay.setExpanded(true);
	rootDisplay(h, rootDisplay);
	showTree = true;
	RequestContext.getCurrentInstance().execute("diag_tree.show()");
	RequestContext.getCurrentInstance().update("formtree:display2 formtree:apercue formtree");
}
public void rootDisplay(Affiliation h, TreeNode root) {
	h.setAffiliationChildren(getAffiliationService().listaffiliation(h));
	for (Affiliation a : h.getAffiliationChildren()) {
		TreeNode node = new DefaultTreeNode(a, root);

		node.setExpanded(true);

		rootDisplay(a, node);
	}
}

public void loadAffiliation(SelectEvent event)
{
	listsociete = new ArrayList<Affiliation>();
	listsociete.addAll(getAffiliationService().getAffiliation(currentType.substring(0,2)));	
	if(currentType.substring(0,2).equals("So")){
		showTree = false;
		showSecondTable = true;

	}
	else 
	{
		String s= listsociete.get(0).getAffiliation().getType();
		listsociete = new ArrayList<Affiliation>();
		listsociete.addAll(getAffiliationService().getAffiliation(s));	
		showSecondTable = false;
	}
}
public List<Affiliation> loadchildrens(Affiliation a)
{
	List<Affiliation> list =new ArrayList<Affiliation>();
	list.addAll(getAffiliationService().listaffiliation(a));
return list;
}
public void handleFileUpload(FileUploadEvent event) {

	FileConverter fc = new FileConverter();
	societe.setLogo(event.getFile().getContents());
	
	societe.setLogoName(fc.getFileName());
}
public void handleFileUploadUpdate(FileUploadEvent event) {

	FileConverter fc = new FileConverter();
	selectedAffiliation.getSociete().setLogo(event.getFile().getContents());
//	System.out.println(selectedAffiliation.getSociete().getLogo());
	selectedAffiliation.getSociete().setLogoName(fc.getFileName());

}
public void storeAffiliation(Affiliation a)
{
	parent = a;
	selectedAffiliation =a;
	//System.out.println(a.getDesignation());
//System.out.println(selectedAffiliation.getDesignation());

}
public void insertAffiliation() {

try {
	Trace trace = new Trace(); 
	affiliation.setType(currentType.substring(0,2));
	System.out.println("enter 23");		
	affiliation.setDescription(currentType);
	getAffiliationService().insertAffiliation(affiliation);
	if(showSecondTable)
	{		affiliation.setAffiliation(getAffiliationService().getRoot());
	societe.setAffiliation(affiliation);
		getSocieteService().insertSociete(societe);
	trace.setObjet("Société");
	trace.setAction("Insertion Société "+affiliation.getDesignation());
	getTraceService().insertTrace(trace);
	}
	else{
		trace.setObjet("Affiliation");
		trace.setAction("Insertion Affiliation "+affiliation.getDesignation());
		getTraceService().insertTrace(trace);
	}
	loadAffiliation(null);
	FacesMessage msg = new FacesMessage("Affiliation Crée avec succés");
	FacesContext.getCurrentInstance().addMessage(null, msg);
	RequestContext.getCurrentInstance().update("form7:msg");
	
	
} catch (Exception e) {
	// TODO: handle exception
	FacesMessage msg = new FacesMessage("Erreur");
	FacesContext.getCurrentInstance().addMessage(null, msg);
	System.out.println(e.getMessage());
	RequestContext.getCurrentInstance().update("form7:msg");

}
	
}
public void updateAffiliation()
{
	try {
		
		Trace trace = new Trace(); 
		if(showSecondTable)
		{
			trace.setObjet("Société");
			trace.setAction("Modification Societe "+selectedAffiliation.getDesignation());
			getTraceService().insertTrace(trace);
		getSocieteService().updateSociete(selectedAffiliation.getSociete());	
		}

		trace.setAction("Modification Affiliation "+selectedAffiliation.getDesignation());
		trace.setObjet("Affiliation");
		getTraceService().insertTrace(trace);
		getAffiliationService().updateAffiliation(selectedAffiliation);
		loadAffiliation(null);
		FacesMessage msg = new FacesMessage("Affiliation modifié avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update("form7:msg");

	} catch (Exception e) {
		// TODO: handle exception
		FacesMessage msg = new FacesMessage("Erreur");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update("form7:msg");

	}
	
}
public void deleteAffiliation()
{
	try {
		
		Trace trace = new Trace();

		if(showSecondTable)
		{
			trace.setAction("Suppression Société "+selectedAffiliation.getDesignation());
			trace.setObjet("Société");
			getTraceService().insertTrace(trace);
		getSocieteService().deleteSociete(selectedAffiliation.getSociete());	
		}
		
		getAffiliationService().deleteAffiliation(selectedAffiliation);
		trace.setObjet("Affiliation");
		trace.setAction("Delete Affiliation "+selectedAffiliation.getDesignation());
		getTraceService().insertTrace(trace);
		loadAffiliation(null);
		FacesMessage msg = new FacesMessage("Affiliation supprimé avec succés");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	} catch (Exception e) {
		// TODO: handle exception
		FacesMessage msg = new FacesMessage("Erreur");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
		private List<String> types;
		public List<Affiliation> getListsociete() {
			
			return listsociete;
		}
		public void setListsociete(List<Affiliation> listsociete) {
			this.listsociete = listsociete;
		}
		public AffiliationService getAffiliationService() {
			return affiliationService;
		}
		public void setAffiliationService(AffiliationService affiliationService) {
			this.affiliationService = affiliationService;
		}
		public SocieteService getSocieteService() {
			return societeService;
		}
		public void setSocieteService(SocieteService societeService) {
			this.societeService = societeService;
		}
		public List<Affiliation> getSelectedAffiliations() {
			return selectedAffiliations;
		}
		public void setSelectedAffiliations(List<Affiliation> selectedAffiliations) {
			this.selectedAffiliations = selectedAffiliations;
		}
		public Affiliation getSelectedAffiliation() {
			return selectedAffiliation;
		}
		public void setSelectedAffiliation(Affiliation selectedAffiliation) {
			this.selectedAffiliation = selectedAffiliation;
		}
		public List<String> getTypes() {
			types = new ArrayList<String>();
			types.addAll(getAffiliationService().getAffiliationTypes());
			return types;
		}
		public void setTypes(List<String> types) {
			this.types = types;
		}
		public String getCurrentType() {
			return currentType;
		}
		public void setCurrentType(String currentType) {
			this.currentType = currentType;
		}
		public boolean isShowSecondTable() {
			return showSecondTable;
		}
		public void setShowSecondTable(boolean showSecondTable) {
			this.showSecondTable = showSecondTable;
		}
		public Affiliation getAffiliation() {
			return affiliation;
		}
		public void setAffiliation(Affiliation affiliation) {
			this.affiliation = affiliation;
		}
		public Affiliation getParent() {
			return parent;
		}
		public void setParent(Affiliation parent) {
			this.parent = parent;
		}
		public List<Affiliation> getListaffiliations() {
			listaffiliations = new ArrayList<Affiliation>();
			listaffiliations.addAll(getAffiliationService().findAllAffiliations());
			return listaffiliations;
		}
		public void setListaffiliations(List<Affiliation> listaffiliations) {
			this.listaffiliations = listaffiliations;
		}
		public Societe getSociete() {
			return societe;
		}
		public void setSociete(Societe societe) {
			this.societe = societe;
		}
		public boolean isShowTree() {
			return showTree;
		}
		public void setShowTree(boolean showTree) {
			this.showTree = showTree;
		}
		public TreeNode getRootDisplay() {
			return rootDisplay;
		}
		public void setRootDisplay(TreeNode rootDisplay) {
			this.rootDisplay = rootDisplay;
		}
		public Operateur getCurrentUser() {
			return currentUser;
		}
		public void setCurrentUser(Operateur currentUser) {
			this.currentUser = currentUser;
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
}
