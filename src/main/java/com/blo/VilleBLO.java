package com.blo;

import java.util.List;

import com.dto.Ville;


public interface VilleBLO {
	
	public List<Ville> getVilles(String code,
			String nom,
			String codePostal,
			String libelle,
			String ligne5,
			String latitude,
			String longitude);
	
	public void creerVilles(List<Ville> villes);
	
	public void supprimerVille(String id);
}
