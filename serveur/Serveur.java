package serveur;

import interfaceCS.Balle;
import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.PointSam;

import java.io.IOException;
import java.net.*;

import javax.swing.JOptionPane;


public class Serveur {
	
	private int ordreLocal = IServeur.ORDRE_RESTE, ordreDistant = IServeur.ORDRE_RESTE;
	
	public Serveur(int port){
		try {
			ServerSocket server = new ServerSocket(port);
			Socket client = server.accept();

			client.close();
			server.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossible de créer le serveur !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setOrdreLocal(int ordre) {
		this.ordreLocal = ordre;
	}

	public PointSam[] getCoordonnees() {
		PointSam[] ret = new PointSam[3];
		ret[IServeur.JOUEUR_LOCAL] = Blob.instanceServeur.getPosition();
		ret[IServeur.JOUEUR_DISTANT] = Blob.instanceClient.getPosition();
		ret[IServeur.BALLE] = Balle.instance.getPosition();
		return ret;
	}
	
}