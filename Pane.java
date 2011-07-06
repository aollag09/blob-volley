import java.awt.Graphics;

import javax.swing.JPanel;


public class Pane extends JPanel {
	
	/* La taille du panel qui peut être variable */
	public static int width = 500;
	public static int height = 300;
	
	
	
	public Pane(){
		
	}
	
	
	public void paintComponent(Graphics g){
		width = getWidth();
		height = getHeight();
		
		
		
	}

}
