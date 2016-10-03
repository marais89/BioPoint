package org.bio.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.ExportOption;
import org.bio.model.Exporter;
import org.bio.model.Pointage;
import org.bio.service.ExportOptionService;
import org.bio.service.ExporterService;
import org.bio.service.PointageService;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ExportBean implements Serializable {

	private List<ExportOption> listExport;
	private boolean display;
	private Date debut;
	private Date fin;
	private String name;
	private TreeNode selectedNode;
	private String node;
	private Affiliation affiliation;
	private Exporter selectedExporter;
	private List<Exporter> exporters;
	private List<Pointage> generatedlist;
	private Exporter exporter;
	@ManagedProperty(value = "#{exportOptionServiceImpl}")
	private ExportOptionService exportoptionService;
	@ManagedProperty(value = "#{exporterServiceImpl}")
	private ExporterService exporterService;
	@ManagedProperty(value = "#{pointageServiceImpl}")
	private PointageService pointageService;

	public PointageService getPointageService() {
		return pointageService;
	}

	public void setPointageService(PointageService pointageService) {
		this.pointageService = pointageService;
	}

	@PostConstruct
	private void init() {
		listExport = new ArrayList<ExportOption>();
		ExportOption eo = new ExportOption();
		eo.setDisplay(true);
		listExport.add(eo);
		node = "";
		exporter =new Exporter();
		
		generatedlist = new ArrayList<Pointage>();
	}

	public void switchDisplay() {
		display = !display;

	}

	public String formatChar(String s, String s2) {
		String val = "";
		if (ispicked(s)) {
			for (ExportOption e : listExport) {
				if (e.getSelectedfield().equals(s)) {
					val = e.getSelectedFormat();
				}

			}
			if (val.equals("En Majiscule")) {
				s2 = s2.toUpperCase();
			} else if (val.equals("En Miniscule")) {
				s2 = s2.toLowerCase();
			} else if (val.equals("En Capital lettre")) {
				String a = s2.substring(0, 1);
				a = a.toUpperCase();
				s2 = a + s2.substring(1);
			}

		}
		return s2;
	}

	public String formatDate(String s, Date d) {
		String val = "";
		SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
		if (d != null) {
			if (ispicked(s)) {
				for (ExportOption e : listExport) {
					if (e.getSelectedfield().equals(s)) {
						val = e.getSelectedFormat();
					}

				}
				if (val.equals("none")) {
					return formater.format(d);
				}
				if (val.equals("En Minutes")) {
					try {
						return String.valueOf(formater.parse(d.toString())
								.getTime() * 1000 * 60) + " minute(s)";
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (val.equals("En Secondes")) {
					try {
						return String.valueOf(formater.parse(d.toString())
								.getTime() * 1000) + " Seconde(s)";
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (val.equals("En Heures")) {
					try {
						return String.valueOf(formater.parse(d.toString())
								.getTime() * 1000 * 60 * 60) + " Heure(s)";
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (s.equals("Jour")) {
					if (val.equals("none")) {
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy/MM/dd");

						return format.format(d);
					}
					val = val.replace("j", "d");
					val = val.replace("a", "y");
					val = val.replace("m", "M");
					SimpleDateFormat format = new SimpleDateFormat(val);
					return format.format(d);
				} else {
					SimpleDateFormat format = new SimpleDateFormat(val);
					return format.format(d);

				}
			}
		}
		return "";
	}

	public boolean ispicked(String s) {
		for (ExportOption e : listExport) {
			if (e.getSelectedfield().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public void select(NodeSelectEvent e) {
		exporter = new Exporter();
		node = e.getTreeNode().getData().toString();
		exporter.setAffiliation((Affiliation) e.getTreeNode().getData());
		selectedNode = e.getTreeNode();
	}

	public void affiche() {
		exporter = new Exporter();
exporter.setAu(fin);
exporter.setName(name);
exporter.setDu(debut);

	exporter.setAffiliation((Affiliation) selectedNode.getData());

System.out.println(exporter);
		generatedlist = new ArrayList<Pointage>();
		generatedlist = pointageService.findPointages(exporter.getDu(),
				exporter.getAu(), exporter.getAffiliation());
		

	}
public void removerExporter()
{
	try {
		getExporterService().deleteExporter(exporter);
	} catch (Exception e) {
		// TODO: handle exception
	}
}
	public void saveExporter() {
		try {
			getExporterService().insertExporter(exporter);
			for (ExportOption e : listExport) {
				e.setExporter(exporter);
				getExportoptionService().insertExportOption(e);
			}
			FacesMessage msg = new FacesMessage("Export enregistré avec Succés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public List<ExportOption> getListExport() {
		return listExport;
	}

	public void setListExport(List<ExportOption> listExport) {
		this.listExport = listExport;
	}

	public void addoption() {
		System.out.println(exporter);
		ExportOption eo = new ExportOption();
		eo.setDisplay(true);

		listExport.add(eo);
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public void remove(ExportOption eo) {
		listExport.remove(eo);
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void selectExport(SelectEvent event) {
		listExport.clear();
		System.out.println(selectedExporter);
		listExport.addAll(selectedExporter.getListoptions());
		for (int i = 0; i < listExport.size(); i++) {
			listExport.get(i).setDisplay(false);
			listExport.get(i).setDisplay2(false);

		}
		name=selectedExporter.getName();
		debut=selectedExporter.getDu();
		fin=selectedExporter.getAu();
		node = selectedExporter.getAffiliation().getDesignation();
		affiliation=selectedExporter.getAffiliation();
	}

	
	public String getNode() {

		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<Pointage> getGeneratedlist() {
		return generatedlist;
	}

	public void setGeneratedlist(List<Pointage> generatedlist) {
		this.generatedlist = generatedlist;
	}

	public Exporter getExporter() {
		return exporter;
	}

	public void setExporter(Exporter exporter) {
		this.exporter = exporter;
	}

	public ExporterService getExporterService() {
		return exporterService;
	}

	public void setExporterService(ExporterService exporterService) {
		this.exporterService = exporterService;
	}

	public ExportOptionService getExportoptionService() {
		return exportoptionService;
	}

	public void setExportoptionService(ExportOptionService exportoptionService) {
		this.exportoptionService = exportoptionService;
	}

	public List<Exporter> getExporters() {
		exporters = new ArrayList<Exporter>();
		exporters.addAll(getExporterService().findAllExporters());
		return exporters;
	}

	public void setExporters(List<Exporter> exporters) {
		this.exporters = exporters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Exporter getSelectedExporter() {
		return selectedExporter;
	}

	public void setSelectedExporter(Exporter selectedExporter) {
		this.selectedExporter = selectedExporter;
	}
}
