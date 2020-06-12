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
	public ArrayList<Ville> getVilles(String info) {
		ArrayList<Ville> listeVille = new ArrayList<Ville>();
		
		if(info != null) {
			listeVille = villeDAOService.trouverCodePostal(info);
		}else {
			listeVille = villeDAOService.lister();
		}
		
		return listeVille;
	}

	@Override
	public void creerVille(Ville ville) {
		villeDAOService.creer(ville);
		
	}

}
