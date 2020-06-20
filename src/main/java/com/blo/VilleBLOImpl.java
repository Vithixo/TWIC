package com.blo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;


@Service
public class VilleBLOImpl implements VilleBLO {
	
	@Autowired
	VilleDAO villeDAOService;

	@Override
	public List<Ville> getVilles(String code,String nom,String codePostal,String libelle,
			String ligne5,String latitude,String longitude) {
		List<Ville> listeVille = new ArrayList<>();
		
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
	public void creerVilles(List<Ville> villes) {
		for (Ville ville : villes) {
			villeDAOService.creer(ville);
		}
	}
	
	@Override
	public void supprimerVille(String id) {
		villeDAOService.supprimer(id);
	}

}
