package correctionDeTexte;

import java.util.ArrayList;

public class Tokenizer {
	
public static String[] textTokens;
	
	public static ArrayList<String> listeTokUniques;

	//tokenizer 
	public static ArrayList<String> creerTokens(String texte) {
		/*
		 * m�thode qui prend une copie du "texte" et retourne un arrayList contenant les mots 
		 * (inspir� du code de la demo10)
		 */
		
		texte = texte.replaceAll("[^A-z0-9�-�]", " ");// on ne garde que les lettres, les nombres, les lettres accentu�es
		
		
		textTokens = texte.split(" +");// on decoupe le texte aux espaces simples (un espace suivie d'un caractere)
		
		listeTokUniques = new ArrayList<String>();// liste de stockage du vocabulaire 
		
		for(String tokenDisponible : textTokens ) {// on parcourt tous les tokens
			
			if(! listeTokUniques.contains(tokenDisponible)) {// si un token n a pas encore ete vu
				listeTokUniques.add(tokenDisponible);// on l enregistre
			}
			
		}
		return listeTokUniques;

}

}
