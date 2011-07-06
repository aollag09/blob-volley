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
	
	/** Le double singleton */
	public static Blob blobServeur = new Blob(true);
	public static Blob blobClient = new Blob(false);
	
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
