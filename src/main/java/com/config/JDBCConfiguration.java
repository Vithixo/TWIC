package com.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConfiguration {
	
	
	public static Connection getConnection() {
		String BDD = "ville_france";
	    String url = "jdbc:mysql://localhost:3306/" + BDD;
	    String user = "somanager";
	    String password = "yhqyD4rJzto+K4";
	    
	    Connection conn = null;
	    // L'essaie de connexion à votre base de donées
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(url, user, password);
	        System.out.println("Connecter");
	    } catch (Exception e){
	        e.printStackTrace();
	        System.out.println("Erreur");
	        System.exit(0);
	    }
	    
	    return conn;
	}
	
	

}
