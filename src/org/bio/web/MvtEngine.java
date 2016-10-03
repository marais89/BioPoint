package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.time.DateUtils;
import org.bio.model.HistoriqueCat;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.SequenceService;

@ManagedBean
@ViewScoped
public class MvtEngine implements Serializable {
	@ManagedProperty(value = "#{sequenceServiceImpl}")
	private SequenceService sequenceService;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	private List<Personnel> listPersonnel;
private Date checkDate;
private List<Mvt> listMvt;
	@PostConstruct
	private void init()
	{listMvt = new ArrayList<Mvt>();
		listPersonnel = new ArrayList<Personnel>();
		listPersonnel.addAll(getPersonnelService().findAllPersonnels());

	}
	public void checkMvt()
	{
		 for(Personnel p:listPersonnel)
		 {
			 loadMvt(p);
			 findCurrentDayDetail(p);
			 System.out.println(isCorrectMvt(p));
		 }
	}
	public String findEtat(int i,Personnel p)
	{
	int dif =	getdaydiffrence(p.getCurrentDetailDay().getE1(),p.getFindmvt().get(i).getHeure());
	String etat="S";
	if((getdaydiffrence(p.getCurrentDetailDay().getE1(),p.getFindmvt().get(i).getHeure())<dif))
		{
			
	
		}
	return null;
	}
	private int getdaydiffrence(Date d1, Date d2) {

		int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60));
		return Math.abs(diffInDays);
	}
	
	private void findCurrentDayDetail(Personnel p)
	{
		 if(getSeqForpersonnel(p)==1)
		 {
			 if (checkDate.getDay() == 0) {
				p.setCurrentDetailDay(getSequenceService().getjourDetail(p.getCurrentSequence(), 7));
				} else {
					p.setCurrentDetailDay(getSequenceService().getjourDetail(p.getCurrentSequence(), checkDate.getDay()));

					
				}
		 }
		 else  if(getSeqForpersonnel(p)==2)
		 {
		     int seq = (getdiffrence(checkDate,p.getCurrentHisto().getDu()))-(p.getCurrentSequence().getLongueurSequence()-p.getCurrentHisto().getClejour()+1);
		     int resultat = seq%p.getCurrentSequence().getLongueurSequence();
		     if(resultat==0)
		     {						p.setCurrentDetailDay(getSequenceService().getjourDetail(p.getCurrentSequence(), p.getCurrentSequence().getLongueurSequence()));

		     }
		     else {
			     {p.setCurrentDetailDay(getSequenceService().getjourDetail(p.getCurrentSequence(), resultat));

		     }
		 }
		
	 }else{
		 System.out.println("pas de sequence");
	 }
	}
	public int isCorrectMvt(Personnel p)
	{
		if(p.getFindmvt().size()<=1)
		{
			return -1;
		}
		if((p.getFindmvt().get(0).getBcketat().equals("S"))&&(p.getFindmvt().get(0).getEtatp()==null))
		{
			return 0;
		}
		for(int i=0;i<p.getFindmvt().size()-1;i++)
		{
			if(p.getFindmvt().get(i).getEtatp()==null)
			{
			if(p.getFindmvt().get(i).getBcketat().equals(p.getFindmvt().get(i+1).getBcketat()))
			{
				return i+1;
			}
			}
			else if(p.getFindmvt().get(i).getBcketat().equals(p.getFindmvt().get(i+1).getEtatp()))
			{
				return i+1;
			}
		}
		return -1;
	}
	
	public int getSeqForpersonnel(Personnel p)
	{
	Sequence	s;
	s= getSequenceService().getCurrentSequence(p);
	if(s!=null)
	{
p.setCurrentSequence(s);
return 1;
	}

	else {
		HistoriqueCat hc =personnelService.getcurrentHistoriqueCat(p);
	if(hc!=null){
		p.setCurrentHisto(hc);
	p.setCurrentSequence(hc.getSequence());
	return 2;
	}
	}
	return 0;
	}
	private int getDaydiffrence(Date d1, Date d2) {

		int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
		return diffInDays + 1;
	}
private int getdiffrence(Date d1,Date d2) {
		
		int diffInDays = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
		return diffInDays +1;
	}
	private void loadMvt(Personnel p)
	{
		p.setFindmvt(getPersonnelService().getMvtByDay(checkDate, p));
	}
	public SequenceService getSequenceService() {
		return sequenceService;
	}
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
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
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	
}
