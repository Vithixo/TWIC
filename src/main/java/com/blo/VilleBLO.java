package com.blo;

import java.util.ArrayList;

import com.dto.Ville;


public interface VilleBLO {
	
	public ArrayList<Ville> getVilles(String info);
	
	public void creerVille(Ville ville);
}
