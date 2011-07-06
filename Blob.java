import java.awt.Color;
import java.awt.Graphics;



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
	public final static double BLOB_BODY_HAUTEUR = 0.20;
	/** the eyes */
	public final static double BLOB_EYES_LARGEUR = 0.02;
	public final static double BLOB_EYES_HAUTEUR = 0.04;
	/** the color */
	public static Color BLOB_SERVEUR_COLOR = Color.blue;
	public static Color BLOB_CLIENT_COLOR = Color.red;
	
	/** Le double singleton */
	public static Blob blobServeur = new Blob(true);
	public static Blob blobClient = new Blob(false);
	
	/* Les variables d'instance */
	/** Un pourcentage de la largeur pour indiquer la position du blob */
	private double positionX;
	/** Un boolean pour différencier le blob local du blob distant */
	private boolean isServeur;
		
	/* Le constructeur */
	private Blob(boolean isMe){
		this.positionX = 1/2;
		this.setIsServeur(isMe);
	}
	
	
	/** Méthode permettant de tracer le blob dans le graphque passé en paramètre */
	public void paintBlob(Graphics g){
		
		/* On choisie la couleur de dessin en fonction du blob à déssiner */
		if(this.isServeur)
			g.setColor(BLOB_SERVEUR_COLOR);
		else
			g.setColor(BLOB_CLIENT_COLOR);
		
		/* On trace dans un premier temps le body */
		g.fillOval ((int)(Pane.width*positionX),
				(int)(Pane.height - (int)(Blob.BLOB_BODY_HAUTEUR*Pane.height)/2 ),
				(int)(Blob.BLOB_BODY_LARGEUR*Pane.width), 
				(int)(Blob.BLOB_BODY_HAUTEUR*Pane.height) );
		
		/* On trace ensuite les yeux */
		g.setColor(Color.white);
		int signe = 1;
		if(!this.isServeur)
			signe = -1;
		g.fillOval((int) (positionX+ signe*positionX/4),
				Pane.height - (int)(Blob.BLOB_BODY_HAUTEUR*Pane.height)/4, 
				(int)(Blob.BLOB_EYES_LARGEUR*Pane.width), 
				(int)(Blob.BLOB_EYES_HAUTEUR*Pane.height) );
		
	
		
	}


	public double getPositionX() {
		return positionX;
	}


	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}


	public boolean isServeur() {
		return isServeur;
	}


	public void setIsServeur(boolean isMe) {
		this.isServeur = isMe;
	}
	
	
}
