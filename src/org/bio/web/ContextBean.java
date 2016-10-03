package org.bio.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.Parametre;
import org.bio.model.Role;
import org.bio.service.OperateurService;
import org.bio.service.ParametreService;
import org.bio.service.RoleService;
import org.omnifaces.util.Faces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@SessionScoped
public class ContextBean implements Serializable {
	private Map<String, String> themes;
	private String theme;

	@ManagedProperty(value = "#{operateurServiceImpl}")
	private OperateurService operateurService;
	@ManagedProperty(value = "#{parametreServiceImpl}")
	private ParametreService parametreService;
	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;
//    ** attribu  ** 
	
	
	private List<String> listSabMenu;
	private List<List<Role>> listListItem;
	private List<Role> listroles;
	
	private Operateur currentUser;
	private MenuModel tmpmenu;
	private MenuModel lookmenu;
	private String menu;
	private MenuModel simpleMenuModel;
	private List<DefaultSubMenu> subs;
	private Affiliation societe;
	private boolean flag;
	private boolean ok;
	private Locale locale;
	private Parametre param;
	private Map<String, String> listparam;
	private String logo;
	private List<ArrayList<DefaultMenuItem>> lDMI;
	private String contant="/pages/accueil.xhtml" ;

	@PostConstruct
	private void init() {
		
		lDMI= new ArrayList<ArrayList<DefaultMenuItem>>();
		
		listListItem= new ArrayList<List<Role>>();
		listSabMenu= new ArrayList<String>();
		listSabMenu= roleService.findRebrique();
		listroles= new ArrayList<Role>();
		List<Parametre> list = getParametreService().findAllParametres();
		listparam = new HashMap<String, String>();
		for (Parametre p : list) {
			listparam.put(p.getNomparam(), p.getValueparam());
		}
		simpleMenuModel = new DefaultMenuModel();
		theme = "bluesky";
		ok = true;
		currentUser = new Operateur();
		themes = new TreeMap<String, String>();
		themes.put("Aristo", "aristo");
		themes.put("Black-Tie", "black-tie");
		themes.put("Blitzer", "blitzer");
		themes.put("Bluesky", "bluesky");
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
		flag = true;
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		currentUser = getOperateurService()
				.getByLogin(authentication.getName());
		currentUser.setListroles(getOperateurService().getopRoles(currentUser,
				"L"));
		if (currentUser.getLangue().equalsIgnoreCase("al"))
			locale = new Locale("de");
		else if (currentUser.getLangue().equalsIgnoreCase("an"))
			locale = new Locale("en");
		else
			locale = new Locale(currentUser.getLangue());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		List<Affiliation> l = getOperateurService().getopaffiliation(
				currentUser);
		if (l.size() > 0) {
			Affiliation af = l.get(0);
			getcurrentParametre(af);
		}
		System.out.println(listSabMenu.size());
		//assembage();
		buildMenu();
		for(int i=0;i<subs.size();i++)
		{
			ArrayList<DefaultMenuItem> inter = new ArrayList<>();
			for(int j=0;j<subs.get(i).getElements().size();j++)
			{
			DefaultMenuItem s= (DefaultMenuItem)subs.get(i).getElements().get(j);
			inter.add(s);
			}
			lDMI.add(inter);
		}
	}
	
	public void changePage(int i, int j)
	{
		System.out.println("****** page :"+i+" "+j);
		String s =lDMI.get(i).get(j).getUrl();
		this.contant=s;	
		System.out.println(s);
	}
	
	public void affectPage()
	{
		this.contant="/pages/accueil.xhtml";	
	}
	
	public void changeEmport()
	{
		String s="/pages/export.xhtml";
		this.contant=s;	
	}
	
	public void updatemenu() {
		if (menu.length() == 0) {
			ok = true;
		}

		else {
			tmpmenu = simpleMenuModel;
			System.out.println(menu.length());
			ok = false;
			lookmenu = new DefaultMenuModel();
			for (DefaultSubMenu s : subs) {
				if (contain(menu, s.getLabel())) {
					lookmenu.addElement(s);
				} else {
					for (MenuElement i : s.getElements()) {
						DefaultMenuItem item = (DefaultMenuItem) i;
						DefaultSubMenu st = new DefaultSubMenu();
						if (contain(menu, String.valueOf(item.getValue()))) {

							st.addElement(item);
							st.setLabel(s.getLabel());
							lookmenu.addElement(st);
						}

					}
				}
			}
			if (lookmenu.getElements().isEmpty()) {
				ok = true;
			}

		}
	}

	private void getcurrentParametre(Affiliation af) {
		System.out.println(af.getType());
		if (af.getType().equalsIgnoreCase("So")) {
			societe = af;

			if (societe.getSociete().getLogo() != null)

			{
				try {
					ExternalContext externalContext = FacesContext
							.getCurrentInstance().getExternalContext();
					String relativeWebPath = "/resources/tmp/";
					ServletContext servletContext = (ServletContext) externalContext
							.getContext();
					String absoluteDiskPath = servletContext
							.getRealPath(relativeWebPath);
					File file = new File(absoluteDiskPath + "/"
							+ societe.getSociete().getLogoName());
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(societe.getSociete().getLogo());
					fos.close();

					logo = "/resources/tmp/"
							+ societe.getSociete().getLogoName();
				} catch (Exception e) {
					e.printStackTrace();
					logo = "/resources/img/agency.png";
				}
			} else
				logo = "/resources/img/agency.png";
		}

		else {
			getcurrentParametre(af.getAffiliation());

		}
	}

	private boolean contain(String s1, String s2) {
		return StringUtils.containsIgnoreCase(s2, s1);
	}

	class ComparateurRole implements Comparator<Role> {
		@Override
		public int compare(Role s1, Role s2) {
			// tri desc
			if (s1.getType().compareTo(s2.getType()) == 1) {
				return -1;
			} else if (s1.getType().compareTo(s2.getType()) == -1) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	private int finMenu(String s) {

		for (DefaultSubMenu sub : subs) {
			if (sub.getLabel().equals(s)) {
				return subs.indexOf(sub);

			}

		}
		return -1;
	}

	public void updatetheme() {
		try {

			getOperateurService().updateOperateur(currentUser);
		} catch (Exception e) {

		}

	}

	private void buildMenu() {

		if (flag) {
			String type = "";

			theme = currentUser.getTheme();
			List<Role> listroles = new ArrayList<Role>();
			int x = 0;
			for (OperateurRoles role : currentUser.getListroles()) {
				listroles.add(role.getRole());
			}
			for (Role role : listroles) {
				type = role.getType();

				x = finMenu(type);

				if (x != -1) {
					DefaultMenuItem menuItem = new DefaultMenuItem();
					menuItem.setValue(role.getRoleName());
					menuItem.setUrl(role.getUrl());
					menuItem.setStyleClass("leftside");
					menuItem.setIcon(role.getIcon());
					subs.get(x).addElement(menuItem);
				} else {
					DefaultSubMenu sub = new DefaultSubMenu();

					sub.setLabel(type);
					DefaultMenuItem menuItem = new DefaultMenuItem();
					menuItem.setValue(role.getRoleName());
					menuItem.setUrl(role.getUrl());
					menuItem.setIgnoreAutoUpdate(true);
					menuItem.setIcon(role.getIcon());
					menuItem.setStyleClass("leftside");

					sub.addElement(menuItem);
					subs.add(sub);
				}

			}

			for (DefaultSubMenu sub : subs) {
				sub.setLabel((String) Faces.evaluateExpressionGet("#{msg['"
						+ sub.getLabel() + "']}"));
				simpleMenuModel.addElement(sub);
			}
			flag = false;

		}
	}
	

	public String getLanguage() {
		return locale.getLanguage();
	}

	/**
	 * Permet de modifier le language de la page
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		locale = new Locale(language);
	}

	/**
	 * Permet d'obtenir la locale courante
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Permet d'obtenir la liste des langues supportées
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SelectItem[] getLocales() {
		ArrayList items = new ArrayList<SelectItem>();
		Application application = FacesContext.getCurrentInstance()
				.getApplication();

		Iterator<Locale> supportedLocales = application.getSupportedLocales();
		while (supportedLocales.hasNext()) {
			Locale loc = supportedLocales.next();
			items.add(new SelectItem(loc.getLanguage(), loc
					.getDisplayName(locale)));
		}
		SelectItem[] locales = new SelectItem[items.size()];
		items.toArray(locales);
		return locales;
	}

	public Map<String, String> getThemes() {
		return themes;
	}

	public void setThemes(Map<String, String> themes) {
		this.themes = themes;
	}

	public String getTheme() {
		if (currentUser.getTheme() != null)
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
		if (ok) {
			buildMenu();
			return simpleMenuModel;
		}

		else {
			return lookmenu;
		}
	}

	public void setSimpleMenuModel(MenuModel simpleMenuModel) {
		this.simpleMenuModel = simpleMenuModel;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Parametre getParam() {
		return param;
	}

	public void setParam(Parametre param) {
		this.param = param;
	}

	public ParametreService getParametreService() {
		return parametreService;
	}

	public void setParametreService(ParametreService parametreService) {
		this.parametreService = parametreService;
	}

	public Affiliation getSociete() {
		return societe;
	}

	public void setSociete(Affiliation societe) {
		this.societe = societe;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Map<String, String> getListparam() {
		return listparam;
	}

	public void setListparam(Map<String, String> listparam) {
		this.listparam = listparam;
	}

	public List<String> getListSabMenu() {
		return listSabMenu;
	}

	public void setListSabMenu(List<String> listSabMenu) {
		this.listSabMenu = listSabMenu;
	}

	public List<List<Role>> getListListItem() {
		return listListItem;
	}

	public void setListListItem(List<List<Role>> listListItem) {
		this.listListItem = listListItem;
	}

	public List<Role> getListroles() {
		return listroles;
	}

	public void setListroles(List<Role> listroles) {
		this.listroles = listroles;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public List<DefaultSubMenu> getSubs() {
		return subs;
	}


	


	public List<ArrayList<DefaultMenuItem>> getlDMI() {
		return lDMI;
	}

	public void setlDMI(List<ArrayList<DefaultMenuItem>> lDMI) {
		this.lDMI = lDMI;
	}
	public String getContant() {
		return contant;
	}
	public void setContant(String contant) {
		this.contant = contant;
	}	

}
