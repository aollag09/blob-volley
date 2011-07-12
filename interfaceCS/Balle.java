package interfaceCS;

import java.awt.Color;
import java.awt.Graphics;

import client.Main;
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
	public static final double MASSE_BALLE = 0.4;
	public static final double VITESSE_REBOND = -2.3;
	public static final Color COULEUR_BALLE = Color.gray;
	public static final Color COULEUR_CONTOUR_BALLE = Color.black;
	public static final double COEFF_DIMINUTION_VITESSE_LATERALE = 4;
	public static final double COEFF_PRISE_EN_COMPTE_ANCIENNE_VITESSE_LATERAL = 0.8;
	public static final double COMPTEUR_AVANT_DEBUT_POINT = 100;
	public static final double VITESSE_INITIALE_LATERALE = 0.1;

	/* Un compteur */
	/** Cet entier permet d'incrï¿½menter le temps pour calculer les trajectoires de la balle uniquement dans le cas
	 * ou elle est "libre"
	 * Il est remit ï¿½ zï¿½ro en cas de choque pour recalculer sa trajectoire 
	 * 
	 * Le temps dans le compteur est en !!!!!!!!!!!!! SECONDES !!!!!!!!!!!!!!!
	 */
	private double compteur = 0;

	/**
	 * Le compteur avant le début de la partie
	 */
	private int compteurDebut = 0;

	/** Un compteur avant le début du point */

	/** Le point initiale du choc */
	private PointSam positionInitiale;
	/** La vitesse initiale du choc */
	private PointSam vitesseInitiale;

	/** Un boolean pour savoir si la balle a eu une colision */ 
	private boolean hasTouched;


	/* Constructeur */
	private Balle(){
		super();
		this.positionInitiale = new PointSam(0,0);
		this.vitesseInitiale = new PointSam(VITESSE_INITIALE_LATERALE,0);
	}

	private Balle(PointSam p){
		super(p);
		this.positionInitiale = new PointSam(0,0);
		this.vitesseInitiale = new PointSam(VITESSE_INITIALE_LATERALE,0);
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
		if(compteurDebut < Balle.COMPTEUR_AVANT_DEBUT_POINT)
			compteurDebut++;
		else{
			this.setCompteur(this.compteur+((IServeur.DELAY+0.0)/1000));

			/* Un boolean pour savoir si la balle a touche quelque chose lors de son trajet */
			hasTouched = false;

			/* On test ensuite si la balle risque de toucher un blob */

			/* Risque de toucher le blob serveur */
			if(super.getPosition().getX()+Balle.BALLE_LARGEUR-Blob.instanceServeur.getPosition().getX()>0
					&& super.getPosition().getX()-Blob.instanceServeur.getPosition().getX()< Blob.BLOB_BODY_LARGEUR){
				if(isUnderServeur()){
					this.rebondirServeur();
				}
			}

			if(super.getPosition().getX()+Balle.BALLE_LARGEUR-Blob.instanceClient.getPosition().getX()>0
					&& super.getPosition().getX()-Blob.instanceClient.getPosition().getX()< Blob.BLOB_BODY_LARGEUR){
				if(isUnderClient()){
					this.rebondirClient();
				}
			}


			/* Risque de toucher le filet */
			if(super.getPosition().getY() > 7.0/8.0){
				if(super.getPosition().getX()+Balle.BALLE_LARGEUR > 0.5-(1.0/100.0)
						&& super.getPosition().getX()<0.5-(1.0/100.0)){
					/* La balle est dans le filet on étudie alors les différents cas : */
					if(super.getPosition().getY()>7.2/8.0){
						if(super.getPosition().getX()+BALLE_LARGEUR/2<0.5){
							this.nouvelleVitesse(new PointSam(-super.getVitesse().getX(),super.getVitesse().getY()));
							hasTouched = true;
						}
						else{
							this.nouvelleVitesse(new PointSam(-super.getVitesse().getX(),super.getVitesse().getY()));
							hasTouched = true;
						}
					}else{
						this.nouvelleVitesse(new PointSam(super.getVitesse().getX(),-super.getVitesse().getY()));
						hasTouched = true;
					}
					if((super.getPosition().getY()+Balle.BALLE_HAUTEUR) >= 1 ){
						/* La balle est tombée par terre */
						super.setPosition(new PointSam(super.getPosition().getX(), 1-Balle.BALLE_HAUTEUR));
						super.setVitesse(new PointSam(0,0));
						super.setAcceleration(new PointSam(0,0));
						/* On donne un point au gagnant en fonction de la position de la balle */
						if(super.getPosition().getX()<0.5)
							Main.partieEnCours.clientMarque();
						else
							Main.partieEnCours.serveurMarque();

						if((super.getPosition().getY()+Balle.BALLE_HAUTEUR) == 1 ){
							try{
								Thread.sleep(50);
							}catch (Exception e) {}
						}

						/* On relance la balle */
						this.repositionnerBalle();
					}

				}

			}


			/* Risque de toucher les murs */
			if(super.getPosition().getX()<0 || super.getPosition().getX()+Balle.BALLE_LARGEUR>1){
				super.nouvelleVitesse(new PointSam(-super.getVitesse().getX(),super.getVitesse().getY()));
				hasTouched = true;
			}



			////////////////////////////////////
			///// La balle na rien touché //////
			////////////////////////////////////

			/* Si la balle ne touche certes rien mais tombe en dessous de zero en Y: */
			if(! hasTouched){
				if((super.getPosition().getY()+Balle.BALLE_HAUTEUR) >= 1 ){
					/* La balle est tombée par terre */
					super.setPosition(new PointSam(super.getPosition().getX(), 1-Balle.BALLE_HAUTEUR));
					super.setVitesse(new PointSam(0,0));
					super.setAcceleration(new PointSam(0,0));
					/* On donne un point au gagnant en fonction de la position de la balle */
					if(super.getPosition().getX()<0.5)
						Main.partieEnCours.clientMarque();
					else
						Main.partieEnCours.serveurMarque();

					if((super.getPosition().getY()+Balle.BALLE_HAUTEUR) == 1 ){
						try{
							Thread.sleep(50);
						}catch (Exception e) {}
					}

					/* On relance la balle */
					this.repositionnerBalle();

				}else{
					/* On modifie la positon de la balle avec sa nouvelle position */
					super.nouvellePosition(this.tomber());
					/* On regarde si la balle ne va pas traverser un mur ... */
					if(isUnderServeur()){
						this.rebondirServeur();
						/* On reinitialise les paramètres du choque */
						this.positionInitiale = super.getPosition();
						this.vitesseInitiale = super.getVitesse();
						this.compteur = 0;
					}

				}
			}else{
				/* On reinitialise les paramètres du choque */
				this.positionInitiale = super.getPosition();
				this.vitesseInitiale = super.getVitesse();
				this.compteur = 0;
			}
		}
	}

	/** Méthode appelée à chaque fin de point pour relancer la balle */
	private void repositionnerBalle() {
		this.compteurDebut = 0;
		if(Main.partieEnCours.getNumeroTour() % 2 == 0){
			/* Au tour du serveur de servir */
			super.setPosition(new PointSam(0,0));
			this.positionInitiale = new PointSam(0,0);
			this.vitesseInitiale = new PointSam(VITESSE_INITIALE_LATERALE,0);
			this.compteur = 0;
			hasTouched = true;
		}else{
			/* Au tour du Client de servir */
			super.setPosition(new PointSam(1-Balle.BALLE_LARGEUR,0));
			this.positionInitiale = new PointSam(1-Balle.BALLE_LARGEUR,0);
			this.vitesseInitiale = new PointSam(-VITESSE_INITIALE_LATERALE,0);
			this.compteur = 0;
			hasTouched = true;
		}


	}

	private boolean isUnderClient(){
		/* Le blod est en forme de demi cercle, on regarde donc si la balle et en dessous 
		 Le centre le la balle peut circuler sur le cercle de centre : le centre de blob
		 et de rayon rayon du blob + rayon de la balle */
		double rayonX = Blob.BLOB_BODY_LARGEUR/2 + Balle.BALLE_LARGEUR/2;
		double cos = ((Balle.BALLE_LARGEUR/2+super.getPosition().getX()
				-(Blob.instanceClient.getPosition().getX()+Blob.BLOB_BODY_LARGEUR/2)))/rayonX;
		/* Calculons le point appartenant au cercle surface ayant cette position en X */
		double angle = Math.acos(cos);
		double sin = Math.sin(angle);
		double hauteurCercle = Blob.instanceClient.getPosition().getY() - (sin*Blob.BLOB_BODY_HAUTEUR); 
		return (super.getPosition().getY() > hauteurCercle);
	}

	private void rebondirClient(){
		double rayonX = Blob.BLOB_BODY_LARGEUR/2 + Balle.BALLE_LARGEUR/2;
		double cos = ((Balle.BALLE_LARGEUR/2+super.getPosition().getX()
				-(Blob.instanceClient.getPosition().getX()+Blob.BLOB_BODY_LARGEUR/2)))/rayonX;
		/* Calculons le point appartenant au cercle surface ayant cette position en X */
		double angle = Math.acos(cos);
		double sin = Math.sin(angle);
		/* La balle doit donc rebondir sur le Blob !!*/
		this.setAcceleration(new PointSam(0, 0));
		double vitX = -VITESSE_REBOND*cos/Balle.COEFF_DIMINUTION_VITESSE_LATERALE
				+Balle.COEFF_PRISE_EN_COMPTE_ANCIENNE_VITESSE_LATERAL*super.getVitesse().getX();
		double vitY = VITESSE_REBOND*sin;
		this.nouvelleVitesse(new PointSam(vitX, vitY));
		hasTouched = true;
	}

	private boolean isUnderServeur(){
		/* Le blod est en forme de demi cercle, on regarde donc si la balle et en dessous 
		 Le centre le la balle peut circuler sur le cercle de centre : le centre de blob
		 et de rayon rayon du blob + rayon de la balle */
		double rayonX = Blob.BLOB_BODY_LARGEUR/2 + Balle.BALLE_LARGEUR/2;
		double cos = ((Balle.BALLE_LARGEUR/2+super.getPosition().getX()
				-(Blob.instanceServeur.getPosition().getX()+Blob.BLOB_BODY_LARGEUR/2)))/rayonX;
		/* Calculons le point appartenant au cercle surface ayant cette position en X */
		double angle = Math.acos(cos);
		double sin = Math.sin(angle);
		double hauteurCercle = Blob.instanceServeur.getPosition().getY() - (sin*Blob.BLOB_BODY_HAUTEUR); 
		return (super.getPosition().getY() > hauteurCercle);
	}

	private void rebondirServeur(){
		double rayonX = Blob.BLOB_BODY_LARGEUR/2 + Balle.BALLE_LARGEUR/2;
		double cos = ((Balle.BALLE_LARGEUR/2+super.getPosition().getX()-(Blob.instanceServeur.getPosition().getX()+Blob.BLOB_BODY_LARGEUR/2)))/rayonX;///Blob.BLOB_BODY_LARGEUR/2;
		/* Calculons le point appartenant au cercle surface ayant cette position en X */
		double angle = Math.acos(cos);
		double sin = Math.sin(angle);
		/* La balle doit donc rebondir sur le Blob !!*/
		this.setAcceleration(new PointSam(0, 0));
		double vitX = -VITESSE_REBOND*cos/Balle.COEFF_DIMINUTION_VITESSE_LATERALE
				+ Balle.COEFF_PRISE_EN_COMPTE_ANCIENNE_VITESSE_LATERAL*super.getVitesse().getX() ;
		double vitY = VITESSE_REBOND*sin;
		this.nouvelleVitesse(new PointSam(vitX, vitY));
		hasTouched = true;
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

	/**
	 * @return the compteurDebut
	 */
	public int getCompteurDebut() {
		return compteurDebut;
	}

	/**
	 * @param compteurDebut the compteurDebut to set
	 */
	public void setCompteurDebut(int compteurDebut) {
		this.compteurDebut = compteurDebut;
	}


}
