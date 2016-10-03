package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "localeManagedBean")
@SessionScoped
public class LocaleManagedBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * La locale courante
     */
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Permet d'obtenir la nom de la locale
     * @return
     */
    public String getLanguage() {
        return locale.getLanguage();
    }

    /**
     * Permet de modifier le language de la page
     * @param language
     */
    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    /**
     * Permet d'obtenir la locale courante
     * @return
     */
    public Locale getLocale()
    {
        return locale;
    }

    /**
     * Permet d'obtenir la liste des langues supportées
     * @return
     */
    @SuppressWarnings("unchecked")
	public SelectItem[] getLocales() {
            ArrayList items = new ArrayList<SelectItem>();
            Application application = FacesContext.getCurrentInstance().getApplication();

            Iterator<Locale> supportedLocales = application.getSupportedLocales();
            while (supportedLocales.hasNext()) {
                    Locale loc = supportedLocales.next();
                    items.add(new SelectItem(loc.getLanguage(), loc.getDisplayName(locale) ));
            }
            SelectItem[] locales = new SelectItem[items.size()];
            items.toArray(locales);
            return locales;
    }

}