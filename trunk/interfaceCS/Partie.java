package interfaceCS;

/**
 * Classe représentant une partie !!
 * @author pyrrha
 *
 */
public class Partie {

	/**
	 * Les scores des joueurs
	 */
	private int scoreClient, scoreServeur;
	
	/**
	 * Les constantes d'état du jeu
	 */
	public final static int SERVEUR_GAGNE = 1;
	public final static int CLIENT_GAGNE = -1;
	public final static int PAS_GAGNANT = 0;
	
	/**
	 * Les constantes de condition de victoire
	 */
	public final static int POINTS_GAGNANTS = 21;
	public final static int POINTS_DIFF = 2;
	
	public Partie(){
		this.scoreClient = 0;
		this.scoreServeur = 0;
	}
	
	
	/**
	 * Fonction à appeler si le client marque, on retourne l'état de la partie
	 * @return
	 */
	public int clientMarque(){
		this.scoreClient++;
		return getEtat();
	}
	
	/**
	 * Foction à appeler si le serveur marque, on retourne l'état de la partie
	 * @return
	 */
	public int serveurMarque(){
		this.scoreServeur++;
		return getEtat();
	}
	
	
	/**
	 * retourne l'état de la partie, qui a gagné ou si ca joue enccore
	 * @return
	 */
	public int getEtat(){
		if (this.scoreServeur>Partie.POINTS_GAGNANTS-1 && this.scoreServeur+Partie.POINTS_DIFF-1>this.scoreClient){
			return Partie.SERVEUR_GAGNE;
		}
		else if (this.scoreClient>Partie.POINTS_GAGNANTS-1 && this.scoreClient+Partie.POINTS_DIFF-1>this.scoreServeur){
			return Partie.CLIENT_GAGNE;
		}
		else {
			return Partie.PAS_GAGNANT;
		}
	}


	public int getScoreClient() {
		return scoreClient;
	}


	public int getScoreServeur() {
		return scoreServeur;
	}


	public void setScoreClient(int scoreClient) {
		this.scoreClient = scoreClient;
	}


	public void setScoreServeur(int scoreServeur) {
		this.scoreServeur = scoreServeur;
	}
	
	
}
