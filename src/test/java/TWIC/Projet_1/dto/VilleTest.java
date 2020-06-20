package TWIC.Projet_1.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dto.Ville;

public class VilleTest {
	
	private static final int INT_TEST = 1;
	private static final String STRING_TEST = "test";
	private static final double DOUBLE_TEST = 4.2;
	
	private static final double DELTA = 1e-15;
	
	/**
	 * Test des gettes et des setters de la classe Ville.
	 */
	
	@Test
	public void testGetSetCode() {
		final Ville ville = new Ville();
		ville.setCode(INT_TEST);
		assertEquals("le code n'est pas bon",INT_TEST, ville.getCode());
	}
	
	@Test
	public void testGetSetNom() {
		final Ville ville = new Ville();
		ville.setNom(STRING_TEST);
		assertEquals("le nom n'est pas bon",STRING_TEST, ville.getNom());
	}
	
	@Test
	public void testGetSetCodePostal() {
		final Ville ville = new Ville();
		ville.setCodePostale(INT_TEST);
		assertEquals("le codePostal n'est pas bon",INT_TEST, ville.getCodePostale());
	}
	
	@Test
	public void testGetSetLibelle() {
		final Ville ville = new Ville();
		ville.setLibelle(STRING_TEST);
		assertEquals("le libelle n'est pas bon",STRING_TEST, ville.getLibelle());
	}
	
	@Test
	public void testGetSetLigne5() {
		final Ville ville = new Ville();
		ville.setLigne5(STRING_TEST);
		assertEquals("la ligne5 n'est pas bonne",STRING_TEST, ville.getLigne5());
	}
	
	@Test
	public void testGetSetLatitude() {
		final Ville ville = new Ville();
		ville.setLatitude(DOUBLE_TEST);
		assertEquals("la latitude n'est pas bonne",DOUBLE_TEST, ville.getLatitude(),DELTA);
	}
	
	@Test
	public void testGetSetLongitude() {
		final Ville ville = new Ville();
		ville.setLongitude(DOUBLE_TEST);
		assertEquals("le longitude n'est pas bonne",DOUBLE_TEST, ville.getLongitude(),DELTA);
	}
	
	@Test
	public void testToString() {
		final Ville ville = new Ville();
		ville.setCode(INT_TEST);
		ville.setNom(STRING_TEST);
		ville.setCodePostale(INT_TEST);
		ville.setLibelle(STRING_TEST);
		ville.setLigne5(STRING_TEST);
		ville.setLatitude(DOUBLE_TEST);
		ville.setLongitude(DOUBLE_TEST);
		String reponse = "Ville [code=" + INT_TEST + ", nom=" + STRING_TEST + ", codePostale=" + INT_TEST + ", libelle=" + STRING_TEST
				+ ", ligne5=" + STRING_TEST + ", latitude=" + DOUBLE_TEST + ", longitude=" + DOUBLE_TEST + "]";
		assertEquals("le longitude n'est pas bonne",reponse, ville.toString());
	}
	
	
}
