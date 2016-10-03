package org.bio.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.validation.constraints.AssertTrue;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;












//import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.bio.model.Affiliation;
import org.bio.model.Document;
import org.bio.model.Historique;
import org.bio.model.HistoriqueCat;
import org.bio.model.LostMvt;
import org.bio.model.Mvt;
import org.bio.model.MvtId;
import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.PersonnelTerminalId;
import org.bio.model.Sequence;
import org.bio.model.Terminaux;
import org.bio.model.Trace;
import org.bio.service.AffiliationService;
import org.bio.service.CategorieService;
import org.bio.service.DocumentService;
import org.bio.service.HistoriqueCatService;
import org.bio.service.HistoriqueService;
import org.bio.service.LostMvtService;
import org.bio.service.MvtService;
import org.bio.service.PersonnelService;
import org.bio.service.PersonnelTerminalService;
import org.bio.service.SequenceService;
import org.bio.service.TerminauxService;
import org.bio.service.TraceService;
import org.omnifaces.util.Faces;
import org.primefaces.component.terminal.Terminal;
import org.primefaces.component.toolbar.ToolbarGroup;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.springframework.dao.DataAccessException;

@ManagedBean
@SessionScoped
public class PersonnelBean implements Serializable {
	@ManagedProperty(value = "#{documentServiceImpl}")
	private DocumentService documentService;
	@ManagedProperty(value = "#{lostMvtServiceImpl}")
	private LostMvtService lostMvtService;
	@ManagedProperty(value = "#{mvtServiceImpl}")
	private MvtService mvtService;
	private Personnel personnel;
	@ManagedProperty(value = "#{personnelServiceImpl}")
	private PersonnelService personnelService;
	@ManagedProperty(value = "#{sequenceServiceImpl}")
	private SequenceService sequenceService;
	private List<Personnel> listpersonnel;
	private Personnel selectedPersonnel;
	private List<Personnel> filtredlistPersonnel;
	private List<Personnel> selectedpersonnels;
	@ManagedProperty(value = "#{categorieServiceImpl}")
	private CategorieService categorieservice;
	@ManagedProperty(value = "#{historiqueServiceImpl}")
	private HistoriqueService historiqueservice;
	@ManagedProperty(value = "#{historiqueCatServiceImpl}")
	private HistoriqueCatService historiquecatservice;
	private Integer idaffeliation;
	@ManagedProperty(value = ("#{terminauxServiceImpl}"))
	private TerminauxService terminauxService;
	private Integer idCategorie;
	@ManagedProperty(value = "#{affiliationServiceImpl}")
	private AffiliationService affiliationService;
	@ManagedProperty("#{traceServiceImpl}")
	private TraceService traceService;
	@ManagedProperty(value = ("#{personnelTerminalServiceImpl}"))
	private PersonnelTerminalService personnelTerminalService;
	private Map<String, Integer> listTerminaux;
	private int idTeminaux;
	private boolean flag;
	private boolean cache;
	private File photo;
	private int sizeliste;
	private TreeNode selectedNode;
	private String node;
	private List<Terminaux> listPTerminaux;
	private List<Terminaux> selectedlistPTerminaux;
	private List<Terminaux> listeTerminauxenroll;
	private List<Personnel> listPersonnel2;
	private boolean action;
	private List<Document> listdocumentsUpdate;
	private List<Document> listdocuments;
	private Document document;
	private String key;
	private DefaultStreamedContent file;
    private DualListModel<Terminaux> listTerminals ;
    private String strCategorie;
    private int indexTab;
    private Terminaux termEnrol;
    private String msgAffiche;
   // private Wizard wiz= new Wizard(); 
    private List<LostMvt> listeLostMvt;
    private String msgLostMvt="";
    private boolean skip;
    private boolean flagLost = false;

	@PostConstruct
	private void init() {
		listPersonnel2= new ArrayList<>();
		listPersonnel2=personnelService.findAllPersonnels2();
		listeLostMvt= new ArrayList<LostMvt>();
		this.indexTab=0;
		listeTerminauxenroll= new ArrayList<Terminaux>();
		listeTerminauxenroll =terminauxService.findEnrollTerminaux();
		action = true;
		document = new Document();
		selectedPersonnel = new Personnel();
		listdocuments = new ArrayList<Document>();
		personnel = new Personnel();
		selectedpersonnels = new ArrayList<Personnel>();
		flag = true;
		key = "Ajout";
		listTerminaux = new HashMap<String, Integer>();
		listTerminals=new DualListModel<>();
		selectedlistPTerminaux=new ArrayList<Terminaux>();
		listPTerminaux=getTerminauxService().findPointageTerminaux();
		listTerminals = new DualListModel<Terminaux>(listPTerminaux,selectedlistPTerminaux);
		
		listpersonnel = new ArrayList<Personnel>();
		listpersonnel.addAll(personnelService.findAllPersonnels());
		for (int i = 0; i < listpersonnel.size(); i++) {
			//listpersonnel.get(i).setDocuments(new HashSet(getPersonnelService().findAllDocument(listpersonnel.get(i))));
			listpersonnel.get(i).setCurrentAffiliation(getPersonnelService().findcurrentAffiliation(listpersonnel.get(i)));
		}	
		System.out.println("personnelsize"+listpersonnel.size());
		
	}
	
	/*static Logger log = Logger.getLogger(
            PersonnelBean.class.getName());*/

	
	public void vider()
	{
		idTeminaux=99999;
		listpersonnel = new ArrayList<Personnel>();
		personnel= new Personnel();
		listpersonnel.addAll(personnelService.findAllPersonnels());
//******** 		
		for (int i = 0; i < listpersonnel.size(); i++) {
			listpersonnel.get(i).setCurrentAffiliation(getPersonnelService().findcurrentAffiliation(listpersonnel.get(i)));
		}	
		personnel= new Personnel();		
		selectedPersonnel = new Personnel();
		selectedpersonnels = new ArrayList<Personnel>();
		listTerminaux = new HashMap<String, Integer>();
		
		listTerminals=new DualListModel<>();
		selectedlistPTerminaux=new ArrayList<Terminaux>();
		listPTerminaux=getTerminauxService().findPointageTerminaux();
		listTerminals = new DualListModel<Terminaux>(listPTerminaux,selectedlistPTerminaux);
		flag=true;		
		action=true;	
		this.strCategorie="";
		idCategorie=null;
		this.selectedNode=null;
		node="";
		this.indexTab=0;	
		
		termEnrol= new Terminaux();
		termEnrol.setLibelle("Selectionnez...");
		personnel = new Personnel();
		RequestContext.getCurrentInstance().reset("formajout:termenrol formajout:pantermenrol formajout:tabpersonnel");
		RequestContext.getCurrentInstance().update("formajout:termenrol formajout:pantermenrol");
		
		RequestContext.getCurrentInstance().update(" formheader:supprim");
		RequestContext.getCurrentInstance().update("formheader:modif");
		RequestContext.getCurrentInstance().update("form:listsize ");
		RequestContext.getCurrentInstance().reset("formajout:affiliation");
		RequestContext.getCurrentInstance().update("form:personnels");
		RequestContext.getCurrentInstance().update("form");
		RequestContext.getCurrentInstance().execute("PF('wiz1').loadStep (PF('wiz1').cfg.steps [0], true)");
	}

	public void switchcreate() {
		msgAffiche="Selectionnez... ";
		termEnrol= new Terminaux();
		termEnrol.setLibelle("Selectionnez...");
		personnel = new Personnel();
		action = true;
		vider();
		RequestContext.getCurrentInstance().reset("formajout:termenrol formajout:pantermenrol formajout:tabpersonnel");
		RequestContext.getCurrentInstance().update("formajout:termenrol formajout:pantermenrol");
	}

	public void switchUpdate() {	
		System.out.println("******* suitchUpdate");
		action=false;
		if(personnel.getTerminaux()!=null){ msgAffiche=personnel.getTerminaux().getLibelle();}
		termEnrol=personnel.getTerminaux();
		HistoriqueCat histcat= historiquecatservice.getCurrentHistoriqueCat(personnel.getIdper());
		if(histcat==null){FacesMessage msg = new FacesMessage("selectionner un personnel !");
		FacesContext.getCurrentInstance().addMessage(null, msg);RequestContext.getCurrentInstance().update("form:messages ");}
		
		else{
			
		idCategorie=histcat.getCategorie().getIdcat();
		this.strCategorie=histcat.getCategorie().getDesigCat();
		action = false;		
		listTerminals.setTarget(getPersonnelService().getAllPointTerminaux(personnel));
		listTerminals.setSource(getPersonnelService().findTerminauxNotAfficated(personnel));
		System.out.println("avant node");
		node = personnel.getCurrentAffiliation().toString();	
		System.out.println("aprés node");
		key = "Modification";}
		
		
	}
public void detail(Personnel P)
{
	personnel=P;
}
	public StreamedContent download(Personnel p) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			String relativeWebPath = "/resources/tmp/";
			ServletContext servletContext = (ServletContext) externalContext.getContext();
			String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
			String path = absoluteDiskPath + "/" + p.getMatricule();
			File dir = new File(path);
			dir.mkdir();

			for (org.bio.model.Document d : p.getDocuments()) {
				File file = new File(path + "/" + d.getDescription());
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(d.getSource());
				fos.close();
			}

			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(absoluteDiskPath + "/"
					+ p.getMatricule() + ".zip");
			// Folder to add
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			zipFile.addFolder(path, parameters);

			InputStream stream = ((ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext())
					.getResourceAsStream("/resources/tmp/" + p.getMatricule()
							+ ".zip");
			DefaultStreamedContent file = new DefaultStreamedContent(stream,
					"application/zip", "documents.zip");
			return file;

		} catch (Exception e) {
			return null;
		}
	}

	public void affect(SelectEvent event) {
		if (event != null) {
			personnel = (Personnel) event.getObject();
			listdocuments = new ArrayList<Document>();
		//	listdocuments.addAll(getPersonnelService().findAllDocument(
				//	personnel));
		}
	}

public boolean switchContrat()
{
	if(personnel.getTypeContrat()==null){
		return true;
	}
	else if(personnel.getTypeContrat().equalsIgnoreCase("cdi"))
	{
		personnel.setFinContrat(null);
		return false;
	}
	else return true;
}
	public String convertToFile(Personnel p) {
		if (p.getPhoto() != null)

		{
			try {
				ExternalContext externalContext = FacesContext
						.getCurrentInstance().getExternalContext();
				String relativeWebPath = "/resources/tmp/";
				ServletContext servletContext = (ServletContext) externalContext
						.getContext();
				String absoluteDiskPath = servletContext
						.getRealPath(relativeWebPath);
				File file = new File(absoluteDiskPath + "/"
						+ p.getPhotoName());
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(p.getPhoto());
				fos.close();

				return "/resources/tmp/" + p.getPhotoName();
			} catch (Exception e) {
				e.printStackTrace();
				return "/resources/img/line.jpg";
			}
		} else
			return "/resources/img/line.jpg";
	}

	public void resetAll() {
		vider();
		RequestContext.getCurrentInstance().update("formajout");
		RequestContext.getCurrentInstance().reset("formajout");
		RequestContext.getCurrentInstance().execute("diag_ajout.hide()");
		//RequestContext.getCurrentInstance().reset("formajout:tabpersonnel");

	}

	public void addDocument() {

		listdocuments.add(document);
	}

	public void deleteDocument(Document doc) {
		listdocuments.remove(doc);
	}

	public void handleFileUploaddocument(FileUploadEvent event) {

		document.setSource(event.getFile().getContents());
		document.setDescription(event.getFile().getFileName());
	}

	// fct qui retourne la liste de personnel pleinne
	public void collect() {
		listpersonnel.clear();
		listpersonnel = personnelService.findAllPersonnels();
//********* ajouter les affiliation des personnel
		for (int i = 0; i < listpersonnel.size(); i++) {
			//listpersonnel.get(i).setDocuments(new HashSet(getPersonnelService().findAllDocument(listpersonnel.get(i))));
			listpersonnel.get(i).setCurrentAffiliation(getPersonnelService().findcurrentAffiliation(listpersonnel.get(i)));
		}
	}

	// fct d'activation ou de désactivation de la colonne de selection //
	public void activate() {
		setFlag(!this.flag);
	}

	// fct d'affichage que les element selectionné de la tableau personnels //
	/*public void affichselect() {
		listpersonnel.clear();
		filtredlistPersonnel.clear();
		setFiltredlistPersonnel(this.selectedpersonnels);
		setListpersonnel(this.selectedpersonnels);
	}*/

	private void insertPersonnelTerminal(Personnel p) {
		try {
			if (action) {
				
				for (Terminaux t : listTerminals.getTarget()) {
					PersonnelTerminal pt = new PersonnelTerminal();	
					if(t.getEnrollement()==true)
					{ pt.setBckId(personnel.getBckIdEnroll());}
					else
					{pt.setBckId(Integer.valueOf(String.valueOf(p.getIdper())
							+ String.valueOf(t.getIdter())));}
					
					pt.setTerminaux(t);
					pt.setPersonnel(p);
					pt.setUploaded(false);
					pt.setId(new PersonnelTerminalId(p.getIdper(), t.getIdter()));
					getPersonnelTerminalService().insertPersonnelTerminal(pt);
						System.out.println("terminal ajouter 'enrollement'");
				}
				PersonnelTerminal pt = new PersonnelTerminal();					
				pt.setBckId(personnel.getBckIdEnroll());
				pt.setTerminaux(termEnrol);
				pt.setPersonnel(p);
				pt.setUploaded(false);
				pt.setId(new PersonnelTerminalId(p.getIdper(), termEnrol.getIdter()));
				getPersonnelTerminalService().insertPersonnelTerminal(pt);
			} else {
				
				List<PersonnelTerminal> listperterms = getPersonnelService().getAllPointPersonnelTerminaux(personnel);
				
				for(PersonnelTerminal tps: listperterms)
				{
					personnelTerminalService.deletePersonnelTerminal(tps);;
				}				
				for (Terminaux t : listTerminals.getTarget()) {
					PersonnelTerminal pt = new PersonnelTerminal();
					if(t.getEnrollement()==true)
					{ pt.setBckId(personnel.getBckIdEnroll());}
					else
					{pt.setBckId(Integer.valueOf(String.valueOf(p.getIdper())
							+ String.valueOf(t.getIdter())));}
					pt.setTerminaux(t);
					pt.setPersonnel(p);
					pt.setUploaded(false);
					pt.setId(new PersonnelTerminalId(p.getIdper(), t.getIdter()));
					getPersonnelTerminalService().insertPersonnelTerminal(pt);
						System.out.println("terminal ajouter 'enrollement'");
				}
				PersonnelTerminal pt = new PersonnelTerminal();					
				pt.setBckId(personnel.getBckIdEnroll());
				pt.setTerminaux(termEnrol);
				pt.setPersonnel(p);
				pt.setUploaded(false);
				pt.setId(new PersonnelTerminalId(p.getIdper(), termEnrol.getIdter()));
				getPersonnelTerminalService().insertPersonnelTerminal(pt);
				
			/*	List<Terminaux> listterms = getPersonnelService()
						.getAllPointTerminaux(personnel);
				for (Terminaux t : listterms) {
					if (!listTerminals.getTarget().contains(t)) {
						PersonnelTerminal pt = getTerminauxService()
								.findAssociatedTerminal(personnel, t);
						pt.setDelete(true);
						getPersonnelTerminalService().updatePersonnelTerminal(pt);
					}
				}
				for (Terminaux t : listTerminals.getTarget()) {
					PersonnelTerminal pt = new PersonnelTerminal();
					pt.setBckId(Integer.valueOf(String.valueOf(p.getIdper())
							+ String.valueOf(t.getIdter())));
					pt.setTerminaux(t);
					pt.setPersonnel(p);
					pt.setUploaded(false);
					pt.setId(new PersonnelTerminalId(p.getIdper(), t.getIdter()));
					getPersonnelTerminalService().insertPersonnelTerminal(pt);

				}*/
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	public Affiliation findste(Affiliation affiliation) {
		Affiliation parent = affiliation;
		while (!parent.getType().equals("So")) {
			parent = parent.getAffiliation();
		}
		return (parent);
	}

	public void insertPersonnel() {
		try {
			/*if(personnel.getTerminaux()==null)
			{personnel.setTerminaux(getTerminauxService().getByid(idTeminaux));}*/
			//RequestContext.getCurrentInstance().execute("diag_lost.show()");
			if(node==null){FacesMessage msg = new FacesMessage("Vous n'avez pas choisi une effiliation !");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:messages");
			return;}
			if (action) {
				personnel.setTerminaux(termEnrol);
				personnel.setFullname(personnel.getCivilite()+ " "+personnel.getPrenom()+" "+personnel.getNom());
				personnel.setCongeAccorde(0);
				personnel.setDroitRecuperation(0);
				personnel.setEncoursDroitRecuperation(0);
				personnel.setBckId(personnel.getBckIdEnroll());
				getPersonnelService().insertPersonnel(personnel);				
				HistoriqueCat hcat = new HistoriqueCat();
				Historique historique = new Historique();
				String strDate = "2099-12-01";				
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        try {
		        	
					Date dateStr = formatter.parse(strDate);
					if(personnel.getFinContrat()==null)
					{System.out.println("contrat cdi affecté");	personnel.setFinContrat(dateStr);		}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				historique.setAffiliation((Affiliation) selectedNode.getData());
				hcat.setCategorie(getCategorieservice().getByid(idCategorie));
				hcat.setDu(personnel.getDebutContrat());
				hcat.setAu(personnel.getFinContrat());
				hcat.setPersonnel(personnel);
				getHistoriquecatservice().insertHistoriqueCat(hcat);				
				historique.setDu(personnel.getDebutContrat());
				historique.setAu(personnel.getFinContrat());
				
				Affiliation ste = findste((Affiliation) selectedNode.getData()); 
				
				//  *** Ajouter nouveau ****
				historique.setDesigSte(ste.getDesignation());
				historique.setIdste(ste.getIdaff());
				//*********************************
				historique.setPersonnel(personnel);
				getHistoriqueservice().insertHistorique(historique);
				RequestContext context = RequestContext.getCurrentInstance();
				context.update(":form:messages :formpersonnel:personnels");
				FacesMessage msg = new FacesMessage("Succés de création");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				context.update("form:messages");
				Trace trace = new Trace();
				trace.setObjet("Personnel");
				trace.setAction("Insertion Personnel " + personnel.getNom()	+ " " + personnel.getPrenom());
				getTraceService().insertTrace(trace);			
				insertPersonnelTerminal(personnel);
				
				

			} else {
				boolean bol= false;
				if(termEnrol!=null){personnel.setTerminaux(termEnrol);}
				personnel.setFullname(personnel.getCivilite()+ " "+personnel.getPrenom()+" "+personnel.getNom());
				getPersonnelService().updatePersonnel(personnel);
				System.out.println("1");
				Trace trace = new Trace();
				trace.setObjet("Personnel");
				trace.setAction("Modification Personnel " + personnel.getNom()
						+ " " + personnel.getPrenom());
				getTraceService().insertTrace(trace);
				System.out.println("2");
				HistoriqueCat hcat = new HistoriqueCat();
				hcat= historiquecatservice.getCurrentHistoriqueCat(personnel.getIdper());
				Historique historique = new Historique();
				System.out.println("3");
				historique=personnelService.getcurrentHistorique(personnel);
				try{
			if(node.equals(personnel.getCurrentAffiliation().toString()))
				{historique.setAffiliation(personnel.getCurrentAffiliation());}
				else
				{historique.setAffiliation((Affiliation) selectedNode.getData());}}
				catch(Exception et1)
				{
					historique= new Historique();
					historique.setAffiliation((Affiliation) selectedNode.getData());
					hcat= new HistoriqueCat();
					bol= true;
				}
				
				hcat.setCategorie(getCategorieservice().getByid(idCategorie));				
				hcat.setDu(personnel.getDebutContrat());
				String strDate = "2099-12-01";				
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        try {
					Date dateStr = formatter.parse(strDate);
					if(personnel.getFinContrat()==null)
					{	personnel.setFinContrat(dateStr);		}
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        System.out.println("7");
				hcat.setAu(personnel.getFinContrat());				
				hcat.setPersonnel(personnel);
				
				
				System.out.println("8");
				historique.setDu(personnel.getDebutContrat());
				historique.setAu(personnel.getFinContrat());
				System.out.println("9");
				Affiliation ste= new Affiliation();
				try{
				if(node.equals(personnel.getCurrentAffiliation().toString()))
				{ ste = findste((Affiliation) personnel.getCurrentAffiliation()); }
				else
				{ste = findste((Affiliation) (Affiliation) selectedNode.getData()); }}
				catch(Exception et2)
				{ste= new Affiliation();ste = findste((Affiliation) (Affiliation) selectedNode.getData()); }
				
				System.out.println("10");
				//  *** valeur ajouter nouveau ****
				historique.setDesigSte(ste.getDesignation());				
				historique.setIdste(ste.getIdaff());
				//*********************************
				historique.setPersonnel(personnel);	
				if(bol=false)
				{getHistoriqueservice().updateHistorique(historique);
				getHistoriquecatservice().updateHistoriqueCat(hcat);}
				else{ getHistoriqueservice().insertHistorique(historique);
				getHistoriquecatservice().insertHistoriqueCat(hcat);	}
				
				System.out.println("11");
				RequestContext context = RequestContext.getCurrentInstance();
				context.update(":form:messages :formpersonnel:personnels");
				FacesMessage msg = new FacesMessage("Succés de modification");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				context.update("form:messages ");
				System.out.println("15");
				insertPersonnelTerminal(personnel);
			}
			
				
				listeLostMvt= new ArrayList<>();
				listeLostMvt.addAll(lostMvtService.getByBckid(personnel.getBckIdEnroll()));
				if(listeLostMvt.size()>0)
				{
					
					retirPointage();
				}
				
			
			vider();
			termEnrol=new Terminaux();
			this.personnel= new Personnel();
			
			
			
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getMessage();
		}
//		catch (NullPointerException e) {
//			FacesMessage msg = new FacesMessage("Vous avez oublié un champ obligatoire vide !");
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//			e.getMessage();
//		}

	}
	
	public void retirPointage()
	{
		
		for(int i=0;i<listeLostMvt.size();i++)
		{
			if(listeLostMvt.get(i).getId().getJour().after(personnel.getDebutContrat()) || listeLostMvt.get(i).getId().getJour() == personnel.getDebutContrat() )
			{
			MvtId mvtid = new MvtId();
			mvtid.setIdper(personnel.getIdper());
			mvtid.setIdter(listeLostMvt.get(i).getTerminaux().getIdter());
			
			Mvt mvt = new Mvt();
			mvt.setPersonnel(this.personnel);
			mvt.setTerminaux(listeLostMvt.get(i).getTerminaux());
			mvt.setMoyen(listeLostMvt.get(i).getMoyen());
			mvt.setMode(listeLostMvt.get(i).getMode());
			mvt.setJourLogique(listeLostMvt.get(i).getJourLogique());
			mvt.setBckId(listeLostMvt.get(i).getId().getBckId());
			mvt.setEtat("new");
			mvt.setHeure(listeLostMvt.get(i).getId().getHeure());
			mvt.setJour(listeLostMvt.get(i).getId().getJour());
			mvt.setId(mvtid);
			mvtService.insertMvt(mvt);
			}
			
		}
		
	}

	public void deletePersonnel() {
		try {

			for (Personnel p : selectedpersonnels) {
				getPersonnelService().deletePersonnel(p);
			}
			FacesMessage msg = new FacesMessage("Succès");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			Trace trace = new Trace();
			trace.setAction("Suppression Personnel " + personnel.getNom() + " "
					+ personnel.getPrenom());
			trace.setObjet("Personnel");
			getTraceService().insertTrace(trace);
			vider();
			RequestContext.getCurrentInstance().update("form:personnels");
		} catch (DataAccessException e) {
			FacesMessage msg = new FacesMessage("Erreur");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.getMessage();
		}

	}

	public void handleFileUpload(FileUploadEvent event) {

		personnel.setPhoto(event.getFile().getContents());
		int str = event.getFile().getFileName().hashCode();		
		personnel.setPhotoName(String.valueOf(str));		
	}

	public void handleFileUploadupdate(FileUploadEvent event) {

		personnel.setPhoto(event.getFile().getContents());
		int str = event.getFile().getFileName().hashCode();		
		personnel.setPhotoName(String.valueOf(str));		
	}

	public void removeSelectedPersonnel(Personnel p) {
		selectedpersonnels.remove(p);
	}

	// ////////////// getters and setters ////////////
	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public List<Personnel> getListpersonnel() {
		//listpersonnel = new ArrayList<Personnel>();
		//listpersonnel.addAll(personnelService.findAllPersonnels());
		/*for (int i = 0; i < listpersonnel.size(); i++) {
			listpersonnel.get(i).setDocuments(new HashSet(getPersonnelService().findAllDocument(listpersonnel.get(i))));
			listpersonnel.get(i).setCurrentAffiliation(getPersonnelService().findcurrentAffiliation(listpersonnel.get(i)));
		}*/

		return listpersonnel;
	}

	public void setListpersonnel(List<Personnel> listpersonnel) {
		this.listpersonnel = listpersonnel;
	}

	public Personnel getSelectedPersonnel() {
		return selectedPersonnel;
	}

	public void setSelectedPersonnel(Personnel selectedPersonnel) {
		this.selectedPersonnel = selectedPersonnel;

	}

	public List<Personnel> getFiltredlistPersonnel() {
		return filtredlistPersonnel;
	}

	public void setFiltredlistPersonnel(List<Personnel> filtredlistPersonnel) {
		this.filtredlistPersonnel = filtredlistPersonnel;
	}

	public List<Personnel> getSelectedpersonnels() {
		return selectedpersonnels;
	}

	public void setSelectedpersonnels(List<Personnel> selectedpersonnels) {
		this.selectedpersonnels = selectedpersonnels;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	public int getSizeliste() {
		if(selectedpersonnels==null)
			return 0;
		else if(selectedpersonnels.size()==0)
			return 0;
		else
		sizeliste = selectedpersonnels.size();
		return sizeliste;
	}
	////////////////////
	public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
           
        }
    }
    
    public String returnProcess(FlowEvent event) {
     
            return event.getOldStep();
           
    }
    //////////////
	public void setSizeliste(int sizeliste) {
		this.sizeliste = sizeliste;
	}

	public List<Document> getListdocuments() {
		return listdocuments;
	}

	public void setListdocuments(List<Document> listdocuments) {
		this.listdocuments = listdocuments;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public CategorieService getCategorieservice() {
		return categorieservice;
	}

	public void setCategorieservice(CategorieService categorieservice) {
		this.categorieservice = categorieservice;
	}

	public HistoriqueService getHistoriqueservice() {
		return historiqueservice;
	}

	public void setHistoriqueservice(HistoriqueService historiqueservice) {
		this.historiqueservice = historiqueservice;
	}

	public Integer getIdaffeliation() {
		return idaffeliation;
	}

	public void setIdaffeliation(Integer idaffeliation) {
		this.idaffeliation = idaffeliation;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public AffiliationService getAffiliationService() {
		return affiliationService;
	}

	public void setAffiliationService(AffiliationService affiliationService) {
		this.affiliationService = affiliationService;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public List<Document> getListdocumentsUpdate() {

		return listdocumentsUpdate;
	}

	public void setListdocumentsUpdate(List<Document> listdocumentsUpdate) {
		this.listdocumentsUpdate = listdocumentsUpdate;
	}

	public String getNode() {
		Affiliation a;
		if (selectedNode != null) {
			a = (Affiliation) selectedNode.getData();
			node = a.getDesignation();
		}
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public HistoriqueCatService getHistoriquecatservice() {
		return historiquecatservice;
	}

	public void setHistoriquecatservice(
			HistoriqueCatService historiquecatservice) {
		this.historiquecatservice = historiquecatservice;
	}

	public TraceService getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
	}

	public Map<String, Integer> getListTerminaux() {
		listPTerminaux = new ArrayList<Terminaux>();
		listPTerminaux.addAll(getTerminauxService().findPointageTerminaux());
		for (Terminaux t : getTerminauxService().findEnrollTerminaux()) {
			listTerminaux.put(t.getLibelle() + " " + t.getType(), t.getIdter());
		}
		return listTerminaux;
		}

	public void setListTerminaux(Map<String, Integer> listTerminaux) {
		this.listTerminaux = listTerminaux;
	}

	public TerminauxService getTerminauxService() {
		return terminauxService;
	}

	public void setTerminauxService(TerminauxService terminauxService) {
		this.terminauxService = terminauxService;
	}

	public int getIdTeminaux() {
		return idTeminaux;
	}

	public void setIdTeminaux(int idTeminaux) {
		this.idTeminaux = idTeminaux;
	}

	public List<Terminaux> getListPTerminaux() {
		return listPTerminaux;
	}

	public void setListPTerminaux(List<Terminaux> listPTerminaux) {
		this.listPTerminaux = listPTerminaux;
	}

	public List<Terminaux> getSelectedlistPTerminaux() {
		return selectedlistPTerminaux;
	}

	public void setSelectedlistPTerminaux(List<Terminaux> selectedlistPTerminaux) {
		this.selectedlistPTerminaux = selectedlistPTerminaux;
	}

	public PersonnelTerminalService getPersonnelTerminalService() {
		
		return personnelTerminalService;
	}

	public void setPersonnelTerminalService(
			PersonnelTerminalService personnelTerminalService) {
		this.personnelTerminalService = personnelTerminalService;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public DefaultStreamedContent getFile() {
		return file;
	}

	public void setFile(DefaultStreamedContent file) {
		this.file = file;
	}

	public DualListModel<Terminaux> getListTerminals() {
		return listTerminals;
	}

	public void setListTerminals(DualListModel<Terminaux> listTerminals) {
		this.listTerminals = listTerminals;
	}
	public String getStrCategorie() {
		return strCategorie;
	}
	public void setStrCategorie(String strCategorie) {
		this.strCategorie = strCategorie;
	}
	public int getIndexTab() {
		return indexTab;
	}
	public void setIndexTab(int indexTab) {
		this.indexTab = indexTab;
	}
	public boolean isAction() {
		return action;
	}
	public void setAction(boolean action) {
		this.action = action;
	}
	public List<Terminaux> getListeTerminauxenroll() {
		return listeTerminauxenroll;
	}
	public void setListeTerminauxenroll(List<Terminaux> listeTerminauxenroll) {
		this.listeTerminauxenroll = listeTerminauxenroll;
	}
	public Terminaux getTermEnrol() {
		return termEnrol;
	}
	public void setTermEnrol(Terminaux termEnrol) {
		this.termEnrol = termEnrol;
	}
	public String getMsgAffiche() {
		return msgAffiche;
	}
	public void setMsgAffiche(String msgAffiche) {
		this.msgAffiche = msgAffiche;
	}
	public SequenceService getSequenceService() {
		return sequenceService;
	}
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}
	public LostMvtService getLostMvtService() {
		return lostMvtService;
	}
	public void setLostMvtService(LostMvtService lostMvtService) {
		this.lostMvtService = lostMvtService;
	}
	public List<LostMvt> getListeLostMvt() {
		return listeLostMvt;
	}
	public void setListeLostMvt(List<LostMvt> listeLostMvt) {
		this.listeLostMvt = listeLostMvt;
	}
	public String getMsgLostMvt() {
		return msgLostMvt;
	}
	public void setMsgLostMvt(String msgLostMvt) {
		this.msgLostMvt = msgLostMvt;
	}
	public boolean isFlagLost() {
		return flagLost;
	}
	public void setFlagLost(boolean flagLost) {
		this.flagLost = flagLost;
	}
	public MvtService getMvtService() {
		return mvtService;
	}
	public void setMvtService(MvtService mvtService) {
		this.mvtService = mvtService;
	}
	public List<Personnel> getListPersonnel2() {
		return listPersonnel2;
	}
	public void setListPersonnel2(List<Personnel> listPersonnel2) {
		this.listPersonnel2 = listPersonnel2;
	}	

}
