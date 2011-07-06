package interfaceCS;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class ServeurDistant implements IServeur {

	private OutputStream oStream;
	private InputStream iStream;
	
	public ServeurDistant(String adresseIp, int port){
		
		try {
			Socket s = new Socket(adresseIp, port);
			
			oStream = s.getOutputStream();
			iStream = s.getInputStream();
			
			
			
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "L'adresse IP spécifiée n'est pas valide !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "La connexion au serveur n'a pas eu s'effectuer !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	@Override
	public void envoyerDonnees(int ordre) {
		try {
			this.oStream.write((""+ordre).getBytes());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "La connexion au serveur n'est plus effective !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public int getTypeServeur() {
		return IServeur.SERVEUR_DISTANT;
	}

	@Override
	public PointSam[] recupererDonnees() {
		String ret = "";
		try {
			byte[] b = new byte[1000];
			int bitsRecus = 0;
			while((bitsRecus = this.iStream.read(b)) >= 0) {
				ret = new String(b, 0, bitsRecus);
			}
		} catch (IOException e){
			JOptionPane.showMessageDialog(null, "La connexion au serveur n'est plus effective !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		if (ret!=""){
			String[] lesTrois = ret.split(";");
			
			return null; 
		}
		else {
			return null;
		}
	}

}
