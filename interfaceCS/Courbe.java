package interfaceCS;
import java.util.ArrayList;
import java.awt.*;

public class Courbe {
	/**
	 * @author Amaury 
	 * Classe qui permet de creer une courbe et qui sera untilisée par la classe graphique pour l'afficher
	 * Elle contiendra principalement des accesseurs mes aussi d'autres méthodes permettant d'ajouter des
	 * points à la courbe
	 */
	
	//CONSTANTES
	
	public static final int MARQUE_PAR_DEFAUT = 0;
	public static final Color COULEUR_PAR_DEFAUT = null;
	public static final double TRANSPARENCE_PAR_DEFAUT = 0;
	public static final int AUCUNE = -1;
	
	//VARIABLES D'INSTANCES 

	/**
	 * tableau renprésentant l'abscisse de chacun des points de la courbe
	 */
	private ArrayList<Double> abscisses;

	/**
	 * tableau représentant l'ordonnée de chacun des points de la courbe
	 */
	private ArrayList<Double> ordonnees;

	/**
	 * tableau représentant la marge d'erreur sur l'ordonnée des points de la courbe
	 */
	private ArrayList<Double>margesErreurs; 

	/**
	 * La marque qui serat utilisé par la classe Graphique afin de tracer la courbe
	 */
	private int marque;

	/**
	 * Couleur du tracé de la courbe
	 */
	private Color couleur;

	/**
	 * Le titre de la courbe 
	 */
	private String titre;
	/**
	 * Le taux de transparence avec lequel sera affiche la marge d'erreur a l'ecran
	 */
	private double transparence;

	//CONSTRUCTEURS

	public Courbe(ArrayList<Point> points){
		this.abscisses = new ArrayList<Double>();
		this.ordonnees = new ArrayList<Double>();
		this.margesErreurs = new ArrayList<Double>();
		for (int i=0;i<points.size();i++){
			this.abscisses.add(i,points.get(i).getX());
			this.ordonnees.add(i,points.get(i).getY());
			this.margesErreurs.add(i,0.0);
		}
		this.couleur = COULEUR_PAR_DEFAUT;
		this.marque = MARQUE_PAR_DEFAUT;
		this.titre = " ";	
		this.transparence =TRANSPARENCE_PAR_DEFAUT;

	}

	public Courbe(){
		this.abscisses = new ArrayList<Double>();
		this.ordonnees = new ArrayList<Double>();
		this.margesErreurs = new ArrayList<Double>();
		this.couleur = COULEUR_PAR_DEFAUT;
		this.marque = MARQUE_PAR_DEFAUT;
		this.titre = "test déplacement";
		this.transparence =TRANSPARENCE_PAR_DEFAUT;
	}


	public Courbe(String string) {
		this.abscisses = new ArrayList<Double>();
		this.ordonnees = new ArrayList<Double>();
		this.margesErreurs = new ArrayList<Double>();
		this.couleur = COULEUR_PAR_DEFAUT;
		this.marque = MARQUE_PAR_DEFAUT;
		this.titre = string;
		this.transparence =TRANSPARENCE_PAR_DEFAUT;
	}

	public Courbe(String s, int m) {
		this.abscisses = new ArrayList<Double>();
		this.ordonnees = new ArrayList<Double>();
		this.margesErreurs = new ArrayList<Double>();
		this.couleur=null;
		this.transparence = TRANSPARENCE_PAR_DEFAUT;
		this.titre=s;
		this.marque=m;
	}
	
	//METHODES

	//Accesseurs
		//Getter



	/**
	 * Accesseur permettant d'avoir l'index de la marque 
	 * @return int
	 */
	public int getMarque(){
		return this.marque;
	}

	/**
	 * Accesseur permettant de modifier la marque de la courbe.
	 * @param int 
	 * @return void
	 */
	public void setMarque(int newMarque){
		this.marque=newMarque;
	}

	/**
	 * Accesseur permettant d'obtenir le titre de la courbe.
	 * @return String
	 */
	public String getTitre(){
		return this.titre;
	}

	/**
	 * Accesseur permettant d'obtenir la couleur de la courbe
	 * @return Color
	 */
	public Color getCouleur(){
		return this.couleur;
	}

	/**
	 * Accesseur permettant d'obtenir le nombre de points que comporte la courbe
	 * @return int
	 */
	public int getNombrePoints(){
		return this.abscisses.size();
	}

	/**
	 * Accesseur permettant d'obtenir l'abscisse du point d'index i de la courbe.
	 * @param int
	 * @return double, (0.0 si index<0 ou index>=this.getNbPoints())
	 */
	public double getX(int i) {
		if(i>=0 && i<this.getNombrePoints()){
			return this.abscisses.get(i);
		}
		else{
			return 0.0;
		}
	}

	/**
	 * Accesseur permettant d'obtenir l'ordonnee du point d'index i de la courbe.
	 * @param int
	 * @return double, (0.0 si index<0 ou index>=this.getNbPoints())
	 */
	public double getY(int i){
		if(i>=0 && i<this.getNombrePoints()){
			return this.ordonnees.get(i);
		}
		else{
			return 0.0;
		}
	}

	/**
	 * Accesseur permettant d'obtenir le point d'index i de la courbe.
	 * @param int
	 * @return Point, (<0,0> si index<0 ou index>=this.getNbPoints())
	 */
	public Point getPoint(int i){
		if(i>=0 && i<this.getNombrePoints()) {
			Point p = new Point(this.abscisses.get(i),this.ordonnees.get(i));
			return p;
		}
		else{
			Point origine = new Point(0,0);
			return origine;
		}
	}

	/**
	 * Accesseur permettant d'obtenir la transparance des ordonnées de la courbe
	 */
	public double getTransparence() {
		return this.transparence;
	} 
	/**
	 * Accesseur permettant d'obtenir la marge d'erreur sur l'ordonnee du point d'index index de la courbe.
	 * @return double, 0.0 si index<0 ou index>=this.getNbPoints(), et retourne la marge d'erreur sur l'ordonnee du point d'index index sinon.
	 */
	public double getErr(int index) {
		if (index>=0 && index<this.getNombrePoints()) {
			return this.margesErreurs.get(index);
		}
		else {
			return 0.0;
		}
	}
		
		//Setter

	/**
	 * Accesseur permettant de modifier la couleur de la courbe
	 * @param Color
	 * @return void
	 */
	public void setCouleur(Color c){
		this.couleur=c;
	}

	/**
	 * Méthode permettant de modifier le titre de la courbe
	 * @param String
	 * @return void
	 */
	public void setTitre(String s){
		this.titre = s;
	}

	/**
	 * Méthode pemettant de modifier la transparence de la courbe
	 * @param double
	 * @return void
	 */
	public void setTransparence(double t){
		this.transparence = t;
	}

	/**
	 * Méthode permettant d'afficher les valeurs des abscisses et des ordonnées des points qui
	 * constituent la courbe.
	 * @return String
	 */
	public String toString(){
		String ar=this.getTitre();
		for (int i=0; i<this.getNombrePoints(); i++) {
			ar+="["+this.abscisses.get(i).doubleValue()+","+this.ordonnees.get(i).doubleValue()+"]";
		}
		return ar;
	}

	/**
	 * Méthode permettant d'ajouter un point à la courbe. Si un point à déjà la même abscisse que celui 
	 * que l'on veut rentrer en paramètre, ce point serat remplacé par le nouveau que crée cette procédure.
	 * Cette méhode à donc pour but de mêtre tous les points en abscisse croissante et qu'il n'y ai jaimais
	 * deux ordonnées pour une seule abscisse.
	 * @param Double, Double
	 * @return void
	 */

	public void ajouter(double x, double y){
		int i = 0;
		//Avec cette boucle while on trouve la position du point que l'on veut ajouter dans le tableau
		//contenant tous les points avec donc la condition :
		//rangé en abscisses croissants
		while ((i<this.getNombrePoints())&&(this.abscisses.get(i)<x)){
			i++;
		}
		//Dans le cas ou il existait déjà un point avec cette abscisse
		//on remplace l'ordonnée déjà existante
		if ((i<this.getNombrePoints())&&(this.abscisses.get(i)==x)){
			this.ordonnees.remove(i);
			this.ordonnees.add(i, y);
		}
		//Sinon on ajoute simplement le point à la bonne place
		else{
			this.abscisses.add(i,x);
			this.ordonnees.add(i,y);
		}
	}
	/**
	 * Même procédure que la précedante, sauf que celle ci prend en compte une marge d'erreur
	 * @param x
	 * @param y
	 * @param err
	 */
	public void ajouter(double x, double y, double err){
		int pos=0; 
		while (pos<this.getNombrePoints() && this.abscisses.get(pos).doubleValue()<x) {
			pos++;
		}
		if (pos<this.getNombrePoints() && this.abscisses.get(pos).doubleValue()==x) { // Modif
			this.ordonnees.remove(pos);
			this.ordonnees.add(pos,new Double(y));
			this.margesErreurs.remove(pos);
			this.margesErreurs.add(pos,new Double(err));
		}
		else { // ajout
			this.abscisses.add(pos,new Double(x));
			this.ordonnees.add(pos,new Double(y));
			this.margesErreurs.add(pos,new Double(err));
		}	 
	}
	public void ajouterPoint(Point p){
		double x = p.getX();
		double y = p.getY();
		ajouter(x,y,0);
	}
	/**
	 * Une fonction test permettant de savoir si la fonction ajouter marche bien
	 */
}