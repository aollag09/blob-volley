package interfaceCS;

/**
 * Classe qui va permettre de mod�liser les diff�rentes trajectoire de la balle
 * 
 * Principe :
 * >> On garde en m�moire la premi�re position ou laposition d'un choc
 * >> En fonction des diff�rentes forces appliqu�es � la balle en ce point de choc on d�termine le point juste apr�s 
 * >> On peut alors savoir nouvelle vitesse initiale de la balle
 * >> On calcule tous les autres points en tenant compte simplement du poids et de cette vitesse initiale
 * >> On incr�mente le compteur � chaque nouveau point "libre"
 * >> On recommence la boucle si la balle touche un autre obstacle, 
 * >> on remet le compteur � z�ro
 * 
 * @author Amaury
 *
 */
public class Balle extends Mobile{

	/* Le singleton */
	public static Balle instance = new Balle(new PointSam(0,0));

	/* Constantes */
	public static final double TAILLE_BALLE = 0.10;
	public static final double CONSTANTE_DE_GRAVITATION = 9;
	public static final double MASSE_BALLE = 0.05;

	/* Un compteur */
	/** Cet entier permet d'incr�menter le temps pour calculer les trajectoires de la balle uniquement dans le cas
	 * ou elle est "libre"
	 * Il est remit � z�ro en cas de choque pour recalculer sa trajectoire 
	 * 
	 * Le temps dans le compteur est en !!!!!!!!!!!!! SECONDES !!!!!!!!!!!!!!!
	 */
	private double compteur = 0;

	/** Le point initiale du choc */
	private PointSam positionInitiale;
	/** La vitesse initiale du choc */
	private PointSam vitesseInitiale;


	/* Constructeur */
	private Balle(){
		super();
		this.positionInitiale = new PointSam(20,20);
	}

	private Balle(PointSam p){
		super(p);
		this.positionInitiale = new PointSam(20,20);
		this.vitesseInitiale = new PointSam(10,-1);
	}

	/** M�thode appel� � chaque delay pour recalculer la position de la balle */
	public void nextPosition(){
		this.setCompteur(this.compteur+((IServeur.DELAY+0.0)/1000));
		PointSam newPosition = new PointSam();

		/* On test dans un premier temps si la balle risque de toucher un blob */
		if(super.getPosition().getY()<Blob.instanceServeur.getPosition().getY()-Blob.BLOB_BODY_HAUTEUR){
			/* Risque de toucher le blob serveur */

		}
		else{
			if(super.getPosition().getY()<Blob.instanceClient.getPosition().getY()-Blob.BLOB_BODY_HAUTEUR){
				/* Risque de toucher le blob client */

			}
			else{
				/* Aucun risque de toucher un blob */
				/* On calcul ainsi les nouveaux coordonn�es de la balle simplement en fonction du poids */
				double posX = this.positionInitiale.getX() + this.getVitesseInitiale().getX()*compteur;
				double posY = this.positionInitiale.getY() + 0.5*MASSE_BALLE*CONSTANTE_DE_GRAVITATION*compteur*compteur
						+ this.getVitesseInitiale().getY()*compteur;
				newPosition = new PointSam(posX, posY);
			}
		}


		/* On modifie la positon de la balle avec sa nouvelle position */
		super.nouvellePosition(newPosition);

	}

	public double getCompteur() {
		return compteur;
	}

	public void setCompteur(double compteur) {
		this.compteur = compteur;
	}

	/**
	 * @return the ref
	 */
	public PointSam getRef() {
		return positionInitiale;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(PointSam ref) {
		this.positionInitiale = ref;
	}

	/**
	 * @return the vitesseInitiale
	 */
	public PointSam getVitesseInitiale() {
		return vitesseInitiale;
	}

	/**
	 * @param vitesseInitiale the vitesseInitiale to set
	 */
	public void setVitesseInitiale(PointSam vitesseInitiale) {
		this.vitesseInitiale = vitesseInitiale;
	}


}
