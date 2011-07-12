package client;
import interfaceCS.Balle;
import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.Partie;
import interfaceCS.PointSam;

import java.awt.Color;
import java.awt.Font;
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
	/* La taille du panel qui peut Ãªtre variable */
	public static int width = 1060;
	public static int height = 525;

	/** La couleur du background */
	public static final Color BACKGROUND_COLOR = new Color(0,163,232);
	public static final Color COULEUR_SCORES = Color.black;
	public static final double DECALAGE_SCORE = 0.12;
	public static final double LARGEUR_PANNEAU = 0.07;
	public static final double HAUTEUR_PANNEAU = 0.15;

	public static String[] infosServeur = new String[4];

	private int ordre;

	public Pane(){
		super();
		this.setSize(width, height);
		this.ordre = IServeur.ORDRE_RESTE;
		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		};
		Timer time = new Timer(IServeur.DELAY, al);
		time.start();
	}


	public void paintComponent(Graphics g){
		/* Actualisation des dimensions de la fenÃªtre */
		width = getWidth();
		height = getHeight();

		/* CrÃ©ation du terrain */

		/* BackGround */
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		/* Crï¿½ation du filet */
		g.setColor(Color.black);
		g.fillRect((int) (width/2 - width/100), 
				(int)(height - height/8), 
				(int)(width/50),
				(int) (height/8));

		/* On déssine les Blob */
		Blob.instanceServeur.paintBlob(g);
		Blob.instanceClient.paintBlob(g);

		/* On déssine la balle */
		Balle.instance.paintBalle(g);

		/* On écrit les scores */
		if(Main.partieEnCours.getEtat() == Partie.PAS_GAGNANT){
			/* On trace dans un premier temps les panneaux */
			g.setColor(Color.gray);
			g.fill3DRect((int) (width/2-width*Pane.DECALAGE_SCORE),
					-1, 
					(int) (width*Pane.LARGEUR_PANNEAU),
					(int) (height*Pane.HAUTEUR_PANNEAU),
					true);
			g.fill3DRect((int) (width/2+width*Pane.DECALAGE_SCORE-width*Pane.LARGEUR_PANNEAU),
					-1, 
					(int) (width*Pane.LARGEUR_PANNEAU),
					(int) (height*Pane.HAUTEUR_PANNEAU),
					true);

			g.setColor(Color.white);
			Font f = new Font("Dialog", Font.BOLD, width/30);
			g.setFont(f);
			g.drawString(""+Main.partieEnCours.getScoreServeur(),
					(int)(width/2-width*Pane.DECALAGE_SCORE + Pane.LARGEUR_PANNEAU*Pane.width*((Main.partieEnCours.getScoreServeur()<10)?0.42:0.3)),
					(int)(height*Pane.HAUTEUR_PANNEAU*0.6));
			g.drawString(""+Main.partieEnCours.getScoreClient(),
					(int)(width/2+width*Pane.DECALAGE_SCORE - Pane.LARGEUR_PANNEAU*Pane.width*((Main.partieEnCours.getScoreServeur()<10)?0.58:0.7)),
					(int)(height*Pane.HAUTEUR_PANNEAU*0.6));

		}else{
			if(Main.partieEnCours.getEtat() == Partie.CLIENT_GAGNE){
				g.setColor(Color.white);
				Font f = new Font("Dialog", Font.BOLD, width/30);
				g.setFont(f);
				g.drawString("Le blob rouge remporte la partie !!", width/2 - width/4, height/6);
			}
			if(Main.partieEnCours.getEtat() == Partie.SERVEUR_GAGNE){
				g.setColor(Color.white);
				Font f = new Font("Dialog", Font.BOLD, width/30);
				g.setFont(f);
				g.drawString("Le blob violet remporte la partie !!", width/2 - width/4, height/6);
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT :  this.ordre = IServeur.ORDRE_GAUCHE;break;
		case KeyEvent.VK_RIGHT : this.ordre = IServeur.ORDRE_DROITE;break;
		case KeyEvent.VK_SPACE : this.ordre = IServeur.ORDRE_SAUT;break;
		}
		Main.iserveur.envoyerDonnees(ordre);
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
