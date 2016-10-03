package org.bio.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.omnifaces.cdi.ViewScoped;

@ManagedBean
@ViewScoped
public class TooltipsController implements Serializable {
 private String name;
 private Date birthDate;
 private int children;
 // getter / setter
 public String getErrorMessage(final String clientId) {
	    Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages(clientId);
	    if (iter.hasNext()) {
	        return iter.next().getDetail(); // or getSummary()
	    }
	 
	    return "";
	}
}