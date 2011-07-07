package interfaceCS;

import java.util.LinkedList;

public class Mobile {

	/* Variables d'instances */
	/** La position courante de la balle */
	private PointSam position;
	/** Un vecteur de position de type FIFO qui va garder les 3 derniï¿½res position afin de dï¿½terminer
	 * la vitesse et l'accï¿½lï¿½ration du blob */
	private LinkedList<PointSam> historique;
	/** La vitesse du blob */
	private PointSam vitesse;
	/** L'accï¿½lï¿½ration du blob */
	private PointSam acceleration;
	
	public Mobile(){
		this.position = new PointSam();
		this.vitesse = new PointSam();
		this.acceleration = new PointSam();
		/* On garde trois points pour calculer la vitesse et l'accï¿½lï¿½ration */
		this.historique = new LinkedList<PointSam>();
		this.historique = new LinkedList<PointSam>();
		this.historique.add(new PointSam());
		this.historique.add(new PointSam());
		this.historique.add(new PointSam());
	}
	
	
	public Mobile(PointSam p){
		this.position = p;
		this.vitesse = new PointSam(1,2);
		this.acceleration = new PointSam();
		/* On garde trois points pour calculer la vitesse et l'accélération */
		this.historique = new LinkedList<PointSam>();
		this.historique.add(p);
		this.historique.add(p);
		this.historique.add(p);
	}
	
	public PointSam getPosition() {
		return position;
	}

	public void setPosition(PointSam p) {
		this.position = p;
		this.historique.pop();
		this.historique.addLast(p);
		/* On redï¿½termine la vitesse actuelle */
		double vitX = (this.historique.get(2).getX()-this.historique.get(1).getX())/IServeur.DELAY;
		double vitY = (this.historique.get(2).getY()-this.historique.get(1).getY())/IServeur.DELAY;
		this.vitesse = new PointSam(vitX,vitY);
		
		/* L'ancienneVitesse */
		PointSam aVitesse;
		double avitX = (this.historique.get(1).getX()-this.historique.get(0).getX())/IServeur.DELAY;
		double avitY = (this.historique.get(1).getY()-this.historique.get(0).getY())/IServeur.DELAY;
		aVitesse = new PointSam(avitX,avitY);
		
		/* La nouvelle accï¿½lï¿½ration */
		double accX = (this.vitesse.getX()-aVitesse.getX())/IServeur.DELAY;
		double accY = (this.vitesse.getY()-aVitesse.getY())/IServeur.DELAY;
		this.acceleration = new PointSam(accX,accY);
	}

	/**
	 * @return the historique
	 */
	public LinkedList<PointSam> getHistorique() {
		return historique;
	}

	/**
	 * @param historique the historique to set
	 */
	public void setHistorique(LinkedList<PointSam> historique) {
		this.historique = historique;
	}


	/**
	 * @return the vitesse
	 */
	public PointSam getVitesse() {
		return vitesse;
	}


	/**
	 * @param vitesse the vitesse to set
	 */
	public void setVitesse(PointSam vitesse) {
		this.vitesse = vitesse;
	}


	/**
	 * @return the acceleration
	 */
	public PointSam getAcceleration() {
		return acceleration;
	}


	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(PointSam acceleration) {
		this.acceleration = acceleration;
	}


}
