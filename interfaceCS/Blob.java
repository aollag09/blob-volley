package interfaceCS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Stack;

import javax.swing.ImageIcon;

import client.Pane;



/**
 * 
 * La classe qui va permettre de creer les deux blob
 * C'est un double singleton 
 * @author Amaury
 */
public class Blob {
	
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
	
	/** Le double singleton */
	public static Blob blobServeur = new Blob(true);
	public static Blob blobClient = new Blob(false);
	
	/* Les variables d'instance */
	/** Un pourcentage de la largeur pour indiquer la position du blob */
	private PointSam position;
	/** Un boolean pour diffÃ©rencier le blob local du blob distant */
	private boolean isServeur;
	/** Un vecteur de position de type LIFO qui va garder les 3 dernières position afin de déterminer
	 * la vitesse et l'accélération du blob */
	private Stack<PointSam> historique;
	/** La vitesse du blob */
	private double vitesse;
	/** L'accélération du blob */
	private double acceleration;
		
	/* Le constructeur */
	private Blob(boolean isMe){
		this.position = new PointSam();
		this.setIsServeur(isMe);
		this.vitesse = 0;
		this.acceleration = 0;
		this.historique.add(new PointSam());
		
	}
	
	
	/** MÃ©thode permettant de tracer le blob dans le graphque passÃ© en paramÃ¨tre */
	public void paintBlob(Graphics g){
		
		/* On choisie la couleur de dessin en fonction du blob Ã  dÃ©ssiner */
		/* On trace dans un premier temps le body */
		String link = LINK_BLOB_CLIENT;
		if(this.isServeur)
			link = LINK_BLOB_SERVEUR;
		ImageIcon img = new ImageIcon(link);
		
		
		g.drawImage (img.getImage(),
				(int)(Pane.width*position.getX()),
				(int)(Pane.height - (int)(Blob.BLOB_BODY_HAUTEUR*Pane.height) ),
				(int)(Blob.BLOB_BODY_LARGEUR*Pane.width), 
				(int)(Blob.BLOB_BODY_HAUTEUR*Pane.height),null );
		
		/* On trace ensuite les yeux */
		/*g.setColor(Color.white);
		int signe = 1;
		if(!this.isServeur)
			signe = -1;
		g.fillOval((int) (position.getX()+ signe*BLOB_BODY_LARGEUR/4),
				Pane.height - (int)(Blob.BLOB_BODY_HAUTEUR*Pane.height)/4, 
				(int)(Blob.BLOB_EYES_LARGEUR*Pane.width), 
				(int)(Blob.BLOB_EYES_HAUTEUR*Pane.height) );
		
	*/
		
	}


	public PointSam getPosition() {
		return position;
	}


	public void setPosition(PointSam p) {
		this.position = p;
	}


	public boolean isServeur() {
		return isServeur;
	}


	public void setIsServeur(boolean isMe) {
		this.isServeur = isMe;
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


	/**
	 * @param isServeur the isServeur to set
	 */
	public void setServeur(boolean isServeur) {
		this.isServeur = isServeur;
	}
	
	
}
