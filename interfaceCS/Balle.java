package interfaceCS;

import java.util.LinkedList;

import client.Pane;

/**
 * Classe qui va permettre de modéliser les différentes trajectoire de la balle
 * 
 * Principe :
 * >> On garde en mémoire la première position ou laposition d'un choc
 * >> En fonction des différentes forces appliquées à la balle en ce point de choc on détermine le point juste après 
 * >> On peut alors savoir nouvelle vitesse de la balle
 * >> On calcule tous les autres points en tenant compte simplement du poids et de cette vitesse initiale
 * >> On incrémente le compteur à chaque nouveau point "libre"
 * >> On recommence la boucle si la balle touche un autre obstacle, et on remet le compteur à zéro
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
	public static final double MASSE_BALLE = 0.05;

	/* Un compteur */
	/** Cet entier permet d'incrémenter le temps pour calculer les trajectoires de la balle uniquement dans le cas
	 * ou elle est "libre"
	 * Il est remit à zéro en cas de choque pour recalculer sa trajectoire 
	 * 
	 * Le temps dans le compteur est en !!!!!!!!!!!!! SECONDES !!!!!!!!!!!!!!!
	 */
	private double compteur = 0;
	
	/** Le point initiale du choc */
	private PointSam ref;
	
	
	/* Constructeur */
	private Balle(){
		super();
		this.ref = new PointSam(20,20);
	}
	
	private Balle(PointSam p){
		super(p);
		this.ref = new PointSam(20,20);
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
				double posX = this.ref.getX() + super.getVitesse().getX()*compteur;
				double posY = this.ref.getY() + 0.5*MASSE_BALLE*CONSTANTE_DE_GRAVITATION*compteur*compteur;
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

	/**
	 * @return the ref
	 */
	public PointSam getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(PointSam ref) {
		this.ref = ref;
	}


}
