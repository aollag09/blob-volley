import java.awt.Graphics;

import javax.swing.JPanel;


public class Pane extends JPanel {
	
	/* La taille du panel qui peut �tre variable */
	public static int width = 500;
	public static int height = 300;
	
	
	
	public Pane(){
		super();
		this.setSize(width, height);
	}
	
	
	public void paintComponent(Graphics g){
		width = getWidth();
		height = getHeight();
		
		
		g.drawString("caca test",(int) width/3, (int) height/2);
		
		
	}

}
