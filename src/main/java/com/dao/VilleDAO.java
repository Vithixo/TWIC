package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {

	public ArrayList<Ville> lister();
	
	public ArrayList<Ville> trouver(String code,
			String nom,
			String codePostal,
			String libelle,
			String ligne5,
			String latitude,
			String longitude);
	
	public void creer(Ville ville);
	
	public void supprimer(String id);
	
	
}
