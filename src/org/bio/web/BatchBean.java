package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.bio.model.Batch;
import org.bio.service.BatchService;
import org.bio.util.PointageEngine;
import org.primefaces.context.RequestContext;
import org.primefaces.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
@ManagedBean
@SessionScoped
public class BatchBean implements Serializable {
	@ManagedProperty(value="#{batchServiceImpl}")
	private BatchService batchService;
	private boolean flag;
	private List<Batch> listBatch;
	@ManagedProperty(value="#{pointageEngineImpl}")
	private PointageEngine pointageEngine;
	private Integer progress;
	@PostConstruct
	private void init()
	{flag=true;
		listBatch = new ArrayList<Batch>();
	}
	
	public void updateList()
	{
		listBatch=getBatchService().findAllBatch(0);
	}
	public void start()
	{
		System.out.println("batch executer");
		listBatch=getBatchService().findAllBatch(0);
		if(listBatch.size()>0)
		{
		if(!flag)
	{
			System.out.println("1");
		RequestContext.getCurrentInstance().execute("PF('pbatch').cancel()");

	}
		System.out.println("2");
		RequestContext.getCurrentInstance().execute("PF('pbatch').start()");
		flag=false;
		for(Batch b:listBatch)
		{System.out.println("3+");
			pointageEngine.initializePointage(b.getPersonnel(),b.getJour(), b.getJour());
			System.out.println("3++");
			b.setEtat(3);
			System.out.println("3+++");
			System.out.println(b);
			System.out.println(b.getEtat());
			System.out.println(b.getPersonnel().getNom());
			getBatchService().updateBatch(b);
			System.out.println("3+++-");
		}
		}
		System.out.println("4");
		RequestContext.getCurrentInstance().execute("switchnotImg()");

	}
	public BatchService getBatchService() {
		return batchService;
	}
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}
	public List<Batch> getListBatch() {
		return listBatch;
	}
	public void setListBatch(List<Batch> listBatch) {
		this.listBatch = listBatch;
	}
	public PointageEngine getPointageEngine() {
		return pointageEngine;
	}
	public void setPointageEngine(PointageEngine pointageEngine) {
		this.pointageEngine = pointageEngine;
	}

	public Integer getProgress() {
		  if(progress == null) {
	            progress = 0;
	        }
	        else {
	            progress = progress + (int)(Math.random() * 35);
	             
	            if(progress > 100)
	                progress = 100;
	        }
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

}
