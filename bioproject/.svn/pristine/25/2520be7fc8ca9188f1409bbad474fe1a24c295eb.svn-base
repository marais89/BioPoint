package org.bio.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.bio.model.Conge;
import org.bio.model.Personnel;
import org.bio.model.Pointage;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.service.PersonnelService;
import org.bio.service.SequenceService;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

@ManagedBean
@ViewScoped
public class HistMvtBean  implements Serializable {
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	private Date start;
	private Date end;
	private TimeZone timeZone = TimeZone.getTimeZone("Africa/Tunis");
	private List<Personnel> listpersonnel;
	private TimelineModel model;
	@ManagedProperty(value = "#{sequenceServiceImpl}")
	private SequenceService sequenceService;
	@PostConstruct
	private void initi() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Tunis"));
		Date now = new Date();
		now.setHours(8);
		cal.setTimeInMillis(now.getTime() - 2 * 60 * 60 * 1000);
		start = cal.getTime();

		cal.setTimeInMillis(now.getTime() + 11 * 60 * 60 * 1000);
		end = cal.getTime();
    	initialize();
	}

	@SuppressWarnings("deprecation")
	protected void initialize() {
		listpersonnel = new ArrayList<Personnel>();
		listpersonnel.addAll(getPersonnelService().findAllPersonnels());

		// set initial start / end dates for the axis of the timeline

		// create timeline model
		model = new TimelineModel();
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Tunis"));
		for (Personnel p : listpersonnel) {
			Date now = new Date();
			int i = 0;
			int index=1;
			p.setFindmvt(getPersonnelService().getMvtByDay(now, p));
			System.out.println(p.getFindmvt().size());
			now.setDate(now.getDate()+1);
			while (i < p.getFindmvt().size()) {
				// create an event with content, start / end dates, editable
				// flag, group name and custom style class
Date e1 = p.getFindmvt().get(i).getJour();
cal.set(2014, e1.getMonth(), e1.getDate(), p.getFindmvt().get(i).getHeure().getHours(), p.getFindmvt().get(i).getHeure().getMinutes());
e1 = cal.getTime();
Date e2 = new Date();

if(p.getFindmvt().size()>i+1)
{
	e2 = p.getFindmvt().get(i+1).getJour();
	cal.set(2014, e2.getMonth(), e2.getDate(), p.getFindmvt().get(i+1).getHeure().getHours(), p.getFindmvt().get(i+1).getHeure().getMinutes());
	e2 = cal.getTime();
}



				TimelineEvent event = new TimelineEvent("Séance "+(index),e1,e2, false,
						p.getCivilite() + " " + p.getPrenom() + " "
								+ p.getNom(), "available");

				model.add(event);
				i += 2;
				index++;
			}
			loadconge(p);
			
			//sequencePlan(p);
			loadAutorisation(p);
			//loadconge(p);

		}
	}
	@SuppressWarnings("deprecation")
	private void loadconge(Personnel p)
	{
		try{
			Date now = new Date();
			Sequence sq = getSequenceService().getCurrentSequence(p);
			
	
			Conge c= getPersonnelService().findCongeByDay(now, p);
			if(c!=null)
			{			System.out.println("enter 3");
			Date d =new Date(); 
d.setHours(8);
d.setMinutes(0);

Date d2 =new Date(); 
d2.setHours(18);
d2.setMinutes(0);


			TimelineEvent event3 = new TimelineEvent(c.getType(),d,d2, false,
					p.getCivilite() + " " + p.getPrenom() + " "
							+ p.getNom(), "maybe");

			model.add(event3);
			}
		}
		
			catch (Exception e) {
				// TODO: handle exception
			}
	}
	public String convertToFile(Personnel p)
	{if(p.getPhoto()!=null)

		{try{ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String relativeWebPath = "/resources/tmp/";
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
			File file = new File(absoluteDiskPath+"/"+p.getPhotoName());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(p.getPhoto());
			fos.close();
			
		    return "/resources/tmp/"+p.getPhotoName();
	     }catch(Exception e){
	         e.printStackTrace();
	     return "/resources/img/line.jpg";
	     }}
	else  return "/resources/img/line.jpg";
		 }
	
	@SuppressWarnings("deprecation")
	private void loadAutorisation(Personnel p)
	{System.out.println("auto");
		Date now = new Date();
		if(getPersonnelService().findAutorisationByDay(now, p)!=null){
		Conge c  = getPersonnelService().findAutorisationByDay(now, p);
		System.out.println(c.getDebut());
				Date s = new Date();
			s.setHours(0);
			s.setMinutes(0);
			s.setSeconds(0);
			s.setTime(s.getTime()+c.getDebut().getTime()+1000*60*60);
			Date s2=new Date();
			s2.setHours(0);
			s2.setMinutes(0);
			s2.setSeconds(0);
			s2.setTime(s2.getTime()+c.getFin().getTime()+1000*60*60);
	TimelineEvent event3 = new TimelineEvent(c.getType(),s,s2, false,
					p.getCivilite() + " " + p.getPrenom() + " "
							+ p.getNom(), "autorise");

			model.add(event3);}
		}
		
@SuppressWarnings("deprecation")
private void sequencePlan(Personnel p)
{
	try{
	Date now = new Date();
	Sequence sq = getSequenceService().getCurrentSequence(p);
	if(sq!=null){
	SequenceDetail d;
	if (now.getDay() == 0) {
		d = getSequenceService().getjourDetail(sq, 7);
	} else {
		d = getSequenceService().getjourDetail(sq,
				now.getDay());
	}
	Date s = new Date();
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Tunis"));
	cal.set(2014, now.getMonth(), now.getDate(), d.getE1().getHours(), d.getE1().getMinutes());
now = cal.getTime();
cal.set(2014, s.getMonth(), s.getDate(), d.getS1().getHours(), d.getS1().getMinutes());
s= cal.getTime();
	TimelineEvent event = new TimelineEvent("Séance Prévu",now,s, false,
			p.getCivilite() + " " + p.getPrenom() + " "
					+ p.getNom(), "unavailable");
	model.add(event);
	cal.set(2014, now.getMonth(), now.getDate(), d.getE2().getHours(), d.getE2().getMinutes());
now = cal.getTime();
cal.set(2014, s.getMonth(), s.getDate(), d.getS2().getHours(), d.getS2().getMinutes());
s= cal.getTime();
TimelineEvent event2 = new TimelineEvent("Séance Prévu",now,s, false,
			p.getCivilite() + " " + p.getPrenom() + " "
					+ p.getNom(), "unavailable");
	model.add(event2);}}
	catch(Exception e)
	{
	}
	
}
	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public List<Personnel> getListpersonnel() {
		return listpersonnel;
	}

	public void setListpersonnel(List<Personnel> listpersonnel) {
		this.listpersonnel = listpersonnel;
	}

	public TimelineModel getModel() {
		return model;
	}

	public void setModel(TimelineModel model) {
		this.model = model;
	}

	public SequenceService getSequenceService() {
		return sequenceService;
	}

	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

}
