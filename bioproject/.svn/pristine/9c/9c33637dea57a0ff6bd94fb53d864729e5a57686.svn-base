package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bio.jobs.ProfileUserSpago;
import org.bio.jobs.UserSpago;
import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.OperateurRolesId;
import org.bio.model.Personnel;
import org.bio.model.Role;
import org.bio.model.SbiOperateurAffiliation;
import org.bio.model.Trace;
import org.bio.service.AffiliationService;
import org.bio.service.OperateurRolesService;
import org.bio.service.OperateurService;
import org.bio.service.PersonnelService;
import org.bio.service.RoleService;
import org.bio.service.SbiOperateurAffiliationService;
import org.bio.service.TraceService;
import org.hibernate.Hibernate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class UserBeanxx implements Serializable{
@ManagedProperty(value="#{operateurServiceImpl}")
	private OperateurService operateurService;
@ManagedProperty(value="#{roleServiceImpl}")
private RoleService roleService;
@ManagedProperty(value="#{affiliationServiceImpl}")
private AffiliationService affiliationService;
@ManagedProperty(value = "#{operateurRolesServiceImpl}")
private OperateurRolesService operateurRolesService;
@ManagedProperty("#{traceServiceImpl}")
private TraceService traceService;
@ManagedProperty("#{sbiOperateurAffiliationServiceImpl}")
private SbiOperateurAffiliationService sbiService;
private ProfileUserSpago profileUsr = new ProfileUserSpago();
private UserSpago userSpago = new UserSpago();
private List<Affiliation> listaffiliation;

private List<OperateurRoles> listroles;
private Operateur operateur;
private Operateur selectedOperateur;
private String currentMenu;
private List<OperateurRoles> storedList;
private OperateurRoles selectedOp;
@ManagedProperty(value = "#{personnelServiceImpl}")
private PersonnelService personnelService;
private Map<String,Integer> listPersonnel;
private String node;
private int idPersonnel;
private List<Operateur> selectedOperateurs;
private List<OperateurRoles> listupdateRoles;
private int sizeList;
private List<String> rebriques;
private String tmpPassword;
private Map<String,Boolean> listrebs;
private Map<String,Boolean> listupdaterebs;
private List<Integer> selectedAffiliation;
private String label;
private List<Affiliation> listaffs;
private List<String> types;
private Map<String,Integer> listaffiliations;
private List<Integer> selectedUpdateAffiliation;
private List<Affiliation> listAffiliation;
private String type;

@PostConstruct
private void init()
{
	listAffiliation= new ArrayList<Affiliation>();
	listAffiliation=affiliationService.findAllAffiliations();
	this.selectedOperateurs = new ArrayList<Operateur>();
	this.storedList = new ArrayList<OperateurRoles>();
	this.operateur = new Operateur();
	listPersonnel = new HashMap<String, Integer>();
	
	for(Personnel p:getPersonnelService().findAllWithoutPersonnels())
	{		listPersonnel.put(p.getFullname(), p.getIdper());	}
	
	rebriques =new ArrayList<String>();
	rebriques.add("role.tous");
	currentMenu="role.tous";
	rebriques.addAll(getRoleService().findRebrique());
	listrebs=new HashMap<>();
	
	for(String str:rebriques)
	{	listrebs.put(str, false);	}
	
	listupdaterebs = listrebs;
	type="So";
	selectedAffiliation = new ArrayList<Integer>();
	types=getAffiliationService().getAffiliationTypes();
	System.out.println("types size : "+types.size());
	listaffiliation =new ArrayList<Affiliation>();
	selectedUpdateAffiliation=new ArrayList<Integer>();
	}

public List<Integer> trouverFils(int x)
{
	List<Integer> inter= new ArrayList<Integer>();
	inter.add(x);
	System.out.println("etapee1 ");
	for(int i=0;i<listaffiliation.size();i++)
	{
		if(inter.contains(listaffiliation.get(i).getAffiliation().getIdaff()))
		{System.out.println("etapee i ");inter.add(listaffiliation.get(i).getIdaff());System.out.println("etapee ii ");}
	}
	System.out.println("etapee 2");
	return inter;
}

public void makeInd(OperateurRoles opRole)
{
	opRole.setR(false);
	opRole.setW(false);
	RequestContext.getCurrentInstance().update("form:formmodifier:roles");
	RequestContext.getCurrentInstance().update("form:formopajout:roles");
}

public void makeAtt(OperateurRoles opRole)
{
	opRole.setR(true);
	opRole.setW(true);
	RequestContext.getCurrentInstance().update("form:formmodifier:roles");
	RequestContext.getCurrentInstance().update("form:formopajout:roles");
}

public void makeLsl(OperateurRoles opRole)
{
	opRole.setR(true);
	opRole.setW(false);
	RequestContext.getCurrentInstance().update("form:formmodifier:roles");
	RequestContext.getCurrentInstance().update("form:formopajout:roles");
}


private String findAffType(List<Affiliation> listaffop)
{String s="all";
	if(listaffop.size()>0)
	{
	for(Affiliation a:listaffop)
	{	if(!listaffop.contains(a.getAffiliation()))
		{	s=a.getDescription();	}
	}
	return s;
	}
	else return "all";
}

public void selectAff(SelectEvent e)
{
	System.out.println(selectedAffiliation.size());
}

public void loadOnCreate()
{loadroles(null);
selectedAffiliation = new ArrayList<Integer>();
types=getAffiliationService().getAffiliationTypes();
type="So";
RequestContext.getCurrentInstance().update("form:formopajout:PojoPickList form:formopajout");
}

public void loadonUpdate()
{
	System.out.println("** type: "+this.type);
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	selectedOperateur = getOperateurService().getByLogin(auth.getName());
    System.out.println("** login: "+selectedOperateur.getLogin());	
    System.out.println("** types size: "+types.size());
	List<Affiliation> listaffop = getOperateurService().getopaffiliation(selectedOperateur);
	System.out.println("**listaffop size:"+listaffop.size());
    String s=findAffType(listaffop);
    System.out.println("** s ="+s);
    if(!s.equalsIgnoreCase("all"))
    {listaffiliations =new HashMap<String, Integer>();
	for(Affiliation a:listaffop)
	{
		if(a.getDescription().equals(s)){
			listaffiliations.put(a.getDesignation(), a.getIdaff());
			selectedUpdateAffiliation.add(a.getIdaff());	
		}
	}
	System.out.println("listaffiliations : "+listaffiliations.size());
types.clear();
types.add(s);
type=s;
}
else{}
loadroles(null);	
//loadupdateRoles(null);
}

public void clearlist(String type)
{
	listaffs= getAffiliationService().getAffiliation(type);
}

public void clearLoad(SelectEvent event)
{	
	selectedOperateur.setListroles(getOperateurService().getopRoles(selectedOperateur));
	this.listupdateRoles =new ArrayList<OperateurRoles>();
	this.currentMenu = "";	
}

public void displayRoot(Affiliation h) {
	h.setAffiliationChildren(getAffiliationService().listaffiliation(h));	
	for (Affiliation a : h.getAffiliationChildren()) 
	{
		listaffiliation.add(a);
		displayRoot(a);
	}
}

public void disableType()
{
	if(selectedAffiliation.size()>0)
	{
		List<String> rmtypes = new  ArrayList<String>();
		for(String s:types)
		{
			if(!s.substring(0, 2).equalsIgnoreCase(type.substring(0,2)))
			{	rmtypes.add(s);		}
		}
		types.removeAll(rmtypes);
	}
	else{
		types=getAffiliationService().getAffiliationTypes();
	}
}

public void disableupdateType()
{
	if(selectedUpdateAffiliation.size()>0)
	{
		List<String> rmtypes = new  ArrayList<String>();
		for(String s:types)
		{
			if(!s.substring(0, 2).equalsIgnoreCase(type.substring(0,2)))
			{	rmtypes.add(s);	}
		}
		types.removeAll(rmtypes);
	}
	else{
		types=getAffiliationService().getAffiliationTypes();
	}
}

public void remplirRoles()
{
	this.listroles = new ArrayList<OperateurRoles>();
	List<Role> roles = new ArrayList<Role>();
	roles.addAll(getRoleService().findAllRole());
for(Role role:roles)
{
	OperateurRoles or = new OperateurRoles();
	OperateurRolesId id = new OperateurRolesId();
	id.setIdrol(role.getIdrol());
	or.setId(id);
	or.setRole(role);
	if(findRole(or)!=-1)
	{
		this.listroles.add(this.storedList.get(findRole(or)));
}
	else
{
		this.listroles.add(or);
}
	}	
}

public void selectAll(String reb)
{
if(!listrebs.get(reb))
{
if(listroles == null )
{	
	//remplirRoles();	
//	FacesMessage msg = new FacesMessage("Vous n'avez pas selectionné un role");
//	FacesContext.getCurrentInstance().addMessage(null, msg);
//	RequestContext.getCurrentInstance().update("form:msg");
	return;
}
for(int i=0;i<listroles.size();i++)
{	listroles.get(i).setR(true);
	listroles.get(i).setW(true);

	storedList.add(	listroles.get(i));	
}
listrebs.remove(reb);
listrebs.put(reb, true);
}
else{	
	if(listroles== null )
	{
		//remplirRoles();	
//		FacesMessage msg = new FacesMessage("Vous n'avez pas selectionné un role");
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//		RequestContext.getCurrentInstance().update("form:msg");
		return;
	}
		for(int i=0;i<listroles.size();i++)
		{	
			listroles.get(i).setR(false);
			listroles.get(i).setW(false);
			storedList.remove(	listroles.get(i));
		}
		listrebs.remove(reb);
		listrebs.put(reb, false);

}
}

public void selectAllupadate(String reb)
{
	if(!listupdaterebs.get(reb))
	{
		for(int i=0;i<listupdateRoles.size();i++)
		{	listupdateRoles.get(i).setR(true);
			listupdateRoles.get(i).setW(true);
			if(findRole(listupdateRoles.get(i))==-1)
				storedList.add(listupdateRoles.get(i));
		}
listupdaterebs.remove(reb);
listupdaterebs.put(reb, true);
}
else{
		for(int i=0;i<listupdateRoles.size();i++)
		{	listupdateRoles.get(i).setR(false);
		listupdateRoles.get(i).setW(false);

			storedList.remove(listupdateRoles.get(i));
		}
		listupdaterebs.remove(reb);
		listupdaterebs.put(reb, false);
}
}

public void loadroles(SelectEvent event)
{	
	this.listroles = new ArrayList<OperateurRoles>();
	List<Role> roles = new ArrayList<Role>();
	if(event!=null){
	roles.addAll(getRoleService().getByType((String) event.getObject()));}
	if(roles.size()==0)
	{  roles.addAll(getRoleService().findAllRole()); }
	for(Role role:roles)
	{
		OperateurRoles or = new OperateurRoles();
		OperateurRolesId id = new OperateurRolesId();
		id.setIdrol(role.getIdrol());
		or.setId(id);
		or.setRole(role);
		if(findRole(or)!=-1)
		{
			this.listroles.add(this.storedList.get(findRole(or)));
	}
		else
	{
			this.listroles.add(or);
	}
		}
}

public void loadupdateRoles(SelectEvent event)
{
	this.listupdateRoles = new ArrayList<OperateurRoles>();
	List<Role> roles = new ArrayList<Role>();
	if(event!=null)
	{roles.addAll(getRoleService().getByType((String) event.getObject()));}
	if(roles.size()==0)
	{
		roles.addAll(getRoleService().findAllRole());
	}
	for(Role role:roles)
	{
		OperateurRoles or = new OperateurRoles();
		OperateurRolesId id = new OperateurRolesId();
		id.setIdrol(role.getIdrol());
		or.setId(id);
		or.setRole(role);
		if(findRole(or)!=-1)
		{
			this.listupdateRoles.add(this.storedList.get(findRole(or)));
	}else if(findRoleop(or)!=-1)
	{
		this.listupdateRoles.add(this.selectedOperateur.getListroles().get(findRoleop(or)));
	}
		else
	{
			this.listupdateRoles.add(or);
	}
		}
	
	
}

public int findRoleop(OperateurRoles op)
{		
List<OperateurRoles> l=getOperateurService().getopRoles(selectedOperateur);
	for(OperateurRoles or: l)
	{
		if(or.getRole().getIdrol()==op.getRole().getIdrol())
		{
			return l.indexOf(or);
		}
	}
	return -1;
}

public int findRole(OperateurRoles op)
{

	for(OperateurRoles or: this.storedList)
	{
		if(or.getRole().getIdrol()==op.getRole().getIdrol())
		{
			return this.storedList.indexOf(or);
		}
	}
	return -1;
}

private void saveAffiliationOperateur(Affiliation a,Operateur op)
{try{
	SbiOperateurAffiliation sbi;
	while(a.getAffiliation()!=null)
	{
		sbi=new SbiOperateurAffiliation();
		sbi.setIdaff(a.getIdaff());
		sbi.setIdop(op.getIdop());
		getSbiService().insertSbiOperateurAffiliation(sbi);
		a=a.getAffiliation();
	}
}
catch(Exception e)
{
}
}

public void deleteUser()
{
	try {
		getOperateurRolesService().deleteRolesbyoperateur(selectedOperateur);
		getSbiService().deletebyOperateur(selectedOperateur.getIdop());

		getOperateurService().deleteOperateur(this.selectedOperateur);
		FacesMessage msg = new FacesMessage("Suppression avec succés");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Trace trace = new Trace();
		 trace .setAction("Suppression operateur "+selectedOperateur.getLogin());
		 trace.setObjet("Operateur");
		 getTraceService().insertTrace(trace);
	} catch (Exception e) {
		// TODO: handle exception
		FacesMessage msg = new FacesMessage("Erreur");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(e.getMessage());
	}
}

public void updateoperateur()
{	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	try {
		selectedOperateur.setPersonnel(getPersonnelService().getByid(idPersonnel));
		Set<Affiliation> aff = new HashSet<Affiliation>();
		for(Integer index:selectedUpdateAffiliation)
		{
			aff.add(getAffiliationService().getByid(index));
			displayRoot(getAffiliationService().getByid(index));
		}
		this.selectedOperateur.setAffiliations(aff);
		if(this.tmpPassword.length()>2){
			this.selectedOperateur.setMotPasse(encoder.encode(this.tmpPassword));}
		getOperateurService().updateOperateur(this.selectedOperateur);
		
			getOperateurRolesService().deleteRolesbyoperateur(selectedOperateur);
		getSbiService().deletebyOperateur(selectedOperateur.getIdop());
		for(OperateurRoles op:this.storedList)
		{
			op.getId().setIdop(this.selectedOperateur.getIdop());
			op.setOperateur(this.selectedOperateur);
			if(op.getR())
			{
			if(getOperateurRolesService().getByid(op.getId())!=null)
			{
				getOperateurRolesService().updateOperateurRoles(op);
			}
			else{
				getOperateurRolesService().insertOperateurRoles(op);
			}
			}
		}		
		Trace trace = new Trace();
		 trace .setAction("Modification operateur "+selectedOperateur.getLogin());
		 trace.setObjet("Operateur");
		 getTraceService().insertTrace(trace);
		FacesMessage msg = new FacesMessage("Operateur modifié avec succés");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
	} catch (Exception e) {
		// TODO: handle exception
		FacesMessage msg = new FacesMessage("Erreur");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(e.getMessage());
	}
}

public void vider()
{
	listroles = new ArrayList<OperateurRoles>();
	operateur = new Operateur();
	selectedOperateur = new Operateur();
	//storedList;
	selectedOp = new OperateurRoles() ;
	idPersonnel=0;
	selectedOperateurs= new ArrayList<>();
	//listupdateRoles;
	//sizeList;
	//rebriques;
	tmpPassword="";
	listrebs= new HashMap<>();
	listupdaterebs=new HashMap<>();
	//selectedAffiliation;
	label="";
	//listaffs;
	types=new ArrayList<String>();
	//listaffiliations;
	//selectedUpdateAffiliation;
	//listAffiliation;
}

public void insertoperateur()
{
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	try {
	Set<Affiliation> aff = new HashSet<Affiliation>();
	System.out.println("^^ ^^  ^^ ^^ ^^ ^^");
	for(int z=0;z<selectedAffiliation.size();z++)
	{
		List<Integer> inter = new ArrayList<Integer>();
			inter.addAll(trouverFils(selectedAffiliation.get(z)));
		for(int i=0;i<inter.size();i++)
		{
			aff.add(affiliationService.getByid(inter.get(i)));
		}
		
		displayRoot(getAffiliationService().getByid(selectedAffiliation.get(z)));
	}
	System.out.println("^^ ^^  ^^ ^^ ^^ ^^");
	//aff.addAll(listaffiliation);
		operateur.setLangue(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
		operateur.setPersonnel(getPersonnelService().getByid(idPersonnel));
		this.operateur.setAffiliations(aff);
		String pass = operateur.getMotPasse();
		this.operateur.setMotPasse(encoder.encode(this.operateur.getMotPasse()));
		getOperateurService().insertOperateur(this.operateur);

		for(OperateurRoles op:this.storedList)
		{
			op.getId().setIdop(this.operateur.getIdop());
			op.setOperateur(this.operateur);
			getOperateurRolesService().insertOperateurRoles(op);
			
		}

		Trace trace = new Trace();
		 trace .setAction("Insertion operateur "+operateur.getLogin());
		 trace.setObjet("Operateur");
		 getTraceService().insertTrace(trace);
		 
	        vider();
	        RequestContext.getCurrentInstance().update("form:formopajout:fie1");
		
		FacesMessage msg = new FacesMessage("Operateur crée avec succés");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
	} catch (Exception e) {
		// TODO: handle exception
		FacesMessage msg = new FacesMessage("Erreur");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(e.getMessage());
	}
}



public void onEdit(RowEditEvent event) {  
	this.storedList.add((OperateurRoles) event.getObject());
} 
public RoleService getRoleService() {
	return roleService;
}
public void setRoleService(RoleService roleService) {
	this.roleService = roleService;
}
public OperateurService getOperateurService() {
	return operateurService;
}
public void setOperateurService(OperateurService operateurService) {
	this.operateurService = operateurService;
}
public Operateur getOperateur() {
	return operateur;
}
public void setOperateur(Operateur operateur) {
	this.operateur = operateur;
}
public List<Operateur> getListusers() {
	this.listusers = new ArrayList<Operateur>();
	this.listusers.addAll(getOperateurService().findAllOperateur());
	return listusers;
}
public void setListusers(List<Operateur> listusers) {
	this.listusers = listusers;
}
public String getCurrentMenu() {
	return currentMenu;
}
public void setCurrentMenu(String currentMenu) {
	this.currentMenu = currentMenu;
}
public List<OperateurRoles> getListroles() {
	return listroles;
}
public void setListroles(List<OperateurRoles> listroles) {
	this.listroles = listroles;
}
public List<OperateurRoles> getStoredList() {
	return storedList;
}

public int getSizeList() {
	return sizeList;
}
public void setSizeList(int sizeList) {
	this.sizeList = sizeList;
}
public void setStoredList(List<OperateurRoles> storedList) {
	this.storedList = storedList;
}
public OperateurRoles getSelectedOp() {
	return selectedOp;
}
public void setSelectedOp(OperateurRoles selectedOp) {
	this.selectedOp = selectedOp;
}
private List<Operateur> listusers;

public String getNode() {
	
	return node;
}
public void setNode(String node) {
	this.node = node;
}
public List<Integer> getSelectedUpdateAffiliation() {
	return selectedUpdateAffiliation;
}

public void setSelectedUpdateAffiliation(List<Integer> selectedUpdateAffiliation) {
	this.selectedUpdateAffiliation = selectedUpdateAffiliation;
}

public OperateurRolesService getOperateurRolesService() {
	return operateurRolesService;
}
public void setOperateurRolesService(OperateurRolesService operateurRolesService) {
	this.operateurRolesService = operateurRolesService;
}

public List<Operateur> getSelectedOperateurs() {
	return selectedOperateurs;
}
public void setSelectedOperateurs(List<Operateur> selectedOperateurs) {
	this.selectedOperateurs = selectedOperateurs;
}
public Operateur getSelectedOperateur() {
	return selectedOperateur;
}
public void setSelectedOperateur(Operateur selectedOperateur) {
	this.selectedOperateur = selectedOperateur;
}
public List<OperateurRoles> getListupdateRoles() {
	return listupdateRoles;
}
public void setListupdateRoles(List<OperateurRoles> listupdateRoles) {
	this.listupdateRoles = listupdateRoles;
}

public String getTmpPassword() {
	return tmpPassword;
}

public void setTmpPassword(String tmpPassword) {
	this.tmpPassword = tmpPassword;
}

public PersonnelService getPersonnelService() {
	return personnelService;
}

public void setPersonnelService(PersonnelService personnelService) {
	this.personnelService = personnelService;
}

public Map<String,Integer> getListPersonnel() {
	return listPersonnel;
}

public void setListPersonnel(Map<String,Integer> listPersonnel) {
	this.listPersonnel = listPersonnel;
}

public int getIdPersonnel() {
	return idPersonnel;
}
public void setIdPersonnel(int idPersonnel) {
	this.idPersonnel = idPersonnel;
}
public Map<String, Boolean> getListrebs() {
	return listrebs;
}

public void setListrebs(Map<String, Boolean> listrebs) {
	this.listrebs = listrebs;
}

public List<String> getRebriques() {
	return rebriques;
}

public void setRebriques(List<String> rebriques) {
	this.rebriques = rebriques;
}

public SbiOperateurAffiliationService getSbiService() {
	return sbiService;
}

public void setSbiService(SbiOperateurAffiliationService sbiService) {
	this.sbiService = sbiService;
}

public ProfileUserSpago getProfileUsr() {
	return profileUsr;
}

public void setProfileUsr(ProfileUserSpago profileUsr) {
	this.profileUsr = profileUsr;
}

public UserSpago getUserSpago() {
	return userSpago;
}

public void setUserSpago(UserSpago userSpago) {
	this.userSpago = userSpago;
}

public List<Integer> getSelectedAffiliation() {
	return selectedAffiliation;
}

public void setSelectedAffiliation(List<Integer> selectedAffiliation) {
	this.selectedAffiliation = selectedAffiliation;
}


public void display()
{
if(selectedAffiliation!=null)
	System.out.println(selectedAffiliation.size());
	
}

public AffiliationService getAffiliationService() {
	return affiliationService;
}

public void setAffiliationService(AffiliationService affiliationService) {
	this.affiliationService = affiliationService;
}

public List<Affiliation> getListaffiliation() {
	return listaffiliation;
}

public void setListaffiliation(List<Affiliation> listaffiliation) {
	this.listaffiliation = listaffiliation;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public List<Affiliation> getListaffs() {
	listaffs = getAffiliationService().getAffiliation(type);
	return listaffs;
}

public void setListaffs(List<Affiliation> listaffs) {
	this.listaffs = listaffs;
}

public Map<String,Integer> getListaffiliations() {
//	listaffs = getAffiliationService().getAffiliation(type.substring(0,2));
	System.out.println(type);
//listaffiliations=new HashMap<String, Integer>();
//	for(Affiliation a:listaffs)
//	{
//		listaffiliations.put(a.getDesignation(), a.getIdaff());
//	}
	return listaffiliations;
}

public void setListaffiliations(Map<String,Integer> listaffiliations) {
	this.listaffiliations = listaffiliations;
}

public List<String> getTypes() {
	return types;
}

public void setTypes(List<String> types) {
	this.types = types;
}

public Map<String, Boolean> getListupdaterebs() {
	return listupdaterebs;
}

public void setListupdaterebs(Map<String, Boolean> listupdaterebs) {
	this.listupdaterebs = listupdaterebs;
}
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public List<Affiliation> getListAffiliation() {
	return listAffiliation;
}
public void setListAffiliation(List<Affiliation> listAffiliation) {
	this.listAffiliation = listAffiliation;
}
public TraceService getTraceService() {
	return traceService;
}

public void setTraceService(TraceService traceService) {
	this.traceService = traceService;
}
}
