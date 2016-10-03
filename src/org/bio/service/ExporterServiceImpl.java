package org.bio.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.dao.ExporterDao;
import org.bio.model.Affiliation;
import org.bio.model.Exporter;
import org.bio.model.Pointage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExporterServiceImpl implements ExporterService,Serializable{

	
@Autowired
private ExporterDao exporterDao;


	@Override
	@Transactional
	public void insertExporter(Exporter exporter) {
		// TODO Auto-generated method stub
		exporterDao.insertExporter(exporter);
	}

	@Override
	@Transactional
	public void updateExporter(Exporter exporter) {
		// TODO Auto-generated method stub
		exporterDao.updateExporter(exporter);
	}

	@Override
	@Transactional
	public List<Exporter> findAllExporters() {
		// TODO Auto-generated method stub
		return exporterDao.findAllExporters();
	}

	@Override
	public void deleteExporter(Exporter exporter) {
		// TODO Auto-generated method stub
		exporterDao.deleteExporter(exporter);
	}

	@Override
	@Transactional
	public Exporter getByid(int id) {
		// TODO Auto-generated method stub
		return exporterDao.getByid(id);
	}

	public ExporterDao getExporterDao() {
		return exporterDao;
	}

	public void setExporterDao(ExporterDao exporterDao) {
		this.exporterDao = exporterDao;
	}

	@Override
	@Transactional
	public List<Pointage> findPointages(Date d1, Date d2, Affiliation a) {
		// TODO Auto-generated method stub
		return exporterDao.findPointages(d1, d2, a);
	}
	

}
