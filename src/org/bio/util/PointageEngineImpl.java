package org.bio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;
import org.bio.model.Conge;
import org.bio.model.HistoriqueCat;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.bio.model.Pointage;
import org.bio.model.PointageId;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.service.FerieService;
import org.bio.service.HistoriqueCatService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.PointageService;
import org.bio.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointageEngineImpl implements PointageEngine {

@Autowired
private FerieService ferieService;
private Date startDate;
	private Date endDate;
	@Autowired	private PointageService pointageService;
	@Autowired	private PersonnelService personnelService;
	@Autowired	private MvtService mvtService;
	@Autowired	private SequenceService sequenceService;
	
	private List<Personnel> listPersonnel;
	private List<Mvt> listMvt;
	@Autowired	private HistoriqueCatService historiquecatservice;
	private List<HistoriqueCat> listHistorique;
	private Pointage pointage;
	
private List<Pointage> listpointage;
	@PostConstruct
	public void init() {
		listHistorique = new ArrayList<HistoriqueCat>();
		listHistorique.addAll(getHistoriquecatservice().findAllHistoriqueCats());
		listPersonnel = new ArrayList<Personnel>();
		listpointage=new ArrayList<Pointage>();														// all
																			// personnel
	}
@Override
	public void initializePointage() {

		listMvt = new ArrayList<Mvt>();		
		try{
			
			getPointageService().deletePointage(startDate,endDate);
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Tunis"));
		while (!startDate.after(endDate)) {
			listMvt.addAll(getMvtService().findMvtByDates(startDate, startDate)); 
			cal.setTime(startDate);

			for (Personnel p : listPersonnel) {
				pointage = new Pointage();
				pointage.setPersonnel(p);
				findMvtforPersonnel(p);
				pointage.setId(new PointageId(cal.getTime(),p.getIdper()));

				loadDetailsSequence(p, startDate);
				System.out.println(p);
				if (p.getCurrentListDetails() != null) {
					System.out.println(p.getCurrentListDetails().size());
				}
				loadPointage(p, startDate);
				checkforDepedencies();
				defineAutorisation(p);

				loadRetard();
				retardTotal();
				loadHoursTowork();
				hoursworked();
				Supplyhours();
				isAbsent();

				System.out.println(pointage);
				listpointage.add(pointage);
//getPointageService().insertPointage(pointage);
System.out.println("succés"+listpointage.size());

			}
			startDate.setDate(startDate.getDate() + 1);

		}}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		saveAll();

	}

	// find all mvt for personnel from listmvt
	private void findMvtforPersonnel(Personnel p) {
		System.out.println("loading Mvt for " + p.getPrenom());
		List<Mvt> listtmpMvt = new ArrayList<Mvt>();
		for (Mvt m : listMvt) {
			if ((p.getIdper() == m.getPersonnel().getIdper())) {
				listtmpMvt.add(m);
			}
		}
		p.setFindmvt(listtmpMvt);
		System.out.println(p.getFindmvt().size());
		System.out.println("loading completed");

	}

	// find detail sequence for Personnel
	private void loadDetailsSequence(Personnel p, Date start) {
		System.out.println("loading sequence for " + p.getNom());

		Sequence s = getSequenceService().getcurrentSequencebyDay(start, p);
		System.out.println("step 1");

		if (s != null) {
			p.setCurrentListDetails(getSequenceService().findAllDayDetail(s));
			p.setCurrentSequence(s);
		} else {
			System.out.println("step 2");

			findcurrentSequenceHist(p, start);
			if (p.getCurrentHisto() != null) {
				System.out.println("step 3");

				s = p.getCurrentHisto().getSequence();
				p.setCurrentSequence(s);
				p.setCurrentListDetails(getSequenceService()
						.findAllDayDetail(s));
				System.out.println("step 4");

			}
		}
		System.out.println("loading completed");

	}

	// search for current historique with sequence
	private void findcurrentSequenceHist(Personnel p, Date start) {

		System.out.println("loading sequence by day for " + p.getPrenom());
		System.out.println("while");
		for (HistoriqueCat hc : listHistorique) {
			System.out.println("for");

			if ((hc.getPersonnel().getIdper() == p.getIdper())
					&& ((hc.getDu().before(start)) || (DateUtils.isSameDay(
							hc.getDu(), start)))
					&& ((hc.getAu().after(start)) || (DateUtils.isSameDay(
							hc.getAu(), start))) && (hc.getSequence() != null)) {
				System.out.println("if");

				p.setCurrentHisto(hc);
				return;
			}
			System.out.println("endfor");

		}

		System.out.println("loading completed");

	}

	private void loadPointage(Personnel p, Date start) {
		System.out.println("loading pointage by day for " + p.getPrenom());
		System.out.println("step 1");

		if ((p.getCurrentSequence() != null)
				&& (p.getCurrentListDetails().size() > 0)) {
			System.out.println("step 2");

			if (p.getCurrentSequence().getTypeSeq().equalsIgnoreCase("semaine")) {
				System.out.println("step 3");
				SequenceDetail d = findSequenceDetail(p, start);
				pointage.setSequenceDetail(d);
				System.out.println(pointage.getSequenceDetail());

				if (d != null) {
					System.out.println("step 4");
					if(d.getNbSeance()>0)
					{
					if (d.getE1() != null) {
						pointage.setE1(FindE1(p, d));
					}
					if (d.getS1() != null) {
						pointage.setS1(FindS1(p, d));
					}
					if (d.getNbSeance() == 2) {
						if (d.getE2() != null)

						{
							pointage.setE2(FindE2(p, d));
						}
						if (d.getS1() != null) {

							pointage.setS2(FindS2(p, d));
						}

					}}
					System.out.println(d);

				}

			} else {
				System.out.println("step 5");

				findMvtforPersonnel(p);
				SequenceDetail d = findDaySequenceDetail(p, start);
				pointage.setSequenceDetail(d);
				if (d != null) {
					if(d.getNbSeance()>0)
					{
					System.out.println("step 4");
					if (d.getE1() != null) {
						pointage.setE1(FindE1(p, d));
					}
					if (d.getS1() != null) {
						pointage.setS1(FindS1(p, d));
					}
					if (d.getNbSeance() == 2) {
						if (d.getE2() != null)

						{
							pointage.setE2(FindE2(p, d));
						}
						if (d.getS2() != null) {

							pointage.setS2(FindS2(p, d));
						}

					}
					}
				}

			}

		}
		System.out.println("loading completed");

	}

	private SequenceDetail findSequenceDetail(Personnel p, Date start) {
		System.out.println("loading sequence detail");

		int x = start.getDay();
		if (start.getDay() == 0) {
			x = 7;
		}
		pointage.setJour(x);
		for (SequenceDetail sd : p.getCurrentListDetails()) {
			if (sd.getJour() == x) {
				return sd;
			}
		}

		System.out.println("loading completed");
		return null;
	}

	private SequenceDetail findDaySequenceDetail(Personnel p, Date start) {
		int seq = (getDaydiffrence(start, p.getCurrentHisto().getDu()))
				- (p.getCurrentSequence().getLongueurSequence()
						- p.getCurrentHisto().getClejour() + 1);
		int resultat = seq % p.getCurrentSequence().getLongueurSequence();
		if (resultat == 0) {
			resultat = p.getCurrentSequence().getLongueurSequence();
		}
		pointage.setJour(resultat);
		for (SequenceDetail sd : p.getCurrentListDetails()) {
			if (sd.getJour() == resultat) {
				return sd;
			}
		}

		System.out.println("loading completed");
		return null;
	}

	private int getDaydiffrence(Date d1, Date d2) {

		int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
		return diffInDays + 1;
	}

	@SuppressWarnings("deprecation")
	public Date FindE1(Personnel p, SequenceDetail d) {
		System.out.println("loading e1");
		System.out.println("E1 ==== " + d.getE1());
		int dif = 500000000;
		Mvt mvt = null;
		Date tmp = new Date();

		 tmp = d.getE1();
		tmp.setHours(tmp.getHours() - 1);
		System.out.println("E1 ==== " + d.getE1());
		System.out.println("tmp 1 ==== " + tmp);

		List<Mvt> listToscan = getmvtbyDate(p, tmp, d.getS1());
		if(listToscan.size()==0)
		{
			return null;
		}
		System.out.println("listtoscan  "+listToscan.size());

		for (Mvt m :listToscan) {
			if ((getdiffrence(m.getHeure(), tmp) < dif)&&(m.getHeure().compareTo(d.getS1())!=0)) {
				dif = getdiffrence(m.getHeure(), tmp);
				mvt = m;
			}
		}
		d.getE1().setHours(d.getE1().getHours()+1);
		return mvt.getHeure();

	}
	@SuppressWarnings("deprecation")
	public Date FindE2(Personnel p, SequenceDetail d) {
		System.out.println("loading e2");
Calendar c = Calendar.getInstance();
Calendar c2 = Calendar.getInstance();

		int dif = 50000000;
		Mvt mvt = null;
		c.setTime(d.getE2());
		c2.setTime(d.getE2());
		c2.setTimeInMillis(c2.getTimeInMillis()-(30*60*1000));
		c.setTimeInMillis(c.getTimeInMillis()+60*60*1000);
		

		List<Mvt> listToscan = getmvtbyDate(p, c2.getTime(), c.getTime());
		if(listToscan.size()==0)
		{	return null;  }
		System.out.println("list to scan size " + listToscan.size());
		if (d.getE2().before(d.getS1())) {
			System.out.println("step 1");

			for (Mvt m : listToscan) {
				System.out.println("step 2");
				System.out.println(getdiffrence(c2.getTime(), m.getHeure()));

				if ((getdiffrence(c2.getTime(), m.getHeure()) < dif)
						&& (m.getHeure().compareTo(d.getS2()) != 0)) {
					dif = getdiffrence(c2.getTime(), m.getHeure());
					mvt = m;
				}
			}
		} else {
			System.out.println("E2 ==== " + d.getE2());

			for (Mvt m : listToscan) {
				if ((getdiffrence(m.getHeure(), c2.getTime()) < dif)
						&& (!m.getHeure().after(d.getS2()))) {
					dif = getdiffrence(m.getHeure(), c2.getTime());
					mvt = m;
				}
			}
		}
		System.out.println("E2===="+mvt.getHeure());
		return mvt.getHeure();

	}
	@SuppressWarnings("deprecation")
	public Date FindS1(Personnel p, SequenceDetail d) {
		System.out.println("loading s1");

		int dif = 1000000;
		Mvt mvt = null;
		Date tmp = d.getS1();
		tmp.setHours(tmp.getHours() - 1);
		Calendar c = Calendar.getInstance();
		System.out.println("s1 ==== " + d.getS1());
		List<Mvt> listToscan ;
		if(d.getE2()!=null){
listToscan = getmvtbyDate(p, tmp, d.getE2());
		}
		else{
			c.setTime(d.getS1());
			c.setTimeInMillis(c.getTimeInMillis()+3*60*1000*60);
	 listToscan = getmvtbyDate(p, tmp, c.getTime());

		}
		if(listToscan.size()==0)
		{
			return null;
		}
		System.out.println("list to scan size" + listToscan.size());
		if (d.getS1().before(d.getE1())) {
			System.out.println("step 1");

			for (Mvt m : listToscan) {
				System.out.println("step 2");
				if ((getdiffrence(tmp, m.getHeure()) < dif)&&(m.getHeure().compareTo(d.getE2())!=0)) {
					dif = getdiffrence(tmp, m.getHeure());
					mvt = m;
				}
			}
		} else {
			System.out.println("step 3");
			for (Mvt m : listToscan) {

				if ((getdiffrence(d.getS1(), m.getHeure()) < dif)) {
					dif = getdiffrence(d.getS1(), m.getHeure());
					mvt = m;
				}
			}
		}
		
		return mvt.getHeure();

	}
	@SuppressWarnings("deprecation")
	public Date FindS2(Personnel p, SequenceDetail d) {
		System.out.println("loading s2");

		int dif = 1000000;
		Mvt mvt = null;
		Date tmp = d.getS2();
		tmp.setHours(d.getS2().getHours() + 3);
		System.out.println("s1 ==== " + d.getS2());
		List<Mvt> listToscan = getmvtbyDate(p,d.getE2(), tmp);
		if(listToscan.size()==0)
		{
			return null;
		}
		for (Mvt m : listToscan) {
			if ((getdiffrence(d.getS2(), m.getHeure()) < dif)
					&& (m.getHeure().before(tmp))) {
				dif = getdiffrence(d.getS2(), m.getHeure());
				mvt = m;
			}
		}

		d.getS2().setHours(d.getS2().getHours() - 3);
		System.out.println("temp =>>>>>>>>>>>>>>>>" + tmp);
		System.out.println(mvt.getHeure());
		return mvt.getHeure();

	}
	public List<Mvt> getmvtbyDate(Personnel p, Date deb, Date fin) {
		List<Mvt> listfilter = new ArrayList<Mvt>();
		for (Mvt m : p.getFindmvt()) {
			System.out.println("debut "+deb+" fin "+fin);

System.out.println("Mvt ===>"+m.getHeure());

			if ((m.getHeure().compareTo(deb) == 0)
					|| (m.getHeure().compareTo(fin) == 0)
					|| ((m.getHeure().after(deb) && (m.getHeure().before(fin))))) {
				System.out.println("day application "+startDate+" day mvt"+m.getJourLogique());
				if(DateUtils.isSameDay(startDate,m.getJourLogique()))
				listfilter.add(m);
			}
		}
		return listfilter;
	}

	private int getdiffrence(Date d1, Date d2) {

		int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60));
		return Math.abs(diffInDays);
	}

	public void checkforDepedencies() {
		Conge c = getPersonnelService().findCongeByDay(
				pointage.getId().getJour(), pointage.getPersonnel());
		if (c != null) {
			pointage.setConge(true);
		} else {
			pointage.setConge(false);
		}
		
			pointage.setAutorisation(getPersonnelService().findAutorisationByDay(pointage.getId().getJour(),pointage.getPersonnel()));
		
		if (getFerieService().isFerie(pointage.getId().getJour())) {
			pointage.setFerie(true);
		}
	}

	public void loadRetard() {
		System.out.println(pointage.getSequenceDetail());
		if (pointage.getSequenceDetail() != null) {
			if ((pointage.getSequenceDetail().getE1() != null)&&(pointage.getE1() != null)) {
				Date dif = new Date();
				if (pointage.getSequenceDetail().getE1()
						.before(pointage.getE1())) {
					System.out.println(pointage.getSequenceDetail().getE1()
							+ "  pointé  " + pointage.getE1());
					dif.setTime((pointage.getE1().getTime()
							- pointage.getSequenceDetail().getE1().getTime() - 1000 * 60 * 60));
					System.out.println("resultat" + dif);
					pointage.setRetard1(dif);
				}
			}
			if ((pointage.getSequenceDetail().getE2() != null)&&(pointage.getE2() != null)) {
				Date dif2 = new Date();
				if (pointage.getSequenceDetail().getE2()
						.before(pointage.getE2())) {
					System.out.println(pointage.getSequenceDetail().getE2()
							+ "  pointé  " + pointage.getE2());

					dif2.setTime(pointage.getE2().getTime()
							- pointage.getSequenceDetail().getE2().getTime()
							- 1000 * 60 * 60);
					System.out.println("resultat" + dif2);
					pointage.setRetard2(dif2);
				}
			}

		}
	}
	public void hoursworked() throws Exception {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		Date dif = f.parse("00:00");
		Date dif2 = f.parse("00:00");
boolean present = false;

			if ((pointage.getE1()!=null)
					&& (pointage.getS1()!=null)) {
				if (pointage.getE1()
						.after(pointage.getS1())) {
					dif.setTime(pointage.getS1().getTime()
							- pointage.getE1().getTime()
							+ (24 * 1000 * 60*60));
					present = true;
				} else {
					dif.setTime(pointage.getS1().getTime()
							- pointage.getE1().getTime());
					present = true;

				}
				System.out.println("------------- "+dif);

			}
			if ((pointage.getE2()!=null)
				&& (pointage.getS2()!=null)) {
				if (pointage.getE2()
						.after(pointage.getS2())) {
					dif2.setTime(pointage.getS2().getTime()
							- pointage.getE2().getTime()
							+ (24 * 1000 * 60*60));
					present = true;

				} else {
					dif2.setTime(pointage.getS2().getTime()
							- pointage.getE2().getTime()-(60*60*1000));
					present = true;

				}
				System.out.println("------------- "+dif2);
			}
			if(present){
Date hourstowork = new Date();
hourstowork.setTime(dif.getTime()+dif2.getTime());
pointage.setPresenceHrMn(hourstowork);
		}
	}

	
	public void loadHoursTowork() throws Exception {
		if (pointage.getSequenceDetail() != null) {
			SimpleDateFormat f = new SimpleDateFormat("HH:mm");
			Date dif = f.parse("00:00");
			Date dif2 = f.parse("00:00");

			if ((pointage.getSequenceDetail().getE1() != null)
					&& (pointage.getSequenceDetail().getS1() != null)) {
				if (pointage.getSequenceDetail().getE1()
						.after(pointage.getSequenceDetail().getS1())) {
					dif.setTime(pointage.getSequenceDetail().getS1().getTime()
							- pointage.getSequenceDetail().getE1().getTime()
							+ (24 * 1000 * 60*60));
				} else {
					dif.setTime(pointage.getSequenceDetail().getS1().getTime()
							- pointage.getSequenceDetail().getE1().getTime()+60*60*1000);

				}
				System.out.println("------------- "+dif);

			}
			if ((pointage.getSequenceDetail().getE2() != null)
					&& (pointage.getSequenceDetail().getS2() != null)) {
				if (pointage.getSequenceDetail().getE2()
						.after(pointage.getSequenceDetail().getS2())) {
					dif2.setTime(pointage.getSequenceDetail().getS2().getTime()
							- pointage.getSequenceDetail().getE2().getTime()
							+ (24 * 1000 * 60*60));
				} else {
					dif2.setTime(pointage.getSequenceDetail().getS2().getTime()
							- pointage.getSequenceDetail().getE2().getTime()-60*60*1000);

				}
				System.out.println("------------- "+dif2);
			}
Date hourstowork = new Date();
hourstowork.setTime(dif.getTime()+dif2.getTime());
pointage.setBudgHrMn(hourstowork);
		}

	}

	private void Supplyhours() {
		if (pointage.getSequenceDetail() != null) {
			if((pointage.getPresenceHrMn()!=null)&&(pointage.getBudgHrMn()!=null))
			{
			Date dif = new Date();

			if (pointage.getPresenceHrMn().after(pointage.getBudgHrMn()))
				 {
			dif.setTime(pointage.getPresenceHrMn().getTime()-pointage.getBudgHrMn().getTime()-(60*60*1000));
			pointage.setHsupHrMn(dif);
		}
		}}
	}
private void retardTotal()
{
if((pointage.getBudgHrMn()!=null)&&(pointage.getPresenceHrMn()!=null))
{
	if(pointage.getBudgHrMn().after(pointage.getPresenceHrMn()))
	{	Date retard = new Date();

		retard.setTime(pointage.getBudgHrMn().getTime()-pointage.getPresenceHrMn().getTime());
		pointage.setRetardHrMn(retard);
	}
}

}
private void isAbsent()
{
	
	if(pointage.getSequenceDetail()!=null){
if((pointage.getSequenceDetail().getNbSeance()!=0))	
{
	if((!pointage.getFerie())&&(!pointage.getConge()))
	{
		if((pointage.getE1()==null)&& (pointage.getS1()==null))
		pointage.setAbsent(true);
	}
}
else if((pointage.getSequenceDetail().getNbSeance()==0))	
{
	 pointage.setAbsent(false);
}}
	else pointage.setAnomalie(true);
	

}
private void defineAutorisation(Personnel p)
{System.out.println("enter autorisation");
	if((pointage.getAutorisation()!=null)&&(!pointage.getAbsent())&&(pointage.getSequenceDetail().getNbSeance()!=0))
	{		System.out.println("enter0");

		System.out.println(pointage.getSequenceDetail().getE1().getTime()+" compare to "+pointage.getAutorisation().getDebut().getTime());
		//System.out.println("SA"+FindSA(p));
		//System.out.println("RA"+FindRA(p));
//pointage.setSa(FindSA(p));
//pointage.setRa(FindRA(p));
	if(pointage.getSequenceDetail().getE1().getTime()==pointage.getAutorisation().getDebut().getTime())
	{
		System.out.println("------------ok--------------");
		pointage.setRa(FindRA(p));
		pointage.setE1(pointage.getRa());
		pointage.setSa(pointage.getAutorisation().getDebut());

	}
	else  if(pointage.getSequenceDetail().getS1().compareTo(pointage.getAutorisation().getFin())==0)
	{
		pointage.setS1(pointage.getSa());
	}
	else  if(pointage.getSequenceDetail().getS2().compareTo(pointage.getAutorisation().getFin())==0)
	{
		pointage.setS2(pointage.getSa());
	}
	else  if(pointage.getSequenceDetail().getE2().compareTo(pointage.getAutorisation().getDebut())==0)
	{
		pointage.setE2(pointage.getRa());
	}
	}
	}


private Date FindSA(Personnel p)
{System.out.println("looking for auto");
	int dif = 1000000;
	Mvt mvt = null;
Date tmp =new Date();
tmp.setTime(pointage.getAutorisation().getDebut().getTime()-60*60*1000);
System.out.println("enter0");
	List<Mvt> listToscan = getmvtbyDate(p,tmp,pointage.getAutorisation().getFin());
	System.out.println("list size"+listToscan.size());
	if(listToscan.size()==0)
	{
		return null;
	}
	if (pointage.getAutorisation().getFin().before(pointage.getAutorisation().getDebut())) {
		System.out.println("step 1");

		for (Mvt m : listToscan) {
			System.out.println("step 2");
			System.out.println(getdiffrence(tmp, m.getHeure()));

			if ((getdiffrence(tmp, m.getHeure()) < dif)) {
				dif = getdiffrence(tmp, m.getHeure());
				mvt = m;
			}
		}
	} else {
	for (Mvt m : listToscan) {
		if ((getdiffrence(m.getHeure(),tmp) < dif)) {
			dif = getdiffrence(m.getHeure(),tmp);
			mvt = m;
		}
	}
	}
	tmp.setTime(pointage.getAutorisation().getDebut().getTime()+60*60*1000);

	System.out.println("temp =>>>>>>>>>>>>>>>>" + tmp);
	if(mvt==null) return null;
	return mvt.getHeure();
}
private Date FindRA(Personnel p)
{System.out.println("looking for auto");
	int dif = 1000000;
	Mvt mvt = null;
	Date tmp2 = new Date();

	Date tmp = new Date();
	tmp.setTime(pointage.getAutorisation().getFin().getTime()-60*60*1000);
	tmp2.setTime(pointage.getAutorisation().getFin().getTime()+60*60*1000);
System.out.println("i1 "+tmp+" i2 "+tmp2);
	System.out.println("enter0");
	List<Mvt> listToscan = getmvtbyDate(p,tmp,tmp2);
	System.out.println("list size"+listToscan.size());
	if(listToscan.size()==0)
	{
		return null;
	}
	if (pointage.getAutorisation().getFin().before(pointage.getAutorisation().getDebut())) {
		System.out.println("step 1");

		for (Mvt m : listToscan) {
			System.out.println("step 2");
			System.out.println(getdiffrence(tmp, m.getHeure()));

			if ((getdiffrence(tmp, m.getHeure()) < dif)) {
				dif = getdiffrence(tmp, m.getHeure());
				mvt = m;
			}
		}
	} else {
	for (Mvt m : listToscan) {
		if ((getdiffrence(m.getHeure(),tmp) < dif)) {
			dif = getdiffrence(tmp, m.getHeure());
			mvt = m;
		}
	}
	}
	pointage.getAutorisation().getDebut().setHours(pointage.getAutorisation().getDebut().getHours() -1);
	System.out.println("temp =>>>>>>>>>>>>>>>>" + tmp);
	if(mvt==null) return null;
	return mvt.getHeure();
}

private void saveAll()
{
	try{
	for(Pointage po:listpointage)
	{
		getPointageService().insertPointage(po);
	}
	}
	catch(Exception e)
	{
		e.getStackTrace();
	}
}
	@SuppressWarnings("deprecation")
	private Date addTimetoDate(Date jour,Date hour) {
		jour.setHours(hour.getHours());
		jour.setMinutes(hour.getMinutes());
		jour.setSeconds(0);

return jour;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PointageService getPointageService() {
		return pointageService;
	}

	public void setPointageService(PointageService pointageService) {
		this.pointageService = pointageService;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public MvtService getMvtService() {
		return mvtService;
	}

	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}

	public SequenceService getSequenceService() {
		return sequenceService;
	}

	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	public List<Personnel> getListPersonnel() {
		return listPersonnel;
	}

	public void setListPersonnel(List<Personnel> listPersonnel) {
		this.listPersonnel = listPersonnel;
	}

	public List<Mvt> getListMvt() {
		return listMvt;
	}

	public void setListMvt(List<Mvt> listMvt) {
		this.listMvt = listMvt;
	}

	public HistoriqueCatService getHistoriquecatservice() {
		return historiquecatservice;
	}

	public void setHistoriquecatservice(
			HistoriqueCatService historiquecatservice) {
		this.historiquecatservice = historiquecatservice;
	}

	public List<HistoriqueCat> getListHistorique() {
		return listHistorique;
	}

	public void setListHistorique(List<HistoriqueCat> listHistorique) {
		this.listHistorique = listHistorique;
	}

	public Pointage getPointage() {
		return pointage;
	}

	public void setPointage(Pointage pointage) {
		this.pointage = pointage;
	}

	public FerieService getFerieService() {
		return ferieService;
	}

	public void setFerieService(FerieService ferieService) {
		this.ferieService = ferieService;
	}
	
	@Override
	public void initializePointage(Personnel p, Date start, Date end) {
		// TODO Auto-generated method stub
		System.out.println("Begin");
		listMvt = new ArrayList<Mvt>();

		listMvt.addAll(getMvtService().findMvtByDates(start, end)); // load
								startDate=start;
								endDate=end;// all
																			// mvt
																			// by
																			// interval
		
		try{
			getPointageService().deletePointage(start, end);
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Tunis"));
		while (!start.after(end)) {
			System.out.println("++++++++++++++++++++++++++++++" + startDate
					+ "++++++++++++++++++++++++++++++");
			cal.setTime(start);


				pointage = new Pointage();
				pointage.setPersonnel(p);
				findMvtforPersonnel(p);
				pointage.setId(new PointageId(cal.getTime(),p.getIdper()));

				loadDetailsSequence(p, start);
				System.out.println(p);
				if (p.getCurrentListDetails() != null) {
					System.out.println(p.getCurrentListDetails().size());
				}
				loadPointage(p, start);
				checkforDepedencies();
				//defineAutorisation(p);

				loadRetard();
				retardTotal();
				loadHoursTowork();
				hoursworked();
				Supplyhours();
				isAbsent();

				System.out.println(pointage);
				listpointage.add(pointage);
//getPointageService().insertPointage(pointage);
System.out.println("succés"+listpointage.size());

		
			start.setDate(start.getDate() + 1);

		}}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		saveAll();

	}

}