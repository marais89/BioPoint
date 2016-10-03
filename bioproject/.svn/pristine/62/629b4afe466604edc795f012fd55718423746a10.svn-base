package org.bio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.bio.model.Trace;
import org.bio.service.TraceService;

@ManagedBean
@ViewScoped
public class TraceBean implements Serializable {
	
	@ManagedProperty("#{traceServiceImpl}")
	private TraceService traceService;
	private List<Trace> filtredList;
private List<Trace> listTrace;



	public TraceService getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
	}

	public List<Trace> getListTrace() {
		listTrace = new ArrayList<Trace>();
		listTrace.addAll(getTraceService().findAllTraces());
		return listTrace;
	}

	public void setListTrace(List<Trace> listTrace) {
		this.listTrace = listTrace;
	}

	public List<Trace> getFiltredList() {
		return filtredList;
	}

	public void setFiltredList(List<Trace> filtredList) {
		this.filtredList = filtredList;
	}

}
