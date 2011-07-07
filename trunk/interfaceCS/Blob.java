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
	public final static double BLOB_EYES_LARGEUR = 0.02;
	public final static double BLOB_EYES_HAUTEUR = 0.04;
	/** the link */
	public static String LINK_BLOB_SERVEUR ="blobServeur.png"; 
	public static String LINK_BLOB_CLIENT = "blobClient.png";
	/** bornes acc�l�ration */
	public static double MAX_ACCELERATION = 3;
	
	
	/** Le double singleton */
	public static Blob instanceServeur = new Blob(true);
	public static Blob instanceClient = new Blob(false);
	
	/* Les variables d'instance */
	/** Un boolean pour différencier le blob local du blob distant */
	private boolean isServeur;

	/* Le constructeur */
	private Blob(boolean isServeur){
		super();
		this.setServeur(isServeur);
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
				(int)(Pane.height - (int)(Blob.BLOB_BODY_HAUTEUR*Pane.height) ),
				(int)(Blob.BLOB_BODY_LARGEUR*Pane.width), 
				(int)(Blob.BLOB_BODY_HAUTEUR*Pane.height),null );
		
		/* On trace ensuite les yeux */
		g.setColor(Color.black);
		int signe = 1;
		if(!this.isServeur)
			signe = -1;
		int centre = 0;
		//g.drawOval(x, y, 6, 6)
		
		
	}
	
	public void nextPosition(int typeOrdre){

		switch(typeOrdre){
		
		case IServeur.ORDRE_RESTE : 
			/* On diminue par 2 la vitesse suivant l'axe des X */
			super.nouvelleVitesse(new PointSam(this.getVitesse().getX()/1.1, this.getVitesse().getY()));
			break;
			
		case IServeur.ORDRE_GAUCHE :
			/* On augmente l'acc�lar�ation vers la gauche */
			super.nouvelleAcceleration(new PointSam(
					Math.max(-Blob.MAX_ACCELERATION,this.getAcceleration().getX()-0.2),
					this.getAcceleration().getY()));		
			break;
			
		case IServeur.ORDRE_DROITE :
			/* On augmente l'acc�lar�ation vers la gauche */
			super.nouvelleAcceleration(new PointSam(
					Math.min(Blob.MAX_ACCELERATION,this.getAcceleration().getX()+0.2),
					this.getAcceleration().getY()));		
			break;
			
		case IServeur.ORDRE_SAUT :
			/* On augmente l'acc�l�ration vers le haut */

		}
		
		if(this.getPosition().getX()<0 || this.getPosition().getX()>(Pane.width/2)-Blob.BLOB_BODY_LARGEUR*Pane.width){
			/* On stoppe le blob en X */
			//super.setPositon(new PointSam(0, super.getPosition().getY()));
			super.nouvelleVitesse(new PointSam(-super.getVitesse().getX(), super.getVitesse().getY()));
			//super.setAcceleration(new PointSam(0,super.getAcceleration().getY()));
		}
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
