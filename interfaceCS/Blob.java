package interfaceCS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import client.Pane;



/**
 * 
 * La classe qui va permettre de creer les deux blob
 * C'est un double singleton 
 * @author Amaury
 *
 */
public class Blob {
	
	/* Les constantes */
	
	/** the body */
	public final static double BLOB_BODY_LARGEUR = 0.10;
	public final static double BLOB_BODY_HAUTEUR = 0.11;
	/** the eyes */
	public final static double BLOB_EYES_LARGEUR = 0.02;
	public final static double BLOB_EYES_HAUTEUR = 0.04;
	/** the color */
	public static Color BLOB_SERVEUR_COLOR = new Color(169,68,164);
	public static Color BLOB_CLIENT_COLOR = Color.red;
	
	/** Le double singleton */
	public static Blob blobServeur = new Blob(true);
	public static Blob blobClient = new Blob(false);
	
	/* Les variables d'instance */
	/** Un pourcentage de la largeur pour indiquer la position du blob */
	private PointSam position;
	/** Un boolean pour différencier le blob local du blob distant */
	private boolean isServeur;
		
	/* Le constructeur */
	private Blob(boolean isMe){
		this.position = new PointSam();
		this.setIsServeur(isMe);
	}
	
	
	/** Méthode permettant de tracer le blob dans le graphque passé en paramètre */
	public void paintBlob(Graphics g){
		
		/* On choisie la couleur de dessin en fonction du blob à déssiner */
		/* On trace dans un premier temps le body */
		String link = "blobClient.png";
		if(this.isServeur)
			link = "blobServeur.png";
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
	
	
}
