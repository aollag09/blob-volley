package interfaceCS;

import java.util.LinkedList;

public class Mobile {

	/* Variables d'instances */
	/** La position courante de la balle */
	private PointSam position;
	/** Un vecteur de position de type FIFO qui va garder les 3 derni�res position afin de d�terminer
	 * la vitesse et l'acc�l�ration du blob */
	private LinkedList<PointSam> historique;
	/** La vitesse du blob */
	private PointSam vitesse;
	/** L'acc�l�ration du blob */
	private PointSam acceleration;
	
	public Mobile(){
		this.position = new PointSam();
		this.vitesse = new PointSam();
		this.acceleration = new PointSam();
		/* On garde trois points pour calculer la vitesse et l'acc�l�ration */
		this.historique = new LinkedList<PointSam>();
		this.historique = new LinkedList<PointSam>();
		this.historique.add(new PointSam());
		this.historique.add(new PointSam());
		this.historique.add(new PointSam());
	}
	
	
	public Mobile(PointSam p){
		this.position = p;
		this.vitesse = new PointSam();
		this.acceleration = new PointSam();
		/* On garde trois points pour calculer la vitesse et l'acc�l�ration */
		this.historique = new LinkedList<PointSam>();
		this.historique.add(p);
		this.historique.add(p);
		this.historique.add(p);
	}
	
	public PointSam getPosition() {
		return position;
	}
	
	public void setPosition(PointSam p){
		this.position = p;
	}

	/**
	 * M�thode permettant de fixer le nouvelle position et ainsi de recalculer la nouvelle vitesse
	 * ainsi que la nouvelle acc�l�ration
	 */
	public void nouvellePosition(PointSam p) {
		this.position = p;
		this.historique.pop();
		this.historique.addLast(p);
		/* On red�termine la vitesse actuelle */
		double vitX = ( this.historique.get(2).getX()-this.historique.get(1).getX() ) / ((IServeur.DELAY+0.0)/1000);
		double vitY = (this.historique.get(2).getY()-this.historique.get(1).getY())/((IServeur.DELAY+0.0)/1000);
		this.vitesse = new PointSam(vitX,vitY);
		
		/* L'ancienneVitesse */
		PointSam aVitesse;
		double avitX = (this.historique.get(1).getX()-this.historique.get(0).getX())/((IServeur.DELAY+0.0)/1000);
		double avitY = (this.historique.get(1).getY()-this.historique.get(0).getY())/((IServeur.DELAY+0.0)/1000);
		aVitesse = new PointSam(avitX,avitY);
		
		/* La nouvelle acc�l�ration */
		double accX = (this.vitesse.getX()-aVitesse.getX())/((IServeur.DELAY+0.0)/1000);
		double accY = (this.vitesse.getY()-aVitesse.getY())/((IServeur.DELAY+0.0)/1000);
		this.acceleration = new PointSam(accX,accY);
	}
	
	/**
	 * M�thode permetant de fixer la nouvelle vitesse et ainsi de recalculer la nouvelle position
	 * ainsi que la nouvelle acc�l�ration
	 */
	public void nouvelleVitesse(PointSam newV){
		this.vitesse = newV;
		
		/* On calcul le nouveau point */
		double pointX =  this.historique.get(2).getX() + this.vitesse.getX()*((IServeur.DELAY+0.0)/1000);
		double pointY =  this.historique.get(2).getY() + this.vitesse.getY()*((IServeur.DELAY+0.0)/1000);
		PointSam p =  new PointSam(pointX, pointY);
		this.position = p;
		this.historique.pop();
		this.historique.addLast(p);
		
		/* L'ancienneVitesse */
		PointSam aVitesse;
		double avitX = (this.historique.get(1).getX()-this.historique.get(0).getX())/((IServeur.DELAY+0.0)/1000);
		double avitY = (this.historique.get(1).getY()-this.historique.get(0).getY())/((IServeur.DELAY+0.0)/1000);
		aVitesse = new PointSam(avitX,avitY);
		
		/* La nouvelle acc�l�ration */
		double accX = (this.vitesse.getX()-aVitesse.getX())/((IServeur.DELAY+0.0)/1000);
		double accY = (this.vitesse.getY()-aVitesse.getY())/((IServeur.DELAY+0.0)/1000);
		this.acceleration = new PointSam(accX,accY);
	}
	
	/** 
	 * M�thode permettant de fixer la nouvelle acc�l�ration et ainsi de recalculer la nouvelle 
	 * vitesse ainsi que la nouvelle position 
	 */
	public void nouvelleAcceleration(PointSam newA){
		this.acceleration = newA;
		
		/* On calcul la nouvelle vitesse */
		double vitX = this.vitesse.getX() + this.acceleration.getX()*((IServeur.DELAY+0.0)/1000);
		double vitY = this.vitesse.getY() + this.acceleration.getY()*((IServeur.DELAY+0.0)/1000);
		this.vitesse = new PointSam(vitX, vitY);
		
		/* On d�termine alors le nouveau point */
		double pointX =  this.historique.get(2).getX() + this.vitesse.getX()*((IServeur.DELAY+0.0)/1000);
		double pointY =  this.historique.get(2).getY() + this.vitesse.getY()*((IServeur.DELAY+0.0)/1000);
		PointSam p =  new PointSam(pointX, pointY);
		this.position = p;
		this.historique.pop();
		this.historique.addLast(p);
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
