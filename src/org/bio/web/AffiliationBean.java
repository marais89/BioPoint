package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.Societe;
import org.bio.service.AffiliationService;
import org.bio.service.OperateurRolesService;
import org.bio.service.OperateurService;
import org.bio.service.SocieteService;
import org.bio.util.FileConverter;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@ViewScoped
public class AffiliationBean implements Serializable {

	@ManagedProperty(value = "#{affiliationServiceImpl}")
	private AffiliationService affiliationService;
	@ManagedProperty(value = "#{societeServiceImpl}")
	private SocieteService societeService;
	@ManagedProperty(value="#{operateurServiceImpl}")
	private OperateurService operateurService;
	private Societe societe;

	private Affiliation affiliation;
	private List<Affiliation> listservice;
	private List<Affiliation> listdirection;
	private List<Affiliation> listteam;
	private List<Affiliation> selectedAffiliations;
	private Affiliation selectedAffiliation;
	private TreeNode[] selectedNodes;
	private List<Affiliation> filtredAffiliations;
	private TreeNode toor;
	private TreeNode toorChild;
	private List<Affiliation> listsociete;
	private String filtres;
	private Operateur currentUser;
	private int idParent;
	private HashMap<String, Integer> listParents;
	private boolean flag;
	private int listsize;
	private List<TreeNode> filtredList;
	private int parent;
	private String titreDialog;
	private boolean first;

	@PostConstruct
	private void init() {
		parent = 1;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	currentUser = getOperateurService().getByLogin(auth.getName());
		filtredList = new ArrayList<TreeNode>();
		affiliation = new Affiliation();
		societe = new Societe();
		Affiliation h = getAffiliationService().getRoot();
		toorChild = new DefaultTreeNode("Root", null);
		toorChild.setExpanded(true);
		
		toor = new CheckboxTreeNode("Root", null);
		toor.setExpanded(true);
		displayRoot(h, toor,true);
		rootDisplay(h, toorChild,true);
		filtres = "";
		parent = 0;
		titreDialog = "Création de société";
		flag = true;
		first=true;
	}
	

	public void store(SelectEvent event) {
		if (event != null)
			selectedAffiliation = (Affiliation) event.getObject();
	}

	public List<Affiliation> convertSet(Set<Affiliation> list) {
		List<Affiliation> listc = new ArrayList<Affiliation>();
		listc.addAll(list);
		return listc;

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

	public void displayRoot(Affiliation h, TreeNode root,boolean ok) {
		if(ok){
		h.setAffiliationChildren(getAffiliationService().getAffiliation(findAffType()));
		System.out.println("type "+findAffType()+" "+h.getAffiliationChildren().size());
		}
		else{
			System.out.println("false");
			h.setAffiliationChildren(getAffiliationService().listaffiliation(h));

		}
		for (Affiliation a : h.getAffiliationChildren()) {
			if(containsAffiliation(a))
			{
			TreeNode node = new CheckboxTreeNode(a, root);
			node.setExpanded(true);
			displayRoot(a, node,false);
			}
			
		}
	}
private boolean containsAffiliation(Affiliation a)
{		List<Affiliation> af=getOperateurService().getopaffiliation(currentUser);

	for(Affiliation afli:af)
	{
		if(afli.getIdaff()==a.getIdaff())
			return true;
	}
	return false;
}
	public void rootDisplay(Affiliation h, TreeNode root,boolean ok) {
		if(ok){
			h.setAffiliationChildren(getAffiliationService().getAffiliation(findAffType()));
			System.out.println("type "+findAffType()+" "+h.getAffiliationChildren().size());
			}
			else{
				System.out.println("false");
				h.setAffiliationChildren(getAffiliationService().listaffiliation(h));

			}
			for (Affiliation a : h.getAffiliationChildren()) {
				if(containsAffiliation(a))
				{
				TreeNode node = new DefaultTreeNode(a, root);
				node.setExpanded(true);
				rootDisplay(a, node,false);
				}
				
			}
	}

	public void buildTree(Affiliation h, TreeNode root) {
		h.setAffiliationChildren(getAffiliationService().listaffiliation(h));
		for (Affiliation a : h.getAffiliationChildren()) {
			a.setAffiliationChildren(getAffiliationService().listaffiliation(a));

			if (a.getAffiliationChildren().size() != 0) {
				TreeNode node = new DefaultTreeNode(a, root);
				node.setExpanded(true);
				node.setSelectable(false);
				if(!currentUser.getListaffiliations().contains(a))
				{
					node.setSelectable(false);
				}
				buildTree(a, node);

				
			} else {
				TreeNode node = new DefaultTreeNode(a, root);
				node.setExpanded(true);
				if(!currentUser.getListaffiliations().contains(a))
				{
					node.setSelectable(false);
				}
				buildTree(a, node);
				node.setSelectable(true);

		
			}

		}
	}
	
	private String findAffType()
	
	{ List<Affiliation> listaffop= getOperateurService().getopaffiliation(currentUser);
		String s="all";
		if(listaffop.size()>0)
		{
		for(Affiliation a:listaffop)
		{	if(!listaffop.contains(a.getAffiliation()))
			{
			s=a.getType();
		}
		}
		return s;
		}
		else return "all";
	}
	public AffiliationService getAffiliationService() {
		return affiliationService;
	}

	public void setAffiliationService(AffiliationService AffiliationService) {
		this.affiliationService = AffiliationService;
	}

	public Affiliation getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Affiliation Affiliation) {
		this.affiliation = Affiliation;
	}

	public Affiliation getSelectedAffiliation() {
		return selectedAffiliation;
	}

	public void setSelectedAffiliation(Affiliation selectedAffiliation) {
		this.selectedAffiliation = selectedAffiliation;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public TreeNode getToor() {

		return toor;
	}

	public void setToor(TreeNode toor) {
		this.toor = toor;
	}

	public String getFiltres() {
		filtres = "";

		for (TreeNode node : getFiltredList()) {
			filtres += node.getData() + " | ";
		}
		return filtres;
	}

	public void enableFlag() {
	}

	public void setFiltres(String filtres) {
		this.filtres = filtres;
	}

	public List<TreeNode> getFiltredList() {
		List<TreeNode> listtoRemove = new ArrayList<TreeNode>();
		for (TreeNode node : filtredList) {
			if (filtredList.containsAll(node.getChildren())) {
				listtoRemove.addAll(node.getChildren());
			}
			if (filtredList.contains(node.getParent())) {
				listtoRemove.add(node);
				listtoRemove.addAll(node.getChildren());
			}
		}
		filtredList.removeAll(listtoRemove);
		return filtredList;
	}

	public void setFiltredList(List<TreeNode> filtredList) {
		this.filtredList = filtredList;
	}

	public List<Affiliation> getSelectedAffiliations() {
		return selectedAffiliations;
	}

	public void setSelectedAffiliations(List<Affiliation> selectedAffiliations) {
		this.selectedAffiliations = selectedAffiliations;
	}

	public boolean isFlag() {
		if (parent <= 1) {
			flag = true;
		} else
			flag = false;
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public HashMap<String, Integer> getListParents() {

		if (parent == 2) {
			listParents = new HashMap<String, Integer>();
			for (Affiliation a : getListsociete()) {
				listParents.put(a.getDescription(), a.getIdaff());
			}
		} else if (parent == 3) {
			listParents = new HashMap<String, Integer>();
			for (Affiliation a : getListdirection()) {
				listParents.put(a.getDescription(), a.getIdaff());
			}
		} else if (parent == 4) {
			listParents = new HashMap<String, Integer>();
			for (Affiliation a : getListservice()) {
				listParents.put(a.getDescription(), a.getIdaff());
			}
		}
		return listParents;
	}

	public void setListParents(HashMap<String, Integer> listParents) {
		this.listParents = listParents;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public SocieteService getSocieteService() {
		return societeService;
	}

	public void setSocieteService(SocieteService societeService) {
		this.societeService = societeService;
	}

	public List<Affiliation> getFiltredAffiliations() {
		return filtredAffiliations;
	}

	public void setFiltredAffiliations(List<Affiliation> filtredAffiliations) {
		this.filtredAffiliations = filtredAffiliations;
	}

	public int getListsize() {
		listsize = selectedAffiliations.size();
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}

	public TreeNode getToorChild() {
		return toorChild;
	}

	public void setToorChild(TreeNode toorChild) {
		this.toorChild = toorChild;
	}

	public List<Affiliation> getListservice() {
		listservice = new ArrayList<Affiliation>();
		listservice.addAll(getAffiliationService().getAffiliation("SE"));
		return listservice;
	}

	public void setListservice(List<Affiliation> listservice) {
		this.listservice = listservice;
	}

	public List<Affiliation> getListdirection() {
		listdirection = new ArrayList<Affiliation>();
		listdirection.addAll(getAffiliationService().getAffiliation("DI"));
		return listdirection;
	}

	public void setListdirection(List<Affiliation> listdirection) {
		this.listdirection = listdirection;
	}

	public List<Affiliation> getListsociete() {
		listsociete = new ArrayList<Affiliation>();
		listsociete.addAll(getAffiliationService().getAffiliation("SC"));
		return listsociete;
	}

	public void setListsociete(List<Affiliation> listsociete) {
		this.listsociete = listsociete;
	}

	public List<Affiliation> getListteam() {
		listteam = new ArrayList<Affiliation>();
		listteam.addAll(getAffiliationService().getAffiliation("EQ"));
		return listteam;
	}

	public void setListteam(List<Affiliation> listteam) {
		this.listteam = listteam;
	}

	public String getTitreDialog() {
		if (parent == 1)
			titreDialog = "Création d'une société";
		else if (parent == 2)
			titreDialog = "Création d'un Direction";
		else if (parent == 3)
			titreDialog = "Création d'une Service";
		else if (parent == 4)
			titreDialog = "Création d'une Equipe";
		return titreDialog;
	}

	public void setTitreDialog(String titreDialog) {
		this.titreDialog = titreDialog;
	}

	public int getIdParent() {
		return idParent;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	

	public Operateur getCurrentUser() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	currentUser = getOperateurService().getByLogin(auth.getName());
		return currentUser;
	}

	public void setCurrentUser(Operateur currentUser) {
		this.currentUser = currentUser;
	}

	public OperateurService getOperateurService() {
		return operateurService;
	}

	public void setOperateurService(OperateurService operateurService) {
		this.operateurService = operateurService;
	}

	
}
