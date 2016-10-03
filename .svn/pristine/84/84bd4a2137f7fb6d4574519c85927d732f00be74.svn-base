package org.bio.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.bio.model.Personnel;
import org.bio.service.PersonnelService;

@FacesConverter(value = "personnelConv")
public class personnelConv implements Converter {
	
	@ManagedProperty(value="#{personnelServiceImpl}")
	private  PersonnelService personnelService;
	  public List<Personnel> listePersonnelDB;
	  
	  @PostConstruct
		private void init()
		
		{
		  listePersonnelDB= new ArrayList<Personnel>();
		  listePersonnelDB=personnelService.findAllPersonnels(); 
		}
	 
	    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
	        if (submittedValue.trim().equals("")) {
	            return null;
	        } else {
	            try {
	                int number = Integer.parseInt(submittedValue);
	 
	                for (Personnel p : listePersonnelDB) {
	                    if (p.getIdper() ==(int) number) {
	                        return p;
	                    }
	                }
	 
	            } catch(NumberFormatException exception) {
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Personnel"));
	            }
	        }
	 
	        return null;
	    }
	 
	    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
	        if (value == null || value.equals("")) {
	            return "";
	        } else {
	            return String.valueOf(((Personnel) value).getIdper());
	        }
	    }
	}


