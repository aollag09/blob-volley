import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Pane extends JPanel {
	
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
		
		
		
		g.setColor(Color.RED);
		Blob.blobMe.paintBlob(g);
		Blob.blobAdversaire.setPositionX(0.9);
		g.setColor(Color.BLUE);
		Blob.blobAdversaire.paintBlob(g);
	
		
	}

}
