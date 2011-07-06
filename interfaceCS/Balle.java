package interfaceCS;

import java.util.LinkedList;

/**
 * Classe qui va permettre de mod�liser les diff�rentes trajectoire de la balle
 * 
 * @author Amaury
 *
 */
public class Balle extends Mobile {
	
	/* Constantes */
	public static double TAILLE_BALLE = 0.10;
		
	
	/* Pattern singleton*/
	public static Balle balle = new Balle();
	
	private Balle(){
		super();
	}


}
