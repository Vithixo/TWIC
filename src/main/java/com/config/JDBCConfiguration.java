package com.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConfiguration {
	
	private final static String MDPBDD = "yhqyD4rJzto+K4";
	public static Connection getConnection() {
		String BDD = "ville_france";
	    String url = "jdbc:mysql://localhost:3306/" + BDD;
	    String user = "somanager";
	    
	    Connection conn = null;
	    // L'essaie de connexion à votre base de donées
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(url, user, MDPBDD);
	    } catch (Exception e){
	        e.printStackTrace();
	        System.exit(0);
	    }
	    
	    return conn;
	}
	
	

}
