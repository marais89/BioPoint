package org.bio.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.bio.model.LostMvt;
import org.bio.model.LostMvtId;
import org.bio.model.Mvt;
import org.bio.model.MvtId;
import org.bio.model.Personnel;
import org.bio.model.Terminaux;
import org.bio.service.LostMvtService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.TerminauxService;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.util.SecurityUtils;

@ManagedBean
@ViewScoped
public class ImportBean implements Serializable{
	
	
	private String path;
	private List<Mvt> listmvt;
	@ManagedProperty(value = ("#{terminauxServiceImpl}"))
	private TerminauxService terminauxService;
	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	@ManagedProperty(value = "#{lostMvtServiceImpl}")
	private LostMvtService lostMvtService;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	private Integer idter;
	private List<Terminaux> listTerminaux;
	private Terminaux terminaux;
	private String url;
	private List<String> listFiles;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private Map<String, Integer> listdisplayTerminaux;

	@PostConstruct
	private void init()
	{
		listmvt=new ArrayList<Mvt>();
		listTerminaux = new ArrayList<Terminaux>();
		listTerminaux=getTerminauxService().findPointageTerminaux();
		listFiles = new ArrayList<String>();
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ectx = context.getExternalContext();
	url = ectx.getRequestScheme()
		  + "://" + Faces.evaluateExpressionGet("#{contextBean.listparam.get('nomdomaine')}")
		  + ":" + ectx.getRequestServerPort()
		  + ectx.getRequestContextPath();
	listdisplayTerminaux =new HashMap<String, Integer>();
	for (Terminaux t : listTerminaux) {
		listdisplayTerminaux.put(t.getLibelle() + " " + t.getType(), t.getIdter());
	}
	}
	
	public void uplodatMvt()
	{try{ 
		terminaux=getTerminauxService().getByid(idter);
		System.out.println("enter1");
listmvt.clear();
		connect();
		System.out.println("enter2");

		String str="";
		System.out.println("enter3");

		out.println(terminaux.getPlatform());
		str = in.readLine();
		System.out.println(str);
		if(str.equals("True"))
		{System.out.println("enter");
		for(String file:listFiles)
		{System.out.println("e1");
			out.println("parseFile "+url+file);
			System.out.println("parseFile "+url+file);
			str = in.readLine();
			System.out.println(str);
			if (str.equals("startAttendances")) {
				//listmvt.clear();
				str = in.readLine();
				while (!str.equals("endData")) {
				listmvt.add(convertToMvt(str));
					System.out.println(str);
					str = in.readLine();
				}
correctMvt();
			}
		}
		}
		out.println("fermer");
		client.close();
		FacesMessage msg = new FacesMessage("Fichier importé avec succés");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}catch(Exception e){
		FacesMessage msg = new FacesMessage("Fichier Invalid");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        }
	}

	private void correctMvt() {
		if (listmvt.size() > 1) {
			Calendar cal = Calendar.getInstance(TimeZone
					.getTimeZone("Africa/Tunis"));
			for (Mvt m : listmvt) {
				cal.setTime(m.getJour());
				m.setJourLogique(cal.getTime());
				Date jl = new Date();
				if (m.getHeure().getHours() <= 6) {
					jl.setTime(cal.getTimeInMillis() - 1000 * 60 * 60 * 24);
					m.setJourLogique(jl);
				}
				if (m.getId().getIdper() != -1) {
					getMvtService().insertMvt(m);
				} else {
					LostMvt lmvt = new LostMvt();
					LostMvtId idm = new LostMvtId();
					idm.setBckId(m.getBckId());
					idm.setHeure(m.getHeure());
					lmvt.setTerminaux(terminaux);
					lmvt.setMode(m.getMode());
					lmvt.setMoyen(m.getMoyen());
					lmvt.setBcketat(m.getBcketat());
					idm.setJour(m.getJour());
					lmvt.setJourLogique(m.getJourLogique());
					lmvt.setId(idm);
					getLostMvtService().insertLostMvt(lmvt);
				}
			}
		}
	}
	public void connect() {
		try {

			client = new Socket(terminaux.getServerIp(),
					Integer.valueOf(terminaux.getServerPort()));

			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream())), true);
		} catch (Exception e) {
			System.out.println("Erreur");

		}

	}
public void remove(String s)
{System.out.println(s);
	listFiles.remove(s);
}

	public void handleFileUpload(FileUploadEvent event) {
	    try {
	    	
	    	path=event.getFile().getFileName();
	    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

	    	String relativeWebPath = "/resources/tmp/";
	    	ServletContext servletContext = (ServletContext) externalContext.getContext();
	    	String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
	        File targetFolder = new File(absoluteDiskPath);
	        InputStream inputStream = event.getFile().getInputstream();
	        OutputStream out = new FileOutputStream(new File(targetFolder,
	                event.getFile().getFileName()));
	    	path=relativeWebPath+event.getFile().getFileName();

	        int read = 0;
	        byte[] bytes = new byte[1024];

	        while ((read = inputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        inputStream.close();
	        out.flush();
	        out.close();
	        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	   	 System.out.println(path);
	   	listFiles.add(path);

	    } catch (IOException e) {
	    	 System.out.println(e.getMessage());
	    }
	}
	private Mvt convertToMvt(String data) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Mvt m = new Mvt();
		MvtId id = new MvtId();
		m.setMode("Terminal");

		StringTokenizer st = new StringTokenizer(data, ";");
		int bckid = Integer.valueOf(st.nextToken());
		Personnel p = getPersonnelService().getPersonnelByEnrollID(bckid,
				terminaux);
		m.setPersonnel(p);
		if (p != null) {
			id.setIdper(p.getIdper());
		} else {
			id.setIdper(-1);
		}
		id.setIdter(terminaux.getIdter());
		m.setBckId(bckid);
		m.setId(id);
		String str = st.nextToken();
		if (str.equalsIgnoreCase("0"))
			m.setMoyen("Mot de pass");
		else if (str.equalsIgnoreCase("1"))
			m.setMoyen("Empreinte");
		else
			m.setMoyen("Carte électronique");
		switch (Integer.valueOf(st.nextToken()))
		{
		case 0:m.setBcketat("E");
		case 1:m.setBcketat("S");
		case 2:m.setBcketat("S");
		case 3:m.setBcketat("S");
		case 4:m.setBcketat("E");
		case 5:m.setBcketat("E");
		}
		Date d = formatter.parse(st.nextToken());
		m.setTerminaux(terminaux);
		m.setJour(d);
		m.setHeure(d);
		return m;

	}
	public TerminauxService getTerminauxService() {
		return terminauxService;
	}
	public void setTerminauxService(TerminauxService terminauxService) {
		this.terminauxService = terminauxService;
	}
	public MvtService getMvtService() {
		return mvtService;
	}
	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}
	public LostMvtService getLostMvtService() {
		return lostMvtService;
	}
	public void setLostMvtService(LostMvtService lostMvtService) {
		this.lostMvtService = lostMvtService;
	}
	public PersonnelService getPersonnelService() {
		return personnelService;
	}
	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}
	public List<Terminaux> getListTerminaux() {
		return listTerminaux;
	}
	public void setListTerminaux(List<Terminaux> listTerminaux) {
		this.listTerminaux = listTerminaux;
	}
	public Terminaux getTerminaux() {
		return terminaux;
	}
	public void setTerminaux(Terminaux terminaux) {
		this.terminaux = terminaux;
	}

	public Map<String, Integer> getListdisplayTerminaux() {
		return listdisplayTerminaux;
	}

	public void setListdisplayTerminaux(Map<String, Integer> listdisplayTerminaux) {
		this.listdisplayTerminaux = listdisplayTerminaux;
	}

	public Integer getIdter() {
		return idter;
	}

	public void setIdter(Integer idter) {
		this.idter = idter;
	}

	public List<String> getListFiles() {
		return listFiles;
	}

	public void setListFiles(List<String> listFiles) {
		this.listFiles = listFiles;
	}
}
