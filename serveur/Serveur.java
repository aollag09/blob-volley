package serveur;

import interfaceCS.Balle;
import interfaceCS.Blob;
import interfaceCS.IServeur;
import interfaceCS.PointSam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import javax.swing.JOptionPane;

import client.Main;

/**
 * Classe serveur en pattern singleton
 * @author pyrrha
 *
 */
public class Serveur {
	
	/**
	 * Pattern singleton du serveur
	 */
	public static Serveur serveur;
	
	/**
	 * Constantes sur l'ordre que le joueur a effectué.
	 */
	private int ordreLocal = IServeur.ORDRE_RESTE, ordreDistant = IServeur.ORDRE_RESTE;
	
	/**
	 * Port du serveur.
	 */
	private int port;
	
	/**
	 * Constructeur serveur, initialisation.
	 * @param port le port du serveur
	 */
	private Serveur(int port){
		this.port = port;
		
		try {
			ServerSocket server = new ServerSocket(port);
			Socket client = null;
			
			while (Main.jeuEnCours){
				
				client = server.accept();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String ligne;
				
				while ((ligne = reader.readLine()) != null) {
					System.out.println(ligne);
				}
				
			}

			client.close();
			server.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossible de créer le serveur !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * 
	 * @return le port du serveur
	 */
	public int getPort(){
		return this.port;
	}
	
	public static void main(String[] a){
		System.out.println("Demarrage du serveur");
		Serveur s = new Serveur(12345);
	}
	
}