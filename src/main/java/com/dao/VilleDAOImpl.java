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
	
	
	@Override
	public ArrayList<Ville> lister() {
		Connection connection = JDBCConfiguration.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Ville> villes = new ArrayList<Ville>();
		
		try {
			// création d'une connexion grâce à la DAOFactory placée en attribut de la
			preparedStatement = connection.prepareStatement("SELECT * FROM ville_france");
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
	public ArrayList<Ville> trouverCodePostal(String codePostal) {
		Connection connection = JDBCConfiguration.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Ville> villes = new ArrayList<Ville>();
		
		try {
			// création d'une connexion grâce à la DAOFactory placée en attribut de la
			preparedStatement = connection.prepareStatement("SELECT * FROM ville_france WHERE ville_france.Code_postal="+codePostal);
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
		
		ville.setCode(resultSet.getInt("Code_commune_INSEE"));
		ville.setNom(resultSet.getString("Nom_commune"));
		ville.setCodePostale(resultSet.getInt("Code_postal"));
		ville.setLibelle(resultSet.getString("Libelle_acheminement"));
		ville.setLigne5(resultSet.getString("Ligne_5"));
		ville.setLatitude(resultSet.getLong("Latitude"));
		ville.setLongitude(resultSet.getLong("Longitude"));
		
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
		
		ResultSet results = null;
		try  {
		   Statement stmt = connection.createStatement();
		   int nbMaj = stmt.executeUpdate(requete);

		} catch (SQLException e) {
			//logger.log(Level.WARN, "Échec du listage des objets.", e);
			System.out.println(e);
		} finally {
			//fermetures(resultSet, preparedStatement, connection);
		}
	}

	
	
}
