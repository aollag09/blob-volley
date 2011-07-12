package client;

import interfaceCS.Balle;
import interfaceCS.Blob;
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

	public static void main(String[] args){

		Main.partieEnCours = new Partie();
		Main.jeuEnCours = true;
		
		JFrame f = new JFrame();
		f.add(Pane.instance);
		f.setTitle("Blob Volley");
		f.addKeyListener(Pane.instance);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon ic2= new ImageIcon(Blob.LINK_BLOB_SERVEUR);
		f.setIconImage(ic2.getImage());
		f.setSize(1060,525);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		JFrame menu = new JFrame();
		menu.add(new Menu());
		menu.setTitle("Blob Volley");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon ic= new ImageIcon(Blob.LINK_BLOB_SERVEUR);
		menu.setIconImage(ic.getImage());
		menu.pack();
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
}


