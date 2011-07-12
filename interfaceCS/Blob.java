package interfaceCS;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import client.Pane;



/**
 * 
 * La classe qui va permettre de creer les deux blob
 * C'est un double singleton 
 * @author Amaury
 */
public class Blob extends Mobile {

	/* Les constantes */
	/** the body */
	public final static double BLOB_BODY_LARGEUR = 0.10;
	public final static double BLOB_BODY_HAUTEUR = 0.09;
	/** the eyes */
	public final static double BLOB_EYES_LARGEUR = 0.006;
	public final static double BLOB_EYES_HAUTEUR = 0.011;
	public final static double BLOB_EYES_RAYON_LARGEUR = 0.004;
	public final static double BLOB_EYES_RAYON_HAUTEUR = 0.010;
	/** the link */
	public static String LINK_BLOB_SERVEUR ="blobServeur.png"; 
	public static String LINK_BLOB_CLIENT = "blobClient.png";
	/** bornes acc�l�ration */
	public static double MAX_ACCELERATION = 1;
	public static double SAUT_ACCELERATION = -20;
	/** Constantes physiques */
	public static final double CONSTANTE_DE_GRAVITATION = 9;
	public static final double MASSE_BALLE = 0.05;


	/** Le double singleton */
	public static Blob instanceServeur = new Blob(true, new PointSam(0.0,1.0));
	public static Blob instanceClient = new Blob(false, new PointSam(0.9,1.0));

	/* Les variables d'instance */
	/** Un boolean pour différencier le blob local du blob distant */
	private boolean isServeur;
	/** Un boolean pour savoir si le blob est en train de sauter */
	private boolean isJumping;

	/* Le constructeur */
	private Blob(boolean isServeur, PointSam p){
		super(p);
		this.setServeur(isServeur);
		this.isJumping = false;
	}


	/** Méthode permettant de tracer le blob dans le graphque passé en paramètre */
	public void paintBlob(Graphics g){

		/* On choisie la couleur de dessin en fonction du blob à déssiner */
		/* On trace dans un premier temps le body */
		String link = LINK_BLOB_CLIENT;
		if(this.isServeur)
			link = LINK_BLOB_SERVEUR;
		ImageIcon img = new ImageIcon(link);


		g.drawImage (img.getImage(),
				(int)(Pane.width*super.getPosition().getX()),
				(int)(Pane.height*super.getPosition().getY() - (int)(Blob.BLOB_BODY_HAUTEUR*Pane.height)),
				(int)(Blob.BLOB_BODY_LARGEUR*Pane.width), 
				(int)(Blob.BLOB_BODY_HAUTEUR*Pane.height),null );

		/* On trace ensuite les yeux */
		g.setColor(Color.black);
		PointSam centreOeil = new PointSam(
				super.getPosition().getX()+Blob.BLOB_BODY_LARGEUR*((this.isServeur)?(92-21):21)/92,
				super.getPosition().getY() + (19-37)*Blob.BLOB_BODY_HAUTEUR/37
				);
		double angleAvecBalle = Math.atan(-Pane.height*(Balle.instance.getPosition().getY()-centreOeil.getY())/(Pane.width*(Balle.instance.getPosition().getX()-centreOeil.getX())));
		int signeH = (Balle.instance.getPosition().getX()>centreOeil.getX())?1:-1;
		int signeV = (Balle.instance.getPosition().getY()>centreOeil.getY())?1:-1;
		g.fillOval((int)(Pane.width*(centreOeil.getX()+signeH*Math.abs(Math.cos(angleAvecBalle)*Blob.BLOB_EYES_RAYON_LARGEUR)-Blob.BLOB_EYES_LARGEUR/2)),
				(int)(Pane.height*(centreOeil.getY()+signeV*Math.abs(Math.sin(angleAvecBalle)*Blob.BLOB_EYES_RAYON_HAUTEUR)-Blob.BLOB_EYES_HAUTEUR/2)),
				(int)(Blob.BLOB_EYES_LARGEUR*Pane.width),
				(int)(Blob.BLOB_EYES_HAUTEUR*Pane.height)
				);


	}

	public void nextPosition(int typeOrdre){
		//System.out.println(super.getAcceleration().getY());
		switch(typeOrdre){
		case IServeur.ORDRE_RESTE : 
			/* On diminue par 2 la vitesse suivant l'axe des X */
			super.nouvelleVitesse(new PointSam(this.getVitesse().getX()/(1.04), this.getVitesse().getY()));
			break;

		case IServeur.ORDRE_GAUCHE :
			//System.out.println("GAUCHE");
			/* On augmente l'acc�lar�ation vers la gauche */
			if(this.getPosition().getX()>0){
				super.nouvelleAcceleration(new PointSam(
						Math.max(-Blob.MAX_ACCELERATION,this.getAcceleration().getX()-0.2),
						this.getAcceleration().getY()));	
			}
			break;

		case IServeur.ORDRE_DROITE :
			if((this.getPosition().getX()+ Blob.BLOB_BODY_LARGEUR)*Pane.width <((Pane.width/2 - Pane.width/100)) ){
				/* On augmente l'acc�lar�ation vers la gauche */
				super.nouvelleAcceleration(new PointSam(
						Math.min(Blob.MAX_ACCELERATION,this.getAcceleration().getX()+0.2),
						this.getAcceleration().getY()));
			}
			break;

		case IServeur.ORDRE_SAUT :
			/* On augmente l'acc�l�ration vers le haut */
			if(!this.isJumping){
				this.isJumping = true;
				super.nouvelleAcceleration(new PointSam(super.getAcceleration().getX(), Blob.SAUT_ACCELERATION));
			}
			else {
				super.nouvelleVitesse(new PointSam(this.getVitesse().getX()/(1.04), this.getVitesse().getY()));
			}
		}

		/* Cas g�n�raux quelque soit la touche touch�e appuy�e lors du timer */
		if(isJumping){
			/* On diminue la vitesse de mont�e pour faire redescendre le Blob */
			super.nouvelleAcceleration(new PointSam(this.getAcceleration().getX(), this.getAcceleration().getY()-Blob.SAUT_ACCELERATION/6));
		}
		if(this.isServeur){
			if(this.getPosition().getX()*Pane.width<=0 ){
				if(this.getPosition().getX()== 0){
					/* On fait le rebond */
					super.nouvelleVitesse(new PointSam(Math.abs(super.getVitesse().getX())/4, super.getVitesse().getY()));
					super.setAcceleration(new PointSam(0, super.getAcceleration().getY()));
				}
				else{
					/* On remet � la position la plus � gauche */
					super.nouvelleVitesse(new PointSam(Math.abs(super.getVitesse().getX())/2, super.getVitesse().getY()));
					super.setAcceleration(new PointSam(0, super.getAcceleration().getY()));
				}

			}
			if((this.getPosition().getX() + Blob.BLOB_BODY_LARGEUR)*Pane.width >= (int) (Pane.width/2 - Pane.width/100)){
				if((this.getPosition().getX() + Blob.BLOB_BODY_LARGEUR)*Pane.width == (int) (Pane.width/2 - Pane.width/100)){
					super.nouvelleVitesse(new PointSam(-Math.abs(super.getVitesse().getX())/4, super.getVitesse().getY()));
					super.setAcceleration(new PointSam(0, super.getAcceleration().getY()));
				}
				else{
					super.nouvelleVitesse(new PointSam(-Math.abs(super.getVitesse().getX())/2, super.getVitesse().getY()));
					super.setAcceleration(new PointSam(0, super.getAcceleration().getY()));
				}
			}
		}
		if((this.getPosition().getY()>1)){
			this.setPosition(new PointSam(this.getPosition().getX(), 1));
			this.setVitesse(new PointSam(this.getVitesse().getX(), 0));
			this.setAcceleration(new PointSam(this.getAcceleration().getX(), 0));
			if(isJumping)
				this.isJumping = false;
		}
	}


	/**
	 * @return the isJumping
	 */
	public boolean isJumping() {
		return isJumping;
	}


	/**
	 * @param isJumping the isJumping to set
	 */
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}


	/**
	 * @return the isServeur
	 */
	public boolean isServeur() {
		return isServeur;
	}


	/**
	 * @param isServeur the isServeur to set
	 */
	public void setServeur(boolean isServeur) {
		this.isServeur = isServeur;
	}


}
