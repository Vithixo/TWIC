package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	private static final String CODE = "Code_commune_INSEE";
	private static final String NOM = "Nom_commune";
	private static final String CODE_POSTAL = "Code_postal";
	private static final String LIBELLE = "Libelle_acheminement";
	private static final String LIGNE5 = "Ligne_5";
	private static final String LATITUDE = "Latitude";
	private static final String LONGITUDE = "Longitude";

	private static final String SELECT_ALL = "SELECT * FROM ville_france";

	private static Logger logger = Logger.getLogger("log");

	private boolean premierChangement = true;

	@Override
	public List<Ville> lister() {
		Connection connection = JDBCConfiguration.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Ville> villes = new ArrayList<>();

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
			logger.log(Level.WARN, "Échec ", e);
		} finally {
			// fermeture des ressources utilisées
			fermetures(resultSet, preparedStatement, connection);
		}
		return villes;
	}

	@Override
	public List<Ville> trouver(String code, String nom, String codePostal, String libelle, String ligne5,
			String latitude, String longitude) {
		Connection connection = JDBCConfiguration.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Ville> villes = new ArrayList<>();
		String requete = SELECT_ALL;
		Boolean nouvelleCondition = true;
		if (code != null) {
			requete = requete + ajouterCondition(CODE, code, nouvelleCondition);
			nouvelleCondition = false;
		}
		if (nom != null) {
			requete = requete + ajouterCondition(NOM, "'" + nom + "'", nouvelleCondition);
			nouvelleCondition = false;
		}
		if (codePostal != null) {
			requete = requete + ajouterCondition(CODE_POSTAL, codePostal, nouvelleCondition);
			nouvelleCondition = false;
		}
		if (libelle != null) {
			requete = requete + ajouterCondition(LIBELLE, "'" + libelle + "'", nouvelleCondition);
			nouvelleCondition = false;
		}
		if (ligne5 != null) {
			requete = requete + ajouterCondition(LIGNE5, "'" + ligne5 + "'", nouvelleCondition);
			nouvelleCondition = false;
		}
		if (latitude != null) {
			requete = requete + ajouterCondition(LATITUDE, latitude, nouvelleCondition);
			nouvelleCondition = false;
		}
		if (longitude != null) {
			requete = requete + ajouterCondition(LONGITUDE, longitude, nouvelleCondition);
			nouvelleCondition = false;
		}

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
			logger.log(Level.WARN, "Échec ", e);
		} finally {
			// fermeture des ressources utilisées
			fermetures(resultSet, preparedStatement, connection);
		}
		return villes;
	}

	private String ajouterCondition(String variable, String valeur, Boolean nouveau) {
		String reponse;
		if (nouveau) {
			reponse = " WHERE " + variable + " = " + valeur;
		} else {
			reponse = " AND " + variable + " = " + valeur;
		}
		return reponse;
	}

	private Ville recupererVille(ResultSet resultSet) throws SQLException {
		Ville ville = new Ville();

		ville.setCode(resultSet.getInt(CODE));
		ville.setNom(resultSet.getString(NOM));
		ville.setCodePostale(resultSet.getInt(CODE_POSTAL));
		ville.setLibelle(resultSet.getString(LIBELLE));
		ville.setLigne5(resultSet.getString(LIGNE5));
		ville.setLatitude(resultSet.getDouble(LATITUDE));
		ville.setLongitude(resultSet.getDouble(LONGITUDE));

		return ville;
	}

	@Override
	public void changerVille(String code, String nom, String codePostal, String libelle, String ligne5, String latitude,
			String longitude) {
		String requete = "UPDATE ville_france SET ";

		requete = requete + ajouterChangement(NOM, nom);
		requete = requete + ajouterChangement(CODE_POSTAL, codePostal);
		requete = requete + ajouterChangement(LIBELLE, libelle);
		requete = requete + ajouterChangement(LIGNE5, ligne5);
		requete = requete + ajouterChangement(LATITUDE, latitude);
		requete = requete + ajouterChangement(LONGITUDE, longitude);
		requete = requete + " WHERE " + CODE + "=" + code;
		premierChangement = true;

		executerRequete(requete);

	}

	private String ajouterChangement(String variable, String valeur) {
		String reponse="";
		if (valeur != null) {
			if (premierChangement) {
				reponse = variable + " = '"+valeur+"'";
				premierChangement = false;
			} else {
				reponse = " , " + variable + " = '"+ valeur+"'";
			}
		}
		return reponse;
	}

	@Override
	public void creer(Ville ville) {
		String requete = "INSERT INTO ville_france VALUES('" + ville.getCode() + "','" + ville.getNom() + "','"
				+ ville.getCodePostale() + "','" + ville.getLibelle() + "','" + ville.getLigne5() + "','"
				+ ville.getLatitude() + "','" + ville.getLongitude() + "')";
		executerRequete(requete);
	}

	@Override
	public void supprimer(String id) {
		String requete = "DELETE FROM ville_france WHERE " + CODE + "=" + id;
		executerRequete(requete);
	}

	private void executerRequete(String requete) {
		Connection connection = JDBCConfiguration.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(requete);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			logger.log(Level.WARN, "Échec de la requete.", e);
		} finally {
			// fermeture des ressources utilisées
			fermetures(preparedStatement, connection);
		}

	}

	// ########################################################################################################
	// # Methodes pour la fermeture des ressources #
	// ########################################################################################################

	/**
	 * Ferme le resultset.
	 * 
	 * @param resultSet le resultSet à fermer.
	 */
	public static void fermeture(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture du ResultSet : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Ferme le statement.
	 * 
	 * @param statement le statement à fermer.
	 */
	public static void fermeture(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture du Statement : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Ferme la connection.
	 * 
	 * @param connection la connection à fermer.
	 */
	public static void fermeture(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture de la connexion : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Ferme le statement et la connection.
	 * 
	 * @param statement  le statement à fermer.
	 * @param connection la connection à fermer.
	 */
	public static void fermetures(Statement statement, Connection connection) {
		fermeture(statement);
		fermeture(connection);
	}

	/**
	 * Ferme le resultSet, le statement et la connection.
	 * 
	 * @param resultSet  le resultSet à fermer.
	 * @param statement  le statement à fermer.
	 * @param connection la connection à fermer.
	 */
	public static void fermetures(ResultSet resultSet, Statement statement, Connection connection) {
		fermeture(resultSet);
		fermeture(statement);
		fermeture(connection);
	}

}
