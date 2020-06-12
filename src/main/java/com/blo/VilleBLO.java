package com.blo;

import java.util.ArrayList;

import com.dto.Ville;


public interface VilleBLO {
	
	public ArrayList<Ville> getVilles(String code,
			String nom,
			String codePostal,
			String libelle,
			String ligne5,
			String latitude,
			String longitude);
	
	public void creerVilles(ArrayList<Ville> villes);
}
