package interfaceCS;

import java.util.Stack;

/**
 * Classe qui va permettre de modéliser les différentes trajectoire de la balle
 * 
 * @author Amaury
 *
 */
public class Balle {
	
	/* Constantes */
	public static double TAILLE_BALLE = 0.10;
		
	/* Variables d'instances */
	/** La position courante de la balle */
	private PointSam position;
	/** Un vecteur de position de type LIFO qui va garder les 3 dernières position afin de déterminer
	 * la vitesse et l'accélération du blob */
	private Stack<PointSam> historique;
	/** La vitesse du blob */
	private double vitesse;
	/** L'accélération du blob */
	private double acceleration;
		
	
	public Balle(){
		
	}

	public PointSam getPosition() {
		return position;
	}

	public void setPosition(PointSam position) {
		this.position = position;
	}

	/**
	 * @return the historique
	 */
	public Stack<PointSam> getHistorique() {
		return historique;
	}

	/**
	 * @param historique the historique to set
	 */
	public void setHistorique(Stack<PointSam> historique) {
		this.historique = historique;
	}

	/**
	 * @return the vitesse
	 */
	public double getVitesse() {
		return vitesse;
	}

	/**
	 * @param vitesse the vitesse to set
	 */
	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * @return the acceleration
	 */
	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

}
