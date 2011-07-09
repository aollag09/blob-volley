package interfaceCS;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.text.Position;

import client.Pane;

/**
 * Classe qui va permettre de modï¿½liser les diffï¿½rentes trajectoire de la balle
 * 
 * Principe :
 * >> On garde en mï¿½moire la premiï¿½re position ou laposition d'un choc
 * >> En fonction des diffï¿½rentes forces appliquï¿½es ï¿½ la balle en ce point de choc on dï¿½termine le point juste aprï¿½s 
 * >> On peut alors savoir nouvelle vitesse initiale de la balle
 * >> On calcule tous les autres points en tenant compte simplement du poids et de cette vitesse initiale
 * >> On incrï¿½mente le compteur ï¿½ chaque nouveau point "libre"
 * >> On recommence la boucle si la balle touche un autre obstacle, 
 * >> on remet le compteur ï¿½ zï¿½ro
 * 
 * @author Amaury
 *
 */
public class Balle extends Mobile{

	/* Le singleton */
	public static Balle instance = new Balle(new PointSam(0,0));

	/* Constantes */
	public final static double BALLE_LARGEUR = 4*0.006;
	public final static double BALLE_HAUTEUR = 4*0.011;
	public static final double CONSTANTE_DE_GRAVITATION = 9;
	public static final double MASSE_BALLE = 0.5;
	public static final Color COULEUR_BALLE = Color.gray;
	public static final Color COULEUR_CONTOUR_BALLE = Color.black;

	/* Un compteur */
	/** Cet entier permet d'incrï¿½menter le temps pour calculer les trajectoires de la balle uniquement dans le cas
	 * ou elle est "libre"
	 * Il est remit ï¿½ zï¿½ro en cas de choque pour recalculer sa trajectoire 
	 * 
	 * Le temps dans le compteur est en !!!!!!!!!!!!! SECONDES !!!!!!!!!!!!!!!
	 */
	private double compteur = 0;

	/** Le point initiale du choc */
	private PointSam positionInitiale;
	/** La vitesse initiale du choc */
	private PointSam vitesseInitiale;


	/* Constructeur */
	private Balle(){
		super();
		this.positionInitiale = new PointSam(0,0);
	}

	private Balle(PointSam p){
		super(p);
		this.positionInitiale = new PointSam(0,0);
		this.vitesseInitiale = new PointSam(0.1,0);
	}

	public void paintBalle(Graphics g){
		g.setColor(COULEUR_BALLE);
		g.fillOval( (int) (Pane.width*this.getPosition().getX()), 
				(int) (Pane.height*this.getPosition().getY()),
				(int) (BALLE_LARGEUR*Pane.width),
				(int) (BALLE_HAUTEUR*Pane.height));
		g.setColor(COULEUR_CONTOUR_BALLE);
		g.drawOval( (int) (Pane.width*this.getPosition().getX()), 
				(int) (Pane.height*this.getPosition().getY()),
				(int) (BALLE_LARGEUR*Pane.width),
				(int) (BALLE_HAUTEUR*Pane.height));

	}

	/** Mï¿½thode appelï¿½ ï¿½ chaque delay pour recalculer la position de la balle */
	public void nextPosition(){
		this.setCompteur(this.compteur+((IServeur.DELAY+0.0)/1000));



		boolean hasTouched = false;

		/* On test ensuite si la balle risque de toucher un blob */
		if(super.getPosition().getY()>Blob.instanceServeur.getPosition().getY()-Blob.BLOB_BODY_HAUTEUR){
			/* Risque de toucher le blob serveur */
			if(Math.abs(super.getPosition().getX()-Blob.instanceServeur.getPosition().getX()) < Blob.BLOB_BODY_LARGEUR){
				/* La balle est juste au dessus de Blob */
				System.out.println("TEST");
				System.out.println(this.getVitesse());
				this.setAcceleration(new PointSam(0, 0));
				/* Mais le blob étant arrondi il n'est pas encore sur que la balle le touche */
				double vitX = this.getVitesseInitiale().getX();
				double vitY = -2.5;
				this.nouvelleVitesse(new PointSam(vitX, vitY));
				hasTouched = true;
			}




			/* Si elle ne touche finalement pas le Blob */
		}

		if(super.getPosition().getY()>Blob.instanceClient.getPosition().getY()-Blob.BLOB_BODY_HAUTEUR){
			/* Risque de toucher le blob client */
		}
		else{
			/* Aucun risque de toucher un blob */
		}


		/* Si la balle ne touche certes rien mais tombe en dessous de zero en Y: */
		if(! hasTouched){
			if((super.getPosition().getY()+this.BALLE_HAUTEUR) >= 1 ){
				super.setPosition(new PointSam(super.getPosition().getX(), 1-this.BALLE_HAUTEUR));
				super.setVitesse(new PointSam(0,0));
				super.setAcceleration(new PointSam(0,0));
			}else{

				/* On modifie la positon de la balle avec sa nouvelle position */
				super.nouvellePosition(this.tomber());

			}
		}else{
			/* On reinitialise les paramètres du choque */
			this.positionInitiale = super.getPosition();
			this.vitesseInitiale = super.getVitesse();
			this.compteur = 0;
		}





	}

	private PointSam  tomber(){
		/* On calcul ainsi les nouveaux coordonnï¿½es de la balle simplement en fonction du poids */
		double posX = this.positionInitiale.getX() + this.getVitesseInitiale().getX()*compteur;
		double posY = this.positionInitiale.getY() + 0.5*MASSE_BALLE*CONSTANTE_DE_GRAVITATION*compteur*compteur
				+ this.getVitesseInitiale().getY()*compteur;
		return new PointSam(posX, posY);
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
		return positionInitiale;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(PointSam ref) {
		this.positionInitiale = ref;
	}

	/**
	 * @return the vitesseInitiale
	 */
	public PointSam getVitesseInitiale() {
		return vitesseInitiale;
	}

	/**
	 * @param vitesseInitiale the vitesseInitiale to set
	 */
	public void setVitesseInitiale(PointSam vitesseInitiale) {
		this.vitesseInitiale = vitesseInitiale;
	}


}
