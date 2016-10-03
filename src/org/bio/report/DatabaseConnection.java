package org.bio.report;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bio.service.IniService;
import org.ini4j.InvalidFileFormatException;

import com.mysql.jdbc.Statement;

import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.IOException;


public class DatabaseConnection {
private static IniService ini = new IniService();

 
    public static Connection getConnection() throws InvalidFileFormatException, FileNotFoundException, IOException {
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            Connection con = DriverManager.getConnection(ini.getUrl(),
                    ini.getUserName(), ini.getPassword());
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
    public static Connection getConnectionSpago() throws InvalidFileFormatException, FileNotFoundException, IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(ini.getUrlSpago(),
                    ini.getUserName(), ini.getPassword());
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
  
  
}
    	
   
   
