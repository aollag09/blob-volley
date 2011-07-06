package client;
import interfaceCS.Blob;
import interfaceCS.PointSam;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Pane extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/** La couleur du background */
	public static Color BACKGROUND_COLOR = new Color(0,163,232);
	
	/* La taille du panel qui peut être variable */
	public static int width = 913;
	public static int height = 407;
	
	
	
	public Pane(){
		super();
		this.setSize(width, height);
	}
	
	
	public void paintComponent(Graphics g){
		/* Actualisation des dimensions de la fenêtre */
		width = getWidth();
		height = getHeight();

		/* Création du terrain */
		
		/* BackGround */
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);
		
		/* Cr�ation du filet */
		g.setColor(Color.black);
		g.fillRect((int) (width/2 - width/100), 
				(int)(height - height/8), 
				(int)(width/50),
				(int) (height/8));
		
		
		Blob.blobServeur.paintBlob(g);
		Blob.blobClient.setPosition(new PointSam(0.9,0.0));
		Blob.blobClient.paintBlob(g);
	
		
	}

}
