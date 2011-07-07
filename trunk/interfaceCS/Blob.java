package interfaceCS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import client.Pane;



/**
 * 
 * La classe qui va permettre de creer les deux blob
 * C'est un double singleton 
 * @author Amaury
 */
public class Blob extends Mobile {

	/* Les constantes */
	/** the body */
	public final static double BLOB_BODY_LARGEUR = 0.10;
	public final static double BLOB_BODY_HAUTEUR = 0.09;
	/** the eyes */
	public final static double BLOB_EYES_LARGEUR = 0.006;
	public final static double BLOB_EYES_HAUTEUR = 0.011;
	/** the link */
	public static String LINK_BLOB_SERVEUR ="blobServeur.png"; 
	public static String LINK_BLOB_CLIENT = "blobClient.png";
	/** bornes acc�l�ration */
	public static double MAX_ACCELERATION = 1;
	public static double SAUT_ACCELERATION = 4;


	/** Le double singleton */
	public static Blob instanceServeur = new Blob(true, new PointSam(0.0,1.0));
	public static Blob instanceClient = new Blob(false, new PointSam(0.9,1.0));

	/* Les variables d'instance */
	/** Un boolean pour différencier le blob local du blob distant */
	private boolean isServeur;
	/** Un boolean pour savoir si le blob est en train de sauter */
	private boolean isJumping;

	/* Le constructeur */
	private Blob(boolean isServeur, PointSam p){
		super(p);
		this.setServeur(isServeur);
		this.isJumping = false;
	}


	/** Méthode permettant de tracer le blob dans le graphque passé en paramètre */
	public void paintBlob(Graphics g){

		/* On choisie la couleur de dessin en fonction du blob à déssiner */
		/* On trace dans un premier temps le body */
		String link = LINK_BLOB_CLIENT;
		if(this.isServeur)
			link = LINK_BLOB_SERVEUR;
		ImageIcon img = new ImageIcon(link);


		g.drawImage (img.getImage(),
				(int)(Pane.width*super.getPosition().getX()),
				(int)(Pane.height*super.getPosition().getY() - (int)(Blob.BLOB_BODY_HAUTEUR*Pane.height)),
				(int)(Blob.BLOB_BODY_LARGEUR*Pane.width), 
				(int)(Blob.BLOB_BODY_HAUTEUR*Pane.height),null );

		/* On trace ensuite les yeux */
		g.setColor(Color.black);
		Point centre = new Point(
				(int)(Pane.width*(super.getPosition().getX()+Blob.BLOB_BODY_LARGEUR*((this.isServeur)?(92-21):21)/92)),
				(int)(Pane.height*(super.getPosition().getY() + (19-37)*Blob.BLOB_BODY_HAUTEUR/37))
				);
		g.fillOval((int)(centre.x-Blob.BLOB_EYES_LARGEUR*Pane.width/2),
				(int)(centre.y-Blob.BLOB_EYES_HAUTEUR*Pane.height/2),
				(int)(Blob.BLOB_EYES_LARGEUR*Pane.width),
				(int)(Blob.BLOB_EYES_HAUTEUR*Pane.height)
				);


	}

	public void nextPosition(int typeOrdre){

		switch(typeOrdre){

		case IServeur.ORDRE_RESTE : 
			/* On diminue par 2 la vitesse suivant l'axe des X */
			super.nouvelleVitesse(new PointSam(this.getVitesse().getX()/1.1, this.getVitesse().getY()));
			break;

		case IServeur.ORDRE_GAUCHE :
			/* On augmente l'acc�lar�ation vers la gauche */
			if(this.getPosition().getX()>0){
				super.nouvelleAcceleration(new PointSam(
						Math.max(-Blob.MAX_ACCELERATION,this.getAcceleration().getX()-0.2),
						this.getAcceleration().getY()));	
			}
			break;

		case IServeur.ORDRE_DROITE :
			if((this.getPosition().getX()+ Blob.BLOB_BODY_LARGEUR)*Pane.width <((Pane.width/2 - Pane.width/100)) ){
				/* On augmente l'acc�lar�ation vers la gauche */
				super.nouvelleAcceleration(new PointSam(
						Math.min(Blob.MAX_ACCELERATION,this.getAcceleration().getX()+0.2),
						this.getAcceleration().getY()));
			}
			break;

		case IServeur.ORDRE_SAUT :
			/* On augmente l'acc�l�ration vers le haut */
			if(!this.isJumping){
				this.isJumping = true;
				super.nouvelleAcceleration(new PointSam(super.getAcceleration().getX(), this.SAUT_ACCELERATION));
			}
		}

		if(this.getPosition().getX()*Pane.width<0 ){
			/* On stoppe le blob en X */
			this.setPositon(new PointSam(0, this.getPosition().getY()));
			super.nouvelleVitesse(new PointSam(Math.abs(super.getVitesse().getX())/4, super.getVitesse().getY()));
			super.setAcceleration(new PointSam(0, super.getAcceleration().getY()));
		}
		if((this.getPosition().getX() + Blob.BLOB_BODY_LARGEUR)*Pane.width>(int) (Pane.width/2 - Pane.width/100)){
			this.setPositon(new PointSam( ((Pane.width/2 - Pane.width/100)) /  Pane.width
					, this.getPosition().getY()));
			super.nouvelleVitesse(new PointSam(-Math.abs(super.getVitesse().getX())/4, super.getVitesse().getY()));
			super.setAcceleration(new PointSam(0, super.getAcceleration().getY()));
		}
	}


	/**
	 * @return the isJumping
	 */
	public boolean isJumping() {
		return isJumping;
	}


	/**
	 * @param isJumping the isJumping to set
	 */
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}


	/**
	 * @return the isServeur
	 */
	public boolean isServeur() {
		return isServeur;
	}


	/**
	 * @param isServeur the isServeur to set
	 */
	public void setServeur(boolean isServeur) {
		this.isServeur = isServeur;
	}


}
