package org.bio.service;

import java.util.Date;
import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.Exporter;
import org.bio.model.Pointage;

public interface ExporterService {
	public void insertExporter(Exporter exporter);
	public void updateExporter(Exporter exporter);
	public List<Exporter> findAllExporters();
	public void deleteExporter(Exporter exporter);
	public Exporter getByid(int  id);
	public List<Pointage> findPointages(Date d1,Date d2,Affiliation a);

}
