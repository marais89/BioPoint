package org.bio.web;

import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.time.DateUtils;
import org.bio.model.Conge;
import org.bio.model.HistoriqueCat;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.bio.model.Pointage;
import org.bio.model.PointageId;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.service.CategorieService;
import org.bio.service.FerieService;
import org.bio.service.HistoriqueCatService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.PointageService;
import org.bio.service.SequenceService;
import org.bio.util.PointageEngine;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.*;

@ManagedBean
@ViewScoped
public class ChartView implements Serializable {

	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;

	private CartesianChartModel categoryModel;
	private CartesianChartModel linearModel;

	private PieChartModel pieModel;

	private List<Mvt> listMvt;
	private ArrayList<ArrayList<Mvt>> listMvtSep;
	private ArrayList<HashSet<Personnel>> listPersSep;
	private List<Integer> listItg;
	private List<Personnel> listpersonnel;
	private List<Boolean> listPresence;
	private String dernierDate;

	public ChartView() 
	{	}

	// /////////////////////////////////////////////////
	@PostConstruct
	public void init() {

		listpersonnel = new ArrayList<Personnel>();
		listpersonnel.addAll(personnelService.findAllPersonnels2());
		listItg = new ArrayList<Integer>();
		listMvt = new ArrayList<Mvt>();
		listMvt = getMvtService().findAllMvts();
		listPersSep = new ArrayList<HashSet<Personnel>>();
		listMvtSep = new ArrayList<ArrayList<Mvt>>();
		filtre(listMvt);
		preparMvt();
		if (listPersSep.size() > 0 && listMvtSep.size() > 0) {
			for (int i = 15; i > 0; i--) {
				listItg.add(listPersSep.get(listPersSep.size() - i).size());
			}
			dernierDate = "Présence du jour :  " + listMvtSep.get(listMvtSep.size() - 1).get(0).getJourLogique().toString();
		}
		createPieModel();
		createCategoryModel();
		createLinearModel();
	}
	
	public boolean conpaireDate(Date d)
	{
		Date date = new Date();
		if(d.before(date)){return false;}
		else{return true;}
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	private void createPieModel() {
		pieModel = new PieChartModel();
		pieModel.set("direction", 0);
		pieModel.set("service", 1);
		pieModel.set("equipe", 3);

	}

	public Boolean inList(int id) {
		if (listPersSep.size() > 0) {
			HashSet<Personnel> hs = listPersSep.get(listPersSep.size() - 1);
			for (Personnel p : hs) {
				if (id == p.getIdper()) {
					return true;
				} else {
				}
			}
		}
		return false;
	}

	public void filtre(List<Mvt> l) {
		if (l.size() > 0) {
			for (int i = 0; i < l.size() - 1; i++) {
				for (int j = i + 1; j < l.size(); j++) {
					if (l.get(i).getPersonnel().getIdper() == l.get(j)
							.getPersonnel().getIdper()
							&& (l.get(i).getJourLogique()
									.compareTo(l.get(j).getJourLogique()) == 0)
							&& (l.get(i).getHeure()
									.compareTo(l.get(j).getHeure()) == 0)) {
						l.remove(j);
						j--;
					}
				}
			}
		}
	}

	public void preparMvt() {
		
		if (listMvt.size() == 0) {
			System.out.println("list Mvt est vide");
		} else {
			System.out.println("=>>> preparMvt");
			ArrayList<Mvt> inter = new ArrayList<Mvt>();
			HashSet<Personnel> interSet = new HashSet<>();
			filtre(listMvt);
			// *SEPARATION DES MVT PAR DATE ET PAR PERSONNEL*
			Date d = listMvt.get(0).getJourLogique();
			for (int i = 0; i < listMvt.size(); i++) {
				Mvt m = listMvt.get(i);
				if (m.getJourLogique().compareTo(d) == 0) {
					inter.add(m);
					interSet.add(m.getPersonnel());
				} else {
					listMvtSep.add(inter);
					listPersSep.add(interSet);
					d = listMvt.get(i).getJourLogique();
					inter = new ArrayList<Mvt>();
					interSet = new HashSet<Personnel>();
					inter.add(m);
					interSet.add(m.getPersonnel());
				}
			}
			listMvtSep.add(inter);
			listPersSep.add(interSet);
		}
	}

	// ////////////////////////////////////////////////
	public CartesianChartModel getCategoryModel() {
		System.out.println("getCategoryModel()");
		return categoryModel;
	}

	public CartesianChartModel getLinearModel() {
		System.out.println("getLinearModel()");
		return linearModel;
	}

	private void createCategoryModel() {
		categoryModel = new CartesianChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("presence");
if(listMvtSep.size()>0){
		boys.set(listMvtSep.get(listMvtSep.size() - 14).get(0).getJourLogique()
				.toString(), listItg.get(1));
		boys.set(listMvtSep.get(listMvtSep.size() - 13).get(0).getJourLogique()
				.toString(), listItg.get(2));
		boys.set(listMvtSep.get(listMvtSep.size() - 12).get(0).getJourLogique()
				.toString(), listItg.get(3));
		boys.set(listMvtSep.get(listMvtSep.size() - 11).get(0).getJourLogique()
				.toString(), listItg.get(4));
		boys.set(listMvtSep.get(listMvtSep.size() - 10).get(0).getJourLogique()
				.toString(), listItg.get(5));
		boys.set(listMvtSep.get(listMvtSep.size() - 9).get(0).getJourLogique()
				.toString(), listItg.get(6));
		boys.set(listMvtSep.get(listMvtSep.size() - 8).get(0).getJourLogique()
				.toString(), listItg.get(7));
		boys.set(listMvtSep.get(listMvtSep.size() - 7).get(0).getJourLogique()
				.toString(), listItg.get(8));
		boys.set(listMvtSep.get(listMvtSep.size() - 6).get(0).getJourLogique()
				.toString(), listItg.get(9));
		boys.set(listMvtSep.get(listMvtSep.size() - 5).get(0).getJourLogique()
				.toString(), listItg.get(10));
		boys.set(listMvtSep.get(listMvtSep.size() - 4).get(0).getJourLogique()
				.toString(), listItg.get(11));
		boys.set(listMvtSep.get(listMvtSep.size() - 3).get(0).getJourLogique()
				.toString(), listItg.get(12));
		boys.set(listMvtSep.get(listMvtSep.size() - 2).get(0).getJourLogique()
				.toString(), listItg.get(13));
		boys.set(listMvtSep.get(listMvtSep.size() - 1).get(0).getJourLogique()
				.toString(), listItg.get(14));
}
		ChartSeries girls = new ChartSeries();
		girls.setLabel("absence");
if(listMvtSep.size()>0){
		girls.set(listMvtSep.get(listMvtSep.size() - 14).get(0)
				.getJourLogique().toString(),
				listpersonnel.size() - listItg.get(1));
		girls.set(listMvtSep.get(listMvtSep.size() - 13).get(0)
				.getJourLogique().toString(),
				listpersonnel.size() - listItg.get(2));
		girls.set(listMvtSep.get(listMvtSep.size() - 12).get(0)
				.getJourLogique().toString(),
				listpersonnel.size() - listItg.get(3));
		girls.set(listMvtSep.get(listMvtSep.size() - 11).get(0)
				.getJourLogique().toString(),
				listpersonnel.size() - listItg.get(4));
		girls.set(listMvtSep.get(listMvtSep.size() - 10).get(0)
				.getJourLogique().toString(),
				listpersonnel.size() - listItg.get(5));
		girls.set(listMvtSep.get(listMvtSep.size() - 9).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(6));
		girls.set(listMvtSep.get(listMvtSep.size() - 8).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(7));
		girls.set(listMvtSep.get(listMvtSep.size() - 7).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(8));
		girls.set(listMvtSep.get(listMvtSep.size() - 6).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(9));
		girls.set(listMvtSep.get(listMvtSep.size() - 5).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(10));
		girls.set(listMvtSep.get(listMvtSep.size() - 4).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(11));
		girls.set(listMvtSep.get(listMvtSep.size() - 3).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(12));
		girls.set(listMvtSep.get(listMvtSep.size() - 2).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(13));
		girls.set(listMvtSep.get(listMvtSep.size() - 1).get(0).getJourLogique()
				.toString(), listpersonnel.size() - listItg.get(14));
}
		categoryModel.addSeries(boys);
		categoryModel.addSeries(girls);
	}

	private void createLinearModel() {
		linearModel = new CartesianChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");
		series2.setMarkerStyle("diamond");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		linearModel.addSeries(series1);
		linearModel.addSeries(series2);
	}

	public List<Mvt> getListMvt() {
		return listMvt;
	}

	public void setListMvt(List<Mvt> listMvt) {
		this.listMvt = listMvt;
	}

	public ArrayList<ArrayList<Mvt>> getListMvtSep() {
		return listMvtSep;
	}

	public void setListMvtSep(ArrayList<ArrayList<Mvt>> listMvtSep) {
		this.listMvtSep = listMvtSep;
	}

	public ArrayList<HashSet<Personnel>> getListPersSep() {
		return listPersSep;
	}

	public void setListPersSep(ArrayList<HashSet<Personnel>> listPersSep) {
		this.listPersSep = listPersSep;
	}

	public MvtService getMvtService() {
		return mvtService;
	}

	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}

	public List<Integer> getListItg() {
		return listItg;
	}

	public void setListItg(List<Integer> listItg) {
		this.listItg = listItg;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public List<Personnel> getListpersonnel() {
		return listpersonnel;
	}

	public void setListpersonnel(List<Personnel> listpersonnel) {
		this.listpersonnel = listpersonnel;
	}

	public List<Boolean> getListPresence() {
		return listPresence;
	}

	public void setListPresence(List<Boolean> listPresence) {
		this.listPresence = listPresence;
	}

	public String getDernierDate() {
		return dernierDate;
	}

	public void setDernierDate(String dernierDate) {
		this.dernierDate = dernierDate;
	}

}
