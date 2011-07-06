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
	private Socket s;
	
	public ServeurDistant(String adresseIp, int port){
		
		try {
			s = new Socket(adresseIp, port);
			
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
			
			PointSam balle;
			try {
				String[] temp = lesTrois[IServeur.BALLE].split(",");
				balle = new PointSam(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
			} catch (Exception e){
				balle = new PointSam();
			}
			
			PointSam joueurLocal;
			try {
				String[] temp = lesTrois[IServeur.JOUEUR_LOCAL].split(",");
				joueurLocal = new PointSam(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
			} catch (Exception e){
				joueurLocal = new PointSam();
			}
			
			PointSam joueurDistant;
			try {
				String[] temp = lesTrois[IServeur.JOUEUR_DISTANT].split(",");
				joueurDistant = new PointSam(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
			} catch (Exception e){
				joueurDistant = new PointSam();
			}
			
			PointSam[] retour = new PointSam[3];
			retour[IServeur.BALLE] = balle;
			retour[IServeur.JOUEUR_DISTANT] = joueurDistant;
			retour[IServeur.JOUEUR_LOCAL] = joueurLocal;
			return retour; 
		}
		else {
			return null;
		}
	}

	@Override
	public void fermerServeur() {
		try {
			this.oStream.close();
		} catch (IOException e) {
		}
		try {
			this.iStream.close();
		} catch (IOException e) {
		}
		try {
			this.s.close();
		} catch (IOException e) {
		}
	}

}
