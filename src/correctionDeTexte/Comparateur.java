package correctionDeTexte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Comparateur {		
	
	private String mot;
	private int i;
	
	public static HashSet<String> erreurs = new HashSet<>();	//HashSet contenant les mots non inclus dans le dictionnaire
	
	
	//on compare le texte mis en tokens avec le dictionnaire
	public static HashSet<String> comparer(ArrayList<String> listeMots,HashSet<String> dictionnaire) throws IOException{
		/*
		 * Méthode qui vérifie si chacun des mots de "listeMots" se trouve dans "dictionnaire"
		 * Les mots non reconnus sont stockés dans le HashSet "erreurs"
		 */
		
		for (String mot:listeMots) {	
				
	        if(!dictionnaire.contains(mot.toLowerCase())&& !erreurs.contains(mot.toLowerCase())){
						erreurs.add(mot); //mot ajouté dans HashSet<> erreurs
			}
						
		}
		
		return erreurs; //retour du HashSet contenant tous les mots ne se trouvant pas dans le dictionnaire
	}
	
	
}

