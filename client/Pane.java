package client;
import interfaceCS.Blob;

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
		
		/* Création du terrain */
		//g.drawRect(width-width/20, y, width, height)
		
		
		
		Blob.blobServeur.paintBlob(g);
		Blob.blobClient.setPositionX(0.9);
		Blob.blobClient.paintBlob(g);
	
		
	}

}
