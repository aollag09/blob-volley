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

		JFrame f = new JFrame();
		Pane p = new Pane();
		f.add(p);
		f.addKeyListener(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1060,525);
		f.setVisible(true);
		
//		ArrayList<Point> v = new ArrayList<Point>();
//		for(int i=0; i<225; i++){
//			Balle.instance.nextPosition();
//			System.out.println("Tick n� "+i);
//			System.out.println("Position      ===> "+ Balle.instance.getPosition());
//			System.out.println("Vitesse       ===> "+ Balle.instance.getVitesse());
//			System.out.println("Acc�l�ration  ===> "+ Balle.instance.getAcceleration());
//			System.out.println();
//			v.add(new Point(Balle.instance.getPosition().getX(),- Balle.instance.getPosition().getY()));
//		}
//		Courbe c = new Courbe(v);
//		Graphique g =new Graphique("Test",900,500) ;
//		g.ajouter(c);
//		g.montrer();
	
		
	}
}


