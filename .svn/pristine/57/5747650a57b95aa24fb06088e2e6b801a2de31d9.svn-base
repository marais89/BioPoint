package org.bio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

public class FileConverter {

	private String fileName;
	private static final String destination="/resources/tmp/";
	private String realPath;
	
	public FileConverter()
	{
		
	}
	private boolean uploadtotmp(FileUploadEvent event) {
	    try {
	    	
	    	fileName=event.getFile().getFileName();
	    	
	        File targetFolder = new File(getRealPath());
	        InputStream inputStream = event.getFile().getInputstream();
	        OutputStream out = new FileOutputStream(new File(targetFolder,
	                event.getFile().getFileName()));
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        while ((read = inputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        inputStream.close();
	        out.flush();
	        out.close();
	        
return true;

	    } catch (IOException e) {
	    	 return false;
	    }
	
	}
	
public byte[]  convertToblob(FileUploadEvent event)
{
	if(uploadtotmp(event))
	{
		File file = new File(getRealPath()+"/"+fileName);
		byte[] bFile = new byte[(int) file.length()];
		try{
			 FileInputStream fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bFile);
	            fileInputStream.close();
			return bFile;
		}
		catch (Exception e) {
            e.printStackTrace();
            return null;
        }
         
	}
	else	
		return null;
}
public String convertToFile(String name,byte[] bytes)
{
	 try {
        
		 FileInputStream fileInputStream=null;
		 
	    //convert array of bytes into file
	    FileOutputStream fileOuputStream = 
               new FileOutputStream("/resources/tmp/"+name); 
	    fileOuputStream.write(bytes);
	    fileOuputStream.close();

	    System.out.println("Done");
	    return "/resources/tmp/"+name;
     }catch(Exception e){
         e.printStackTrace();
     return null;
     }
	 }
public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}



public String getRealPath() {
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	String relativeWebPath = destination;
	ServletContext servletContext = (ServletContext) externalContext.getContext();
	String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
	return absoluteDiskPath;
}

public void setRealPath(String realPath) {
	this.realPath = realPath;
}

public static String getDestination() {
	return destination;
}
	
}
