package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {

	public ArrayList<Ville> lister();
	
	public ArrayList<Ville> trouverCodePostal(String codePostal);
	
	public void creer(Ville ville);
	
	
}
