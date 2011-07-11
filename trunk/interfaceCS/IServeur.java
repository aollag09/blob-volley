package interfaceCS;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import client.Main;


/**
 * Classe regroupant les différents connexions possibles au serveur, les fonctions sont générique afin que, quelque soit le type de serveur, le transfert de données se fasse bien.
 * @author pyrrha
 *
 */
public class IServeur {
	/**
	 * Delai de connexion définissant le temps entre chaque requête
	 */
	public static int DELAY = 20;
	
	/**
	 * Constantes qui définissent le type du serveur.
	 */
	public static final boolean SERVEUR_LOCAL = true, SERVEUR_DISTANT = false;
	
	/**
	 * Constantes qui définissent la positions des différentes données dans les tableaux échangés.
	 */
	public static final int BALLE = 0, JOUEUR_LOCAL = 1, JOUEUR_DISTANT = 2, SCORE = 3;

	/**
	 * Constantes qui définissent les différents ordre des controles du joueur : gauche, immmobile, droite
	 */
	public static final int ORDRE_GAUCHE = -1, ORDRE_RESTE = 0, ORDRE_DROITE = 1, ORDRE_SAUT = 2; 
	
	
	
	/******************************************************************************************************/
	/************DEBUT DE LA PARTIE CLASSE*****************************************************************/
	/******************************************************************************************************/
	
	/** 
	 * Adresse de connexion au serveur
	 */
	private String addr;
	/** 
	 * Port de connexion au serveur
	 */
	private int port;
	/** 
	 * Est ce que le serveur est local ?
	 */
	private boolean local;
	
	
	/**
	 * Stream de sortie vers le serveur
	 */
	private OutputStream oStream;
	/**
	 * Stream d'entree depuis le serveur
	 */
	private InputStream iStream;
	/**
	 * Socket gérant les connexion au serveur
	 */
	private Socket s;
	
	
	
	/**
	 * Constructeur de l'interface au serveur si le serveur est local.
	 */
	public IServeur(){
		this.local = SERVEUR_LOCAL;
		this.addr = "localhost";
		this.port = Main.port;
		
		demarrage();
	}
	
	/**
	 * Constructeur de l'interface au serveur si le serveur est distant.
	 * @param adresseIp l'adresse ip du serveur
	 * @param port le port du serveur
	 */
	public IServeur(String adresseIp, int port){
		this.local = SERVEUR_DISTANT;
		this.addr = adresseIp;
		this.port = port;
		
		demarrage();
	}
	
	/**
	 * Fonction permettant d'instancer les différentes variables gérant les connexions au serveur.
	 */
	public void demarrage(){
		try {
			this.s = new Socket(this.addr, this.port);
			
			this.oStream = this.s.getOutputStream();
			this.iStream = this.s.getInputStream();
			
			
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "L'adresse IP spécifiée n'est pas valide !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "La connexion au serveur n'a pas eu s'effectuer !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	/**
	 * Envoie l'ordre que fait l'utilisateur et retourne les différentes coordonnées et le score.
	 * @param ordre
	 * @return retourne les coordonnées des différents objets et le score
	 */
	public Object[] envoyerDonnees(int ordre){
		// Envoie des données concernant le déplacement local.
		try {
			this.oStream.write((((this.local==SERVEUR_LOCAL)?"l:":"d:")+(""+ordre+"\n")).getBytes());
			this.oStream.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "La connexion au serveur n'est plus effective !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		
		// Récupération des différentes coordonnées.
		String coord = "";
		try {
			byte[] b = new byte[1000];
			int bitsRecus = 0;
			System.out.println("avant read");
			if((bitsRecus = this.iStream.read(b)) >= 0) {
				System.out.println("dans read");
				coord = new String(b, 0, bitsRecus);
				System.out.println("coord : " + coord);
			}
			System.out.println("apres read");
		} catch (IOException e){
			JOptionPane.showMessageDialog(null, "La connexion au serveur n'est plus effective !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		
		// Si le message est exit, on quitte le programme par la variable jeuEnCours.
		if (coord.equals("exit")){
			Main.jeuEnCours = false;
			return null;
		}
		
		// Si le message n'est pas exit et n'est pas vide, il essaye de le décrypter et retourne les coordonnées.		
		if (!coord.equals("")){
			String[] lesTrois = coord.split(";");
			
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
			
			int scoreServeur, scoreClient;
			try {
				String[] temp = lesTrois[IServeur.SCORE].split(",");
				scoreServeur = Integer.parseInt(temp[0]);
				scoreClient = Integer.parseInt(temp[1]);
			} catch (Exception e){
				scoreServeur = 0;
				scoreClient = 0;
			}
			
			PointSam[] coordour = new PointSam[3];
			coordour[IServeur.BALLE] = balle;
			coordour[IServeur.JOUEUR_DISTANT] = joueurDistant;
			coordour[IServeur.JOUEUR_LOCAL] = joueurLocal;
			Object[] retour = new Object[2];
			retour[0] = coordour;
			int[] scores = new int[2];
			scores[0] = scoreServeur;
			scores[1] = scoreClient;
			retour[1] = scores;
			return retour; 
		}
		else {
			return null;
		}
		
		
	}
	
	/**
	 * 
	 * @return le type de serveur (local, distant)
	 */
	public boolean getTypeServeur(){
		return this.local;
	}
	
	/**
	 * Ferme les connexions avec le serveur.
	 */
	public void fermerServeur(){
		try {
			this.oStream.write("exit".getBytes());
		} catch (IOException e1) {
		}
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
