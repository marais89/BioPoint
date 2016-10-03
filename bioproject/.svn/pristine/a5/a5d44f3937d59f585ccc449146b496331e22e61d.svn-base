package org.bio.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.dao.OperateurDao;
import org.bio.dao.TraceDao;
import org.bio.model.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TraceServiceImpl implements TraceService, Serializable {
@Autowired
	private TraceDao traceDao;

	public TraceDao getTraceDao() {
	return traceDao;
}

public void setTraceDao(TraceDao traceDao) {
	this.traceDao = traceDao;
}
@Autowired
private OperateurDao operateurDao;
	@Override
	@Transactional
	public void insertTrace(Trace trace) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
trace.setOperateur(operateurDao.getByLogin(auth.getName()));
		trace.setDateHeure(new Date());
		traceDao.insertTrace(trace);
	}

	@Override
	@Transactional
	public void updateTrace(Trace trace) {
	traceDao.updateTrace(trace);

	}

	@Override
	@Transactional
	public List<Trace> findAllTraces() {
		
		return traceDao.findAllTraces();
	}

	@Override
	@Transactional
	public void deleteTrace(Trace trace) {
	traceDao.deleteTrace(trace);

	}

	@Override
	@Transactional
	public Trace getByid(int id) {
		
		return traceDao.getByid(id);
	}

	public OperateurDao getOperateurDao() {
		return operateurDao;
	}

	public void setOperateurDao(OperateurDao operateurDao) {
		this.operateurDao = operateurDao;
	}

}
