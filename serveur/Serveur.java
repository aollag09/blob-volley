package serveur;

import java.awt.Point;
import java.io.IOException;
import java.net.*;

public class Serveur {
	
	public Serveur(int port){
		try {
			ServerSocket server = new ServerSocket(port);
			Socket client = server.accept();
			
			
			
			client.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setOrdreLocal(int ordre) {
		// TODO Auto-generated method stub
		
	}

	public Point[] getCoordonnees() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}