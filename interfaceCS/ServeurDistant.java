package interfaceCS;

import java.awt.Point;


public class ServeurDistant implements IServeur {

	
	
	public ServeurDistant(String adresseIp, int port){
		
		
		
		
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
	public Point[] recupererDonnees() {
		// TODO Auto-generated method stub
		return null;
	}

}
