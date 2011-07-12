package client;

import interfaceCS.Balle;
import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.Partie;
import interfaceCS.PointSam;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class Main {

	public static int port = 0;

	public static boolean jeuEnCours = false;
	
	public static Partie partieEnCours = null;

	public static IServeur iserveur;
	
	public static JFrame menu, jeu;


	public static void main(String[] args){

				
		// le début
		menu = new JFrame();
		menu.add(new Menu());
		menu.setTitle("Blob Volley");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon ic= new ImageIcon(Blob.LINK_BLOB_SERVEUR);
		menu.setIconImage(ic.getImage());
		menu.pack();
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
	public static void lancerJeu(){
		Main.partieEnCours = new Partie();
		Main.jeuEnCours = true;
		
		jeu = new JFrame();
		jeu.add(Pane.instance);
		jeu.setTitle("Blob Volley");
		jeu.addKeyListener(Pane.instance);
		jeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon ic2= new ImageIcon(Blob.LINK_BLOB_SERVEUR);
		jeu.setIconImage(ic2.getImage());
		jeu.setSize(1060,525);
		jeu.setLocationRelativeTo(null);
		jeu.setVisible(true);
	}
}


