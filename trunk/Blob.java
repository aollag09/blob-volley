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
	
	/* Le double singleton */
	public static Blob blobMe = new Blob();
	public static Blob blobAdversaire = new Blob();
	
	/* Les variables d'instance */
	private double positionX;
		
	/* Le constructeur */
	private Blob(){
		this.positionX = 1/2;
	}
	
	
	/** Méthode permettant de tracer le blob dans le graphque passé en paramètre */
	public void paintBlob(Graphics g){
		
		/* On trace dans un premier temps le body */
		g.fillOval ((int)(Pane.width*positionX),
				(int)(Pane.height - (int)(Blob.BLOB_HAUTEUR*Pane.height)/2 ),
				(int)(Blob.BLOB_LARGEUR*(Pane.width+0.0)), 
				(int)(Blob.BLOB_HAUTEUR*Pane.height));
	
		
	}


	public double getPositionX() {
		return positionX;
	}


	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	
	
}
