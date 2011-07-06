import java.awt.Point;

import serveur.Serveur;


public class ServeurLocal implements IServeur {

	private Serveur serveur;
	
	public ServeurLocal(Serveur s){
		this.serveur = s;
	}
	
	@Override
	public void envoyerDonnees(int ordre) {
		s.setOrdreLocal(ordre);
	}

	@Override
	public int getTypeServeur() {
		return IServeur.SERVEUR_LOCAL;
	}

	@Override
	public Point[] recupererDonnees() {
		return s.getCoordonnees();
	}

}
