package interfaceCS;

import java.util.LinkedList;

import client.Pane;

/**
 * Classe qui va permettre de modéliser les différentes trajectoire de la balle
 * 
 * @author Amaury
 *
 */
public class Balle extends Mobile{
	
	/* Le singleton */
	public static Balle instance = new Balle(new PointSam(300,300));

	/* Constantes */
	public static final double TAILLE_BALLE = 0.10;
	public static final double CONSTANTE_DE_GRAVITATION = 9;
	public static final double MASSE_BALLE = 0.01;

	/* Un compteur */
	/** Cet entier permet d'incrémenter le temps pour calculer les trajectoires de la balle uniquement dans le cas
	 * ou elle est "libre"
	 * Il est remit à zéro en cas de choque pour recalculer sa trajectoire 
	 * 
	 * Le temps dans le compteur est en !!!!!!!!!!!!! SECONDES !!!!!!!!!!!!!!!
	 */
	private double compteur = 0;
	
	
	/* Constructeur */
	private Balle(){
		super();
	}
	
	private Balle(PointSam p){
		super(p);
	}

	/** Méthode appelé à chaque delay pour recalculer la position de la balle */
	public void nextPosition(){
		this.setCompteur(this.compteur+((IServeur.DELAY+0.0)/1000));
		PointSam newPosition = new PointSam();

		/* On test dans un premier temps si la balle risque de toucher un blob */
		if(super.getPosition().getY()<Blob.instanceServeur.getPosition().getY()-Blob.BLOB_BODY_HAUTEUR){
			/* Risque de toucher le blob serveur */

		}
		else{
			if(super.getPosition().getY()<Blob.instanceClient.getPosition().getY()-Blob.BLOB_BODY_HAUTEUR){
				/* Risque de toucher le blob client */
				
			}
			else{
				/* Aucun risque de toucher un blob */
				/* On calcul ainsi les nouveaux coordonnées de la balle simplement en fonction du poids */
				double posX = super.getPosition().getX() + super.getVitesse().getX()*this.compteur;
				double posY = super.getPosition().getY() + 0.5*MASSE_BALLE*CONSTANTE_DE_GRAVITATION*(IServeur.DELAY)*this.compteur;
				newPosition = new PointSam(posX, posY);
			}
		}
		
		
		/* On modifie la positon de la balle avec sa nouvelle position */
		super.setPosition(newPosition);

	}

	public double getCompteur() {
		return compteur;
	}

	public void setCompteur(double compteur) {
		this.compteur = compteur;
	}


}
