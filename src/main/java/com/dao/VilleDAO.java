package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDAO {

	public List<Ville> lister();
	
	public List<Ville> trouver(String code,
			String nom,
			String codePostal,
			String libelle,
			String ligne5,
			String latitude,
			String longitude);
	
	public void creer(Ville ville);
	
	public void supprimer(String id);
	
	
}
