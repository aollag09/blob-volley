package serveur;

import interfaceCS.Balle;
import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.PointSam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import client.Main;

/**
 * Classe serveur en pattern singleton
 * 
 * <h1>Utilisation :</h1>
 * 
 * <h2>Démarrage :</h2>
 * if (Main.jeuEnCours){
 * 		Serveur.serveur = new Serveur(Main.port);
 *			    Thread t = new Thread(Serveur.serveur);
 *				t.start();
 *	}
 *
 * <h2>Arrêt :</h3>
 * Main.jeuEnCours = false;
 * t.join();
 * 
 * @author pyrrha
 *
 */
public class Serveur implements Runnable {

	/**
	 * Pattern singleton du serveur
	 */
	public static Serveur serveur;





	/**************VARIABLES LIEES AU JEU**************************************************/
	/**
	 * Constantes sur l'ordre que le joueur a effectué.
	 */
	private int ordreLocal = IServeur.ORDRE_RESTE, ordreDistant = IServeur.ORDRE_RESTE;






	/*************VARIABLES LIEES AUX CONNEXION AVEC LE SERVEUR**************************/
	/**
	 * ServerSocket du serveur
	 */
	private ServerSocket server;
	/**
	 * Socket du client potentiel.
	 */
	private Socket client;



	/**
	 * Constructeur serveur, initialisation.
	 * @param port le port du serveur
	 */
	private Serveur(){
		try {
			this.server = new ServerSocket(Main.port);
			this.client = null;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossible de créer le serveur !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Thread runnable pour mettre le serveur en multithreading.
	 */
	public void run() {
		ActionListener lAction = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Balle.instance.nextPosition();
				Blob.instanceClient.nextPosition(ordreDistant);
				Blob.instanceServeur.nextPosition(ordreLocal);
			}
		};
		Timer time = new Timer(IServeur.DELAY, lAction);
		time.start();
		
		try {
			while (Main.jeuEnCours){

				this.client = this.server.accept();

				BufferedReader reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
				String ligne;

				while ((ligne = reader.readLine()) != null) {
					/* Envoi des informations des positions */
					String[] coordonnees = new String[3];
					coordonnees[IServeur.JOUEUR_DISTANT] = Blob.instanceClient.getPosition().getX() + "," + Blob.instanceClient.getPosition().getY();
					coordonnees[IServeur.JOUEUR_LOCAL] = Blob.instanceServeur.getPosition().getX() + "," + Blob.instanceServeur.getPosition().getY();
					coordonnees[IServeur.BALLE] = Balle.instance.getPosition().getX() + "," + Balle.instance.getPosition().getY();
					String aEnvoyer = "";
					for (int i=0;i<3;i++){
						aEnvoyer+=coordonnees[i]+";";
					}
					this.client.getOutputStream().write(aEnvoyer.getBytes());

					/* On récupère les ordres suivants */
					String[] infos = ligne.split(":");
					if (infos.length==2){
						String joueur = infos[0], ordre = infos[1];
						try {
							int lOrdre = Integer.parseInt(ordre);
							if (joueur.equals("l")){
								this.ordreLocal = lOrdre;
							}
							else if (joueur.equals("d")){
								this.ordreDistant = lOrdre;
							}
						} catch (Exception e){
						}
					}
				}
			}
			time.stop();
			this.client.close();
			this.server.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossible de créer le serveur !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}
}