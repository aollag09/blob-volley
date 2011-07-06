package interfaceCS;

import java.awt.Point;

/**
 * Interface regroupant les différents connexions possibles au serveur, les fonctions sont générique afin que, quelque soit le type de serveur, le transfert de données se fasse bien.
 * @author pyrrha
 *
 */
public interface IServeur {
	
	/**
	 * Constantes qui définissent le type du serveur.
	 */
	public static final int SERVEUR_LOCAL = 0, SERVEUR_DISTANT = 1;
	
	/**
	 * Constantes qui définissent la positions des différentes données dans les tableaux échangés.
	 */
	public static final int BALLE = 0, JOUEUR_LOCAL = 1, JOUEUR_DISTANT = 2; 

	/**
	 * Constantes qui définissent les différents ordre des controles du joueur : gauche, immmobile, droite
	 */
	public static final int ORDRE_GAUCHE = -1, ORDRE_RESTE = 0, ORDRE_DROITE = 1; 
	
	/**
	 * Envoie l'ordre que fait l'utilisateur.
	 * @param ordre
	 */
	public void envoyerDonnees(int ordre);
	
	/**
	 * 
	 * @return le type de serveur (local, distant)
	 */
	public int getTypeServeur();
	
	/**
	 * 
	 * @return les coordonnées des différents éléments
	 */
	public Point[] recupererDonnees();
	
}
