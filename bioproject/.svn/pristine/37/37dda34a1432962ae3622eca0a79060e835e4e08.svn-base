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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable{
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
public TraceService getTraceService() {
	return traceService;
}

public void setTraceService(TraceService traceService) {
	this.traceService = traceService;
}
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
private String action="IND";
private String action2;
private int Wfichier[]= new int[10];
private int Wsuperviseur[]= new int[10];
private int Wutilitaire[]= new int[10];
private int Wplanification[]= new int[10];
private int Wterminaux[]= new int[10];
private int Wbi[]= new int[10];
private String listRubrique[]= new String[10];
private String strOut;
private String strOut2;
private String type;
private List<OperateurRoles> listOPRO;
private List<OperateurRoles> listOPROZero;
@PostConstruct
private void init()
{
	listroles= new ArrayList<OperateurRoles>();
	this.selectedOperateurs = new ArrayList<Operateur>();
	this.storedList = new ArrayList<OperateurRoles>();
	this.operateur = new Operateur();
	listPersonnel = new HashMap<String, Integer>();
	for(Personnel p:getPersonnelService().findAllWithoutPersonnels())
	{
		listPersonnel.put(p.getFullname(), p.getIdper());
	}
	rebriques =new ArrayList<String>();
//	rebriques.add("role.file");
//	currentMenu="role.file";
	rebriques.addAll(getRoleService().findRebrique());
	listrebs=new HashMap<>();
	for(String str:rebriques)
	{
		listrebs.put(str, false);
	}
	listupdaterebs = listrebs;
	type="So";
	selectedAffiliation = new ArrayList<Integer>();
	types=getAffiliationService().getAffiliationTypes();
	listaffiliation =new ArrayList<Affiliation>();
	selectedUpdateAffiliation=new ArrayList<Integer>();
	// NOUVELLE EDITION
	listOPROZero= new ArrayList<OperateurRoles>();
	listOPRO= new ArrayList<OperateurRoles>();
	factory();
	}

public void factory()
{
	listOPRO= new ArrayList<OperateurRoles>();
	List<Role> listRole = new ArrayList<Role>();
	listRole=roleService.findAllRole();
	for(int i=0;i<listRole.size();i++)
	{
		OperateurRoles opr= new OperateurRoles();
		OperateurRolesId id = new OperateurRolesId();
		id.setIdrol(listRole.get(i).getIdrol());
		opr.setId(id);
		opr.setRole(listRole.get(i));
		opr.setR(false);
		opr.setW(false);
		opr.getRole().setEditable(false);
		listOPRO.add(opr);
	}	
}


public void affect()
{
 this.action2=this.action;
 System.out.println(action);
 System.out.println(action2);
}

private String findAffType(List<Affiliation> listaffop)
{String s="all";
	if(listaffop.size()>0)
	{
	for(Affiliation a:listaffop)
	{	if(!listaffop.contains(a.getAffiliation()))
		{
		s=a.getDescription();
	}
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
	listroles= new ArrayList<>();
	
}
public void loadonUpdate()
{
	init();	
	List<Affiliation> listaffop = getOperateurService().getopaffiliation(selectedOperateur);
System.out.println("selected operrateur "+selectedOperateur.getLogin());
System.out.println("listaffop ***/ "+listaffop.size());
String s=findAffType(listaffop);
System.out.println("1");
if(!s.equalsIgnoreCase("all"))
{System.out.println("2");listaffiliations =new HashMap<String, Integer>();
	for(Affiliation a:listaffop)
	{System.out.println(a.getDesignation());
		if(a.getDescription().equals(s)){
			System.out.println(a.getDesignation()+"**");
			listaffiliations.put(a.getDesignation(), a.getIdaff());
	selectedUpdateAffiliation.add(a.getIdaff());	
		}
	}
	System.out.println("3");
types.clear();
types.add(s);
	type=s;
	listroles= new ArrayList<>();
}
else{}
loadroles(null);	
	loadupdateRoles(null);
	listOPROZero= new ArrayList<>();
	listOPROZero= operateurService.getopRoles0(selectedOperateur);
	listOPRO= new ArrayList<OperateurRoles>();
	listOPRO= operateurService.getopRoles(selectedOperateur);
	parceIt();
	listupdateRoles = new ArrayList<>();
	
//	System.out.println(listOPRO.size());
//	for(int i=0;i<listOPRO.size();i++)
//	{
//		System.out.println("****************************");
//		System.out.println("read"+listOPRO.get(i).getR());  
//		System.out.println("write"+listOPRO.get(i).getW()); 
//		System.out.println("write"+listOPRO.get(i).getOperateur().getIdop()); 
//		System.out.println("write"+listOPRO.get(i).getRole().getNom()); 
//	}	
	//RequestContext.getCurrentInstance().execute("makeUpdate(currentMenu)");
}

public void clearlist(String type)
{
	listaffs= getAffiliationService().getAffiliation(type);
}
public void clearLoad(SelectEvent event)
{
	
	selectedOperateur.setListroles(getOperateurService().getopRoles(selectedOperateur));
//System.out.println(selectedOperateur.getLogin());
//System.out.println(selectedOperateur.getListroles().size());

	this.listupdateRoles =new ArrayList<OperateurRoles>();
	this.currentMenu = "";
	
}
public void displayRoot(Affiliation h) {
	h.setAffiliationChildren(getAffiliationService().listaffiliation(h));
	
	for (Affiliation a : h.getAffiliationChildren()) {
		listaffiliation.add(a);
		displayRoot(a);
	}
}
public void disableType()
{
	if(selectedAffiliation.size()>0)
	{List<String> rmtypes = new  ArrayList<String>();
		for(String s:types)
		{
			if(!s.substring(0, 2).equalsIgnoreCase(type.substring(0,2)))
			{
				rmtypes.add(s);
			}
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
	{List<String> rmtypes = new  ArrayList<String>();
		for(String s:types)
		{
			if(!s.substring(0, 2).equalsIgnoreCase(type.substring(0,2)))
			{
				rmtypes.add(s);
			}
		}
		types.removeAll(rmtypes);
	}
	else{
		types=getAffiliationService().getAffiliationTypes();
	}
}
public void selectAll(String reb)
{
if(!listrebs.get(reb))
{
for(int i=0;i<listroles.size();i++)
{	listroles.get(i).setR(true);
	listroles.get(i).setW(true);
	storedList.add(	listroles.get(i));
}
listrebs.remove(reb);
listrebs.put(reb, true);
}
else{


		for(int i=0;i<listroles.size();i++)
		{	listroles.get(i).setR(false);
			listroles.get(i).setW(false);

			storedList.remove(	listroles.get(i));
		}
		listrebs.remove(reb);
		listrebs.put(reb, false);

}
///loadroles();
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
			this.listroles.add(this.storedList.get(findRole(or)));
	}
		else
	{	this.listroles.add(or);	}
		}
}

public void loadupdateRoles(SelectEvent event)
{
 selectedOperateur.setListroles(operateurService.getopRoles(selectedOperateur));
	this.listupdateRoles = new ArrayList<OperateurRoles>();
	List<Role> roles = new ArrayList<Role>();
	if(event!=null)
	roles.addAll(getRoleService().getByType((String) event.getObject()));
	System.out.println("roles size "+roles.size());
	if(roles.size()==0)
	{
		System.out.println("r2");
		roles.addAll(getRoleService().findAllRole());
	}
	System.out.println("r3");
	System.out.println("r3 roles size"+roles.size());
	for(Role role:roles)
	{System.out.println("r4");
	
		OperateurRoles or = new OperateurRoles();
		OperateurRolesId id = new OperateurRolesId();
		id.setIdrol(role.getIdrol());
		or.setId(id);
		or.setRole(role);
		if(findRole(or)!=-1)
		{System.out.println("r5*");
			this.listupdateRoles.add(this.storedList.get(findRole(or)));
	}else if(findRoleop(or)!=-1)
	{System.out.println("r6*");
	System.out.println("-*-*---*-*-"+selectedOperateur.getListroles().size());
		this.listupdateRoles.add(this.selectedOperateur.getListroles().get(findRoleop(or)));
		
	}
		else
	{System.out.println("r7*");
			this.listupdateRoles.add(or);
	}
		System.out.println("r8*");
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
System.out.println(e.getMessage());	
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


public void updateoperateur2()
{
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	try {
		System.out.println(selectedOperateur.getIdop());
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
		System.out.println("AVANT EXECUTION DE UPDATE OPERATEUR !");
		getOperateurService().updateOperateur(this.selectedOperateur);	
		System.out.println(selectedOperateur.getLogin());
		/// 	NEW UPDATE
		System.out.println(this.selectedOperateur.toString());
		System.out.println("size listOPRO"+listOPRO.size());
		System.out.println("THIS IS THE NEW FOR UPDATE ");
		for(OperateurRoles or: listOPRO)
		{
			System.out.println("----------------------");
			System.out.println(or.getRole().getNom());
			System.out.println("read :"+or.getR());
			System.out.println("write"+or.getW());
			//System.out.println(or.getOperateur().getIdop());
			or.setOperateur(selectedOperateur);
			or.getId().setIdop(selectedOperateur.getIdop());
			System.out.println(or.getOperateur().getIdop());
			getOperateurRolesService().updateOperateurRoles(or);
		}
		
		Trace trace = new Trace();
		 trace .setAction("Modification operateur "+selectedOperateur.getLogin());
		 trace.setObjet("Operateur");
		 getTraceService().insertTrace(trace);
		FacesMessage msg = new FacesMessage("Operateur modifié avec succés");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        	} catch (Exception e) {
		FacesMessage msg = new FacesMessage("Erreur");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(e.getMessage());
	}
	///////////////////////////////////// FIN UPDATE 2 ////////////////////////////////////////
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
//			getOperateurRolesService().deleteRolesbyoperateur(selectedOperateur);
//		getSbiService().deletebyOperateur(selectedOperateur.getIdop());
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
			}}
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
public void insertoperateur()
{
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	try {
	Set<Affiliation> aff = new HashSet<Affiliation>();
	for(Integer index:selectedAffiliation)
	{
		aff.add(getAffiliationService().getByid(index));
		displayRoot(getAffiliationService().getByid(index));
	}
	aff.addAll(listaffiliation);
		operateur.setLangue(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
		operateur.setPersonnel(getPersonnelService().getByid(idPersonnel));
		this.operateur.setAffiliations(aff);
		String pass = operateur.getMotPasse();
		this.operateur.setMotPasse(encoder.encode(this.operateur.getMotPasse()));
		getOperateurService().insertOperateur(this.operateur);
		//userSpago.registerUser(operateur.getLogin(), pass);

		for(OperateurRoles op:this.listOPRO)
		{
			op.getId().setIdop(this.operateur.getIdop());
			op.setOperateur(this.operateur);
			getOperateurRolesService().insertOperateurRoles(op);
			
		}
		
		/*	for(int i=0;i<selectedAffiliation.size();i++)
			{
				saveAffiliationOperateur(getAffiliationService().getByid(selectedAffiliation.get(i)),operateur);
			}*/
	
		//String[] args = {""};
		//profileUsr.runJobInTOS(args, operateur.getLogin());

		Trace trace = new Trace();
		 trace .setAction("Insertion operateur "+operateur.getLogin());
		 trace.setObjet("Operateur");
		 getTraceService().insertTrace(trace);
		 
		FacesMessage msg = new FacesMessage("Operateur crée avec succés");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
	} catch (Exception e) {
		// TODO: handle exception
		FacesMessage msg = new FacesMessage("une Valeur null a declenché une erreur interne !");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(e.getMessage());
	}
}

public int decode(Boolean r, Boolean w,Boolean ed)
{ 
	if(r.equals(false) && w.equals(false)){if(ed.equals(true)){return 2;}else{return 1;}}
	else if(r.equals(true) && w.equals(false)){if(ed.equals(true)){return 1;}else{return 0;}}
	else{return 0;}
}

public void parceIt()
{
	this.strOut="v";
	List<String> ls= roleService.findRebrique();
	HashMap<String,ArrayList<Integer>> hm= new HashMap<>();
	for(int i=0;i<ls.size();i++)
	{
		ArrayList<Integer> l= new ArrayList<>();		
		if(this.strOut.equals("v")){this.strOut=ls.get(i);}		
		else{this.strOut+=ls.get(i);}
		this.strOut+=";";
		for(int j=0;j<listOPRO.size();j++)
		{
			String str=listOPRO.get(j).getRole().getType();
			if(str.equals(ls.get(i)))
			{System.out.println(strOut +" "+listOPRO.get(j).getR()+listOPRO.get(j).getW());l.add(decode(listOPRO.get(j).getR(),listOPRO.get(j).getW(),listOPRO.get(j).getRole().getEditable()));strOut+=decode(listOPRO.get(j).getR(),listOPRO.get(j).getW(),listOPRO.get(j).getRole().getEditable())+",";}
		}	
		strOut+="*";
		hm.put(ls.get(i),l);
	}	
	

	for(int i=0;i<ls.size();i++)
	{ 
		listRubrique[i]=ls.get(i);
	}
	
		ArrayList<Integer> inter= new ArrayList<>();
		inter.addAll(hm.get("role.file"));
		for(int j=0;j<inter.size();j++)
		{Wfichier[j]=inter.get(j);}
		inter= new ArrayList<>();
		inter.addAll(hm.get("role.super"));
		for(int j=0;j<inter.size();j++)
		{Wsuperviseur[j]=inter.get(j);}		
		inter= new ArrayList<>();
		inter.addAll(hm.get("role.util"));
		for(int j=0;j<inter.size();j++)
		{Wutilitaire[j]=inter.get(j);}
		inter= new ArrayList<>();
		inter.addAll(hm.get("role.plan"));
		for(int j=0;j<inter.size();j++)
		{Wplanification[j]=inter.get(j);}
		
		inter= new ArrayList<>();
		inter.addAll(hm.get("role.ter"));
		
//		for(int j=0;j<inter.size();j++)
//		{Wterminaux[j]=inter.get(j);}
//		inter= new ArrayList<>();
//		inter.addAll(hm.get("role.bi"));
		
		for(int j=0;j<inter.size();j++)
		{Wbi[j]=inter.get(j);}	
		RequestContext.getCurrentInstance().update("form:notvisible");
}

public void applique(int val)
{
	System.out.println("******* ok executer");
	if(val==1)
		{for(int i=0;i<listroles.size();i++)
		{	listroles.get(i).setR(true);
			listroles.get(i).setW(false);
		}}
	else if(val==2)
	{for(int i=0;i<listroles.size();i++)
	{	listroles.get(i).setR(true);
		listroles.get(i).setW(true);
	}}
	else
	{for(int i=0;i<listroles.size();i++)
	{	listroles.get(i).setR(false);
		listroles.get(i).setW(false);
	}}	
}

public void appliqueNew(int val)
{
	
	if(val==1)
		{for(int i=0;i<listOPRO.size();i++)
		{
			if(listOPRO.get(i).getRole().getType().equals(this.currentMenu))
				{listOPRO.get(i).setR(true);
				listOPRO.get(i).setW(false);}
		}
		System.out.println("listOPROZero 1");
		for(int j=0;j<listOPROZero.size();j++)
		{  if(listOPROZero.get(j).getRole().getType().equals(this.currentMenu))
		{listOPROZero.get(j).setR(true);
		 listOPROZero.get(j).setW(false);listOPRO.add(listOPROZero.get(j));}}
		}
	else if(val==2)
	{for(int i=0;i<listOPRO.size();i++)
	{
		if(listOPRO.get(i).getRole().getType().equals(this.currentMenu))
			{listOPRO.get(i).setR(true);
			listOPRO.get(i).setW(true);	}}
	
	for(int j = 0;j<listOPROZero.size();j++)
	{ if(listOPROZero.get(j).getRole().getType().equals(this.currentMenu))
	{listOPROZero.get(j).setR(true);
	 listOPROZero.get(j).setW(true);listOPRO.add(listOPROZero.get(j));}}
	}
	else
	{for(int i=0;i<listOPRO.size();i++)
	{
		if(listOPRO.get(i).getRole().getType().equals(this.currentMenu))
			{listOPRO.get(i).setR(false);
			listOPRO.get(i).setW(false);
	}}
	System.out.println("listOPROZero 3");
	for(int i=0;i<listOPROZero.size();i++)
	{  if(listOPROZero.get(i).getRole().getType().equals(this.currentMenu))
	{listOPROZero.get(i).setR(false);
	 listOPROZero.get(i).setW(false);listOPRO.add(listOPROZero.get(i));}}
	}
}

public void applique2(int val)
{
	
	if(val==1)
		{for(int i=0;i<listupdateRoles.size();i++)
		{	listroles.get(i).setR(true);
			listroles.get(i).setW(false);
		
		}}
	else if(val==2)
	{for(int i=0;i<listupdateRoles.size();i++)
	{	listroles.get(i).setR(true);
		listroles.get(i).setW(true);
		
	}}
	else
	{for(int i=0;i<listupdateRoles.size();i++)
	{	listroles.get(i).setR(false);
		listroles.get(i).setW(false);
	}}
	
}

public void affiche(OperateurRoles opRole)
{
	System.out.println("fonction executer ---");	
}

public void selectActionUpdate(OperateurRoles opRole)
{
	System.out.println(" #### selectActionUpdate");
	if(action.equals("IND"))
	{makeIndUpdate(opRole);}
	else if(action.equals("ATT"))
	{makeAttUpdate(opRole);}
	else if(action.equals("LSL"))
	{makeLslUpdate(opRole);}
}
public void makeIndUpdate(OperateurRoles opRole)
{boolean bol= false;
	for(OperateurRoles or:listOPRO)
	{
		if(or.getRole().getIdrol()==opRole.getRole().getIdrol())
		{or.setR(false);or.setW(false);bol=true;}
	}
	if(bol== false)
	{
		opRole.setR(false);opRole.setW(false);
		listOPRO.add(opRole);
	}
}


public void makeAttUpdate(OperateurRoles opRole)
{boolean bol= false;
for(OperateurRoles or:listOPRO)
{
	if(or.getRole().getIdrol()==opRole.getRole().getIdrol())
	{or.setR(true);or.setW(true);bol=true;}
}
if(bol== false)
{
	opRole.setR(true);opRole.setW(true);
	listOPRO.add(opRole);
}	
}
public void makeLslUpdate(OperateurRoles opRole)
{boolean bol= false;
for(OperateurRoles or:listOPRO)
{
	if(or.getRole().getIdrol()==opRole.getRole().getIdrol())
	{or.setR(true);or.setW(false);bol=true;}
}
if(bol== false)
{
	opRole.setR(true);opRole.setW(false);
	listOPRO.add(opRole);
}	
}



public void selectAction(OperateurRoles opRole)
{
	System.out.println(" #### selectAction");
	if(action.equals("IND"))
	{makeInd(opRole);}
	else if(action.equals("ATT"))
	{makeAtt(opRole);}
	else if(action.equals("LSL"))
	{makeLsl(opRole);}
		
}

public void makeInd(OperateurRoles opRole)
{
	opRole.setR(false);
	opRole.setW(false);
	for(int i=0;i<listOPRO.size();i++)
	{
		if(listOPRO.get(i).getRole().getIdrol() == opRole.getRole().getIdrol())
				{listOPRO.get(i).setR(false);
				listOPRO.get(i).setW(false);}
	}
	//RequestContext.getCurrentInstance().update("formmodifier:roles");
	//RequestContext.getCurrentInstance().update("formopajout:roles");
}

public void makeAtt(OperateurRoles opRole)
{
	opRole.setR(true);
	opRole.setW(true);
	for(int i=0;i<listOPRO.size();i++)
	{
		if(listOPRO.get(i).getRole().getIdrol() == opRole.getRole().getIdrol())
				{listOPRO.get(i).setR(true);
				listOPRO.get(i).setW(true);}
	}
	//RequestContext.getCurrentInstance().update("formmodifier:roles");
	//RequestContext.getCurrentInstance().update("formopajout:roles");
}

public void makeLsl(OperateurRoles opRole)
{
	
	opRole.setR(true);
	opRole.setW(false);
	for(int i=0;i<listOPRO.size();i++)
	{
		if(listOPRO.get(i).getRole().getIdrol() == opRole.getRole().getIdrol())
				{listOPRO.get(i).setR(true);
				listOPRO.get(i).setW(false);}
	}
	//RequestContext.getCurrentInstance().update("formmodifier:roles");
	//RequestContext.getCurrentInstance().update("formopajout:roles");
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
	listaffs = getAffiliationService().getAffiliation(type.substring(0,2));
listaffiliations=new HashMap<String, Integer>();
	for(Affiliation a:listaffs)
	{
		listaffiliations.put(a.getDesignation(), a.getIdaff());
	}
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
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}
public String getAction2() {
	return action2;
}
public void setAction2(String action2) {
	this.action2 = action2;
}

public int[] getWfichier() {
	return Wfichier;
}
public void setWfichier(int[] wfichier) {
	Wfichier = wfichier;
}
public int[] getWsuperviseur() {
	return Wsuperviseur;
}
public void setWsuperviseur(int[] wsuperviseur) {
	Wsuperviseur = wsuperviseur;
}
public int[] getWutilitaire() {
	return Wutilitaire;
}

public void setWutilitaire(int[] wutilitaire) {
	Wutilitaire = wutilitaire;
}

public int[] getWplanification() {
	return Wplanification;
}

public void setWplanification(int[] wplanification) {
	Wplanification = wplanification;
}

public int[] getWterminaux() {
	return Wterminaux;
}

public void setWterminaux(int[] wterminaux) {
	Wterminaux = wterminaux;
}

public int[] getWbi() {
	return Wbi;
}
public void setWbi(int[] wbi) {
	Wbi = wbi;
}

public String[] getListRubrique() {
	return listRubrique;
}

public void setListRubrique(String[] listRubrique) {
	this.listRubrique = listRubrique;
}

public String getStrOut() {
	return strOut;
}

public void setStrOut(String strOut) {
	this.strOut = strOut;
}

public String getStrOut2() {
	return strOut2;
}

public void setStrOut2(String strOut2) {
	this.strOut2 = strOut2;
}

public List<OperateurRoles> getListOPRO() {
	return listOPRO;
}

public void setListOPRO(List<OperateurRoles> listOPRO) {
	this.listOPRO = listOPRO;
}

public List<OperateurRoles> getListOPROZero() {
	return listOPROZero;
}

public void setListOPROZero(List<OperateurRoles> listOPROZero) {
	this.listOPROZero = listOPROZero;
}



}
