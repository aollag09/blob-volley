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
	public static final double MASSE_BALLE = 1;

	
	/* Constructeur */
	private Balle(){
		super();
	}
	
	private Balle(PointSam p){
		super(p);
	}

	/** Méthode appelé à chaque delay pour recalculer la position de la balle */
	public void nextPosition(){
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
				double posX = super.getPosition().getX() + super.getVitesse().getX()*IServeur.DELAY/1000;
				double posY = super.getPosition().getY() + 0.5*MASSE_BALLE*CONSTANTE_DE_GRAVITATION*(IServeur.DELAY)*(IServeur.DELAY)/1000;
				newPosition = new PointSam(posX, posY);
			}
		}
		
		
		/* On modifie la positon de la balle avec sa nouvelle position */
		super.setPosition(newPosition);

	}


}
