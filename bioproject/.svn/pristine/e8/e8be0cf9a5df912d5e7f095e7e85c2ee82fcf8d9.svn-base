package org.bio.web;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bio.model.Identification;
import org.bio.model.IdentificationId;
import org.bio.model.LostMvt;
import org.bio.model.LostMvtId;
import org.bio.model.Mvt;
import org.bio.model.MvtId;
import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.Terminaux;
import org.bio.service.IdentificationService;
import org.bio.service.LostMvtService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.PersonnelTerminalService;
import org.bio.service.TerminauxService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

@ManagedBean
@ViewScoped
public class ConsoleBean implements Serializable {
	@ManagedProperty(value = ("#{terminauxServiceImpl}"))
	private TerminauxService terminauxService;
	@ManagedProperty(value = "#{personnelTerminalServiceImpl}")
	private PersonnelTerminalService personnelTerminalService;

	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	@ManagedProperty(value = "#{lostMvtServiceImpl}")
	private LostMvtService lostMvtService;
	
	@ManagedProperty(value = "#{PointageBean2}")
	private PointageBean2 pointageBean2;
	
	private List<Terminaux> listactif;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private Terminaux terminaux;
	private String msg;
	private List<Mvt> listmvt;
	private String date;
	private List<Terminaux> selectedTerminaux;
	private List<Terminaux> connectedTerminaux;
	private List<Terminaux> disconnectedterminaux;

	@ManagedProperty("#{identificationServiceImpl}")
	private IdentificationService identificationService;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	private boolean connected;
	private Integer progress;
	private String status;

	@PostConstruct
	private void init() {
		listmvt = new ArrayList<Mvt>();
		listactif = new ArrayList<Terminaux>();
		listactif.addAll(getTerminauxService().findAllActiveTerminaux());
		connected = false;
		connectedTerminaux = new ArrayList<Terminaux>();
		HttpSession ses = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		selectedTerminaux = new ArrayList<Terminaux>();
		disconnectedterminaux = new ArrayList<Terminaux>();
	}

	public void connectPointer() {
		if (!terminaux.isConnected()) {
			RequestContext.getCurrentInstance().execute(
					"PF('" + terminaux.getIdter() + "').start()");
			try {
				terminaux.setProgress(2);
				terminaux.getOut().println(terminaux.getPlatform());
				System.out.println(terminaux.getPlatform());
				progress = 30;

				if (terminaux.getIn().readLine().equalsIgnoreCase("true")) {
					HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
							.getCurrentInstance().getExternalContext()
							.getRequest();
					String ip = httpServletRequest.getRemoteAddr();
					out.println("connect " + terminaux.getIp() + " "
							+ terminaux.getPort() + " "
							+ terminaux.getPassword() + " "
							+ terminaux.getDeviceId() + " " + ip);
					terminaux.setProgress(10);
					System.out.println(("connect " + terminaux.getIp() + " "
							+ terminaux.getPort() + " "
							+ terminaux.getPassword() + " "
							+ terminaux.getDeviceId() + " " + ip));
					String str = terminaux.getIn().readLine();
					terminaux.setProgress(20);

					if (str.equalsIgnoreCase("true")) {
						msg = "Opération en cours....";
						terminaux.setEtat(msg);
						connectedTerminaux.add(terminaux);
						terminaux.setConnected(true);
						terminaux.setProgress(25);

						// RequestContext.getCurrentInstance().execute("diagpointer.show()");

					} else if (str.equalsIgnoreCase("false")) {
						msg = "Impossible de connecté";
						terminaux.setEtat(msg);
						terminaux.setProgress(100);

					} else {
						msg = "Terminal occupé par " + str;
						terminaux.setEtat(msg);
						terminaux.setProgress(100);

					}

					RequestContext.getCurrentInstance().update("form:ters");
					System.out.println(str);
				} else
					msg = "Marque non Supporté";
				terminaux.setEtat(msg);
				terminaux.setProgress(100);
				RequestContext.getCurrentInstance().update("form:ters");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

	}
	
public void correctDate()
{
	try{
	selectedTerminaux = disconnectedterminaux;
	for (Terminaux t : selectedTerminaux) {
		terminaux = t;
		connect();
		connectPointer();
		if (terminaux.isConnected()) {
			SimpleDateFormat f=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			terminaux.getOut().println("setDatePc");
			if(terminaux.getIn().readLine().equalsIgnoreCase("True"))
			{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					null, f.format(new Date()));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext.getCurrentInstance().update("form:msg");
			}
			disconnectAll();
			t.setConnected(false);
			terminaux.setConnected(false);
			terminaux.setEtat("Opération terminé");
			RequestContext.getCurrentInstance().update(":form:ters");
		}
	}
	}
	catch(Exception e)
	{
		
	}
}
	public void selectConsole(SelectEvent e) {
		disconnectedterminaux.add((Terminaux) e.getObject());
		System.out.println(disconnectedterminaux.size());
	}

	public void UnselectConsole(UnselectEvent e) {
		disconnectedterminaux.remove((Terminaux) e.getObject());
		System.out.println(disconnectedterminaux.size());

	}

	public void loadMvt() {

		String str = "";

		try {
			//selectedTerminaux = disconnectedterminaux;
			//	terminauxService.truncateAll();
			for (Terminaux t : selectedTerminaux) {
				terminaux = t;
				connect();				
				connectPointer();
				
				if (terminaux.isConnected()) {
					terminaux.getOut().println("getAttendances True");
					System.out.println("initialisation str : "+str);
					str = terminaux.getIn().readLine();
					System.out.println("*-*-"+str);
					if (str.equals("startAttendances")) {
						//listmvt.clear();
						listmvt= new ArrayList<>();
						str = terminaux.getIn().readLine();
						System.out.println("++ avant chargement pointage");
						System.out.println(str);
						while (!str.equals("endData")) {
						//	System.out.println("++ not end of data");
						//	terminaux.setProgress(terminaux.getProgress() + 2);
							listmvt.add(convertToMvt(str));
						
							System.out.println(str);
							str = terminaux.getIn().readLine();

						}
						correctMvt();
					}
					disconnectAll();
					t.setConnected(false);
					terminaux.setProgress(100);
					terminaux.setConnected(false);
					terminaux.setEtat("Opération terminé");
					RequestContext.getCurrentInstance().update(":form:ters");
					RequestContext.getCurrentInstance().update(":formheader:loadpointage");
				}
				
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					null, listmvt.size() + " pointages été teléchargé");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext.getCurrentInstance().update("form:msg");
			
			selectedTerminaux= new ArrayList<Terminaux>();
//			pointageBean2.detectMvt();
//			RequestContext.getCurrentInstance().update("formheader:loadpointage formheader:gif1 formheader:gif2 formheader:gif3");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

	}

	public void synchronize() {
		selectedTerminaux = disconnectedterminaux;
		try {
			for (Terminaux t : selectedTerminaux) {
				terminaux = t;
				connect();
				connectPointer();
				if (terminaux.isConnected()) {
					List<PersonnelTerminal> listtoupdate = getPersonnelService()
							.listtoupdate(terminaux);
					System.out.println("list to update " + listtoupdate.size());
					for (PersonnelTerminal pt : listtoupdate) {

						List<Identification> list = getPersonnelService()
								.findidentification(pt.getPersonnel());
						System.out.println(list.size());
						for (Identification i : list) {
							System.out.println("enter 1");
							String str = pt.getBckId() + ";"
									+ pt.getTerminaux().getDeviceId() + ";"
									+ pt.getPersonnel().getNom() + ";"
									+ i.getId().getIndex() + ";0;"
									+ i.getValeurBlob() + ";0;True;0";
							terminaux.getOut().println("setEnrollData");
							terminaux.getOut().println(str);
							terminaux.getOut().println("endData");
							String rep = terminaux.getIn().readLine();
							System.out.println("reponse " + rep);
							if (rep.equals("True")) {
								pt.setUploaded(true);
								getPersonnelTerminalService()
										.updatePersonnelTerminal(pt);

							}

						}
						synchronizeCarte(pt);
					}
					List<PersonnelTerminal> listtodelete = getPersonnelService()
							.listtodelete(terminaux);
					for (PersonnelTerminal pt : listtodelete) {
						terminaux.getOut().println(
								"deleteUser " + pt.getBckId());

					}

					disconnectAll();
					t.setConnected(false);
					terminaux.setConnected(false);
					terminaux.setEtat("Opération terminé");
					RequestContext.getCurrentInstance().update(":form:ters");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void synchronizeCarte(PersonnelTerminal pt) {
		Identification idn = getPersonnelService().findCarteidentification(
				pt.getPersonnel());
		if (idn != null) {
			terminaux.getOut().println("setCardNumber");

			terminaux.getOut().println(
					pt.getBckId() + ";" + pt.getTerminaux().getDeviceId() + ";"
							+ pt.getPersonnel().getPrenom() + ";0;;"
							+ idn.getValeurText() + ";True");

			terminaux.getOut().println("endData");

		}
	}

	public void connectAll() {
		for (Terminaux t : selectedTerminaux) {
			terminaux = t;
			connectPointer();
		}
	}

	public void disconnectAll() {
		if (terminaux.isConnected()) {
			try {
				terminaux.getOut().println("exit");
				terminaux.getOut().println("fermer");
				if (terminaux.getIn().readLine().equals("fermer")) {
					terminaux.getClient().close();
					System.out.println("succés");
				}
				out.println("fermer");
				System.out.println(in.readLine());
				client.close();
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}

	}

	public void updateCartes(Personnel p) {
		try {
			System.out.println("enter");

			terminaux.getOut().println("getCardNumber " + p.getBckIdEnroll());
			String str = terminaux.getIn().readLine();
			System.out.println(str);
			StringTokenizer st = new StringTokenizer(str, ";");
			st.nextToken();
			st.nextToken();
			st.nextToken();
			Identification iden = new Identification();
			IdentificationId id = new IdentificationId(p.getIdper(), 1, "Carte");
			iden.setId(id);
			iden.setValeurText(st.nextToken());
			iden.setPersonnel(p);
			getIdentificationService().insertIdentification(iden);
			System.out.println("succé");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void updateEmpInfo(String data, Personnel p) {
		StringTokenizer st = new StringTokenizer(data, ";");

		if (p != null) {
			st.nextToken();
			st.nextToken();
			IdentificationId id = new IdentificationId();
			Identification iden = new Identification();
			id.setIndex(Integer.valueOf(st.nextToken()));
			st.nextToken();

			iden.setValeurBlob(st.nextToken());
			System.out.println(iden.getValeurBlob());
			iden.setPersonnel(p);
			id.setIdper(p.getIdper());
			id.setType("Emp");
			iden.setId(id);
			getIdentificationService().insertIdentification(iden);
		}
	}

	public void updateFaceInfo(String data) {
		StringTokenizer st = new StringTokenizer(data, ";");
		Personnel p = getPersonnelService().getPersonnelByEnrollID(
				Integer.valueOf(st.nextToken()), terminaux);
		if (p != null) {
			p.setBckNom(st.nextToken());

			IdentificationId id = new IdentificationId();
			Identification iden = new Identification();
			iden.setValeurText(st.nextToken());
			st.nextToken();
			// iden.setValeurBlob(st.nextToken().getBytes());
			iden.setPersonnel(p);
			id.setIdper(p.getIdper());
			id.setType("Face");
			id.setIndex(1);
			iden.setId(id);
			getIdentificationService().insertIdentification(iden);
			getPersonnelService().updatePersonnel(p);
		}
	}

	public void loadIdentifaction() {
		try {
			List<Personnel> listpersonnel = getPersonnelService()
					.PersonnelswithoutEmpreinte();
			selectedTerminaux = disconnectedterminaux;
			for (Terminaux t : selectedTerminaux) {
				terminaux = t;
				connect();
				connectPointer();
				if (terminaux.isConnected()) {
					for (Personnel p : listpersonnel) {
						System.out.println(p.getTerminaux().getIdter()
								+ "compare to" + terminaux.getIdter());
						if (p.getTerminaux().getIdter() == terminaux.getIdter()) {
							updateCartes(p);
							terminaux.getOut().println(
									"getEnrollData " + p.getBckIdEnroll() + " "
											+ terminaux.getDeviceId() + " -1");

							System.out.println("getEnrollData "
									+ p.getBckIdEnroll() + " "
									+ t.getDeviceId() + " -1");
							String str = terminaux.getIn().readLine();
							if (str.equals("startEnrollData")) {
								getTerminauxService().truncateAll();
								while (!str.equals("endData")) {

									str = terminaux.getIn().readLine();
									System.out.println(str);
									if (!str.equals("endData")) {
										updateEmpInfo(str, p);
									}
								}
							}
						}
					}
					disconnectAll();
					t.setConnected(false);
					terminaux.setEtat("Opération terminé");
					RequestContext.getCurrentInstance().update(":form:ters");
					terminaux.setConnected(false);
				}
			}
		} catch (Exception e) {

		}

	}

	private Mvt convertToMvt(String data) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Mvt m = new Mvt();
		MvtId id = new MvtId();
		m.setMode("Terminal");
		
		
		String[] tok = data.split(";");
		int bckid =(int) Integer.valueOf(tok[0]);
		m.setBckId(bckid);
		System.out.println("****************************");
		
		System.out.println("bckid :"+bckid+" Terminaux :"+terminaux.getIdter());
		System.out.println("****************************");
		
		Personnel p = getPersonnelService().getPersonnelByEnrollID(bckid,terminaux);
		m.setPersonnel(p);
		System.out.println("////////////////////////////////////////////");
		
//		switch (tok[1])
//		{
//		case "0":m.setMoyen("all");
//		case "1":m.setMoyen("Empreinte");
//		case "2":m.setMoyen("Mot_de_passe");
//		case "3":m.setMoyen("Carte electronique");
//		}
		
		String str = tok[1];
		if (str.equalsIgnoreCase("0"))
			m.setMoyen("Mot de pass");
		else if (str.equalsIgnoreCase("1"))
			m.setMoyen("Empreinte");
		else
			m.setMoyen("Carte électronique");
		
		switch (tok[2])
		{
		case "0":m.setBcketat("E");
		case "1":m.setBcketat("S");
		case "2":m.setBcketat("S");
		case "3":m.setBcketat("S");
		case "4":m.setBcketat("E");
		case "5":m.setBcketat("E");
		}
		
		Date d = formatter.parse(tok[3]);
		m.setTerminaux(terminaux);
		m.setJour(d);
		m.setHeure(d);
		m.setEtat("new");		
		
		// **remplir id terminal
		if (p != null) {
			id.setIdper(p.getIdper());
		} else {
			id.setIdper(-1);
		}
		id.setIdter(terminaux.getIdter());
		m.setId(id);
		
//		StringTokenizer st = new StringTokenizer(data, ";");
//		int bckid = Integer.valueOf(st.nextToken());
//		Personnel p = getPersonnelService().getPersonnelByEnrollID(bckid,
//				terminaux);
//		m.setPersonnel(p);
//		if (p != null) {
//			id.setIdper(p.getIdper());
//		} else {
//			id.setIdper(-1);
//		}
//		id.setIdter(terminaux.getIdter());
//		m.setBckId(bckid);
//		m.setId(id);
//		//System.out.println(p);
//		String str = st.nextToken();
//		if (str.equalsIgnoreCase("0"))
//			m.setMoyen("Mot de pass");
//		else if (str.equalsIgnoreCase("1"))
//			m.setMoyen("Empreinte");
//		else
//			m.setMoyen("Carte électronique");
//		switch (Integer.valueOf(st.nextToken()))
//		{
//		case 0:m.setBcketat("E");
//		case 1:m.setBcketat("S");
//		case 2:m.setBcketat("S");
//		case 3:m.setBcketat("S");
//		case 4:m.setBcketat("E");
//		case 5:m.setBcketat("E");
//		}
//		Date d = formatter.parse(st.nextToken());
//		m.setTerminaux(terminaux);
//		m.setJour(d);
//		m.setHeure(d);
//		m.setEtat("new");
		
		
		
		return m;

	}

	public String status(String ip) {

		String resultat = "";
		try {

			final InetAddress target = InetAddress.getByName(ip);
			if (target != null) {
				// final boolean ping = target.isReachable(3000);
				resultat = true ? "En ligne" : "Déconnecté";
			} else
				resultat = "Déconnecté";
		} catch (Exception e) {
			System.err.println(" Erreur : " + e);
			e.printStackTrace();

		}
		return resultat;
	}

	public void connect() {
		try {

			client = new Socket(terminaux.getServerIp(),
					Integer.valueOf(terminaux.getServerPort()));

			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			progress = 15;
			status = "Etablisement de channel de transmission";
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream())), true);
			progress = 25;
			status = "Intialisation de client";
			terminaux.setClient(client);
			terminaux.setIn(in);
			terminaux.setOut(out);

			System.out.println("succés");

		} catch (Exception e) {
			System.out.println("Erreur");

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
				//	List<Mvt> listMvtTest= new ArrayList<>();
				//	boolean bol= false;
				//	listMvtTest.addAll(mvtService.findByIdOk(m.getId().getIdper(), m.getJour()));
				//	if(listMvtTest.size()>0)
				//	{for(int i=0;i<listMvtTest.size();i++)
				//		{if(listMvtTest.get(i).getHeure()==m.getHeure()){bol=true;}}}
					
				//	if(bol!=true)
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

	public void onComplete() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Connexion terminé", ""));
	}

	public void cancel() {
		progress = null;
	}

	public TerminauxService getTerminauxService() {
		return terminauxService;
	}

	public void setTerminauxService(TerminauxService terminauxService) {
		this.terminauxService = terminauxService;
	}

	public List<Terminaux> getListactif() {

		return listactif;
	}

	public void setListactif(List<Terminaux> listactif) {
		this.listactif = listactif;
	}

	public Terminaux getTerminaux() {
		return terminaux;
	}

	public void setTerminaux(Terminaux terminaux) {
		this.terminaux = terminaux;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MvtService getMvtService() {
		return mvtService;
	}

	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public IdentificationService getIdentificationService() {
		return identificationService;
	}

	public void setIdentificationService(
			IdentificationService identificationService) {
		this.identificationService = identificationService;
	}

	public List<Mvt> getListmvt() {
		return listmvt;
	}

	public void setListmvt(List<Mvt> listmvt) {
		this.listmvt = listmvt;
	}

	public LostMvtService getLostMvtService() {
		return lostMvtService;
	}

	public void setLostMvtService(LostMvtService lostMvtService) {
		this.lostMvtService = lostMvtService;
	}

	public Integer getProgress() {

		if (progress == null)
			progress = 0;
		else if (terminaux.isConnected()) {

			progress = 80;
			progress = 100;

		}

		else {
			progress = progress + (int) (Math.random() * 35);

			if (progress > 100)
				progress = 100;
		}
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PersonnelTerminalService getPersonnelTerminalService() {
		return personnelTerminalService;
	}

	public void setPersonnelTerminalService(
			PersonnelTerminalService personnelTerminalService) {
		this.personnelTerminalService = personnelTerminalService;
	}

	public List<Terminaux> getSelectedTerminaux() {
		return selectedTerminaux;
	}

	public void setSelectedTerminaux(List<Terminaux> selectedTerminaux) {
		this.selectedTerminaux = selectedTerminaux;
	}

	public List<Terminaux> getDisconnectedterminaux() {
		return disconnectedterminaux;
	}

	public void setDisconnectedterminaux(List<Terminaux> disconnectedterminaux) {
		this.disconnectedterminaux = disconnectedterminaux;
	}

	public PointageBean2 getPointageBean2() {
		return pointageBean2;
	}

	public void setPointageBean2(PointageBean2 pointageBean2) {
		this.pointageBean2 = pointageBean2;
	}
	

}
