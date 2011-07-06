package interfaceCS;

import java.awt.Point;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class ServeurDistant implements IServeur {

	
	
	public ServeurDistant(String adresseIp, int port){
		
		try {
			Socket s = new Socket(adresseIp, port);
			
			
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "L'adresse IP spécifiée n'est pas valide !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "La connexion au serveur n'a pas eu s'effectuer !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	@Override
	public void envoyerDonnees(int ordre) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTypeServeur() {
		// TODO Auto-generated method stub
		return IServeur.SERVEUR_DISTANT;
	}

	@Override
	public PointSam[] recupererDonnees() {
		// TODO Auto-generated method stub
		return null;
	}

}
