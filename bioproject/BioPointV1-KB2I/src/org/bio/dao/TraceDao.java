package org.bio.dao;

import java.util.List;

import org.bio.model.Trace;

public interface TraceDao {
	public void insertTrace(Trace trace);
	public void updateTrace(Trace trace);
	public List<Trace> findAllTraces();
	public void deleteTrace(Trace trace);
	public Trace getByid(int  id);
}
