package serveur;

import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.PointSam;

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
		this.ordreLocal = ordre;
	}

	public PointSam[] getCoordonnees() {
		PointSam[] ret = new PointSam[3];
		ret[IServeur.JOUEUR_LOCAL] = Blob.blobServeur.getPosition();
		ret[IServeur.JOUEUR_DISTANT] = Blob.blobClient.getPosition();
		ret[IServeur.BALLE] = Balle.balle.getPosition();
		return ret;
	}
	
	
	
}