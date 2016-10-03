package org.bio.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import org.bio.model.Mvt;
import org.bio.service.MvtService;
import org.primefaces.context.RequestContext;

public class ControlMvtThread extends Thread {
	
	// les injections
	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	@ManagedProperty(value = "#{pointageBean2}")
	private PointageBean2 pb2;
	
	//les attributs
	private Timer timer= new Timer();
	private List<Mvt> listMvt= new ArrayList<Mvt>();
	
	
	
	private  TimerTask task = new TimerTask() {
		public void run() {
		System.out.println("timer executer");
			listMvt = new ArrayList<Mvt>();
			listMvt= mvtService.findNewMvt();
			if(listMvt.size()>0)
			{pb2.setFlag1(false);pb2.setFlag2(true);pb2.setFlag3(false);
			RequestContext.getCurrentInstance().update("formheader");
			}
		}

	};
	
	@PostConstruct
	public void init()
	{
		timer.scheduleAtFixedRate(task,3000,1* 1000 * 60);
		start();
	}
	
	
	
	
	
	// GETTERS AND SETTERS 
	public MvtService getMvtService() {
		return mvtService;
	}
	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public List<Mvt> getListMvt() {
		return listMvt;
	}
	public void setListMvt(List<Mvt> listMvt) {
		this.listMvt = listMvt;
	}

}
