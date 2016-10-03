package org.bio.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

public class IniService {

		
    public String getUrl() throws InvalidFileFormatException, FileNotFoundException, IOException{
    	Ini ini = new Ini(new FileReader(getRealPath()));
       	Section section = ini.get("conf");
       
    	return section.get("url");
    
      }
    public String getPassword() throws InvalidFileFormatException, FileNotFoundException, IOException{
    	Ini ini = new Ini(new FileReader(getRealPath()));
       	Section section = ini.get("conf");

    	return section.get("password");
    
      }
    public String getUrlServerBiop() throws InvalidFileFormatException, FileNotFoundException, IOException{
    	Ini ini = new Ini(new FileReader(getRealPath()));
       	Section section = ini.get("conf");
       
    	return section.get("urlServerBiop");
    
      }
    public String getUserName() throws InvalidFileFormatException, FileNotFoundException, IOException{
    	Ini ini = new Ini(new FileReader(getRealPath()));
       	Section section = ini.get("conf");
    	
    	return section.get("username");
    
      }
    public String getUrlDW() throws InvalidFileFormatException, FileNotFoundException, IOException{
    	Ini ini = new Ini(new FileReader(getRealPath()));
       	Section section = ini.get("conf");
    	
    	return section.get("urlDW");
    
      }
    public String getUrlSpago() throws InvalidFileFormatException, FileNotFoundException, IOException{
    	Ini ini = new Ini(new FileReader(getRealPath()));
       	Section section = ini.get("conf");
    	
    	return section.get("urlSpago");
    
      }
    public String getUrlServerSpago() throws InvalidFileFormatException, FileNotFoundException, IOException{
    	Ini ini = new Ini(new FileReader(getRealPath()));
       	Section section = ini.get("conf");
    	System.out.println(section.get("urlServerSpago"));
    	return section.get("urlServerSpago");
    
      }
	
	 public String getRealPath() {
	  		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	  		ServletContext servletContext = (ServletContext) externalContext.getContext();
	  		String absoluteDiskPath = servletContext.getRealPath("/WEB-INF/conf/conf.ini");
	  		return absoluteDiskPath;
	  	}

	 



}