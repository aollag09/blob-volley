package client;
import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.PointSam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Pane extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public static Pane instance = new Pane();

	/** La couleur du background */
	public static Color BACKGROUND_COLOR = new Color(0,163,232);
	
	/* La taille du panel qui peut être variable */
	public static int width = 1060;
	public static int height = 525;
	
	private int ordre;
	
	public Pane(){
		super();
		this.setSize(width, height);
		this.ordre = IServeur.ORDRE_RESTE;
		ActionListener al = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Blob.instanceServeur.nextPosition(ordre);
				repaint();
			}
		};
		Timer time = new Timer(IServeur.DELAY, al);
		time.start();
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
		Blob.instanceClient.paintBlob(g);
	
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT :  this.ordre = IServeur.ORDRE_GAUCHE;break;
		case KeyEvent.VK_RIGHT : this.ordre = IServeur.ORDRE_DROITE;break;
		case KeyEvent.VK_SPACE : this.ordre = IServeur.ORDRE_SAUT;break;
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		this.ordre = IServeur.ORDRE_RESTE;
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * @return the ordre
	 */
	public int getOrdre() {
		return ordre;
	}


	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

}
