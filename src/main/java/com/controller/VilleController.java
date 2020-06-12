package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;


@RestController
//@RequestMapping("/path")
class VilleController {

	@Autowired
	private VilleBLO villeService;

	// Methode GET
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> appelGet(@RequestParam(required = false,value = "code") String code,
			@RequestParam(required = false,value = "nom") String nom,
			@RequestParam(required = false,value = "codePostal") String codePostal,
			@RequestParam(required = false,value = "libelle") String libelle,
			@RequestParam(required = false,value = "ligne5") String ligne5,
			@RequestParam(required = false,value = "latitude") String latitude,
			@RequestParam(required = false,value = "longitude") String longitude) {
		System.out.println("Appel GET");
		ArrayList<Ville> listeVilles = new ArrayList<Ville>();
		listeVilles = villeService.getVilles(code,nom,codePostal,libelle,ligne5,latitude,longitude);

		return listeVilles;
	}
	
	// Methode POST
	@RequestMapping(value = "/ville", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost(@RequestBody ArrayList<Ville> villes) {
		System.out.println("Appel POST");
		villeService.creerVilles(villes);
	}	
}
