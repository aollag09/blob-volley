package serveur;

import interfaceCS.Balle;
import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.Partie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

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
	private ServerSocket server = null;
	/**
	 * Socket du client potentiel.
	 */
	private Socket client = null;



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
	
	public boolean isLaunched(){
		return this.server != null;
	}

	public static void launchServeur(){
		Serveur.serveur = new Serveur();
		if (Serveur.serveur.isLaunched()){
			Thread t = new Thread(Serveur.serveur);
			t.start();
		}
	}
	
	public void setOrdreLocal(int o){
		this.ordreLocal = o;
	}

	/**
	 * Thread runnable pour mettre le serveur en multithreading.
	 */
	public void run() {
		try {
			JDialog attente = new JDialog(Main.menu, "Attente de connexion", false);
			
			attente.add(new JLabel("En attente d'une connexion..."));
			attente.pack();
			attente.setLocationRelativeTo(null);
			attente.setVisible(true);
			attente.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			this.client = this.server.accept();

			Main.iserveur = new IServeur();
			attente.dispose();	

			Main.menu.dispose();
			Main.lancerJeu();

			ActionListener lAction = new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					Balle.instance.nextPosition();
					Blob.instanceClient.nextPosition(ordreDistant);
					Blob.instanceServeur.nextPosition(ordreLocal);
				}
			};
			Timer time = new Timer(IServeur.DELAY, lAction);
			time.start();

			
			while (Main.jeuEnCours){

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
					aEnvoyer+=Main.partieEnCours.getScoreServeur()+","+Main.partieEnCours.getScoreClient();
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