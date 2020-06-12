package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;


@Service
public class VilleBLOImpl implements VilleBLO {
	
	@Autowired
	VilleDAO villeDAOService;

	@Override
	public ArrayList<Ville> getVilles(String code,String nom,String codePostal,String libelle,
			String ligne5,String latitude,String longitude) {
		ArrayList<Ville> listeVille = new ArrayList<Ville>();
		
		if(code==null && nom==null && codePostal==null && libelle==null &&
				ligne5==null && latitude==null && longitude==null ) {
			listeVille = villeDAOService.lister();
			
		}else {
			listeVille = villeDAOService.trouver(code,nom,codePostal,libelle,
					ligne5,latitude,longitude);
		}
		
		return listeVille;
	}

	@Override
	public void creerVilles(ArrayList<Ville> villes) {
		for (Ville ville : villes) {
			villeDAOService.creer(ville);
		}
	}

}
