package org.bio.report;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.base.JRBaseField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.fill.JRFillField;
import net.sf.jasperreports.engine.xml.JRFieldFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

























import org.apache.commons.io.IOUtils;
import org.bio.service.CalendrierService;
import org.bio.model.Personnel;
import org.bio.report.DatabaseConnection;

import com.sun.faces.application.resource.ResourceManager;

public class ReportingService {
	 
      private JasperPrint jasperPrint;
      private String code;
     
      public boolean findGroupBeak( Map<Integer, Boolean> groupBreakPageMap, int indexGroup)
      {
      	 Set listKeys=groupBreakPageMap.keySet();  
      		Iterator iterateur=listKeys.iterator();
      		boolean isBreak = false;
      	 while(iterateur.hasNext() && isBreak == false)
      		{
      		  
      			Object key= iterateur.next();
      			if (key == (Object)indexGroup && groupBreakPageMap.get(key) == true  )
      			{	isBreak = true;
      			System.out.println(key+" saut de page");}
      		}
      	 return isBreak;
      }
      	public void setPageBreak( Map<Integer, Boolean> groupBreakPageMap , JasperReport jasperReport) {    	  		
      	  		
      		
      	  		
      		JRGroup[] groups = (JRGroup[]) jasperReport.getGroups();
      	
      		  for (int i = 0; groups != null && i < groups.length; i++) 
      		  { 
      			 
      			  int concatGroup = i+1;
      			
      		  JRGroup currentGroup = groups[i]; 
      		
      		  if (currentGroup.getName().equals("group"+concatGroup) == true && findGroupBeak(groupBreakPageMap, i) == true ) 
      		  { 
      		System.out.print("HELLO GROUP");
      		currentGroup.setStartNewPage(true);
      		  } 
      	  } 
      	}
      	


        public void previewPdf(String pathReport,String query,String order, String period, String filtre,Map<Integer, String> groupMap,Map<Integer, String> groupLibelleMap, Map<Integer, Boolean> groupBreakPageMap, String langue, String societe, String code) {
      	  Connection connection = null;
      	  try {        	
      		  connection = DatabaseConnection.getConnection();
      		  Map<String, Object> reportParameters = new HashMap<String, Object>();
      		
      	      		 
      		

      		  // find Groups
      		  JasperDesign jasperDesign = JRXmlLoader.load(FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathReport));//charger le fichier .jrxml
      		  JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);//compile .jrxml to .jasper		  
    
      		  reportParameters.put("periode", period);
      		  if (query != null){
      		  reportParameters.put("DynamicQuery", query);
      		  }
      		reportParameters.put("filtre", filtre);
      		 if (order != null){
      		reportParameters.put("orderParam", order);
      		 }
      		if (societe != null){
      		  reportParameters.put("societe", societe);
      		  }
//      		reportParameters.put("REPORT_RESOURCE_BUNDLE","report_AL");
//      
//      		JRBaseField[] fields = (JRBaseField[]) jasperReport.getFields();
//      
// 			for (int i = 0; fields != null && i < fields.length; i++) 
//    		  { 
//  				 System.out.println("++++++++++++++++++++++++++++++");
//    			 System.out.println("fields[i]");
//    			 System.out.println("++++++++++++++++++++++++++++++");
//    			  if (fields[i].getName()=="pointage_jour")
//    			  {
//   
//    			  }
//    		  }
      		
      		
      		
      		ResourceBundle resourceBundle = ResourceBundle.getBundle("report_"+langue);
      		 reportParameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, resourceBundle); 
      		
      		
      	
         // valeur des groupes par exemple pointage.`jour`
          Set<Integer> listKeys=groupMap.keySet();  
        		Iterator<Integer> iterateur=listKeys.iterator();
        	   	 while(iterateur.hasNext())
        		{
        		  
        			int key= iterateur.next();
        			int indexGroup = key + 1;
        			System.out.println("group"+indexGroup);
        		    reportParameters.put("paramGroup"+indexGroup, groupMap.get(key));       		    
        		    
        		}
          
        	   // libelle des groupes     
                 Set<Integer> listLibelleKeys=groupLibelleMap.keySet();  
               		Iterator<Integer> iterateurLibelle=listLibelleKeys.iterator();
               	   	 while(iterateurLibelle.hasNext())
               		{
               		  
               			int key= iterateurLibelle.next();
               			int indexGroup = key + 1;
               			System.out.println("group"+indexGroup);
               		    reportParameters.put("libParamGroup"+indexGroup, groupLibelleMap.get(key));               		    
               		    
               		}
      		 	  
      		//  groupBreakPageMap.put(2,false);
      	   // groupBreakPageMap.put(1,true);
      		
               	  
     		 
      		  
      		  
              setPageBreak(groupBreakPageMap, jasperReport);
      	       
              jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters, connection);
              JasperViewer  jReportsViewer = new JasperViewer(jasperPrint, false, null);
      	    jReportsViewer.setFitWidthZoomRatio();
      	    jReportsViewer.setVisible(true);
      	    

      	 
      	      
        }catch(Exception e){
      	
      	 e.printStackTrace();
        }
      	  finally {
                try {
                
              	  connection.close();
              	  System.out.println("echoué");
                } catch (Exception e) {
              	e.printStackTrace();
                
                }
      	  }
        }
        public String getRealPath( String destination ) {
      		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      		ServletContext servletContext = (ServletContext) externalContext.getContext();
      		String absoluteDiskPath = servletContext.getRealPath(destination);
      		 System.out.println("+++++++++++++++++absolut path "+absoluteDiskPath);
      		return absoluteDiskPath;
      	}
        
        static public void deleteDirectory( String emplacement )
        {
          File path = new File( emplacement );
          Date d= new Date();
			Calendar c= new GregorianCalendar();
			c.setTime(d);	
			String strDate = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH)+1)+""+(c.get(Calendar.DAY_OF_MONTH)-1);
			int nbr= new Integer(strDate);
          
          if( path.exists() )
          {
            File[] files = path.listFiles();
            for( int i = 0 ; i < files.length ; i++ )
            {  String f=files[i].toString();
            String ext = f.substring(f.lastIndexOf("."));
           
              if( files[ i ].isDirectory() )
              { }
              else{
            	  if(ext.equals(".pdf")){ 
            		  
            		  System.out.println("lastIndexOf('M')"+f.lastIndexOf("M"));
                      System.out.println("f.lastIndexOf('.')"+f.lastIndexOf("."));
                     String date = f.substring(f.lastIndexOf("M")+1,f.lastIndexOf("."));   
                     int dateNbr = new Integer(date);
                     System.out.println("---->"+date);
                     System.out.println("-nbr->"+nbr);
                     if(dateNbr<nbr)
            		  {files[ i ].delete();}
            		  }
            	  }
              
            }
          }
        }
        
    public void exportToPdf(String pathReport,String query,String order, String period, String filtre,Map<Integer, String> groupMap,Map<Integer, String> groupLibelleMap, Map<Integer, Boolean> groupBreakPageMap, String langue, String societe,String s,String code) {  	
    	Connection connection = null;
   
	  try {  
	
		  connection = DatabaseConnection.getConnection();
		  Map<String, Object> reportParameters = new HashMap<String, Object>();

		  // find Groups
		  JasperDesign jasperDesign = JRXmlLoader.load(FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathReport));//charger le fichier .jrxml
		  JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);//compile .jrxml to .jasper		  

		  reportParameters.put("periode", period);
		  if (query != null){
      		  reportParameters.put("DynamicQuery", query);
      		  }
      		reportParameters.put("filtre", filtre);
      		 if (order != null){
      		reportParameters.put("orderParam", order);
      		 }
      		if (societe != null){
        		  reportParameters.put("societe", societe);
        		  }
		
//		reportParameters.put("REPORT_RESOURCE_BUNDLE","report_AL");

//		JRBaseField[] fields = (JRBaseField[]) jasperReport.getFields();
//
//		for (int i = 0; fields != null && i < fields.length; i++) 
//		  { 
//			 System.out.println("++++++++++++++++++++++++++++++");
//			 System.out.println("fields[i]");
//			 System.out.println("++++++++++++++++++++++++++++++");
//			  if (fields[i].getName()=="pointage_jour")
//			  {
//
//			  }
//		  }		
		
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("report_"+langue);
		reportParameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, resourceBundle); 
		
		
	
   // valeur des groupes par exemple pointage.`jour`
    Set<Integer> listKeys=groupMap.keySet();  
  		Iterator<Integer> iterateur=listKeys.iterator();
  	   	 while(iterateur.hasNext())
  		{
  		  
  			int key= iterateur.next();
  			int indexGroup = key + 1;
  			System.out.println("group"+indexGroup);
  		    reportParameters.put("paramGroup"+indexGroup, groupMap.get(key)); 		    
  		}
    
  	   // libelle des groupes     
           Set<Integer> listLibelleKeys=groupLibelleMap.keySet();  
         		Iterator<Integer> iterateurLibelle=listLibelleKeys.iterator();
         	   	 while(iterateurLibelle.hasNext())
         		{         		  
         			int key= iterateurLibelle.next();
         			int indexGroup = key + 1;
         			System.out.println("group"+indexGroup);
         		    reportParameters.put("libParamGroup"+indexGroup, groupLibelleMap.get(key));   			
         		    
         		    
         		}		 	  
		//  groupBreakPageMap.put(2,false);
	   // groupBreakPageMap.put(1,true);
		
         	  
		 
		  
		  
        setPageBreak(groupBreakPageMap, jasperReport);
   	      
            JasperPrint print = JasperFillManager.fillReport(jasperReport, reportParameters, connection);
      	 
      	FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        
        String dir = "pages/";
        String dirPath = ec.getRealPath(dir);
        deleteDirectory( dirPath.toString() );
 
        String pdfName = "pages/Report"+s+code+".pdf";
        String pdfPath = ec.getRealPath(pdfName);
        System.out.println("+++++++++++++++++Path cote serveur "+pdfPath);
        //JasperExportManager.exportReportToPdfFile(print,pdfPath); 
        JasperExportManager.exportReportToPdfFile(print,pdfPath); 
        ec.responseReset(); 
        ec.setResponseContentType(ec.getMimeType(pdfPath)); 
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + pdfName + "\"");
     
        // afaire ddddddd
       // ec.wait(1000);
        //ec.addResponseHeader("newContent"," + pdfName + ");
       
  	   // ec.addResponseHeader("Content-Disposition", pdfName);
        InputStream input = new FileInputStream(pdfPath);
        OutputStream output = ec.getResponseOutputStream();
        //
        IOUtils.copy(input, output);

        System.out.println("Sending to browser...");
//
        fc.responseComplete();  
           
          
            
            
           
            
      }catch(Exception e){
    	
    	 e.printStackTrace();
      }
    	  finally {
              try {
              
            	  connection.close();
            	  System.out.println("echoué");
              } catch (Exception e) {
            	e.printStackTrace();
              
              }
    	  }
	
      	}
    
    ////////////// export to pdf 2
    
    public void exportToPdf2(String pathReport,String query,String order, String period, String filtre,Map<Integer, String> groupMap,Map<Integer, String> groupLibelleMap, Map<Integer, Boolean> groupBreakPageMap, String langue, String societe,String code) {  	
    	Connection connection = null;
   
	  try {  
	
		  connection = DatabaseConnection.getConnection();
		  Map<String, Object> reportParameters = new HashMap<String, Object>();

		  // find Groups
		  JasperDesign jasperDesign = JRXmlLoader.load(FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathReport));//charger le fichier .jrxml
		  JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);//compile .jrxml to .jasper		  

		  reportParameters.put("periode", period);
		  if (query != null){
      		  reportParameters.put("DynamicQuery", query);
      		  }
      		reportParameters.put("filtre", filtre);
      		 if (order != null){
      		reportParameters.put("orderParam", order);
      		 }
      		if (societe != null){
        		  reportParameters.put("societe", societe);
        		  }
		
//		reportParameters.put("REPORT_RESOURCE_BUNDLE","report_AL");

//		JRBaseField[] fields = (JRBaseField[]) jasperReport.getFields();
//
//		for (int i = 0; fields != null && i < fields.length; i++) 
//		  { 
//			 System.out.println("++++++++++++++++++++++++++++++");
//			 System.out.println("fields[i]");
//			 System.out.println("++++++++++++++++++++++++++++++");
//			  if (fields[i].getName()=="pointage_jour")
//			  {
//
//			  }
//		  }
		
		
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("report_"+langue);
		 reportParameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, resourceBundle); 
		
		
	
   // valeur des groupes par exemple pointage.`jour`
    Set<Integer> listKeys=groupMap.keySet();  
  		Iterator<Integer> iterateur=listKeys.iterator();
  	   	 while(iterateur.hasNext())
  		{
  		  
  			int key= iterateur.next();
  			int indexGroup = key + 1;
  			System.out.println("group"+indexGroup);
  		    reportParameters.put("paramGroup"+indexGroup, groupMap.get(key));
  		       		       			
  		    
  		    
  		}
    
  	   // libelle des groupes     
           Set<Integer> listLibelleKeys=groupLibelleMap.keySet();  
         		Iterator<Integer> iterateurLibelle=listLibelleKeys.iterator();
         	   	 while(iterateurLibelle.hasNext())
         		{
         		  
         			int key= iterateurLibelle.next();
         			int indexGroup = key + 1;
         			System.out.println("group"+indexGroup);
         		    reportParameters.put("libParamGroup"+indexGroup, groupLibelleMap.get(key));
         		      		       			
         		    
         		    
         		}
		 	  
		//  groupBreakPageMap.put(2,false);
	   // groupBreakPageMap.put(1,true);
		
         	  
		 
		  
		  
        setPageBreak(groupBreakPageMap, jasperReport);
   	      
            JasperPrint print = JasperFillManager.fillReport(jasperReport, reportParameters, connection);
      	 
      	FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
 
        String pdfName = "/Report.pdf";
        String pdfPath = ec.getRealPath(pdfName);
        JasperExportManager.exportReportToPdfFile(print,pdfPath); 
        ec.responseReset(); 
        ec.setResponseContentType(ec.getMimeType(pdfPath)); 
        // 	
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + pdfName + "\"");
  	     
        InputStream input = new FileInputStream(pdfPath);
        OutputStream output = ec.getResponseOutputStream();
        //
        IOUtils.copy(input, output);

        System.out.println("Sending to browser...");
//
        fc.responseComplete();  
           
            
          
            
            
           
            
      }catch(Exception e){
    	
    	 e.printStackTrace();
      }
    	  finally {
              try {
              
            //	  connection.close();
            	  System.out.println("echoué");
              } catch (Exception e) {
            	e.printStackTrace();
              
              }
    	  }
	
      	}

    
    
    ///////////////////////////////


	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
//	listeIdAff.add(41); historique.`idaff` in ("personnel.`matricule`  <>  ''")
		
		//  reportParameters.put("DynamicQuery", "historique.`idaff`  in   ("+StringUtils.join(listeIdAff, ',')+")");

//	  JasperDesign jasperDesign = JRXmlLoader.load(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/rapport/pointageRpt.jrxml"));//charger le fichier .jrxml
//    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); //compile .jrxml to .jasper
	
//  FacesContext context = FacesContext.getCurrentInstance();
//  HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
//  InputStream reportStream = context.getExternalContext().getResourceAsStream(pathReport);
//
//  ServletOutputStream servletOutputStream = response.getOutputStream();
//  JasperRunManager.runReportToPdfStream(reportStream,    servletOutputStream,  reportParameters, connection);
// 
//	  response.setContentType("application/pdf");
//  response.setHeader("Content-Disposition","attachement; filename=\"pointRpt.pdf\"");
//  servletOutputStream.flush();
//  servletOutputStream.close(); 
//  reportStream.close();	  
//  String path=getRealPath("/resources/rapport/");
//  System.out.println(path);
//      JasperExportManager.exportReportToPdfFile(jasperPrint,path+"/pointagePers.pdf");  


}