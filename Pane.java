import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Pane extends JPanel {
	
	/* Constantes */
	public static Color BACKGROUND_COLOR = new Color(0,163,232);
	
	/* La taille du panel qui peut être variable */
	public static int width = 500;
	public static int height = 300;
	
	
	
	public Pane(){
		super();
		this.setSize(width, height);
	}
	
	
	public void paintComponent(Graphics g){
		/* Actualisation des dimensions de la fenêtre */
		width = getWidth();
		height = getHeight();
		
		/* Création du terrain */
		
		/* Le background */
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);
		
		Blob.blobServeur.paintBlob(g);
		Blob.blobClient.setPositionX(0.9);
		Blob.blobClient.paintBlob(g);
	
		
	}

}
