package org.bio.jobs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.bio.model.LostMvt;
import org.bio.model.LostMvtId;
import org.bio.model.Mvt;
import org.bio.model.MvtId;
import org.bio.model.Personnel;
import org.bio.model.Terminaux;
import org.bio.service.LostMvtService;
import org.bio.service.MvtService;
import org.bio.service.ParametreService;
import org.bio.service.PersonnelService;
import org.bio.service.TerminauxService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@WebServlet(loadOnStartup=2)
public class ServletInitializer extends HttpServlet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int initialDelay = 1000; // start after 30 seconds
	private int period = 30000; // repeat every 5 seconds
	private Timer timer = new Timer();
	private TerminauxService terminauxService;
	private List<Terminaux> selectedTerminaux;
	private Terminaux terminaux;
	private PersonnelService personnelService;
	private List<Mvt> listmvt;
	private LostMvtService lostMvtService;
	private MvtService mvtService;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	private  TimerTask task = new TimerTask() {

		public void run() {
			// job code here

			System.out.println(new Date());
			//loadMvt();
		}

	};

	public void init() throws ServletException {
listmvt=new ArrayList<Mvt>();
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/application-context.xml");
		ParametreService service = (ParametreService) context
				.getBean("parametreServiceImpl");
		terminauxService = (TerminauxService) context
				.getBean("terminauxServiceImpl");
		selectedTerminaux = terminauxService.findAllActiveTerminaux();
		personnelService=  (PersonnelService) context
				.getBean("personnelServiceImpl");
		mvtService=  (MvtService) context
				.getBean("mvtServiceImpl");
		lostMvtService=  (LostMvtService) context
				.getBean("lostMvtServiceImpl");
		timer.scheduleAtFixedRate(
				task,
				3000,
				Integer.valueOf(service.byId("freqlecture").getValueparam()) * 1000 * 60);

	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void destroy() {
		timer.cancel();
	}

	public void connectPointer() {
		if (!terminaux.isConnected()) {

			try {
				terminaux.getOut().println(terminaux.getPlatform());
				System.out.println(terminaux.getPlatform());
				if (terminaux.getIn().readLine().equalsIgnoreCase("true")) {
					System.out.println("enter1");
				
					System.out.println("enter2");

					String ip = "127.0.0.1";
					System.out.println("connect " + terminaux.getIp() + " "
									+ terminaux.getPort() + " "
									+ terminaux.getPassword() + " "
									+ terminaux.getDeviceId() + " " + ip);
					System.out.println("enter3");

					out.println(
							"connect " + terminaux.getIp() + " "
									+ terminaux.getPort() + " "
									+ terminaux.getPassword() + " "
									+ terminaux.getDeviceId() + " " + ip);
					System.out.println("enter4");

					String str = terminaux.getIn().readLine();

					if (str.equalsIgnoreCase("true")) {

						terminaux.setConnected(true);
						System.out.println("enter5");

						// RequestContext.getCurrentInstance().execute("diagpointer.show()");

					} else if (str.equalsIgnoreCase("false")) {

						System.out.println("enter6");
						terminaux.setConnected(false);

					} else {
						System.out.println("enter7");

						terminaux.setConnected(false);

					}

				} 

			} catch (Exception e) {
System.out.println(e.getMessage());
			}
		}

	}

	public void loadMvt() {
		System.out.println("enter");
		String str = "";

		try {

			for (Terminaux t : selectedTerminaux) {
				terminaux = t;
				connect();
				connectPointer();
				if (terminaux.isConnected()) {
					terminaux.getOut().println("getAttendances False");
					str = terminaux.getIn().readLine();
					if (str.equals("startAttendances")) {
						listmvt.clear();
						str = terminaux.getIn().readLine();
						while (!str.equals("endData")) {

							listmvt.add(convertToMvt(str));
							str = terminaux.getIn().readLine();

						}

						correctMvt();
					}

					disconnectAll();
					terminaux.setConnected(false);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			disconnectAll();
			terminaux.setConnected(false);		}

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
	private Mvt convertToMvt(String data) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Mvt m = new Mvt();
		MvtId id = new MvtId();
		m.setMode("Terminal");

		StringTokenizer st = new StringTokenizer(data, ";");
		int bckid = Integer.valueOf(st.nextToken());
		Personnel p =personnelService.getPersonnelByEnrollID(bckid,
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
		System.out.println(p);
		String str = st.nextToken();
		if (str.equalsIgnoreCase("0"))
			m.setMoyen("Mot de pass");
		else if (str.equalsIgnoreCase("1"))
			m.setMoyen("Empreinte");
		else
			m.setMoyen("Carte électronique");
		switch (Integer.valueOf(st.nextToken())) {
		case 0:
			m.setBcketat("E");
		case 1:
			m.setBcketat("S");
		case 2:
			m.setBcketat("S");
		case 3:
			m.setBcketat("S");
		case 4:
			m.setBcketat("E");
		case 5:
			m.setBcketat("E");
		}
		Date d = formatter.parse(st.nextToken());
		m.setTerminaux(terminaux);
		m.setJour(d);
		m.setHeure(d);
		return m;

	}

	public void connect() {
		try {

			client = new Socket(terminaux.getServerIp(),
					Integer.valueOf(terminaux.getServerPort()));

			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream())), true);
			terminaux.setClient(client);
			terminaux.setIn(in);
			terminaux.setOut(out);

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
					mvtService.insertMvt(m);
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
					lostMvtService.insertLostMvt(lmvt);
				}
			}
		}
	}

}
