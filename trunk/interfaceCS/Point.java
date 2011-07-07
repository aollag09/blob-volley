package interfaceCS;


public class Point {
	/**
	 * @auteur Amaury Cette classe impl�mente des points de deux dimensions Elle
	 *         permet aussi quelque m�thodes : les accesseurs ainsi que les
	 *         m�thodes d�placer et equals.
	 */

	// VARIABLES D'INSTANCES

	/**
	 *Double repr�sentant l'abscisse du point
	 */
	private double x;
	/**
	 * Double repr�sentant l'ordonn�e du point
	 */
	private double y;

	// CONSTRUCTEURS

	/**
	 * Constructeur avec en entr�e l'abscisse est l'ordonn�e du point
	 * 
	 * @param double x,double y
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructeur avec aucune variable en entr�e construit un point au centre
	 * du rep�re
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Constructeur par recopie d'un autre point p
	 * 
	 * @param Point
	 *            p
	 */
	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	// METHODES

	// Accesseurs
	/**
	 * Accesseur permettant d'obtenir l'abscisse du point.
	 * 
	 * @return double, abscisse du point.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Accesseur permettant d'obtenir l'ordonn��e du point.
	 * 
	 * @return double, ordonn�e du point
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Accesseur permettant de modifier l'abscisse du point
	 * 
	 * @param double
	 * @return void
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Accesseur permettant de modifier l'ordonn�e du point
	 * 
	 * @param double
	 * @return void
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * M�thode pouvant d�placer un point de dx et dy
	 * 
	 * @param double dx
	 * @param double dy
	 * @return void
	 */
	public void deplacer(double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}

	/**
	 * M�thode calculant la distance d'un point par rapport � l'origine
	 * 
	 * @return Double la distance de ce point p par raport � l'origine
	 */
	public double distanceAZero() {
		return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
	}

	/**
	 * Une m�thode permettant de connaitre l'angle entre l'abscisse et la droite
	 * passant par le point et l'origine
	 * 
	 * @return valeur de l'angle en radian
	 */
	public double angleParRapportAbs() {
		double aRetourner = 0;
		if (this.getX() == 0) {
			//On ne peut pas diviser par zero il faut donc s�parer les cas
			if (this.getY() > 0) {
				return Math.PI / 2;
			} else {
				if (this.getY() == 0) {
					return 0;
				} else {
					return -Math.PI / 2;
				}
			}
		} 
		else {
			//On s�pare les cas en fonction de la pasitivit� de l'abscisse et de l'ordonn�e du point
			if(this.getX()>0&&this.getY()>=0){
				aRetourner = Math.atan(this.getY() / this.getX());
			}
			if(this.getX()<0&&this.getY()<0){
				aRetourner = Math.atan(this.getY() / this.getX())+Math.PI;
			}
			if(this.getX()>0&&this.getY()<=0){
				aRetourner = Math.atan(this.getY() / this.getX());
			}
			if(this.getX()<0&&this.getY()>0){
				aRetourner = Math.atan(this.getY() / this.getX())+Math.PI;
			}
			return aRetourner;
		}
	}
	/**
	 * M�thode permettant de comparer la sutructure entre deux points, et donc
	 * d�terminer s'ils sont �gaux
	 * 
	 * @param Point
	 * @return boolean
	 */
	public boolean equals(Point p) {
		return ((this.getX() == p.getX()) && (this.getY() == p.getY()));
	}

	/**
	 * M�thode permettant d'afficher un point sous la forme [x,y]
	 * 
	 * @param Point
	 * @return String
	 */
	public static String toStringPoint(Point p) {
		String s;
		s = "[" + p.getX() + "," + p.getY() + "]";
		return s;
	}
}