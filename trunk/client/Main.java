package client;
import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args){
		
		JFrame f = new JFrame();
		f.add(new Pane());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(900, 460);
		f.setVisible(true);
	}

}
