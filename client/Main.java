package client;
import interfaceCS.Balle;
import interfaceCS.PointSam;

import java.util.LinkedList;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args){

//		JFrame f = new JFrame();
//		f.add(new Pane());
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setSize(913, 407);
//		f.setVisible(true);
		
		for(int i=0; i<200; i++){
			Balle.instance.nextPosition();
			System.out.println(Balle.instance.getPosition());
		}
		
	}
}


