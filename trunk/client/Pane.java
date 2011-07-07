package client;
import interfaceCS.Blob;
import interfaceCS.PointSam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class Pane extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;

	/** La couleur du background */
	public static Color BACKGROUND_COLOR = new Color(0,163,232);
	
	/* La taille du panel qui peut être variable */
	public static int width = 1060;
	public static int height = 525;
	
	
	
	public Pane(){
		super();
		this.setSize(width, height);
		this.addKeyListener(this);
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
		
		
		Blob.instanceServeur.paintBlob(g);
		Blob.instanceClient.nouvellePosition(new PointSam(0.9,0.0));
		Blob.instanceClient.paintBlob(g);
	
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT : System.out.println("left");break;
		case KeyEvent.VK_RIGHT : System.out.println("right");break;
		case KeyEvent.VK_SPACE : System.out.println("space");break;
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
