package client;
import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args){
		
		JFrame f = new JFrame();
		f.add(new Pane());
		f.setSize(900, 460);
		f.setVisible(true);
	}

}
