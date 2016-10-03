package org.bio.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.bio.jobs.*;
import org.bio.model.Operateur;
import org.bio.report.DatabaseConnection;
import org.bio.service.*;
import org.bio.jobs.ProfileUserSpago;
import org.bio.jobs.UserSpago;
import org.ini4j.InvalidFileFormatException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mysql.jdbc.Statement;

import routines.system.ResumeUtil;
@ManagedBean
@ViewScoped
public class SpagoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{operateurServiceImpl}")
	private OperateurService operateurService;
	private AffiliationServiceImpl affiliationService;
	private Operateur currentUser;
	private ProfileUserSpago profileUsr = new ProfileUserSpago();
	private UserSpago userSpago = new UserSpago();	
	private PasswordFetch passwordFetch = new PasswordFetch();;
	private IniService ini = new IniService();;
	@PostConstruct
	private void init()
	{
		currentUser = new Operateur();
		
		
	}
	public String getUrlOlap() throws SQLException, InvalidFileFormatException, FileNotFoundException, IOException 
	{
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse(); 
//		response.setHeader("Cache-Control", "no-cache"); // Prevents HTTP 1.1 caching.  
//		response.setHeader("Pragma", "no-cache"); // Prevents HTTP 1.0 cach
//		response.setDateHeader("Expires", -1);
						
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String urlServerSpago = ini.getUrlServerSpago();
		
		String	pswd = passwordFetch.getPassword(authentication.getName());
		
	
		//return "http://localhost:8100/SpagoBISDK/execution.jsp?login=biadmin&password="+pswd;
	  	return ini.getUrlServerBiop()+"KB2I/pages/execution.jsp?login="+authentication.getName()+"&password="+URLEncoder.encode(pswd)+"&urlServerSpago="+urlServerSpago;

	}
	public String getUrlDashAff(String documentId) throws SQLException, InvalidFileFormatException, FileNotFoundException, IOException 
	{

		String urlServerSpago = ini.getUrlServerSpago();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String	pswd = passwordFetch.getPassword(authentication.getName());
		System.out.println(authentication.getName());
	  	System.out.println("***********"+URLEncoder.encode(pswd));
		//return "http://localhost:8100/BioPointV1-KB2I/KB2I/pages/executionDashboard.jsp?documentId="+documentId+"&login="+authentication.getName()+"&password="+URLEncoder.encode(pswd)+"&urlServerSpago="+urlServerSpago;
	    return ini.getUrlServerBiop()+"KB2I/pages/executionDashboard.jsp?documentId="+documentId+"&login="+authentication.getName()+"&password="+URLEncoder.encode(pswd)+"&urlServerSpago="+urlServerSpago;
	
	}

	public String getUrlDash(String documentId) throws SQLException, InvalidFileFormatException, FileNotFoundException, IOException 
	{

		
		String urlServerSpago = ini.getUrlServerSpago();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		
		String	pswd = passwordFetch.getPassword(authentication.getName());
		
		System.out.println(authentication.getName());
	  	System.out.println("***********"+URLEncoder.encode(pswd));
	   return ini.getUrlServerBiop()+"KB2I/pages/executionDashboard.jsp?documentId="+documentId+"&login="+authentication.getName()+"&password="+URLEncoder.encode(pswd)+"&urlServerSpago="+urlServerSpago;
	  	//return "http://localhost:8100/BioPointV1-KB2I/KB2I/pages/executionDashboard.jsp?documentId="+documentId+"&login="+authentication.getName()+"&password="+URLEncoder.encode(pswd)+"&urlServerSpago="+urlServerSpago;
	}
public void registerUserSpago(){
	
//	userSpago.registerUser("kb2i", "biadmin");	
	userSpago.registerUser("kb2i3", "maresca");
			String[] args = {""};
			profileUsr.runJobInTOS(args, "kb2i3");
	
	
	
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
	
	public void testIni() throws InvalidFileFormatException, FileNotFoundException, IOException{
		IniService ini = new IniService();
		ini.getUrl();
		ini.getUrlDW();
		ini.getUrlSpago();
		ini.getUserName();
		ini.getPassword();
		
	}
	
	
	
}
