

/**
 * 
 * La classe qui va permettre de creer les deux blob
 * C'est un double singleton 
 * @author Amaury
 *
 */
public class Blob {
	
	/* Les constantes */
	public final static double BLOB_LARGEUR = 0.20;
	public final static double BLOB_HAUTEUR = 0.30;
	
	/* Le double singleton */
	public static Blob blobMe = new Blob();
	public static Blob blobAdversaire = new Blob();
	
	/* Les variables d'instance */
	
	/* Le constructeur */
	private Blob(){
		
	}
	
	
}
