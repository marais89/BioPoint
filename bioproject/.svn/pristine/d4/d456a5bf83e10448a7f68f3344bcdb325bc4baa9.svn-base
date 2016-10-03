package org.bio.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.bio.model.Personnel;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.lingala.zip4j.core.ZipFile;

public class FileCompress {

	public void zipfile(Personnel p) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			String relativeWebPath = "/resources/tmp/";
			ServletContext servletContext = (ServletContext) externalContext
					.getContext();
			String absoluteDiskPath = servletContext
					.getRealPath(relativeWebPath);
			boolean success = (new File(absoluteDiskPath +"/"+ p.getMatricule()))
					.mkdirs();
			String path = absoluteDiskPath + p.getMatricule();
			for(org.bio.model.Document d:p.getDocuments())
			{
			File file = new File(path+"/"+d.getDescription());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(d.getSource());
			fos.close();
			}
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(path+"/"+ p.getMatricule()+".zip");
			// Folder to add
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters();
			// set compression method to store compression
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

			// Set the compression level
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

			// Add folder to the zip file
			zipFile.addFolder(path, parameters);
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
