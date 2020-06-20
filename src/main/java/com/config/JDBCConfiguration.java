package com.config;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class JDBCConfiguration {
	
	private JDBCConfiguration() {
		
	}
	
	private static final String MDPBDD = "yhqyD4rJzto+K4";
	
	private static Logger logger = Logger.getLogger("log");
	
	public static Connection getConnection() {
		String bdd = "ville_france";
	    String url = "jdbc:mysql://localhost:3306/" + bdd;
	    String user = "somanager";
	    
	    Connection conn = null;
	    // L'essaie de connexion à votre base de donées
	    try {
	        conn = DriverManager.getConnection(url, user, getMotDePasse());
	    } catch (Exception e){
	    	logger.log(Level.WARN, "Échec de la connexion", e);
	        System.exit(0);
	    }
	    
	    return conn;
	}
	
	private static String getMotDePasse() {
		return MDPBDD;
	}
	
	

}
