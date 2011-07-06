import java.awt.Graphics;

import javax.swing.JPanel;


public class Pane extends JPanel {
	
	/* La taille du panel qui peut être variable */
	public static int width;
	public static int height;
	
	
	public Pane(){
		
	}
	
	
	public void paintComponent(Graphics g){
		
		width = getWidth();
		height = getHeight();
		
		
		
	}

}
