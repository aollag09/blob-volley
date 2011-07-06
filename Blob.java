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
	public final static double BLOB_LARGEUR = 0.10;
	public final static double BLOB_HAUTEUR = 0.20;
	
	public static Color BLOB_ME_COLOR = Color.blue;
	public static Color BLOB_ADVERSAIRE_COLOR = Color.red;
	
	/* Le double singleton */
	public static Blob blobMe = new Blob(true);
	public static Blob blobAdversaire = new Blob(false);
	
	/* Les variables d'instance */
	/** Un pourcentage de la largeur pour indiquer la position du blob */
	private double positionX;
	/** Un boolean pour différencier le blob local du blob distant */
	private boolean isMe;
		
	/* Le constructeur */
	private Blob(boolean isMe){
		this.positionX = 1/2;
		this.setMe(isMe);
	}
	
	
	/** Méthode permettant de tracer le blob dans le graphque passé en paramètre */
	public void paintBlob(Graphics g){
		
		/* On choisie la couleur de dessin en fonction du blob à déssiner */
		
		
		/* On trace dans un premier temps le body */
		g.fillOval ((int)(Pane.width*positionX),
				(int)(Pane.height - (int)(Blob.BLOB_HAUTEUR*Pane.height)/2 ),
				(int)(Blob.BLOB_LARGEUR*(Pane.width+0.0)), 
				(int)(Blob.BLOB_HAUTEUR*Pane.height));
		
		/* On trace ensuite les yeux */
		
		if(this.isMe){
			
		}
	
		
	}


	public double getPositionX() {
		return positionX;
	}


	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}


	public boolean isMe() {
		return isMe;
	}


	public void setMe(boolean isMe) {
		this.isMe = isMe;
	}
	
	
}
