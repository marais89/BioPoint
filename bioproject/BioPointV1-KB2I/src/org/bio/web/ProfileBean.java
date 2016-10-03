package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.Parametre;
import org.bio.model.Role;
import org.bio.service.OperateurService;
import org.bio.service.ParametreService;
import org.hibernate.Hibernate;
import org.omnifaces.util.Ajax;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


@ManagedBean
@RequestScoped
public class ProfileBean implements Serializable
{
	private Map<String,String> themes;
	private String theme;
	
	@ManagedProperty(value = "#{operateurServiceImpl}")
	private OperateurService operateurService;
	
	//private Map<String, Boolean> rights;
	
	private Operateur currentUser;
	
    private MenuModel simpleMenuModel;
	private List<DefaultSubMenu> subs; 
	private String apass;
	
	private String pass;
	private String pass2;

	private boolean flag ;
	private Parametre param;
	@ManagedProperty(value="#{parametreServiceImpl}")
	private ParametreService parametreService;
	@PostConstruct
	private void init()
	{
try{
		 simpleMenuModel = new DefaultMenuModel();
		theme = "redmond";
	currentUser = new Operateur();
		themes = new TreeMap<String, String>();
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Aristo", "aristo");
        themes.put("Kb2i", "kb2i");
        themes.put("Bleu Kb2i", "bleukb2i");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");
    	subs = new ArrayList<DefaultSubMenu>();
    	flag= true;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		currentUser = getOperateurService().getByLogin(authentication.getName());
		currentUser.setListroles(getOperateurService().getopRoles(currentUser));
		 
}
catch(Exception e){
	
}
	}
	class ComparateurRole implements Comparator<Role> {
		@Override
		public int compare(Role s1,Role s2){
	                //tri desc
			if (s1.getType().compareTo(s2.getType()) == 1) {
				return -1;
			} else if (s1.getType().compareTo(s2.getType()) == -1) {
				return 1;        	
			} else {
				return 0;
			}
		}      
	}
	

	private int finMenu(String s)
	{		

	
		for(DefaultSubMenu sub:subs)
		{
			if(sub.getLabel().equals(s))
			{
				return subs.indexOf(sub);
				
			}
			
		}
		return -1;
	}
	public void updateuser()
	{
		try{
		if(apass.length()>0)
		{BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			
		if(encoder.matches(apass, currentUser.getMotPasse()))
		{
		currentUser.setMotPasse(encoder.encode(pass));
			
			
			
		}
		else{
			  FacesMessage msg = new FacesMessage("Mot de passe incorret");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        return;
		}
		}
		String lang=FacesContext.getCurrentInstance().getViewRoot().getLocale().getDisplayLanguage();
		lang=lang.substring(0, 2);
		currentUser.setLangue(lang);
		getOperateurService().updateOperateur(currentUser);
		  FacesMessage msg = new FacesMessage("Opérateur modifié avec succés");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e)
		{
			
		}
	
	}
	public void updateParam()
	{
		try{
			getParametreService().updateParametre(param);
			 FacesMessage msg = new FacesMessage("Parametre modifié avec succés");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e)
		{
			 FacesMessage msg = new FacesMessage("erruer");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	private void buildMenu()
	{		Hibernate.initialize(currentUser.getListroles());

		
		if(flag)
		{
		String type ="";
		
		theme = currentUser.getTheme();
		List<Role> listroles = new ArrayList<Role>();
		int x=0;
		for(OperateurRoles role:currentUser.getListroles())
		{
		listroles.add(role.getRole());
		}
		for(Role role:listroles)
		{type= role.getType();

	x = finMenu(type);

		if(x!=-1)	
		{
				DefaultMenuItem menuItem = new DefaultMenuItem();
		        menuItem.setValue(role.getRoleName());
		        menuItem.setUrl(role.getUrl());
		        subs.get(x).addElement(menuItem);
		}
		else{
			DefaultSubMenu sub = new DefaultSubMenu();
			sub.setLabel(type);
			DefaultMenuItem menuItem = new DefaultMenuItem(); 
			menuItem.setValue(role.getRoleName());
		        menuItem.setUrl(role.getUrl());
		        menuItem.setIgnoreAutoUpdate(true);
		        sub.addElement(menuItem);
		       
		        subs.add(sub);
		}		
		}
	
		for(DefaultSubMenu sub:subs)
        simpleMenuModel.addElement(sub);
		}
		flag =false;
	}
	public boolean loadRights(String page)
	{		
		
		for(OperateurRoles op:currentUser.getListroles())
		{
			if(op.getRole().getUrl().equalsIgnoreCase(page))
			{						
				return op.getW();
			}
		}
		return false;
	}
	public ParametreService getParametreService() {
		return parametreService;
	}
	public void setParametreService(ParametreService parametreService) {
		this.parametreService = parametreService;
	}
	public Parametre getParam() {
		return param;
	}
	public void setParam(Parametre param) {
		this.param = param;
	}
	public Map<String, String> getThemes() {
		return themes;
	}
	public void setThemes(Map<String, String> themes) {
		this.themes = themes;
	}
	public String getTheme() {
		if(currentUser.getTheme()!=null)
			return currentUser.getTheme();
		else
			
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public OperateurService getOperateurService() {
		return operateurService;
	}
	public void setOperateurService(OperateurService operateurService) {
		this.operateurService = operateurService;
	}
	public Operateur getCurrentUser() {
	
		return currentUser;
	}
	public void setCurrentUser(Operateur currentUser) {
		this.currentUser = currentUser;
	}
	
	public MenuModel getSimpleMenuModel() {
		buildMenu();
		return simpleMenuModel;
	}
	public void setSimpleMenuModel(MenuModel simpleMenuModel) {
		this.simpleMenuModel = simpleMenuModel;
	}
	public String getApass() {
		return apass;
	}
	public void setApass(String apass) {
		this.apass = apass;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	
}