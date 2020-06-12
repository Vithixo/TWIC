package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO{
	
	private static String CODE = "Code_commune_INSEE";
	private static String NOM = "Nom_commune";
	private static String CODE_POSTAL = "Code_postal";
	private static String LIBELLE = "Libelle_acheminement";
	private static String LIGNE5 = "Ligne_5";
	private static String LATITUDE = "Latitude";
	private static String LONGITUDE = "Longitude";
	
	private static String SELECT_ALL = "SELECT * FROM ville_france";
	
	
	@Override
	public ArrayList<Ville> lister() {
		Connection connection = JDBCConfiguration.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Ville> villes = new ArrayList<Ville>();
		
		try {
			// création d'une connexion grâce à la DAOFactory placée en attribut de la
			preparedStatement = connection.prepareStatement(SELECT_ALL);
			resultSet = preparedStatement.executeQuery();
			// récupération des valeurs des attributs de la BDD pour les mettre dans une
			// liste
			while (resultSet.next()) {
				villes.add(recupererVille(resultSet));
			}
		} catch (SQLException e) {
			//logger.log(Level.WARN, "Échec du listage des objets.", e);
			System.out.println(e);
		} finally {
			//fermetures(resultSet, preparedStatement, connection);
		}
		return villes;
	}
	
	@Override
	public ArrayList<Ville> trouver(String code,String nom,String codePostal,String libelle,
			String ligne5,String latitude,String longitude) {
		Connection connection = JDBCConfiguration.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Ville> villes = new ArrayList<Ville>();
		String requete = SELECT_ALL;
		Boolean nouvelleCondition = true;
		if(code != null) {
			requete = requete+ajouterCondition(CODE,code,nouvelleCondition);
			nouvelleCondition = false;
		}
		if(nom != null) {
			requete = requete+ajouterCondition(NOM,"'"+nom+"'",nouvelleCondition);
			nouvelleCondition = false;
		}
		if(codePostal != null) {
			requete = requete+ajouterCondition(CODE_POSTAL,codePostal,nouvelleCondition);
			nouvelleCondition = false;
		}
		if(libelle != null) {
			requete = requete+ajouterCondition(LIBELLE,"'"+libelle+"'",nouvelleCondition);
			nouvelleCondition = false;		
		}
		if(ligne5 != null) {
			requete = requete+ajouterCondition(LIGNE5,"'"+ligne5+"'",nouvelleCondition);
			nouvelleCondition = false;
		}
		if(latitude != null) {
			requete = requete+ajouterCondition(LATITUDE,latitude,nouvelleCondition);
			nouvelleCondition = false;
		}
		if(longitude != null) {
			requete = requete+ajouterCondition(LONGITUDE,longitude,nouvelleCondition);
			nouvelleCondition = false;
		}
		
		System.out.println(requete);
		
		try {
			// création d'une connexion grâce à la DAOFactory placée en attribut de la
			preparedStatement = connection.prepareStatement(requete);
			resultSet = preparedStatement.executeQuery();
			// récupération des valeurs des attributs de la BDD pour les mettre dans une
			// liste
			while (resultSet.next()) {
				villes.add(recupererVille(resultSet));
			}
		} catch (SQLException e) {
			//logger.log(Level.WARN, "Échec du listage des objets.", e);
			System.out.println(e);
		} finally {
			//fermetures(resultSet, preparedStatement, connection);
		}
		return villes;
	}
	
	private Ville recupererVille(ResultSet resultSet) throws SQLException {
		Ville ville= new Ville();
		
		ville.setCode(resultSet.getInt(CODE));
		ville.setNom(resultSet.getString(NOM));
		ville.setCodePostale(resultSet.getInt(CODE_POSTAL));
		ville.setLibelle(resultSet.getString(LIBELLE));
		ville.setLigne5(resultSet.getString(LIGNE5));
		ville.setLatitude(resultSet.getFloat(LATITUDE));
		ville.setLongitude(resultSet.getFloat(LONGITUDE));
		
		return ville;
	}

	@Override
	public void creer(Ville ville) {
		Connection connection = JDBCConfiguration.getConnection();
		
		String requete = "INSERT INTO ville_france VALUES('"+ville.getCode()+"','"
				+ville.getNom()+"','"
				+ville.getCodePostale()+"','"
				+ville.getLibelle()+"','"
				+ville.getLigne5()+"','"
				+ville.getLatitude()+"','"
				+ville.getLongitude()+"')";
		
		try  {
		   Statement stmt = connection.createStatement();
		   stmt.executeUpdate(requete);

		} catch (SQLException e) {
			//logger.log(Level.WARN, "Échec du listage des objets.", e);
			System.out.println(e);
		} finally {
			//fermetures(resultSet, preparedStatement, connection);
		}
	}
	
	private String ajouterCondition(String variable, String valeur, Boolean nouveau) {
		String reponse;
		if(nouveau) {
			reponse =" WHERE "+variable+" = "+valeur;
		}else {
			reponse =" AND "+variable+" = "+valeur;
		}	
		return reponse;
	}

	
	
}
