package client;

import interfaceCS.Balle;
import interfaceCS.Blob;
import interfaceCS.PointSam;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Main {

	public static int port = 0;

	public static boolean jeuEnCours = true;

	public static void main(String[] args){

		JFrame f = new JFrame();
		f.add(Pane.instance);
		f.setTitle("Blob Volley");
		f.addKeyListener(Pane.instance);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1060,525);
		f.setVisible(true);
		ImageIcon ic= new ImageIcon(Blob.LINK_BLOB_SERVEUR);
		f.setIconImage(ic.getImage());
	}
}


