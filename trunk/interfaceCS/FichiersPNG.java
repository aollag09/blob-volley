package interfaceCS;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * @version 1.0
 * <P>
 */
public class FichiersPNG extends FileFilter {
	// Classe servant a n'afficher que les fichiers PNG lors de la sauvegarde de l'image	
	public static boolean accepts(File f) {
		String n=f.getName().toLowerCase();
		int l=n.length();
		char ex0=n.charAt(l-4);
		char ex1=n.charAt(l-3);
		char ex2=n.charAt(l-2);
		char ex3=n.charAt(l-1);
		return (ex0=='.' && ex1=='p' && ex2=='n' && ex3=='g');
	}
	public boolean 	accept(File f) {
		if (f.isDirectory()) {
			return true; 
		}
		String n=f.getName().toLowerCase();
		int l=n.length();

		char ex0=n.charAt(l-4);
		char ex1=n.charAt(l-3);
		char ex2=n.charAt(l-2);
		char ex3=n.charAt(l-1);
		return (ex0=='.' && ex1=='p' && ex2=='n' && ex3=='g');
	}
	public String 	getDescription() {
		return "fichiers PNG";
	}
}
