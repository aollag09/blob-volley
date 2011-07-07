package client;

import interfaceCS.Balle;
import interfaceCS.Courbe;
import interfaceCS.Graphique;
import interfaceCS.Point;
import interfaceCS.PointSam;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JFrame;


public class Main {
	
	public static int port = 0;
	
	public static boolean jeuEnCours = true;

	public static void main(String[] args){

//		JFrame f = new JFrame();
//		f.add(new Pane());
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setSize(913, 407);
//		f.setVisible(true);
		
		ArrayList<Point> v = new ArrayList<Point>();
		for(int i=0; i<200; i++){
			Balle.instance.nextPosition();
			System.out.println("Tick n° "+i);
			System.out.println("Position      ===> "+ Balle.instance.getPosition());
			System.out.println("Vitesse       ===> "+ Balle.instance.getVitesse());
			System.out.println("Accélération  ===> "+ Balle.instance.getAcceleration());
			System.out.println();
			v.add(new Point(Balle.instance.getPosition().getY(),Balle.instance.getCompteur()*1000));
		}
		Courbe c = new Courbe(v);
		Graphique g =new Graphique("Test",900,500) ;
		g.ajouter(c);
		g.montrer();
	
		
	}
}


